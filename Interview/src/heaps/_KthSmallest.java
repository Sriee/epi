package heaps;

import java.util.*;

public class _KthSmallest {
    Random rand;

    public int kthSmallest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        while (left < right) {
            int partitionIdx = lomutoPartition(nums, left, right);

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

    private void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    public static void main(String[] args) {
        _KthSmallest ks = new _KthSmallest();
        ks.rand = new Random();
        int[] nums = {7, 10, 4, 3, 20, 15};
        System.out.println(ks.kthSmallest(nums, 4));
    }
}
