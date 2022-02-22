package graph;

import javafx.util.Pair;
import util.PrintHypens;

import java.util.*;

public class _743_NetworkDelayTime {

    /*
     * Dijstra's Shortest path Algorithm
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<Pair<Integer, Integer>>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] time : times) {
            graph.get(time[0]).add(new Pair<>(time[1], time[2]));
        }

        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(
                Comparator.comparingInt(Pair::getValue)
        );
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        pq.offer(new Pair<>(k, 0));

        while (!pq.isEmpty()) {
            Pair<Integer, Integer> top = pq.poll();
            int node = top.getKey(), time = top.getValue();

            if (time > dist[node])
                continue;

            for (Pair<Integer, Integer> neighbor : graph.get(node)) {
                int neighborNode = neighbor.getKey(), neighborTime = neighbor.getValue();

                if (dist[neighborNode] > neighborTime + time) {
                    dist[neighborNode] = neighborTime + time;
                    pq.offer(new Pair<>(neighborNode, dist[neighborNode]));
                }
            }
        }

        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            res = Math.max(dist[i], res);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public static void main(String[] args) {
        _743_NetworkDelayTime bdt = new _743_NetworkDelayTime();
        int[] ns = {4, 2, 2};
        int[] ks = {2, 1, 2};
        int[][][] times = {
                {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}},
                {{1, 2, 1}},
                {{1, 2, 1}}
        };

        for (int i = 0; i < ns.length; i++) {
            System.out.printf("\n%d.\tn = %d, k = %d, Times = %s\n", (i + 1), ns[i], ks[i], Arrays.deepToString(times[i]));
            System.out.printf("\tMinimum time it takes for all the n nodes to receive the signal = %d\n", bdt.networkDelayTime(times[i], ns[i], ks[i]));
            System.out.println(PrintHypens.generate());
        }
    }
}
