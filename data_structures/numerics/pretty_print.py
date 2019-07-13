def generate_pascal_triangle(num_rows):
    """
    Leet code. Solution -> Accepted

    Generate Pascal triangle for given number of rows

    :param num_rows: number of rows
    :return: Pascal triangle rows
    """
    res = [[1]]

    if num_rows == 1:
        return res

    res.append([1, 1])
    if num_rows == 2:
        return res

    for i in range(3, num_rows + 1):
        this = [1]
        for j in range(1, len(res[-1])):
            this.append(res[-1][j] + res[-1][j - 1])
        this.append(1)
        res.append(this)

    return res


def generate_pascal(num_rows):
    """
    Leet code. Solution -> Accepted

    Cleaner way of printing pascal triangle

    :param num_rows: number of rows
    :return: Pascal triangle rows
    """
    res = []

    if num_rows == 0:
        return res

    row = [1]
    res.append(row)
    for _ in range(num_rows):
        row = [x + y for x, y in zip(row + [0], [0] + row)]
        res.append(row)

    return res


pt = generate_pascal(5)
last_row = " ".join([str(i) for i in pt[-1]])

for row in pt:
    print("{:^{length}}".format(" ".join([str(i) for i in row]), length=len(last_row)))
