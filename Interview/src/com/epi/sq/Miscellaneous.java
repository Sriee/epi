package com.epi.sq;

import java.util.Arrays;
import java.util.Stack;

public class Miscellaneous {
	
	public int[] steroidCollision(int[] asteroids){
		if(asteroids == null || asteroids.length == 0) return asteroids;
		
		int[] stack = new int[asteroids.length];
		int top = -1;
		boolean done = false;
		
		for(int i = 0; i < asteroids.length; i++){
			stack[++top] = asteroids[i]; 
			done = false;
			while(top > 1 && !done){
				int a = stack[top];
				int b = stack[top - 1];
				top--;
				if(b > 0 && a < 0){
					if(Math.abs(a) == Math.abs(b))
						continue;
					else
						stack[top] = (Math.abs(a) > Math.abs(b) ? a : b);
				} else {
					done = true;
					top++;
				}
			}	
		}
		
		int[] result = Arrays.copyOf(stack, top);
		return result;
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
			{-2, -1, 1, 2}
		};
		
		for(int[] inp : cases){
			System.out.println(Arrays.toString(misc.steroidCollision(inp)));
		}
	}
}
