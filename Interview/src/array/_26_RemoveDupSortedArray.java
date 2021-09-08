package array;

import java.util.Arrays;

class _26_RemoveDupSortedArray {
    public int removeDuplicates(int[] nums) {
        int wp = 1;
        for (int rp = 1; rp < nums.length; rp++) {
            if (nums[rp] != nums[rp - 1]) nums[wp++] = nums[rp];
        }

        return wp;
    }

    public static void main(String[] args) {
        _26_RemoveDupSortedArray rd = new _26_RemoveDupSortedArray();
        int[][] input = {
                {1, 1, 2},
                {0, 0, 1, 1, 1, 2, 2, 3, 3, 4}
        };

        for (int[] nums : input) {
            int n = rd.removeDuplicates(nums);
            int[] res = new int[n];
            System.arraycopy(nums, 0, res, 0, n);
            System.out.println(Arrays.toString(res));
        }
    }
}