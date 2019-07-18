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
