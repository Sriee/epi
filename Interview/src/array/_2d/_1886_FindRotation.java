package array._2d;

import java.util.BitSet;

public class _1886_FindRotation {

    public boolean findRotationHack(int[][] m, int[][] t) {
        //4 bits for each rotation, i bit false -> i rotation equals to m
        BitSet r = new BitSet(4);
        for (int i = 0, n = m.length - 1; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                if (m[i][j] != t[i][j]) r.set(0, true);
                if (m[i][j] != t[j][n - i]) r.set(1, true);
                if (m[i][j] != t[n - i][n - j]) r.set(2, true);
                if (m[i][j] != t[n - j][i]) r.set(3, true);
            }
        }

        /**
         * If any element is out of order then the bit set will be 4, for a valid rotation we will only be setting 3 out
         * of 4.
         */
        return r.cardinality() != 4;
    }

    int n;
    public boolean findRotation(int[][] mat, int[][] target) {
        n = mat.length;

        for (int i = 0; i < 4; i++) {
            if (check(rotateMatrix(mat), target))
                return true;
        }

        return false;
    }

    // Rotate matrix
    private int[][] rotateMatrix(int[][] mat) {
        mat = reverseMatrix(mat);
        return transposeMatrix(mat);
    }

    // Reverse the rows in a matrix
    private int[][] reverseMatrix(int[][] mat) {
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int[] temp = mat[i];
            mat[i] = mat[j];
            mat[j] = temp;
        }

        return mat;
    }

    // Transpose a matrix
    private int[][] transposeMatrix(int[][] mat) {
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }

        return mat;
    }

    // Compare two matrices
    private boolean check(int[][] mat, int[][] target) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[i][j])
                    return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        _1886_FindRotation fr = new _1886_FindRotation();
        int[][] matrix, target;

        // 1
        matrix = new int[][] {{0, 1}, {1, 0}};
        target = new int[][] {{1, 0}, {0, 1}};
        System.out.println(fr.findRotation(matrix, target));

        // 2
        matrix = new int[][] {{0, 1}, {1, 1}};
        target = new int[][] {{1, 0}, {0, 1}};
        System.out.println(fr.findRotation(matrix, target));

        // 3
        matrix = new int[][] {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        target = new int[][] {{1, 1, 1}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(fr.findRotation(matrix, target));
    }
}
