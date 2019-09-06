package company;

import java.util.HashMap;
import java.util.Map;

public class Microsoft {
	
	/**
	 * Calculate digit sum
	 * 
	 * Example:
	 * 	42 -> 6
	 * 
	 * @param digit
	 * @return digit sum
	 */
	public int digitSum(int digit) {
		int ans = 0;
		digit = Math.abs(digit);
		while(digit != 0) {
			ans += digit % 10;
			digit /= 10;
		}
		
		return ans;
	}
	
	/**
	 * Given an array containing integers, find two integers a, b such that sum of digits 
	 * of a and b is equal. Return maximum sum of a and b. Return -1 if no such numbers exist.
	 * 
	 * Example:
	 * 	A: [51, 71, 17, 42, 33, 44, 24, 62]
	 *  {
	 *  	6 => 24, 33, 42, 51,
	 *  	8 => 17, 44, 62, 71 
	 *  }
	 *  
	 *  Maximum sum = 62 + 71 (whose digit sum is 8)
	 *  
	 * @param A
	 */
	public void equalDigitSum(int[] A) {
		Map<Integer, Integer> mem = new HashMap<>();
		int result = -1;
		
		for(int i : A) {
			int d = digitSum(i);
			
			if(mem.containsKey(d)) {
				result = Math.max(result, mem.get(d) + i);
				mem.put(d, Math.max(i, mem.get(d)));
			} else {
				mem.put(d, i);
			}
			
		}
		System.out.println(result);
	}

	public static void main(String[] args) {
		Microsoft ms = new Microsoft();
		ms.equalDigitSum(new int[] {51, 71, 17, 42, 33, 44, 24, 62}); // 133
		ms.equalDigitSum(new int[] {51, 71, 17, 42}); // 93
		ms.equalDigitSum(new int[] {51, 32, 43}); // -1
	}

}
