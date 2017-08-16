package util.generator;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		IntegerGenerator ig = new IntegerGenerator(0, 30);
		int arr[] = ig.generate(3);
		System.out.println(Arrays.toString(arr));
	}

}
