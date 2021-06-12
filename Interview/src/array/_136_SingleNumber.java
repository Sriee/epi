package array;

import java.util.*;

class _136_SingleNumber {

    // Approach 1: XOR
    public int singleNumber(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[0] ^= nums[i];
        }

        return nums[0];
    }

    // Approach 2:
    public int singleNumber2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums)
            map.put(i, map.getOrDefault(i, 0) + 1);

        for (int i : map.keySet()) {
            if (map.get(i) == 1)
                return i;
        }

        return -1;
    }

    public static void main(String[] args) {
        _136_SingleNumber sn = new _136_SingleNumber();
        System.out.println(sn.singleNumber(new int[]{2, 2, 1}));
        System.out.println(sn.singleNumber2(new int[]{4, 1, 2, 1, 2}));
    }
}