package array._2d;

public class _566_ReshapeMatrix {

    private static int[][] reshape(int[][] mat, int r, int c) {
        int row = mat.length, column = mat[0].length;
        if ((r * c) != (row * column))
            return mat;

        int[][] reshaped = new int[r][c];
        int m = 0, n = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                reshaped[i][j] = mat[m][n];
                n++;
                if (n == column) {
                    m++;
                    n = 0;
                }
            }
        }
        return reshaped;
    }

    private static void print(int[][] mat) {
        int row = mat.length, column = mat[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int mat[][] = new int[2][3];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                mat[i][j] = (i + j) * 2;
            }
        }
        print(mat);
        System.out.println();
        print(reshape(mat, 6, 1));
    }

}
