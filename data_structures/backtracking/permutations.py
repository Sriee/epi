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
