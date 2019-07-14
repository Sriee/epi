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
