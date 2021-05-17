package binary_search;

public class _35_SearchInsertPosition {

    /**
     * Binary Search Template 2
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] >= target)
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }

    public static void main(String[] args) {
        _35_SearchInsertPosition sp = new _35_SearchInsertPosition();
        int[] nums = {1, 3, 5, 6}, toInsert = {0, 2, 5, 20};
        for (int k : toInsert) {
            System.out.println(sp.searchInsert(nums, k));
        }
    }
}
