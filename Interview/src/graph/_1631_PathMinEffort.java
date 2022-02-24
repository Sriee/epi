package graph;

import util.PrintHypens;

import java.util.*;

public class _1631_PathMinEffort {
    int[][] directions = {
            {0, -1}, // Left
            {-1, 0}, // Up
            {0, 1}, // Right
            {1, 0}, // Down
    };

    public int minimumEffortPath(int[][] heights) {
        int rowLen = heights.length, colLen = heights[0].length;
        int[][] dist = new int[rowLen][colLen];
        for (int[] eachRow : dist)
            Arrays.fill(eachRow, Integer.MAX_VALUE);
        boolean[][] visited = new boolean[rowLen][colLen];
        dist[0][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.offer(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int row = curr[0], col = curr[1];

            if (row == rowLen - 1 && col == colLen - 1)
                return curr[2];

            if (visited[row][col])
                continue;

            visited[row][col] = true;
            for (int[] dir : directions) {
                int nrow = dir[0] + row;
                int ncol = dir[1] + col;

                if (nrow < 0 || nrow >= rowLen || ncol < 0 || ncol >= colLen || visited[nrow][ncol]) {
                    continue;
                }

                int maxDiff = Math.max(dist[row][col], Math.abs(heights[row][col] - heights[nrow][ncol]));
                if (dist[nrow][ncol] > maxDiff) {
                    dist[nrow][ncol] = maxDiff;
                    pq.offer(new int[]{nrow, ncol, maxDiff});
                }
            }
        }

        return dist[rowLen - 1][colLen - 1];
    }

    public static void main(String[] args) {
        _1631_PathMinEffort pmf = new _1631_PathMinEffort();
        int[][][] heights = {
                {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}},
                {{1, 2, 2}, {3, 8, 4}, {5, 3, 5}},
                {{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}},
                {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}}
        };

        for (int i = 0; i < heights.length; i++) {
            System.out.printf("\n%d.\theights = %s\n", (i + 1), Arrays.deepToString(heights[i]));
            System.out.println("\tMinimum effort required to travel from the top-left cell to the bottom-right cell = " + pmf.minimumEffortPath(heights[i]));
            System.out.println(PrintHypens.generate());
        }
    }
}
