package graph;

import util.PrintHypens;

import java.util.*;

public class _323_ConnectedComponents {

    /* ===========================================================================================
     * Approach 1: DFS Recursive Approach
     *
     * TC: O(V + E)
     * SC: O(V + E)
     * ===========================================================================================
     */
    public int countComponentsDFS(int n, int[][] edges) {
        // Use adjacency list to represent a graph
        List<List<Integer>> graph = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] seen = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!seen[i]) {
                dfs(i, graph, seen);
                count++;
            }
        }

        return count;
    }

    private void dfs(int node, List<List<Integer>> graph, boolean[] seen) {
        seen[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!seen[neighbor]) {
                dfs(neighbor, graph, seen);
            }
        }
    }

    /* ===========================================================================================
     * Approach 2: DFS Iterative Approach
     *
     * TC: O(V + E)
     * SC: O(V + E)
     * ===========================================================================================
     */
    public int countComponentsBFS(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        Deque<Integer> queue = new ArrayDeque<>();
        int count = 0;

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] seen = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (seen[i])
                continue;

            seen[i] = true;
            count++;
            queue.offer(i);

            while (!queue.isEmpty()) {
                int node = queue.poll();

                for (int neighbor: graph.get(node)) {
                    if (seen[neighbor])
                        continue;

                    seen[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        _323_ConnectedComponents cc = new _323_ConnectedComponents();

        int[][][] inputs = {
                // Test case 1
                {{0, 1}, {0, 2}, {3, 4}},
                // Test case 2
                {{0, 1}, {1, 2}, {2, 3}, {3, 4}},
                // Test case 3
                {{0, 1}, {2, 3}},
                // Test case 4
                {{1, 2}, {6, 7}, {3, 8}, {8, 9}, {5, 6}, {2, 5}},
        };
        int[] ns = new int[]{5, 5, 5, 10};

        for (int i = 0, j = 0; i < inputs.length; i++) {
            System.out.printf("\n%d.\tEdges = %s\n", (i + 1), Arrays.deepToString(inputs[i]));
            int numberOfComponents = switch (j) {
                case 0 -> cc.countComponentsDFS(ns[i], inputs[i]);
                case 1 -> cc.countComponentsBFS(ns[i], inputs[i]);
                case 2 -> cc.countComponentsUF(ns[i], inputs[i]);
                default -> 0;
            };
            j = (j + 1) % 3;
            System.out.println("\tNumber of connected components " + numberOfComponents);
            System.out.println(PrintHypens.generate());
        }
    }
}
