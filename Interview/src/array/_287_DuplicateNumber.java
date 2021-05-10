package array;

import java.util.*;

/**
 * The objective of the problem is to find a duplicate number in a array without
 * modifying the array or using any additional space.
 */
public class _287_DuplicateNumber {
    private Random rand = new Random();

    /**
     * TC: O(n log n); O(n log n) for sort + O(n) for linear scan to find duplicates.
     * SC: O(log n) Java uses a variant of the quick sort algorithm.
     * <p>
     * This approach modifies the input array.
     */
    private int sortApproach(int[] nums) {
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1])
                return nums[i];
        }

        return -1;
    }

    /**
     * TC: O(n) - we are visiting all elements
     * SC: O(n) - Worst case we have to store n - 1 elements in the set
     * <p>
     * This approach uses additional space.
     */
    private int setApproach(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        for (int i : nums) {
            if (seen.contains(i))
                return i;
            seen.add(i);
        }

        return -1;
    }

    /**
     * Negative Marking Technique
     * <p>
     * Conditions
     * 1. The array should have positive numbers
     * 2. 0 <= nums[i] <= n
     * <p>
     * TC: O(n) - O(n + n) - Negative marking + restoring the array
     * SC: O(1)
     * <p>
     * For example: [1, 3, 3, 2]
     * i  num
     * 0   1  flip the number at index 1, making the array [1, -3, 3, 2]
     * 1  -3  flip the number at index 3, making the array [1, -3, 3, -2]
     * 2   3  When we reach the second 3, we'll notice that nums[3] is already negative, indicating that we have
     * already flipped it in the past and hence is the duplicate number
     */
    private int negativeMarkingApproach(int[] nums) {
        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            int ia = Math.abs(nums[i]);
            if (nums[ia] < 0) {
                res = ia;
                break;
            }

            nums[ia] *= -1;
        }

        // Restore nums
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Math.abs(nums[i]);
        }

        return res;
    }

    public int findDuplicate(int[] nums) {
        int result = -1, op = rand.nextInt(4);

        switch (op) {
            case 1:
                System.out.print("Sort Approach. ");
                result = sortApproach(nums);
                break;
            case 2:
                System.out.print("Set Approach. ");
                result = setApproach(nums);
                break;
            case 3:
                System.out.print("Negative Marking Approach. ");
                result = negativeMarkingApproach(nums);
                break;
            default:
                System.out.println("Reached default.");
                break;
        }

        return result;
    }

    public static void main(String[] args) {
        _287_DuplicateNumber dn = new _287_DuplicateNumber();

        int[][] input = {
                {1, 3, 4, 2, 2},
                {2, 2, 2},
                {3, 1, 3, 4},
                {4, 6, 4, 2, 1, 4, 3, 5}
        };

        /*
        for (int[] inp : input) {
            System.out.printf("Duplicate in %s is %s\n", Arrays.toString(inp), dn.findDuplicate(inp));
        }
*/

        System.out.println(dn.negativeMarkingApproach(input[3]));
    }
}
