package array;

import java.util.*;

public class _350_ArrayIntersection {

    /**
     * Approach 1: A simpler Two Pointer Approach
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, k = 0, l1 = nums1.length, l2 = nums2.length;
        List<Integer> common = new ArrayList<>();

        while (i < l1 && j < l2) {
            if (nums1[i] == nums2[j]) {
                common.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        int[] result = new int[common.size()];

        for (int v : common)
            result[k++] = v;

        return result;
    }

    /**
     * Approach 2: HashMap approach
     * <p>
     * TC: O(m + n)
     * SC: Because of the trick the SC will be O(min(n, m))
     */
    public int[] intersection2(int[] nums1, int[] nums2) {

        // A Neat little trick to keep nums1 <= nums2
        if (nums1.length > nums2.length) {
            return intersection2(nums2, nums1);
        }
        /*
         * Normally we will be doing
         *
         * if (nums1.length > nums2.length) {
         *   int[] temp = nums1;
         *   nums1 = nums2;
         *   nums2 = temp;
         * }
         *
         */

        // To increase the run time of the solution, replace hashmap with int[] if we know the
        // upper bound of elements
        Map<Integer, Integer> map = new HashMap<>();
        int k = 0;

        for (int i : nums1) map.put(i, map.getOrDefault(i, 0) + 1);

        for (int i : nums2) {
            Integer value = map.get(i);
            if (value != null && value > 0) {
                nums1[k++] = i;
                map.put(i, value - 1);
            }
        }

        // By re-using nums1, we are avoiding the need to create a list + array
        return Arrays.copyOfRange(nums1, 0, k);
    }

    public static void main(String[] args) {
        _350_ArrayIntersection ai = new _350_ArrayIntersection();
        int[] nums1, nums2, result;

        // 1
        nums1 = new int[]{1, 2, 3, 3, 4, 5};
        nums2 = new int[]{3, 3, 4};
        result = ai.intersection(nums1, nums2);
        System.out.println(Arrays.toString(result));

        // 2
        nums1 = new int[]{-1, 5, 6, 7, 4, 6, 8, 7, 3, 9, 1, 3, 8, 7, 3, 5, 4, 7, 3, 9, 4, 7, 9};
        nums2 = new int[]{7, 8, 9, 1, 5, 6, 8, 3, 5, 4, 9, 6, 8, 9};
        result = ai.intersection2(nums1, nums2);
        System.out.println(Arrays.toString(result));

        // 3
        nums1 = new int[]{1};
        nums2 = new int[]{1};
        result = ai.intersection(nums1, nums2);
        System.out.println(Arrays.toString(result));

        // 4
        nums1 = new int[]{1, 1, 1, 1, 1, 1, 1};
        nums2 = new int[]{1, 1, 1, 1};
        result = ai.intersection2(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }
}
