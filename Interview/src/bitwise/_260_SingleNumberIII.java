package bitwise;

import java.util.Arrays;

public class _260_SingleNumberIII {

    /**
     * TC: O(n)
     * SC: O(1)
     */
    public static int[] twoSingleNumbers(int[] nums) {
        int bitmaskSum = 0, results = 0;

        /*
         * For nums = [1, 2, 3, 2, 5, 1]. After the end the below loop, bitmaskSum will be 6 (3 ^ 5)
         */
        for (int num : nums) {
            bitmaskSum ^= num;
        }

        /*
         * Now we need to find the first set bit.
         * 3 = 0 1 1
         * 5 = 1 0 1
         * 6 = 1 1 0
         *
         * Our bitmask should be 010. By creating the bitmask we will split the input array into two groups. In which
         * each group will have the single number.
         *
         * For nums = [1, 2, 3, 2, 5, 1], we will have [2, 2, 3] and [1, 1, 5]. At the end of the below loop we will have
         * results = 3.
         *
         * How to compute this 010 bitmask?
         * bitmaskSum = 6                            = 0 0 0 0 0 0 1 1 0 <- 8-bit representation.
         * -bitmaskSum will give 2's complement of 6 = 1 1 1 1 1 1 0 1 0
         *                                   bitmask = 0 0 0 0 0 0 0 1 0
         */
        int bitmask = bitmaskSum & (-bitmaskSum);

        for (int num : nums) {
            if ((num & bitmask) != 0)
                results ^= num;
        }

        return new int[] {results, bitmaskSum ^ results};
    }


    public static void main(String[] args) {
        int[][] arr = {
            {1, 2, 2, 3},
            {4, 4, 3, 2, 3, 5},
            {1, 1, 7, 4, 5, 5, 8, 8},
            {1, 0},
            {9, 8, 8, 7, 6, 6, 4, 4}
        };

        for (int i = 0; i < arr.length; i++) {
            int[] result = twoSingleNumbers(arr[i]);
            System.out.print(i + 1 + ".\tInput list:" + Arrays.toString(arr[i]));
            System.out.print("\n\tTwo Singles numbers in a list: " + Arrays.toString(result) + "\n");
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
