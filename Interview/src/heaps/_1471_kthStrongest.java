package heaps;

import java.util.*;

class _1471_kthStrongest {
    Random rand = new Random();

    /**
     * Approach 1: Sort + Two pointer approach
     * <p>
     * TC: O(n log n) + O (k)
     * SC: O(log n) - Used by sorting algorithm
     */
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int m = arr[(arr.length - 1) / 2], left = 0, right = arr.length - 1, i = 0;
        int[] res = new int[k];

        while (i < k) {
            if (m - arr[left] > arr[right] - m)
                res[i++] = arr[left++];
            else
                res[i++] = arr[right--];
        }

        return res;
    }

    /**
     * Approach 2: Quick Select
     * <p>
     * TC:
     * O(n)  +          O(n)           +      O(n)     +       O(k)         = O(n) + O(k)
     * (Median) (Create arrWithStrength) (kth Strongest) (Construct res arr)
     * SC:                O(n)            +                  O(k)
     * <p>
     * Note: This solution is failing for a single test case with huge input array. In an interview setting it's
     * better to opt in for Approach 1 than quick select approach.
     */
    public int[] getStrongestQS(int[] arr, int k) {
        int n = arr.length;
        int medianIdx = quickSelect(arr, 0, n - 1, (n - 1) / 2, -1);

        quickSelect(arr, 0, n - 1, k, arr[medianIdx]);
        return Arrays.copyOfRange(arr, 0, k);
    }

    private int quickSelect(int[] nums, int left, int right, int k, int median) {
        int partitionIdx;
        while (left < right) {
            if (median == -1)
                partitionIdx = findMedianPartition(nums, left, right);
            else
                partitionIdx = findKStrongestPartition(nums, left, right, median);

            if (partitionIdx < k)
                left = partitionIdx + 1;
            else
                right = partitionIdx;
        }

        return left;
    }

    /* ==============================================================
     * Hoare's Partition scheme to Find Median
     * ==============================================================
     */
    private int findMedianPartition(int[] nums, int left, int right) {
        int pivotIdx = left + rand.nextInt(right - left + 1);
        swap(nums, left, pivotIdx);
        int pivot = nums[left], i = left - 1, j = right + 1;

        while (true) {
            do {
                i++;
            } while (nums[i] < pivot);

            do {
                j--;
            } while (nums[j] > pivot);

            if (i >= j)
                return j;

            swap(nums, i, j);
        }
    }

    /* ==============================================================
     * Hoare's Partition scheme to Find Kth Strongest Values
     * ==============================================================
     */
    private int findKStrongestPartition(int[] nums, int left, int right, int median) {
        int pivotIdx = left + rand.nextInt(right - left + 1);
        swap(nums, left, pivotIdx);
        int pivot = nums[left], i = left - 1, j = right + 1;

        while (true) {
            do {
                i++;
            } while (compare(nums[i], pivot, median));

            do {
                j--;
            } while (compare(pivot, nums[j], median));

            if (i >= j)
                return j;

            swap(nums, i, j);
        }
    }

    private boolean compare(int i, int pivot, int median) {
        int mi = Math.abs(i - median), mp = Math.abs(pivot - median);
        return mi == mp ? i > pivot : mi > mp;
    }

    private void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    public static void main(String[] args) {
        _1471_kthStrongest ks = new _1471_kthStrongest();

        // 1
        int[] nums = {7, 10, 4, 3, 20, 15};
        int k = 4;
        System.out.println(Arrays.toString(ks.getStrongest(nums, k)));

        // 2
        nums = new int[]{1, 2, 3, 4, 5};
        k = 2;
        System.out.println(Arrays.toString(ks.getStrongestQS(nums, k)));


        // 3
        nums = new int[]{1, 1, 3, 5, 5};
        k = 2;
        System.out.println(Arrays.toString(ks.getStrongestQS(nums, k)));

        // 4
        nums = new int[]{6, 7, 11, 7, 6, 8};
        k = 5;
        System.out.println(Arrays.toString(ks.getStrongestQS(nums, k)));

        // 5
        nums = new int[]{6, -3, 7, 2, 11};
        k = 3;
        System.out.println(Arrays.toString(ks.getStrongestQS(nums, k)));

        // 6
        nums = new int[]{-7, 22, 17, 3};
        k = 2;
        System.out.println(Arrays.toString(ks.getStrongestQS(nums, k)));
    }
}