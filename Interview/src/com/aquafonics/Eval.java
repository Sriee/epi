package com.aquafonics;

import java.util.Stack;
import java.util.Map;

public class Eval {
	
	public Map<String, Condition> map = null;
	
	public void evaluateBoolean(String[] tokens) {
		Stack<Boolean> value = new Stack<>();
		Stack<String> operator = new Stack<>();
		
		for(String token : tokens) {
			
			if(token.equals("(")) {
				operator.push("(");
			} else if (token.equals("&&") || token.equals("||")) {
				if(!operator.isEmpty() && this.hasPrecedence(token, operator.peek())) 
					value.push(this.evaluateOperator(operator.pop(), value.pop(), value.pop()));
				
				operator.push(token);
			} else if (token.equals(")")) {
				while(!operator.peek().equals("(")) {
					value.push(this.evaluateOperator(operator.pop(), value.pop(), value.pop()));
				}
				operator.pop();
			} else {
				value.push(this.map.get(token).isValue());
			}
		}
		while(!operator.isEmpty()) {
			value.push(this.evaluateOperator(operator.pop(), value.pop(), value.pop()));
		}
		System.out.println(value.pop());
	}
	
	private void evaluate(String expression) {
		if(expression == null) {
			System.out.println();
			return;
		}
		
		expression = expression.trim();
		if(expression.isEmpty()) { 
			System.out.println();
			return;
		}
		
		char[] expressionToken = expression.toCharArray();
		Stack<Integer> valueStack = new Stack<>();
		Stack<Character> operatorStack = new Stack<>();
		
		for(int i = 0; i < expressionToken.length; i++) {
			int value = 0;
			if(expressionToken[i] == ' ')
				continue;
			
			if(expressionToken[i] == '(') {
				operatorStack.push('(');
			} else if (expressionToken[i] >= '0' && expressionToken[i] <='9') {
				while(i < expressionToken.length && (expressionToken[i] >= '0' && expressionToken[i] <='9')) {
					value = (value * 10) + (expressionToken[i++] - '0');
				}
				i--;
				valueStack.push(value);
			} else if (expressionToken[i] == ')') {
				while(operatorStack.peek() != '(') {
					if(valueStack.isEmpty())
						throw new IllegalArgumentException("Invalid Expression Format.");
					Integer a = valueStack.pop();
					Integer b = valueStack.pop();
					if(a == null || b == null)
						throw new IllegalArgumentException("Invalid Expression Format.");
					valueStack.push(this.evaluateOperator(operatorStack.pop(), a, b));
				}
				operatorStack.pop();
			} else {
				if(!operatorStack.isEmpty() && this.hasPrecedence(expressionToken[i], operatorStack.peek())) 
					valueStack.push(this.evaluateOperator(operatorStack.pop(), valueStack.pop(), valueStack.pop()));
				
				operatorStack.push(expressionToken[i]);
			}
		}
		System.out.println(valueStack.toString() + "\n" + operatorStack.toString());
		while(!operatorStack.isEmpty()) {
			valueStack.push(this.evaluateOperator(operatorStack.pop(), valueStack.pop(), valueStack.pop()));
		}
		System.out.println(valueStack.pop());
	}
	
	private int evaluateOperator(char op, int b, int a) {
		int result = -1;
		switch(op) {
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
			result = a / b;
			break;
		}
		return result;
	}
	
	private boolean evaluateOperator(String operator, boolean b, boolean a) {
		boolean result = false;
		char op = operator.equals("&&") ? '&' : '|';
		
		switch(op) {
		case '&':
			result = a && b; 
			break;
		case '|':
			result = a || b;
			break;
		}
		return result;
	}
	
	private boolean hasPrecedence(char existingOp, char newOp) {
		if(newOp == '(' || newOp == ')') 
			return false;
		else if((existingOp == '*' || existingOp == '/') && (newOp == '+' || newOp == '-'))
			return false;
		return true;
	}

	private boolean hasPrecedence(String existingOp, String newOp) {
		if(newOp.equals("(") || newOp.equals(")")) 
			return false;
		else if(existingOp.equals("&&")  && newOp.equals("||"))
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		Eval e = new Eval();
		/*e.evaluate("(1 + 2) * 3");
		e.evaluate("100 * 2 + 12");
	    e.evaluate("100 * ( 2 + 12 )");*/
	    e.evaluate("100 * ( 2 + (12+23-(28-5)))/ 14");
	}

}
