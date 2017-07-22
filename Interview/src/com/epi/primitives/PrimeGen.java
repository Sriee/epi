package com.epi.primitives;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PrimeGen {

	/**
	 * The method for generating primes are based on sieve of eratosthene
	 * 
	 * @param n
	 * @return list of primes
	 */
	public String generate(long n){
		int len = -1, size = new Double(Math.floor(0.5 * (n - 3)) + 1).intValue();
		List<Integer> primes = new ArrayList<>();
		int value; 
		
		if(n < 2)
			return null;
		
		if(n == 2)
			return "2";
		
		// Initialize boolean array
		boolean[] isPrime = new boolean[size];
		Arrays.fill(isPrime, true);
		
		primes.add(2);
		for(long i = 0; i < size; i++){
			if(isPrime[(int) i]){
				value = (int) ((i << 1) + 3); // 2i + 3
				primes.add(value);
				
				/**
				 * We need to know the index of square of the number 'num'
				 * Example - For 3, 9 is at index 3, For 5, 25 is at index 11
				 * 
				 * We need to know i for p^2 
				 * p = (2i + 3)
				 * p^2 = 4i^2 + 12i + 9
				 * 
				 * i to p mapping is i = (p - 3) / 2
				 * 
				 * Substitute p^2 in the place of p in the above equation 
				 * 
				 * i = (p^2 - 3) / 2
				 * i = (4i^2 + 12i + 9 - 3) / 2
				 * i = (2i^2 + 6i + 3)
				 */
				for(long p = ((i * i) << 1) + 6 *i + 3; p < size; p+= value){
					isPrime[(int) p] = false;
				}
			}
		}
		len = primes.toString().length();
		return primes.toString().substring(1, len - 1);
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		long n = -1;
		PrimeGen pg = null;
		try{
			n = scan.nextLong();
			pg = new PrimeGen();
			System.out.println(pg.generate(n));
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			scan.close();
		}
	}
}
