package array._2d;

public class _48_RotateImage {

    /**
     * Formula to rotate a matrix 90 degree = Reverse the rows in the arr + Transpose the matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // Reverse
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int[] temp = matrix[i];
            matrix[i] = matrix[j];
            matrix[j] = temp;
        }

        // Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    private void print(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        _48_RotateImage ri = new _48_RotateImage();
        int[][] mat;

        // 1
        mat = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        ri.rotate(mat);
        ri.print(mat);
        System.out.println("====================================\n");

        // 2
        mat = new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        ri.rotate(mat);
        ri.print(mat);
    }
}
