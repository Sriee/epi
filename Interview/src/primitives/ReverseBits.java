package primitives;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ReverseBits {

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 0 ms. Optimal solution. 
	 * 
	 * Given a number do a bit wise complement on that number
	 * 
	 * Example:
	 * 		Input: 5 (b'101) -> 2 (b'010)
	 *  
	 * @param N Number
	 */
	public int bitwiseComplement(int N) {
		int res = 0, pos = 0;
		
		while(N != 0) {
			if((N & 1) == 0) {
				res += (1 << pos);
			}
			
			N >>= 1;
			pos++;
		}
		
		return res;
	}
	
	public long reverseBits(long number){
		if(number < 0) throw new IllegalArgumentException("Number should be positive.");
		return((number >> 48) & 0xFFFF) |
				((number >> 32) & 0xFFFF) << 16 |
				((number >> 16) & 0xFFFF) << 32 |
				(number & 0xFFFF) << 48;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ReverseBits rev = new ReverseBits();
		
		try{
			System.out.print("Enter the number: ");
			long number = scan.nextLong();
			System.out.println(rev.reverseBits(number));
		} catch (InputMismatchException e){
			System.out.println("Invalid input.");
			e.printStackTrace();
		} catch(IllegalArgumentException i){
			System.out.println(i.getMessage());
			i.printStackTrace();
		} finally {
			scan.close();
		}
		System.out.println(rev.bitwiseComplement(10000));
	}

}
