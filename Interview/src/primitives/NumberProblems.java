package primitives;

public class NumberProblems {

    // you need to treat n as an unsigned value
	/**
	 * Given an unsigned integer, compute the number of 1's in the number. This is known as 
	 * Hamming Weight
	 * 
	 * Run Time: 1ms
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
