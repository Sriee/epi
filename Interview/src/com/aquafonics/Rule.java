package com.aquafonics;

public class Rule {
	private String action;
	private String actuator;
	
	public Rule(String action, String actuator) {
		super();
		this.action = action;
		this.actuator = actuator;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getActuator() {
		return actuator;
	}
	public void setActuator(String actuator) {
		this.actuator = actuator;
	}
	
	@Override
	public int hashCode() {
		final int prime = 197;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((actuator == null) ? 0 : actuator.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rule other = (Rule) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (actuator == null) {
			if (other.actuator != null)
				return false;
		} else if (!actuator.equals(other.actuator))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Rule [action=" + action + ", actuator=" + actuator + "]";
	}		
}
