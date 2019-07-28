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


def search_range(nums, target):
    """
    Leet code. Solution -> Accepted

    Run Time: 136 ms. It's a very slow solution. Need to improve

    Given a sorted array find the range of the element.
        If the element is not present return [-1, -1]
        Else If only one element is present return [idx, idx]
        Else return [start, end] position of the element

    :param nums: Sorted array
    :param target: element to find
    :return: range of the element
    """
    def binary_search(nums, target, l, r, dir=0):
        fidx = -1
        while l <= r:
            m = l + (r - l) // 2

            if nums[m] < target:
                l = m + 1
            elif nums[m] == target:
                fidx = m
                if dir == 0:
                    return l, r, fidx
                elif dir == 1:
                    r = m - 1
                else:
                    l = m + 1
            else:
                r = m - 1

        return None, None, fidx

    idx = binary_search(nums, target, 0, len(nums) - 1)

    if idx[2] == -1:
        return [-1, -1]

    first = binary_search(nums, target, idx[0], idx[2], 1)
    last = binary_search(nums, target, idx[2], idx[1], 2)

    return [first if first != -1 else idx, last if last != -1 else idx]
