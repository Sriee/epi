package com.epi.leet;

public class Flip {

    public static int flipBit(int number){
        if(number == 1) return 0;
        if(number == 0 || number == 2) return 1;

        int mask = 2;

        while(mask < number){
            mask = mask << 1;
        }

        mask -= 1;
        return (mask ^ number);
    }

    public static void main(String[] args) {
        System.out.println(flipBit(2017));
    }
}
