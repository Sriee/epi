def reverse_vowels(inp):
    res, i, j = '', 0, len(inp) - 1
    vowels = {'a', 'e', 'i', 'o', 'u'}

    for i in range(len(inp)):
        if inp[i] in vowels:
            while inp[j] not in vowels:
                j -= 1

            res += inp[j]
            j -= 1
            continue

        res += inp[i]

    print(res)


def zig_zag(s, num_rows):

    if num_rows < 2:
        return s

    mem, cur, down = [''] * num_rows, 0, False

    for i in range(len(s)):
        mem[cur] += s[i]

        if cur == 0 or cur == num_rows - 1:
            down = not down

        if down:
            cur += 1
        else:
            cur -= 1

    res = ''.join(mem)
    print(res)


def is_palindrome(x):
    s = str(x)
    return s == s[::-1]


def div(dividend, divisor):
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

    return min(max(-2147483648, res), 2147483647)


def strStr(haystack, needle):
    if needle == "":
        return 0

    for i in range(len(haystack)):
        if haystack[i] == needle[0]:
            start, j, count = i, 0, 0

            while j < len(needle) and start < len(haystack):
                if haystack[start] != needle[j]:
                    break
                else:
                    count += 1

                j += 1
                start += 1

            if count == len(needle):
                return i

    return -1


def strStr2(haystack, needle):
    if needle == '':
        return 0

    for i in range(len(haystack) - len(needle) + 1):
        print(i, haystack[i: i + len(needle)])
        if haystack[i: i + len(needle)] == needle:
            return i
    return -1


def int_to_roman(num):
    roman = {
        1: "I",
        4: "IV",
        5: "V",
        9 : "IX",
        10: "X",
        40: "XL",
        50: "L",
        90: "XC",
        100: "C",
        400: "CD",
        500: "D",
        900: "CM",
        1000: "M",
    }

    prev = {
           4: 1,
           5: 4,
           9: 5,
          10: 9,
          40: 10,
          50: 40,
          90: 50,
         100: 90,
         400: 100,
         500: 400,
         900: 500,
        1000: 900
    }
    ans = ""

    while num > 0:
        for i in prev:
            if num <= i:
                if num == i:
                    ans += roman[i]
                    num -= i
                else:
                    rem = num // prev[i]
                    ans += roman[prev[i]] * rem
                    num -= prev[i] * rem
                break
    print(ans)


# reverse_vowels('hello')
# zig_zag('PAYPALISHIRING', 4)
# print(is_palindrome(121))
# print(div(-2147483648, -1))
# print(strStr2("", "ll"))

# int_to_roman(3x)
int_to_roman(488)
