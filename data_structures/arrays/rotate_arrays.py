def rotate_array(nums, k):
    """
    Leet code. Solution -> Accepted

    Given an array, rotate it k times

    :param nums: array
    :param k: rotation count
    :return: rotated array k times
    """
    k = k % len(nums)
    if k == 0 or len(nums) == k:
        return nums

    pivot = len(nums) - k
    res = nums[pivot:] + nums[:pivot]
    return res
