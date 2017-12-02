package com.rule;

import java.util.List;

import com.entity.*;
import com.logger.FileLogger;

public class RuleHelper {
	
	String expression;
	List<Trigger> triggers;
	List<Environment> environments;
	List<Action> action;
	
	public RuleHelper() { this(null, null, null, null); }
	
	/**
	 * @param expression
	 * @param triggers
	 * @param environments
	 * @param action
	 */
	public RuleHelper(String expression, List<Trigger> triggers, List<Environment> environments, List<Action> action) {
		this.expression = expression;
		this.triggers = triggers;
		this.environments = environments;
		this.action = action;
	}

	/**
	 * @param triggers
	 * @param environments
	 * @param action
	 */
	public RuleHelper(List<Trigger> triggers, List<Environment> environments, List<Action> action) {
		this(null, triggers, environments, action);
	}

	public boolean checkConflict(RuleHelper otherRule){
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
	 * @return the triggers
	 */
	public List<Trigger> getTriggers() {
		return triggers;
	}

	/**
	 * @param triggers the triggers to set
	 */
	public void setTriggers(List<Trigger> triggers) {
		this.triggers = triggers;
	}

	/**
	 * @return the environments
	 */
	public List<Environment> getEnvironments() {
		return environments;
	}

	/**
	 * @param environments the environments to set
	 */
	public void setEnvironments(List<Environment> environments) {
		this.environments = environments;
	}

	/**
	 * @return the actuators
	 */
	public List<Action> getActuators() {
		return action;
	}

	/**
	 * @param actuators the actuators to set
	 */
	public void setActuators(List<Action> action) {
		this.action = action;
	}

	public int numOfTrigges(){ return (this.triggers == null)? 0 : this.triggers.size(); }
	
	public int numOfEnvironment(){ return (this.environments == null) ? 0 : this.environments.size(); }
	
	public int numOfActuators(){ return (this.action == null) ? 0 : this.action.size(); }

	@Override
	public String toString(){
		StringBuilder lhs = new StringBuilder(), rhs = new StringBuilder();
		
		for(Trigger trg : this.triggers){
			lhs.append(trg.toString());
			lhs.append(" & ");
		}
		lhs.setLength(lhs.length() - 3);

		for(Action act : this.action){
			rhs.append(act.toString());
			rhs.append(" & ");
		}
		rhs.setLength(rhs.length() - 3);
		return lhs.toString() + " -> " + rhs.toString();
	}
}
