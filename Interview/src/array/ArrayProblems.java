package array;

import java.util.*;

public class ArrayProblems {

	public int distanceBetweenBusStops(int[] distance, int start, int destination) {
		int clockwise = 0, anti = 0, n = distance.length;
		
		if(start > destination) {
			int temp = start;
			start = destination;
			destination = temp;
		}
		
		for(int i = start; i < destination; i++) {
			clockwise += distance[i];
		}
		
		for(int i = destination; i != start; i = (i + 1) % n) {
			anti += distance[i];
		}
		
		return (clockwise < anti) ? clockwise : anti;
	}
	
	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 0ms. Optimal solution
	 * 
	 * Rotate the array k times
	 * 
	 * Solution uses Approach 2: Reversing the array to rotate it
	 * 
	 * @param nums given array
	 * @param k number of times to rotate 
	 */
    public void rotate(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k == 0)
            return;
        
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    
    /**
     * Utility method to reverse elements of an array
     * 
     * @param nums given array
     * @param start pointer location
     * @param end pointer location
     */
    public void reverse(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            
            start++;
            end--;
        }
    }
    
	public void trapWater() {
		int[] A = new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        Stack<Integer> s = new Stack<Integer>();
        int i = 0, maxWater = 0, maxBotWater = 0;
        while (i < A.length){
            if (s.isEmpty() || A[i]<=A[s.peek()]){
                s.push(i++);
            }
            else {
                int bot = s.pop();
                maxBotWater = s.isEmpty()? // empty means no il
                0:(Math.min(A[s.peek()],A[i])-A[bot])*(i-s.peek()-1);
                maxWater += maxBotWater;
            }
        }
        System.out.println(maxWater);
	}
	
	
	public static void main(String[] args) {
		ArrayProblems ap = new ArrayProblems();
		int res = ap.distanceBetweenBusStops(new int[] {1, 2, 3, 4},  0, 2);
		System.out.println(res);
		
		int[] arr = new int[] {1, 2, 3, 46, 79, 34, 6, 7, 23, 4, 6, 0, 9, 4, 37, 4, 5, 64, 9, 80, 50, 8, 6};
		res = ap.distanceBetweenBusStops(arr,  18, 2);
		System.out.println(res);
		
		ap.trapWater();
	}

}
