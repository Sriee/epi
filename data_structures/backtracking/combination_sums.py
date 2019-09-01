def combination_sum(candidates, target):
    """
    Leet code. Solution -> Accepted

    Run Time: 100 ms. Not optimal but this gives a template for writing backtracking
    problems

    Given an array without duplicates. Find the list of candidates which are equal to
    the target sum. Each element can be repeated n times

    Examples:
        nums: [2, 3, 5] target = 8

        Output should be
        [
            [2, 2, 2, 2],
            [2, 3, 3]
            [3, 5]
        ]
    :param candidates: Given array
    :param target: target sum
    :return: list of candidates who sum is equal to candidates sum
    """
    res = []

    def dfs(candidates, target, index, path):
        if target == 0:
            res.append(path.copy())
            return

        for i in range(index, len(candidates)):
            if target - candidates[i] < 0:
                continue

            path.append(candidates[i])
            dfs(candidates, target - candidates[i], i, path)
            path.pop()

    dfs(candidates, target, 0, [])
    return res


def combination_sum2(candidates, target):
    """
    Leet code. Solution -> Accepted

    Run Time: 52 ms. Optimal solution

    Given an array with duplicates. Find the list of candidates which are equal to
    the target sum. Each element in the output array should appear once.

    Examples:
        nums: [10, 1, 2, 7, 6, 1, 5] target = 8

        Output should be
        [
            [1, 7],
            [1, 2, 5],
            [2, 6],
            [1, 1, 6]
        ]

    :param candidates: Given array
    :param target: target sum
    :return: list of candidates who sum is equal to candidates sum
    """
    res = []

    def dfs(target, idx, path):
        if target == 0:
            res.append(path)
            return

        for i in range(idx, len(candidates)):
            if i > idx and candidates[i] == candidates[i - 1]:
                continue

            if target - candidates[i] < 0 or candidates[i] > target:
                break

            dfs(target - candidates[i], i + 1, path + [candidates[i]])

    dfs(target, 0, [])
    return res


def combination_sum3(k, n):
    """
    Leet code. Solution -> Accepted

    Run time: 36 ms Optimized. Optimal solution

    Find all possible combinations of k numbers that add up to a number n, given that
    only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

    Examples:
        k: 3 n: 7

        Output should be
        [
            [1, 2, 4],
        ]

    :param k: Length of combination
    :param n: target sum
    :return: list of candidates who sum is equal to candidates sum
    """
    res, candidates = [], [i for i in range(1, 10)]

    def dfs(candidates, target, path):
        if target == 0 and len(path) == k:
            res.append(path)
            return

        for i in range(len(candidates)):
            if target - candidates[i] >= 0 and len(path) + 1 <= k:
                dfs(candidates[i + 1:], target - candidates[i], path + [candidates[i]])
            else:
                break
    dfs(candidates, n, [])
    return res


def combination_sum_4(nums, target):
    """
    Leet Code. Time Limit Exceeded

    Given an integer array with all positive numbers and no duplicates, find the number
    of possible combinations that add up to a positive integer target.

    Example:
        nums: [1, 2, 3], target: 4

        Output should be 7 and the combinations are
        [1, 1, 1, 1]
        [1, 1, 2]
        [1, 2, 1]
        [1, 3]
        [2, 1, 1]
        [2, 2]
        [3, 1]

    :param nums: Given array
    :param target: target sum
    :return: total number of combinations that can formed equal to sum
    """
    output = 0

    def combination_helper(nums, target):
        nonlocal output
        if target == 0:
            output += 1
            return

        for i in range(len(nums)):
            if target - nums[i] < 0:
                continue

            combination_helper(nums, target - nums[i])

    combination_helper(nums, target)

    return output


def combination_sum_4_optimized(nums, target):
    """
    Leet Code. Solution -> Accepted

    Given an integer array with all positive numbers and no duplicates, find the number
    of possible combinations that add up to a positive integer target.

    Example:
        nums: [1, 2, 3], target: 4

        Output should be 7 and the combinations are
        [1, 1, 1, 1]
        [1, 1, 2]
        [1, 2, 1]
        [1, 3]
        [2, 1, 1]
        [2, 2]
        [3, 1]

    :param nums: Given array
    :param target: target sum
    :return: total number of combinations that can formed equal to sum
    """
    nums.sort()
    mem = {}

    def combination_helper(target):
        if target in mem:
            return mem[target]

        count = 0
        for i in nums:
            if i > target:
                break
            elif i == target:
                count += 1
                break
            else:
                count += combination_helper(target - i)

        mem[target] = count
        return count

    return combination_helper(target)


print(combination_sum_4_optimized([1, 2, 3], 4))
