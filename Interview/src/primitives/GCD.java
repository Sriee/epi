/**
 * 
 */
package primitives;

import java.util.Scanner;

/**
 * @author sriee
 *
 */
public class GCD {

	/**
	 * The below method calculates the GCD of two positive numbers using Euclid algorithm
	 * 
	 * The steps for calculatin the GCD using euclid algorithm is as follows
	 * For our example, GCD(24, 60)
	 * 			
	 * 1. Divide the larger number by the small one. In this case we divide 60 by 24 to get a quotient of 2 and remainder of 12.
     * 2. Next we divide the smaller number (i.e. 24) by the remainder from the last division (i.e. 12). 
     * 	  So 24 divide by 12, we get a quotient of 2 and remainder of 0.
     * 3. Since we already get a remainder of zero, the last number that we used to divide is the GCD, i.e 12.
	 * 
	 * Note: The following program does not use multiplication, division or modular division to calculate GCD
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public int euclid(int num1, int num2){
		int low, high;
		Division div = null;
		
		// Input Validation
		if(num1 == 0 || num2 == 0){
			System.out.println("Input numbers should not be '0'.");
			return -1;
		}
		
		// Handling trivial cases
		if(num1 == 1 || num2 ==1)
			return 1;
		
		if(num1 == num2){
			return num1;
		}
		
		if(num1 < num2){
			low = num1;
			high = num2;
		} else {
			low = num2;
			high = num1;
		}

		div = new Division();
		do{
			div = div.divide(high, low);
			
			if(div.getRemainder() != 0){
				high = low;
				low = div.getRemainder();
			}
		} while(div.getRemainder() != 0);
			
		return low;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String[] nums = null;
		String input = null;
		GCD gcd = new GCD();
		try{
			input = scan.nextLine().trim();
			nums = input.split(" ");
			System.out.println(
					gcd.euclid(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]))
					);
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			scan.close();
		}
	}

}
