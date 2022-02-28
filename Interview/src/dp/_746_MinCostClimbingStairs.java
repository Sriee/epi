package dp;

import java.util.*;
import java.util.stream.Stream;

public class _746_MinCostClimbingStairs {

    Map<Integer, Integer> mem;

    /**
     * Top-Down Dynamic Programming Approach with Memoization
     *
     * TC: O(n)
     * SC: O(n)
     */
    public int minCostClimbingStairsTopDown(int[] cost) {
        mem = new HashMap<>();

        return dp(cost, cost.length);
    }

    private int dp(int[] cost, int i) {
        if (i < 2) {
            return 0;
        }

        if (!mem.containsKey(i)) {
            mem.put(i, Math.min(dp(cost, i - 1) + cost[i - 1], dp(cost, i - 2) + cost[i - 2]));
        }

        return mem.get(i);
    }

    /**
     * Bottom-up Dynamic Programming Approach
     *
     * TC: O(n)
     * SC: O(n)
     */
    public int minCostClimbingStairs(int[] cost) {
        int prev1 = 0, prev2 = 0;

        for (int i = 2; i <= cost.length; i++) {
            int temp = prev1;
            prev1 = Math.min(prev2 + cost[i - 2], prev1 + cost[i - 1]);
            prev2 = temp;
        }

        return prev1;
    }

    public static void main(String[] args) {
        _746_MinCostClimbingStairs mc = new _746_MinCostClimbingStairs();

        int[][] costs = {
                {10, 15, 20},
                {1, 100, 1, 1, 1, 100, 1, 1, 100, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 132, 235, 99, 2, 993, 521, 64, 5, 61, 6, 12},
                {13,5,68,7,36,4,8,38,70,8,7,9,36,54,6,2,8,35,47,10,3,8,47,23,108}
        };
        int[] expectedResults = {15, 6, 769, 189};

        for (int i = 0; i < costs.length; i++) {
            System.out.println((i + 1) + ".\t Input Array: '" + Arrays.toString(costs[i]) + "'");
            int actualResult = (i & 1) == 0 ? mc.minCostClimbingStairsTopDown(costs[i]) : mc.minCostClimbingStairs(costs[i]);
            System.out.println("\t Minimum cost for climbing stairs: " + actualResult);
            assert mc.minCostClimbingStairs(costs[i]) == expectedResults[i];
            Stream.generate(() -> "-").limit(100).forEach(System.out::print);
            System.out.println();
        }
    }
}
