package design;

public class _304_RangeSum2D {

    int[][] mat;
    public _304_RangeSum2D(int[][] matrix) {
        mat = matrix;
    }

    /**
     * Brute force approach to sum a region.
     *
     * TC: O(mn)
     * SC: O(1)
     */
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;

        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                sum += mat[i][j];
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        int[][] input = {
            {3, 0, 1, 4, 2},
            {5, 6, 3, 2, 1},
            {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7},
            {1, 0, 3, 0, 5}
        };
        _304_RangeSum2D rs2d = new _304_RangeSum2D(input);

        System.out.println(rs2d.sumRegion(2, 1, 4, 3)); // 8
        System.out.println(rs2d.sumRegion(1, 2, 2, 4)); // 12
    }
}
