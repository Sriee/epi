package array;

import java.util.Arrays;

/**
 * Asked in Microsoft OA 2019
 */
public class _1304_UniqueN {
    public int[] sumZero(int n) {
        int[] arr = new int[n];
        int toFill, end = n / 2, i = 0;

        // Fill negative values
        toFill = -n;
        while(i < end) arr[i++] = toFill++;

        if(n % 2 != 0) i++;

        // Fill positive values
        toFill = -toFill;
        while(i < n) arr[i++] = ++toFill;

        return arr;
    }

    private void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        _1304_UniqueN un = new _1304_UniqueN();
        un.print(un.sumZero(3));
        un.print(un.sumZero(5));
        un.print(un.sumZero(10));
    }
}
