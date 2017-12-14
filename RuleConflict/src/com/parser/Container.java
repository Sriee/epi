package com.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entity.Action;
import com.entity.Environment;
import com.entity.Trigger;
import com.exceptions.DirectDependenceConflict;
import com.exceptions.EnvironmentMutualConflict;
import com.exceptions.ExecutionConflict;
import com.exceptions.RuleConflict;
import com.exceptions.ShadowConflict;
import com.json.LogicalOperator;
import com.logger.FileLogger;
import com.rule.Interval;
import com.rule.Relation;

public class Container {

	private String expression;
	private String ruleName;
	private List<Trigger> triggerList;
	private List<Environment> environmentList;
	private List<Action> actionList;

	public Container() {
	}

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
		this.triggerList = (triggerList != null) ? triggerList : new ArrayList<>();
		this.environmentList = (environmentList != null) ? environmentList : new ArrayList<>();
		this.actionList = (actionList != null) ? actionList : new ArrayList<>();
	}

	public void checkConflict(Container other) throws RuleConflict {
		FileLogger log = FileLogger.instance();
		Map<Relation, Boolean> relation = this.relation(other);

		log.writeLog(String.format("Relation between %s & %s:", this.getRuleName(), other.getRuleName()));
		log.writeLog(relation.toString());

		if (relation.get(Relation.SIMILAR_TRIGGER)) {
			if (relation.get(Relation.CONTORARY_POSTSTATE))
				throw new EnvironmentMutualConflict();
			if (relation.get(Relation.SIMILAR_ACTION))
				throw new ShadowConflict();
		}

		if (relation.get(Relation.TRIGGER_EVENT) || relation.get(Relation.SIMILAR_TRIGGER)) {
			if (relation.get(Relation.NEGATIVE_ACTION))
				throw new ExecutionConflict();
		}

		if (relation.get(Relation.EXPLICIT_DEPENDENCY) || relation.get(Relation.IMPLICIT_DEPENDENCY)) {
			if (relation.get(Relation.CONTRARY_EXPLICIT_DEPENDENCY)
					|| relation.get(Relation.CONTRARY_IMPLICIT_DEPENDENCY))
				throw new DirectDependenceConflict();
		}
	}

	private Map<Relation, Boolean> relation(Container other) {
		Map<Relation, Boolean> relationMap = new HashMap<>();
		Trigger triggerRb = null;
		Environment envRb = null;

		for (Relation r : Relation.values())
			relationMap.put(r, false);

		for (int i = 0; i < other.triggerList.size(); i++) {
			triggerRb = other.triggerList.get(i);
			envRb = other.environmentList.get(i);
			for (Trigger Ra : this.triggerList) {

				// Similar Trigger
				if (Ra.getId() == triggerRb.getId() && this.contains(Ra.getOperator().toString(), Ra.getValue(),
						triggerRb.getOperator().toString(), triggerRb.getValue())) {
					relationMap.put(Relation.SIMILAR_TRIGGER, true);
				}

				// Contrary Implicit Dependence
				if (Ra.getId() == envRb.getId() && this.contains(envRb.getNextState().split(";")[0],
						Integer.parseInt(envRb.getNextState().split(";")[1]), Ra.getOperator().toString(),
						Ra.getValue())) {
					relationMap.put(Relation.CONTRARY_IMPLICIT_DEPENDENCY, true);
				}

				// Contrary Explicit Dependence
				for (Action actionRb : other.actionList) {
					if (Ra.getId() == actionRb.getId() && this.contains(actionRb.getOperator().toString(),
							actionRb.getValue(), Ra.getOperator().toString(), Ra.getValue())) {
						relationMap.put(Relation.CONTRARY_EXPLICIT_DEPENDENCY, true);
					}
				}
			}
			
			// For each Ra.action do
			for (Action actionRa : this.actionList) {
				
				// Explicit Dependency
				if (actionRa.getId() == triggerRb.getId() && this.contains(actionRa.getOperator().toString(),
						actionRa.getValue(), triggerRb.getOperator().toString(), triggerRb.getValue())) {
					relationMap.put(Relation.EXPLICIT_DEPENDENCY, true);
				}

				// Similar Action
				for (Action actionRb : other.actionList) {
					if (actionRa.getActuatorId().getId() == actionRb.getActuatorId().getId()) {
						if(actionRa.getOperator() == actionRb.getOperator() && 
								actionRa.getValue() == actionRb.getValue()){
							relationMap.put(Relation.SIMILAR_ACTION, true);
						}
						
						if(actionRa.getOperator() == actionRb.getOperator() && 
								actionRa.getValue() != actionRb.getValue()){
							relationMap.put(Relation.NEGATIVE_ACTION, true);
							relationMap.put(Relation.TRIGGER_EVENT, true);
						} else {
							if (this.exclude(actionRa.getOperator().toString(), actionRa.getValue(),
								actionRb.getOperator().toString(), actionRb.getValue())) {
									relationMap.put(Relation.NEGATIVE_ACTION, true);
							}
						}
					}
				}
			}
			
			// For each Ra.envir do
			for(Environment envRa : this.environmentList){
				if(envRa.getId() == envRb.getId()){
					if(this.contains(envRa.getPreviousState().split(";")[0], Integer.parseInt(envRa.getPreviousState().split(";")[1]), 
							envRb.getPreviousState().split(";")[0], Integer.parseInt(envRb.getPreviousState().split(";")[1]))){
						relationMap.put(Relation.SIMILAR_PRESTATE, true);
					}
					
					if(this.contains(envRa.getNextState().split(";")[0], Integer.parseInt(envRa.getNextState().split(";")[1]),
							envRb.getNextState().split(";")[0], Integer.parseInt(envRb.getNextState().split(";")[1]))){
						relationMap.put(Relation.CONTORARY_POSTSTATE, true);
					}
					
					if(this.contains(envRa.getPreviousState().split(";")[0], Integer.parseInt(envRa.getPreviousState().split(";")[1]),
							envRb.getNextState().split(";")[0], Integer.parseInt(envRb.getNextState().split(";")[1]))){
						relationMap.put(Relation.TRIGGER_EVENT, true);
					}
				}
				
				if(envRa.getId() == triggerRb.getId() && this.contains(
						envRa.getNextState().split(";")[0], Integer.parseInt(envRa.getNextState().split(";")[1]),
						triggerRb.getOperator().toString(), triggerRb.getValue())
						){
					relationMap.put(Relation.IMPLICIT_DEPENDENCY, true);
				}
			}
		}

		return relationMap;
	}

	private boolean contains(String raOperator, int raValue, String rbOperator, int rbValue) {
		FileLogger log = FileLogger.instance();
		LogicalOperator operator = LogicalOperator.LESSER_THAN;
		
		if(raOperator.equals("=") && rbOperator.equals("!="))
			return true;
		
		if(raOperator.equals("!=") && rbOperator.equals("="))
			return true;
		
		Interval firstInterval = this.getInterval((LogicalOperator) operator.getOperator(raOperator), raValue, true);
		log.writeLog("First Interval: " + firstInterval.toString());

		Interval secondInterval = this.getInterval((LogicalOperator) operator.getOperator(rbOperator), rbValue, true);
		log.writeLog("Second Interval: " + secondInterval.toString());

		return firstInterval.intersects(secondInterval);
	}

	private boolean exclude(String raOperator, int raValue, String rbOperator, int rbValue) {
		FileLogger log = FileLogger.instance();
		LogicalOperator operator = LogicalOperator.LESSER_THAN;

		Interval firstInterval = this.getInterval((LogicalOperator) operator.getOperator(raOperator), raValue, true);
		log.writeLog("First Interval: " + firstInterval.toString());

		Interval secondInterval = this.getInterval(
				this.getOppositeOperator((LogicalOperator) operator.getOperator(rbOperator)), rbValue, false);
		log.writeLog("Second Interval: " + secondInterval.toString());

		return firstInterval.intersects(secondInterval);
	}

	/**
	 * Helper function to create the interval based on operator and threshold
	 * value
	 * 
	 * @param operator (<, <=, >, >=)
	 * 
	 * @param thresholdValue
	 * 
	 * @param flag true: while adding action false: while adding opposite
	 * actions
	 * 
	 * @return Interval object
	 */
	private Interval getInterval(LogicalOperator operator, int thresholdValue, boolean flag) {
		Interval newInterval = null;
		int min = 0, max = 150;
		switch (operator) {
		case LESSER_THAN:
			if (flag)
				newInterval = new Interval(min, thresholdValue - 1);
			else
				newInterval = new Interval(thresholdValue, max);
			break;
		case LESSER_THAN_OR_EQUAL:
			if (flag)
				newInterval = new Interval(min, thresholdValue);
			else
				newInterval = new Interval(thresholdValue + 1, max);
			break;
		case GREATER_THAN:
			if (flag)
				newInterval = new Interval(thresholdValue + 1, max);
			else
				newInterval = new Interval(min, thresholdValue);
			break;
		case GREATER_THAN_OR_EQUAL:
			if (flag)
				newInterval = new Interval(thresholdValue, max);
			else
				newInterval = new Interval(min, thresholdValue - 1);
			break;
		case EQUAL:
			if (flag)
				newInterval = new Interval(thresholdValue, thresholdValue);
			break;
		case NOT_EQUAL:
			if (!flag)
				newInterval = new Interval(thresholdValue, thresholdValue);
			break;
		default:
			break;
		}
		return newInterval;
	}

	/**
	 * Helper function to retrieve the opposite operator
	 * 
	 * @param operator
	 *            (<, <=, >, >=)
	 * @return opposite operator
	 */
	private LogicalOperator getOppositeOperator(LogicalOperator operator) {
		LogicalOperator oppositeOperator = null;
		switch (operator) {
		case LESSER_THAN:
			oppositeOperator = LogicalOperator.GREATER_THAN_OR_EQUAL;
			break;
		case GREATER_THAN:
			oppositeOperator = LogicalOperator.LESSER_THAN_OR_EQUAL;
			break;
		case LESSER_THAN_OR_EQUAL:
			oppositeOperator = LogicalOperator.GREATER_THAN;
			break;
		case GREATER_THAN_OR_EQUAL:
			oppositeOperator = LogicalOperator.LESSER_THAN;
			break;
		case EQUAL:
			oppositeOperator = LogicalOperator.NOT_EQUAL;
			break;
		case NOT_EQUAL:
			oppositeOperator = LogicalOperator.EQUAL;
			break;
		}
		return oppositeOperator;
	}

	/**
	 * @return the expression
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * @param expression
	 *            the expression to set
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
	 * @param ruleName
	 *            the ruleName to set
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
	 * @param triggerList
	 *            the triggerList to set
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
	 * @param environmentList
	 *            the environmentList to set
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
	 * @param actionList
	 *            the actionList to set
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
