package dp;

import java.util.Arrays;

public class _300_LongestIncreasingSubsequence {

    /**
     * TC: O(n ^ 2)
     * SC: O(n) -> To construct the 'dp' array.
     *
     * @see <a href="https://cp-algorithms.com/sequences/longest_increasing_subsequence.html">CP-Algorithms LIS</a>
     */
    private int lengthOfLIS(int[] nums) {
        int n = nums.length, res = Integer.MIN_VALUE;
        int[] d = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i])
                    d[i] = Math.max(d[i], d[j] + 1);
            }
            res = Math.max(res, d[i]);
        }

        System.out.println(Arrays.toString(d));
        return res + 1;
    }

    public static void main(String[] args) {
        _300_LongestIncreasingSubsequence lis = new _300_LongestIncreasingSubsequence();
        int[] inp = {10, 22, 9, 33, 21, 50, 41, 60, 80};
        System.out.println(lis.lengthOfLIS(inp));
    }
}
