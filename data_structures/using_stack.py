import string


def decode_optimized(word):
    """
    Leet Code optimized solution

    :param word: encoded string
    :return: decoded string
    :rtype str
    """
    stack, num = [['', '1']], ''

    for i in word:
        if i == '[':
            stack.append(['', num])
            num = ''
        elif i == ']':
            s, k = stack.pop()
            stack[-1][0] += s * int(k)
        elif i in string.ascii_letters:
            stack[-1][0] += i
        else:
            num += i
    return stack[-1][0] * int(stack[-1][1])


def decode(word):
    """
    Leet Code. Solution -> Accepted

    Runtime: 31 seconds

    :param word: encoded string
    :rtype str
    """
    stack, value, j = [], [], 0

    for i in word:
        if i == '[':
            stack.append('[')
        elif i == ']':
            idx = len(stack) - stack[::-1].index('[') - 1
            stack[idx] = ''.join(stack[idx + 1:]) * value.pop()
            del stack[idx + 1:]
        elif i in string.ascii_letters:
            if j == 0 or stack[-1] == '[':
                stack.append(i)
            else:
                stack[-1] = stack[-1] + i
        elif j != 0 and word[j - 1] in string.digits:
            value[-1] = value[-1] * 10 + int(i)
        else:
            value.append(int(i))
        j += 1

    return ''.join(stack)


class NestedInteger(object):
    """Minimal implementation used for the problem."""
    def __init__(self, value=None):
        """
        If value is not specified, initializes an empty list.
        Otherwise initializes a single integer equal to value.
        """
        self.value = value if value else []

    def add(self, elem):
        """
        Set this NestedInteger to hold a nested list and adds a nested integer elem to it.
        :rtype void
        """
        if type(self.value) is list:
            self.value.append(elem)


def deserialize(s):
    """
    Leet Code. Solution -> Accepted

    :type s: str to be deserialized
    :rtype: NestedInteger
    """
    token, stack = '', []
    for i in s:
        if i == '[':
            stack.append(NestedInteger())
        elif i == ']':
            if token:
                stack[-1].add(int(token))
                token = ''
            elem = stack.pop()
            stack[-1].add(elem)
        elif i == ',' and token:
            stack[-1].add(int(token))
            token = ''
        elif i in string.digits:
            token += i

    return NestedInteger(int(token)) if token else stack.pop()


if __name__ == '__main__':

    # Unit tests for Decode implementation
    assert decode('leetcode') == 'leetcode'
    assert decode('2[a]') == 'aa'
    assert decode('10[a]') == '{}'.format('a' * 10)
    assert decode('2[ac]3[b]') == '{0}{0}{1}'.format('ac', 'b' * 3)
    assert decode('2[a10[b]]3[c]') == '{0}{0}{1}'.format('a' + 'b' * 10, 'c' * 3)


    # print encode_optimized('2[ac]3[b]')
    # print decode('leetcode')
    print decode_optimized('2[ac]3[b]')
    # print encode_optimized('10[a]')
    # res = encode_optimized('2[a10[b]]3[c]')
