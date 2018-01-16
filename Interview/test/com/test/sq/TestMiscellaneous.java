package com.test.sq;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import com.epi.sq.Miscellaneous;

public class TestMiscellaneous {

	static Miscellaneous misc = null;
	
	@Before
	public void setUp(){
		misc = new Miscellaneous();
	}
	
	@Test
	public void asteroidEdgeCasesTest() {
		int[][] cases = new int[][]{
			{},
			null,
			{8, -8},
			{1000},
			{-1000, 1000}
		};
		
		int[][] expected = new int[][]{
			{},
			null,
			{},
			{1000},
			{-1000, 1000}
		};
		
		for(int i = 0; i < cases.length; i++)
			assertArrayEquals(misc.asteroidCollision(cases[i]), expected[i]);	
	}

	@Test
	public void asteroidCorrectnessTest(){
		int[][] cases = new int[][]{
			{5, 10, -5},
			{20, 10, 8, -8},
			{10, 2, -5},
			{-2, -1, 1, 2},
			{1, -2, -2, -2}
		};
		
		int[][] expected = new int[][]{
			{5, 10},
			{20, 10},
			{10},
			{-2, -1, 1, 2},
			{-2, -2, -2}
		};
		
		for(int i = 0; i < cases.length; i++)
			assertArrayEquals(misc.asteroidCollision(cases[i]), expected[i]);	
	}
	
	@Test
	public void rpnEdgeCasesTest() {
		String[][] expressions = new String[][]{
			null,
			{},
			{"21", "54", "*"},
			{"7682"},
		};
		
		int[] expected = new int[]{0, 0, 1134, 7682};
		
		for(int i = 0; i < expressions.length; i++)
			assertEquals(misc.evalRPN(expressions[i]), expected[i]);	
	}

	@Test
	public void rpnCorrectnessTest(){
		String[][] expressions = new String[][]{
			{"2", "1", "+", "3", "*"},
			{"4", "13", "5", "/", "+"},
			{"4", "13", "-", "12", "79", "+", "-"}
		};
		
		int[] expected = new int[]{9, 6, -100};
		
		for(int i = 0; i < expressions.length; i++)
			assertEquals(misc.evalRPN(expressions[i]), expected[i]);	
	}
	
	@Test
	public void Pattern132Test(){
		int[][] inputs = new int[][]{
			{1},
			{1,1,1,1,2},
			{},
			null,
			{1, 3, 2},
			{3, 1, 4, 2},
			{-1, 3, 2, 0}
		};
		
		boolean[] expected = {false, false, false, false, true, true, true};
		for(int i = 0; i < inputs.length; i++)
			assertEquals(misc.find132pattern(inputs[i]), expected[i]);
	}
}
