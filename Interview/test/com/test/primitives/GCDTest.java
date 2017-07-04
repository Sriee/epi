package com.test.primitives;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.epi.primitives.GCD;

/**
 * @author sriee
 *
 */
public class GCDTest {

	static GCD gcd = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		gcd = new GCD();
	}

	@Test
	public void shouldPass() {
		assertEquals(gcd.euclid(308, 1176), 28);
		assertEquals(gcd.euclid(1234, 1234), 1234);
		assertEquals(gcd.euclid(4950, 7020), 90);
		assertEquals(gcd.euclid(7920, 92664), 792);
		assertEquals(gcd.euclid(0, 1176), -1);
	}

}
