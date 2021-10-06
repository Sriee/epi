package array._2d;

import java.util.Arrays;

public class _59_SpiralMatrixII {

    /*
     *  ------------->
     *  ^            |
     *  |            |
     *  |            v
     *  <-------------
     *
     * When approaching problems related to spiral matrix the following construct
     * helps. Think of having four boundaries left, right, top, and bottom. While
     * traversing through the array our pointers will run from
     *      1. left   --> right
     *      2. top    --> bottom
     *      3. right  --> left
     *      4. bottom --> top
     *
     */
    public int[][] generateMatrix(int n) {
        int[][] mat = new int[n][n];
        int left = 0, right = n - 1, top = 0, bottom = n - 1, count = 1, size = n * n;
        while (count <= size) {
            for (int i = top, j = left; j <= right && count <= size; j++)
                mat[i][j] = count++;

            for (int i = top + 1, j = right; i < bottom && count <= size; i++)
                mat[i][j] = count++;

            for (int i = bottom, j = right; j >= left && count <= size; j--)
                mat[i][j] = count++;

            for (int i = bottom - 1, j = left; i > top && count <= size; i--)
                mat[i][j] = count++;

            left++; right--; top++; bottom--;
        }

        return mat;
    }

    private void print(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
        System.out.println();
    }
    public static void main(String[] args) {
        _59_SpiralMatrixII sm = new _59_SpiralMatrixII();

        for (int n : new int[] {1, 3, 10}) {
            sm.print(sm.generateMatrix(n));
        }
    }
}
