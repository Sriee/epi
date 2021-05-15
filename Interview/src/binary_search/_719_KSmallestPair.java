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

    // TreeMap approach
    public int smallestDistancePair3(int[] nums, int k) {
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        Map<Integer, List<int[]>> countMap = new TreeMap<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int key = nums[j] - nums[i];
                List<int[]> lst = countMap.getOrDefault(key, new ArrayList<>());
                lst.add(new int[]{i, j});
                countMap.put(key, lst);
            }
        }

        int num = 0;
        for (int count : countMap.keySet()) {
            System.out.println(count + " " + Arrays.deepToString(countMap.get(count).toArray()));
            num += countMap.get(count).size();
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
