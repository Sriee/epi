package com.test.pure;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import company.Pure;

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
        String[] events = new String[] { "ACQUIRE 364", "ACQUIRE 84", "RELEASE 84", "RELEASE 364" };
        assertEquals(ps.checkLockHistory(events), 0);
    }

    @Test
    public void shouldReturnLenPlusOne() {
        String[] events = new String[] { "ACQUIRE 123", "ACQUIRE 364", "ACQUIRE 84", "RELEASE 84", "RELEASE 364",
                "ACQUIRE 456" };
        assertEquals(ps.checkLockHistory(events), events.length + 1);
        events = new String[] { "ACQUIRE 364", "ACQUIRE 84", "RELEASE 84" };
        assertEquals(ps.checkLockHistory(events), events.length + 1);
    }

    @Test
    public void shouldReturnIndexofError() {
        String[] events = new String[] { "ACQUIRE 364", "ACQUIRE 84", "RELEASE 364", "RELEASE 84" };
        assertEquals(ps.checkLockHistory(events), 3);

        events = new String[] { "ACQUIRE 123", "ACQUIRE 364", "ACQUIRE 84", "RELEASE 84", "RELEASE 364", "ACQUIRE 789",
                "RELEASE 456", "RELEASE 123" };
        assertEquals(ps.checkLockHistory(events), 7);

        events = new String[] { "ACQUIRE 364", "RELEASE 84", "RELEASE 364" };
        assertEquals(ps.checkLockHistory(events), 2);
    }

    @Test
    public void testCountPalindromes() {
        assertEquals(4, ps.countPalindromes("abcd"));
        assertEquals(0, ps.countPalindromes(""));
        assertEquals(0, ps.countPalindromes(null));
        assertEquals(11, ps.countPalindromes("abbaeae"));
        assertEquals(13, ps.countPalindromes("hellolle"));
        assertEquals(14, ps.countPalindromes("wowpurerocks"));
        assertEquals(10, ps.countPalindromes("aaaa"));
    }
}
