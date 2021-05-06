package binary_search;

import java.util.*;

public class _34_SearchRange {

    // Used a modified version of Template 2
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};

        if (nums == null || nums.length == 0)
            return res;

        int left = leftBoundary(nums, target);

        if (left == -1)
            return res;

        res[0] = left;
        res[1] = rightBoundary(nums, target);

        return res;
    }

    private int leftBoundary(int[] nums, int target) {
        int left = 0, right = nums.length - 1, ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid - 1;
            else {
                ans = mid;
                right = mid - 1;
            }
        }

        return ans;
    }

    private int rightBoundary(int[] nums, int target) {
        int left = 0, right = nums.length - 1, ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid - 1;
            else {
                ans = mid;
                left = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        _34_SearchRange fl = new _34_SearchRange();

        int[][] arr = {
                {2, 2},
                {5, 7, 7, 8, 8, 8, 9, 10},
                {5, 7, 7, 8, 8, 8, 9, 10},
                {5, 7, 7, 8, 8, 8, 9, 10}
        };
        int[] toSearch = {2, 8, 5, 11};

        for (int i = 0; i < toSearch.length; i++) {
            int[] res = fl.searchRange(arr[i], toSearch[i]);
            System.out.println(Arrays.toString(res));
        }
    }
}
