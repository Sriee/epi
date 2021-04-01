package leet;

public class MatrixAddition {

    public int maxCount(int m, int n, int[][] ops) {
        if (ops.length == 0)
            return m * n;
        int[][] M = new int[m][n];
        int currentMax = Integer.MIN_VALUE, num = 0;
        for (int it = 0; it < ops.length; it++) {
            for (int i = 0; i < ops[it][0]; i++) {
                for (int j = 0; j < ops[it][1]; j++) {
                    M[i][j] += 1;
                    if (M[i][j] > currentMax) {
                        currentMax = M[i][j];
                        num = 1;
                    } else if (M[i][j] == currentMax) {
                        num += 1;
                    }
                }
            }
        }
        this.matrix(M);
        return num;
    }

    public int maxCountOptimized(int m, int n, int[][] ops) {
        for (int[] op : ops) {
            m = Math.min(m, op[0]);
            n = Math.max(n, op[1]);
        }
        return m * n;
    }

    private void matrix(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MatrixAddition s = new MatrixAddition();
        System.out.println("Max Count " + s.maxCount(3, 3, new int[][] { { 2, 2 }, { 3, 3 } }));
        System.out.println("Max Count " + s.maxCountOptimized(4, 4, new int[][] { { 1, 1 }, { 2, 3 }, { 1, 4 } }));
        System.out.println("Max Count " + s.maxCountOptimized(3999, 3999, new int[][] { { 1999, 1999 } }));
    }

}
