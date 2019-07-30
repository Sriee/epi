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
    arr1.sort(key=lambda k : (mem.get(k, len(arr2)), k))
    return arr1


def peak_index(A):
    """
    Leet code. Solution -> Accepted

    Run Time: 92 ms. Solution is not optimal.

    An array is a mountain with the following properties
         i. Min length is 3. If length is less than 3 return 0
        ii. There exists 0 < i < A.length-1 such that A[i - 1] < A[i] < A[i + 1]

    Example:
        [10, 9, 8, 7, 1, 2, 3, 4, 3] -> 7
        [0, 2, 1, 0] -> 1

    :param A: Given array
    :return: peak index of the array if found else 0
    """
    if len(A) < 3:
        return 0

    if len(A) == 3:
        return 1

    low, high = 0, len(A) - 1
    while low <= high:
        mid = (low + high) // 2

        if A[mid - 1] < A[mid] > A[mid + 1]:
            return mid
        elif A[mid] < A[mid + 1]:
            low = mid + 1
        else:
            high = mid - 1
    return 0
