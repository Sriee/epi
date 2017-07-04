package com.test.primitives;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.epi.primitives.DecodeExcel;

public class DecodeExcelTest {

	static DecodeExcel dc = null;
	
	@Before
	public void setUp() throws Exception {
		dc = new DecodeExcel();
	}

	@Test
	public void wrongInputs() {
		assertEquals(dc.ssDecodeColId(null), -1);
		assertEquals(dc.ssDecodeColId(""), -1);
		assertEquals(dc.ssDecodeColId("ZZZZZZZ"), -1);
		assertEquals(dc.ssDecodeColId("ABDHHUHSD"), -1);
	}
	
	@Test
	public void correctInputs(){
		assertEquals(dc.ssDecodeColId("A"), 1);
		assertEquals(dc.ssDecodeColId("z"), 26);
		assertEquals(dc.ssDecodeColId("M"), 13);
		assertEquals(dc.ssDecodeColId("ZY"), 701);
		assertEquals(dc.ssDecodeColId("mnc"), 9155);
	}

}
