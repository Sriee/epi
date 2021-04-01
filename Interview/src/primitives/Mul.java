/**
 * 
 */
package primitives;

import java.util.Scanner;

/**
 * @author sriee
 */
public class Mul {

    private static long add(long x, long y) {
        while (y != 0) {
            long carry = x & y;
            x ^= y;
            y = carry << 1;
        }
        return x;
    }

    public static long multiply(long multiplicant, long multiplier) {
        long ans = 0;
        boolean isNegative = (multiplicant < 0) ^ (multiplier < 0);
        long a = Math.abs(multiplicant), b = Math.abs(multiplier);
        // Length of strings
        int bLength = Long.toBinaryString(b).length();

        for (int i = 0; i < bLength; ++i) {
            if ((b & (1 << i)) != 0) {
                // Equivalent to ans += (a << i);
                ans = add(ans, (a << i));
            }
        }

        // Handle negative numbers
        if (isNegative) {
            ans = 0 - ans;
        }

        return ans;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = null;
        long a = -1, b = -1;
        try {
            scan = new Scanner(System.in);
            System.out.print("Enter the multiplicant: ");
            a = scan.nextLong();
            System.out.print("Enter the multiplier: ");
            b = scan.nextLong();
            System.out.println(a + " x " + b + " = " + multiply(a, b));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scan.close();
        }
    }
}
