package array.cyclic_sort;

import java.util.*;

public class _268_MissingNumber {
    private final Random rand = new Random();

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

    /**
     * Cyclic Sort technique
     *
     * This is the template code for cyclic sort pattern.
     *
     * TC: O(N) -> We will be making N-1 swaps. After N-1 swaps, the elements in the array would be in their correct
     * position. After than we would make N comparisons. O(N-1) + O(N) = O(2N-1) = O(N).
     * SC: O(1)
     */
    private int missingNumber1(int[] nums) {
        int i = 0, n = nums.length;
        while (i < n) {
            if (nums[i] < n && nums[i] != i) {
                swap(nums, i, nums[i]);
            } else {
                i++;
            }
        }

        for (int j = 0; j < n; j++) {
            if (nums[j] != j)
                return j;
        }

        return n;
    }

    private void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    public static void main(String[] args) {
        _268_MissingNumber mn = new _268_MissingNumber();
        int[][] inputs = {
                {3, 0, 1},  // 2
                {0, 1},     // 2
                {2, 1},     // 0
                {9, 6, 4, 2, 3, 5, 7, 0, 1}, // 8
                {5, 3, 1, 2, 0} // 4
        };

        for (int[] arr : inputs) {
            if (mn.rand.nextInt(2) % 2 == 0)
                System.out.println("Using Negative Marking Technique: " + mn.missingNumber(arr));
            else
                System.out.println("Using Cyclic Sort Technique: " + mn.missingNumber1(arr));
        }
    }
}