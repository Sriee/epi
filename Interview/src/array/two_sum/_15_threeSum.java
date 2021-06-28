package array.two_sum;

import java.util.*;

public class _15_threeSum {

    /*
     * Approach 1: Two Pointer Approach
     *
     * The solution article goes through the steps and complexities in detail. Ignoring re-writing stuff.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            // We are handling duplicates here
            if (i == 0 || nums[i] != nums[i - 1]) {
                twoSumII(nums, i, res);
            }
        }
        return res;
    }

    private void twoSumII(int[] nums, int i, List<List<Integer>> res) {
        int left = i + 1, right = nums.length - 1;

        while (left < right) {
            int sum = nums[i] + nums[left] + nums[right];

            if (sum < 0)
                left++;
            else if (sum > 0)
                right--;
            else {
                res.add(Arrays.asList(nums[i], nums[left++], nums[right--]));

                while (left < right && nums[left] == nums[left - 1])
                    left++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, 4};
        _15_threeSum ts = new _15_threeSum();
        List<List<Integer>> res = ts.threeSum(nums);
        System.out.println(res);
    }
}
