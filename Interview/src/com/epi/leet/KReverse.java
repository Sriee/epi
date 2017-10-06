package com.epi.leet;

import java.util.Arrays;

public class KReverse{
	
	private static int romanToInt(String s){
		int value = 0, i = 0;
		s = s.toUpperCase();
		while(i < s.length()){
			if(s.charAt(i) == 'I'){
				if(i != (s.length() - 1) && s.charAt(i + 1) == 'V'){
				    value += 4;
				    i += 2;
                } else if(i != (s.length() - 1) && s.charAt(i + 1) == 'X' && i != (s.length() - 1)){
				    value += 9;
				    i += 2;
                } else {
                    value += 1;
                    i += 1;
                }
			} else if(s.charAt(i) == 'V'){
				value += 5;
                i += 1;
			} else if(s.charAt(i) == 'X'){
				if(i != (s.length() - 1) && s.charAt(i + 1) == 'L') {
				    value += 40;
                    i += 2;
                } else if(i != (s.length() - 1) && s.charAt(i + 1) == 'C') {
				    value += 90;
                    i += 2;
                } else{
				    value += 10;
                    i += 1;
                }
			} else if(s.charAt(i) == 'L'){
				value += 50;
				i += 1;
			} else if(s.charAt(i) == 'C'){
				if(i != (s.length() - 1) && s.charAt(i + 1) == 'D'){
				    value += 400;
                    i += 2;
                } else if(i != (s.length() - 1) && s.charAt(i + 1) == 'M') {
				    value += 900;
                    i += 2;
                } else {
				    value += 100;
                    i += 1;
                }
			} else if(s.charAt(i) == 'D'){
				value += 500;
                i += 1;
			} else if(s.charAt(i) == 'M'){
				value += 1000;
                i += 1;
			}
        }
		return value;
	}

	private static String base7(int num){
		StringBuilder sb = new StringBuilder();
		boolean isNegative = false;
		if(num < 0){
			isNegative = true;
			num = num * -1; 
		}	
		while(num != 0){
			sb.append(num % 7);
			num = num / 7;
		}
		if(isNegative) sb.append("-");
		return sb.reverse().toString();
	}

	private static int majority(int[] arr){
		Arrays.sort(arr);
		return arr[arr.length / 2];
	}	

	private static String kReverse(String inp, int k){
		int l = 0, idx = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < inp.length(); i++){
			if(l < k){
				sb.insert((2 * k * idx), inp.charAt(i));
			} else {
				sb.append(inp.charAt(i));
			}
			l = (l + 1) % (2 * k);
			if(l == 0) idx++;
		}

		return sb.toString();
	}

	public static void main(String[] args){
		System.out.println("abcdef " + kReverse("abcdef", 2));
		System.out.println("ab " + kReverse("ab", 4));
		System.out.println("Thisisaline " + kReverse("Thisisaline", 1));	
		System.out.println("Thiscouldbeasentenceabcdef " + kReverse("Thiscouldbeasentenceabcdef", 3));
		System.out.println(majority(new int[]{2, 2, 2, 3}));
		System.out.println(romanToInt("xxXvi"));
		System.out.println(romanToInt("MDCCCLXXXIV"));
		System.out.println(romanToInt("MmXII"));
		System.out.println(base7(100));
		System.out.println(base7(-7));
	}
}
