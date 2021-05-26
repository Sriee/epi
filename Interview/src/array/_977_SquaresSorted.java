package array;

import java.util.Arrays;

class _977_SquaresSorted {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int left = 0, right = n - 1, square;

        for (int j = n - 1; j >= 0; j--) {
            if (Math.abs(nums[left]) < Math.abs(nums[right])) {
                square = nums[right];
                right--;
            } else {
                square = nums[left];
                left++;
            }

            res[j] = square * square;
        }

        return res;
    }

    public static void main(String[] args) {
        _977_SquaresSorted ss = new _977_SquaresSorted();

        int[][] inputs = {
                {-4, -1, 0, 3, 10},
                {-7, -3, 2, 3, 11}
        };

        for (int[] input : inputs) {
            System.out.println(Arrays.toString(ss.sortedSquares(input)));
        }
    }
}