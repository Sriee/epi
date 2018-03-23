from datetime import datetime
import pytz
import math

# TODO: Enable logging. Add the logging configuration with the program
# TODO: Add comments (low priority)

def diff_time(start, end):
    diff = end - start
    return math.ceil(diff.seconds / 60)


def main():
    stop, start_time = False, datetime.now(pytz.timezone('US/Pacific')).replace(tzinfo=None)
    previous = start_time
    while not stop:
        current_time = datetime.now(pytz.timezone('US/Pacific')).replace(tzinfo=None)
        if diff_time(previous, current_time) >= 5:
            with open('temp.txt', 'a') as wp:
                wp.write(current_time.strftime('%a, %m/%d/%Y %H:%M:%S') + '\n')
            previous = current_time
            print('Written to file')

        if diff_time(start_time, current_time) >= 20:
            stop = True
            print('Stopping!..')


if __name__ == '__main__':
    main()
