def max_sum_after_partition(nums, k):
    mem = [0] * (len(nums) + 1)
    for i in range(len(nums)):
        cur = 0
        for j in range(1, min(k, i + 1) + 1):
            a = nums[i - j + 1]
            cur = max(cur, a)
            b = mem[i - j]
            c = j * cur
            mem[i] = max(mem[i], b + c)

    print(mem[len(nums) - 1])
