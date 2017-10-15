package com.epi.leet;

public class Flip {

    public static int flipBit(int number){
        if(number == 1) return 0;
        if(number == 0 || number == 2) return 1;

        int mask = 2;

        while(mask <= number){
            mask = mask << 1;            
            if(mask <= 0) break;
        }
        
        System.out.println(mask);
        mask -= 1;
        System.out.println(mask);
        return (mask ^ number);
    }

    /**
     * Decimal to Hexadecimal conversion 
     * 
     * Handles negative number also
     * 
     * @param num
     */
    private static void decimalToHex(int num) {
    	StringBuilder sb = new StringBuilder();
    	char literals[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    	while(num != 0) {
    		sb.append(literals[num & 15]);
    		num = num >>> 4;
    	}
    	System.out.println(sb.reverse().toString());
    }
    public static void main(String[] args) {
        System.out.println(flipBit(2147483647));
        decimalToHex(-2);
    }
}
