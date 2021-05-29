package array;

import java.util.Arrays;

class _1051_HeightChecker {

    public int heightChecker(int[] heights) {
        int n = heights.length, diff = 0;
        int[] expected = new int[n];

        System.arraycopy(heights, 0, expected, 0, n);
        Arrays.sort(expected);

        for (int i = 0; i < n; i++) {
            if (heights[i] != expected[i])
                diff++;
        }

        return diff;
    }

    public static void main(String[] args) {
        _1051_HeightChecker hc = new _1051_HeightChecker();
        int[][] inputs = {
                {1, 1, 4, 2, 1, 3},
                {5, 1, 2, 3, 4},
                {1, 2, 3, 4, 5}
        };

        for (int[] input : inputs) {
            System.out.println(hc.heightChecker(input));
        }
    }
}