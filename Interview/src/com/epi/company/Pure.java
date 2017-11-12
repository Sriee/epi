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
	
	private String preProcess(String input) {
		StringBuilder sb = new StringBuilder();
		sb.append("^#");
		for(int i = 0; i < input.length(); i++) {
			sb.append(input.charAt(i));
			sb.append("#");
		}
		sb.append("?");
		return sb.toString();
	}
	
	public int countPalindromes(String input) {
		if(input == null || input.trim().isEmpty()) return 0;
		
		String s = this.preProcess(input), temp = null;
		int p[] = new int[s.length()];
		int count = 0, c = 0, r = 0, i;
		
		for(i = 1; i < s.length() - 1; i++) {
			
			// p[i] = (r > p[i]) ? Math.min(r- i, p[2*c - i]) : 0;
			
			while(s.charAt(i + 1 + p[i]) == s.charAt(i - 1 - p[i])) {
				p[i]++;
				if(p[i] > 2 && s.charAt(i) != '#') {
					/*
					if(s.charAt(i) == '#') {
						temp = s.substring(i - p[i] + 1, i + p[i]);
					} else {
						temp = s.substring(i - p[i], i + 1 + p[i]);	
					}
					*/
					temp = s.substring(i - p[i], i + 1 + p[i]);
					temp = temp.replaceAll("#", "");
					count++;
					System.out.println(temp);
				} else if (p[i] > 1 && s.charAt(i) == '#') {
					temp = s.substring(i - p[i] + 1, i + p[i]);
				}
			}
			
			if(i + p[i] > r) {
				c = i;
				r = i + p[i];
			}
			System.out.println(i + " " + Arrays.toString(p));
		}
		
		return count + input.length();
	}
	
	public static void main(String[] args) {
		Pure p = new Pure();
		int cnt = p.countPalindromes("aaaa");
		System.out.println(cnt);
	}
}
