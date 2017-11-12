package com.test.pure;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.epi.company.Pure;

public class PureStorage {

	static Pure ps;
	
	@Before
	public void setUp() throws Exception {
		ps = new Pure();
	}

	@Test
	public void shouldReturnZero() {
		assertEquals(ps.checkLockHistory(null), 0);
		assertEquals(ps.checkLockHistory(new String[] {}), 0);
		String[] events = new String[] {"ACQUIRE 364", "ACQUIRE 84", "RELEASE 84", "RELEASE 364"};
		assertEquals(ps.checkLockHistory(events), 0);
	}
	
	@Test
	public void shouldReturnLenPlusOne() {
		String[] events = new String[] {"ACQUIRE 123", "ACQUIRE 364", "ACQUIRE 84", "RELEASE 84", 
				"RELEASE 364", "ACQUIRE 456"};
		assertEquals(ps.checkLockHistory(events), events.length + 1);
		events = new String[] {"ACQUIRE 364", "ACQUIRE 84", "RELEASE 84"};
		assertEquals(ps.checkLockHistory(events), events.length + 1);
	}

	@Test
	public void shouldReturnIndexofError() {
		String[] events = new String[] {"ACQUIRE 364", "ACQUIRE 84", "RELEASE 364", "RELEASE 84"};
		assertEquals(ps.checkLockHistory(events), 3);
		
		events = new String[] {"ACQUIRE 123", "ACQUIRE 364", "ACQUIRE 84", "RELEASE 84", "RELEASE 364", 
				"ACQUIRE 789", "RELEASE 456", "RELEASE 123"};
		assertEquals(ps.checkLockHistory(events), 7);
		
		events = new String[] {"ACQUIRE 364", "RELEASE 84", "RELEASE 364"};
		assertEquals(ps.checkLockHistory(events), 2);
	}
	
	@Test
	public void testCountPalindromes() {
		assertEquals(ps.countPalindromes("abcd"), 4);
		assertEquals(ps.countPalindromes(""), 0);
		assertEquals(ps.countPalindromes(null), 0);
		assertEquals(11, ps.countPalindromes("abbaeae"));
		assertEquals(ps.countPalindromes("hellolle"), 13);
		assertEquals(ps.countPalindromes("wowpurerocks"), 14);
		assertEquals(ps.countPalindromes("aaaa"), 11);
	}
}
