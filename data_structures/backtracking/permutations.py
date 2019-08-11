def permutations(nums):
    """
    Leet code. Solution -> Accepted

    Run Time: 44 ms. Average run time. The optimal one is iterative. We will keep this
    as an example of performing permutation using backtracking

    Given an array, find all the permutations of the elements in it. The given array
    doesn't contain duplicates

    Example:
        [1, 2, 3] -> [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]

    :param nums: Given array
    :return: list of subsets of elements in the array
    """
    res = []

    def backtrack(curr, available):
        if len(curr) == len(nums):
            res.append(curr)
            return

        for i in range(len(available)):
            backtrack(curr + [available[i]], available[:i] + available[i + 1:])

    backtrack([], nums)
    return res


def letter_case_permutation(letter):
    """
    Leet code. Solution -> Accepted

    Run Time: 32 ms. Optimal solution without using backtracking

    Given an alpha numeric letter. Generate permutation with lower and upper case letters
    Example:
        'a1b2' -> ['a1b2', 'A1b2', 'a1B2', 'A1B2']
        '1234' -> ['1234']

    :param letter: letter
    :return: list of letter permutations
    """
    res = ['']
    for ch in letter:
        if ch.isdigit():
            res = [i + ch for i in res]
        else:
            res = [i + j for j in [ch.lower(), ch.upper()] for i in res]

    return res


def letter_combinations(n):
    """
    Leet code. Solution -> Accepted

    Run Time: 32 ms. Optimal Soution. The solution is straight forward creating
    permutations

    Given a string containing digits from 2-9 inclusive, return all possible letter
    combinations that the number could represent.

    :param n: phone numbers in string
    :return: p
    """
    comb = {
        "2": "abc",
        "3": "def",
        "4": "ghi",
        "5": "jkl",
        "6": "mno",
        "7": "pqrs",
        "8": "tuv",
        "9": "wxyz",

    }

    res = ['']

    for ch in n:
        res = [i + j for i in res for j in comb[ch]]

    return res
