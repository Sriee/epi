package binary_search;

import java.util.*;

public class _34_SearchRange {

    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};

        if (nums == null || nums.length == 0)
            return res;

        res[0] = leftBoundary(nums, target);

        // TODO: We don't need to search for a right boundary if the element is not present in our previous search
        res[1] = rightBoundary(nums, target);

        return res;
    }

    private int leftBoundary(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid;
        }

        return nums[left] == target ? left : -1;
    }

    private int rightBoundary(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        // The below method is represented as Binary Search Template 3 in leetcode but we can solve it using Ericchto
        // Template 2
        while (left + 1 < right) {  // left < right will result in a endless loop
            int mid = left + (right - left) / 2;

            if (nums[mid] > target)
                right = mid - 1;
            else
                left = mid;
        }

        // We will end up with 2 values [left, left + 1]. We need to do a separate check for these two positions
        if (nums[right] == target) return right;
        if (nums[left] == target) return left;

        return -1;
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
