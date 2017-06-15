package com.epi.primitives;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class PowerSet {

	private String input = null;
	
	public PowerSet(String input){
		// Input Validation
		if(input == null) throw new NullPointerException();
		
		if(input.isEmpty()) throw new NullPointerException("Empty string is not allowed");

		this.input = input;
	}
	
	
	/**
	 * @param input the input to set
	 */
	public void setInput(String input) {
		this.input = input;
	}


	public LinkedList<String> powerSet(){
		LinkedList<String> set = new LinkedList<>();
		StringBuilder sb = null;
		Double d = Math.pow(2, this.input.length());
		int i = -1, j = -1, len = d.intValue(), pos = 0;
		
		// Appending Null set
		set.add(null);
		
		for(i = 1; i < len; i++){
			sb = new StringBuilder();
			pos = 0;
			j = i;
			
			while(j != 0){
				if((j & 1) == 1){
					sb.append(this.input.charAt(pos));
				}
				pos += 1;
				j = j >> 1;
			}
			set.add(sb.toString());
		}
		
		return set;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PowerSet set = null;
		String inp = null;

		try{
			inp = scan.nextLine();
			inp = inp.trim();
			inp = inp.replaceAll(" ", "");
			
			System.out.println(inp);
			set = new PowerSet(inp);
			System.out.println("Power Set: " + set.powerSet());
			
		} catch (InputMismatchException e){
			System.out.println("Invalid input.");
			e.printStackTrace();
		} catch (NullPointerException n){
			n.printStackTrace();
		} finally {
			scan.close();
		}
	}

}
