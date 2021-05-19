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
    public int findKthNumberPq(int m, int n, int k) {
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
     * Binary Search - Template 3
     * <p>
     * TC: O(mn log w) where w = [1, m * n]
     * SC: O(1)
     * <p>
     * OJ - TLE for large values of m, n, and k
     */
    public int findKthNumber(int m, int n, int k) {
        if (m == n)
            return -1;

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
        int val = 0;

        /*
         * The importance of binary search compared to the priority queue approach is that we don't have to search
         * m * n space to get the result. But we are doing the same thing here. Need a better search condition.
         */
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i * j <= mid) {
                    val++;

                    if (val == k)
                        return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        _668_KthLargestMulTable kmul = new _668_KthLargestMulTable();

        System.out.println(kmul.findKthNumberPq(3, 3, 5)); // 5
        System.out.println(kmul.findKthNumberPq(30000, 30000, 99)); // 28
        System.out.println(kmul.findKthNumberPq(30000, 30000, 899999)); // 91056
    }
}
