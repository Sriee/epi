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
    public boolean validPath(int n, int[][] edges, int source, int destination) {
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

    public static void main(String[] args) {
        _1971_PathExists pe = new _1971_PathExists();
        int[][][] graphs = {
                {{0, 1}, {1, 2}, {2, 0}},
                {{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}}
        };
        int[] sources = {0, 0, 1};
        int[] destination = {2, 5, 3};

        pe.validPath(3, graphs[0], 0, 2);
        for (int i = 0; i < graphs.length; i++) {
            System.out.printf("\n%d.\tGraph= %s\n", (i + 1), Arrays.deepToString(graphs[i]));
            System.out.printf("\tDoes path exists from %d to %d? %s", sources[i], destination[i],
                    pe.validPath(graphs[i].length, graphs[i], sources[i], destination[i]));
            System.out.println(PrintHypens.generate());
        }
    }
}
