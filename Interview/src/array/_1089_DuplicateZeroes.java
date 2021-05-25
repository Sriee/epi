package array;

import java.util.Arrays;

class _1089_DuplicateZeroes {
    public void duplicateZeros(int[] arr) {
        int n = arr.length, counter = 0;

        for (int i : arr) counter += i == 0 ? 1 : 0;

        for (int i = n - 1, write = n + counter - 1; i >= 0 && write >= 0; i--, write--) {
            if (arr[i] == 0) {
                if (write < n) arr[write] = arr[i];
                write--;
                if (write < n) arr[write] = arr[i];
            } else {
                if (write < n) arr[write] = arr[i];
            }
        }
    }

    public static void main(String[] args) {
        _1089_DuplicateZeroes dz = new _1089_DuplicateZeroes();
        int[][] inputs = {
                {1, 2, 3},
                {1, 0, 2, 3, 0, 4, 5, 0}
        };

        for (int[] input : inputs) {
            dz.duplicateZeros(input);
            System.out.println(Arrays.toString(input));
        }
    }
}