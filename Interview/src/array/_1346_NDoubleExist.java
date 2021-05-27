package array;

import java.util.*;

class _1346_NDoubleExist {
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

    public static void main(String[] args) {
        _1346_NDoubleExist nd = new _1346_NDoubleExist();
        int[][] inputs = {
                {10, 2, 5, 3},
                {7, 1, 14, 11}
        };
        for (int[] input : inputs) {
            System.out.println(nd.checkIfExist(input));
        }
    }
}