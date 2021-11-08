package array._2d;

import array.ArrayUtils;

import java.util.Arrays;

public class _73_SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean fcol = false;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0)
                    continue;

                if (j != 0) {
                    matrix[0][j] = 0;
                } else {
                    fcol = true;
                }

                matrix[i][0] = 0;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }

        if (matrix[0][0] == 0)
            Arrays.fill(matrix[0], 0, n, 0);

        if (fcol) {
            for (int i = 0; i < m; i++)
                matrix[i][0] = 0;
        }
    }

    public static void main(String[] args) {
        _73_SetMatrixZeroes smz = new _73_SetMatrixZeroes();

        // 1
        int[][] input1 = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        smz.setZeroes(input1);
        ArrayUtils.print2D(input1);
        System.out.println();

        // 2
        int[][] input2 = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
        smz.setZeroes(input2);
        ArrayUtils.print2D(input2);
        System.out.println();

        // 3
        int[][] input3 = {
                {1, 2, 3, 4},
                {5, 0, 7, 8},
                {0, 10, 11, 12},
                {13, 14, 15, 0}
        };
        smz.setZeroes(input3);
        ArrayUtils.print2D(input3);
        System.out.println();
    }
}
