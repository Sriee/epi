import os
import shutil
from queue import Queue, Empty
import threading
import sqlite3
import json
import logging.config

# Get the loggers
consoleLogger = logging.getLogger('harley.console')
fileLogger = logging.getLogger('harley.file')

thread_local = threading.local()


class ParallelLoader(object):
    def __init__(self):
        self.db_file = os.path.join('database', 'test.db')
        self.test_case_queue = None
        self.populated = 0
        self.connection = sqlite3.connect(os.path.join('database', 'test.db'))
        self.cursor = self.connection.cursor()

    def populate_test_case(self):
        statement = 'INSERT INTO Borrower VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)'
        setattr(thread_local, 'load', [])
        thread_local.line, thread_local.each_line = '', ''
        while True:
            try:
                task = self.test_case_queue.get()

                if task is None:
                    fileLogger.debug(
                        'Exiting {}'.format(threading.current_thread().getName()))
                    break

                name = 'borrow_{}.csv'.format(task // 100)

                with open(os.path.join('files', name), 'r') as fp:
                    for thread_local.line in fp:
                        thread_local.each_line = thread_local.line.split(',')
                        thread_local.each_line[5] = thread_local.each_line[5][1:-1]
                        thread_local.each_line[8] = thread_local.each_line[8][1:-2]

                        thread_local.load.append(tuple(thread_local.each_line))

                fileLogger.info('{0} processed \'{1}\''
                                    .format(threading.current_thread().getName(), name))
                load_length = len(thread_local.load)
                fileLogger.info('Will load {0}-{1}'
                                    .format(thread_local.load[0][0],
                                            thread_local.load[load_length - 1][0]))

                lock = threading.Lock()

                with lock:
                    connection = sqlite3.connect(self.db_file)
                    cursor = connection.cursor()
                    cursor.executemany(statement, thread_local.load)
                    connection.commit()

                    # Print status
                    stat = '{} added {}'.format(threading.current_thread().getName(),
                                                self.populated)

                    self.populated += 100
                    print('{}-{} entries'.format(stat, self.populated))

                    thread_local.load.clear()
                    cursor.close()
                    connection.close()

                    self.test_case_queue.task_done()
            except (sqlite3.OperationalError, sqlite3.IntegrityError) as e:
                print(thread_local.load[0])
                print(e)


    def start(self):
        num_threads, offset, count, threads = -1, 0, 1000, []

        if count < 100:
            num_threads = 1
        elif 100 < count < 1000:
            num_threads = count % 100
        else:
            num_threads = 5

        # Initialize the number of threads
        fileLogger.info('Number of threads: {}'.format(num_threads))
        self.test_case_queue = Queue(num_threads)

        for i in range(1, num_threads + 1):
            t = threading.Thread(target=self.populate_test_case)
            threads.append(t)

        fileLogger.debug('Starting the threads.')
        # Start the threads
        for j in range(num_threads):
            threads[j].start()

        # Load the tasks
        while offset < count:
            self.test_case_queue.put(offset)
            offset += 100

        # Block until all the items in the queue are completed
        self.test_case_queue.join()

        # Stop test case loader threads 
        for l in range(num_threads):
            self.test_case_queue.put(None)

        # Kill the threads after loading the test case table
        for k in range(num_threads):
            threads[k].join()

        fileLogger.info('Threads completed tasks.')

        # Check whether the test case table is correctly populated
        if self.check_test_case(count):
            consoleLogger.info('Test Case table populated successfully.')
        else:
            consoleLogger.info('Populating Test case table failed.')

    def check_test_case(self, entries):
        # Delete entries
        self.cursor.execute('SELECT COUNT(*) FROM BORROWER')
        self.connection.commit()

        return entries == self.cursor.fetchone()[0]


def create_files():
    file_count = 0
    file_name = os.path.join('files', 'borrow_{}.csv'.format(file_count))

    with open('borrowers.csv', 'r') as rp:
        file_out = open(file_name, "w")
        for line_count, line in enumerate(rp, 1):
            file_out.write(line)
            if line_count % 100 == 0:
                file_out.close()
                file_count += 1
                file_name = os.path.join('files', 'borrow_{}.csv'.format(file_count))
                file_out = open(file_name, "w")

        file_out.close()
        os.remove(file_name)


def setup_logging(default_path='config/log_config.json', default_level=logging.INFO):
    """Setup logging configuration"""
    path = default_path
    if os.path.exists(path):
        with open(path, 'r') as f:
            config = json.load(f)
        logging.config.dictConfig(config)
    else:
        logging.basicConfig(level=default_level)


if __name__ == '__main__':

    setup_logging()
    pl = ParallelLoader()

    # Remove files directory
    if os.path.isdir('files'):
        consoleLogger.info('Removing \'files\' directory')
        shutil.rmtree('files')

    os.mkdir('files')
    create_files()

    conn = sqlite3.connect(os.path.join('database', 'test.db'))
    cur = conn.cursor()

    try:
        # Delete entries
        cur.execute('SELECT COUNT(*) FROM BORROWER')
        conn.commit()

        num_entries = cur.fetchone()

        if num_entries[0] != 0:
            cur.execute('DELETE FROM BORROWER')
            conn.commit()

        pl.start()
    finally:
        cur.close()
        conn.close()
        logging.shutdown()
