package graph;

import util.PrintHypens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _797_AllPathSourceToTarget {

    /* ========================================================================
     * Approach 1: DFS Approach
     * ========================================================================
     * TC: O(2^n x n)
     * SC: O(n)
     *
     * where n = number of nodes
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(graph, 0, path, new boolean[graph.length], res);
        return res;
    }

    private void dfs(int[][] graph, int vertex, List<Integer> path, boolean[] visited, List<List<Integer>> res) {
        if (vertex == graph.length - 1) {
            res.add(new ArrayList<>(path));
            return;
        }

        visited[vertex] = true;

        for (int neighbor : graph[vertex]) {
            if (!visited[neighbor]) {
                path.add(neighbor);
                dfs(graph, neighbor, path, visited, res);
                path.remove(path.size() - 1);
            }
        }

        visited[vertex] = false;
    }

    public static void main(String[] args) {
        _797_AllPathSourceToTarget apst = new _797_AllPathSourceToTarget();
        int[][][] graphs = {
                {{1, 2}, {3}, {3}, {}},
                {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}}
        };

        for (int i = 0; i < graphs.length; i++) {
            System.out.printf("\n%d.\tMatrix= %s\n", (i + 1), Arrays.deepToString(graphs[i]));
            System.out.println("\tAll path from source to target: " + apst.allPathsSourceTarget(graphs[i]));
            System.out.println(PrintHypens.generate());
        }
    }
}
