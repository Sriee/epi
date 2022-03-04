/**
 * 
 */
package primitives;

import java.util.Scanner;

/**
 * @author sriee
 */
public class BaseConversion {

    public String baseConversion(int base1, String input, int base2) {

        // Input validation
        if (base1 < 1 || base2 < 1 || base1 > 16 || base2 > 16) {
            System.out.println("Bases should be between 2 and 16");
            return null;
        }

        if (input == null || input.isEmpty()) {
            System.out.println("Input should not be null/empty.");
            return null;
        }

        char[] inputChar = null;
        boolean isNeg = false;
        String result = null;
        int len, index = 0, dec = 0;

        if (input.charAt(0) == '-') {
            isNeg = true;
            inputChar = input.substring(1).toCharArray();
        } else {
            inputChar = input.toCharArray();
        }

        // Prevents "-" input
        if (inputChar.length == 0)
            return null;

        len = inputChar.length - 1;

        // Convert from base 1 to decimal
        if (base1 != 10) {
            while (len >= 0) {
                if (Character.isLetter(inputChar[len])) {
                    if (this.getValue(Character.toLowerCase(inputChar[len])) == -1)
                        return null;

                    dec += this.getValue(Character.toLowerCase(inputChar[len]))
                            * (Double.valueOf(Math.pow(base1, index)).intValue());
                } else {
                    dec += Character.getNumericValue(inputChar[len]) * (Double.valueOf(Math.pow(base1, index)).intValue());
                }
                len--;
                index++;
            }
        } else {
            dec = Integer.parseInt(new String(inputChar));
        }

        // Convert from decimal to base 2
        result = "";
        while (dec > 0) {
            if (dec % base2 > 9)
                result = this.getString(dec % base2) + result;
            else
                result = Integer.toString(dec % base2) + result;

            dec /= base2;
        }

        if (isNeg)
            result = "-" + result;
        return result;
    }

    private int getValue(char ch) {
        int value = -1;
        switch (ch) {
        case 'a':
            value = 10;
            break;
        case 'b':
            value = 11;
            break;
        case 'c':
            value = 12;
            break;
        case 'd':
            value = 13;
            break;
        case 'e':
            value = 14;
            break;
        case 'f':
            value = 15;
            break;
        default:
            value = -1;
            break;
        }
        return value;
    }

    private String getString(int value) {
        String str = null;
        switch (value) {
        case 10:
            str = "A";
            break;
        case 11:
            str = "B";
            break;
        case 12:
            str = "C";
            break;
        case 13:
            str = "D";
            break;
        case 14:
            str = "E";
            break;
        case 15:
            str = "F";
            break;
        }
        return str;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BaseConversion bc = null;
        String input = null;
        String[] tokens = null;
        int base1 = -1, base2 = -1;
        try {
            System.out.println("Format of input: Number base1 base2");
            input = scan.nextLine();
            tokens = input.split(" ");
            input = tokens[0];
            base1 = Integer.parseInt(tokens[1]);
            base2 = Integer.parseInt(tokens[2]);

            bc = new BaseConversion();
            System.out.println(bc.baseConversion(base1, input, base2));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scan.close();
        }
    }

}
