package hash_table;

import java.util.*;

class _202_HappyNumber {

    /**
     * Using Fast-Slow pointer technique.
     *
     * TC: O(log n + log n) = O(log n)
     *     O(log n) One for fast pointer and another for slow pointer
     * SC: O(1)
     */
    public boolean isHappy(int n) {
        /**
         * Tried using the below 'pow' array to store the square numbers but did not yield a faster run time. Instead,
         * it increased the space complexity.
         * /
        int[] pow = new int[10];
        for (int i = 1; i < 10; i++)
            pow[i] = i * i;
        */

        int slow = n, fast = sumDigits(n);

        while (fast != 1 && slow != fast) {
            slow = sumDigits(slow);
            fast = sumDigits(sumDigits(fast));
        }

        return fast == 1;
    }

    /**
     * Brute Force Approach.
     *
     * TC: O(log n)
     * SC: O(n) -> Using hash set
     */
    public boolean isHappyBF(int n) {
        Set<Integer> seen = new HashSet<>();

        while (n > 0) {
            n = sumDigits(n);

            if (n == 1) return true;
            if (seen.contains(n)) break;

            seen.add(n);
        }

        return false;
    }

    private int sumDigits(int n) {
        int sum = 0;
        for (int i = n; i > 0; i /= 10) {
            int d = i % 10;
            sum += d * d;
        }

        return sum;
    }

    public static void main(String[] args) {
        _202_HappyNumber hn = new _202_HappyNumber();

        int[] nums = {7, 19, 2, 250};
        for (int i : nums) {
            if (i % 2 == 0)
                System.out.println(hn.isHappy(i));
            else
                System.out.println(hn.isHappyBF(i));
        }
    }
}