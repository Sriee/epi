package strings;

import java.util.Map;
import java.util.HashMap;


public class StringProblems {

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 19 ms. Below average run time. Bulk of the timing is going for accessing the map collection
	 * 
	 * Given a string 's' and a substring 't' find the minimum window of 't' in 's'. 't' can be in any combination
	 * in s. The string only contains letters (both upper & lower case) 
	 *  
	 * Refer Reference/Sliding_Window.pdf for explanation.  
	 * 
	 * @param s given string
	 * @param t substring
	 * @return substring with minium window
	 */
	public String minWindow(String s, String t) {
		Map<Character, Integer> mem = new HashMap<>();
		String result = "";
		
		if(s == null || s.length() == 0)
			return result;
		
		for(char ch : t.toCharArray())
			mem.put(ch, mem.getOrDefault(ch, 0) + 1);
		
		int begin = 0, end = 0, length = Integer.MAX_VALUE, counter = mem.size();
		
		while(end < s.length()) {
			char ch = s.charAt(end);
			
			if(mem.containsKey(ch)) {
				mem.put(ch, mem.get(ch) - 1);
				if(mem.get(ch) == 0)
					counter--;
			}
			
			end++;
			
			while(counter == 0) {
				
				if(end - begin < length) {
					length = end - begin;
					result = s.substring(begin, end);
				}
				
				// Trick: incrementing the table value as we slide right
				// This trick avoids intializing counters for a window again !
				char bc = s.charAt(begin);
				if(mem.containsKey(bc)) {
					mem.put(bc, mem.get(bc) + 1);
					if(mem.get(bc) > 0)
						counter++;
				}
				
				begin++;
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		StringProblems sp = new StringProblems();
		String ans = sp.minWindow("fgiowblkjadgfladfbmnyuhijo", "ibn");
		System.out.println(ans);
	}
}
