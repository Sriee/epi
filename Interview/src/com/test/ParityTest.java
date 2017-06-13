/**
 * 
 */
package com.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epi.primitives.Parity;

/**
 * @author sriee
 *
 */
public class ParityTest {

	static private Parity parity;
	
	@BeforeClass
	public static void setUp(){
		parity = new Parity();
	}
	
	@Test
	public void shouldRejectNeg() {
		assertEquals(parity.checkParity(-1), -1);
	}
	
	@Test
	public void shouldReturnZero(){
		assertEquals(parity.checkParity(54), 0);
	}
	
	@Test
	public void maxValue(){
		assertEquals(parity.checkParity(Long.MAX_VALUE - 1), 0);
	}

}
