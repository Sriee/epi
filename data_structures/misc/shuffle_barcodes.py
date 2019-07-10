def distant_barcodes(barcodes):
    """
    Leet code problem. Solution -> Accepted

    Given a list of barcodes, shuffle the barcodes such that adjacent barcodes are not
    the same

    :param barcodes: list of bracodes
    :return: distant barcodes
    """
    max_n, max_count, pos, mem = 0, 0, 0, [0] * 10000

    for n in barcodes:
        mem[n] += 1
        max_count = max(max_count, mem[n])
        max_n = n if max_count == mem[n] else max_n

    for i in range(10000):
        n = max_n if i == 0 else i

        while mem[n] > 0:
            mem[n] -= 1
            barcodes[pos] = n
            pos = pos + 2 if pos + 2 < len(barcodes) else 1

    return barcodes
