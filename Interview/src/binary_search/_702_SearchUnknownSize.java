package binary_search;

/**
 * Binary Search Template 1.
 * <p>
 * This problem illustrates how to do a binary search if we don't know the boundaries.
 */
public class _702_SearchUnknownSize {

    private static class ArrayReader {
        public int[] secret;

        public int get(int index) {
            if (index < 0 || index >= this.secret.length)
                return Integer.MAX_VALUE;

            return this.secret[index];
        }
    }

    /**
     * TC: O(T) where T is the index of the target.
     * Finding Boundaries = O(log T) where 2^k < T <= 2^k+1 = 2 ^ logT = k
     * Binary Search = We will be making 2^k+1 - 2^k steps to find the target = 2^k steps
     * = 2 ^ log T = T
     * <p>
     * SC: O(1)
     */
    public int search(ArrayReader reader, int target) {
        // Step 1. Find boundaries. We double right pointer each time to identify the left and the right boundary
        int left = 0, right = 1;

        // After this operation target will be between 2^k < target <= 2^k+1
        while (reader.get(right) < target) {
            left = right;
            right <<= 1;
        }

        // Binary Search
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int val = reader.get(mid);

            if (target < val)
                right = mid - 1;
            else if (target > val)
                left = mid + 1;
            else
                return mid;
        }

        return -1;
    }

    public static void main(String[] args) {
        _702_SearchUnknownSize us = new _702_SearchUnknownSize();
        ArrayReader reader = new ArrayReader();
        int[] arr;

        // 1
        arr = new int[]{-1, 0, 3, 5, 9, 12};
        reader.secret = arr;
        System.out.println(us.search(reader, 5));
        System.out.println(us.search(reader, 14));

        // 2
        arr = new int[]{0, 3, 4, 5, 7, 14, 15, 36, 65, 68, 76, 90, 95, 98, 125, 256, 457, 536, 637, 834, 840};
        reader.secret = arr;
        System.out.println(us.search(reader, 0));
        System.out.println(us.search(reader, 256));
        System.out.println(us.search(reader, 637));
    }
}
