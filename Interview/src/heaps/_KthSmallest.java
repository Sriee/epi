package heaps;

import java.util.*;

public class _KthSmallest {
    Random rand;

    /* ========================================================================
     * Approach 1: Sort
     * ========================================================================
     * Trivial approach: not used in Interviews
     *
     * TC: O(n log n)
     * SC: O(log n)
     */
    public int kthSmallestSort(int[] nums, int k) {
        int[] arr = new int[nums.length];
        System.arraycopy(nums, 0, arr, 0, nums.length);
        Arrays.sort(arr);
        return arr[k - 1];
    }

    /* ========================================================================
     * Approach 2: Quick Select Algorithm
     * ========================================================================
     *
     * This approach provides better run time algorithm.
     * TC: O(n) - Average case. O(n ^ 2) - Worst case
     * SC: O(1)
     */
    public int kthSmallest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        while (left < right) {
            int partitionIdx = hoarePartition(nums, left, right);

            if (partitionIdx < k - 1)
                left = partitionIdx + 1;
            else
                right = partitionIdx;
        }

        return nums[left];
    }

    /**
     * The following implementation covers the Lomuta partition scheme. It is straight forward to implement but has a
     * drawback. This partition scheme run time degrades to O(n ^ 2) for the following 2 cases.
     * 1. If the input array is already sorted in non-decreasing order
     * 2. If the input array contains all the elements with a same value
     */
    private int lomutoPartition(int[] nums, int left, int right) {
        /*
         * Normally the scheme uses nums[right] as the pivot.
         * int pivotIdx = right, j = left;
         *
         * But this will for sure degrades the run time to O(n ^ 2) for the above mentioned cases. To reduce the
         * probability of this drawback we are choosing a random pivot index, swapping that to be the right most
         * element and then do the partition.
         */
        int pivotIdx = left + rand.nextInt(right - left), j = left;
        swap(nums, pivotIdx, right);
        pivotIdx = right;

        for (int i = left; i <= right; i++) {
            if (nums[i] < nums[pivotIdx]) {
                swap(nums, i, j);
                j++;
            }
        }

        swap(nums, right, j);
        return j;
    }

    /**
     * The following implementation covers the Hoare's partition scheme. Hoare scheme is much more efficient than
     * Lomuto's because it does fewer swaps on average and it creates an efficient partition when all the values are
     * equal. But for sorted array, similar to Lomuto's scheme, the run time degrades to O(n^2).
     * <p>
     * Note: In this scheme the pivot's final location is not necessarily at the index that was returned. Notice the
     * change in driver function for this scheme. Also, we need to look at [left, pivot] and [pivot + 1, high] as
     * opposed to [left, pivot - 1], [pivot + 1, right] as in Lomuto's scheme.
     */
    private int hoarePartition(int[] nums, int left, int right) {
        int pivotIdx = left + rand.nextInt(right - left);
        swap(nums, left, pivotIdx);
        int pivot = nums[left], i = left - 1, j = right + 1;

        while (true) {
            do {
                i++;
            } while (nums[i] < pivot);

            do {
                j--;
            } while (nums[j] > pivot);

            if (i >= j) {
                return j;
            }

            swap(nums, i, j);
        }
    }

    private void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    /* ========================================================================
     * Approach 3: Priority Queue
     * ========================================================================
     *
     * This is the default approach when we want to find kth something.
     * TC: O(n log k)
     * SC: O(k)
     */
    public int kthSmallestPQ(int[] nums, int k) {
        // Max Heap
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        for (int i : nums) {
            pq.offer(i);

            if (pq.size() > k)
                pq.poll();
        }

        return pq.poll();
    }

    public static void main(String[] args) {
        _KthSmallest ks = new _KthSmallest();
        ks.rand = new Random();
        int[] nums = {7, 10, 4, 3, 20, 15};
        int k = 4;
        System.out.println(ks.kthSmallestPQ(nums, k));

        nums = new int[]{-1, 5, 6, 7, 4, 6, 8, 7, 3, 9, 1, 3, 8, 7, 3, 5, 4, 7, 3, 9, 4, 7, 9};
        k = 8;
        System.out.printf("Expected: %d, Actual: %d\n\n", ks.kthSmallestSort(nums, k), ks.kthSmallest(nums, k));

        nums = new int[]{-1, 3, 0};
        k = 1;
        System.out.printf("Expected: %d, Actual: %d\n", ks.kthSmallestSort(nums, k), ks.kthSmallest(nums, k));
    }
}
