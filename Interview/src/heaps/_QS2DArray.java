package heaps;

public class _QS2DArray {

    /**
     * Quick Select technique to find the kth largest value in a 2D array.
     */
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0)
                    matrix[i][j] ^= matrix[i - 1][j];

                if (j > 0)
                    matrix[i][j] ^= matrix[i][j - 1];

                if (i > 0 && j > 0)
                    matrix[i][j] ^= matrix[i - 1][j - 1];
            }
        }

        return quickSelect(matrix, n, 0, m * n - 1, m * n - k);
    }

    private int quickSelect(int[][] matrix, int col, int left, int right, int nk) {
        while (left < right) {
            int partitionIdx = partition(matrix, col, left, right);

            if (partitionIdx < nk)
                left = partitionIdx + 1;
            else
                right = partitionIdx;
        }

        return matrix[left / col][left % col];
    }

    private int partition(int[][] matrix, int col, int left, int right) {
        int pivotIdx = (left + right >> 1), i = left - 1, j = right + 1;
        int pivot = matrix[pivotIdx / col][pivotIdx % col];

        while (true) {
            do {
                i++;
            } while (matrix[i / col][i % col] < pivot);

            do {
                j--;
            } while (matrix[j / col][j % col] > pivot);

            if (i >= j)
                return j;

            swap(matrix, col, i, j);
        }
    }

    private void swap(int[][] matrix, int col, int first, int second) {
        int temp = matrix[first / col][first % col];
        matrix[first / col][first % col] = matrix[second / col][second % col];
        matrix[second / col][second % col] = temp;
    }

    public static void main(String[] args) {
        _QS2DArray q2 = new _QS2DArray();
        int[][] matrix = new int[][]{
                {5, 2},
                {1, 6}
        };
        System.out.println(q2.kthLargestValue(matrix, 2));
    }
}
