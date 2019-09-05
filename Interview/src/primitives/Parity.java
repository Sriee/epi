/**
 * 
 */
package primitives;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author sriee
 *
 */
public class Parity {

	public int checkParity(long x){
		int result = 0;
		try{
			
			if(x < 0) throw new NumberFormatException();
			
			while(x != 0){
				result ^= (x & 1);
				x >>= 1;
			}
		} catch(NumberFormatException f){
			System.out.println("Input should be positive!");
			return -1;
		}
		return result;
	}
	
	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		long number = 0;
		Parity par = new Parity();
		try{
			System.out.print("Enter the number to check parity :");
			number = scan.nextLong();

			System.out.println("Parity of " + number + " : " + par.checkParity(number));

		} catch(InputMismatchException e){
			System.out.println("Input should be positive and integer value!");
			e.printStackTrace();
		} finally {
			scan.close();
		}
	}

}
