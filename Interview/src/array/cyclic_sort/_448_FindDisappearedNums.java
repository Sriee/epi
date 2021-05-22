package array.cyclic_sort;

import java.util.*;

public class _448_FindDisappearedNums {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int i = 0, n = nums.length;
        List<Integer> result = new ArrayList<>();

        while (i < n) {
            int correct = nums[i] - 1;

            if (correct < n && nums[i] != nums[correct]) {
                swap(nums, i, correct);
            } else {
                i++;
            }

        }

        for (int k = 0; k < n; k++) {
            if (nums[k] != k + 1)
                result.add(k + 1);
        }

        return result;
    }

    private void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    public static void main(String[] args) {
        _448_FindDisappearedNums fdn = new _448_FindDisappearedNums();
        int[][] inputs = {
                {4, 3, 2, 7, 8, 2, 3, 1},
                {1, 1}
        };

        for (int[] inp : inputs)
            System.out.println(fdn.findDisappearedNumbers(inp));
    }
}
