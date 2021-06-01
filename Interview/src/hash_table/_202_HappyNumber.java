package hash_table;

import java.util.*;

class _202_HappyNumber {
    public boolean isHappy(int n) {
        if (n == 1)
            return true;

        Set<Integer> seen = new HashSet<>();

        while (n > 0) {
            n = findHappyNumber(n);

            if (n == 1)
                return true;

            if (seen.contains(n))
                break;

            seen.add(n);
        }

        return false;
    }

    private int findHappyNumber(int n) {
        int sum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
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