package dfs;

import java.util.*;

/**
 * Did not solve.
 * <p>
 * Recursive Approach - TLE Since we are considering all combination. Intuition of using combination is right but for
 * numbers > 10000 the stack is overflowing. Memoization does not help either.
 */
public class _279_PerfectSquares {

    /**
     * BFS Approach: Trick is to identify the relation ship between current element to next element.
     */
    private int numSquares(int n) {
        int max = (int) Math.sqrt(n), curr, next, step = 0;
        int[] sq = new int[max + 1];

        for (int i = 1; i <= max; i++)
            sq[i] = i * i;

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(0);

        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;

            for (int k = 0; k < size; k++) {
                curr = queue.remove();

                for (int i = 1; i <= max; i++) {
                    next = curr + sq[i];

                    if (next <= n) {
                        if (next == n)
                            return step;
                        else
                            queue.offer(next);
                    }
                }
            }
        }

        return step;
    }

    /**
     * Dynamic Programming Approach
     * <p>
     * For n = 12, what are the possible perfect squares possible: [1, 4, 9]
     * Think of the problem
     * 1 + minSquares(12 - 1)
     * 1 + 1 + minSquares(11 - 1)
     * 1 + 4 + minSquares(11 - 4)
     * 1 + 9 + minSquares(11 - 1)
     * <p>
     * 4 + minSquares(12 - 4)
     * 9 + minSquares(12 - 9)
     */
    private int numSquaresDP(int n) {
        int max = (int) Math.sqrt(n);
        int[] dp = new int[n + 1];
        int[] sq = new int[max + 1];

        for (int i = 1; i <= max; i++)
            sq[i] = i * i;

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= max; j++) {
                if (i < sq[j])
                    break;
                dp[i] = Math.min(dp[i], dp[i - sq[j]] + 1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        _279_PerfectSquares ps = new _279_PerfectSquares();
        int[] nums = {159, 19, 169, 5172, 9999};
        int[] expected = {4, 3, 1, 3, 4};

        // To enable assertions in a java, add "-ea" flag to javac compiler
        for (int i = 0; i < nums.length; i++) {
            assert ps.numSquares(nums[i]) == expected[i] : String.format("BFS: Assertion check failed for i = %d", i);
            assert ps.numSquaresDP(nums[i]) == expected[i] : String.format("DP: Assertion check failed for i = %d", i);
        }
    }
}
