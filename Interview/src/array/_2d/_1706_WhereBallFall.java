package array._2d;

import java.util.Arrays;

public class _1706_WhereBallFall {

    public int[] findBall(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] res = new int[n];
        Arrays.fill(res, -1);

        for (int col = 0; col < n; col++) {
            int currentCol = col;

            for (int row = 0; row < m; row++) {
                int nextCol = currentCol + grid[row][currentCol];

                if (nextCol < 0 || nextCol >= n || grid[row][currentCol] != grid[row][nextCol])
                    break;

                if (row == m - 1)
                    res[col] = nextCol;

                currentCol = nextCol;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        _1706_WhereBallFall wbf = new _1706_WhereBallFall();
        int[] res;

        // 1
        int[][] grid = {
                {-1, -1},
                {1, 1}
        };

        res = wbf.findBall(grid);
        System.out.println(Arrays.toString(res));


    }
}
