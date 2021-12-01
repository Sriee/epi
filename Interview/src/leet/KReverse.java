package leet;

import java.util.Arrays;

public class KReverse {

    private static int romanToInt(String s) {
        int value = 0, i = 0;
        s = s.toUpperCase();
        while (i < s.length()) {
            if (s.charAt(i) == 'I') {
                if (i != (s.length() - 1) && s.charAt(i + 1) == 'V') {
                    value += 4;
                    i += 2;
                } else if (i != (s.length() - 1) && s.charAt(i + 1) == 'X' && i != (s.length() - 1)) {
                    value += 9;
                    i += 2;
                } else {
                    value += 1;
                    i += 1;
                }
            } else if (s.charAt(i) == 'V') {
                value += 5;
                i += 1;
            } else if (s.charAt(i) == 'X') {
                if (i != (s.length() - 1) && s.charAt(i + 1) == 'L') {
                    value += 40;
                    i += 2;
                } else if (i != (s.length() - 1) && s.charAt(i + 1) == 'C') {
                    value += 90;
                    i += 2;
                } else {
                    value += 10;
                    i += 1;
                }
            } else if (s.charAt(i) == 'L') {
                value += 50;
                i += 1;
            } else if (s.charAt(i) == 'C') {
                if (i != (s.length() - 1) && s.charAt(i + 1) == 'D') {
                    value += 400;
                    i += 2;
                } else if (i != (s.length() - 1) && s.charAt(i + 1) == 'M') {
                    value += 900;
                    i += 2;
                } else {
                    value += 100;
                    i += 1;
                }
            } else if (s.charAt(i) == 'D') {
                value += 500;
                i += 1;
            } else if (s.charAt(i) == 'M') {
                value += 1000;
                i += 1;
            }
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("xxXvi"));
        System.out.println(romanToInt("MDCCCLXXXIV"));
        System.out.println(romanToInt("MmXII"));
    }
}
