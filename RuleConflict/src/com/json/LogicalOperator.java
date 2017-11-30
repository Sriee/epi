package com.json;

public enum LogicalOperator implements Operator{
	LESSER_THAN,
	GREATER_THAN,
	LESSER_THAN_OR_EQUAL,
	GREATER_THAN_OR_EQUAL,
	EQUAL,
	NOT_EQUAL;
	
	@Override
	public Operator getOperator(String text){
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
	
	@Override
	public String toString(){
		if(this == LESSER_THAN){
			return "<";
		} else if(this == GREATER_THAN){
			return ">";
		} else if(this == LESSER_THAN_OR_EQUAL){
			return "<=";
		} else if(this == GREATER_THAN_OR_EQUAL){
			return ">=";
		} else if(this == NOT_EQUAL){
			return "!=";
		} else{
			return "=";
		}
	}

}
