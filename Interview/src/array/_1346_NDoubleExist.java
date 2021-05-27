package array;

import java.util.*;

class _1346_NDoubleExist {
    // Approach 1: Using HashMap
    public boolean checkIfExist(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = arr.length;

        for (int i = 0; i < n; i++)
            map.put(arr[i], i);

        for (int i = 0; i < n; i++) {
            int twice = 2 * arr[i];

            if (map.containsKey(twice)) {
                if (map.get(twice) != i) {
                    return true;
                }
            }
        }

        return false;
    }

    // Approach 2: Using Sets
    public boolean checkIfExist2(int[] arr) {
        Set<Integer> set = new HashSet<>();

        for (int i : arr) {
            if (set.contains(2 * i) || (i % 2 == 0 && set.contains(i / 2)))
                return true;

            set.add(i);
        }

        return false;
    }

    public static void main(String[] args) {
        _1346_NDoubleExist nd = new _1346_NDoubleExist();
        int[][] inputs = {
                {10, 2, 5, 3},
                {7, 1, 14, 11}
        };
        System.out.println(nd.checkIfExist(inputs[0]));
        System.out.println(nd.checkIfExist2(inputs[1]));
    }
}