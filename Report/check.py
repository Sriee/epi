import os
import sys

root_dir = ''
protocols_with_pts, protocols_without_pts = [], []


def main():
    sub_directories = filter(lambda file_name: file_name not in ['README.md',
                                                                 '.DS_Store', '.gitignore'], os.listdir(root_dir))
    for sub in sub_directories:
        current_version = filter(lambda file_name: file_name not in
                                 ['README.md', '.DS_Store', '.gitignore'],
                                 os.listdir(os.path.join(root_dir, sub)))[-1]

        if os.path.isdir(root_dir, current_version, 'test'):
            if len(filter(lambda name: name.endswith('.pts'), os.listdir(root_dir, sub, current_version, 'test'))) == 1:
                protocols_with_pts.append(sub)
            else:
                protocols_without_pts.append(sub)
        else:
            protocols_without_pts.append(sub)

    if choice == 1:
        print(', '.join(protocols_with_pts))
    else:
        print(', '.join(protocols_with_pts))


if __name__ == '__main__':

    print sys.argv
    if len(sys.argv) != 3:
        print('Incorrect number of arguments.')
        sys.exit(-1)

    root_dir, choice = sys.argv[1], sys.argv[2]

    if not os.path.isdir(root_dir):
        print('{} is not a directory.'.format(root_dir))
        sys.exit(-1)

    main(choice)
