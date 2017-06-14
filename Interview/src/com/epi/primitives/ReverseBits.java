package com.epi.primitives;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ReverseBits {

	public long reverseBits(long number){
		if(number < 0) throw new IllegalArgumentException("Number should be positive.");
		return((number >> 48) & 0xFFFF) |
				((number >> 32) & 0xFFFF) << 16 |
				((number >> 16) & 0xFFFF) << 32 |
				(number & 0xFFFF) << 48;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ReverseBits rev = null;
		try{
			System.out.print("Enter the number: ");
			long number = scan.nextLong();
			rev = new ReverseBits();
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

	}

}
