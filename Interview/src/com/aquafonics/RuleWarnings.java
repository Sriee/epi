package com.aquafonics;

public class RuleWarnings extends Exception{

	private static final long serialVersionUID = -3089178816045813645L;

	public RuleWarnings() { super(); }
	public RuleWarnings(String message) { super(message); }
	public RuleWarnings(String message, Throwable cause) { super(message, cause); }
	public RuleWarnings(Throwable cause) { super(cause); }
}
