package graph.union_find;

public class QuickUnion {
    int[] root, rank;

    /**
     * TC: O(n) to initialize the root and the rank array
     *
     * @param size - Number of vertices in a graph
     */
    public QuickUnion(int size) {
        root = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            root[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * Path compression
     *  While traversing the root array to find the root node, we update the parent of intermediate nodes to point
     *  to the root node.
     *
     *  New Time Complexity is O(α(N)) where α(N) is called an ackermann function. This function has O(1) TC on average
     *  so Best Case & Average Case TC = O(1)
     *   & Worst case TC = O(α(N))
     */
    public int find(int x) {
        if (x == root[x])
            return x;

        return root[x] = find(root[x]);
    }

    /**
     * Since this function depends on find calls, it shares the same TC as the find function.
     */
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    /**
     * Union by rank
     *  In this optimization we use the rank array to avoid the graph to be a skewed tree by joining a subtree with a
     *  lower rank to a tree with a higher rank.
     *
     * Since this function depends on find calls, it shares the same TC as the find function.
     */
    public void union(int x, int y) {
        int rootX = find(x), rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY])
                root[rootY] = rootX;
            else if (rank[rootX] < rank[rootY])
                root[rootX] = rootY;
            else {
                root[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    public static void main(String[] args) {
        QuickUnion uf = new QuickUnion(10);

        // 1-2-5-6-7 3-8-9 4
        uf.union(1, 2);
        uf.union(2, 5);
        uf.union(5, 6);
        uf.union(6, 7);
        uf.union(3, 8);
        uf.union(8, 9);
        System.out.println(uf.isConnected(1, 5)); // true
        System.out.println(uf.isConnected(5, 7)); // true
        System.out.println(uf.isConnected(4, 9)); // false

        // 1-2-5-6-7 3-8-9-4
        uf.union(9, 4);
        System.out.println(uf.isConnected(4, 9)); // true
    }
}
