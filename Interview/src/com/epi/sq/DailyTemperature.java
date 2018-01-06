package com.epi.sq;

import java.util.Arrays;

public class DailyTemperature {

	public int[] dailyTemperatures(int[] temperatures){
		if(temperatures == null || temperatures.length == 0) return temperatures; 
		
		int[] stack = new int[temperatures.length];
		int[] result = new int[temperatures.length];
		int top = -1;
		for(int i = 0; i < temperatures.length; i++){
			while(top > -1 && temperatures[i] > temperatures[stack[top]]){
				int index = stack[top];
				result[index] = i - index;
				top--;
			}
			stack[++top] = i;
		}
		
		while(top != -1)
			result[stack[top--]] = 0;
		
		return result;
	}
	
	public static void main(String[] args) {
		DailyTemperature dt = new DailyTemperature();
		int[][] cases= new int[][]{
			{60, 60, 60, 60},
			{78, 77, 50, 40, 30, 10},
			{20, 30, 50, 65, 73, 73, 80},
			{35, 53, 44, 72, 29, 63, 50, 66},
			{},
			null,
			{73},
			{54, 64}
		}; 
		
		for(int[] inp : cases){
			System.out.println(Arrays.toString(dt.dailyTemperatures(inp)));
		}
	}
}
