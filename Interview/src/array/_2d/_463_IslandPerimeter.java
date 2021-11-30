package array._2d;

import java.util.*;

public class _463_IslandPerimeter {

    /**
     * BFS - Iterative Approach
     *
     * Worst case when all the cells are land cells.
     *
     * TC - O(mn)
     * SC - O(mn)
     */
    public int islandPerimeter(int[][] grid) {
        Deque<int[]> queue = new ArrayDeque<>();
        int[][] directions = {
            {0, -1}, // left
            {-1, 0}, // top
            {0, 1}, // right
            {1, 0} // bottom
        };
        boolean stop = false;
        int perimeter = 0, m = grid.length, n = grid[0].length;

        for (int row = 0; row < m && !stop; row++) {
            for (int col = 0; col < n && !stop; col++) {
                if (grid[row][col] == 1) {
                    queue.offer(new int[] {row, col});
                    stop = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] currentPosition = queue.poll();

            if (grid[currentPosition[0]][currentPosition[1]] == 2)
                continue;

            grid[currentPosition[0]][currentPosition[1]] = 2;

            for (int i = 0; i < 4; i++) {
                int nextRow = currentPosition[0] + directions[i][0];
                int nextCol = currentPosition[1] + directions[i][1];

                if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n || grid[nextRow][nextCol] == 0)
                    perimeter++;
                else if (grid[nextRow][nextCol] == 1)
                    queue.offer(new int[] {nextRow, nextCol});
            }
        }

        // print(grid);
        return perimeter;
    }

    private void print(int[][] mat) {
        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < mat[0].length; col++) {
                System.out.print(mat[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        _463_IslandPerimeter ip = new _463_IslandPerimeter();

        int[][] grid = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        };
        System.out.println(ip.islandPerimeter(grid));
    }
}
