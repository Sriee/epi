package com.epi.leet;

public class DigitRoot {

    private static int dr(int num){
        return (1 + (num - 1) % 9);
    }

    private static int add(int a, int b){
        int carry = 0;
        return sum(a, b, carry);
    }

    private static int sum(int a, int b, int carry){
        if(a == 0 && b == 0)
            return carry;

        int sum = carry ^ (a & 1) ^ (b & 1);
        int c = ((carry & (b & 1)) | ((a & 1) & (b & 1)) | (carry & (a & 1)));
        // System.out.println(sum + " " + c);
        return (sum(a >> 1, b >> 1, c) << 1) | sum;
    }

    public static void main(String[] args) {
        System.out.println(dr(35));
        System.out.println(add(226,4567));
    }
}
