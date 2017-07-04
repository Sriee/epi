/**
 * 
 */
package com.epi.primitives;

/**
 * @author sriee
 *
 */
public class Division {
	private int quotient;
	private int remainder;
	
	public Division(){
		this.setQuotient(0);
		this.setRemainder(0);
	}
	
	public Division divide(int divident, int divisor){
		int q = 0;
		
		while((divident - divisor) >= 0){
			divident -= divisor;
			++q;
		}
		
		this.setQuotient(q);
		this.setRemainder(divident);
		return this;
	}
	
	/**
	 * @return the quotient
	 */
	public int getQuotient() {
		return quotient;
	}
	/**
	 * @param quotient the quotient to set
	 */
	public void setQuotient(int quotient) {
		this.quotient = quotient;
	}
	/**
	 * @return the remainder
	 */
	public int getRemainder() {
		return remainder;
	}
	/**
	 * @param remainder the remainder to set
	 */
	public void setRemainder(int remainder) {
		this.remainder = remainder;
	}

	@Override
	public String toString() {
		return "Division [quotient=" + quotient + ", remainder=" + remainder + "]";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Division d = new Division();
		System.out.println(d.divide(1176, 308));
	}

}
