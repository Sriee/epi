package array;

import java.util.*;

public class _4_MedianSortedArr {
    private final Random rand = new Random();

    /**
     * Approach 1: Merge and sort
     * <p>
     * TC: O((m + n) log (m + n))
     * SC: O(m + n)
     */
    public double mergeSortApproach(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        int mid = size / 2;
        int[] arr = new int[size];

        System.arraycopy(nums1, 0, arr, 0, nums1.length);
        System.arraycopy(nums2, 0, arr, nums1.length, nums2.length);
        Arrays.sort(arr);

        if (size % 2 == 0)
            return ((double) arr[mid] + arr[mid - 1]) / 2;
        else
            return arr[mid];
    }

    /**
     * Approach 2: Priority Queue Approach
     * <p>
     * TC: O((m + n) log (m + n))
     * SC: O(1) - The bottleneck was that we were creating the new int[] arrays for fetching next elements of both
     * arrays.
     */
    public double pqApproach(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;

        if (m == 0)
            return calcMedian(nums2, n);

        if (n == 0)
            return calcMedian(nums1, m);

        int size = m + n;
        boolean isEven = size % 2 == 0;
        int mid = (size / 2) + 1;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> a[2] == b[2] ? a[1] - b[1] : Integer.compare(a[2], b[2])
        );

        pq.offer(new int[]{1, 0, nums1[0]});
        pq.offer(new int[]{2, 0, nums2[0]});
        int[] first = new int[3], second = new int[3], curr;

        while (mid > 0) {
            curr = pq.poll();

            System.arraycopy(second, 0, first, 0, 3);
            System.arraycopy(curr, 0, second, 0, 3);

            curr[1]++;

            if (curr[0] == 1 && curr[1] < m) {
                curr[2] = nums1[curr[1]];
                pq.offer(curr);
            }

            if (curr[0] == 2 && curr[1] < n) {
                curr[2] = nums2[curr[1]];
                pq.offer(curr);
            }

            mid--;
        }

        if (isEven) {
            return ((double) first[2] + second[2]) / 2;
        } else {
            return second[2];
        }
    }

    private double calcMedian(int[] arr, int len) {
        int mid = len / 2;
        if (len % 2 == 0)
            return ((double) arr[mid] + arr[mid - 1]) / 2;
        else
            return arr[mid];
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = -1;
        int op = rand.nextInt(2) + 1;

        switch (op) {
            case 1:
                System.out.print("Merge and Sort Approach. ");
                result = mergeSortApproach(nums1, nums2);
                break;
            case 2:
                System.out.print("Priority Queue Approach. ");
                result = pqApproach(nums1, nums2);
                break;
        }

        return result;
    }

    public static void main(String[] args) {
        _4_MedianSortedArr ms = new _4_MedianSortedArr();

        int[][] nums1 = {
                {1, 3},
                {1},
                {},
                {6},
                {1, 2, 3},
                {30, 40, 50, 60},
                {9},
                {6, 11, 11, 11, 15, 17, 23, 60}
        };
        int[][] nums2 = {
                {2},
                {2},
                {5, 6},
                {},
                {4, 5, 6},
                {9, 39, 44, 59},
                {1, 2, 3},
                {1, 2, 5, 6, 15, 17}
        };

        for (int i = 0; i < nums1.length; i++) {
            System.out.println(ms.findMedianSortedArrays(nums1[i], nums2[i]));
        }
    }
}
