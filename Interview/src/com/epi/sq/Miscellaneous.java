package com.epi.sq;

import java.util.Arrays;
import java.util.Stack;

public class Miscellaneous {
	
	/**
	 * Leet code problem. Solution -> Accepted
	 * 
	 * We are given an array asteroids of integers representing asteroids in a row.
	 *  
	 * For each asteroid, the absolute value represents its size, and the sign represents its direction
	 * (+ meaning right, - meaning left).
	 * 
	 * Conditions:
	 * 	1. If two asteroids meet, the smaller one will explode.
	 *  2. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet. 
	 *  
	 * @param asteroids array asteroids of integers representing asteroids in a row
	 * @return state of the asteroids after all collisions
	 */
	public int[] steroidCollision(int[] asteroids){
		if(asteroids == null || asteroids.length == 0) return asteroids;
		
		int[] stack = new int[asteroids.length];
		int top = -1;
		boolean done = false;
		
		for(int i = 0; i < asteroids.length; i++){
			stack[++top] = asteroids[i]; 
			done = false;
			while(top > 0 && !done){
				if(stack[top - 1] > 0 && stack[top] < 0){
					if(Math.abs(stack[top]) == Math.abs(stack[top - 1]))
						top -= 2;
					else{
                        stack[top - 1] = (Math.abs(stack[top]) > Math.abs(stack[top - 1]) ? stack[top] : stack[top - 1]);
                        top--;                        
                    }
				} else {
					done = true;
				}
			}	
		}
		
		return Arrays.copyOf(stack, top + 1);
	}
	
	/**
	 * Stack version for the above. 
	 * 
	 * @param asteroids array asteroids of integers representing asteroids in a row
	 * @return state of the asteroids after all collisions
	 */
	public int[] asteroidCollision(int[] asteroids){
		if(asteroids == null || asteroids.length == 0) return asteroids;
		
		Stack<Integer> stack = new Stack<>();
		boolean done = false;
		
		for(int i = 0; i < asteroids.length; i++){
			stack.push(asteroids[i]);
			done = false;
			while(!stack.isEmpty() && stack.size() > 1 && !done){
				int a = stack.pop();
				int b = stack.pop();
				if(b > 0 && a < 0){
					if(Math.abs(a) == Math.abs(b))
						continue;
					else
						stack.push((Math.abs(a) > Math.abs(b) ? a : b));
				} else {
					done = true;
					stack.push(b);
					stack.push(a);
				}
			}	
		}
		
		int[] result = new int[stack.size()];
		int idx = stack.size() - 1;
		while(!stack.isEmpty())
			result[idx--] = stack.pop();
		
		return result;
	}
	
	/**
	 * Leet code problem. Solution -> Accepted
	 * 
	 * Reverse Polish Notation (Array Implementation)
	 * 
	 * @param expression arithmetic expression
	 * @return evaluated result
	 */
	public int evalRPN(String[] expression){
		if(expression == null || expression.length == 0)
			return 0;
		
		int[] stack = new int[expression.length];
		int top = -1;
		for(String token : expression){
			if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
				switch(token.charAt(0)){
				case '+':
					stack[top - 1] = stack[top - 1] + stack[top]; 
					break;
				case '-':
					stack[top - 1] = stack[top - 1] - stack[top];
					break;
				case '*':
					stack[top - 1] = stack[top - 1] * stack[top];
					break;
				case '/':
					stack[top - 1] = stack[top - 1] / stack[top];
					break;
				}
				top--;
			} else {
				stack[++top] = Integer.parseInt(token);
			}
		}
		
		return stack[0];
	}

	public static void main(String[] args) {
		Miscellaneous misc = new Miscellaneous();
		
		String[][] expressions = new String[][]{
			null,
			{},
			{"2", "1", "+", "3", "*"},
			{"21", "54", "*"},
			{"7682"},
			{"4", "13", "5", "/", "+"},
			{"4", "13", "-", "12", "79", "+", "-"}
		};
		
		for(String[] token : expressions){
			System.out.println(misc.evalRPN(token));
		}
	}
}
