package com.rule;

import com.json.LogicalOperator;

public class Trigger {
	int triggerId;
	String name;
	LogicalOperator operator;
	int value;
	int priority;
	
	public Trigger() { this(-1, null, null, -1, -1); }

	/**
	 * @param triggerId
	 * @param operator
	 * @param value
	 * @param priority
	 */
	public Trigger(int triggerId, String name, LogicalOperator operator, int value, int priority) {
		this.triggerId = triggerId;
		this.name = name;
		this.operator = operator;
		this.value = value;
		this.priority = priority;
	}

	
	/**
	 * @param triggerId
	 * @param name
	 * @param operator
	 * @param value
	 */
	public Trigger(int triggerId, String name, LogicalOperator operator, int value) {
		this(triggerId, name, operator, value, -1);
	}

	/**
	 * @param triggerId
	 * @param operator
	 * @param value
	 */
	public Trigger(int triggerId, LogicalOperator operator, int value) {
		this(triggerId, null, operator, value, -1);
	}

	public String toExpressionString(){
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(this.name + " " + this.operator.toString() + " " + this.value);
		sb.append(")");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "Trigger [triggerId=" + triggerId + ", name=" + name + ", operator=" + operator + ", value=" + value
				+ ", priority=" + priority + "]";
	}	
}
