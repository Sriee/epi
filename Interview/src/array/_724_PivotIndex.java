package array;

class _724_PivotIndex {
    public int pivotIndex(int[] nums) {
        int left = 0, right = 0, n = nums.length, i;

        for (i = n - 1; i >= 0; i--)
            right += nums[i];

        for (i = 0; i < n - 1; i++) {
            right -= nums[i];
            if (left == right)
                return i;
            left += nums[i];
        }

        return left == 0 ? i : -1;
    }

    public static void main(String[] args) {
        _724_PivotIndex pi = new _724_PivotIndex();
        int[][] nums = {
                {1, 7, 3, 6, 5, 6},
                {1, 2, 3},
                {2, 1, -1}
        };

        for (int[] num : nums)
            System.out.println(pi.pivotIndex(num));
    }
}