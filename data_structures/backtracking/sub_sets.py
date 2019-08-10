def letter_case_permutation(letter):
    """
    Leet code. Solution -> Accepted

    Run Time: 696 ms. Worst run time. Avoid dfs for numerics improved the run
    time but still requires optimization

    Given an alpha numeric letter. Generate permutation with lower and upper case letters
    Example:
        'a1b2' -> ['a1b2', 'A1b2', 'a1B2', 'A1B2']
        '1234' -> ['1234']

    :param letter: letter
    :return: list of letter permutations
    """
    res = []

    def dfs(idx, curr):
        for i in range(idx, len(letter)):
            if letter[i].isdigit():
                curr = curr + letter[i]
            else:
                dfs(i + 1, curr + letter[i].lower())
                dfs(i + 1, curr + letter[i].upper())

        if len(curr) == len(letter):
            res.append(curr)

    dfs(0, '')
    return res


def subsets(nums):
    """
    Leet code. Solution -> Accepted

    Run time: 44 ms

    Given an array find all possible subsets of an array. The given array does not
    contain a duplicate. Order of the output doesn't matter. Output should not contain
    any duplicates.

    Example:
        [1, 2, 3] -> [], [1], [2], [3], [1, 2], [1, 3], [2, 3], [1, 2, 3]

    :param nums: array
    :return: subsets of the array
    """
    res = []

    def subset_helper(nums, idx, path, res):
        res.append(path.copy())

        for i in range(idx, len(nums)):
            path.append(nums[i])
            subset_helper(nums, i + 1, path, res)
            path.pop()
    subset_helper(nums, 0, [], res)

    return res


def subsets_optimal(nums):
    """
    Leet code. Solution -> Accepted

    Run time: 36 ms. Optimal solution. We are reducing the run time by not copying the
    list and from popping elements from it.

    Given an array find all possible subsets of an array. The given array does not
    contain a duplicate. Order of the output doesn't matter. Output should not contain
    any duplicates.

    Example:
        [1, 2, 3] -> [], [1], [2], [3], [1, 2], [1, 3], [2, 3], [1, 2, 3]

    :param nums: array
    :return: subsets of the array
    """
    res = [[]]

    def dfs(idx, path):

        if idx == len(nums):
            return

        path = path +[nums[idx]]
        res.append(path)

        for i in range(idx + 1, len(nums)):
            dfs(i, path)

    for idx in range(len(nums)):
        dfs(idx, [])

    return res


def subsets_duplicates(nums):
    """
    Leet code. Solution -> Accepted

    Given an array find all possible subsets of an array. The given array contains
    duplicate entries. Order of the output doesn't matter. Output should not contain
    any duplicates.

    Run time: 44 ms. Little puzzled about the run time.
        - We are sorting the input array
        - Converting list to tuple + Hashing a tuple when adding it to result
        - Converting tuple entries back to set while returning the result yet the run
        time seems to be the same as above sub set sum problem

    Example:
        [4, 4, 4, 1, 4] -> [[], [1], [1, 4], [1, 4, 4], [1, 4, 4, 4], [1, 4, 4, 4, 4],
                            [4], [4, 4], [4, 4, 4], [4, 4, 4, 4]]

    :param nums: Arrays with duplicates
    :return: subsets of the array without duplicates
    """
    res = set()

    def subset_helper(nums, idx, path, res):
        res.add(tuple(path.copy()))

        for i in range(idx, len(nums)):
            path.append(nums[i])
            subset_helper(nums, i + 1, path, res)
            path.pop()
    subset_helper(sorted(nums), 0, [], res)

    return [list(i) for i in res]


def subsets_duplicates_without_set(nums):
    """
    Leet code. Solution -> Accepted

    Run time: 40 ms. Checking whether the path is visited seems to improve time and
    space complexity. Space is significantly improved since we won't be traversing
    redundant branches

    :param nums: Array with duplicates
    :return: subsets of the array without duplicates
    """
    res = []

    def subset_helper(nums, idx, path, res):
        res.append(path.copy())

        for i in range(idx, len(nums)):
            path.append(nums[i])
            if path not in res:
                subset_helper(nums, i + 1, path, res)
            path.pop()

    subset_helper(sorted(nums), 0, [], res)
    return res


def subsets_duplicates_optimized(nums):
    """
    Leet code. Solution -> Accepted

    Run time: 40 ms. Checking whether the path is visited seems to improve time and
    space complexity. Space is significantly improved since we won't be traversing
    redundant branches

    :param nums: Array with duplicates
    :return: subsets of the array without duplicates
    """
    res = []

    def subset_helper(nums, idx, path, res):
        res.append(path.copy())

        for i in range(idx, len(nums)):
            if i > idx and nums[i] == nums[i - 1]:
                continue

            path.append(nums[i])
            subset_helper(nums, i + 1, path, res)
            path.pop()

    subset_helper(sorted(nums), 0, [], res)

    return res


print(letter_case_permutation('a1b2'))
