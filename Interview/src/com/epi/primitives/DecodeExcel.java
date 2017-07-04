package com.epi.primitives;

import java.util.Scanner;

public class DecodeExcel {

	public int ssDecodeColId(String input){
		int result = 0;
		
		// Question asked to skip A-Z check
		// Skip that validation
		if(input == null || input.isEmpty()){
			System.out.println("Input string should be a valid column ID.");
			return -1;
		}
		
		// Restricting the limit
		if(input.length() > 6){
			System.out.println("Allowed range [A - ZZZZZZ]");
			return -1;
		}
		
		input = input.toUpperCase();
		char[] inputChar = input.toCharArray();
		
		for(char ch: inputChar){
			result = result * 26 + ch - 'A' + 1;
		}
		return result;
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String inp = null;
		DecodeExcel decode = new DecodeExcel();
		try{
			System.out.print("Enter the colum ID: ");
			inp = scan.nextLine();
			inp = inp.trim();
			System.out.println(decode.ssDecodeColId(inp));
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			scan.close();
		}
	}

}
