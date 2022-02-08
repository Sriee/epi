package bfs;

import util.PrintHypens;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class _1091_ShortestMatrix {

    int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
    };

    public int shortestPathBinaryMatrix(int[][] grid) {
        int distance = 0, n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
            return -1;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();

                if (curr[0] == n - 1 && curr[1] == n - 1)
                    return distance;

                for (int[] dir : directions) {
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];

                    if (nr < 0 || nr >= n || nc < 0 || nc >= n || grid[nr][nc] == 1)
                        continue;

                    grid[nr][nc] = 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        _1091_ShortestMatrix sm = new _1091_ShortestMatrix();
        int[][][] inputs = {
                {{0, 1}, {1, 0}},
                {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}},
                {{1, 0, 0}, {1, 1, 0}, {1, 1, 0}},
                {{0, 1}, {1, 1}},
        };

        for (int i = 0; i < inputs.length; i++) {
            System.out.printf("\n%d.\tMatrix= %s\n", (i + 1), Arrays.deepToString(inputs[i]));
            System.out.println("\tShortest path in binary matrix: " + sm.shortestPathBinaryMatrix(inputs[i]));
            System.out.println(PrintHypens.generate());
        }
    }
}
