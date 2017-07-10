import os
import json


class Performance(object):
    def __init__(self, uuid):
        self.uuid = uuid
        self.test_case = {}
        self._names = []
        self._test_type = ''
        self.load_object()

    def load_object(self):
        with open(os.path.join('Performance', self.uuid), 'r') as rp:
            inp = json.load(rp)

        self.test_type = inp['test_type']
        for item in inp['testcases']:
            name = item['name']
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

    def __str__(self):
        return 'Performance(uuid: {}, Test_cases: {}'.format(self.uuid,
                                                             self.test_case.keys() if
                                                             self.test_case else 'Empty')

    def __repr__(self):
        return str(self.test_case)


def compare(first, second):
    first_set, second_set, lim = set(first.get_keys()), set(second.get_keys()), '-' * 85
    left, right, lines = None, None, []
    lines.append(lim)
    lines.append('| {:^9} | {:^40} | {:^11} | {:^11} {:>2} '
                 .format('Item Id', 'Test Case Name', str(first.test_type).title(),
                         str(second.test_type).title(), '|'))
    lines.append(lim)
    # Finding the intersection
    common = first_set.intersection(second_set)

    for element in common:

        if first[element]['status'] == 'FAILED':
            left = 'None'
        else:
            left = first[element]['total_time'] // first[element]['iterations']

        if second[element]['status'] == 'FAILED':
            right = 'None'
        else:
            right = second[element]['total_time'] // second[element]['iterations']

        each_line = '| {}-{} | {:40} | {:<11} | {:<11} {:>2}' \
            .format(first[element]['item_id'], second[element]['item_id'], element,
                    left, right, '|')
        lines.append(each_line)

    # Finding the difference
    diff = list(first_set ^ second_set)

    for element in diff:
        if element in first:
            if first[element]['status'] == 'FAILED':
                left = 'None'
            else:
                left = first[element]['total_time'] // first[element]['iterations']

            each_line = '| {}-{} | {:40} | {:<11} | {:<11} {:>2}' \
                .format(first[element]['item_id'], 'None', element, left, 'None', '|')
            lines.append(each_line)
        else:
            if second[element]['status'] == 'FAILED':
                right = 'None'
            else:
                right = first[element]['total_time'] // first[element]['iterations']

            each_line = '| {}-{} | {:40} | {:<11} | {:<11} {:>2}' \
                .format('None', second[element]['item_id'], element, 'None', right, '|')
            lines.append(each_line)

    lines.append(lim)

    for l in lines:
        print(l)


if __name__ == '__main__':
    first = Performance('http2_smoke.json')
    second = Performance('http2_minisoak.json')
    compare(first, second)
