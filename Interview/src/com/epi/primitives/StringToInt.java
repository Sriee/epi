package com.epi.primitives;

import java.util.Scanner;

public class StringToInt {

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 0 ms. Optimal Solution
	 * 
	 * Given a string reverse only letters. Non letters should stay in the same position
	 * 
	 * Example:
	 * 	a-b-c -> c-b-a
	 * 
	 * @param S Given string
	 */
	private void reverseOnlyLetters(String S) {
		char[] result = new char[S.length()];
		int i =0, j = 0; 
		
		for(; i < S.length(); i++) {
			char ch = S.charAt(i);
			if((ch - 'a' >= 0 && ch - 'a' < 26) || (ch - 'A' >= 0 && ch - 'A' < 26))
				continue;
			result[i] = ch;
		}
		
		for(i = i - 1; i >= 0; i--) {
			char ch = S.charAt(i);
			if(result[j] != 0) {
				i++;
				j++;
			} else if(ch - 'a' >=0 && ch - 'a' < 26)
				result[j++] = ch;
			else if(ch - 'A' >=0 && ch - 'A' < 26)
				result[j++] = ch;
		}
		System.out.println(new String(result));
	}
	
	public long stringToInt(String inp){
		if(inp.isEmpty() || inp == null) throw new NullPointerException();
		long num = 0;
		int len = inp.length(), literal = -1;
		boolean isNeg = false;
		
		// Handle negative number
		if(inp.startsWith("-")){
			inp = inp.substring(1);
			isNeg = true;
			len = inp.length();
		}
		
		for(int i = 0, pos = (len - 1); i < len; i++, pos--){
			literal = getNumForChar(inp.charAt(i));
			
			if(literal == -1) throw new NumberFormatException();
			
			num +=literal * (int)Math.pow(10, pos);
		}
		
		//Negate the number
		if(isNeg)
			num = 0 - num;
		
		return num;
	}
	
	/**
	 * Gives integer values for characters
	 * @param ch
	 * @return
	 */
	private int getNumForChar(char ch){
		int val = -1;
		switch(ch){
		case '0': 
			val = 0;
			break;
		case '1': 
			val = 1;
			break;
		case '2': 
			val = 2;
			break;
		case '3': 
			val = 3;
			break;
		case '4': 
			val = 4;
			break;
		case '5': 
			val = 5;
			break;
		case '6': 
			val = 6;
			break;
		case '7': 
			val = 7;
			break;
		case '8': 
			val = 8;
			break;
		case '9': 
			val = 9;
			break;
		}
		return val;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String inp = null;
		StringToInt toInt = null;
		try{
			inp = scan.next();
			toInt = new StringToInt();
			System.out.println("Integer : " + toInt.stringToInt(inp));
			
			toInt.reverseOnlyLetters("a-B-Dc");
			toInt.reverseOnlyLetters("a-bC-dEf-ghIj");
			toInt.reverseOnlyLetters("Test1ng-Leet=code-Q!");

		} catch(Exception e){
			e.printStackTrace();	
		} finally {
			scan.close();
		}
	}

}
