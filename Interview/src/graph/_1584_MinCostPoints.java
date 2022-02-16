package graph;

import util.PrintHypens;

import java.util.*;

public class _1584_MinCostPoints {
    class Point {
        int x, y, weight;

        public Point(int _x, int _y, int _w) {
            this.x = _x;
            this.y = _y;
            this.weight = _w;
        }
    }
    public int minCostConnectPointsPrims(int[][] points) {
        List<List<Point>> graph = new ArrayList<>();
        int n = points.length;

        for (int i = 0; i < n; i++) {
            List<Point> edges = new ArrayList<>();
            for (int j = i + 1; j < n; j++) {
                edges.add(new Point(i, j, manhattanDistance(points[i], points[j])));
            }

            if(!edges.isEmpty()) graph.add(edges);
        }

        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        boolean[] visited = new boolean[n];
        int cost = 0, visitedCount = 0;

        pq.addAll(graph.get(0));

        while (visitedCount <= n) {
            Point edge = pq.poll();

            if (visited[edge.x])
                continue;

            cost += edge.weight;
            visited[edge.x] = true;
            visitedCount++;

            for (Point neighbor : graph.get(edge.y)) {
                if (!visited[neighbor.x]) {
                    pq.offer(neighbor);
                }
            }
        }

        return cost;
    }

    private int manhattanDistance(int[] p, int[] q) {
        return Math.abs(p[0] - q[0]) + Math.abs(p[1] - q[1]);
    }

    public static void main(String[] args) {
        _1584_MinCostPoints mcp = new _1584_MinCostPoints();

        int[][][] points = {
                {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}},
                {{-1, 11}, {-2, 2}, {3, 5}, {4, 10}}
        };

        for (int i = 0; i < points.length; i++) {
            System.out.println((i + 1) + ".\tPoints: " + Arrays.deepToString(points[i]));
            System.out.println("\tMinimum cost to connect all points= " + mcp.minCostConnectPointsPrims(points[i]));
            System.out.println(PrintHypens.generate());
        }
    }
}
