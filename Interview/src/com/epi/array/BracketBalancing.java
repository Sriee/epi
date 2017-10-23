package com.epi.array;

import java.util.Stack;

public class BracketBalancing {

	private boolean isBalanced(String input){
		if(input == null || input.isEmpty()) return false;
		Stack<Character> stack = new Stack<>();
		
		for(int i = 0; i < input.length(); i++){
			if(input.charAt(i) == '<')
				stack.push('<');
			else{
				if(stack.isEmpty())
					return false;
				if(stack.peek() != '<')
					return false;
				else
					stack.pop();
			}
		}
		return stack.isEmpty();
	}
	
	private String notBalancedPart(String input){
		if(input == null || input.isEmpty()) return null;
		
		if(input.indexOf('>') == -1) return null;
		
		Stack<Character> stack = new Stack<>();
		
		for(int i = 0; i < input.length(); i++){
			if(input.charAt(i) == '<')
				stack.push('<');
			else{
				if(stack.isEmpty())
					return input.substring(i);
				if(stack.peek() != '<')
					return input.substring(i);
				else
					stack.pop();
			}
		}
		return null;
	}
	
	private int[] balance(String []expressions, int[] maxReplacements){
		int[] result = new int[maxReplacements.length];
		int j = 0, max;
		boolean terminate = false;
		for(int i = 0; i < expressions.length; i++){
			String temp = expressions[i];
			if(isBalanced(temp)){
				result[i] = 1;
			} else {
				max = maxReplacements[i];
				j = 0;
				terminate = false;
				while(j < max && !terminate){
					temp = this.notBalancedPart(temp);
					if(temp == null){
						result[i] = 0;
						terminate = true;
					}
					temp = '<' + temp;
					if(this.isBalanced(temp)){
						result[i] = 1;
						terminate = true;
					}
					j++;
				}
				
				if(!terminate)
					result[i] = 0;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		BracketBalancing b = new BracketBalancing();
		String[] expressions = {"<>>", "<<><>>>"};
		int[] maxReplacements = new int[]{2, 3};
		int[] result = b.balance(expressions, maxReplacements);
		
		for(int i = 0; i < result.length; i++){
			System.out.print(result[i] + " ");
		}
		System.out.println();
	}

}
