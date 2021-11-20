package heaps.two_heaps;

import java.util.*;

/**
 * Two Heap Pattern - Using 2 heaps with Lazy removal approach
 *
 * Since remove in priority queue was degrading the runtime. TreeSet is used, which has O(log n) time complexity for
 * adding and removing elements. The solution works, but it's not the fastest solution.
 *
 * TC:
 * ==
 *      i. add
 *          For n insertions = O(n log k)
 *
 *     ii. balance
 *          For n insertions = O((n - k) log k)
 *
 *    iii. getMedian = O(1)
 *
 *     iv. lazy remove = O(1)
 *          get min/max call of a heap = O(1)
 *          Hash map put = O(1)
 *
 *      v. remove = O(1)
 *          Hash map get/put/delete = O(1)
 *
 *     vi. tryRemove
 *          poll/peek call of a heap = O(1)
 *
 *    Overall time complexity = O(n log k) + O((n - k)log k) = 2 O(n log k) = O(n log k)
 *
 * SC: O(n - k) + O(k) = O(n)
 *    O(k) = Number of elements in both the heaps will be equal to the sliding window
 *    O(n - k) = Number of elements in the hash table
 */
public class _480_SlidingWindowMedian {

    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    int maxHeapSize = 0, minHeapSize = 0;
    Map<Integer, Integer> delete = new HashMap<>();

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            if (i >= k)
                lazyRemove(nums[i - k]);
            add(nums[i]);

            if (i >= k - 1)
                result[i - k + 1] = getMedian();
        }

        return result;
    }

    private void add(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.add(num);
            maxHeapSize++;
        } else {
            minHeap.add(num);
            minHeapSize++;
        }

        balance();
    }

    private void balance() {
        tryRemove();

        if (maxHeapSize > minHeapSize + 1) {
            minHeap.add(maxHeap.remove());
            maxHeapSize--; minHeapSize++;
        } else if (maxHeapSize < minHeapSize) {
            maxHeap.add(minHeap.remove());
            maxHeapSize++; minHeapSize--;
        }
    }

    private double getMedian() {
        tryRemove();

        return minHeapSize == maxHeapSize ? maxHeap.peek() / 2.0 + minHeap.peek() / 2.0: maxHeap.peek();
    }

    private void lazyRemove(int num) {
        if (!maxHeap.isEmpty() && num <= maxHeap.peek())
            maxHeapSize--;
        else if (!minHeap.isEmpty() && num >= minHeap.peek())
            minHeapSize--;

        delete.put(num, delete.getOrDefault(num, 0) + 1);
    }

    private void remove(int num) {
        int count = delete.get(num) - 1;

        if (count == 0)
            delete.remove(num);
        else
            delete.put(num, count);
    }

    private void tryRemove() {
        while(!maxHeap.isEmpty() && delete.containsKey(maxHeap.peek()))
            remove(maxHeap.poll());

        while(!minHeap.isEmpty() && delete.containsKey(minHeap.peek()))
            remove(minHeap.poll());
    }

    public static void main(String[] args) {
        _480_SlidingWindowMedian swm = new _480_SlidingWindowMedian();
        double[] medians;
        medians = swm.medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println(Arrays.toString(medians));

        medians = swm.medianSlidingWindow(new int[]{
                -2147483648, -2147483648, 2147483647, -2147483648, 1, 3,
                -2147483648, -100, 8, 17, 22, -2147483648, -2147483648, 2147483647, 2147483647, 2147483647, 2147483647,
                -2147483648, 2147483647, -2147483648}, 6);
        System.out.println(Arrays.toString(medians));
    }
}