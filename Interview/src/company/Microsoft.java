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

	/**
	 * Given a string which contains only chars 'a' & 'b' find the number of moves required to make 
	 * the string valid. A string is said to be valid if it doesnot have more than 2 contiguous chars
	 * 
	 * The idea is to find 3 char which run continuously. For every 3 continuous character we can change  
	 * a single char. 
	 *  
	 * Example:
	 * 		aabbaa -> Valid. Moves = 0
	 * 		aaabb -> Invalid, Moves = 1 <- ababb
	 * 
	 * @param s Given string
	 */
	public void icl(String s) {
		int moves = 0, i;
		
		for(i = 0; i < s.length(); i++) {
			int runner = 1;
			for(; i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1); i++)
				runner++;
			moves += runner / 3;
		}
		System.out.println(moves);
	}
	
	public static void main(String[] args) {
		Microsoft ms = new Microsoft();
		ms.equalDigitSum(new int[] {51, 71, 17, 42, 33, 44, 24, 62}); // 133
		ms.equalDigitSum(new int[] {51, 71, 17, 42}); // 93
		ms.equalDigitSum(new int[] {51, 32, 43}); // -1
		
        ms.icl("baaaaa"); // 1
        ms.icl("baaaaaa"); // 2
        ms.icl("baaaab"); // 1
        ms.icl("baaabbaabbba"); //2
        ms.icl("baabab"); //0
        ms.icl("bbaabbaabbabab"); //0
	}

}
