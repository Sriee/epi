package graph;

import util.PrintHypens;

import java.util.*;

public class _1971_PathExists {

    /* ========================================================================
     * Approach 1: Union Find Approach
     * ========================================================================
     * TC: O(n)
     * SC: O(1)
     */
    int[] root, rank;

    public boolean validPathUF(int n, int[][] edges, int source, int destination) {
        root = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            root[i] = i;
            rank[i] = 1;
        }

        for (int[] edge: edges) {
            union(edge[0], edge[1]);
        }

        return find(source) == find(destination);
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
        }
    }

    private int find(int x) {
        if (x == root[x]) {
            return x;
        }

        return root[x] = find(root[x]);
    }

    /* ========================================================================
     * Approach 2: DFS Approach
     * ========================================================================
     * TC: O(V + E)
     * SC: O(V + E)
     *
     * where V = number of vertices
     *       E = number of edges
     */
    public boolean validPathDfs(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        return dfs(graph, source, destination, new boolean[n]);
    }

    private boolean dfs(List<List<Integer>> graph, int vertex, int destination, boolean[] visited) {
        if (vertex == destination)
            return true;

        visited[vertex] = true;
        for (int neighbor : graph.get(vertex)) {
            if (!visited[neighbor]) {
                boolean ret = dfs(graph, neighbor, destination, visited);
                if (ret)
                    return ret;
            }
        }

        return false;
    }

    /* ========================================================================
     * Approach 3: BFS Approach
     * ========================================================================
     * TC: O(V + E)
     * SC: O(V + E)
     *
     * where V = number of vertices
     *       E = number of edges
     */
    public boolean validPathBfs(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        queue.offer(source);
        visited[source] = true;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            if (vertex == destination)
                return true;

            for (int neighbor : graph.get(vertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        _1971_PathExists pe = new _1971_PathExists();
        int[][][] graphs = {
                {{0, 1}, {1, 2}, {2, 0}},
                {{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}},
                {{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}}
        };
        int[] sources = {0, 0, 1};
        int[] destination = {2, 2, 3};

        for (int i = 0; i < graphs.length; i++) {
            System.out.printf("\n%d.\tGraph= %s\n", (i + 1), Arrays.deepToString(graphs[i]));
            boolean res = switch (i) {
                case 0 -> pe.validPathDfs(3, graphs[i], sources[i], destination[i]);
                case 1 -> pe.validPathBfs(6, graphs[i], sources[i], destination[i]);
                case 2 -> pe.validPathUF(6, graphs[i], sources[i], destination[i]);
                default -> false;
            };

            System.out.printf("\tDoes path exists from %d to %d? %s\n", sources[i], destination[i], res);
            System.out.println(PrintHypens.generate());
        }
    }
}
