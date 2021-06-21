package heaps;

import java.util.*;

class _1471_kthStrongest {
    Random rand = new Random();

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

    public int[] getStrongestQS(int[] arr, int k) {
        int n = arr.length;
        int medianIdx = quickSelect(arr, 0, n - 1, (n - 1) / 2, -1);

        quickSelect(arr, 0, n - 1, n - k, arr[medianIdx]);

        return Arrays.copyOfRange(arr, n - k, n);
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

    private int findKStrongestPartition(int[] nums, int left, int right, int median) {
        int pivotIdx = left + rand.nextInt(right - left + 1);
        swap(nums, left, pivotIdx);
        // int pivot = Math.abs(nums[left] - median);
        int pivot = nums[left], i = left - 1, j = right + 1;

        while (true) {
            do {
                i++;
            } while (compare1(nums, i, pivot, median));
            //while (Math.abs(nums[i] - median) < pivot);

            do {
                j--;
            } while (compare2(nums, i, pivot, median));
            // while (Math.abs(nums[j] - median) > pivot);

            if (i >= j)
                return j;

            swap(nums, i, j);
        }
    }

    private boolean compare1(int[] nums, int i, int pivot, int median) {
        int mi = Math.abs(nums[i] - median), mp = Math.abs(pivot - median);

        return mi == mp ? nums[i] < pivot : mi < mp;
    }

    private boolean compare2(int[] nums, int i, int pivot, int median) {
        int mi = Math.abs(nums[i] - median), mp = Math.abs(pivot - median);

        return mi == mp ? nums[i] > pivot : mi > mp;
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
        //        int[] nums = {3, -1, 15};
    }
}