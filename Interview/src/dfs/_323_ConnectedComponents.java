package dfs;

import java.util.*;

public class _323_ConnectedComponents {

    private Map<Integer, Set<Integer>> buildGraph(int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        Set<Integer> neighbors;

        for(int[] pair : edges) {
            graph.putIfAbsent(pair[0], new HashSet<>());
            graph.putIfAbsent(pair[1], new HashSet<>());

            neighbors = graph.get(pair[0]);
            neighbors.add(pair[1]);

            neighbors = graph.get(pair[1]);
            neighbors.add(pair[0]);
        }

        return graph;
    }

    private void dfs(Map<Integer, Set<Integer>> graph, Set<Integer> visited, List<Integer> components, Integer vertex) {
        visited.add(vertex);
        components.add(vertex);

        for(Integer neighbor : graph.get(vertex)) {
            if(!visited.contains(neighbor))
                dfs(graph, visited, components, neighbor);
        }
    }

    private int countComponents(Map<Integer, Set<Integer>> graph) {
        int count = 0;
        Set<Integer> visited = new HashSet<>();
        List<List<Integer>> components = new ArrayList<>();

        for(Integer node : graph.keySet()) {
            if(!visited.contains(node)) {
                count++;
                List<Integer> comp = new ArrayList<>();
                dfs(graph, visited, comp, node);
                components.add(comp);
            }

            visited.add(node);
        }

        System.out.println("The closed components are:");
        for(int i = 0; i < components.size(); i++) {
            System.out.printf("%d : %s\n", i + 1, components.get(i).toString());
        }
        return count;
    }

    private void print(Map<Integer, Set<Integer>> graph) {
        for(Integer node : graph.keySet())
            System.out.println(node + " " + graph.get(node).toString());
    }

    /**
     * Approach 2: Iterative approach instead of recursion.
     * Note: We are still doing a DFS on each vertex
     * Run time: 8 ms
     */
    public int countComponents(int n, int[][] edges) {
        int count = 0;
        HashSet<Integer>[] graph = new HashSet[n];
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
        int[][] edges = new int[][] {
                {0, 1},
                {1, 2},
                {3, 4}
        };

/*        Map<Integer, Set<Integer>> graph = cc.buildGraph(edges);
        cc.print(graph);
        System.out.println(cc.countComponents(graph));*/
        System.out.println(cc.countComponents(5, edges));
    }

}
