package array;

public class _268_MissingNumber {

    /**
     * Uses negative marking technique
     */
    public int missingNumber(int[] nums) {
        int j = 0, n = nums.length, ia;

        for (int i = 0; i < n; i++) {
            ia = Math.abs(nums[i]);
            if (ia == n)
                continue;

            if (ia == Integer.MIN_VALUE)
                nums[0] = -nums[0];
            else if (nums[ia] == 0)
                nums[ia] = Integer.MIN_VALUE;
            else
                nums[ia] = -nums[ia];
        }

        for (; j < n; j++) {
            if (nums[j] < 0)
                continue;
            return j;
        }

        return j;
    }

    public static void main(String[] args) {
        _268_MissingNumber mn = new _268_MissingNumber();
        int[][] inputs = {
                {3, 0, 1},  // 2
                {0, 1},     // 2
                {2, 1},     // 0
                {9, 6, 4, 2, 3, 5, 7, 0, 1} // 8
        };

        for (int[] arr : inputs) {
            System.out.println(mn.missingNumber(arr));
        }
    }
}
