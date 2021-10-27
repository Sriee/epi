package design;

public class _304_RangeSum2D {

    int[][] mat, arr;
    public _304_RangeSum2D(int[][] matrix) {
        mat = matrix;

        /**
         * Time taken to pre-compute the cumulative sum - TC: O(mn)
         * SC: O(mn)
         */
        int m = matrix.length, n = matrix[0].length;
        // Trick to create a new matrix with one size bigger to
        // make our solution easy and avoid (index < 0) handling
        arr = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i + 1][j + 1] = arr[i][j + 1] + arr[i + 1][j] + matrix[i][j] - arr[i][j];
            }
        }
    }

    /**
     * Cumulative sum on a 2D Matrix
     *
     * TC: O(1)
     * SC: O(1)
     */
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return arr[row2 + 1][col2 + 1] + arr[row1][col1] - arr[row2 + 1][col1] - arr[row1][col2 + 1];
    }

    /**
     * Brute force approach to sum a region.
     *
     * TC: O(mn)
     * SC: O(1)
     */
    public int sumRegionBruteForce(int row1, int col1, int row2, int col2) {
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

        System.out.println(rs2d.sumRegionBruteForce(2, 1, 4, 3)); // 8
        System.out.println(rs2d.sumRegion(1, 2, 2, 4)); // 12
    }
}
