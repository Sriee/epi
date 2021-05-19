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

    public static void main(String[] args) {
        _668_KthLargestMulTable kmul = new _668_KthLargestMulTable();

        System.out.println(kmul.findKthNumberPq(3, 3, 5)); // 5
        System.out.println(kmul.findKthNumberPq(30000, 30000, 99)); // 28
        System.out.println(kmul.findKthNumberPq(30000, 30000, 899999)); // 91056
    }
}
