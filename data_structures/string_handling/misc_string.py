def length_of_last(s):
    """
    Leet code. Solution -> Accepted

    Given a sentence, find the length of the last word. If there is a space at the end,
    ignore it

    Example:
        'This is a sentence' -> 8
        'a b' -> 1
        'a b  ' -> 1
        'a' -> 1

    :param s: sentence
    :return: length of the last word
    """
    s = s.strip()

    i = s.rfind(" ")

    if i != -1:
        res = len(s) - i - 1
        print(res, len(s))
        return res

    return len(s)


def plus_one(digits):
    """
    Leet code. Solution -> Accepted

    Given a digit as list, add one to the digit list.

    Example:
              [1] -> [2]
           [1, 9] -> [2, 0]
        [1, 2, 3] -> [1, 2, 4]
        [9, 9, 9] -> [1, 0, 0, 0]

    :param digits: digits given as list
    :return: add one to digit list
    """
    done, i = False, -1

    while not done:
        if -i > len(digits):
            digits.insert(0, 1)
            done = True
            continue

        s = digits[i] + 1
        if s > 9:
            digits[i] = 0
            i -= 1
        else:
            digits[i] = s
            done = True

    return digits
