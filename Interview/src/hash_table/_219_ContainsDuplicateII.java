package hash_table;

import java.util.*;

class _219_ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (i - k <= map.getOrDefault(nums[i], -k - 1))
                return true;
            map.put(nums[i], i);
        }

        return false;
    }

    public static void main(String[] args) {
        _219_ContainsDuplicateII cd = new _219_ContainsDuplicateII();
        int[][] inputs = {
                {1, 2, 3, 1},
                {1, 0, 1, 1},
                {1, 2, 3, 1, 2, 3}
        };

        int[] ks = {3, 1, 2};
        for (int i = 0; i < ks.length; i++) {
            System.out.println(cd.containsNearbyDuplicate(inputs[i], ks[i]));
        }
    }
}