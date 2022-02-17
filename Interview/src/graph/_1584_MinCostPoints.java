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

    int[] root, rank;

    /* ===========================================================================================
     * Approach 1: Minimum Spanning Tree - Kruskal Algorithm
     * ===========================================================================================
     */
    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<Point> graph = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        int n = points.length;

        root = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            root[i] = i;
            rank[i] = 1;
            for (int j = i + 1; j < n; j++) {
                graph.offer(new Point(i, j, manhattanDistance(points[i], points[j])));
            }
        }

        int cost = 0, numComponents = n;

        while (!graph.isEmpty() && numComponents > 1) {
            Point edge = graph.poll();
            if (union(edge.x, edge.y)) {
                cost += edge.weight;
                numComponents--;
            }
        }

        return cost;
    }

    private int find(int x) {
        if (x == root[x]) {
            return x;
        }

        return root[x] = find(root[x]);
    }

    private boolean union(int x, int y) {
        int rootX = find(x), rootY = find(y);

        if (rootX == rootY)
            return false;

        if (rank[rootX] > rank[rootY]) {
            root[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            root[rootX] = rootY;
        } else {
            root[rootY] = rootX;
            rank[rootX]++;
        }

        return true;
    }

    /* ===========================================================================================
     * Approach 2: Minimum Spanning Tree - Prims Algorithm
     * ===========================================================================================
     */
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
        int res;

        for (int i = 0; i < points.length; i++) {
            System.out.println((i + 1) + ".\tPoints: " + Arrays.deepToString(points[i]));
            if (i == 0)
                res = mcp.minCostConnectPointsPrims(points[i]);
            else
                res = mcp.minCostConnectPoints(points[i]);

            System.out.println("\tMinimum cost to connect all points= " + res);
            System.out.println(PrintHypens.generate());
        }
    }
}
