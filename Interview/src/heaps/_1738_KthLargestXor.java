package heaps;

import java.util.*;

class _1738_KthLargestXor {
    Random rand;

    /**
     * Approach 1: Priority Queue
     *
     * Run Time: 589 ms.
     *
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

    /**
     * Approach 2: Priority Queue + Summed Area Table technique.
     * <p>
     * This technique uses an additional dp array which we populate as we traverse the input array. The way we fill
     * the dp array is based on a technique called "Summed Area Table".
     *
     * <p>
     * Run Time: 228 ms.
     * <p>
     * TC: O(mn log k)
     * SC: O(mn) + O(k)
     */
    public int kthLargestXorPQ2(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i + 1][j + 1] = dp[i + 1][j] ^ dp[i][j + 1] ^ dp[i][j] ^ matrix[i][j];

                pq.offer(dp[i + 1][j + 1]);
                if (pq.size() > k)
                    pq.poll();
            }
        }


        return pq.poll();
    }

    /**
     * Approach 3: Sorted Array.
     * <p>
     * This approach TC is more than Priority Queue Approach but OJ's reports a lesser run time.
     * <p>
     * Run Time: 64 ms.
     * <p>
     * TC: O(3mn) + O(mn log mn)
     * SC: O(mn) + O(log mn)
     */
    public int kthLargestXorArr(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;

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

        int[] arr = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i * n + j] = matrix[i][j];
            }
        }

        Arrays.sort(arr);
        return arr[arr.length - k];
    }

    /**
     * Approach 4: Sorted Array with Summed Area Table technique.
     * <p>
     * This run time better than the PQ approach.
     * <p>
     * Run time: 46 ms
     * <p>
     * TC: O(mn) + O(mn log mn)
     * SC: O(mn) + O(log mn)
     */
    public int kthLargestXorArr2(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int[] arr = new int[m * n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i + 1][j + 1] = dp[i + 1][j] ^ dp[i][j + 1] ^ dp[i][j] ^ matrix[i][j];
                arr[i * n + j] = dp[i + 1][j + 1];
            }
        }

        Arrays.sort(arr);

        return arr[arr.length - k];
    }

    /**
     * Approach 5: Sorted Array with Summed Area Table technique (without dp array).
     * <p>
     * This approach run time has increased because of the three if checks.
     * <p>
     * Run time: 55 ms
     * <p>
     * TC: O(mn) + O(mn log mn)
     * SC: O(mn) + O(log mn)
     */
    public int kthLargestXorArr3(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length, idx = 0;
        int[] arr = new int[m * n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0)
                    matrix[i][j] ^= matrix[i - 1][j];

                if (j > 0)
                    matrix[i][j] ^= matrix[i][j - 1];

                if (i > 0 && j > 0)
                    matrix[i][j] ^= matrix[i - 1][j - 1];

                arr[idx++] = matrix[i][j];
            }
        }

        Arrays.sort(arr);
        return arr[arr.length - k];
    }

    public int kthLargestValue(int[][] matrix, int k) {
        int ans = -1;
        switch (rand.nextInt(3) + 1) {
            case 1:
                ans = this.kthLargestXorPQ(matrix, k);
                break;
            case 2:
                ans = this.kthLargestXorPQ2(matrix, k);
                break;
            case 3:
                ans = this.kthLargestXorArr(matrix, k);
                break;
            case 4:
                ans = this.kthLargestXorArr2(matrix, k);
                break;
        }
        return ans;
    }


    private void print(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
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
        System.out.println(klx.kthLargestValue(matrix, 4));

        // 2
        matrix = new int[][]{
                {10, 9, 5},
                {2, 0, 4},
                {1, 0, 9},
                {3, 4, 8}
        };
        System.out.println(klx.kthLargestValue(matrix, 10));
    }
}