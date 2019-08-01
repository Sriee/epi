import heapq
import math

from collections import Counter


def top_k_frequent(words: list, k: int):
    """
    Leet code problem. Solution -> accepted

    Given a list of words, find the most frequent words. Words with similar frequency
    should be ordered alphabetically

    :param words: list of words
    :param k: frequency
    :return: list of k frequent words
    """
    c = Counter(words)
    heap = [(-freq, char) for char, freq in c.items()]
    heapq.heapify(heap)
    result = []
    while k != 0:
        k -= 1
        result.append(heapq.heappop(heap)[1])

    print(result)


def frequency_sort(inp: str):
    """
    Leet code problem. Solution -> accepted

    Given a word, return a string in the order of most repeated characters

    :param inp: Word with repeated characters
    :return: string with repeated characters in ascending order
    """
    c = Counter(inp).most_common()
    result = ''

    for i in c:
        result += i[0] * i[1]

    return result


def k_closest(points, k):
    """
    Leet code problem. Solution -> accepted

    Given a list of co-ordinates. Find k closest points to the origin

    :param points: list of co-ordinates
    :param k: number of points closest to the origin
    :return: k closest points to the origin
    """
    heap = []
    for point in points:
        heapq.heappush(heap, (-math.sqrt(point[0] ** 2 + point[1] ** 2), point))

        if len(heap) > k:
            heapq.heappop(heap)

    return [heapq.heappop(heap)[1] for _ in range(k)]


def third_max(nums):
    """
    Leet code. Solution -> Accepted

    Run time: 40 ms

    Given an array find the third largest number in the array. If there are no three numbers, return the maximum number

    Optimized version - Run time : 20ms
        nums = sorted(list(set(nums)))  # Apparently
        return nums[-1] if len(nums) == 1 else nums[-3]

    :param nums: Number Array
    :return: 3rd largest number in the array
    """
    heap, seen, max_3 = [], set(), set()

    for i in nums:
        if i in seen:
            continue

        """
        Adding an element one by one seems to take long time compared to initialization
            
            lst = [random.randint() for i in range(5000000)]
            set1 = set(lst)  # takes 2.4 seconds

            set2 = set()  # takes 3.37 seconds
            for item in lst:
                set2.add(item)
        """
        seen.add(i)
        heapq.heappush(heap, i)
        if len(heap) > 3:
            heapq.heappop(heap)

    while heap:
        max_3.add(heapq.heappop(heap))

    return min(max_3) if len(max_3) == 3 else max(max_3)


third_max([2, 1])
