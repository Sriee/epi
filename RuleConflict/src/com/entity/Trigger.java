package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.json.LogicalOperator;

@Entity
@Table(name = "Triggers")
public class Trigger {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToOne
	@JoinColumn(name = "sensor_id")
	private Sensor sensorId;
	
	@Column(name = "operator")
	@Enumerated(EnumType.STRING)
	private LogicalOperator operator;
	
	@Column(name = "value")
	private int value;
	
	@Column(name = "priority")
	private int priority;
	
	public Trigger(){ 
		this(null, null, null, null, -1, -1);
	} 
	
	/**
	 * @param id
	 * @param name
	 * @param sensorId
	 * @param operator -> Logical Operator
	 * @param value
	 * @param priority
	 */
	public Trigger(Long id, String name, Sensor sensorId, LogicalOperator operator, int value, int priority){
		this.id = id;
		this.name = name;
		this.sensorId = sensorId;
		this.operator = operator;
		this.value = value;
		this.priority = priority;
	}
	
	/**
	 * @param id
	 * @param name
	 * @param sensorId
	 * @param operator -> Logical Operator
	 * @param value
	 * @param priority
	 */
	public Trigger(Long id, String name, Sensor sensorId, LogicalOperator operator, int value){
		this(id, name, sensorId, operator, value, -1);
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
	 * @return the sensorId
	 */
	public Sensor getSensorId() {
		return sensorId;
	}

	/**
	 * @param sensorId the sensorId to set
	 */
	public void setSensorId(Sensor sensorId) {
		this.sensorId = sensorId;
	}

	/**
	 * @return the operator
	 */
	public LogicalOperator getOperator() {
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(LogicalOperator operator) {
		this.operator = operator;
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

	@Override
	public String toString() {
		return "Trigger [id=" + id + ", name=" + name + ", sensorId=" + sensorId.getId() + ", operator=" + operator.toString() + ", value="
				+ value + ", priority=" + priority + "]";
	}	
}
