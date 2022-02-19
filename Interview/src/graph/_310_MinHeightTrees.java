package graph;

import util.PrintHypens;

import java.util.*;

public class _310_MinHeightTrees {

    /**
     * Tree Ring order traversal
     *
     * TC: O(|V|)
     * SC: O(|V|)
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();

        if (n < 2) {
            res.add(0);
            return res;
        }

        List<Set<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (graph.get(i).size() == 1) {
                leaves.add(i);
            }
        }

        while (n > 2) {
            n -= leaves.size();
            List<Integer> nextLeaves = new ArrayList<>();

            for (int leafNode : leaves) {
                for (int neighbor : graph.get(leafNode)) {
                    graph.get(neighbor).remove(leafNode);

                    if (graph.get(neighbor).size() == 1) {
                        nextLeaves.add(neighbor);
                    }
                }
            }

            leaves = nextLeaves;
        }

        return leaves;
    }

    public static void main(String[] args) {
        _310_MinHeightTrees mht = new _310_MinHeightTrees();

        int[][][] edges = {
                {{1, 0}, {1, 2}, {1, 3}},
                {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}},
                {}
        };
        int[] ns = {4, 6, 1};

        for (int i = 0; i < ns.length; i++) {
            System.out.printf("%d.\tn = %d, edges = %s\n", (i + 1), ns[i], Arrays.deepToString(edges[i]));
            System.out.println("\tRoots of Minimum Height Trees = " + mht.findMinHeightTrees(ns[i], edges[i]));
            System.out.println(PrintHypens.generate());
        }
    }
}
