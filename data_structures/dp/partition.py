nums = [7, 2, 5, 10, 8]
s = [nums[0]]

for i in range(1, len(nums)):
    s.append(s[-1] + nums[i])

idx = 1
print(s, nums[idx:], s[len(s) - 1] - s[idx - 1])
