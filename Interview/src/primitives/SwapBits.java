package primitives;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SwapBits {

    /**
     * Swap bits for the given number
     * 
     * @param value - The number
     * @param i     - ith index
     * @param j     - jth index
     * @return result - The number in decimal after the bits are swapped
     */
    public int swapBits(int value, final int i, final int j) {
        int result = -1;
        // Input validation
        if (value < 0 || i < 0 || j < 0)
            throw new IllegalArgumentException("Invalid inputs.");

        if (i > 63 || j > 63)
            throw new IllegalArgumentException("Indices should be within [0-63]");

        // If both indices are same. No swapping is required
        if (i == j)
            return value;

        // If both the bits are same. No swapping is required
        if (((value >> i) & 1) == ((value >> j) & 1))
            return value;

        if (((value >> i) & 1) == 1) {
            result = value & (~(1 << i));
            result |= (1 << j);
        } else {
            result = value & (~(1 << j));
            result |= (1 << i);
        }

        return result;
    }

    /**
     * Driver program
     * 
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int number = -1, i = -1, j = -1;
        SwapBits sb = null;
        try {
            System.out.print("Enter number: ");
            number = scan.nextInt();
            System.out.println("(i,j)th index: ");
            i = scan.nextInt();
            j = scan.nextInt();

            sb = new SwapBits();
            System.out.println("Number after swapping: " + sb.swapBits(number, i, j));
        } catch (InputMismatchException e) {
            System.out.println("Integer and positive value are allowed.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            scan.close();
        }

    }

}
