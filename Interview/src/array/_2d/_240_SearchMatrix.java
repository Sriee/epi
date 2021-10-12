package array._2d;

public class _240_SearchMatrix {

    /**
     * We can start either from bottom-left or top-right. Other two edges won't
     * work. For example, if we start from bottom-left,
     *   - if matrix[n - 1][0] < target:
     *      we know we have to go 'right' because the row is sorted left -> right.
     *   - else if matrix[n - 1][0] > target:
     *      we know we have to go 'up' because the column is sorted top -> bottom.
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length - 1, col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        _240_SearchMatrix sm = new _240_SearchMatrix();
        int[][] input = {
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };

        int[] targets = {5, 20, 13};
        for (int i : targets) {
            System.out.println(sm.searchMatrix(input, i));
        }
    }
}
