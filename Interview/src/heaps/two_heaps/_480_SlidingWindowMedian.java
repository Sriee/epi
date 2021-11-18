package heaps.two_heaps;

import java.util.*;

/**
 * Two Heap Pattern - Using priority queue's
 *
 * The solution works but gives a TLE because the removal operation of priority queue takes O(k).
 *
 * TC:
 *  For n insertions = add = O(n log k)
 *    n - k deletion = remove = O((n - k) k) = O(nk - k ^ 2) = O(nk)
 *
 *    Overall time complexity = O(n log k) + O(nk) = O(nk)
 *
 *  add
 *  ===
 *    Insertion into either of the queues - O(log k)
 *    Worst case -> we re-balance for each insertion - add + removeHead = O(log k) + O(1)
 *
 *  remove <-- Problem
 *  ======
 *    Remember removing an element in priority queue takes linear time
 *    O(k)
 *
 *  getMedian - O(1)
 *
 * SC: O(k) - Number of elements in both the heaps will be equal to the sliding window
 */
public class _480_SlidingWindowMedian {

    PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> rightMinHeap = new PriorityQueue<>();

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length, i = 0;
        double[] medians = new double[n - k + 1];

        if (k == 1) {
            return Arrays.stream(nums).asDoubleStream().toArray();
        }

        for (i = 0; i < k; i++)
            add(nums[i]);

        for (; i < n; i++) {
            medians[i - k] = getMedian();
            add(nums[i]);
            remove(nums[i - k]);
        }

        medians[i - k] = getMedian();
        return medians;
    }

    private void add(int num) {
        if (leftMaxHeap.isEmpty() || leftMaxHeap.peek() >= num)
            leftMaxHeap.add(num);
        else
            rightMinHeap.add(num);

        rebalance();
    }

    private double getMedian() {
        return leftMaxHeap.size() == rightMinHeap.size() ? (leftMaxHeap.peek() / 2.0 + rightMinHeap.peek() / 2.0) : leftMaxHeap.peek() / 1.0;
    }

    private void rebalance() {
        if (leftMaxHeap.size() == rightMinHeap.size())
            return;

        if (leftMaxHeap.size() > rightMinHeap.size() + 1)
            rightMinHeap.add(leftMaxHeap.remove());
        else if (leftMaxHeap.size() < rightMinHeap.size())
            leftMaxHeap.add(rightMinHeap.remove());
    }

    private void remove(int num) {
        if (num > leftMaxHeap.peek())
            rightMinHeap.remove(num);
        else
            leftMaxHeap.remove(num);

        rebalance();
    }

    public static void main(String[] args) {
        _480_SlidingWindowMedian swm = new _480_SlidingWindowMedian();
        double medians[];
        medians = swm.medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println(Arrays.toString(medians));

        medians = swm.medianSlidingWindow(new int[]{
                -2147483648, -2147483648, 2147483647, -2147483648, 1, 3,
                -2147483648, -100, 8, 17, 22, -2147483648, -2147483648, 2147483647, 2147483647, 2147483647, 2147483647,
                -2147483648, 2147483647, -2147483648}, 6);
        System.out.println(Arrays.toString(medians));
    }
}