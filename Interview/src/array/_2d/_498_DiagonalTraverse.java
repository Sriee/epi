package array._2d;

import java.util.*;

class _498_DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int size = m * n, idx = 0, row = 0, col = 0;
        int[] res = new int[size];

        while (idx < size) {

            while (row >= 0 && col < n)
                res[idx++] = mat[row--][col++];
            row++;
            if (col >= n) {
                col = n - 1;
                row++;
            }

            while (row < m && col >= 0)
                res[idx++] = mat[row++][col--];
            col++;
            if (row >= m) {
                row = m - 1;
                col++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        _498_DiagonalTraverse dt = new _498_DiagonalTraverse();
        int[][][] input = {
                {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                {{1, 2}, {3, 4}, {5, 6}, {7, 8}},
                {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}},
                {{1}, {2}, {3}},
                {{1, 2, 3, 4, 5, 6}},
                {{5}}
        };

        for (int[][] mat : input) {
            System.out.println(Arrays.toString(dt.findDiagonalOrder(mat)));
        }
    }
}