def bsearch(nums, target):
    """
    Binary Search with duplicates.

    :param nums: Given array
    :param target: The element to find in the array
    :return: first index of the element found
    """
    idx, l, r = -1, 0, len(nums) - 1

    while l <= r:
        mid = l + (r - l) // 2

        if nums[mid] < target:
            l = mid + 1
        elif nums[mid] == target:
            idx = mid
            r = mid - 1
        else:
            r = mid - 1

    return idx
