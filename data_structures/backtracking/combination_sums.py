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
