from collections import Counter


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


def search_range2(nums, target):
    """
    Leet code. Solution -> Accepted

    Run Time: 136 ms. Leet code error with run time. Fastest solution follows the similar
    route

    Keep search range
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
                    r = m - 1
                else:
                    l = m + 1
            else:
                r = m - 1

        return fidx

    first = binary_search(nums, target, 0, len(nums) - 1)

    if first == -1:
        return [-1, -1]

    last = binary_search(nums, target, first, len(nums) - 1, 1)

    return [first, last if last != -1 else first]


def shortest_completing_words(license_plate, words):
    """
    Leet code. Solution -> Accepted

    Run Time: 180 ms Not optimal. Intersection of Counter is taking too much time.
    However not sure why this is happening. Because worst case we will doing an
    intersection of 26 letters

    Given a License Plate will be alpha numeric character. Extract strings from the
    license plate and return the shortest word with all the characters in the license
    plate

    Example:
        license_plate: "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
        Output: "steps"

    :param license_plate: Alphanumeric license plate string
    :param words: list of words
    :return: return smallest word will all characters in the license plate
    """
    res, lp = None, Counter(''.join(i for i in license_plate if i.isalpha()).lower())

    for w in words:
        if (Counter(w) & lp) != lp:
            continue

        if res is None:
            res = w
            continue

        if len(w) < len(res):
            res = w

    return res


def shortest_completing_words_optimized(license_plate, words):
    """
    Leet code. Solution -> Accepted

    Run Time: 74 ms Optimal Solution.

    Given a License Plate will be alpha numeric character. Extract strings from the
    license plate and return the shortest word with all the characters in the license
    plate

    Example:
        license_plate: "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
        Output: "steps"

    :param license_plate: Alphanumeric license plate string
    :param words: list of words
    :return: return smallest word will all characters in the license plate
    """
    res, lp = None, Counter(''.join(i for i in license_plate if i.isalpha()).lower())

    for word in words:
        flag = True

        for k, v in lp.items():
            if k not in word and v > word.count(k):
                flag = False
                break

        if flag:
            if not res:
                res = word
            elif len(word) < len(res):
                res = word
    return res


def find_min_rotated_sorted_array(nums):
    """
    Leet code. Solution -> Accepted

    Run Time:

    Find minimum element in a rotated sorted array. The array does not contains
    duplicates. If the array has duplicates, optical time

    :param nums: Given array
    :return: minimum element in an array
    """
    low, high = 0, len(nums) - 1

    while low <= high:
        mid = (low + high) // 2

        if nums[mid] > nums[len(nums) - 1]:
            low = mid + 1
        else:
            high = mid - 1

    return nums[low]


def search_matrix(matrix, target):
    """
    Leet code. Solution -> Accepted

    Run Time: 72 ms. Optimal solution exists but the code is not simple

    Search an element in an m x n Matrix. Each row in the matrix is sorted in ascending
    order and matrix[i][0] > matrix[i - 1][len(matrix[i - 1]) - 1]

    :param matrix: Given matrix as list of lists
    :param target: element to find
    :return: True if the element is found, False otherwise
    """
    if not matrix:
        return False

    low, high = 0, len(matrix) * len(matrix[0]) - 1

    while low <= high:
        mid = (low + high) // 2
        x, y = mid // len(matrix[0]), mid % len(matrix[0])

        if matrix[x][y] == target:
            return True
        elif matrix[x][y] < target:
            low = mid + 1
        else:
            high = mid - 1

    return False
