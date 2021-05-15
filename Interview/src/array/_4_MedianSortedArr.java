package array;

import java.util.*;

public class _4_MedianSortedArr {
    private final Random rand = new Random();

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = -1;
        int op = rand.nextInt(3) + 1;

        switch (op) {
            case 1:
                System.out.print("Merge and Sort Approach. ");
                result = mergeSortApproach(nums1, nums2);
                break;
            case 2:
                System.out.print("Priority Queue Approach. ");
                result = pqApproach(nums1, nums2);
                break;
            case 3:
                System.out.print("Binary Search Approach. ");
                result = binarySearchApproach(nums1, nums2);
                break;
        }

        return result;
    }

    /**
     * Approach 1: Merge and sort
     * <p>
     * TC: O((m + n) log (m + n))
     * SC: O(m + n)
     */
    private double mergeSortApproach(int[] nums1, int[] nums2) {
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
    private double pqApproach(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;

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

            if (curr == null) break;

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

    /**
     * Approach 3:
     * <p>
     * TC: O(log(min(m, n))
     * SC: O(1)
     */
    private double binarySearchApproach(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;

        // Trick: To make sure nums1.length is always < nums2.length. We don't need to do a explicit swap of the
        // array's
        if (m > n) {
            return binarySearchApproach(nums2, nums1);
        }

        /*
            Why right = m instead of m - 1?
            The idea is to think of the partition is between numbers [1, 2 | 3, 4] since we can't replicate this
            literally, we will consider idx = 2 as the partition.
                So left of the partition = 2 and right of the partition = 3. In order to achieve this we need to
                consider right = m because let's say the partition end's up like - [1, 2, 3, 4] |. We need right = m
                inorder to fetch 4 (m - 1).
         */

        int left = 0, right = m;
        int combinedLength = m + n;

        while (left <= right) {
            // What is the idea here?
            int partX = left + (right - left) / 2;
            int partY = (combinedLength + 1) / 2 - partX;

            int leftX = getMax(nums1, partX);
            int rightX = getMin(nums1, partX);

            int leftY = getMax(nums2, partY);
            int rightY = getMin(nums2, partY);

            if (leftX <= rightY && leftY <= rightX) {
                if (combinedLength % 2 == 0) {
                    // Look at this trick below. Since we are dividing by 2.0 it provides a implicit cast to double.
                    // We don't need to do ( (double) x + y ) / 2;
                    return (Math.max(leftX, leftY) + Math.min(rightX, rightY)) / 2.0;
                }
                return Math.max(leftX, leftY);
            }

            if (leftX <= rightY)
                left = partX + 1;
            else
                right = partX - 1;
        }

        return -1;
    }

    private int getMax(int[] arr, int partition) {
        return (partition == 0) ? Integer.MIN_VALUE : arr[partition - 1];
    }

    private int getMin(int[] arr, int partition) {
        return (partition == arr.length) ? Integer.MAX_VALUE : arr[partition];
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
