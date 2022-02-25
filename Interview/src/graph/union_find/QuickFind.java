package graph.union_find;

/*
 * SC for Quick Find: O(n) -> Size required for the root array
 */
public class QuickFind {

    int[] root;

    /**
     * TC: O(n) - to initialize the root array
     */
    public QuickFind(int size) {
        root = new int[size];
        for (int i = 0; i < size; i++)
            root[i] = i;
    }

    /**
     * TC: O(1)
     */
    public int find(int x) {
        return root[x];
    }

    /**
     * TC: O(1)
     */
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    /**
     * TC: O(n) - Iterate through root array and update the root vertices for all vertices that is going to be merged
     * into another set.
     */
    public void union(int x, int y) {
        int rootX = root[x], rootY = root[y];

        if (rootX != rootY) {
            for (int i = 0; i < root.length; i++) {
                if (root[i] == rootY) {
                    root[i] = rootX;
                }
            }
        }
    }

    public static void main(String[] args) {
        QuickFind uf = new QuickFind(10);

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
