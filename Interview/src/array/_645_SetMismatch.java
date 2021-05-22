package array;

import java.util.Arrays;

public class _645_SetMismatch {

    public int[] findErrorNums(int[] nums) {
        int i = 0, n = nums.length;

        while (i < n) {
            int correct = nums[i] - 1;

            if (nums[i] != nums[correct]) {
                swap(nums, i, correct);
            } else {
                i++;
            }
        }

        for (int k = 0; k < n; k++) {
            if (nums[k] != k + 1)
                return new int[]{nums[k], k + 1};
        }

        return null;
    }

    private void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    public static void main(String[] args) {
        _645_SetMismatch sm = new _645_SetMismatch();

        int[][] inputs = {
                {4, 3, 2, 5, 5, 1},
                {1, 1}
        };

        for (int[] inp : inputs)
            System.out.println(Arrays.toString(sm.findErrorNums(inp)));
    }
}
