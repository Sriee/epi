package util;

import java.util.ArrayList;

public class RangeTest {

	public static void main(String[] args) {
		ArrayList<Integer> data = null;
		data = (ArrayList<Integer>)Range.range(-5, -10, 2);
		System.out.println(data.toString());
	}

}
