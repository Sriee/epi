package com.epi.array;

import java.util.Arrays;

public class Test {
	
	private static void how(int... varargs){
		for(int i: varargs)
			System.out.print(i + " ");
		
		System.out.println();
	}		
	
	public static void main(String[] args) {
		int[] arr = {1,2,4,5};
		int[] inserted = new int[arr.length + 1];
		int pos = 4;
		
		if(pos > arr.length || pos < 0)
			throw new ArrayIndexOutOfBoundsException();
		
		System.arraycopy(arr, 0, inserted, 0, pos);
		inserted[pos] = 3;
		System.arraycopy(arr, pos, inserted, pos + 1, arr.length - pos);
		
		System.out.println(Arrays.toString(inserted));
		
		how(1, 2, 3);
		how(1, 2, 3, 4, 5, 6, 7, 8, 9);
		how(1, 2, 3, 4);
		how(1, 2, 3, -2);
	}
}
