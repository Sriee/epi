package graph;

import java.util.*;
import util.PrintHypens;

public class _323_ConnectedComponents {

    /**
     * Approach 1: Iterative approach instead of recursion.
     * Note: We are still doing a DFS on each vertex
     * Run time: 8 ms
     */
    public int countComponents(int n, int[][] edges) {
        int count = 0;
        Set<Integer>[] graph = new HashSet[n];
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[n];

        // Build graph
        for(int i = 0; i < n; i++)
            graph[i] = new HashSet<>();

        for(int[] pair : edges) {
            graph[pair[0]].add(pair[1]);
            graph[pair[1]].add(pair[0]);
        }

        // DFS on each node
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                count++;
                stack.push(i);

                while(!stack.isEmpty()) {
                    int vertex = stack.pop();
                    visited[vertex] = true;
                    for(int neighbor : graph[vertex]) {
                        if(!visited[neighbor]) {
                            stack.push(neighbor);
                        }
                    }
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

        for (int i = 0; i < inputs.length; i++) {
            System.out.printf("\n%d.\tEdges = %s\n", (i + 1), Arrays.deepToString(inputs[i]));
            System.out.println("\tNumber of connected components " + cc.countComponents(ns[i], inputs[i]));
            System.out.println(PrintHypens.generate());
        }
    }

}
