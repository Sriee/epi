import collections


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


def relative_sort(arr1, arr2):
    """
    Leet code. Solution -> Accepted

    Run time: 40 ms
    
    Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements
    in arr2 are also in arr1. Sort the elements of arr1 such that the relative ordering
    of items in arr1 are the same as in arr2.

    Elements that don't appear in arr2 should be placed at the end of arr1 in
    ascending order.

    :param arr1: Array 1
    :param arr2: Distinct array 2
    :return: relatively sorted array
    """
    mem = collections.OrderedDict()
    for idx, i in enumerate(arr2):
        mem[idx] = i

    counts = collections.Counter(arr1)
    start = 0
    for i in mem:
        while counts[mem[i]] != 0:
            arr1[start] = mem[i]
            start += 1
            counts[mem[i]] -= 1
            
        del counts[mem[i]]

    return arr1[:start] + sorted(counts.elements())

    
def relative_sort_optimized(arr1, arr2):
    """
    Optimized version of relative sort array

    :param arr1: Array 1
    :param arr2: Distinct Array 2
    :return: relatively sorted array
    """
    mem = {i: idx for idx, i in enumerate(arr2)}
    arr1.sort(key=lambda k: (mem.get(k, len(arr2)), k))
    return arr1


def peak_index(arr):
    """
    Leet code. Solution -> Accepted

    Run Time: 92 ms. Solution is not optimal.

    An array is a mountain with the following properties
         i. Min length is 3. If length is less than 3 return 0
        ii. There exists 0 < i < A.length-1 such that A[i - 1] < A[i] < A[i + 1]

    Example:
        [10, 9, 8, 7, 1, 2, 3, 4, 3] -> 7
        [0, 2, 1, 0] -> 1

    :param arr: Given array
    :return: peak index of the array if found else 0
    """
    if len(arr) < 3:
        return 0

    if len(arr) == 3:
        return 1

    low, high = 0, len(arr) - 1
    while low <= high:
        mid = (low + high) // 2

        if arr[mid - 1] < arr[mid] > arr[mid + 1]:
            return mid
        elif arr[mid] < arr[mid + 1]:
            low = mid + 1
        else:
            high = mid - 1
    return 0


def search_rotate_array_non_optimal(nums, target):
    """
    Leet code. Solution -> Accepted

    Search target value in rotated sorted array

    Run Time: 68 ms Not Optimal solution

    :param nums: Rotated sorted array
    :param target: value to find
    :return: index of target value in a sorted array
    """
    low, high = 0, len(nums) - 1
    while low < high:
        mid = (low + high) // 2

        if nums[mid] > nums[len(nums) - 1]:
            low = mid + 1
        else:
            high = mid

    piv = low
    low, high = 0, len(nums) - 1

    while low <= high:
        real_mid = (low + high) // 2
        pivot_mid = (real_mid + piv) % len(nums)

        if nums[pivot_mid] == target:
            return pivot_mid
        elif nums[pivot_mid] < target:
            low = real_mid + 1
        else:
            high = real_mid - 1
    return -1


def search_rotate_arrays(nums, target):
    """
    Leet code. Solution -> Accepted

    Run Time: 44 ms Optimal Solution. The improvement happens, when we search for an
    element in an array without finding the pivot index

    Search for an element in the rotated sorted array

    :param nums: Rotated sorted array
    :param target: The element to find
    :return: index of the found element
    """
    if not nums:
        return -1

    low, high = 0, len(nums) - 1

    while low <= high:
        mid = (low + high) // 2

        if nums[mid] == target:
            return mid
        elif nums[mid] < nums[low]:
            if nums[mid] < target <= nums[high]:
                low = mid + 1
            else:
                high = mid - 1
        else:
            if nums[mid] > target >= nums[low]:
                high = mid - 1
            else:
                low = mid + 1
    return -1
