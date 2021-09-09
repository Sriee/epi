package array;

import java.util.Arrays;

class _189_RotateArray {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        if (k == 0) return;

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int begin, int end) {
        while (begin < end) {
            int temp = nums[begin];
            nums[begin++] = nums[end];
            nums[end--] = temp;
        }
    }

    public static void main(String[] args) {
        _189_RotateArray ra = new _189_RotateArray();
        int[][] input = {
                {1, 2, 3, 4, 5, 6, 7},
                {-1, -100, 3, 99}
        };
        int[] ks = {3, 2};
        for (int i = 0; i < ks.length; i++) {
            ra.rotate(input[i], ks[i]);
            System.out.println(Arrays.toString(input[i]));
        }
    }
}