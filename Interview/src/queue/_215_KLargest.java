package queue;

import java.util.PriorityQueue;

public class _215_KLargest {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i : nums) {
            pq.offer(i);

            if (pq.size() > k)
                pq.poll();
        }

        return pq.poll();
    }

    public static void main(String[] args) {
        _215_KLargest kl = new _215_KLargest();
        System.out.println(kl.findKthLargest(new int[]{1}, 1));
        System.out.println(kl.findKthLargest(new int[]{6, 71, 25, 38, 17, 23, 0, 19, 3, 46, 27, 3, 48, 1, 2}, 6));
    }
}
