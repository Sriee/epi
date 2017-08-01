/**
 * 
 */
package com.epi.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.SortedMap;

/**
 * @author sriee
 *
 */
public class Dutch {

	public static int[] efficientPartition(int[] arr){
		return arr;
	}
	
	public static int[] partition(int[] arr){
		HashMap<Integer, Integer> map = new HashMap<>();
		SortedMap<Integer, Integer> sortedMap = null;
		int idx = 0;
		
		if(arr == null){
			return arr;
		}
		
		if(arr.length == 0){
			return null;
		}
		
		for(int element : arr){
			if(map.containsKey(element)){
				map.put(element, map.get(element) + 1);
			} else {
				map.put(element, 1);
			}
		}
		
		// Sort the keys
		sortedMap = new TreeMap<>(map);
		printMap(sortedMap);
		
		for(int key : sortedMap.keySet()){
			int value = sortedMap.get(key);
			for(int i = 0; i < value; i++){
				arr[i + idx] = key;
			}
			idx += value; 
		}
		
		return arr;
	}
	
	/**
	 * Print map utility function for debugging 
	 * @param <T>
	 * @param map
	 */
	private static <T> void printMap(Map<T, T> map){
		for(Map.Entry<T, T> entry : map.entrySet()){
			System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
		}
	} 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = {0, 1, 0, 2, 2, 2, 1, 0, 1, 0, 0, 1, 0, 2, 2}; 
		
		System.out.println(Arrays.toString(partition(arr)));
	}

}
