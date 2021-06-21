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
     */
    public int[] getStrongestQS(int[] arr, int k) {
        int n = arr.length;
        int medianIdx = findMedian(arr, 0, n - 1, (n - 1) / 2);

        int median = arr[medianIdx];
        int[][] arrWithStrength = new int[n][2];
        for (int i = 0; i < n; i++) {
            arrWithStrength[i][0] = Math.abs(arr[i] - median);
            arrWithStrength[i][1] = arr[i];
        }

        findKthStrongest(arrWithStrength, 0, n - 1, k);

        System.out.println("\n" + Arrays.deepToString(arrWithStrength));

        int[] res = new int[k];
        for (int i = 0, j = 0; i < k; i++, j++) {
            res[j] = arrWithStrength[i][1];
        }
        return res;
    }

    /* ==============================================================
     * Quick Select Algorithm to Find Median
     * ==============================================================
     */
    private int findMedian(int[] nums, int left, int right, int k) {
        int partitionIdx;
        while (left < right) {
            partitionIdx = findMedianPartition(nums, left, right);

            if (partitionIdx < k)
                left = partitionIdx + 1;
            else
                right = partitionIdx;
        }

        return left;
    }

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

    private void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    /* ==============================================================
     * Quick Select Algorithm to Find Kth Strongest Values
     * ==============================================================
     */
    private int findKthStrongest(int[][] nums, int left, int right, int k) {
        int partitionIdx;
        while (left < right) {
            partitionIdx = findKStrongestPartition(nums, left, right);

            if (partitionIdx < k)
                left = partitionIdx + 1;
            else
                right = partitionIdx;
        }

        return left;
    }

    private int findKStrongestPartition(int[][] nums, int left, int right) {
        int pivotIdx = left + rand.nextInt(right - left + 1);
        swap2D(nums, left, pivotIdx);
        int[] pivot = nums[left];
        int i = left - 1, j = right + 1;

        while (true) {
            do {
                i++;
            } while (compare(nums[i], pivot));

            do {
                j--;
            } while (compare(pivot, nums[j]));

            if (i >= j)
                return j;

            swap2D(nums, i, j);
        }
    }

    private boolean compare(int[] i, int[] pivot) {
        return i[0] == pivot[0] ? i[1] > pivot[1] : i[0] > pivot[0];
    }

    private void swap2D(int[][] nums, int first, int second) {
        int[] temp = nums[first];
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