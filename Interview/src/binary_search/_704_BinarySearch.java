package binary_search;

public class _704_BinarySearch {

    /**
     * Binary Search Template 1
     * <p>
     * This is the first template that illustrates the use of Binary Search. This Template is used to search for an
     * element or condition which can be determined by accessing a single index in the array.
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;

        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > target)
                right = mid - 1;
            else if (nums[mid] < target)
                left = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        _704_BinarySearch bs = new _704_BinarySearch();
        int[][] input = {
                {-1, 0, 3, 5, 9, 12},
                {0, 3, 4, 5, 7, 14, 15, 36, 65, 68, 76, 90, 95, 98, 125, 256, 457, 536, 637, 834, 840}
        };

        System.out.println(bs.search(input[0], 5));
        System.out.println(bs.search(input[0], 13));
        System.out.println(bs.search(input[1], 256));
        System.out.println(bs.search(input[1], 840));
        System.out.println(bs.search(input[1], 0));
        System.out.println(bs.search(input[1], 7));
    }
}
