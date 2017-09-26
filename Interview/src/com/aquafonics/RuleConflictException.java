package com.aquafonics;

public class RuleConflictException extends Exception{

	private static final long serialVersionUID = -3563222641514466917L;

	public RuleConflictException() { super(); }
	public RuleConflictException(String message) { super(message); }
	public RuleConflictException(String message, Throwable cause) { super(message, cause); }
	public RuleConflictException(Throwable cause) { super(cause); }
}
