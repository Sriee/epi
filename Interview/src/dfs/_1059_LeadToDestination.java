package dfs;

import util.PrintHypens;

import java.util.*;

public class _1059_LeadToDestination {

    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        return dfs(graph, source, destination, new int[n]);
    }

    // 0 - White, 1 - Gray, 2 - Black
    private boolean dfs(List<List<Integer>> graph, int vertex, int destination, int[] state) {
        // Have reached an already visited node
        if (state[vertex] != 0) {
            return state[vertex] == 2;
        }

        if (graph.get(vertex).isEmpty()) {
            return vertex == destination;
        }

        state[vertex] = 1;
        for (int neighbor : graph.get(vertex)) {
            if (!dfs(graph, neighbor, destination, state)) {
                return false;
            }
        }

        state[vertex] = 2;
        return true;
    }

    public static void main(String[] args) {
        _1059_LeadToDestination ld = new _1059_LeadToDestination();

        int[][][] edges = {
                {{0, 1}, {1, 2}},
                {{0, 1}, {0, 3}, {1, 2}, {2, 1}},
                {{0, 1}, {0, 2}, {1, 3}, {2, 3}}
        };
        int[] ns = {3, 4, 1};
        int[] sources = {0, 0, 1};
        int[] destinations = {2, 3, 3};

        for (int i = 0; i < edges.length; i++) {
            System.out.printf("\n%d.\tGraph= %s\n", (i + 1), Arrays.deepToString(edges[i]));
            System.out.printf("\tAll path leads to destination (source=%d, destination=%d)? %s\n",
                    sources[i], destinations[i], ld.leadsToDestination(ns[i], edges[i], sources[i], destinations[i]));
            System.out.println(PrintHypens.generate());
        }
    }
}
