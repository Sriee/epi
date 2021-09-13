package array;

import java.util.Arrays;

class _561_ArrayPartitionI {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += Math.min(nums[i], nums[i + 1]);
        }

        return sum;
    }

    public static void main(String[] args) {
        _561_ArrayPartitionI ap1 = new _561_ArrayPartitionI();

        int[][] input = {
                {1, 4, 3, 2},
                {6, 2, 6, 5, 1, 2}
        };

        for (int[] nums : input)
            System.out.println(ap1.arrayPairSum(nums));
    }
}