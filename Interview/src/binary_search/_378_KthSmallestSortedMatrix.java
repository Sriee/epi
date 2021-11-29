package binary_search;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _378_KthSmallestSortedMatrix {

    /**
     * TC
     * ==
     * Inserting n first column = O(n log n)
     * Worst case we might need to add k more elements - O(k log n)
     *
     * Total TC = O((n + k) log n)
     *
     * SC - O(n) - Total number of elements in the heap
     */
    public int kthSmallestUsingHeap(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> matrix[a[0]][a[1]]));

        for (int i = 0; i < n; i++)
            minHeap.offer(new int[] {i, 0});

        while (!minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            if (--k == 0)
                return matrix[top[0]][top[1]];

            if (++top[1] == n)
                continue;

            minHeap.offer(top);
        }

        return -1;
    }

    /**
     * TC
     * ==
     * For normal binary search the TC is O(log n). We get log (n) because at each iteration the search space is reduced
     * to half. Here the search space is max - min value, but for each iteration the search space varies. Even if the
     * search space varies, we do bring it down in the next iteration. The complexity is O(log (max - min)).
     *
     * For each iteration we count the number of elements in the left half = O(n)
     * Total TC = O(n log(max - min))
     *
     * SC- O(1)
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int low = matrix[0][0], high = matrix[n - 1][n - 1], mid;

        while (low != high) {
            mid = low + (high - low) / 2;

            if (countLessThanMid(matrix, mid) < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    private int countLessThanMid(int[][] matrix, int target) {
        int n = matrix.length, count = 0;
        int row = 0, col = n - 1;

        while (row < n && col >= 0) {
            if (matrix[row][col] <= target) {
                count += col - 1;
                row++;
            } else {
                col--;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        _378_KthSmallestSortedMatrix ksm = new _378_KthSmallestSortedMatrix();

        // sorted arr = [1, 1, 5, 8, 10, 11, 12, 12, 13]
        //        idx = [1, 2, 3, 4,  5,  6,  7,  8,  9]
        int[][] matrix = {
                {1, 1, 5},
                {8, 10, 11},
                {12, 12, 13}
        };

        System.out.println(ksm.kthSmallestUsingHeap(matrix, 4));
        System.out.println(ksm.kthSmallest(matrix, 9));
    }
}
