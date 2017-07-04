/**
 * 
 */
package com.test.primitives;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epi.primitives.SwapBits;

/**
 * @author sriee
 *
 */
public class SwapBitsTest {

	static private SwapBits sb = null;
	
	@BeforeClass
	public static void setUp(){
		sb = new SwapBits();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldRejectInvalidIndex() {
		assertEquals(sb.swapBits(2378, -1, 3), -1);
		assertEquals(sb.swapBits(25, 0, 64), -1);
		assertEquals(sb.swapBits(928, Integer.MAX_VALUE, Integer.MIN_VALUE), -1);
	}

	@Test
	public void shouldWork(){
		assertEquals(sb.swapBits(10, 1, 2), 12);
		assertEquals(sb.swapBits(1011, 8, 3), 763);
	}

	@Test
	public void noSwapTest(){
		assertEquals(sb.swapBits(678, 12, 12), 678);
		assertEquals(sb.swapBits(182, 0, 0), 182);
	}
}
