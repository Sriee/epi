package com.epi.sq;

import java.util.Arrays;
import java.util.Stack;
import java.util.List;
import java.util.LinkedList;

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

	/**
	 * Leet code problem. Solution -> Accepted.
	 * 
	 * @param n number of process ids
	 * @param logs. List of logs
	 * @return execution time of functions
	 */
	public int[] exclusiveTime(int n, List<String> logs) {
		int[] result = new int[n];
		int[][] stack = new int[logs.size()][4];
		int top = -1;
		for(String entry : logs){
			String[] stringTokens = entry.split(":");
			
			stack[++top] = new int[]{
					Integer.parseInt(stringTokens[0]),
					(stringTokens[1].equals("start")) ? 1 : 0,
					Integer.parseInt(stringTokens[2]),
					0
					};
			
			while(top > 0 && stack[top][1] != 1){
				int[] b = stack[top];
				int[] a = stack[top - 1];
				top -= 2;
				if(top > -1)
					stack[top][3] = stack[top][3] + (b[2] - a[2]) + 1;
				
				result[a[0]] = result[a[0]] + (b[2] - a[2]) + 1 - a[3];
			}
		}
		return result; 
	}

	public static void main(String[] args) {
		Miscellaneous misc = new Miscellaneous();
	}
}
