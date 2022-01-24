package graph.union_find;

public class QuickUnion {
    int[] root;
    public QuickUnion(int size) {
        root = new int[size];
        for (int i = 0; i < size; i++)
            root[i] = i;
    }

    // TC: O(n)
    public int find(int x) {
        while (x != root[x])
            x = root[x];

        return x;
    }

    // TC: O(n)
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    // TC: O(n)
    public void union(int x, int y) {
        int rootX = find(x), rootY = find(y);

        if (rootX != rootY) {
            root[rootY] = rootX;
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
