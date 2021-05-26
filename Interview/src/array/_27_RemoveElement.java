package array;

import java.util.Arrays;

class _27_RemoveElement {

    /**
     * Two Pointer Technique - In-place operation
     */
    public int removeElement(int[] nums, int val) {
        int write = 0;

        for (int read = 0; read < nums.length; read++) {
            if (nums[read] != val)
                nums[write++] = nums[read];
        }

        return write;
    }

    public static void main(String[] args) {
        _27_RemoveElement re = new _27_RemoveElement();
        int[][] inputs = {
                {3, 2, 2, 3},
                {-7, -3, 0, 2, 0, 3, 11, 0}
        };
        int[] toRemove = {2, 0};

        for (int i = 0; i < toRemove.length; i++) {
            int newLength = re.removeElement(inputs[i], toRemove[i]);
            int[] res = new int[newLength];
            System.arraycopy(inputs[i], 0, res, 0, newLength);
            System.out.println(Arrays.toString(res));
        }
    }
}