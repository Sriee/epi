package array._2d;

import java.util.*;

class _54_SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix[0].length == 0)
            return res;

        int m = matrix.length, n = matrix[0].length;
        int top = 0, right = n - 1, bottom = m - 1, left = 0, size = m * n, count = 0;

        while (res.size() < size) {
            for (int j = left; j <= right && count < size; j++, count++)
                res.add(matrix[top][j]);

            for (int i = top + 1; i <= bottom - 1 && count < size; i++, count++)
                res.add(matrix[i][right]);

            for (int j = right; j >= left && count < size; j--, count++)
                res.add(matrix[bottom][j]);

            for (int i = bottom - 1; i >= top + 1 && count < size; i--, count++)
                res.add(matrix[i][left]);

            top++;
            right--;
            bottom--;
            left++;
        }

        return res;
    }

    public static void main(String[] args) {
        _54_SpiralMatrix sm = new _54_SpiralMatrix();
        int[][][] input = {
                {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                {{1, 2}, {3, 4}, {5, 6}, {7, 8}},
                {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}},
                {{1}, {2}, {3}},
                {{1, 2, 3, 4, 5, 6}},
                {{5}}
        };

        for (int[][] mat : input) {
            System.out.println(sm.spiralOrder(mat));
        }
    }
}