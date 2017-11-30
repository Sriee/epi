package com.rule;

import com.json.LogicalOperator;

public class Actuator {
	int actuatorId;
	String name;
	LogicalOperator operator;
	int value;
	
	public Actuator() { this(-1, null, null, -1); }

	/**
	 * @param actuatorId
	 * @param operator
	 * @param value
	 */
	public Actuator(int actuatorId, String name, LogicalOperator operator, int value) {
		this.actuatorId = actuatorId;
		this.name = name;
		this.operator = operator;
		this.value = value;
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
		return "Actuator [actuatorId=" + actuatorId + ", name=" + name + ", operator=" + operator + ", value=" + value
				+ "]";
	}
}
