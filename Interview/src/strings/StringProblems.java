package strings;

import java.util.*;


public class StringProblems {

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 23 ms. Below average run time. Bulk of the time is because of 
	 * Hashmap handling. 
	 * 
	 * Given a string s and p, find the indices of all anagrams of p in s. Both
	 * the string consists of only lower case letter.
	 * 
	 * @param s given string
	 * @param p substring whose anagrams to be matched
	 * @return all the indices of anagrams of p in s
	 */
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> result = new ArrayList<>();
		Map<Character, Integer> mem = new HashMap<>();
		
		if(s == null || s.length() == 0)
			return result;
		
		for(char ch : p.toCharArray())
			mem.put(ch, mem.getOrDefault(ch, 0) + 1);
		
		int begin = 0, end = 0, counter = mem.size();
		
		while(end < s.length()) {
			char ech = s.charAt(end);
			
			if(mem.containsKey(ech)) {
				mem.put(ech, mem.get(ech) - 1);
				if(mem.get(ech) == 0)
					counter--;
			}
			end++;
			while(counter == 0) {
				
				if(end - begin == p.length()) {
					result.add(begin);
				}
				
				char bch = s.charAt(begin);
				
				if(mem.containsKey(bch)) {
					mem.put(bch, mem.get(bch) + 1);
					if(mem.get(bch) > 0) {
						counter++;
					}
				}
				
				begin++;
			}
		}
		return result;
	}
	
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
		sp.findAnagrams("abaacbabc", "abc");
		
		System.out.println(ans);
	}
}
