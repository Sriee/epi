package array;

import java.util.*;

class _1299_ReplaceGreatest {

    // Equivalent to monotonic stack approach
    public int[] replaceElements(int[] arr) {
        int n = arr.length;
        int great = arr[n - 1], j = n - 2;
        arr[n - 1] = -1;

        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > great) {
                int prev = arr[i];
                for (; j >= i; j--)
                    arr[j] = great;

                great = prev;
            }
        }

        for (; j >= 0; j--) arr[j] = great;

        return arr;
    }

    public static void main(String[] args) {
        _1299_ReplaceGreatest rg = new _1299_ReplaceGreatest();
        int[][] inputs = {
                {17, 18, 5, 4, 6, 1},
                {33, 44, 11, 9, 5, 1}
        };

        for (int[] input : inputs) {
            System.out.println(Arrays.toString(rg.replaceElements(input)));
        }
    }
}