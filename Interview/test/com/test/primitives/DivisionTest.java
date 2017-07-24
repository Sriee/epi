/**
 * 
 */
package com.test.primitives;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.epi.primitives.Division;

/**
 * @author sriee
 *
 */
public class DivisionTest {

	static Division div = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		div = new Division();
	}

	@Test
	public void checkNegative() {
		assertTrue(div.divide(-123, 1).getQuotient() < 0);
		assertTrue(div.divide(4, -2).getQuotient() < 0);
		assertFalse(div.divide(-123, -1).getQuotient() < 0);
	}

	@Test
	public void checkNull() {
		assertNull(div.divide(-123, 0));
		assertNull(div.divide(0, 0));
		assertNull(div.divide(-1, 0));
	}

	@Test
	public void checkDivision(){
		assertEquals(div.divide(13, 13).getQuotient(), 1);
		assertEquals(div.divide(-1585825, 25).getQuotient(), -63433);
		assertEquals(div.divide(22848, -19).getQuotient(), -1202);
		assertEquals(div.divide(22848, 19).getRemainder(), 10);
	}
}