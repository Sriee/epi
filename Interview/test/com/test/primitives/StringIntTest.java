/**
 * 
 */
package com.test.primitives;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import primitives.StringToInt;

/**
 * @author sriee
 *
 */
public class StringIntTest {

	static StringToInt toInt = null;
	
	@Before
	public void setUp() throws Exception {
		toInt = new StringToInt();
	}

	@Test(expected=NullPointerException.class)
	public void shouldAvoidEmptyString() {
		toInt.stringToInt("");
	}

	@Test(expected=NullPointerException.class)
	public void shouldAvoidNullString() {
		toInt.stringToInt(null);
	}
	
	@Test
	public void correctRun(){
		assertEquals(toInt.stringToInt("563798"), 563798);
		assertEquals(toInt.stringToInt("0"), 0);
		assertEquals(toInt.stringToInt("34567890"), 34567890);
	}
	
	@Test
	public void handleNegativeInputs(){
		assertEquals(toInt.stringToInt("-43834"), -43834);
		assertEquals(toInt.stringToInt("-9484"), -9484);
		assertEquals(toInt.stringToInt("-0"), 0);
	}
	
	@Test(expected=NumberFormatException.class)
	public void handleWrongFormats(){
		assertEquals(toInt.stringToInt("-95tg"), -95);
		assertEquals(toInt.stringToInt("hdf56213"), 56123);
	}
		
}
