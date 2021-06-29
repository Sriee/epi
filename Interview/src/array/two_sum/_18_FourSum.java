package array.two_sum;

import java.util.*;

/**
 * Follows K Sum template
 */
class _18_FourSum {
    int n = 0;

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4)
            return res;

        Arrays.sort(nums);
        n = nums.length;
        return kSum(nums, target, 4, 0, new ArrayList<>(), res);
    }

    private List<List<Integer>> kSum(int[] nums, int target, int k, int start, List<Integer> lst, List<List<Integer>> res) {
        if (k == 2) {
            int left = start, right = n - 1;

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    List<Integer> inner = new ArrayList<>(lst);
                    inner.add(nums[left++]);
                    inner.add(nums[right--]);
                    res.add(inner);

                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                }
            }
        } else {
            for (int i = start; i <= n - k; i++) {
                if (i > start && nums[i] == nums[i - 1]) continue;

                lst.add(nums[i]);
                kSum(nums, target - nums[i], k - 1, i + 1, lst, res);
                lst.remove(lst.size() - 1);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        _18_FourSum fs = new _18_FourSum();
        List<List<Integer>> result;
        int[] nums;

        // 1
        nums = new int[]{1, 0, -1, 0, -2, 2};
        result = fs.fourSum(nums, 0);
        System.out.println(result);

        // 2
        nums = new int[]{7, 1, 7, -7, 10, 3, -5, -3, -9, 6, -8, -6, -10, 10, -3, 4, 2};
        result = fs.fourSum(nums, 18);
        System.out.println(result);
    }
}