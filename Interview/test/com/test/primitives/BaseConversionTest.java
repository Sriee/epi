/**
 * 
 */
package com.test.primitives;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.epi.primitives.BaseConversion;

/**
 * @author sriee
 *
 */
public class BaseConversionTest {

	static BaseConversion bc= null;
	@Before
	public void setUp() throws Exception {
		bc = new BaseConversion();
	}

	@Test
	public void invalidBase1() {
		assertNull(bc.baseConversion(-1, "123", 2));
		assertNull(bc.baseConversion(0, "123", 2));
		assertNull(bc.baseConversion(17, "123", 2));
		assertNull(bc.baseConversion(517, "123", 2));
		
	}

	@Test
	public void invalidBase2() {
		assertNull(bc.baseConversion(4, "67", 0));
		assertNull(bc.baseConversion(5, "908", -1));
		assertNull(bc.baseConversion(6, "9823", -55));
		assertNull(bc.baseConversion(7, "56789", 67));
	}
	
	@Test
	public void invalidInput(){
		assertNull(bc.baseConversion(2, "", 3));
		assertNull(bc.baseConversion(4, null, 5));
		assertNull(bc.baseConversion(6, "12XZE", 7));
		assertNull(bc.baseConversion(8, "-", 9));
	}
	
	@Test
	public void shouldRunCorrectly(){
		assertEquals(bc.baseConversion(2, "1011010001", 10), "721");
		assertEquals(bc.baseConversion(10, "468", 8), "724");
		assertEquals(bc.baseConversion(10, "77853", 16), "1301D");
		assertEquals(bc.baseConversion(2, "1011011111", 16), "2DF");
		assertEquals(bc.baseConversion(8, "-4356435", 13), "-31CB42");	
	}
}
