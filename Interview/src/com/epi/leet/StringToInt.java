package com.epi.leet;

public class StringToInt {

    private static int stringToInt(String inp){

        if(inp.isEmpty() || inp == null) return 0;
        inp = inp.trim();
        long num = 0;
        int len = inp.length(), literal;
        boolean isNeg = false;

        if(inp.startsWith("+")){
            if(inp.length() == 1 || inp.charAt(1) - '0' < 0 || inp.charAt(1) - '0' > 9) return 0;
        }
        if(inp.startsWith("-")){    // Handle negative number
            if(inp.length() == 1 || inp.charAt(1) - '0' < 0 || inp.charAt(1) - '0' > 9) return 0;
            inp = inp.substring(1);
            isNeg = true;
            len = inp.length();
        }

        for(int i = 0; i < len; i++){
            literal = inp.charAt(i) - '0';
            if(literal < 0 || literal > 9) break;
            num =literal + (num * 10);
        }
        if(isNeg) num = 0 - num;    // Negate the number

        if(num > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if(num < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return (int)num;
    }

    public static void main(String[] args) {
        System.out.println(stringToInt("+"));
        System.out.println(stringToInt("34567890-87654345678"));
    }
}
