package com.epi.array;

import java.util.Arrays;

public class Test {
	
	private static void how(int[] array, int index, int... varargs){
		int arrayLength = -1, pos = 0;
		if(index > array.length || index < 0)
			throw new ArrayIndexOutOfBoundsException();
		
		arrayLength = (array == null) ? 0 : array.length;
		int[] inserted = new int[arrayLength + varargs.length];
		
		System.arraycopy(array, 0, inserted, 0, index);
		
		for(int i: varargs){
			inserted[pos + index] = i;
			pos++;
		}
		
		System.out.println(Arrays.toString(inserted));
		System.arraycopy(array, pos, inserted, index + pos, array.length - varargs.length);
		System.out.println(Arrays.toString(inserted));
	}		
	
	public static void main(String[] args) {
		int[] arr = {1, 2, 5, 6};		
		how(arr, 2, 3, 4);
	}
}
