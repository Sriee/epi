package com.parser;

import com.entity.Action;
import com.entity.Environment;
import com.entity.Trigger;
import com.logger.FileLogger;

import java.util.List;

public class Container {

	private String expression;
    private String ruleName;
    private List<Trigger> triggerList;
    private List<Environment> environmentList;
    private List<Action> actionList;

    public Container(){}

	/**
	 * @param expression
	 * @param ruleName
	 * @param triggerList
	 * @param environmentList
	 * @param actionList
	 */
	public Container(String expression, String ruleName, List<Trigger> triggerList, List<Environment> environmentList,
			List<Action> actionList) {
		this.expression = expression;
		this.ruleName = ruleName;
		this.triggerList = triggerList;
		this.environmentList = environmentList;
		this.actionList = actionList;
	}

	public boolean checkConflict(Container other){
		FileLogger log = FileLogger.instance();
		log.writeLog("Should Implement check conflict method.");
		// Implementation of Rule conflict algorithm goes here
		return true;
	}
	
	/**
	 * @return the expression
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * @param expression the expression to set
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}

	/**
	 * @return the ruleName
	 */
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * @param ruleName the ruleName to set
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	/**
	 * @return the triggerList
	 */
	public List<Trigger> getTriggerList() {
		return triggerList;
	}

	/**
	 * @param triggerList the triggerList to set
	 */
	public void setTriggerList(List<Trigger> triggerList) {
		this.triggerList = triggerList;
	}

	/**
	 * @return the environmentList
	 */
	public List<Environment> getEnvironmentList() {
		return environmentList;
	}

	/**
	 * @param environmentList the environmentList to set
	 */
	public void setEnvironmentList(List<Environment> environmentList) {
		this.environmentList = environmentList;
	}

	/**
	 * @return the actionList
	 */
	public List<Action> getActionList() {
		return actionList;
	}

	/**
	 * @param actionList the actionList to set
	 */
	public void setActionList(List<Action> actionList) {
		this.actionList = actionList;
	}

	@Override
	public String toString() {
		return "Container [expression=" + expression + ", ruleName=" + ruleName + ", triggerList=" + triggerList
				+ ", environmentList=" + environmentList + ", actionList=" + actionList + "]";
	}
}
