package com.aquafonics;

import java.util.Stack;

public class Eval {

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
	
	private boolean hasPrecedence(char existingOp, char newOp) {
		if(newOp == '(' || newOp == ')') 
			return false;
		else if((existingOp == '*' || existingOp == '/') && (newOp == '+' || newOp == '-'))
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		Eval e = new Eval();
		e.evaluate("(1 + 2) * 3");
		e.evaluate("100 * 2 + 12");
	    e.evaluate("100 * ( 2 + 12 )");
	    e.evaluate("100 * ( 2 + 12 ) / 14");
	}

}
