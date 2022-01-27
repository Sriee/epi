package graph;

public class _261_ValidTree {

    /* ===========================================================================================
     * Approach 1: Union Find
     * ===========================================================================================
     */
    int[] root, rank;

    public boolean validTree(int n, int[][] edges) {

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

}
