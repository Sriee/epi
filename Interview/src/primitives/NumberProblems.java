package primitives;

public class NumberProblems {

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 0 ms. Optimal solution
	 *  
	 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
	 * Given two integers x and y, calculate the Hamming distance.
	 * 
	 * Ex:
	 *  1 - 0 0 0 1
	 *  4 - 0 1 0 0
	 *        |   |
	 *  Dist    2 
	 *         
	 * @param x
	 * @param y
	 * @return
	 */
    public int hammingDistance(int x, int y) {
        int count = 0;
        while(x != 0 || y != 0) {
            count = count + ((x & 1) ^ (y & 1));
            x >>>= 1;
            y >>>= 1;
        }
        return count;
    }
    
	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 1 ms. Almost Optimal solution
	 * 
	 * Given an unsigned integer, compute the number of 1's in the number. This is known as 
	 * Hamming Weight
	 * 
	 * @param n
	 * @return
	 */
    public int hammingWeight(int n) {
        int count = 0;
        long i = 1;
        
        for(int j = 0; j < 32; j++) {            
            if((n & i) != 0)
                count++;
            i <<= 1;
        }
        
        /**
         * while(n != 0) {
         * 		count = count + (n & 1);
         * 	
         * 		// This will lead to an infinite loop. While right shifting
         * 		// Java appends '1' to the left most bit. 
         * 		n >> = 1;
         * 
         *  	// Instead use right shift operator which fills '0' to its
         *  	// left most bit
         *  	n >>> = 1; 	
         * }
         */
        return count; 
    }
    
	public static void main(String[] args) {
		NumberProblems num = new NumberProblems();
		num.hammingWeight(-3);
	}

}
