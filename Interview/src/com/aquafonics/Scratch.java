package com.aquafonics;

import java.util.Map;
import java.util.HashMap;

public class Scratch {

	private static boolean isOverride(int[] arr1, int[] arr2) {
		
		if(arr1[0] == arr2[0] && (arr1[1] < arr2[1] || arr2[1] < arr1[1])) {
			return true;
		} else if(arr1[1] == arr2[1] && (arr1[0] < arr2[0] || arr2[0] < arr1[0])){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Rule r1 = new Rule("humidity1", "actuator1");
		Rule r2 = new Rule("humidity1", "actuator2");
		Rule r3 = new Rule("humidity1", "actuator1");
		Rule r4 = new Rule("humidity2", "actuator2");
		
		System.out.println(r1);
		System.out.println(r1.hashCode() + " " + r2.hashCode() + " " + r3.hashCode() + " " + r4.hashCode());
		Map<Rule, Integer> hm = new HashMap<>();
		
		hm.put(r1, 1);
		hm.put(r2, 2);
		hm.put(r4, 4);
		if(hm.containsKey(r3))
			System.out.println("Done deal.");

		System.out.println(isOverride(new int[]{1, 10}, new int[]{1, 5}));
		System.out.println(isOverride(new int[]{1, 10}, new int[]{1, 10}));
		System.out.println(isOverride(new int[]{1, 10}, new int[]{2, 5}));
	}

}
