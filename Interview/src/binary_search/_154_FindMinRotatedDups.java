package binary_search;

public class _154_FindMinRotatedDups {

    /**
     * Follows Binary Search Template 2.
     * <p>
     * There is a little trick part that made us fail few test cases
     * <p>
     * 1. [1, 3, 3]
     * <p>
     * Pointer Update condition
     * if (nums[mid] >= nums[right])
     * left = mid + 1;
     * else
     * right = mid;
     * <p>
     * l = 0, r = 2, m = 1 - The above condition would fail. I changed >= to >, but
     * this will cause the below test case to fail.
     * <p>
     * 2. [3, 1, 3]
     * l = 0, r = 2, m = 1
     * l = 0, r = 1, m = 0
     * l = 0, r = 0
     * <p>
     * 3. [3, 3, 3, 1, 3, 3]
     * <p>
     * Second problem is that if nums[mid] == nums[right] how to update the pointer? This was the tricky part of the
     * problem.
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right])
                left = mid + 1;
            else if (nums[mid] < nums[right])
                right = mid;
            else
                right -= 1;
        }

        return nums[left];
    }

    public static void main(String[] args) {
        _154_FindMinRotatedDups rdup = new _154_FindMinRotatedDups();
        int[][] inputs = {
                {1, 2, 3, 4, 5},
                {0, 6, 7, 8, 9, 10, 11, 0},
                {2, 3, 3},
                {3, -1, 3},
                {3, 3, 3, -50, 3, 3, 3}
        };

        for (int[] inp : inputs)
            System.out.println(rdup.findMin(inp));
    }
}
