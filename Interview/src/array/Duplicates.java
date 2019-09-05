package array;

import java.util.Arrays;

public class Duplicates {

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 1 ms. Optimal Solution
	 * 
	 * Given an array with zeroes. Duplicate one more zero next to it
	 * 
	 * Example:
	 * 	[1, 0, 2, 3, 0, 4, 5, 0] -> [1, 0, 0, 2, 3, 0, 0, 4]
	 *  [1, 2, 3] -> [1, 2, 3]
	 *  
	 * @param arr
	 */
	public void duplicateZeroes(int[] arr) {
		int i = 0, toShift = 0;
		
		// Look at the trick of adding toShift to condition check of for loop
		// In the example: [1, 0, 2, 3] we need to shift 1 to the right, 
		// by adding toShift value to i we are ignoring 3, which we should
		for(i = 0; i + toShift < arr.length; i++)
			if(arr[i] == 0)
				toShift++;
		
		// Trick of using i instead of assigning i = arr.length - 1
		for(i = i - 1; toShift > 0; i--) {
			// We start filling the element with the shift. Note here if arr[i] == 0
			// We will additional 0 at the i + shift position 
			if(i + toShift < arr.length)
				arr[i + toShift] = arr[i];
			
			// If the element is 0 fill again. Trick of decrementing and using toShift in
			// a single line
			if(arr[i] == 0)
				arr[i + --toShift] = arr[i];
		}
		
		System.out.println(Arrays.toString(arr));
	}
	
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
		
		dup.duplicateZeroes(new int[] {1, 0, 2, 3, 0, 4, 5, 0});
	}
}
