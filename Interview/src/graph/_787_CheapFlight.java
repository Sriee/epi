package graph;

import util.PrintHypens;

import java.util.Arrays;

public class _787_CheapFlight {

    /*
     * Bellman Ford Shortest Path Algorithm
     *
     * TC: O((N + E) * K)
     *     where N = number of cities, E = number of flights, K = K iterations
     * SC: O(N)
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        if (src == dst)
            return 0;

        int[] curr = new int[n];
        Arrays.fill(curr, Integer.MAX_VALUE);
        curr[src] = 0;
        boolean isRoutePossible = false;

        for (int i = 0; i <= k && !isRoutePossible; i++) {
            int[] prev = curr.clone();
            isRoutePossible = true;

            for (int[] flight : flights) {
                int source = flight[0], destination = flight[1], cost = flight[2];

                // Short circuit
                if (prev[source] != Integer.MAX_VALUE && curr[destination] > prev[source] + cost) {
                    curr[destination] = prev[source] + cost;
                    isRoutePossible = false;
                }
            }
        }

        return curr[dst] == Integer.MAX_VALUE ? -1 : curr[dst];
    }

    public static void main(String[] args) {
        _787_CheapFlight cf = new _787_CheapFlight();
        int[] ns = {4, 3, 3, 4};
        int[] sources = {0, 0, 0, 0};
        int[] destinations = {3, 2, 2, 3};
        int[] ks = {1, 1, 0, 4};
        int[][][] flights = {
                {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}},
                {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}},
                {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}},
                {{0, 1, 1}, {0, 2, 5}, {1, 2, 1}, {2, 3, 1}}
        };

        for (int i = 0; i < ns.length; i++) {
            System.out.printf("\n%d.\tn = %d, source = %d, destination = %d, k = %d, flights = %s\n",
                    (i + 1), ns[i], sources[i], destinations[i], ks[i], Arrays.deepToString(flights[i]));

            System.out.printf("\tCheapest price from %d to %d with at most %d stops = %s\n", sources[i],
                    destinations[i], ks[i], cf.findCheapestPrice(ns[i], flights[i], sources[i], destinations[i], ks[i]));
            System.out.println(PrintHypens.generate());
        }
    }
}
