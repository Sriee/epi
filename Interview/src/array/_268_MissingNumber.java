package array;

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
     * <p>
     * This is the template code for cyclic sort pattern.
     * <p>
     * TC: O(n) -> We will be making N-1 swaps. After N-1 swaps, the elements in the array would be in their correct
     * position. After than we would make N comparisons. O(N-1) + O(n) = O(2N-1) = O(n).
     * SC: O(1)
     */
    private int missingNumber1(int[] arr) {
        int i = 0, n = arr.length;
        while (i < n) {
            int correct = arr[i] - 1;
            if (correct < n && arr[i] != arr[correct]) {
                swap(arr, i, correct);
            } else {
                i++;
            }
        }

        for (int j = 0; j < n; j++) {
            if (arr[j] != j)
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
                {9, 6, 4, 2, 3, 5, 7, 0, 1} // 8
        };

        for (int[] arr : inputs) {
            if (mn.rand.nextInt(2) % 2 == 0)
                System.out.println("Using Negative Marking Technique: " + mn.missingNumber(arr));
            else
                System.out.println("Using Cyclic Sort Technique: " + mn.missingNumber1(arr));
        }
    }
}