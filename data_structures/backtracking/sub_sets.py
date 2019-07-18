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
