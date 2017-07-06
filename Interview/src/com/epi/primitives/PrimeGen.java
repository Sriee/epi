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
		String res = null;
		int len = -1, size = new Double(Math.floor(0.5 * (n - 3)) + 1).intValue();
		List<Integer> primes = new ArrayList<>();
		int value; 
		
		// Initialize boolean array
		boolean[] isPrime = new boolean[size];
		Arrays.fill(isPrime, true);
		
		primes.add(2);
		for(long i = 0; i < size; i++){
			if(isPrime[(int) i]){
				value = (int) ((i << 1) + 3); // 2i + 3
				primes.add(value);
				
				for(long p = ((i * i) << 1) + 6 *i + 3; p < size; p+= value){
					isPrime[(int) p] = false;
				}
			}
		}
		len = primes.toString().length();
		res = primes.toString().substring(1, len - 1);
		return res;
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
