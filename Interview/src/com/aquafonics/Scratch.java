package com.aquafonics;

import java.util.*;

public class Scratch {
	
	public static void main(String[] args) {
		Set<String> s = new HashSet<>();
		List<String> l = new ArrayList<>();
		l.add("221"); l.add("223"); l.add("224");
		System.out.println(l.contains("221"));
		l.add("221");
		s.addAll(l);
		System.out.println(s.toString());
	}

}
