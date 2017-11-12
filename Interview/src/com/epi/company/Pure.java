package com.epi.company;

import java.util.*;

public class Pure {

	public int checkLockHistory(String[] events) {
		
		if(events == null || events.length == 0) return 0;
		Set<String> uniq = new HashSet<>();
		Stack<String> stack = new Stack<>();
		
		for(int i = 0; i < events.length; i++) {
			String[] param = events[i].split(" ");
			if(param[0].equals("ACQUIRE")) {
				if(uniq.contains(param[1])) return i + 1;
				
				stack.push(param[1]);
				uniq.add(param[1]);
			} else {
				if(!uniq.contains(param[1]) || !stack.peek().equals(param[1])) return i + 1;
				stack.pop();
			}
		}
		return (stack.isEmpty()) ? 0 : events.length + 1;
	}
	
	/**
	 * Should not use manacher's algorithm. The following solution is a simple version
	 * 
	 * For a string of length N, there will be 2N-1 centers. The elements themselves are centers while
	 * considering odd palindromes. While considering even palindromes the centers are imaginary points in the 
	 * middle of each element (Hence N-1). 
	 * 
	 * We maintain two pointers left & right which progresses away from the center. 
	 * If the elements at left == right
	 * 		decrement left & increment right
	 * 		Increment the count 
	 * 
	 * Run time : O(n^2)  
	 * Space complexity: O(1)
	 * 
	 * @param s String 
	 * @return count of number of palindrome substrings
	 */
	public int countPalindromes(String s) {
		int count = 0;
		if(s == null || s.trim().isEmpty()) return count;
		int left = 0, right = 0, length = (2 * s.length() - 1);
		for(int center = 0; center < length; center++){
			left = center / 2;
			right = left + center % 2;
			while(left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)){
				count++;
				left--;
				right++;
			}
		}
		
		return count; 
	}
	
	public void breakBinarySearch(){
		int left = 0, right =Integer.MAX_VALUE;
		int mid = (left + right + 1) / 2;
		System.out.println(mid);
	}
	
	public static void main(String[] args) {
		Pure p = new Pure();
		p.breakBinarySearch();
	}
}
