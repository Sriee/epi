package heaps.two_heaps;

import java.util.*;

/**
 * Two Heap Pattern - Using TreeSet aka Balanced Binary Search Tree
 *
 * Since remove in priority queue was degrading the runtime. TreeSet is used, which has O(log n) time complexity for
 * adding and removing elements. The solution works, but it's not the fastest solution.
 *
 * TC:
 *  For n insertions = add = O(n log k)
 *    n - k deletion = remove = O((n - k) log k) = O(n log k - k log k) = O(n log k)
 *         getMedian = O(1)
 *
 *    Overall time complexity = O(n log k) + O(n log k) = 2 O(n log k) = O(n log k)
 *
 * SC: O(k) - Number of elements in both the heaps will be equal to the sliding window
 */
public class _480_SlidingWindowMedian {

    TreeSet<Integer> left, right;
    boolean isEven = false;

    public double[] medianSlidingWindow(int[] nums, int k) {
        isEven = k % 2 == 0;

        /*
         * Drawback of using tree set is that duplicates will be ignored. We solved this drawback customizing the
         * comparator to use index, but this pattern cannot be used for other problems.
         */
        Comparator<Integer> comparator = (a, b) -> nums[a] == nums[b] ? a - b : Integer.compare(nums[a], nums[b]);
        left = new TreeSet<>(comparator.reversed());
        right = new TreeSet<>(comparator);
        double[] result = new double[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            if (i >= k)
                remove(i - k);
            add(i);

            if (i >= k - 1)
                result[i - k + 1] = getMedian(nums);
        }

        return result;
    }

    private void add(int idx) {
        left.add(idx);
        right.add(left.pollFirst());

        if (left.size() < right.size())
            left.add(right.pollFirst());
    }

    private void remove(int idx) {
        if (left.remove(idx)) {
            if (left.size() < right.size())
                left.add(right.pollFirst());
        } else {
            right.remove(idx);

            if (left.size() > right.size() + 1)
                right.add(left.pollFirst());
        }
    }

    private double getMedian(int[] nums) {
        return isEven ? nums[left.first()] / 2.0 + nums[right.first()] / 2.0 : nums[left.first()];
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