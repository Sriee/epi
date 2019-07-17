class NumArray:

    def __init__(self, nums):
        """
        Leet code. Solution -> Accepted

        Given an array which is unmutable. Calculate the sum given an range
        
        Optimized Version:
            Optimized version is to store the sum from 0 to k
            s, t = [0], 0
                
            for i in nums:
                t += i
                s.append(t)

            When given the range (i, j) perform the following to get the range
            def sum_range(i, j):
                return s[0, j] - s[0, i - 1]

        :param nums: unmutable given array
        """
        self._arr = nums
        self._sum = {}

        s = 0
        for i in range(len(self._arr)):
            s += self._arr[i]
            self._sum[(0, i)] = s

    def sum_range(self, i, j):
        if (i, j) in self._sum:
            return self._sum[(i, j)]

        return self._sum[(0, j)] - self._sum[(0, i - 1)]


a = NumArray([-2, 0, 3, -5, 2, -1])
print(a.sum_range(0, 3))
print(a.sum_range(2, 5))
print(a.sum_range(1, 2))
