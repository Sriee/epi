package com.epi.array;

public class ReverseInteger {

    public static void main(String[] args) {
        int number = 1534236469, answer = 0;
        boolean isNegative = (number < 0);
        number = Math.abs(number);
        // if(number == 0 || number < Integer.MIN_VALUE || number > Integer.MAX_VALUE) return 0;

        while(number > 0){
            try {
                answer = (number % 10) + Math.multiplyExact(answer, 10);
                number = number / 10;
            } catch (ArithmeticException e){
                answer = 0;
                break;
            }
        }

        if(isNegative) answer = 0 - answer;

        System.out.println(answer);
    }
}
