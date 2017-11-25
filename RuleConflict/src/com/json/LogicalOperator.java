package com.json;

public enum LogicalOperator {
	LESSER_THAN,
	GREATER_THAN,
	LESSER_THAN_OR_EQUAL,
	GREATER_THAN_OR_EQUAL,
	EQUAL,
	NOT_EQUAL;
	
	public static LogicalOperator getLogicalOperator(String text){
		if(text.equals("<")){
			return LESSER_THAN;
		} else if(text.equals(">")){
			return GREATER_THAN;
		} else if(text.equals("<=")){
			return LESSER_THAN_OR_EQUAL;
		} else if(text.equals(">=")){
			return GREATER_THAN_OR_EQUAL;
		} else if(text.equals("!=")){
			return NOT_EQUAL;
		} else {
			return EQUAL;
		}
	}
	
	public static String toString(LogicalOperator op){
		if(op == LESSER_THAN){
			return "<";
		} else if(op == GREATER_THAN){
			return ">";
		} else if(op == LESSER_THAN_OR_EQUAL){
			return "<=";
		} else if(op == GREATER_THAN_OR_EQUAL){
			return ">=";
		} else if(op == NOT_EQUAL){
			return "!=";
		} else{
			return "=";
		}
	}
}
