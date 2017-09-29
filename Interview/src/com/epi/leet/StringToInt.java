package com.epi.leet;

public class StringToInt {

    private static int atoi(String inp) {
        inp = inp.trim();
        int literal;
        long num = 0, dummy = 0;
        boolean isNegative = false;

        for (int i = 0; i < inp.length(); i++) {
            literal = inp.charAt(i) - '0';

            if (inp.charAt(i) == '+' && i == 0)
                continue;

            if (inp.charAt(i) == '-' && i == 0) {
                isNegative = true;
                continue;
            }

            if (literal < 0 || literal > 9)
                break;

            num = literal + (num * 10);

            if(isNegative) dummy = 0 - num;

            if (dummy <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            if (num >= Integer.MAX_VALUE){
                num = Integer.MAX_VALUE;
                break;
            }
        }

        if (isNegative) num = 0 - num;
        return (int)num;
    }

    public static void main(String[] args) {
        System.out.println(atoi("-2147483649"));
        System.out.println(atoi("2147483647"));
        System.out.println(atoi("-2147483647"));
        System.out.println(atoi("2147483648"));
    }
}
