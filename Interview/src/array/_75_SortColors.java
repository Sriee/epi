package array;

import java.util.Arrays;

public class _75_SortColors {

    /**
     * Using Dutch national flag algorithm.
     *
     * TC: O(n)
     * SC: O(1)
     */
    public void sortColors(int[] nums) {
        int left = 0, curr = 0, right = nums.length - 1;

        while (curr <= right) {
            if (nums[curr] == 0) {
                swap(nums, left, curr);
                left++; curr++;
            } else if (nums[curr] == 2) {
                swap(nums, curr, right);
                right--;
            } else {
                curr++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void main(String[] args) {
        _75_SortColors sc = new _75_SortColors();

        int[][] inputs = {
            {2, 0, 2, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 2},
            {0, 0, 0, 0, 0, 1, 2},
            {0, 1, 1, 1, 1, 1, 2},
            {0, 1, 1, 2, 2, 2, 2, 2, 2},
        };

        for (int[] inp : inputs) {
            sc.sortColors(inp);
            System.out.println(Arrays.toString(inp));;
        }
    }
}
