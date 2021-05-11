package array;

import java.util.*;

/**
 * The objective of the problem is to find a duplicate number in a array without
 * modifying the array or using any additional space.
 */
public class _287_DuplicateNumber {
    private final Random rand = new Random();

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

    /**
     * Use the Array as a HashMap - map each number to its equivalent index in the array. Surprisingly this simple
     * technique has many applications.
     * <p>
     * So let's start with the number at index 0 since it must be out of place. Say that the number at index 0 is
     * first. Then first needs to be stored at nums[first]. But there's some other number at
     * nums[first] that needs to be stored at its respective location (and so on).
     * <p>
     * Walk through for example: [1, 3, 3, 2]
     * store(nums, 0) - We are using 0 as a starting element. We chose 0 because all numbers in nums are in range
     * [1, n]
     * - next = 1, nums = [0, 3, 3, 2]
     * - call store(nums, 1)
     * <p>
     * store(nums, 1)
     * - next = 3, nums = [0, 1, 3, 2]
     * - call store(nums, 3)
     * <p>
     * store(nums, 3)
     * - next = 2, nums = [0, 1, 3, 3]
     * - call store(nums, 2)
     * <p>
     * store(nums, 2)
     * - next = 3, nums = [0, 1, 2, 3]
     * - call store(nums, 3)
     * <p>
     * since nums[3] = 3 this is the duplicate number
     */
    private int useArrAsMapApproach(int[] nums) {
        return store(nums, 0);
    }

    private int store(int[] nums, int curr) {
        if (nums[curr] == curr)
            return curr;

        int next = nums[curr];
        nums[curr] = curr;
        return store(nums, next);
    }

    private int useArrAsMapIterativeApproach(int[] nums) {
        int curr = 0;

        while (nums[curr] != curr) {
            int next = nums[curr];
            nums[curr] = curr;
            curr = next;
        }

        return nums[curr];
    }

    /**
     * We can solve using binary search if we can think about the problem in a different.
     * <p>
     * For Example: [4, 3, 1, 4, 2, 6, 5] lets calculate the count of values <= nums[i]
     * <p>
     * Without duplicates = [1, 2, 3, 4, 5, 6]
     * With duplicates = [1, 2, 3, 5, 6, 7] or [f, f, f, t, t, t]
     * |-> (1, 2, 3, 4, 4)
     * <p>
     * if count of values > mid
     * the search space = [1, mid - 1]
     * else
     * the search space = [mid + 1, n]
     * <p>
     * Also note, how binary search was applied without sorting the input array!
     * <p>
     * TC: O(n log n) - O(n) for finding count + O(log n) for binary search.
     * SC: O(1)
     */
    private int binarySearchApproach(int[] nums) {
        int left = 1, right = nums.length - 1, res = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int count = 0;
            for (int i : nums) {
                if (i <= mid)
                    count++;
            }

            if (count > mid) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return res;
    }

    public int findDuplicate(int[] nums) {
        int result = -1, op = rand.nextInt(5) + 1;

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
            case 4:
                System.out.print("Using Array as Map (Recursive) Approach. ");
                result = useArrAsMapApproach(nums);
                break;
            case 5:
                System.out.print("Using Array as Map (Iterative) Approach. ");
                result = useArrAsMapIterativeApproach(nums);
                break;
            case 6:
                System.out.print("Using Binary Search Approach. ");
                result = binarySearchApproach(nums);
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
                {3, 1, 3, 4, 2},
                {4, 6, 4, 2, 1, 4, 3, 5},
                {4, 3, 1, 4, 2}
        };

        for (int[] inp : input) {
            System.out.printf("Duplicate in %s is %s\n", Arrays.toString(inp), dn.findDuplicate(inp));
        }
    }
}
