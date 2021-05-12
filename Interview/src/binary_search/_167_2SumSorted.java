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

    public static void main(String[] args) {
        _167_2SumSorted ts = new _167_2SumSorted();
        int[][] input = {
                {2, 7, 11, 13, 15},
                {-1, 2, 100},
                {1, 2, 3, 4, 4, 9, 56, 90}
        };
        int[] targets = {20, 99, 8};

        for (int i = 0; i < targets.length; i++) {
            System.out.println(Arrays.toString(ts.twoSum(input[i], targets[i])));
        }
    }
}