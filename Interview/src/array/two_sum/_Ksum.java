package array.two_sum;

import java.util.*;

/**
 * K Sum template to solve 2 sum related problems.
 * <p>
 * TC: O(n log n) + O(n ^ (k - 1)) = O(n ^ (k - 1))
 * SC: O(log n)
 */
public class _Ksum {
    int n;

    public List<List<Integer>> kSum(int[] nums, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < k)
            return res;

        n = nums.length;
        Arrays.sort(nums);
        return helper(nums, k, 0, 0, new ArrayList<>(), res);
    }

    private List<List<Integer>> helper(int[] nums, int k, int target, int start,
                                       List<Integer> lst, List<List<Integer>> res) {
        if (k == 2) {
            int i = start, j = n;
            while (i < j) {
                int sum = nums[i] + nums[j];
                if (sum < target)
                    i++;
                else if (sum > target)
                    j--;
                else {
                    List<Integer> inner = new ArrayList<>(lst);
                    inner.add(i++);
                    inner.add(j--);
                    res.add(inner);

                    while (i < n && nums[i] == nums[i - 1]) i++;
                    while (j < n && nums[j] == nums[j + 1]) j--;
                }
            }
        } else {
            for (int i = start; i <= n - k; i++) {
                if (i > start && nums[i] == nums[i - 1])
                    continue;

                lst.add(nums[i]);
                helper(nums, k - 1, target, i + 1, lst, res);
                lst.remove(lst.size() - 1);
            }
        }
        return res;
    }
}
