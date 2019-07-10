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
