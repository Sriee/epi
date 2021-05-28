package array;

import java.util.Arrays;

class _905_SortArrayByParity {

    /*
     * Approach 1: Two Pointer
     *
     * TC: O(n) + O(n) = O(n) // 2 passes
     * SC: O(1)
     */
    public int[] sortArrayByParity1(int[] nums) {
        int n = nums.length, nE = 0;

        for (int i : nums) {
            if (i % 2 == 0) nE++;
        }

        int ep = 0, op = nE;

        while (ep < nE && op < n) {
            while (ep < nE && nums[ep] % 2 == 0)
                ep++;

            while (op < n && nums[op] % 2 != 0)
                op++;

            if (ep < nE && op < n)
                swap(nums, ep, op);
        }

        return nums;
    }

    /*
     * Approach 2: In Place swap
     *
     * TC: O(n) // single pass
     * SC: O(1)
     */
    public int[] sortArrayByParity2(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                swap(nums, i, j);
                j++;
            }
        }

        return nums;
    }

    private void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    public static void main(String[] args) {
        _905_SortArrayByParity sap = new _905_SortArrayByParity();
        int[][] inputs = {
                {17, 18, 5, 4, 6, 1},
                {33, 44, 11, 9, 5, 1}
        };

        System.out.println(Arrays.toString(sap.sortArrayByParity1(inputs[0])));
        System.out.println(Arrays.toString(sap.sortArrayByParity2(inputs[1])));
    }
}