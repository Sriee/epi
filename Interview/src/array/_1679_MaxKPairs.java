package array;

import java.util.*;

class _1679_MaxKPairs {
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int op = 0;

        for (int i : nums) {
            int ki = k - i;
            if (map.containsKey(ki)) {
                op++;
                if (map.get(ki) == 1)
                    map.remove(ki);
                else
                    map.put(ki, map.get(ki) - 1);
            } else if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }

        return op;
    }

    public static void main(String[] args) {
        _1679_MaxKPairs mkp = new _1679_MaxKPairs();
        int[] nums;

        // 1
        nums = new int[]{1, 2, 3, 4};
        System.out.println(mkp.maxOperations(nums, 5));

        // 2
        nums = new int[]{3, 1, 3, 4, 3};
        System.out.println(mkp.maxOperations(nums, 6));

        // 3
        nums = new int[]{2, 5, 4, 4, 1, 3, 4, 4, 1, 4, 4, 1, 2, 1, 2, 2, 3, 2, 4, 2};
        System.out.println(mkp.maxOperations(nums, 3));
    }
}