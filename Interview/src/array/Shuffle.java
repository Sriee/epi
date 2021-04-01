package array;

import java.util.Set;
import java.util.HashSet;
import java.util.Random;

/**
 * Leet code. Solution -> Accepted Run Time: 96 ms. Above average solution for
 * brute force approach Given a array of elements which doesn't contain
 * duplicates implement two functions 1. shuffle -> Should return any
 * permutation of the input array 2. reset -> Return the original array
 */
public class Shuffle {
    int[] arr, shuffled;
    int[] original, array;
    Random rand;

    public Shuffle(int[] nums) {
        this.rand = new Random();
        this.arr = nums;
        this.shuffled = new int[nums.length];

        // For Fisher-Yates algorithm
        this.array = nums;
        this.original = nums.clone();
    }

    /**
     * Resets the array to its original configuration
     * 
     * @return original array
     */
    public int[] reset() {
        return this.arr;
    }

    /**
     * Resets the array to its original configuration
     * 
     * @return original array
     */
    public int[] resetFY() {
        // array would be in shuffle state, so creating a new clone of the original
        this.array = this.original.clone();
        return this.array;
    }

    /**
     * Fisher-Yates algorithm to shuffle an array. Note tha, by shuffling we mean we
     * need one of the permutation of the array. In our brute force soution we
     * maintained a set to keep track of the elements we already filled. Instead, in
     * this algorithm; 1. we select a number from the i to len(array) -> r 2.
     * swap(r, i) - by swaping notice here we are moving the unseen element to the
     * right of i so that next time we wouldn't be selecting an element which is
     * already present in the shuffled array. This way we don't need an extra
     * HashSet to keep track of elements we have seen already Side note: Python's
     * random.shuffle() uses this algorithm
     * 
     * @return the shuffled array
     */
    public int[] shuffleFY() {
        int temp, r, len = this.original.length;
        for (int i = 0; i < len; i++) {
            // Finds random number between [i, len]
            r = this.rand.nextInt(len - i) + i;

            // Swap ith value in the array to rth value
            temp = this.array[i];
            this.array[i] = this.array[r];
            this.array[r] = temp;
        }

        return this.array;
    }

    /**
     * Returns a random shuffling of the array. Time Complexity - O(n^2) - Why ?
     * This is a brute force approach to shuffle an array. Notice that when we have
     * shuffle more than half the array we will be spending more time in the
     * do-while loop. This will increase the time complexity. Space Complexity -
     * O(n)
     * 
     * @return the shuffled array
     */
    public int[] shuffle() {
        int r = 0;
        Set<Integer> seen = new HashSet<>();
        Random rand = new Random();

        for (int i = 0; i < this.arr.length; i++) {
            do {
                r = rand.nextInt(this.arr.length);
            } while (seen.contains(r));
            seen.add(r);
            this.shuffled[i] = this.arr[r];
        }

        return this.shuffled;
    }
}
