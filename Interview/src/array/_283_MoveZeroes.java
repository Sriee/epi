package array;

import java.util.Arrays;

class _283_MoveZeroes {

    /**
     * Two Pointer Technique - In-place operation
     */
    public void moveZeroes(int[] nums) {
        int writePtr = 0, n = nums.length;

        for (int readPtr = 0; readPtr < n; readPtr++) {
            if (nums[readPtr] != 0)
                nums[writePtr++] = nums[readPtr];
        }

        for (; writePtr < n; writePtr++)
            nums[writePtr] = 0;
    }

    public static void main(String[] args) {
        _283_MoveZeroes mz = new _283_MoveZeroes();

        int[][] inputs = {
                {17, 18, 5, 4, 6, 1},
                {33, 44, 11, 9, 5, 1}
        };

        for (int[] input : inputs) {
            mz.moveZeroes(input);
            System.out.println(Arrays.toString(input));
        }
    }
}