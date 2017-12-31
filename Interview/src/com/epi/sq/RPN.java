package com.epi.sq;

import java.util.Deque;
import java.util.LinkedList;

public class RPN {
	public long evaluateRpnExpression(String expression){
		if(expression == null)
			return 0;
		
		expression = expression.trim();
		if(expression.isEmpty())
			return 0;
		
		if(expression.length() == 1)
			return Long.parseLong(expression);
		
		String[] tokens = expression.split(",");
		Deque<Long> valueStack = new LinkedList<>();
		Deque<Character> operatorStack = new LinkedList<>();
		for(String token : tokens){
			if(token.isEmpty())
				continue;
			
			if(!this.isExpression(token)){
				valueStack.push(Long.parseLong(token));
			} else {
				if(!operatorStack.isEmpty() && this.hasPrecedence(token.charAt(0), operatorStack.peek())) 
					valueStack.push(this.evaluate(valueStack.pop(), valueStack.pop(), operatorStack.pop()));
				
				operatorStack.push(token.charAt(0));
			}
		}
		
		while(!operatorStack.isEmpty()) {
			valueStack.push(this.evaluate(valueStack.pop(), valueStack.pop(), operatorStack.pop()));
		}
		return valueStack.pop();
	}
	
	private boolean isExpression(String token){
		if(token.length() != 1) return false;
		
		return token.equals("*") || token.equals("/") || token.equals("+") || token.equals("-");  
	}
	
	private boolean hasPrecedence(char existingOp, char newOp){
		if((existingOp == '*' || existingOp == '/') && (newOp == '+' || newOp == '-'))
			return false;
		return true;
	}
	
	private Long evaluate(Long a, Long b, char operator){
		Long result = null;
		
		switch(operator){
		case '+':
			result = a + b;
			break;
		case '-':
			result = a - b;
			break;
		case '*':
			result = a * b;
			break;
		case '/':
			if(b == 0)
				throw new IllegalArgumentException("Argument 'divisor' is 0.");
			result = a / b;
			break;
		}
		return result;
	}
}
