package com.rule;

import java.util.*;

public class Rule {
	
	String expression;
	List<Trigger> triggers;
	List<Environment> environments;
	List<Actuator> actuators;
	
	public Rule() { this(null, null, null, null); }
	
	/**
	 * @param expression
	 * @param triggers
	 * @param environments
	 * @param actuators
	 */
	public Rule(String expression, List<Trigger> triggers, List<Environment> environments, List<Actuator> actuators) {
		this.expression = expression;
		this.triggers = triggers;
		this.environments = environments;
		this.actuators = actuators;
	}


	/**
	 * @param triggers
	 * @param environments
	 * @param actuators
	 */
	public Rule(List<Trigger> triggers, List<Environment> environments, List<Actuator> actuators) {
		this(null, triggers, environments, actuators);
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
	public List<Actuator> getActuators() {
		return actuators;
	}

	/**
	 * @param actuators the actuators to set
	 */
	public void setActuators(List<Actuator> actuators) {
		this.actuators = actuators;
	}

	public int numOfTrigges(){ return (this.triggers == null)? 0 : this.triggers.size(); }
	
	public int numOfEnvironment(){ return (this.environments == null) ? 0 : this.environments.size(); }
	
	public int numOfActuators(){ return (this.actuators == null) ? 0 : this.actuators.size(); }

	@Override
	public String toString(){
		StringBuilder lhs = new StringBuilder(), rhs = new StringBuilder();
		
		for(Trigger trg : this.triggers){
			lhs.append(trg.toString());
			lhs.append(" & ");
		}
		lhs.setLength(lhs.length() - 2);

		for(Actuator act : this.actuators){
			rhs.append(act.toString());
			rhs.append(" & ");
		}
		rhs.setLength(rhs.length() - 2);
		return lhs.toString() + " -> " + rhs.toString();
	}
}
