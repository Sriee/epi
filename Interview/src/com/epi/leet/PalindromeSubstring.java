package com.epi.leet;

import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

public class PalindromeSubstring {

	/**
	 * Refer ../../../../docs/Manachers_Algorithm for details of the algorithm
	 * and implementation. 
	 */
	private void manachersAlgorithm(String input){
		input = this.preProcessor(input);
		int[] p = new int[input.length()];
		int mirror = 0, r = 0, c = 0, max = 0;
		String longest = "";
		for(int i = 1; i < input.length()-1; i++){
			mirror = 2*c-i;
			
			p[i] = (r > p[i]) ? Math.min(r- i, p[mirror]) : 0;
			while(input.charAt(i + 1 + p[i]) == input.charAt(i - 1 - p[i])) p[i]++;
		
			if(i + p[i] > r){
				c = i;
				r = c + p[i];
			}
		}
		
		for(int j = 0; j < p.length;j++){
			if(p[j] > max){
				max = p[j];
				c = j;
			}
		}
		
		System.out.println(Arrays.toString(p));
		System.out.println(max + " " + c);
		longest = input.substring(c - p[c], c + p[c] + 1);
		longest = longest.replaceAll("#", "");
		
		if(longest.indexOf('^') != -1)
			longest = longest.replaceAll("^", "");
		
		if(longest.indexOf('$') != -1)
			longest = longest.replaceAll("$", "");
		
		System.out.println("Longest palindrome substring " + longest);
	}
	
	private String preProcessor(String input){
		StringBuilder sb = new StringBuilder();
		// Adding '^' at the begining and '?' at the end for avoiding
		// boundary checks
		sb.append("?#");
		for(int i = 0; i < input.length(); i++){
			sb.append(input.substring(i, i + 1));
			sb.append("#");
		}
		sb.append("$");
		return sb.toString();
	}
	
	private void bruteForce(String input){
		if(input == null || input.trim().isEmpty()) return;
		String longest = "";
		Set<String> uniquePalindrome = new HashSet<>();
		
		for(int i = 0; i < input.length(); i++){
			for(int j = i + 1; j < input.length(); j++){
				String sub = input.substring(i, j + 1);
				 if(this.isPalindrome(sub)){
					System.out.println(sub);
					uniquePalindrome.add(sub);
					if(sub.length() > longest.length()){
						longest = sub;
					}
				 }
			}
		}
		System.out.println("Longest Palindrome: " + longest);
		System.out.println("Number of Palindrome: " + uniquePalindrome.size());
	}
	
	private boolean isPalindrome(String literal){
		int i = 0, j = literal.length() - 1;
		if(i == j) return false;
		while(i < j){
			if(literal.charAt(i) != literal.charAt(j)) return false;
			i++;
			j--;
		}
		return true;
	}
	
	private void dpSolution1(String input){
		boolean table[][] = new boolean[input.length()][input.length()];
		int start = 0, maxLength = 0;
		Set<String> uniquePalindrome = new HashSet<>();
		for(int i = 0; i < input.length(); i++){
			table[i][i] = true;
		}		
		
		for(int j = 0; j < input.length()-1; j++){
			if(input.charAt(j) == input.charAt(j + 1)){
				table[j][j+1] = true;
				start = j;
				maxLength = 2;
				uniquePalindrome.add(input.substring(j, j + 2));
			}
		}
		
		for(int k = 3; k <= input.length(); k++){
			for(int i = 0; i < input.length() - k + 1; i++){
				int j = k + i - 1;
				if(table[i + 1][j - 1] && (input.charAt(i) == input.charAt(j))){
					table[i][j] = true;
					start = i;
					uniquePalindrome.add(input.substring(i, j + 1));
					if(k > maxLength){
						maxLength = k;
					}
				}
			}
		}
		
		for(String item : uniquePalindrome)
			System.out.println(item);
		
		System.out.println("Longest Palindrome: " + input.substring(start, start + maxLength));
		System.out.println("Number of Palindrome: " + uniquePalindrome.size());
	}
	
	public static void main(String[] args) {
		PalindromeSubstring ps = new PalindromeSubstring();
		String s = "abaab";
		ps.bruteForce(s);
		
		System.out.println("\nDP Solution");
		ps.dpSolution1(s);
		
		ps.manachersAlgorithm(s);
		ps.manachersAlgorithm("babcbabcbaccba");
		ps.manachersAlgorithm("");
	}

}
