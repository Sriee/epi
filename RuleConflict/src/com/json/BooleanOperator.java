package com.json;

public enum BooleanOperator {
	AND, OR;
	
	public static BooleanOperator getBooleanOperator(String text){
		return (text.equals("&")) ? AND : OR;
	}
	
	public static String toString(BooleanOperator op){
		return (op == AND) ? "&" : "|";
	}
}
