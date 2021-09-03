package array;

import java.util.Arrays;

class _66_PlusOne {
    public int[] plusOne(int[] digits) {
        int n = digits.length, carry = 1;
        int i = n - 1;

        do {
            digits[i] += carry;
            if (digits[i] > 9) {
                digits[i] = 0;
                carry = 1;
            } else {
                carry = 0;
            }
        } while (carry != 0 && i-- > 0);

        if (carry == 0) return digits;

        int[] res = new int[n + 1];
        System.arraycopy(digits, 0, res, 1, n);
        res[0] = 1;
        return res;
    }

    public static void main(String[] args) {
        _66_PlusOne po = new _66_PlusOne();
        int[][] input = {
                {0},
                {9},
                {9, 9},
                {4, 9, 2, 1},
                {1, 2, 8, 9}
        };

        for (int[] nums : input) {
            int[] res = po.plusOne(nums);
            System.out.println(Arrays.toString(res));
        }
    }
}