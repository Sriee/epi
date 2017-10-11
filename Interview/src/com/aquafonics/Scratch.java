package com.aquafonics;

import java.util.HashMap;

public class Scratch {
	
	public static void main(String[] args) {
		Eval e = new Eval();
		e.map = new HashMap<>();
		Condition c1 = new Condition("c1", null, ">", true);
		Condition c2 = new Condition("c2", null, ">=", true);
		Condition c3 = new Condition("c3", null, "<", true);
		Condition c4 = new Condition("c4", null, "<", false);
		e.map.put(c1.getName(), c1);
		e.map.put(c2.getName(), c2);
		e.map.put(c3.getName(), c3);
		e.map.put(c4.getName(), c4);
		
		String[] expression = {"c1", "&&", "c2", "&&", "c3", "||", "c4"};
		e.evaluateBoolean(expression); // false
		
		String[] expression1 = {"c1", "||", "(", "c2", "&&", "c3", ")", "||", "c4"};
		c1.setValue(false); c2.setValue(true); c3.setValue(true); c4.setValue(false);
		e.evaluateBoolean(expression1); // true
		
		String[] expression2 = {"c1", "||", "(", "c2", "&&", "(", "c3", "||", "c4", ")", ")"};
		e.evaluateBoolean(expression2); // true
	}
}
