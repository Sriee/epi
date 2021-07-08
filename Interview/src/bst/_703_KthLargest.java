package bst;

import java.util.*;

class _703_KthLargest {

    /*
     * Classic Kth largest problem solved with Heap
     */
    private PriorityQueue<Integer> pq;
    private final int k;

    public _703_KthLargest(int k, int[] nums) {
        this.pq = new PriorityQueue<>();
        this.k = k;

        for (int i : nums)
            this.add(i);
    }

    public int add(int val) {
        this.pq.offer(val);

        if (this.pq.size() > this.k)
            pq.poll();

        return pq.peek();
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 8, 2};
        _703_KthLargest kl = new _703_KthLargest(3, nums);
        System.out.println(kl.add(3));
    }
}