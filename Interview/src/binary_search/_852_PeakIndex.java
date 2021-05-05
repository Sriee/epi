package binary_search;

/**
 * Binary Search Template 2 - This problem is very much similar to Finding Peak Element.
 *
 * @see binary_search/_162_PeakElement.java
 */
public class _852_PeakIndex {

    public int peakIndexInMountainArray(int[] arr) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < arr[mid + 1])
                left = mid + 1;
            else
                right = mid;
        }

        return left;
    }

    public static void main(String[] args) {
        _852_PeakIndex pi = new _852_PeakIndex();

        int[][] input = {
                {1, 3, 5, 3, 1},
                {0, 1, 0},
                {1, 2, 5, 8, -1}
        };

        for (int[] inp : input) {
            System.out.println(pi.peakIndexInMountainArray(inp));
        }
    }
}
