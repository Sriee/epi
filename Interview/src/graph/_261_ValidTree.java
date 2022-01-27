package graph;

import util.PrintHypens;

import java.util.Arrays;

public class _261_ValidTree {

    /* ===========================================================================================
     * Approach 1: Union Find
     * ===========================================================================================
     */
    int[] root, rank;

    public boolean validTree(int n, int[][] edges) {

        /*
         * For a graph to be valid, it needs to satisfy two properties.
         *  1. The graph should be fully connected and
         *  2. It should not have cycles
         *
         * If the number of edges < n, then there will be isolated nodes. If a graph has isolated nodes, then
         * it won't be fully connected.
         *
         * If the number of edges > n, then there will be cycles in the graph. Hence, we return false in the
         * below step.
         */
        if (edges.length != n - 1)
            return false;

        // Initialize root and rank
        root = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            root[i] = i;
            rank[i] = 1;
        }

        for (int[] edge : edges) {
            if (!union(edge[0], edge[1]))
                return false;
        }

        return true;
    }

    private boolean union(int x, int y) {
        int rootX = find(x), rootY = find(y);

        if (rootX == rootY)
            return false;

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

    private int find(int x) {
        if (x == root[x])
            return x;
        return root[x] = find(root[x]);
    }

    public static void main(String[] args) {
        _261_ValidTree vt = new _261_ValidTree();

        int[][][] inputs = {
                // Test case 1
                {{0, 1}, {0, 2}, {0, 3}, {1, 4}},
                // Test case 2
                {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}},
                // Test case 3
                {{0, 1}, {2, 3}},
                // Test case 4
                {{1, 2}, {6, 7}, {3, 8}, {8, 9}, {5, 6}, {2, 5}},
        };
        int[] ns = new int[]{5, 5, 5, 10};

        for (int i = 0; i < inputs.length; i++) {
            System.out.printf("\n%d.\tEdges = %s\n", (i + 1), Arrays.deepToString(inputs[i]));
            System.out.println("\tIs Graph a Valid Tree? " + vt.validTree(ns[i], inputs[i]));
            System.out.println(PrintHypens.generate());
        }
    }
}
