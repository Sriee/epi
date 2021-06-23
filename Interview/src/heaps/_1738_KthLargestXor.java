package heaps;

import java.util.*;

class _1738_KthLargestXor {
    Random rand;

    /**
     * Approach 1: Priority Queue
     * <p>
     * Run Time: 589 ms.
     * <p>
     * TC: O(2mn) + O(mn log k) - O(mn log k)
     * SC: O(k)
     */
    public int kthLargestXorPQ(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] ^= matrix[i][j - 1];
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] ^= matrix[i - 1][j];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pq.offer(matrix[i][j]);
                if (pq.size() > k)
                    pq.poll();
            }
        }

        return pq.poll();
    }

    public static void main(String[] args) {
        _1738_KthLargestXor klx = new _1738_KthLargestXor();
        klx.rand = new Random();

        int[][] matrix;

        // 1
        matrix = new int[][]{
                {5, 2},
                {1, 6}
        };
        System.out.println(klx.kthLargestXorPQ(matrix, 4));

        // 2
        matrix = new int[][]{
                {10, 9, 5},
                {2, 0, 4},
                {1, 0, 9},
                {3, 4, 8}
        };
        System.out.println(klx.kthLargestXorPQ(matrix, 10));
    }
}