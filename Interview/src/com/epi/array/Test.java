package com.epi.array;

import java.util.Arrays;

import util.generator.DoubleGenerator;

public class Test {
	
	public static void main(String[] args) {
		DoubleGenerator fg = new DoubleGenerator(-4526, 8235, 4);
		double[] test = fg.generate(15);
		System.out.println(Arrays.toString(test));
	}

}
