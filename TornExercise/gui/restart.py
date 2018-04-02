from collections import OrderedDict
import json


def restart_command(reqinfo_list, test_name):
    for value in list(reqinfo_list.keys()):
        if value == test_name:
            break
        del reqinfo_list[value]
    return reqinfo_list


def main():
    with open('RequestInfo.json', 'r') as rp:
        data = json.load(rp, object_pairs_hook=OrderedDict)

    data = data['StdReqInfo']
    trimmed_dict = restart_command(data, 'Decompression')
    print(len(trimmed_dict))

    k, _ = trimmed_dict.popitem(last=False)
    print('I will start running tests from', k)


if __name__ == '__main__':
    main()
