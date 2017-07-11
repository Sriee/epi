import os
import json


class Performance(object):
    def __init__(self, status):
        self.test_case = {}
        self._names = []
        self._test_type = ''
        self._protocol_name = ''
        self._max_length = -1
        with open( os.path.join('Performance', status), 'r') as inp:
            d = json.load(inp)
        self.load_object(d)

    def load_object(self, inp):

        self.test_type = inp['test_type']
        self.protocol_name = inp['protocol']
        for item in inp['testcases']:
            name = item['name']

            if len(name) > self.max_length:
                self.max_length = len(name)

            self.test_case[name] = {}
            self.test_case[name]['status'] = item['status']
            self.test_case[name]['item_id'] = item['item_id']
            self.test_case[name]['iterations'] = item['iterations']
            self.test_case[name]['total_time'] = item['total_time']
        else:
            print('Object loaded')

    def __contains__(self, item):
        return item in self.test_case

    def __len__(self):
        if self.test_case is None:
            return 0
        else:
            return len(self.test_case)

    def __iter__(self):
        self.index = -1
        self.names = self.test_case.keys()
        return self

    def next(self):
        try:
            while self.index < self.__len__():
                self.index += 1
                return self.names[self.index]
        except IndexError:
            raise StopIteration

    def __getitem__(self, item):
        return self.test_case[item] if item in self.test_case else None

    def __le__(self, other):
        return self.max_length <= other.max_length

    def __ge__(self, other):
        return self.max_length >= other.max_length

    def get_keys(self):
        return self.test_case.keys()

    @property
    def names(self):
        return self._names

    @names.setter
    def names(self, value):
        self._names = value

    @property
    def test_type(self):
        return self._test_type

    @test_type.setter
    def test_type(self, value):
        self._test_type = value

    @property
    def protocol_name(self):
        return self._protocol_name

    @protocol_name.setter
    def protocol_name(self, value):
        self._protocol_name = value

    @property
    def max_length(self):
        return self._max_length

    @max_length.setter
    def max_length(self, value):
        self._max_length = value

    def __str__(self):
        return 'Performance(uuid: {}, Test_cases: {}'.format(self.test_case.keys() if
                                                             self.test_case else 'Empty')

    def __repr__(self):
        return str(self.test_case)



def compare(first, second):

    first_set, second_set = set(first.get_keys()), set(second.get_keys())
    left, right, lines, name_length = None, None, [], (first.max_length if first <= second else second.max_length) + 4
    tt_length, lim = max(len(first.test_type), len(second.test_type)), '-' * (45 + name_length)

    lines.append(lim)
    lines.append('| {:^9} | {:^{length}} | {:^11} | {:^12} |'
                 .format('Item Id', 'Test Case Name', str(first.test_type).title(), str(second.test_type).title(),
                         length=name_length, tt=tt_length))
    lines.append('| {:^9} | {:^{length}} | {} |'.format(' ', ' ', 'Time taken / Iteration(ms)', '|',
                                                               length=name_length))
    lines.append('|{:11}|{:{length}}|{}|'.format('-' * 11, '-' * (name_length + 2), '-' * 28, length=name_length))

    # Finding the intersection
    common = first_set.intersection(second_set)

    for element in common:

        if first[element]['status'] == 'FAILED':
            left = 'None'
        else:
            left = float(first[element]['total_time']) / first[element]['iterations']

        if second[element]['status'] == 'FAILED':
            right = 'None'
        else:
            right = float(second[element]['total_time']) / second[element]['iterations']

        each_line = '| {}-{} | {:{length}} | {:{left_inner}} | {:{right_inner}} {:>2}' \
            .format(first[element]['item_id'], second[element]['item_id'], element, left, right, '|',
                    left_inner='<11' if left == 'None' else '<11.3f', length=name_length,
                    right_inner='<11' if right == 'None' else '<11.3f')
        lines.append(each_line)

    # Finding the difference
    diff = list(first_set ^ second_set)

    for element in diff:
        if element in first:
            if first[element]['status'] == 'FAILED':
                left = 'None'
            else:
                left = float(first[element]['total_time']) / first[element]['iterations']

            each_line = '| {}-{} | {:{length}} | {:{left_inner}} | {:<11} {:>2}' \
                .format(first[element]['item_id'], 'None', element, left, 'None', '|', length=name_length,
                        left_inner='<11' if left == 'None' else '<11.3f')
            lines.append(each_line)
        else:
            if second[element]['status'] == 'FAILED':
                right = 'None'
            else:
                right = float(first[element]['total_time']) / first[element]['iterations']

            each_line = '| {}-{} | {:{length}} | {:<11} | {:{right_inner}} {:>2}' \
                .format('None', second[element]['item_id'], element, 'None', right, '|', length=name_length,
                        right_inner='<11' if right == 'None' else '<11.3f')
            lines.append(each_line)

    lines.append(lim)

    # Pretty printing the output
    print('{} {} Vs {}'.format(first.protocol_name, str(first.test_type).title(),
                                            str(second.test_type).title()))
    for l in lines:
        print(l)


if __name__ == '__main__':
    first = Performance('http2_minisoak.json')
    second = Performance('http2_smoke.json')
    compare(first, second)
