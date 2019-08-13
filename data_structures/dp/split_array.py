from collections import defaultdict


def brute_force(nums, k):
    """
    Run Time: Time Limit Exceeded

    Time Complexity: O(n^3 * m). We are looking at all possible combinations - O(n^2 * m)
    Addition O(n) is because of sum function.
    """
    def helper(num, m):
        if len(num) == 0:
            return 0
        elif m == 1:
            return sum(num)

        min_res = float('inf')
        for i in range(1, len(num) + 1):
            left = sum(num[:i])
            right = helper(num[i:], m - 1)

            min_res = min(min_res, max(left, right))

            """The following condition is applicable only when the elements of the 
            array are positive.
            
            a | bcd -> ab | bcd. If ab is the maximum value i.e max(l, r), values after 
            that would be increasing Why?
                ab | bcd -> abc | d ; since we will be adding elements to the left, 
                left will keep increasing. 
            
            What if it's increasing?
                The min_res check will always fail after the point ab. 
            """
            if left > right:
                break
        return min_res

    return helper(nums, k)


def cumulative_sum(nums, k):
    """
    Run Time: Time Limit Exceeded

    Time Complexity: O(n^2 * m). We are looking at all possible combinations - O(n^2 * m)
    We avoided O(n) because of using cumulative sum approach.
    """
    cum = [0]

    for x in nums:
        cum.append(cum[-1] + x)

    def helper(idx, num, m):
        if len(num) == 0:
            return 0
        elif m == 1:
            return cum[-1] - cum[idx]

        min_res = float('inf')
        for i in range(1, len(num) + 1):
            left = cum[idx + i] - cum[idx]
            right = helper(idx + i, num[i:], m - 1)

            print(left, right)
            min_res = min(min_res, max(left, right))
            if left > right:
                break
        return min_res

    return helper(0, nums, k)


def memoization_cumulative_sum(nums, k):
    """
    Run Time: Time Limit Exceeded

    Time Complexity: O(n^2 * m). Even though memoization reduces the run time, the time
    complexity is still in O(n^2)
    """
    mem, cum = defaultdict(dict), [0]

    for x in nums:
        cum.append(cum[-1] + x)

    def helper(idx, num, m):
        if len(num) == 0:
            return 0
        elif m == 1:
            return cum[-1] - cum[idx]

        if idx in mem and m in mem[idx]:
            return mem[idx][m]

        mem[idx][m] = float('inf')
        for i in range(1, len(num) + 1):
            left = cum[idx + i] - cum[idx]
            right = helper(idx + i, num[i:], m - 1)

            mem[idx][m] = min(mem[idx][m], max(left, right))
            if left > right:
                break

        return mem[idx][m]

    return helper(0, nums, k)


def binary_search(nums, m):
    """
    Leet code. Solution -> Accepted

    Run Time: 44 ms. Optimal solution using binary search

    Binary Search Approach:
    - In order to a binary search we need a search range

    What is the search range?
    - If m = 1, maximum sum would be sum of the whole array. Since we need to split to
    array into '1' sub array which is itself.
    - If m = n, maximum sum would be the max element in the array. Since we have n
    pieces, each element can be placed in one piece

    Now that we have the search how to solve this problem?
    - We know that our maximum sum can lie in [max(array), sum(array)], we can start
    linearly from the start. Since binary search has the ability to reduce the seach
    space to half each time we are using binary search

    - Pick mid from the search range. Let this be the largest sum
    - We are gonna see how many pieces can we make such that their sum is larger than
    the picked largest sum.
        > if we were able to create pieces > m. Which means we have to decrease the
        sum so that we are able to split the pieces evenly
        > if we were able to create pieces <= m. Which means we can increase the sum


    :param nums: Given array
    :param m: How many pieces to split
    :return: minimum of largest sum in a sub array
    """
    low, high = max(nums), sum(nums)

    def get_pieces(largest_sum):
        piece, current = 1, 0
        for x in nums:
            current += x
            if current > largest_sum:
                piece += 1
                current = x

        return piece

    while low < high:
        mid = (low + high) // 2
        pieces = get_pieces(mid)

        if pieces > m:
            low = mid + 1
        else:
            high = mid

    return low
