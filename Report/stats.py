import json
import os
import sys

class Stats(object):

    def __init__(self, file_name):
        self.file_name = file_name
        self._data, self._stat= None, None
        if not os.path.isfile(self.file_name):
            print 'Cannot find \'{}\''.format(self.file_name)
            sys.exit(-1)

    @property
    def data(self):
        return self._data

    @data.setter
    def data(self, value):
        self._data = value

    @property
    def stat(self):
        return self._stat

    @stat.setter
    def stat(self, value):
        self._stat = value

    @property
    def non_fuzz_iteration(self):
        if self._data is None:
            return None

        return self._data.get('non_fuzz_iteration', None)



    def reverse(self):
        line, fh = '', None

        try:
            fh = open(self.file_name, 'r')
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
            print 'Internal Stat Error'
        finally:
            if fh is not None:
                fh.close()

    def __enter__(self):
        try:
            for line in self.reverse():
                if 'test.completed' in line:
                    self.data = json.loads(line)
                    break

            self.stat = self.data.get('fuzzing_statistics', None)
        except json.JSONDecoder:
            print 'hi'

        return self

    def __exit__(self, *args):
        pass


if __name__ == '__main__':

    with Stats('data/chuma.json') as s:
        print s
