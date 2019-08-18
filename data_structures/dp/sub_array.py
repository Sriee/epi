def longest_increasing_sub_sequence(nums):
    """
    Run Time: 1252 ms. Worst Run Time. Time complexity is O(n^2). The below 
    solution is based on Skienna's Algorithm Design Manual. But the run time
    is not optimal. 

    Given an array. Find the longest increasing subsequence. 
    Note the difference between sub-sequence and a sub-array

    Example:
        [-2, 1, 3, 8, 2, 0, 9] -> [1, 3, 8, 9]

    :param nums: Given array
    :return: Longest increasing sub sequence
    """
    if len(nums) == 0:
        return
    mem, j, res = [1] * len(nums), 1, 0
    while j < len(nums):
        i = 0
        while i < j:
            if nums[i] < nums[j]:
                mem[j] = max(mem[j], mem[i] + 1)
            i += 1
        if mem[j] > res:
            res = mem[j]
        j += 1

    return res


if __name__ == '__main__':
    nums = [-2, 1, -3, 4]
    cur_max = nums[0]

    grid = [] * len(nums)
    for i in range(len(nums)):
        grid.append([0] * len(nums))

    for i in range(len(grid)):
        for j in range(len(grid[i])):
            if j > i:
                continue

            grid[i][j] = nums[i] if i == j else grid[i - 1][j] + nums[i]

            if grid[i][j] > cur_max:
                cur_max = grid[i][j]

    # Print the results
    for i in range(len(grid)):
        for j in range(len(grid[i])):
            print(grid[i][j], end=" ")
        print()

    # Max value of sub array
    print('Maximum value in the sub-array:', cur_max)
