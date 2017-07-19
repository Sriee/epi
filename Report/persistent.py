import os
import sys
import shelve


root_dir, with_pts, without_pts, choice = '', [], [], ('with_pts', 'without_pts')


def is_shelve_file():
    return os.path.isfile('.pts')


def shelve_data(len_1, len_2):
    with shelve.open('.pts', writeback=True) as db:
        db['with_pts'] = with_pts
        db['without_pts'] = without_pts
        db['0'] = len_1
        db['1'] = len_2


def un_shelve_data(opt):

    if not is_shelve_file():
        print('Could not find shelved file')
        return

    read = shelve.open('.pts', writeback=True)
    try:
        return read[opt]
    finally:
        read.close()


def main(opt):

    # Use the persistent storage
    if is_shelve_file():
        data = un_shelve_data(choice[opt - 1])
        fmt_length = un_shelve_data(str(opt - 1))
        for i in range(0, len(data), 3):
            print('{0:<{length}}\t{1:<{length}}\t{2:<{length}}'
                  .format(data[i], data[i + 1], data[i + 2], length=fmt_length))
        return

    max_length, max_length_n = -1, -1
    files = os.listdir(root_dir)
    for file_name in files:
        if str(file_name).endswith('.mp3'):
            with_pts.append(file_name)

            if len(file_name) > max_length:
                max_length = len(file_name)
        else:
            without_pts.append(file_name)
            if len(file_name) > max_length_n:
                max_length_n = len(file_name)

    # Shelve the file
    shelve_data(max_length, max_length_n)

    # Print values
    print('Protocols with PTS')
    length_with_pts = len(with_pts)
    for i in range(0, length_with_pts, 3):
        print('{0:<{length}}\t{1:<{length}}\t{2:<{length}}'
              .format(with_pts[i] if i < length_with_pts else '',
                      with_pts[i + 1] if i + 1 < length_with_pts else '',
                      with_pts[i + 2] if i + 2 < length_with_pts else '',
                      length=max_length))

    print('\nProtocols without PTS')
    length_without_pts = len(without_pts)
    for i in range(0, length_without_pts, 3):
        print('{0:<{length}}\t{1:<{length}}\t{2:<{length}}'
              .format(without_pts[i] if i < length_without_pts else '',
                      without_pts[i + 1] if i + 1 < length_without_pts else '',
                      without_pts[i + 2] if i + 2 < length_without_pts else '',
                      length=max_length_n))


if __name__ == '__main__':

    if len(sys.argv) != 3:
        print('Invalid Number of arguments.')
        sys.exit(-1)

    root_dir, option = sys.argv[1], int(sys.argv[2])

    if not os.path.isdir(root_dir):
        print('{} not a directory'.format(root_dir))
        sys.exit(-1)

    if option not in (1, 2):
        print('Incorrect option, choose between [1, 2]')
        sys.exit(-1)

    main(option)
