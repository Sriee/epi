from collections import Counter


def majority_element(nums):
    """
    Leet code. Sotuion -> Accepted

    Run Time: 44 ms optimal solution

    Given an array find the majority element. Majority element appears more than
    floor( n/2 ) times.

    :param nums: Array
    :return: majority element
    """
    count = Counter(nums)

    # Apparently using most common method increased the run time to 212 ms
    # res = counter.mos_common(1)[0][0]

    # Using max did not increase the run time
    return max(count.keys(), key=count.get)


def majority_element_optimal(nums):
    """
    Using Boyer-Moore Algorithm

    O(n) solution with O(1) space

    :param nums: Array
    :return: majority element or -1 if no such element exists
    """
    candidate, count = nums[0], 0

    for i in range(2, nums):
        if nums[i] == candidate:
            count += 1
        elif count == 0:
            # We were able to fully pair other values with candidate. Assigning new
            # candidate
            candidate = nums[i]
        else:
            # Paired found another value to pair candidate duplicate
            count -= 1

    return candidate if nums.count(candidate) > len(nums) // 2 else -1


def majority_element_alternate(nums):
    """
    Leet code. Sotuion -> Accepted

    Run Time: 132 ms Around 90% fast solution
    Run Time: 134 ms using alternate version of Boyer-Moore Algorithm

    Given an array find the majority element. Majority element appears more than
    floor( n/3 ) times.

    :param nums: Array
    :return: majority element
    """
    return [i for i in set(nums) if nums.count(i) > len(nums) // 3]


def find_duplicates_linear(nums):
    """
    Leet code. Solution -> Accepted

    Run Time: 80 ms Not Optimal. The time delay may be caused by doing a linear scan
    after sorting

    Given an array with n + 1 integers in the range [1, n] only. There is one element
    which will appear multiple times. Find the element.

    :param nums: Given array
    :return: Duplicate element
    """
    nums.sort()
    i = 1

    while i < len(nums):
        if nums[i] == nums[i - 1]:
            return nums[i]
        i += 1


def find_duplicates_binary_search(nums):
    """
    Leet code. Solution -> Accepted

    Run Time: 80 ms Not Optimal. Binary search is not improving the run time when
    compared to linear search because sorting is the dominating factor.

    Given an array with n + 1 integers in the range [1, n] only. There is one element
    which will appear multiple times. Find the element.

    :param nums: Given array
    :return: Duplicate element
    """
    nums.sort()
    low, high = 0, len(nums) - 1

    while low < high:
        mid = (low + high) // 2

        if nums[mid] == nums[mid - 1] or nums[mid] == nums[mid + 1]:
            return nums[mid]
        elif nums[mid] >= mid + 1:
            low = mid + 1
        else:
            high = mid - 1
