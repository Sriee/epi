import json
import os


def rev():
    line, fh = '', None

    try:
        fh = open('data/events.json', 'r')
        fh.seek(0, os.SEEK_END)

        position = fh.tell()

        while position >= 0:
            fh.seek(position)
            char = fh.read(1)
            if char == '\n':
                yield line[::-1]
                line = ''
            else:
                line += char
            position -= 1
        yield line[::-1]
    except IOError:
        print 'Error while reading reverse'
    finally:
        if fh is not None:
            fh.close()


if __name__ == '__main__':

    stats = {}
    for line in rev():
        if 'test.completed' in line:
            stats = json.loads(line)
            break

    non_fuzz_iteration, fuzzing_stats = stats['data']['non_fuzz_iterations'], stats['data']['fuzzing_statistics']
    print fuzzing_stats
