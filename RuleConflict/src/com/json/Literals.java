package com.json;

/**
 * POJO for mapping condition and action elements in a Json expression
 *  
 * @author sriee
 */
public class Literals {
	private String name;
	private String id;
	private LogicalOperator operator;
	private int value;
	
	public Literals() {}

	/**
	 * @param name
	 * @param id
	 * @param operator
	 * @param value
	 */
	public Literals(String name, String id, LogicalOperator operator, int value) {
		this.name = name;
		this.id = id;
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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

	/**
	 * Helper function to return the literal in [Name operator value] form
	 * 
	 * 	Ex: Temparature > 40
	 * 
	 * @return	formatted expression
	 */
	public String toExpressionString(){
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(this.name + " " + this.operator.toString() + " " + this.value);
		sb.append(")");
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return "Literals [name=" + name + ", operator=" + operator + ", value=" + value + "]";
	}
		
}
