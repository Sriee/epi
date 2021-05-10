package array;

import java.util.*;

public class _349_ArrayIntersection {

    /**
     * Approach 1: Two Hash set
     * <p>
     * TC: O(n + m)
     * SC: O(n + m)
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[]{};
        }

        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        Set<Integer> set = new HashSet<>(), common = new HashSet<>();

        for (int i : nums1) set.add(i);

        for (int i : nums2) {
            if (set.contains(i))
                common.add(i);
        }

        int[] result = new int[common.size()];
        int j = 0;

        for (int i : common)
            result[j++] = i;

        return result;
    }

    /**
     * Approach 2: Two pointer approach
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int p1 = 0, p2 = 0, l1 = nums1.length, l2 = nums2.length, first, second;
        List<Integer> common = new ArrayList<>();

        while (p1 < l1 && p2 < l2) {
            first = nums1[p1];
            second = nums2[p2];

            if (first == second) {
                common.add(nums1[p1]);

                while (p1 < l1 && nums1[p1] == first)
                    p1++;

                while (p2 < l2 && nums2[p2] == second)
                    p2++;
            }

            if (first > second) {
                while (p2 < l2 && nums2[p2] < first)
                    p2++;
            } else {
                while (p1 < l1 && nums1[p1] < second)
                    p1++;
            }
        }

        int[] result = new int[common.size()];
        int j = 0;

        for (int i : common)
            result[j++] = i;

        return result;
    }

    public static void main(String[] args) {
        _349_ArrayIntersection ai = new _349_ArrayIntersection();
        int[] nums1, nums2, result;

        // 1
        nums1 = new int[]{1, 2, 3, 3, 4, 5};
        nums2 = new int[]{3, 3, 4};
        result = ai.intersection(nums1, nums2);
        System.out.println(Arrays.toString(result));

        // 2
        nums1 = new int[]{5, 6, 7, 4, 6, 8, 7, 3, 9, 1, 3, 8, 7, 3, 5, 4, 7, 3, 9, 4, 7, 9};
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
