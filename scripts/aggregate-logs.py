import os
import sys


def main():
    check_program_args()

    working_dir = sys.argv[1]
    target_file = sys.argv[2]
    write_file = sys.argv[3]
    folders = next(os.walk(working_dir))[1]

    with open(os.path.join(working_dir, write_file), 'w') as outfile:
        for folder in folders:
            file_with_path = os.path.join(working_dir, folder, target_file)
            if os.path.isfile(file_with_path):
                with(open(file_with_path)) as infile:
                    for line in infile:
                        outfile.write(line)
                    outfile.write(os.linesep)


def check_program_args():
    assert len(sys.argv) == 4


if __name__ == '__main__':
    main()
