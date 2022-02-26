package graph.union_find;

/*
 * SC for Quick Find: O(n) -> Size required for the root array
 */
public class QUWithoutOptimization {
    int[] root;
    public QUWithoutOptimization(int size) {
        root = new int[size];
        for (int i = 0; i < size; i++)
            root[i] = i;
    }

    /**
     * TC: O(n) - WC Traverse the height of the tree to get the root vertex
     */
    public int find(int x) {
        while (x != root[x])
            x = root[x];

        return x;
    }

    /**
     * TC: O(n) - WC One find operation takes O(n) time.
     */
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    /**
     * TC: O(n) - WC One find operation takes O(n) time.
     */
    public void union(int x, int y) {
        int rootX = find(x), rootY = find(y);

        if (rootX != rootY) {
            root[rootY] = rootX;
        }
    }

    public static void main(String[] args) {
        QUWithoutOptimization uf = new QUWithoutOptimization(10);

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
