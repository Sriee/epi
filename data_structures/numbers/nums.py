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
