package com.entity;

import javax.persistence.*;

import com.json.LogicalOperator;

@Entity
@Table(name = "Action")
public class Action {
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;
	
	@OneToOne
	@JoinColumn(name = "actuator_id")
	private Actuator actuatorId;
	
	@Column(name = "operator")
	private String operator; 
	
	@Column(name = "value")
	private int value;
	
	@Column(name = "priority")
	private int priority;
	
	public Action() {}
	/**
	 * @param name
	 * @param actuatorId
	 * @param operator
	 * @param value
	 */
	public Action(String name, Actuator actuatorId, LogicalOperator operator, int value) {
		this(null, name, actuatorId, operator, value, -1);
	}
	
	/**
	 * @param id
	 * @param name
	 * @param actuatorId
	 * @param operator
	 * @param value
	 */
	public Action(Long id, String name, Actuator actuatorId, LogicalOperator operator, int value) {
		this(id, name, actuatorId, operator, value, -1);
	}
	
	/**
	 * @param id
	 * @param name
	 * @param actuatorId
	 * @param operator
	 * @param value
	 * @param priority
	 */
	public Action(Long id, String name, Actuator actuatorId, LogicalOperator operator, int value, int priority) {
		this.id = id;
		this.name = name;
		this.actuatorId = actuatorId;
		this.operator = operator.toString();
		this.value = value;
		this.priority = priority;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the actuatorId
	 */
	public Actuator getActuatorId() {
		return actuatorId;
	}

	/**
	 * @param actuatorId the actuatorId to set
	 */
	public void setActuatorId(Actuator actuatorId) {
		this.actuatorId = actuatorId;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(LogicalOperator operator) {
		this.operator = operator.toString();
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
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
		return "Action [id=" + id + ", name=" + name + ", actuatorId=" + actuatorId.getId() + ", operator=" + operator
				+ ", value=" + value + ", priority=" + priority + "]";
	}	
}
