package com.epi.array;

import java.util.Arrays;

public class Duplicates {

	/**
	 * Leet code problem. Solution -> Accepted
	 * 
	 * Given sorted array nums = [1,1,1,2,2,3], Your function should return length = 5, with the first five 
	 * elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length. 
	 * 
	 * @param nums sorted array
	 * @return duplicates removed from the sorted array
	 */
	private int removeDuplicates(int[] nums){
        if(nums == null || nums.length == 0) return 0;
        
        int iter = 1, idx = 1, k = 0;
		while(iter < nums.length){
			if(nums[iter] == nums[iter - 1]){
				k++;
				if(k < 2){
					nums[idx++] = nums[iter];
				} 
				iter++;
			} else {
				k = 0;
				nums[idx++] = nums[iter++];
			}
		}
        return idx;
	}
	
	public static void main(String[] args) {
		Duplicates dup = new Duplicates();
		int[] nums = new int[]{1, 1, 1, 1, 3, 3};
		int totalElements = dup.removeDuplicates(nums);
		System.out.println(Arrays.toString(nums) + "\n" + totalElements);
	}
}
