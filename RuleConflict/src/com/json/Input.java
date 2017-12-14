package com.json;

import java.util.Map;

/**
 * POJO for mapping Deserialized json object
 * 
 * @author sriee
 *
 */
public class Input {
	private String conditionalExpression;
	private String actionExpression;
	private int conditionCount;
	private int actionCount;
	private Map<String, Literals> literals;
	
	public Input() {}

	
	/**
	 * @param conditionalExpression
	 * @param actionExpression
	 * @param conditionCount
	 * @param actionCount
	 * @param literals
	 */
	public Input(String conditionalExpression, String actionExpression, int conditionCount, int actionCount,
			Map<String, Literals> literals) {
		this.conditionalExpression = conditionalExpression;
		this.actionExpression = actionExpression;
		this.conditionCount = conditionCount;
		this.actionCount = actionCount;
		this.literals = literals;
	}


	/**
	 * @return the conditionalExpression
	 */
	public String getConditionalExpression() {
		return conditionalExpression;
	}

	/**
	 * @param conditionalExpression the conditionalExpression to set
	 */
	public void setConditionalExpression(String conditionalExpression) {
		this.conditionalExpression = conditionalExpression;
	}

	/**
	 * @return the actionExpression
	 */
	public String getActionExpression() {
		return actionExpression;
	}

	/**
	 * @param actionExpression the actionExpression to set
	 */
	public void setActionExpression(String actionExpression) {
		this.actionExpression = actionExpression;
	}

	/**
	 * @return the conditionCount
	 */
	public int getConditionCount() {
		return conditionCount;
	}

	/**
	 * @param conditionCount the conditionCount to set
	 */
	public void setConditionCount(int conditionCount) {
		this.conditionCount = conditionCount;
	}

	/**
	 * @return the actionCount
	 */
	public int getActionCount() {
		return actionCount;
	}

	/**
	 * @param actionCount the actionCount to set
	 */
	public void setActionCount(int actionCount) {
		this.actionCount = actionCount;
	}

	/**
	 * @return the literals
	 */
	public Map<String, Literals> getLiterals() {
		return literals;
	}

	/**
	 * @param literals the literals to set
	 */
	public void setLiterals(Map<String, Literals> literals) {
		this.literals = literals;
	}

	@Override
	public String toString() {
		return "Input [conditionalExpression=" + conditionalExpression + ", actionExpression=" + actionExpression
				+ ", conditionCount=" + conditionCount + ", actionCount=" + actionCount + "]";
	}
}
