/**
 * 
 */
package com.test.primitives;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import primitives.PrimeGen;

import java.util.Arrays;

/**
 * @author sriee
 *
 */
public class PrimeGeneratorTest {

	static PrimeGen pg = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		pg = new PrimeGen();
	}

	@Test
	public void checkNonValidInputs() {
		assertNull(pg.generate(-123));
		assertNull(pg.generate(0));
		assertNull(pg.generate(1));
	}

	@Test
	public void checkValidPrimes(){
		assertEquals(pg.generate(30), "2, 3, 5, 7, 11, 13, 17, 19, 23, 29");
		String inp = "2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59,"
				+ " 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, "
				+ "137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, "
				+ "211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, "
				+ "283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, "
				+ "379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, "
				+ "461, 463, 467";
		
		assertTrue(Arrays.deepEquals(pg.generate(468).split(", "), inp.split(", ")));
		
	}
}
