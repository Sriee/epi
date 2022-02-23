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
        if (src == dst) {
            return 0;
        }

        int[] previous = new int[n];
        int[] current = new int[n];
        for (int i = 0; i < n; i++) {
            previous[i] = Integer.MAX_VALUE;
            current[i] = Integer.MAX_VALUE;
        }
        previous[src] = 0;
        for (int i = 1; i < k + 2; i++) {
            current[src] = 0;
            for (int[] flight : flights) {
                int previous_flight = flight[0];
                int current_flight = flight[1];
                int cost = flight[2];

                if (previous[previous_flight] < Integer.MAX_VALUE) {
                    current[current_flight] = Math.min(current[current_flight],
                            previous[previous_flight] + cost);
                }
            }
            previous = current.clone();
        }
        return current[dst] == Integer.MAX_VALUE ? -1 : current[dst];
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
