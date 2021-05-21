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
     * The conditions for negative marking technique applies to this technique as well.
     * 1. 0 <= nums[i] <= n
     * 2. The array should have positive integers
     */
    public int missingNumber1(int[] nums) {
        int idx = 0, next, n = nums.length;

        while (idx < n) {
            if (nums[idx] < n && nums[idx] != idx) {
                next = nums[idx];
                nums[idx] = nums[next];
                nums[next] = next;
            } else {
                idx++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i)
                return i;
        }
        return n;
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