package strings;

import java.util.*;


public class StringProblems {

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 6 ms. Optimal run time
	 * 
	 * Given a string s and p, find the indices of all anagrams of p in s. Both
	 * the string consists of only lower case letter.
	 * 
	 * @param s given string
	 * @param p substring whose anagrams to be matched
	 * @return all the indices of anagrams of p in s
	 */
	public List<Integer> findAnagrams(String s, String p) {
		// Use ArrayList instead of LinkedList
		List<Integer> result = new ArrayList<>();
		
		// Trick of using 256 int array instead of 26 int array
		int[] table = new int[256]; 
		int counter = 0, length = 0, begin = 0;
		
		for(char ch : p.toCharArray()) {
			table[ch]++;
			counter++;
			
			// Using length instead of p.length() lookup reduces the run time by 3 ms
			length++;	
		}
		
		char[] inp = s.toCharArray();
		for(int end = 0; end < s.length(); end++) {
			if(table[inp[end]]-- > 0)
				counter--;
			
			while(counter == 0) {
				if(end - begin + 1 == length)
					result.add(begin);
				
				if(table[inp[begin++]]++ == 0)
					counter++;
			}
		}
		
		return result;
	}
	
	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 2 ms. Optimal run time.  
	 * 
	 * Given a string, find the length of the longest substring without repeating characters.
	 * 
	 * @param s Given string
	 * @return length of the longest substring
	 */
	public int lengthOfLongestSubstring(String s) {
		int len = 0, begin = 0, end = 0;
		int[] table = new int[256];
		
		while(end < s.length()) {
			char ch = s.charAt(end); 
			table[ch]++;
			
			if(table[ch] > 1 && end - begin > len) {
				len = end - begin;
			}
			
			while(table[ch] > 1) {
				table[ s.charAt(begin++) ]--;
			} 
			
			end++; 
		}
		
		if(end - begin > len)
			len = end - begin;
		
		return len;
	}
	
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		int len = 0, end = 0, begin = 0, counter = 0;
		int[] table = new int[256];
		
		while(end < s.length()) {
			char ech = s.charAt(end);
			table[ech]++;
			
			if(table[ech] == 1)
				counter++;
			
			end++;
			
			while(counter > 2) {
				char bch = s.charAt(begin);
				
				table[bch]--;
				if(table[bch] == 0)
					counter--;
				begin++;
			}
			
			if(end - begin > len)
				len = end - begin;
		}
		
		return len;
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
		
		System.out.println(sp.lengthOfLongestSubstring("pwwkew"));
	}
}
