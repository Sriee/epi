package binary_search;

import java.util.PriorityQueue;

public class _668_KthLargestMulTable {

    /**
     * Approach 1: Priority Queue
     * <p>
     * TC: O(mn log mn)
     * SC: O(k)
     * <p>
     * OJ will give TLE
     */
    public int findKthNumberPq(int m, int n, int k) throws NullPointerException {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                pq.offer(i * j);

                if (pq.size() > k)
                    pq.poll();
            }
        }

        return pq.poll();
    }

    /**
     * Approach 2: Binary Search - Template 3
     * <p>
     * TC: O(n log w) where w = [1, m * n] and n is the number of rows
     * SC: O(1)
     * <p>
     */
    public int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // Do we have enough values that are >= mid?
            if (enough(m, n, k, mid))
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }

    private boolean enough(int m, int n, int k, int mid) {
        /*
         * The importance of binary search compared to the priority queue approach is that we don't have to search
         * m * n space to get the result. But we are doing the same thing here. Need a better search condition.
         *
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i * j <= mid) {
                    val++;

                    if (val == k)
                        return true;
                }
            }
        }
        */

        /*
         * 1 2 3 --> If we look at the matrix, we can simply find the total number of numbers less than mid by
         * 2 4 6     doing a simple row by row scan. We divide mid / (row num) => This gives us the number of elements
         * 3 6 9     that are <= mid for that row.
         */
        int count = 0, numLtMid;
        for (int i = 1; i <= m; i++) {
            numLtMid = Math.min(mid / i, n);
            if (numLtMid == 0)
                break;

            count += numLtMid;
            if (count >= k)
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        _668_KthLargestMulTable kmul = new _668_KthLargestMulTable();

        System.out.println(kmul.findKthNumberPq(3, 3, 5)); // 5
        System.out.println(kmul.findKthNumber(30000, 30000, 99)); // 28
        System.out.println(kmul.findKthNumber(30000, 30000, 899999)); // 91056
    }
}
