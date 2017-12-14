package com.exceptions;

/**
 * Exception for Invalid Expression Syntax
 * @author sriee
 *
 */
public class InvalidExpressionSyntaxException extends Exception{

	private static final long serialVersionUID = 8409329196170357282L;
	public InvalidExpressionSyntaxException() { super(); }
	public InvalidExpressionSyntaxException(String message) { super(message); }
	public InvalidExpressionSyntaxException(String message, Throwable cause) { super(message, cause); }
	public InvalidExpressionSyntaxException(Throwable cause) { super(cause); }

}
