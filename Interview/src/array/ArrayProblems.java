package array;

import java.util.*;

public class ArrayProblems {

    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int clockwise = 0, anti = 0, n = distance.length;

        if (start > destination) {
            int temp = start;
            start = destination;
            destination = temp;
        }

        for (int i = start; i < destination; i++) {
            clockwise += distance[i];
        }

        for (int i = destination; i != start; i = (i + 1) % n) {
            anti += distance[i];
        }

        return (clockwise < anti) ? clockwise : anti;
    }

    /**
     * Leet code. Solution -> Accepted Run time: 0ms for both approaches
     * 
     * @param nums1 Array 1
     * @param m     number of elements in first array
     * @param nums2 Array 2
     * @param n     number of elements in second array
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        /**
         * Approach 1: Adding elements and sorting. Time Complexity O(m + n log (m + n))
         */
        for (int i = 0, j = m; i < n; i++) {
            nums1[j++] = nums2[i];
        }

        Arrays.sort(nums1);

        /**
         * Approach 2: Two pointer approach. Time complexity: O(m + n)
         */
        int k = m + n - 1, i = m - 1, j = n - 1;
        while (i >= 0 && j >= 0)
            nums1[k--] = (nums1[i] > nums2[j]) ? nums1[i--] : nums2[j--];

        while (i >= 0)
            nums1[k--] = nums1[i--];

        while (j >= 0)
            nums1[k--] = nums2[j--];
    }

    /**
     * Leet code. Solution -> Accepted Run Time: 0ms. Optimal solution Rotate the
     * array k times Solution uses Approach 2: Reversing the array to rotate it
     * 
     * @param nums given array
     * @param k    number of times to rotate
     */
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0)
            return;

        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    /**
     * Leet code. Solution -> Accepted Run Time: 0ms. Optimal solution Rotate the
     * array k times Solution uses Approach 1: Cyclic Replacement We can directly
     * place every number of the array at its required correct position. But if we
     * do that, we will destroy the original element. Thus, we need to store the
     * number being replaced in a temptemptemp variable. Then, we can place the
     * replaced number(temp) at its correct position and so on
     * 
     * @param nums given array
     * @param k    number of times to rotate
     */
    public void rotateCyclic(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0)
            return;

        k %= nums.length;
        int count = 0, nextIdx = 0, currentIdx, prev, temp;
        while (count < nums.length) {
            currentIdx = nextIdx;
            prev = nums[currentIdx];

            do {
                nextIdx = (nextIdx + k) % nums.length;
                temp = nums[nextIdx];
                nums[nextIdx] = prev;
                prev = temp;
                count++;
            } while (currentIdx != nextIdx);
            nextIdx++;
        }
    }

    /**
     * Utility method to reverse elements of an array
     * 
     * @param nums  given array
     * @param start pointer location
     * @param end   pointer location
     */
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }

    public void trapWater() {
        int[] A = new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        Stack<Integer> s = new Stack<Integer>();
        int i = 0, maxWater = 0, maxBotWater = 0;
        while (i < A.length) {
            if (s.isEmpty() || A[i] <= A[s.peek()]) {
                s.push(i++);
            } else {
                int bot = s.pop();
                maxBotWater = s.isEmpty() ? // empty means no il
                        0 : (Math.min(A[s.peek()], A[i]) - A[bot]) * (i - s.peek() - 1);
                maxWater += maxBotWater;
            }
        }
        System.out.println(maxWater);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < len; i++) {
            int low = i + 1, high = len - 1;
            int sum = nums[i] + nums[low] + nums[high];

            if (sum > 0) {
                high--;
                if (nums[high] == nums[high + 1])
                    high--;
            } else if (sum < 0) {
                low++;
                if (nums[low] == nums[low - 1])
                    low++;
            } else {
                List<Integer> curr = new ArrayList<>();
                curr.add(nums[i]);
                curr.add(nums[low]);
                curr.add(nums[high]);
                res.add(curr);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        ArrayProblems ap = new ArrayProblems();
        int res = ap.distanceBetweenBusStops(new int[] { 1, 2, 3, 4 }, 0, 2);
        System.out.println(res);

        int[] arr = new int[] { 1, 2, 3, 46, 79, 34, 6, 7, 23, 4, 6, 0, 9, 4, 37, 4, 5, 64, 9, 80, 50, 8, 6 };
        res = ap.distanceBetweenBusStops(arr, 18, 2);
        System.out.println(res);

        ap.trapWater();
    }

}
