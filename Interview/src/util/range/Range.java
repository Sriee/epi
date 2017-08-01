package util;

import java.util.List;
import java.util.ArrayList;

public class Range{

	public Range(){}
	
	private static List<Integer> generateRange(int start, int stop, int step){
		List<Integer> list = new ArrayList<Integer>();
		for(int i = start; i < stop; i+= step){
			list.add(i);
		}
		return list;	
	}
	
	public static List<Integer> range(int stop){
		return generateRange(0, stop, 1);
	}
	
	public static List<Integer> range(int start, int stop){
		return generateRange(start, stop, 1);
	}
	
	public static List<Integer> range(int start, int stop, int step){
		if(step < 1 )
			throw new IllegalArgumentException("Step shoule be >= 1");
		
		return generateRange(start, stop, step);
	}
	
}
