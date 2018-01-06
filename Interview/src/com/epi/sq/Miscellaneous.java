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
	
	public static void main(String[] args) {
		Miscellaneous misc = new Miscellaneous();
		int[][] cases = new int[][]{
			{},
			null,
			{5, 10, -5},
			{8, -8},
			{20, 10, 8, -8},
			{10, 2, -5},
			{-2, -1, 1, 2},
			{1, -2, -2, -2}
		};
		
		for(int[] inp : cases){
			System.out.println(Arrays.toString(misc.steroidCollision(inp)));
		}
	}
}
