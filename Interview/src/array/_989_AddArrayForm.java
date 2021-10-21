package array;

import java.util.*;

public class _989_AddArrayForm {

    /**
     * TC: O(n)
     * SC: O(n)
     */
    public List<Integer> addToArrayForm(int[] num, int k) {
        Deque<Integer> res = new ArrayDeque<>();
        int carry = 0, sum = 0;

        for (int i = num.length - 1; i >= 0 || k > 0; ) {
            sum = carry;

            if (i >= 0) sum += num[i--];
            if (k > 0) {
                sum += (k % 10);
                k /= 10;
            }

            res.offerFirst(sum % 10);
            carry = sum / 10;
        }

        if (carry != 0)
            res.offerFirst(carry);

        return new ArrayList<>(res);
    }

    public static void main(String[] args) {
        _989_AddArrayForm aaf = new _989_AddArrayForm();

        int[][] inp = {
            {1, 2, 6, 3, 0, 7, 1, 7, 1, 9, 7, 5, 6, 6, 4, 4, 0, 0, 6, 3},
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 9}
        };
        int[] ks = {516, 1};

        for (int i = 0; i < ks.length; i++) {
            System.out.println(aaf.addToArrayForm(inp[i], ks[i]));
        }
    }
}
