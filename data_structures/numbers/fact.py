def trailing_zeroes_1(n):
    """
    Leet code. Time Limit Exceeded

    Find the number of trailing zeroes in a factorial

    We are performing O(n) operation for computing the factorial and for computing the
    trailing zeroes

    :param n: number
    :return: n!
    """
    count, f = 0, 1
    while n > 0:
        f *= n
        n -= 1

    s = str(f)
    print(s)
    for i in s[::-1]:
        if i == '0':
            count += 1
        else:
            break

    print(count)


def factorial_recursive(n):
    """
    Recursive implementation of factorial

    :param n: given number
    :return: n!
    """
    if 0 <= n < 2:
        return 1

    return n * factorial_recursive(n - 1)


def trailing_zeros_2(n):
    """
    Find trailing zeroes for n!

    Leet Code. Run Time Error

    For large numbers python throws run time error for reaching recursive depths

    :param n: num
    :return: n!
    """
    count, f = 0, factorial_recursive(n)
    s = str(f)
    for i in s[::-1]:
        if i == '0':
            count += 1
        else:
            break
    print(count)


def trailing_zeroes_3(n):
    """
    Find trailing zeroes for n!

    Leet Code. Solution -> Accepted

    Since trailing zeroes happens with factor 5 & 2, we divide n repeatedly with 5

    :param n: num
    :return: n!
    """
    if n == 0:
        return 0

    a = (n // 5) + trailing_zeroes_3(n // 5)
    print(n, a)
    return a
