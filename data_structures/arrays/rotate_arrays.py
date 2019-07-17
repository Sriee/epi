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
