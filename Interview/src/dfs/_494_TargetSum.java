package dfs;

import java.util.Arrays;

/**
 * DFS + Memoization. Did not look into DP Approach.
 */
public class _494_TargetSum {
    int[][] mem;

    public int findTargetSumWays(int[] nums, int target) {
        /*
         * Why 2001?
         * The bound for the target is 1000. Since we are considering [-1, 1] for nums[idx], positive number are
         * memorized from mem[1000 to 2001] while negative numbers are memorized at mem[0 to 999].
         *
         * This is also the reason for adding + 1000 while storing the result of a dfs traversal into mem array at
         * line 41.
         * mem[idx][soFar + 1000] = add + sub;
         */
        mem = new int[nums.length][2001];

        for (int[] row : mem)
            Arrays.fill(row, Integer.MIN_VALUE);

        return dfs(nums, target, 0, 0);
    }

    private int dfs(int[] nums, int target, int soFar, int idx) {
        if (idx == nums.length) {
            if (soFar == target)
                return 1;
            else
                return 0;
        } else {

            if (mem[idx][soFar + 1000] == Integer.MIN_VALUE) {
                int sub = dfs(nums, target, soFar - nums[idx], idx + 1);
                int add = dfs(nums, target, soFar + nums[idx], idx + 1);

                mem[idx][soFar + 1000] = add + sub;
            }

            return mem[idx][soFar + 1000];
        }
    }
}
