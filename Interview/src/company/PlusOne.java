package company;

import java.util.Arrays;

public class PlusOne {

    public int[] plusOne(int[] digits) {
        int[] result = new int[digits.length];
        int len = digits.length - 1, carry = 0, sum = 0;
        while(len >= 0){
            sum = (len == digits.length - 1) ? digits[len] + carry + 1 : digits[len] + carry;
            if(sum >= 10){
                sum = 10 - sum;
                carry = 1;
            } else {
            	carry = 0;
            }
            result[len] = sum;
            len--;
        }
        
        if(carry == 1){
            int[] newResult = new int[digits.length + 1];
            newResult[0] = carry;
            System.arraycopy(result, 0, newResult, 1, digits.length);
            return newResult;
        }
        return result;
    }
    
	public static void main(String[] args) {
		PlusOne po = new PlusOne();
		System.out.println(Arrays.toString(po.plusOne(new int[]{3, 8, 9})));
	}

}
