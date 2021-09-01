package array;

import java.util.Arrays;

class _220_ContainsDuplicate3 {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0)
            return false;

        int start = 0, end = 1, n = nums.length - 1;
        Arrays.sort(nums);

        while (end < n) {
            if (end - start > k) {
                continue;
            }

            int diff = nums[end] - nums[start];
            if (diff > t) {
                start++;
                end = start + 1;
            } else {
                return true;
            }

        }
        return false;
    }

    public static void main(String[] args) {
        _220_ContainsDuplicate3 cd = new _220_ContainsDuplicate3();
        int[] nums = {1, 5, 9, 1, 5, 9};
        System.out.println(cd.containsNearbyAlmostDuplicate(nums, 2, 3));
    }
}