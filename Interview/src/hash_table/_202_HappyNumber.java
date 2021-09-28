package hash_table;

import java.util.*;

class _202_HappyNumber {

    /**
     * Brute Force Approach.
     *
     * TC: O(log n)
     * SC: O(n) -> Using hash set
     */
    public boolean isHappy(int n) {
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

        int[] nums = {7, 19, 250};
        for (int i : nums) {
            System.out.println(hn.isHappy(i));
        }
    }
}