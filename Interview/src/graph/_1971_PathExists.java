package graph;

import util.PrintHypens;

import java.util.*;

public class _1971_PathExists {

    /* ========================================================================
     * Approach 1: DFS Approach
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
     * Approach 2: BFS Approach
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
        int[] destination = {2, 5, 3};

        for (int i = 0; i < graphs.length; i++) {
            System.out.printf("\n%d.\tGraph= %s\n", (i + 1), Arrays.deepToString(graphs[i]));
            boolean res = false;

            switch (i) {
                case 0:
                    res = pe.validPathDfs(graphs[i].length, graphs[i], sources[i], destination[i]);
                    break;
                case 1:
                    res = pe.validPathBfs(graphs[i].length, graphs[i], sources[i], destination[i]);
                    break;
            }
            System.out.printf("\tDoes path exists from %d to %d? %s", sources[i], destination[i], res);
            System.out.println(PrintHypens.generate());
        }
    }
}
