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
    public int kthSmallest(int[][] matrix, int k) {
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

    public static void main(String[] args) {
        _378_KthSmallestSortedMatrix ksm = new _378_KthSmallestSortedMatrix();

        // sorted arr = [1, 1, 5, 8, 10, 11, 12, 12, 13]
        //        idx = [1, 2, 3, 4,  5,  6,  7,  8,  9]
        int[][] matrix = {
                {1, 1, 5},
                {8, 10, 11},
                {12, 12, 13}
        };

        System.out.println(ksm.kthSmallest(matrix, 4));
        System.out.println(ksm.kthSmallest(matrix, 9));
    }
}
