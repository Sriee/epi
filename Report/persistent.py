import os
import sys
import shelve


root_dir, option, with_pts, without_pts, choice = '', -1, [], [], ('with_pts', 'without_pts')


def is_shelve_file():
    return os.path.isfile('.pts.db')


def shelve_data():
    db = None
    try:
        db = shelve.open('.pts', writeback=True)
        db['with_pts'] = with_pts
        db['without_pts'] = without_pts
    finally:
        db.close()


def un_shelve_data(opt):

    if not is_shelve_file():
        print('Could not find shelved file')
        return

    read = shelve.open('.pts.db', writeback=True)
    try:
        return read[opt]
    finally:
        read.close()


def print_values(data):
    data_length = len(data)
    for i in range(0, data_length, 6):
        print('{0:<{length}}\t{1:<{length}}\t{2:<{length}}\t{3:<{length}}\t{4:<{length}}\t{5:<{length}}'
              .format(data[i] if i < data_length else '', data[i + 1] if i + 1 < data_length else '',
                      data[i + 2] if i + 2 < data_length else '', data[i + 3] if i + 3 < data_length else '',
                      data[i + 4] if i + 4 < data_length else '', data[i + 5] if i + 5 < data_length else '',
                      length=8))


def create_new_shelf():
    sub_directories = filter(lambda file_name: file_name not in ['README.md', '.DS_Store', '.gitignore'],
                             os.listdir(root_dir))
    for sub in sub_directories:
        current_version = filter(lambda file_name: file_name not in ['README.md', '.DS_Store', '.gitignore'], 
                                 os.listdir(os.path.join(root_dir, sub)))[-1]

        if os.path.isdir(os.path.join(root_dir, sub, current_version, 'test')):
            if len(filter(lambda name: name.endswith('.pts'),
                          os.listdir(os.path.join(root_dir, sub, current_version, 'test')))) == 1:
                with_pts.append(sub)
            else:
                without_pts.append(sub)
        else:
            without_pts.append(sub)

    # Shelve the file
    shelve_data()


def main():

    # Use the persistent storage
    if is_shelve_file():
        print 'Protocols with PTS files.' if option == 1 else 'Protocols without PTS files.'
        print_values(un_shelve_data(choice[option - 1]))
        return
    else: 
        create_new_shelf()
        print 'Protocols with PTS files.' if option == 1 else 'Protocols without PTS files.'
        print_values(un_shelve_data(choice[option - 1]))

if __name__ == '__main__':

    if len(sys.argv) != 3:
        print 'ERROR: Invalid Number of arguments.\nSyntax:'
        print 'python check_for_pts.py \'path/to/protocols/folder\' {1, 2}'
        sys.exit(-1)

    root_dir, option = sys.argv[1], int(sys.argv[2])

    if not str(root_dir).startswith('/'):
        root_dir = os.path.join(os.curdir, root_dir)

    if not os.path.isdir(root_dir):
        print 'ERROR: {} not a directory.\nSyntax:'.format(root_dir)
        print 'python check_for_pts.py \'path/to/protocols/folder\' {1, 2}'
        sys.exit(-1)

    if option not in (1, 2):
        print 'ERROR: Incorrect option, choose between [1, 2].\nSyntax:'
        print 'python check_for_pts.py \'path/to/protocols/folder\' {1, 2}'
        sys.exit(-1)

    main()
