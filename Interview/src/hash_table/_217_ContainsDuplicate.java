package hash_table;

import java.util.*;

class _217_ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int i : nums) {
            if (seen.contains(i))
                return true;

            seen.add(i);
        }

        return false;
    }

    public static void main(String[] args) {
        _217_ContainsDuplicate cd = new _217_ContainsDuplicate();
        int[][] inputs = {
                {1, 1, 4, 2, 1, 3},
                {5, 1, 2, 3, 4},
                {-1, 2, 3, -1, 4, 5}
        };

        for (int[] input : inputs) {
            System.out.println(cd.containsDuplicate(input));
        }
    }
}