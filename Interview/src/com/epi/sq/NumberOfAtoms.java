package com.epi.sq;

import java.util.*;

public class NumberOfAtoms {
	
	/**
	 * Leet Code problem. Solution -> Accepted
	 * 
	 * @param formula 
	 * @return number of atoms for each element
	 */
	public String count(String formula){
		if(formula == null || formula.isEmpty())
			return "";
		
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
			map.put(key, map.containsKey(key) ? map.get(key) + 1 : 1);
		}

		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, Integer> entry : map.entrySet()){
			sb.append(entry.getKey());
			if(entry.getValue() != 1)
				sb.append(entry.getValue());
		}
		
		return sb.toString();
	}
	
	/*
    public String countOfAtoms(String formula) {
        int[][] dict = new int[26][27];
        helper(dict, formula, 0, formula.length(), 1);
        StringBuilder res = new StringBuilder();
        for(int i = 0; i< 26; i++){
            for(int j = 0; j< 27; j++){
                if(dict[i][j] == 0){
                    continue;
                }
                res.append((char) (i+'A'));
                if(j>0){
                    res.append((char)(j+'a'-1));
                }
                if(dict[i][j]>1){
                    res.append(dict[i][j]);
                }
            }
        }
        return res.toString();
    }
    
    public void helper(int[][] dict, String s, int start, int end, int mult){
        if(start == end){
            return;
        }

        if(s.charAt(start) == '('){
            int j = start+1;
            for(int diff = 1; diff>0 ; j++){
                if(s.charAt(j) == '('){
                    diff++;
                }
                if(s.charAt(j) == ')'){
                    diff--;
                }
                
                if(diff == 0){
                    break;
                }
            }
            
            int count = 0;
            int i = j+1;
            
            while(i< end && Character.isDigit(s.charAt(i))){
                count = count*10+s.charAt(i++)- '0';
                
            }
            
            if(count == 0){
                count = 1;
            }
            
            helper(dict, s, start+1, j, mult*count);
            helper(dict, s, i,end, mult);
        }
        else{
            int i = s.charAt(start++) - 'A';
            int j = 0;
            
            if(start< end && s.charAt(start) >= 'a' && s.charAt(start) <= 'z' ){
                j = s.charAt(start++)-'a'+1;
            }
            
            int count = 0;
            
            while(start< end && Character.isDigit(s.charAt(start))){
                count = count*10+ s.charAt(start++)-'0';
            }
            
            if(count == 0){
                count = 1;
            }
            
            dict[i][j] += count * mult;
            
            helper(dict, s, start, end, mult);
        }
    }
	* */
}
