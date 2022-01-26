package dfs;

import java.util.*;

public class _547_NumberProvinces {

    /* ===========================================================================================
     * Approach 1: Connect Component pattern
     * ===========================================================================================
     * Another problem that follows Connect Component pattern. The only difference
     * is how the connectivity between cities are defined.
     *
     * Optimum Solution.
     */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length, count = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                dfs(isConnected, visited, i);
            }
        }
        return count;
    }

    private void dfs(int[][] graph, boolean[] visited, int vertex) {
        visited[vertex] = true;

        for (int city = 0; city < graph[0].length; city++) {
            if (city == vertex || graph[vertex][city] == 0 || visited[city])
                continue;
            dfs(graph, visited, city);
        }
    }

    /* ===========================================================================================
     * Approach 2: Union Find
     * ===========================================================================================
     */
    int[] root, rank;
    int count;

    private int find(int x) {
        if (x == root[x])
            return x;
        return root[x] = find(root[x]);
    }

    private void union(int x, int y) {
        int rootX = find(x), rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                root[rootX] = rootY;
            } else {
                root[rootY] = rootX;
                rank[rootX]++;
            }
            count--;
        }
    }

    public int findNumOfProvinces(int[][] isConnected) {
        int n = isConnected.length;
        root = new int[n];
        rank = new int[n];
        count = n;

        for (int i = 0; i < n; i++) {
            root[i] = i;
            rank[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    union(i, j);
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        _547_NumberProvinces np = new _547_NumberProvinces();

        int[][] graph1 = {
                {1, 1, 0}, {1, 1, 0}, {0, 0, 1}
        };
        System.out.println(np.findCircleNum(graph1));

        int[][] graph2 = {
                {1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
                {1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1}
        };
        System.out.println(np.findNumOfProvinces(graph2));
    }
}
