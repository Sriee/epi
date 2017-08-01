import os
import sys
import xml.etree.ElementTree as x


class XML_Parser(object):

    def __init__(file_name):
        if os.path.isfile(os.path.join('data', file_name)):
            self.file_name = file_name
        else:
            print '\'{}\' File not found.'.format(file_name)
            sys.exit(-1)

def main():
    file_name = 'feed.xml'
    if os.path.isfile(os.path.join('data', file_name)):
        print '\'{}\' File Found'.format(file_name)
        tree = x.parse(os.path.join('data', file_name))
        root = tree.getroot()
        print root
    else:
        print '\'{}\' File not found.'.format(file_name)
        sys.exit(-1)


if __name__ == '__main__':
    main()
