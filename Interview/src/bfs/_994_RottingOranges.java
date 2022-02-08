package bfs;

import util.PrintHypens;

import java.util.*;

public class _994_RottingOranges {

    int[][] directions = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    /**
     * TC: O(mn) + O(mn) = O(mn)
     * SC: O(mn)
     */
    public int orangesRotting(int[][] grid) {
        int mins = 0, freshOranges = 0, m = grid.length, n = grid[0].length;
        Deque<int[]> queue = new ArrayDeque<>();

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1) {
                    freshOranges++;
                } else if (grid[row][col] == 2) {
                    queue.offer(new int[]{row, col});
                }
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();

                for (int[] dir : directions) {
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                        continue;

                    if (grid[nr][nc] == 1) {
                        freshOranges--;
                        grid[nr][nc] = 2;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
            if (!queue.isEmpty()) mins++;
        }

        return freshOranges == 0 ? mins : -1;
    }

    public static void main(String[] args) {
        _994_RottingOranges ro = new _994_RottingOranges();

        int[][][] grids = {
                {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}},
                {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}},
                {{0, 2}},
                {{2, 1, 1}, {1, 1, 1}, {0, 1, 2}},
                {{0}}
        };

        for (int i = 0; i < grids.length; i++) {
            System.out.printf("\n%d.\tgrid= %s\n", (i + 1), Arrays.deepToString(grids[i]));
            System.out.println("\tMinute to rot: " + ro.orangesRotting(grids[i]));
            System.out.println(PrintHypens.generate());
        }
    }
}
