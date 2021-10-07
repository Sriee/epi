package array;

import java.util.Arrays;
import java.util.Random;

public class _75_SortColors {

    private Random rand;

    /**
     * Using Dutch national flag algorithm.
     *
     * Not adding it as a pattern since I haven't seen the usage of this algorithm
     * in other problems.
     *
     * TC: O(n)
     * SC: O(1)
     */
    public void sortColorsDNF(int[] nums) {
        int left = 0, curr = 0, right = nums.length - 1;

        while (curr <= right) {
            if (nums[curr] == 0) {
                swap(nums, left, curr);
                left++; curr++;
            } else if (nums[curr] == 2) {
                swap(nums, curr, right);
                right--;
            } else {
                curr++;
            }
        }
    }

    /**
     * Two pass approach
     *
     * TC: O(n)
     * SC: O(1)
     */
    public void sortColorsTwoPass(int[] nums) {
        int[] count = new int[3];

        for (int i : nums)
            count[i]++;

        for (int i = 0, j = 0; i < nums.length; i++) {
            if (count[j]-- > 0)
                nums[i] = j;
            else {
                j++; i--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    private void sortColors(int[] nums){
        int op = rand.nextInt(2) + 1;

        switch (op) {
            case 1:
                System.out.println("Dutch National Flag Algorithm Approach. ");
                sortColorsDNF(nums);
                break;
            case 2:
                System.out.println("Two pass Approach. ");
                sortColorsTwoPass(nums);
                break;
        }
    }

    public static void main(String[] args) {
        _75_SortColors sc = new _75_SortColors();
        sc.rand = new Random();

        int[][] inputs = {
            {2, 0, 2, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 2},
            {0, 0, 0, 0, 0, 1, 2},
            {0, 1, 1, 1, 1, 1, 2},
            {0, 1, 1, 2, 2, 2, 2, 2, 2},
        };

        for (int[] inp : inputs) {
            sc.sortColors(inp);
            System.out.println(Arrays.toString(inp) + "\n");;
        }
    }
}
