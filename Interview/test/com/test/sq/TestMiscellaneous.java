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
		
		for(int i = 0; i < cases.length; i++){
			assertArrayEquals(cases[i], expected[i]);	
		}
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
		
		for(int i = 0; i < cases.length; i++){
			assertArrayEquals(cases[i], expected[i]);	
		}
	}
}
