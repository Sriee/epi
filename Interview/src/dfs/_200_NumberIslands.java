package dfs;

import java.util.*;

public class _200_NumberIslands {
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    /**
     * Recursive Approach. This problem follows the same pattern as connected
     * components in a graph problem.
     * <p>
     * Optimal solution.
     */
    private int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0')
                    continue;

                dfs(grid, i, j);
                count++;
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int row, int col) {
        grid[row][col] = '0';

        for (int[] dir : directions) {
            int x = row + dir[0];
            int y = col + dir[1];

            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0')
                continue;

            dfs(grid, x, y);
        }
    }

    /**
     * BFS Approach. This problem uses the BFS template to traverse the grids
     */
    public int numIslands2(char[][] grid) {
        int islands = 0;
        Queue<int[]> queue = new LinkedList<>();
        int[][] directions = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    islands++;
                    queue.offer(new int[]{i, j});

                    // Start BFS routine
                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();

                        for (int[] d : directions) {
                            int x = curr[0] + d[0];
                            int y = curr[1] + d[1];

                            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0')
                                continue;

                            grid[x][y] = '0';
                            queue.offer(new int[]{x, y});
                        }
                    }
                }
            }
        }

        return islands;
    }

    public static void main(String[] args) {
        _200_NumberIslands ns = new _200_NumberIslands();

        char[][][] grids = {
                {{'1', '1', '0', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '1', '0'}},
                {{'1', '1', '0'}, {'0', '0', '1'}, {'0', '1', '0'}},
                {{'1', '1', '1'}, {'1', '1', '1'}, {'1', '1', '1'}},
                {{'0', '0', '0'}, {'0', '0', '0'}, {'0', '0', '0'}}
        };

        System.out.print("DFS Approach: ");
        for (char[][] grid : grids) {
            System.out.print(ns.numIslands(grid) + " ");
        }

        grids = new char[][][]{
                {{'1', '1', '0', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '1', '0'}},
                {{'1', '1', '0'}, {'0', '0', '1'}, {'0', '1', '0'}},
                {{'1', '1', '1'}, {'1', '1', '1'}, {'1', '1', '1'}},
                {{'0', '0', '0'}, {'0', '0', '0'}, {'0', '0', '0'}}
        };
        System.out.print("\nBFS Approach: ");
        for (char[][] grid : grids) {
            System.out.print(ns.numIslands2(grid) + " ");
        }
    }
}
