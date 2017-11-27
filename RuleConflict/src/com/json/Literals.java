package com.json;

public class Literals {
	private String name;
	private LogicalOperator operator;
	private int value;
	
	public Literals() {}

	/**
	 * @param name
	 * @param operator
	 * @param value
	 */
	public Literals(String name, LogicalOperator operator, int value) {
		this.name = name;
		this.operator = operator;
		this.value = value;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the operator
	 */
	public LogicalOperator getOperator() {
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(LogicalOperator operator) {
		this.operator = operator;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Literals [name=" + name + ", operator=" + operator + ", value=" + value + "]";
	}
		
}
