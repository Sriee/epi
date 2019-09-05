from collections import defaultdict


if __name__ == '__main__':
    bucket, words = defaultdict(list), ["POPE", "PIPE", "PAPE", "POLE", "ROPE", "NOPE",
                                        "HOPE", "PORE", "POSE", "LOPE", "MOPE", "COPE",
                                        "POKE", "POPS"]

    for word in words:
        for i in range(4):
            b = word[: i] + '_' + word[i + 1:]
            bucket[b].append(word)

    for b in bucket:
        print(b, bucket[b])
