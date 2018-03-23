from datetime import datetime
import pytz
import math
import os

# TODO: Enable logging. Add the logging configuration with the program
# TODO: Add comments (low priority)
# TODO: Add perforce logic

def diff_time(start, end):
    diff = end - start
    return math.ceil(diff.seconds / 60)


def main():
    stop, start_time = False, datetime.now(pytz.timezone('US/Pacific')).replace(tzinfo=None)
    previous = start_time
    while not stop:
        current = datetime.now(pytz.timezone('US/Pacific')).replace(tzinfo=None)
        if diff_time(previous, current) >= 5:
            with open('temp.txt', 'a') as wp:
                wp.write(start_time.strftime('%a, %m/%d/%Y %H:%M:%S') + os.linesep)
            previous = current
            print('Written to file')

        if diff_time(start_time, current) >= 20:
            stop = True
            print('Stopping!..')


if __name__ == '__main__':
    main()
