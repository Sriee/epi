package com.aquafonics;

public class Condition {
	
	private String name = null;
	private Interval interval = null;
	private String operator = null;
	private boolean value = false;
	
	public Condition(String name, Interval interval, String operator, boolean value) {
		super();
		this.name = name;
		this.interval = interval;
		this.operator = operator;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Interval getInterval() {
		return interval;
	}

	public void setInterval(Interval interval) {
		this.interval = interval;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Condition [name=" + name + ", interval=" + interval + ", operator=" + operator + ", value=" + value
				+ "]";
	}
}
