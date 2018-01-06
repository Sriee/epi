package com.test.sq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.epi.sq.DailyTemperature;

public class TestTemperatures {

	static DailyTemperature dailyTemp = null;
	
	@Before
	public void setUp(){
		dailyTemp = new DailyTemperature();
	}
	
	@Test
	public void shouldReturnEmpty() {
		int[][] cases= new int[][]{
			{60, 60, 60, 60},
			{},
			null,
			{73},
			{54, 64}
		};
		
		int[][] expected = new int[][]{
			{0, 0, 0, 0},
			{},
			null,
			{0},
			{1, 0}
		};
		
		for(int i = 0; i < cases.length; i++){
			assertArrayEquals(dailyTemp.dailyTemperatures(cases[i]), expected[i]);	
		}
	}

	@Test
	public void correctnessTest(){
		int[][] cases= new int[][]{
			{78, 77, 50, 40, 30, 10},
			{20, 30, 50, 65, 73, 73, 80},
			{35, 53, 44, 72, 29, 63, 50, 66}
		};
		
		int[][] expected = new int[][]{
			{0, 0, 0, 0, 0, 0},
			{1, 1, 1, 1, 2, 1, 0},
			{1, 2, 1, 0, 1, 2, 1, 0}
		};
		
		for(int i = 0; i < cases.length; i++){
			assertArrayEquals(dailyTemp.dailyTemperatures(cases[i]), expected[i]);
		}
	}
}
