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

    public static void main(String[] args) {
        _349_ArrayIntersection ai = new _349_ArrayIntersection();

        // 1
        int[] result = ai.intersection(new int[]{1, 2, 3, 4, 5}, new int[]{5, 5, 5, 5});
        System.out.println(Arrays.toString(result));
    }
}
