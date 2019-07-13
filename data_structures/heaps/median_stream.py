import heapq
import math


class MedianFinder(object):
    def __init__(self):
        """
        Leet code. Solution -> Accepted

        Calculate running median

        """
        self.min_heap, self.max_heap = [], []

    def add_num(self, val):
        heapq.heappush(self.min_heap, val)
        heapq.heappush(self.max_heap, -heapq.heappop(self.min_heap))

        if len(self.max_heap) > len(self.min_heap):
            heapq.heappush(self.min_heap, -heapq.heappop(self.max_heap))

    def find_median(self):
        if len(self.min_heap) == len(self.max_heap):
            res = (self.min_heap[0] - self.max_heap[0]) / 2
        else:
            res = self.min_heap[0]
        return float(res)


def median_sorted_arrays(nums1, nums2):
    """
    Leet code. Solution -> Accepted

    Find median between 2 sorted array

    :param nums1: array 1
    :param nums2: array 2
    :return: median of the sorted array
    """
    heap, res, median = [], [], 0

    if len(nums1) == 0:
        res = nums2
    elif len(nums2) == 0:
        res = nums1
    else:
        heapq.heappush(heap, (nums1[0], 0, 0))
        heapq.heappush(heap, (nums2[0], 1, 0))

    while heap:
        current = heapq.heappop(heap)
        res.append(current[0])

        if current[1] == 0 and current[2] + 1 < len(nums1):
            heapq.heappush(heap, (nums1[current[2] + 1], 0, current[2] + 1))
        elif current[1] == 1 and current[2] + 1 < len(nums2):
            heapq.heappush(heap, (nums2[current[2] + 1], 1, current[2] + 1))

    mid = len(res) // 2

    if len(res) % 2 == 0:
        return (res[mid] + res[mid - 1]) / 2
    else:
        return float(res[mid])


def median_sorted_arrays_optimized(nums1, nums2):
    """
    Leet code. Solution -> accepted

    Optimized version of finding median of 2 sorted arrays

    :param nums1: array 1
    :param nums2: array 2
    :return: median of a sorted array
    """
    heap, res, mid = [], [], 0

    if len(nums1) == 0:
        res = nums2
    elif len(nums2) == 0:
        res = nums1

    if len(nums1) == 0 or len(nums2) == 0:
        mid = len(res) // 2
        if len(res) % 2 == 0:
            return (res[mid] + res[mid - 1]) / 2
        else:
            return float(res[mid])
    else:
        if (len(nums1) + len(nums2)) % 2 == 0:
            mid = (len(nums1) + len(nums2)) // 2
        else:
            mid = math.ceil((len(nums1) + len(nums2)) / 2)

        heapq.heappush(heap, (nums1[0], 0, 0))
        heapq.heappush(heap, (nums2[0], 1, 0))

    while heap:
        current = heapq.heappop(heap)
        res.append(current[0])

        if len(res) == mid:
            break

        if current[1] == 0 and current[2] + 1 < len(nums1):
            heapq.heappush(heap, (nums1[current[2] + 1], 0, current[2] + 1))
        elif current[1] == 1 and current[2] + 1 < len(nums2):
            heapq.heappush(heap, (nums2[current[2] + 1], 1, current[2] + 1))

    if (len(nums1) + len(nums2)) % 2 == 0:
        return (res[-1] + res[-2]) / 2
    else:
        return float(res[-1])


a = MedianFinder()
a.add_num(1)
a.add_num(2)
print(a.find_median())
a.add_num(3)
c = a.find_median()
print(c, type(c))
