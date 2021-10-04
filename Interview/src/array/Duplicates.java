package array;

import java.util.Arrays;

public class Duplicates {

    /**
     * Leet code. Solution -> Accepted Run Time: 1 ms. Optimal Solution Given an
     * array with zeroes. Duplicate one more zero next to it Example: [1, 0, 2, 3,
     * 0, 4, 5, 0] -> [1, 0, 0, 2, 3, 0, 0, 4] [1, 2, 3] -> [1, 2, 3]
     * 
     * @param arr
     */
    public void duplicateZeroes(int[] arr) {
        int i = 0, toShift = 0;

        // Look at the trick of adding toShift to condition check of for loop
        // In the example: [1, 0, 2, 3] we need to shift 1 to the right,
        // by adding toShift value to i we are ignoring 3, which we should
        for (i = 0; i + toShift < arr.length; i++)
            if (arr[i] == 0)
                toShift++;

        // Trick of using i instead of assigning i = arr.length - 1
        for (i = i - 1; toShift > 0; i--) {
            // We start filling the element with the shift. Note here if arr[i] == 0
            // We will additional 0 at the i + shift position
            if (i + toShift < arr.length)
                arr[i + toShift] = arr[i];

            // If the element is 0 fill again. Trick of decrementing and using toShift in
            // a single line
            if (arr[i] == 0)
                arr[i + --toShift] = arr[i];
        }

        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        Duplicates dup = new Duplicates();
        dup.duplicateZeroes(new int[] { 1, 0, 2, 3, 0, 4, 5, 0 });
    }
}
