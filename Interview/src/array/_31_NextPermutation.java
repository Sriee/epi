package array;

import java.util.Arrays;

public class _31_NextPermutation {

    private void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2)
            return;
        int i, j, n = nums.length;

        // Find the first element that is lesser than it's successor
        for (i = n - 2; i >= 0 && nums[i] >= nums[i + 1]; i--);

        if (i >= 0) {
            // Find the first element greater than nums[i]
            for (j = n - 1; nums[j] <= nums[i]; j--);

            swap(nums, i, j);
        }

        /*
         * Since the elements right of index i will be decreasing order,
         * sorting it will give us the shortest value.
         */
        reverse(nums, i + 1, n - 1);
        System.out.println(Arrays.toString(nums));
    }

    private void reverse(int[] nums, int i, int j) {
        while(i < j) swap(nums, i++, j--);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        _31_NextPermutation np = new _31_NextPermutation();
        np.nextPermutation(new int[] {1, 3, 5, 4, 2});
        np.nextPermutation(new int[] {1, 3, 3});
        np.nextPermutation(new int[] {5, 4, 2, 1});
    }
}
