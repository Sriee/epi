package binary_search;

public class _33_SearchRotated {

    /**
     * Using Binary Search - Template 1
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target)
                return mid;
            else if (nums[mid] >= nums[left]) {
                if (target >= nums[left] && target < nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else {
                if (target > nums[mid] && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        _33_SearchRotated sr = new _33_SearchRotated();
        int[] nums = {4, 5, 6, 7, 0, 1, 2};

        System.out.println(sr.search(nums, 5));
        System.out.println(sr.search(nums, 2));
    }
}
