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

