package com.epi.leet;

public class DigitRoot {

    private static int dr(int num){
        return (1 + (num - 1) % 9);
    }

    public static void main(String[] args) {
        System.out.println(dr(35));
    }
}
