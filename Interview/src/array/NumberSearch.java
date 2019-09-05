package array;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class NumberSearch {
	
	private int[] countElementPresent(List<Integer> list, int numberToFind){
		if(list == null || list.isEmpty() || !list.contains(numberToFind)) return null;
		List<Integer> result = new ArrayList<>();
		int startIndex = 0, temp;
		boolean exit = false;
		while(startIndex < list.size() && !exit){
			temp = list.subList(startIndex, list.size()).indexOf(numberToFind);
			if(temp == -1)
				exit = true;
			else{
				result.add(startIndex + temp);
				startIndex += temp + 1;
			}
		}
		
		return (result.isEmpty()) ? null : result.stream().filter(i -> i != null).mapToInt(i -> i).toArray();
	}
	
	private int countOccurances(List<Integer> list, int numberToFind){
		return Collections.frequency(list, numberToFind);
	}
	
	public static void main(String[] args) {
		NumberSearch t = new NumberSearch();
		List<Integer> list = Arrays.asList(1, 29, 8, 367, 38, 71, 6, 4, 75, 29, 64, 29);
		int freq = 0;
		int[] counts = t.countElementPresent(list, 29);
		try{
			freq = t.countOccurances(list, 29); 
			if(!(freq == counts.length)) throw new AssertionError(freq + " != " + counts.length); 
			System.out.println(Arrays.toString(counts));
		} catch(AssertionError e){
			e.printStackTrace();
		}
	}
}
