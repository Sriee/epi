package com.test.array;

import static org.junit.Assert.assertNull;

import org.junit.Test;

import array.Dutch;

public class TestDutch {

	@Test
	public void testWrongInputParameters() {
		assertNull(Dutch.partition(null));
		int[] emptyArray = {};
		assertNull(Dutch.partition(emptyArray));
	}
}
