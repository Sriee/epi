package com.epi.array;

import java.util.Arrays;

public class Test {

	private static void print(int arr[]){
		System.out.println(Arrays.toString(arr));
	}
	
	private static void print(double arr[]){
		System.out.println(Arrays.toString(arr));
	}
	
	public static void main(String[] args) {
		int[] intArr = {1, 2, 3, 4};
		double[] doubleArr = {2.4, 4.654, 3.564};
		
		print(intArr);
		print(doubleArr);
		
//		for(int i = 0; i < 5; i++)
//			System.out.print(dummy[i] + " ");
	}

}
