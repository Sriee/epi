package com.epi.leet;

import java.util.Arrays;

public class NextGreat {

	private static int[] nextGreater(int[] nums1, int[] nums2) {
		int[] result = new int[nums1.length];
		
		if(nums1 == null || nums2 == null)
			return new int[] {};
		
		if(nums1.length == 0 || nums2.length == 0)
			return new int[] {};
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(nextGreater(new int[] {12, 2, 4}, new int[] {1, 2, 3, 4}))); 
	}

}
