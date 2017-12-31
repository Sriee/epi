package com.test.sq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.epi.sq.RPN;

public class TestRPN {

	static RPN rpn= null;
	@Before
	public void setUp() throws Exception {
		rpn = new RPN();
	}
	
	@Test
	public void shouldReturnZero() {
		assertEquals(0, rpn.evaluateRpnExpression(null));
		assertEquals(0, rpn.evaluateRpnExpression(" "));
		assertEquals(0, rpn.evaluateRpnExpression(""));
	}

	@Test
	public void shouldReturnCorrect(){
		assertEquals(1729, rpn.evaluateRpnExpression("1729"));
		assertEquals(9, rpn.evaluateRpnExpression("3,4,+,2,*,1,+"));
		assertEquals(-1, rpn.evaluateRpnExpression("1,1,+,-2,*"));
		assertEquals(0, rpn.evaluateRpnExpression("-641,28,/,6,/"));
	}
}
