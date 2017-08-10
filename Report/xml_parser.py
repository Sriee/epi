import os
import sys
import xml.etree.ElementTree as x


class XML_Parser(object):

    def __init__(self, file_name):

        if not file_name:
            print 'File name required.'
            sys.exit(-1)

        if os.path.isfile(file_name):
            self.file_name = file_name
        else:
            print '\'{}\' File not found.'.format(file_name)
            sys.exit(-1)

        try:
            self._parser = x.parse(self.file_name)
            self._root = self._parser.getroot()
        except x.ParseError:
            print 'Invalid XML file.'
            sys.exit(-1)

    @property
    def root(self):
        return self._root.tag

    @property
    def parser(self):
        if self._parser:
            return self._parser
        return None

    @property
    def keys(self):
        return [element.tag for element in self._root]

    def child(self, tag):
        try:
            if tag in self:
                return self._root[self.keys.index(tag)]
            return None
        except ValueError:
            return None

    def __contains__(self, tag):
        return tag in self.keys

    def __repr__(self):
        return '{!r}'.format(self.parser)

    def __str__(self):
        return 'XML_Parser(file_name={}, root={})'.format(self.file_name, self.root)


def main():
    file_name = 'data/feed.xml'
    parser = XML_Parser(file_name)
    print repr(parser)
    print parser
    print parser.keys
    for child in parser.child('link'):
        print child.tag

    print parser.child('howdy')


if __name__ == '__main__':
    main()
