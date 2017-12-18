package com.json;

/**
 * Enum type for logical and & or operator
 * @author sriee
 *
 */
public enum BooleanOperator implements Operator{
	AND, OR;
	
	@Override
	public Operator getOperator(String text){
		return (text.equals("&")) ? AND : OR;
	}
	
	@Override
	public String toString(){
		return (this == AND) ? "&" : "|";
	}
}
