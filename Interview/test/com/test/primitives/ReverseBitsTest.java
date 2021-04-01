package com.test.primitives;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import primitives.ReverseBits;

public class ReverseBitsTest {

    static ReverseBits rev = null;

    @Before
    public void setUp() throws Exception {
        rev = new ReverseBits();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRaiseException() {
        assertEquals(rev.reverseBits(-231), 0);
    }

    @Test
    public void shouldWork() {
        assertEquals(rev.reverseBits(4), 1125899906842624L);
        assertEquals(rev.reverseBits(1125899906842624L), 4);
    }
}
