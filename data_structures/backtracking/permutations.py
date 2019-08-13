def get_permutation(n, k):
    """
    Leet code. Solution -> Accepted

    Run Time: 1528 ms. Worst Run Time.

    Get the kth permutation for a given number
    Example:
        n: 2 & k: 1 -> "12"
        Permutations for n = 2 -> "12", "21"

    :param n: n
    :param k: k
    :return: kth iteration value
    """
    if n == 1:
        return "1"

    res, done = [], False

    def backtrack(curr, available, j):
        nonlocal done
        if len(curr) == n:
            res.append(curr)

            if len(res) == j:
                done = True
            return

        for i in range(len(available)):
            backtrack(curr + available[i], available[:i] + available[i + 1:], j)

            if done:
                break

    # Using math.factorial is increasing the run time
    fact = 1
    for m in range(2, n + 1):
        fact *= m

    # Using integers instead of strings is increasing the run time because in backtrack
    # call we have to do curr + [available[i]] which is taking up run time
    nums = [str(i) for i in range(1, n + 1)]
    k -= 1
    partition_size = fact // n
    start_from = k // partition_size

    backtrack(nums[start_from], nums[:start_from] + nums[start_from + 1:],
              (k % partition_size) + 1)

    return res[-1]


def generate_permutation_optimal(n, k):
    """
    Leet code. Solution -> Accepted

    Run Time: 28 ms. Optimal Run Time.

    Get the kth permutation for a given number
    Example:
        n: 2 & k: 1 -> "12"
        Permutations for n = 2 -> "12", "21"

    :param n: n
    :param k: k
    :return: kth iteration value
    """
    # Storing factorial for easy look up
    fact = [1]
    for i in range(1, n + 1):
        fact.append(fact[-1] * i)

    nums, res = list(range(1, n + 1)), ""
    k -= 1
    while n > 0:
        n -= 1
        # Using div mod is increasing run time
        # idx, k = divmod(k, fact[n])
        idx, k = k // fact[n], k % fact[n]
        res += str(nums[idx])
        nums.remove(nums[idx])

    return res


def generate_brackets(n):
    """
    Leet code. Solution -> Accepted

    Run Time: 44 ms. Average Run Time. Optimal Solution is iterative

    Given a number, generate combination of valid braces.

    Example:
        n : 2 -> "(())", "()()"

    :param n: number of braces
    :return: list of braces combination
    """
    res = []

    def dfs(curr, op, close):
        if op == 0 and close == 0:
            res.append(curr)
            return

        if op > 0:
            dfs(curr + "(", op - 1, close + 1)

        if close > 0:
            dfs(curr + ")", op, close - 1)

    dfs("", n, 0)
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


def permutation_with_duplicates(nums):
    """
    Leet code. Solution -> Accepted

    Run Time: 64 ms. Average run time. Optimal solution is iterative. Previous version
    of using an available element for the next permutation is how to think about the
    solution.

    Given an array, find all the permutations of the elements in it. The given array
    contain duplicates

    Example:
        [1, 1, 2] -> [1, 1, 2], [1, 2, 1], [2, 1, 1]

    :param nums: Given array
    :return: list of subsets of elements in the array
    """
    # Handle edge cases
    if len(nums) == 0 or len(nums) == 1:
        return [nums]

    nums.sort()
    res = []

    def backtrack(curr, available):
        if len(curr) == len(nums):
            res.append(curr)
            return

        for i in range(len(available)):
            if i > 0 and available[i] == available[i - 1]:
                continue
            else:
                backtrack(curr + [available[i]], available[:i] + available[i + 1:])

    backtrack([], nums)
    return res
