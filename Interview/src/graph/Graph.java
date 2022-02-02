package graph;

import java.util.*;

public class Graph {

    private List<List<Integer>> buildGraph(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]); // For undirected graph, add mutual connection
        }

        return graph;
    }

    private void printGraph(int n, List<List<Integer>> graph) {
        for (int i = 0; i < n; i++) {
            System.out.printf("%d: %s\n", i, Arrays.toString(graph.get(i).toArray()));
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        /*
         * There are three ways to represent a Graph
         *  1. Using adjacency list
         *      - The preferred way to represent a graph in an Interview. The implementation is shown in this class.
         *  2. Using adjacency matrix
         *      - Often, we'd only use an adjacency matrix if we know that the number of edges is substantially higher
         *      than the number of nodes. If the number of edges are not high, the adjacency matrix will become a sparse
         *      matrix and hence it is not first choice when representing a graph.
         *  3. Using a linked representation
         *      - Refer commit '7ea759e' for the linked representation using Graph and Vertex class. They are
         *      complicated representation of a graph using objects and is not preferable in an interview.
         */

        // The problem provides the number of nodes in a graph and its edges in a 2D array.
        // Build graph 0-1-4-5-6 2-7-8 3
        List<List<Integer>> graph = g.buildGraph(9, new int[][] {
                {0, 1}, {2, 7}, {1, 4}, {4, 5}, {5, 6}, {7, 8}
        });

        // Print graph
        g.printGraph(9, graph);
    }
}
