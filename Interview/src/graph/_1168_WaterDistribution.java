package graph;

import javafx.util.Pair;
import util.PrintHypens;
import java.util.*;

public class _1168_WaterDistribution {

    /* ===========================================================================================
     * Approach 1: Minimum Spanning Tree - Kruskal Algorithm
     * ===========================================================================================
     */
    class Node implements Comparable<Node>{
        int x, y, weight;

        public Node(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    int[] root, rank;
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        root = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            root[i] = i;
            rank[i] = 1;
        }

        List<Node> graph = new ArrayList<>();
        // Add node 0
        for (int i = 0; i < wells.length; i++) {
            graph.add(new Node(0, i + 1, wells[i]));
        }

        // Add remaining pipes
        for (int[] pipe : pipes) {
            graph.add(new Node(pipe[0], pipe[1], pipe[2]));
        }

        Collections.sort(graph);
        int minCost = 0;
        for (Node vertex : graph) {
            if (union(vertex.x, vertex.y)) {
                minCost += vertex.weight;
            }
        }

        return minCost;
    }

    private int find(int x) {
        if (x == root[x]) {
            return x;
        }

        return root[x] = find(root[x]);
    }

    private boolean union(int x, int y) {
        int rootX = find(x), rootY = find(y);

        if (rootX == rootY) {
            return false;
        }

        if (rank[rootX] > rank[rootY]) {
            root[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            root[rootX] = rootY;
        } else {
            root[rootY] = rootX;
            rank[rootX]++;
        }

        return true;
    }

    /* ===========================================================================================
     * Approach 2: Minimum Spanning Tree - Prims Algorithm
     * ===========================================================================================
     */
    public int minCostToSupplyWaterPrims(int n, int[] wells, int[][] pipes) {
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

        int[] ns = {3, 2};
        int[][] wells = {
                {1, 2, 2},
                {1, 1}
        };
        int[][][] pipes = {
                {{1, 2, 1}, {2, 3, 1}},
                {{1, 2, 1}, {1, 2, 2}}
        };

        int res;
        for (int i = 0; i < ns.length; i++) {
            System.out.printf("%d.\tn=%d wells=%s pipes=%s\n", (i + 1), ns[i], Arrays.toString(wells[i]), Arrays.deepToString(pipes[i]));
            if (i == 0) {
                res = wd.minCostToSupplyWaterPrims(ns[i], wells[i], pipes[i]);
            } else {
                res = wd.minCostToSupplyWater(ns[i], wells[i], pipes[i]);
            }
            System.out.println("\tMinimum cost to supply water = " + res);
            System.out.println(PrintHypens.generate());
        }
    }
}
