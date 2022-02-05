package graph;

import javafx.util.Pair;
import java.util.*;

public class _1168_WaterDistribution {

    /* ===========================================================================================
     * Approach 1: Minimum Spanning Tree - Prims Algorithm
     * ===========================================================================================
     */
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        List<List<Pair<Integer, Integer>>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());

        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparing(Pair::getValue));
        for (int i = 0; i < wells.length; i++) {
            Pair<Integer, Integer> virtualPair = new Pair<>(i + 1, wells[i]);
            graph.get(0).add(virtualPair);
            pq.offer(virtualPair);
        }

        for (int[] pipe : pipes) {
            graph.get(pipe[0]).add(new Pair<>(pipe[1], pipe[2]));
            graph.get(pipe[1]).add(new Pair<>(pipe[0], pipe[2]));
        }

        Set<Integer> set = new HashSet<>();
        int cost = 0;
        set.add(0);

        while (set.size() <= n) {
            Pair<Integer, Integer> nodePair = pq.poll();

            if (set.contains(nodePair.getKey()))
                continue;
            set.add(nodePair.getKey());
            cost += nodePair.getValue();

            for (Pair<Integer, Integer> neighbor : graph.get(nodePair.getKey())) {
                if (!set.contains(neighbor.getKey())) {
                    pq.offer(neighbor);
                }
            }
        }

        return cost;
    }

    public static void main(String[] args) {
        _1168_WaterDistribution wd = new _1168_WaterDistribution();

        // Test case 1
        int optimizedCost = wd.minCostToSupplyWater(3, new int[]{1, 2, 2}, new int[][]{{1, 2, 1}, {2, 3, 1}});
        System.out.println(optimizedCost);
    }
}
