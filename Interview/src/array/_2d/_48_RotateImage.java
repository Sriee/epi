package array._2d;

public class _48_RotateImage {

    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // Reverse
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int[] temp = matrix[i];
            matrix[i] = matrix[j];
            matrix[j] = temp;
        }
        // print(matrix);

        // Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        _48_RotateImage ri = new _48_RotateImage();
        int[][] mat = new int[][]{{1, 2, 3}, {4, 5, 6}, {7,8,9}};
        ri.rotate(mat);
    }
}
