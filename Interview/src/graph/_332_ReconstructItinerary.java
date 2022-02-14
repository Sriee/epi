package graph;

import java.util.*;

public class _332_ReconstructItinerary {

    /**
     * Eulerian path approach.
     *
     * The difference between DFS approach and Eulerian approach is that we won't visit already visited node in the
     * traditional DFS Approach. But in, we do visit already visited edge.
     *
     * But how to avoid cycles?
     * Once we traversed an edge, we remove the edge from the graph.
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        // We use a priority queue, so that the itineraries are ordered in lexicographic order
        Map<String, PriorityQueue<String>> graph = new HashMap<>();

        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);

            if (!graph.containsKey(from)) {
                graph.put(from, new PriorityQueue<>());
            }
            graph.get(from).offer(to);
        }

        LinkedList<String> result = new LinkedList<>();
        // The requirement for the problem is to always start from "JFK"
        dfs(graph, "JFK", result);
        return result;
    }

    private void dfs(Map<String, PriorityQueue<String>> graph, String arrival, LinkedList<String> result) {
        PriorityQueue<String> destinations = graph.get(arrival);

        while (destinations != null && !destinations.isEmpty()) {
            dfs(graph, destinations.poll(), result);
        }
        result.addFirst(arrival);
    }

    public static void main(String[] args) {
        _332_ReconstructItinerary ri = new _332_ReconstructItinerary();
        List<List<String>> tickets = new ArrayList<>();

        // [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
        tickets.add(Arrays.asList("JFK", "SFO"));
        tickets.add(Arrays.asList("JFK", "ATL"));
        tickets.add(Arrays.asList("SFO", "ATL"));
        tickets.add(Arrays.asList("ATL", "JFK"));
        tickets.add(Arrays.asList("ATL", "SFO"));
        List<String> itineraries = ri.findItinerary(tickets);
        System.out.println(itineraries);
    }
}
