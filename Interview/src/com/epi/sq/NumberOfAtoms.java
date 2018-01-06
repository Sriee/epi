package com.epi.sq;

import java.util.*;

public class NumberOfAtoms {
	
	public String count(String formula){
		if(formula == null || formula.isEmpty())
			return "empty";
		
		Map<String, Integer> map = new TreeMap<>();
		Stack<String> stack = new Stack<>();
		Stack<String> brackets = new Stack<>();
		String key = null;
		
		for(int i = 0; i < formula.length(); i++){
			if(formula.charAt(i) == '('){
				brackets.push("(");
				stack.push("(");
			} else if(formula.charAt(i) - '0' >= 0 && formula.charAt(i) - '0' <= 9){
				String num = ""; 
				while(i < formula.length() && Character.isDigit(formula.charAt(i))){
					num = num + formula.charAt(i++);
				}
				i--;
				if(stack.peek().equals(")")){
					stack.pop();
					brackets.pop();
					while(!stack.peek().equals("(")){
						String a = stack.pop();
						if(a.indexOf(',') == -1)
							a = a + ",1";
						
						String[] temp = a.split(",");
						
						int value = Integer.parseInt(num) * Integer.parseInt(temp[1]);  
						if(brackets.isEmpty()){
							map.put(temp[0], map.containsKey(temp[0]) ? map.get(temp[0]) + value : value);		
						} else {
							brackets.push(temp[0] + "," + value);
						}
					}
					
					stack.pop();
					while(!brackets.isEmpty() && !brackets.peek().equals("("))
						stack.push(brackets.pop());
					
				} else if(brackets.isEmpty()){
					key = stack.pop();
					map.put(key, map.containsKey(key) ? map.get(key) + Integer.parseInt(num) : Integer.parseInt(num));
				} else {
					stack.push(stack.pop() + "," + num);	
				}
			} else if(formula.charAt(i) - '0' > 48 && formula.charAt(i) - '0' < 75){
				stack.push(stack.pop() + formula.charAt(i));
			} else {
				stack.push(formula.substring(i, i + 1));
			}
		}
		
		while(!stack.isEmpty()){
			key = stack.pop();
			map.put(key, map.containsKey(key) ? map.get(key)+1 : 1);
		}

		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, Integer> entry : map.entrySet())
			sb.append(entry.getKey() + entry.getValue());
		
		return sb.toString();
	}
	
	private void form(String formula){
		if(formula == null || formula.isEmpty()){
			System.out.println("Empty");
			return;
		}
		Stack<String> stack = new Stack<>();
		for(int i = 0; i < formula.length(); i++){
			String num = "";
			if(Character.isDigit(formula.charAt(i))){
				while(i < formula.length() && Character.isDigit(formula.charAt(i)))
					num = num + formula.charAt(i++);
				i--;
				stack.push(num);
			} else if(Character.isLowerCase(formula.charAt(i))){
				stack.push(stack.pop() + formula.charAt(i));
			} else {
				stack.push(formula.substring(i, i + 1));
			}
		}
		while(!stack.isEmpty())
			System.out.print(stack.pop() + " ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		NumberOfAtoms num = new NumberOfAtoms();
		String[] formulas = new String[]{
			"",
			null,
			"H", 
			"H2",
			"H2O",
			"H2O2",
			"H2O2He3Mg4",
			"(H2O2)3",
			"Mg(OH)2",
			"K4(ON(SO3)2)2",
			"Be32",
			"H11He49NO35B7N46Li20",
			"(B2O39He17BeBe49)39"
		};
		
		System.out.println(num.count("H11He49NO35B7N46Li20"));
		
		for(String item : formulas)
			System.out.println(num.count(item));
		
	}
}
