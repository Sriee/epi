package binary_search;

import java.util.Arrays;

public class _167_2SumSorted {

    private int search(int[] arr, int target, int start) {
        int left = start + 1, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target)
                return mid;
            else if (arr[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }

        return -1;
    }

    /**
     * Binary Search Approach
     * <p>
     * TC: O(n log n)
     * SC: O(1)
     */
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];

        for (int i = 0; i < numbers.length; i++) {
            int j = search(numbers, target - numbers[i], i);

            if (j == -1)
                continue;

            result[0] = i + 1;
            result[1] = j + 1;

            break;
        }
        return result;
    }

    /**
     * Better Binary Search
     * <p>
     * TC: O(n)
     * Worst case: O(n) when two indexes are at the center
     * Best case: O(log n)
     * <p>
     * SC: O(1)
     * <p>
     * How to move the pointers? Ex: [2, 7, 11, 13, 15], target = 20
     * left right mid  sum
     * 0     4    2   17  17 < 20. We have to move the left pointer. We have 2 options mid or left + 1.
     * mid + right = 26 > 20 so, we can't move our left boundary to mid.
     * We choose left + 1
     * <p>
     * 1     4    2   22  22 > 20  We have to move the right pointer. We have 2 options mid or right - 1.
     * left + mid = 7 + 11 = 18 < 20 so, we can't move our right boundary to mid.
     * We choose right - 1
     * <p>
     * 1     3    2   20  20 == 20 We found the result!
     */
    public int[] twoSum2(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            int sum = numbers[left] + numbers[right];

            if (sum == target)
                return new int[]{left + 1, right + 1};
            else if (sum < target)
                left = (numbers[mid] + numbers[right] < target) ? mid : left + 1;
            else
                right = (numbers[left] + numbers[mid] > target) ? mid : right - 1;
        }

        return null;
    }

    public static void main(String[] args) {
        _167_2SumSorted ts = new _167_2SumSorted();
        int[][] input = {
                {2, 7, 11, 13, 15},
                {-1, 2, 100},
                {1, 2, 3, 4, 4, 9, 56, 90}
        };
        int[] targets = {20, 99, 8}, result;

        for (int i = 0; i < targets.length; i++) {
            result = ((i & 1) == 0) ? ts.twoSum(input[i], targets[i]) : ts.twoSum2(input[i], targets[i]);
            System.out.println(Arrays.toString(result));
        }
    }
}