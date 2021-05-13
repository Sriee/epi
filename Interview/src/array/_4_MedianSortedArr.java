package array;

import java.util.*;

public class _4_MedianSortedArr {

    /**
     * Approach 1: Merge and sort
     * <p>
     * TC: O((m + n) log (m + n))
     * SC: O(m + n)
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
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
