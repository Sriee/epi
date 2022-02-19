package graph.union_find;

import util.PrintHypens;

import java.util.Arrays;

public class _1361_ValidateBinaryTree {

    /* ===========================================================================================
     * Approach 1: Union Find
     * ===========================================================================================
     */
    int[] root, rank;
    public boolean validateBinaryTreeNodesUf(int n, int[] leftChild, int[] rightChild) {
        root = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
            rank[i] = 1;
        }

        int numComponents = n;
        for (int i = 0; i < n; i++) {
            if (leftChild[i] != -1) {
                if (!union(i, leftChild[i]))
                    return false;
                numComponents--;
            }

            if (rightChild[i] != -1) {
                if (!union(i, rightChild[i]))
                    return false;
                numComponents--;
            }
        }

        return numComponents == 1;
    }

    private int find(int x) {
        if (x == root[x]) {
            return x;
        }

        return root[x] = find(root[x]);
    }

    private boolean union(int x, int y) {
        int rootX = find(x), rootY = find(y);
        // cycle          child has another parent
        if (rootX == rootY || rootY != y) {
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
     * Approach 2: DFS
     * ===========================================================================================
     */
    public boolean validateBinaryTreeNodesDfs(int n, int[] leftChild, int[] rightChild) {
        int root = findRoot(n, leftChild, rightChild);
        if (root == -1)
            return false;

        int visitedCount = dfs(root, leftChild, rightChild, new int[n]);
        return visitedCount == n;
    }

    private int findRoot(int n, int[] left, int[] right) {
        boolean[] seen = new boolean[n + 1];

        for (int i = 0; i < n; i++) {
            seen[left[i] + 1] = true;
            seen[right[i] + 1] = true;
        }

        for (int i = 0; i < n; i++) {
            if (!seen[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    private int dfs(int node, int[] left, int[] right, int[] visited) {
        if (visited[node] != 0)
            return visited[node];

        visited[node] = -1;
        int[] neighbors = {left[node], right[node]};
        int vc = 1;

        for (int neighbor: neighbors) {
            if (neighbor == -1)
                continue;
            // Cycle
            int len = dfs(neighbor, left, right, visited);
            if (len == -1)
                return -1;
            vc += len;
        }
        visited[node] = 1;
        return vc;
    }

    public static void main(String[] args) {
        _1361_ValidateBinaryTree vbt = new _1361_ValidateBinaryTree();
        int[] ns = {6, 3, 4, 4};
        int[][] leftChilds = {
                {-1, 0, 1, 2, 3, 4},
                {-1, -1, 1},
                {1, 0, 3, -1},
                {1, -1, 3, -1}
        };
        int[][] rightChilds = {
                {-1, -1, -1, -1, -1, -1},
                {1, -1, -1},
                {-1, -1, -1, -1},
                {-1, -1, -1, -1}
        };

        for (int i = 0; i < ns.length; i++) {
            System.out.printf("%d.\tn=%d, leftChild=%s, rightChild=%s\n", (i + 1), ns[i], Arrays.toString(leftChilds[i]), Arrays.toString(rightChilds[i]));
            boolean isValidTree = false;
            switch (i) {
                case 0:
                    isValidTree = vbt.validateBinaryTreeNodesUf(ns[i], leftChilds[i], rightChilds[i]);
                    break;
                case 1:
                    isValidTree = vbt.validateBinaryTreeNodesDfs(ns[i], leftChilds[i], rightChilds[i]);
                    break;
            }
            System.out.println("\tIs Valid Binary Tree = " + isValidTree);
            System.out.println(PrintHypens.generate());
        }
    }
}
