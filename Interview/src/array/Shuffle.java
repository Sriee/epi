package array;

import java.util.Set;
import java.util.HashSet;
import java.util.Random;

/**
 * Leet code. Solution -> Accepted
 * 
 * Run Time: 89 ms. Above average solution
 * 
 * Given a array of elements which doesn't contain duplicates implement two functions
 * 
 * 	1. shuffle -> Should return any permutation of the input array
 *  2. reset -> Return the original array
 *  
 */
public class Shuffle {
	int[] arr, shuffled;

	public Shuffle(int[] nums) {
	    this.arr = nums;
	    this.shuffled = new int[nums.length];
	}

	/** Resets the array to its original configuration and return it. */
	public int[] reset() {
	    return this.arr;
	}

	/** Returns a random shuffling of the array. */
	public int[] shuffle() {
	    int r = 0;
	    Set<Integer> seen = new HashSet<>();
	    Random rand = new Random();
	    
	    for(int i = 0; i < this.arr.length; i++) {
	        do {
	            r = rand.nextInt(this.arr.length);
	        } while(seen.contains(r));
	        seen.add(r);
	        this.shuffled[i] = this.arr[r];
	    }
	            
	    return this.shuffled;
	}
}
