def div_un_optimized(dividend, divisor):
    """
    Leet code. Run time error

    Perform Division operation without using inbuilt div function

    :param dividend: Dividend
    :param divisor: Divisor
    :return: Dividend/Divisor
    """
    if dividend == 0:
        return 0

    res, is_neg = 0, False

    # There is a shorthand expression for this in python.
    # Did not use abs for turning negative numbers
    if dividend < 0 and not divisor < 0:
        dividend = -dividend
        is_neg = True
    elif divisor < 0 and not dividend < 0:
        divisor = -divisor
        is_neg = True
    elif divisor < 0 and dividend < 0:
        dividend = -dividend
        divisor = -divisor

    # Fails for test cases with integer overflow numbers
    if divisor == 1:
        return -dividend if is_neg else dividend

    # Times out for really huge numbers
    while dividend > 0:
        dividend -= divisor
        res += 1

    # Fails for test cases which requires us to handle integer over flow
    return -res if is_neg else res


def div(dividend, divisor):
    """
    Leet code. Solution -> Accepted

    Perform Division operation without using inbuilt div function

    :param dividend: Dividend
    :param divisor: Divisor
    :return: Dividend/Divisor
    """
    if dividend == 0:
        return 0

    pos = (dividend < 0) is (divisor < 0)
    dividend, divisor = abs(dividend), abs(divisor)
    res = 0

    if divisor == 1:
        if not pos:
            dividend = -dividend
        return min(max(-2147483648, dividend), 2147483647)

    while divisor <= dividend:
        temp, i = dividend, 1
        while dividend >= temp:
            dividend -= temp
            res += i
            i <<= 1
            temp <<= 1

    if not pos:
        res = -res

    # Handle Integer overflow, since we are doing an integer division
    return min(max(-2147483648, res), 2147483647)


def numbers_excel_column(n):
    """
    Leet code. Solution -> Accepted

    Convert number to Excel column

    Example:
         1 - A
        52 - AZ

    :param n: number
    :return: Excel column
    """
    res = ''

    while n > 0:
        n, r = divmod(n - 1, 26)
        res = chr(r + ord('A')) + res

    return res


def exponent(base, power):
    """
    Leet code. Solution -> Accepted

    Run Time: 24 ms Optimal solution

    Find x ^ n. Divide and conquer approach is used below. Solution handles negative
    power as well.

    Ex:
        10 ^ 4  = 10 ^ 2 * 10 ^ 2
            - half_pow will compute and doubles it. Don't need to compute 10 ^ 2 2 times
        10 ^ 5 = 10 * 10 ^ 4
            - For odd case we have to multiply with base once more

    :param base: Base
    :param power: Power
    :return: base ^ power
    """
    def helper(base, power):
        if power == 0:
            return 1
        else:
            half_pow = helper(base, power // 2)
            full_pow = half_pow * half_pow

            if power % 2 == 1:
                full_pow *= base
            return full_pow

    if power < 0:
        return 1 / helper(base, abs(power))
    else:
        return helper(base, power)


def sqrt(x):
    """
    Leet code. Solution -> Accepted

    Find square root of the number without using the inbuilt.

    :param x: num
    :return: square root of a number
    """
    return int(x ** 0.5)
