package com.json;

import java.util.Map;

public class Input {
	private String conditionalExpression;
	private String actionExpression;
	private int conditionCount;
	private int actionCount;
	private Map<String, Literals> literals;
	
	public Input() {}

	public Input(String conditionalExpression, String actionExpression, int conditionCount, int actionCount,
			Map<String, Literals> literals) {
		this.conditionalExpression = conditionalExpression;
		this.actionExpression = actionExpression;
		this.conditionCount = conditionCount;
		this.actionCount = actionCount;
		this.literals = literals;
	}

	public String getConditionalExpression() {
		return conditionalExpression;
	}

	public void setConditionalExpression(String conditionalExpression) {
		this.conditionalExpression = conditionalExpression;
	}

	public String getActionExpression() {
		return actionExpression;
	}

	public void setActionExpression(String actionExpression) {
		this.actionExpression = actionExpression;
	}

	public int getConditionCount() {
		return conditionCount;
	}

	public void setConditionCount(int conditionCount) {
		this.conditionCount = conditionCount;
	}

	public int getActionCount() {
		return actionCount;
	}

	public void setActionCount(int actionCount) {
		this.actionCount = actionCount;
	}

	public Map<String, Literals> getLiterals() {
		return literals;
	}

	public void setLiterals(Map<String, Literals> literals) {
		this.literals = literals;
	}

	@Override
	public String toString() {
		return "Input [conditionalExpression=" + conditionalExpression + ", actionExpression=" + actionExpression
				+ ", conditionCount=" + conditionCount + ", actionCount=" + actionCount + "]";
	}
}
