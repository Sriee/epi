package com.epi.leet;

public class SingleNumber {
    private static int singleNumber(int[] num){
        if(num == null || num.length == 0) return 0;

        for(int i = 1; i < num.length; i++) num[0] ^= num[i];

        return num[0];
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 2, 3};
        System.out.println(singleNumber(null));
        System.out.println(singleNumber(new int[]{}));
        System.out.println(singleNumber(arr));
    }
}
