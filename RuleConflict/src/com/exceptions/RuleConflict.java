package com.exceptions;

public class RuleConflict extends Exception{
	
	private static final long serialVersionUID = 460607590627711350L;
	
	public RuleConflict() { super(); }
	public RuleConflict(String message) { super(message); }
	public RuleConflict(String message, Throwable cause) { super(message, cause); }
	public RuleConflict(Throwable cause) { super(cause); }
}