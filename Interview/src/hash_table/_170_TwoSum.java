package hash_table;

import java.util.*;

// Variation of Two Sum
class _170_TwoSum {

    Map<Integer, Integer> map;

    public _170_TwoSum() {
        map = new HashMap<>();
    }

    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    public boolean find(int value) {
        for (int i : map.keySet()) {
            int diff = value - i;
            if (diff != i) {
                if (map.containsKey(diff))
                    return true;
            } else {
                if (map.get(diff) > 1)
                    return true;
            }
        }

        return false;
    }
}