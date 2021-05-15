package binary_search;

import java.util.*;

// https://www.youtube.com/watch?v=HiSvEhLIaTI
public class _719_KSmallestPair {

    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int count[] = new int[nums[nums.length - 1] + 1];

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                count[nums[j] - nums[i]]++;
            }
        }
        System.out.println(Arrays.toString(nums) + "\n" + Arrays.toString(count));

        int number = 0;
        for (int i = 0; i < count.length; i++) {
            number += count[i];
            if (number >= k)
                return i;
        }

        return -1;
    }

    public int smallestDistancePair2(int[] nums, int k) {
        Arrays.sort(nums);
        int lo = 0, hi = nums[nums.length - 1] - nums[0];

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int j = 0, count = 0; //findNumPairs(nums, mid);

            for (int i = 0; i < nums.length; i++) {
                while (j < nums.length && nums[j] <= nums[i] + mid) {
//                    System.out.println(nums[j] + " " + nums[i] + "+" + mid + " = " + (j + 1));
                    j++;
                }
                count += j - i - 1;
//                System.out.println("count= " + count);
            }

            if (count >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    /**
     * Modified TreeMap approach
     * <p>
     * TC: O(n log n) + O(n ^ 2) + O(log m) + O(m)
     * SC: O(m)
     * where m = m = nums[nums.length - 1] + 1
     * <p>
     * Solution TLE As expected.
     */
    public int smallestDistancePair3(int[] nums, int k) {
        // O(n log n)
        Arrays.sort(nums);

        // We don't need to store the pairs. It was just for our trial purposes. Replacing it with a simple counter.
        Map<Integer, Integer> countMap = new TreeMap<>();

        // O(n^2)
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int key = nums[j] - nums[i];

                // O (log m) where m = nums[nums.length - 1] + 1
                countMap.put(key, countMap.getOrDefault(key, 0) + 1);
            }
        }

        int num = 0;

        // Worst case: O(m)
        for (int count : countMap.keySet()) {
            num += countMap.get(count);
            if (num >= k)
                return count;
        }

        return -1;
    }

    public static void main(String[] args) {
        _719_KSmallestPair sp = new _719_KSmallestPair();
//        int[] nums = {39,5,6,0,8,15,20};
        int[] nums = {3, 4, 1, 2, 5, 3, 7, 5};
        System.out.println(sp.smallestDistancePair3(nums, 11));
//        System.out.println(sp.smallestDistancePair(nums, 11));
    }
}
