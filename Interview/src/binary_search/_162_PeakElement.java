package binary_search;

/**
 * Binary Search Template 2
 */
public class _162_PeakElement {

    /**
     * For this template we need to look at Binary Search in a different way as an array with prefixes of True's
     * followed by suffixes of False or vice versa.
     * <p>
     * For this problem as an example [3, 4, 9, 11, 23, 19, 13, 6], the array could be thought as
     * [T, T, T, T, T, F, F, F] and we are find the last Truth value.
     * <p>
     * Note the placements of print statements for Binary Search problems. It helps us to identify
     * the problems in our binary search routine.
     */
    public int findPeakElement(int[] arr) {
        int left = 0, right = arr.length - 1, mid;

        while (left < right) {
            mid = left + (right - left) / 2;

            // System.out.println(left + " " + right + " " + mid);
            if (arr[mid] < arr[mid + 1])
                left = mid + 1;
            else
                right = mid;
        }
        // System.out.println(left + " " + right + " " + mid);
        return left;
    }

    public static void main(String[] args) {
        _162_PeakElement pe = new _162_PeakElement();
        int[][] input = {
                {1, 2, 1, 3, 5, 6, 7},
                {1, 2, 3, 4},
                {8, 1, 2, 3, -1}
        };

        for (int[] inp : input) {
            System.out.println(pe.findPeakElement(inp));
        }
    }
}
