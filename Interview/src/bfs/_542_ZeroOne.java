package bfs;

import java.util.*;

public class _542_ZeroOne {

    public int[][] updateMatrix(int[][] mat) {
        int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();

        /*
         * Note the other method here. Adding the co-ordinate of interest to the queue when we pre process the matrix
         */
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1)
                    mat[i][j] = Integer.MAX_VALUE;
                else
                    queue.offer(new int[]{i, j});
            }
        }

        /* For some reason, iterating the matrix and doing the bfs routine is giving a TLE.
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] != 0)
                    continue;
         */
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int[] d : directions) {
                int x = curr[0] + d[0];
                int y = curr[1] + d[1];

                if (x < 0 || x >= mat.length || y < 0 || y >= mat[0].length || mat[x][y] <= mat[curr[0]][curr[1]] + 1)
                    continue;

                mat[x][y] = mat[curr[0]][curr[1]] + 1;
                queue.offer(new int[]{x, y});
            }
        }
        return mat;
    }

    private void print(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("==============");
    }

    public static void main(String[] args) {
        _542_ZeroOne zo = new _542_ZeroOne();

        int[][][] matrix = {
                {{0, 1, 1, 0}, {1, 1, 1, 1}, {1, 1, 0, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}},
                {{0, 0, 0}, {0, 0, 0}, {0, 0, 1}},
                {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 0, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}}
        };

        for (int[][] mat : matrix) {
            zo.updateMatrix(mat);
            zo.print(mat);
        }
    }
}
