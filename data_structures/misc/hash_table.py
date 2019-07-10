from collections import defaultdict, Counter


def is_happy(num):
    """
    Leet code problem. Solution -> Accepted

    A number is a happy number if repeated sum of it's squares sum to one. 7 is a happy
    number.

    Ex:   7 = 7^2 = 49
         49 = 4^2 + 9 ^ 2 = 16 + 81 = 97
         97 = 9^2 + 7^2 = 81 + 49 = 130
        130 = 1^2 + 3^2 + 0 = 1 + 9 = 10
         10 = 1^2 + 0 = 1

    :param num: number to check if it's a happy number
    :return: True if its a happy number, False otherwise
    """
    squares, visited, done = {}, set(), False

    for i in range(10):
        squares[i] = i * i

    while not done:
        res = 0
        while num != 0:
            digit = num % 10
            num = num // 10
            res += squares[digit]

        if res == 1:
            return True

        if res in visited:
            done = True
        else:
            visited.add(res)
            num = res

    return False


def count_primes1(num):
    """
    Leet code problem. Solution -> Time Limit exceeded

    To see the time limit use cProfile to run the function. The stack trace will tell
    us the bottleneck. For large numbers nested loop & set addition was taking too much
    time

    >> import cProfile
    >> cProfile.run("count_primes1(999978)")

    Given a number, calculate the number of primes from 2 to the give number

    :param num: Given Number
    :return: number of primes present < given number
    """
    result = set()
    for i in range(2, num + 1):
        is_prime = True
        for j in range(2, int(i ** 0.5) + 1):
            if i % j == 0:
                is_prime = False
                break

        if is_prime:
            result.add(i)

    return result


def count_primes2(num):
    """
    Leet code problem. Solution -> accepted

    Given a number, calculate the number of primes from 2 to the give number

    :param num: Given Number
    :return: number of primes present < given number
    """
    if num < 3:
        return 0

    primes = [1] * num
    primes[0], primes[1] = 0, 0

    for i in range(2, int(num ** 0.5) + 1):
        if primes[i]:
            for j in range(i * i, num, i):
                print(j)
                primes[j] = 0
        print('-' * 10)
    return sum(primes)


def find_restaurant(list_1, list_2):
    """
    Leet code problem. Solution -> Accepted

    Find the common restaurants in both the list.
        Choose the least index sum if there are more than one common restaurant.
        If there is tie then return all the restaurants with least index sum

    :param list_1: Restaurant list 1
    :param list_2: Restaurant list 2
    :return: common restaurant between two list with least index sum
    """
    mem = {k: v for v, k in enumerate(list_1)}
    least, res = -1, []

    for v, k in enumerate(list_2):
        if k in mem:
            if least == -1 or mem[k] + v == least:
                res.append(k)
                least = mem[k] + v
            elif mem[k] + v < least:
                least = mem[k] + v
                del res
                res = [k]

    return res


def is_alien_sorted(words, order):
    """
    Leet code problem. Solution -> accepted

    :param words: List of words
    :param order: lexical order
    :return: True if they are lexically sorted False otherwise
    """
    mem = {k: v for v, k in enumerate(order)}
    i, j = 0, 1

    while j < len(words):
        ml = min(len(words[i]), len(words[i]))

        for k in range(ml):
            if words[i][k] != words[j][k]:
                if mem[words[i][k]] > mem[words[j][k]]:
                    return False
                break
        else:
            if len(words[i]) > len(words[j]):
                return False

        i = j
        j += 1

    return True


def uncommon_sentence(A, B):
    """
    Leet code problem. Solution -> accepted

    A word is said to uncommon if it appears once in a sentence and does not appear in
    the other sentence

    :param A: Space separated sentence 1
    :param B: Space separated sentence 2
    :return: list of uncommon words
    """
    mem, res = {}, []

    for a in A.split(' '):
        if a in mem:
            mem[a] += 1
        else:
            mem[a] = 1

    for b in B.split(' '):
        if b in mem:
            if mem[b] > 1:
                mem[b] += 1
            else:
                mem[b] -= 1
        else:
            mem[b] = 1

    print(mem)
    for k in mem:
        if mem[k] == 1:
            res.append(k)

    print(res)


def uncommon(A, B):
    """
    Leet code problem. Solution -> accepted

    A word is said to uncommon if it appears once in a sentence and does not appear in
    the other sentence

    Alternate solution to the above problem. Did not improve the timing of the
    computation

    :param A: Space separated sentence 1
    :param B: Space separated sentence 2
    :return: list of uncommon words
    """

    A += ' '
    B += ' '
    mem, res, start, end = {}, [], 0, 0

    while end < len(A):

        if A[end] != ' ':
            end += 1
            continue

        word = A[start : end]
        if word in mem:
            mem[word] += 1
        else:
            mem[word] = 1

        start = end + 1
        end += 1

    start, end = 0, 0

    while end < len(B):

        if B[end] != ' ':
            end += 1
            continue

        word = B[start : end]
        if word in mem:
            if mem[word] > 1:
                mem[word] += 1
            else:
                mem[word] -= 1
        else:
            mem[word] = 1

        start = end + 1
        end += 1

    for i in mem:
        if mem[i] == 1:
            res.append(i)

    print(res)


def intersection(num1, num2):
    """
    Leet code problem. Solution -> accepted

    Find the intersection of both arrays

    :param num1: Array 1
    :param num2: Array 2
    :return: common values in both arrays
    """
    return list(set(num1) & set(num2))


def intersection2(nums1, nums2):
    """
    Leet code problem. Solution -> accepted

    Find the intersection of both arrays. Slight difference from the above one is that
    the result should have as many numbers they appear in both the arrays

    :param num1: Array 1
    :param num2: Array 2
    :return: common values in both arrays
    """
    mem, res = defaultdict(int), []

    for i in nums1:
        mem[i] += 1

    for j in nums2:
        if j in mem and mem[j] != 0:
            mem[j] -= 1
            res.append(j)

    return res


def find_occurance(text, first, second):
    """
    Leet code problem. Solution -> accepted
    """
    i, words, res = 2, text.split(' '), []

    while i < len(text):
        if words[i - 2] == first and words[i - 1] == second:
            res.append(words[i])
        i += 1

    print(res)


def common_chars(A):
    t, res = Counter(A[0]), []

    for i in range(1, len(A)):
        t = t & Counter(A[i])

    for j in t:
        res += [j] * t[j]

    print(res)
    print(list(t.elements()))


def three_sum(nums):
    """
    Leet code problem. Solution -> Accepted

    Given an array, find three indices whose sum = 0. The solution will take O(n^2 log n)
    There is no optimal solution other than this. Efficient algorithm are out of scope
    for interview.

    :param nums: array
    :return: list of three indices so that their sum = 0
    """
    nums, res = sorted(nums), []

    for i in range(len(nums) - 2):
        if i == 0 or nums[i] != nums[i - 1]:
            j = i + 1
            k = len(nums) - 1

            while j < k:
                if nums[i] + nums[j] + nums[k] == 0:
                    res.append([nums[i], nums[j], nums[k]])

                if nums[i] + nums[j] + nums[k] < 0:
                    current = j

                    while nums[j] == nums[current] and j < k:
                        j += 1
                else:
                    current = k

                    while nums[k] == nums[current] and j < k:
                        k -= 1

    return res


def find_anagrams(s, p):
    res, mem, i = [], {}, 0
    for j in p:
        if j in mem:
            mem[j] += 1
        else:
            mem[j] = 1

    while i < (len(s) - len(p) + 1):
        start = i
        end = i + len(p)
        mc = mem.copy()

        while start < end:
            if s[start] not in mc:
                i = start + 1
                break

            mc[s[start]] -= 1

            if mc[s[start]] < 0:
                i += 1
                break

            start += 1
        else:
            res.append(i)
            i += 1

    print(res)


def group_anagrams(nums):
    imem = {}
    init = Counter(nums[0])
    imem[0] = init
    res = [[nums[0]]]
    j = 0

    for i in range(1, len(nums)):
        t = Counter(nums[i])

        for j in imem:
            if imem[j] == t:
                res[j].append(nums[i])
                break
        else:
            imem[j + 1] = t
            res.append([nums[i]])

    print(res)


def group_anagrams1(nums):
    res = {}
    for i in range(len(nums)):
        t = ''.join(sorted(nums[i]))

        if t in res:
            res[t].append(nums[i])
        else:
            res[t] = [nums[i]]
    res = res.values()
    print(list(res))

# print(is_happy(278))
# print(count_primes2(10))

# find_restaurant(
# ["Tapioca Express", "Shogun","Burger King","KFC"],
# ["Piatti","The Grill at Torrey Pines","Hungry Hunter ", "Tapioca Express",
#     "Steakhouse", "Burger King", "Shogun"]
# )

# print(is_alien_sorted(["kuvp", "q"], "ngxlkthsjuoqcpavbfdermiywz"))
# uncommon_sentence("apple apple", "apple this is sour")
# uncommon("apple apple", "apple this is sour")
# print(intersection([1, 2, 2, 1], [2, 2]))
# print(intersection2([4, 9, 5, 4], [9,9,5,4,2]))
# common_chars(["bella", "label", "roller"])
# print(three_sum([1, 0, -1, 0, -2, 2]))
# four_sum([1, 0, -1, 0, -2, 2], 0)
# find_anagrams('ababbcb', 'ab')

# group_anagrams(['eat', 'tea', 'tan', 'ate', 'nat', 'bat'])
group_anagrams1(['eat', 'tea', 'tan', 'ate', 'nat', 'bat'])