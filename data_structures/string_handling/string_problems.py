import re


def backspace_compare(s, t):
    """
    Leet code. Solution -> Accepted

    Given 2 strings compare if they are the same. # is equivalent to back space
    character.

    Example:
        ab#c, ad#c => ac, ac
         ##c, bc#c => c, bc
         
    :param s: String 1
    :param t: String 2
    :return: True if string 1 == string 2
    """
    sk, tk = [], []

    for i in s:
        if i == '#':
            if sk:
                sk.pop()
        else:
            sk.append(i)

    for j in t:
        if j == '#':
            if tk:
                tk.pop()
        else:
            tk.append(j)

    return ''.join(sk) == ''.join(tk)


def reverse_vowels(inp):
    """
    Leet code. Solution -> Accepted

    Given a string, reverse the positions of vowels alone. Input string will only have lower case letters

    Example:
        "love"     -> "levo"
        "leetcode" -> "leotcede"

    :param inp: Input string
    :return: String with vowels in the reversed order
    """
    res, i, j = '', 0, len(inp) - 1
    vowels = {'a', 'e', 'i', 'o', 'u'}

    for i in range(len(inp)):
        if inp[i] in vowels:
            while inp[j] not in vowels:
                j -= 1

            res += inp[j]
            j -= 1
            continue

        res += inp[i]

    return res


def int_to_roman(num):
    """
    Leet code. Solution -> Accepted

    Convert integer numbers to roman numerals

    Example:
          4 - IV
         20 - XX
        724 - DCCXXIV

    :param num: Input number
    :return: Roman numerals corresponding to that number
    """
    roman = {
        1: "I",
        4: "IV",
        5: "V",
        9 : "IX",
        10: "X",
        40: "XL",
        50: "L",
        90: "XC",
        100: "C",
        400: "CD",
        500: "D",
        900: "CM",
        1000: "M",
    }

    prev = {
           4: 1,
           5: 4,
           9: 5,
          10: 9,
          40: 10,
          50: 40,
          90: 50,
         100: 90,
         400: 100,
         500: 400,
         900: 500,
        1000: 900
    }
    ans = ""

    while num > 0:
        for i in prev:
            if num <= i:
                if num == i:
                    ans += roman[i]
                    num -= i
                else:
                    rem = num // prev[i]
                    ans += roman[prev[i]] * rem
                    num -= prev[i] * rem
                break
    return ans


def is_palindrome(x):
    """
    Leet code. Solution -> Accepted

    Given an input string, check whether it's a palindrome or not. Numbers could also be input strings.

    Example:
        "abb" -> False
        "abba" -> True
        "121" -> True
        "0P" -> False

    :param x: Input String
    :return: True if the string is a palindrome, False otherwise
    """
    s = str(x)
    return s == s[::-1]


def search_rotated(A, B):
    """
    Leet code. Solution -> Accepted

    Run Time: 28 ms Optimal Solution

    Given 2 strings find if they are equal. String B could be rotated (lef-shifted) i
    times.

    Examples:
        A: 'bbbacddceeb', B: 'ceebbbbacdd' -> True
        A: 'abcde', B: 'cdeab' -> True
        A: 'abcde', B: 'cdxaz'-> False

    :param A: String A
    :param B: String B (Rotated)
    :return: True if both the strings are same with rotation False otherwise
    """
    if len(A) != len(B):
        return False

    if len(A) == 0:
        return True

    if A == B:
        return True

    r, i, j = 0, 0, 0
    while i < len(A):
        if A[i] != B[j]:
            r += 1
            i += 1
        else:
            j, start = 0, i
            while i < len(A):
                if A[i] == B[j]:
                    i += 1
                    j += 1
                else:
                    r += i - start
                    j = 0
                    break
    i = 0
    return A[:r] == B[len(A) - r:]


def split_words(paragraph, banned):
    """
    Leet code. Solution -> Accepted

    Given a paragraph return the most frequent word. The most frequent word should not
    be in the list of banned words.

    Frequent Word should be case in-sensitive. Paragraph will have punctuations in it.

    :param paragraph: Paragraph
    :param banned: list of banned words
    :return: most frequent word
    """
    "Bob! is going to hit a ball!, BOB hit was powerful flew far after it was hit."

    mem, freq, words = {}, None, re.findall(r'[\w]+', paragraph)


    for b in banned:
        mem[b.lower()] = -1

    for i in words:
        i = i.lower()
        if i in mem:
            if mem[i] != -1:
                mem[i] += 1
        else:
            mem[i] = 1

        if not freq:
            freq = i
        elif mem[i] > mem[freq]:
            freq = i

    print(freq)


def strStr(haystack, needle):
    """
    Leet code. Time Limit Exceeded

    Implement str.find() function

    For some reason, using python string slicing is faster than checking individual characters in a while loop. In a
    logical stand point, the operations are same.

    :param haystack: Input String
    :param needle: String index to find
    :return: index of first found instance of needle in haystack
    """
    if needle == "":
        return 0

    for i in range(len(haystack)):
        if haystack[i] == needle[0]:
            start, j, count = i, 0, 0

            while j < len(needle) and start < len(haystack):
                if haystack[start] != needle[j]:
                    break
                else:
                    count += 1

                j += 1
                start += 1

            if count == len(needle):
                return i

    return -1


def strStr2(haystack, needle):
    """
    Leet code. Solution -> Accepted

    Implement str.find() function

    :param haystack: Input String
    :param needle: String index to find
    :return: index of first found instance of needle in haystack
    """
    if needle == '':
        return 0

    for i in range(len(haystack) - len(needle) + 1):
        if haystack[i: i + len(needle)] == needle:
            return i
    return -1


def valid_palindrome(s):
    """
    Leet code. Solution -> Accepted

    Given an alpha numeric string, find if it's a valid palindrome or not

    Example:
           0P -> False
         c00c -> True
    :param s: String
    :return: True if it's a valid palindrome, False otherwise
    """
    _s = ''

    for i in s:
        if i.isalnum():
            _s += i.lower()

    return _s == _s[::-1]


def zig_zag(s, num_rows):
    """
    Leet coe. Solution -> Accepted

    Given a string print it out in a zig zag pattern

    Example:
        PAYPALISHIRING, 4

        P     I     N
        A   L S   I G
        Y A   H R
        P     I

    :param s: Input string
    :param num_rows: Number of rows
    :return: string in zig zag pattern
    """
    if num_rows < 2:
        return s

    mem, cur, down = [''] * num_rows, 0, False

    for i in range(len(s)):
        mem[cur] += s[i]

        if cur == 0 or cur == num_rows - 1:
            down = not down

        if down:
            cur += 1
        else:
            cur -= 1

    return ''.join(mem)
