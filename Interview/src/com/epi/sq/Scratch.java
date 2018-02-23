package com.epi.sq;

import java.util.Stack;

public class Scratch {

	private String decode(String encode){
		Stack<String> strStack = new Stack<>();
		Stack<Integer> valueStack = new Stack<>();

		for(int i = 0; i < encode.length(); i++){
			String ch = encode.substring(i, i + 1);
			if(ch == '['){
				strStack.push(ch);
			} else if(ch.equals("]"){
				String token = strStack.pop();
				strStack.pop();
				int count = valueStack.pop(), idx = 0;
				StringBuilder sb = new StringBuilder();
				while(idx < count){
					idx++;
					sb.append(token);
				}	
				strStack.push(sb.toString());
			} else if(ch >= '0' && ch <= '9'){
				valueStack.push(Integer.parseInt(ch));
			} else {
				if(strStack.peek() != '['){
					strStack.push(strStack.pop() + ch);
				} else {
					strStack.push(ch);	
				}
			}
		}
	}
	
	public static void main(String[] args){
		Scratch s = new Scratch();
		System.out.println(s.duplicate(5, "a"));
	}
}
