package com.aquafonics;

public class Rule {
	private String sensor;
	private String actuator;
	
	public Rule(String sensor, String rule) {
		this.sensor = sensor;
		this.actuator = rule;
	}
	
	public String getRule() {
		return actuator;
	}

	public void setRule(String rule) {
		this.actuator = rule;
	}

	public void setSensor(String sensor) {
		this.sensor = sensor;
	}

	public String getSensor() {
		return this.sensor;
	}

	@Override
	public int hashCode() {
		final int prime = 197;
		int result = 1;
		result = prime * result + ((actuator == null) ? 0 : actuator.hashCode());
		result = prime * result + ((sensor == null) ? 0 : sensor.hashCode());
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
		if (actuator == null) {
			if (other.actuator != null)
				return false;
		} else if (!actuator.equals(other.actuator))
			return false;
		if (sensor == null) {
			if (other.sensor != null)
				return false;
		} else if (!sensor.equals(other.sensor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rule [sensor=" + sensor + ", actuator=" + actuator + "]";
	}
	
}
