package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class for Sensor table
 * 
 * @author sriee
 *
 */
@Entity
@Table(name = "Sensor")
public class Sensor {
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private boolean type;
	
	@Column(name = "unit")
	private String unit;
	
	@Column(name = "active")
	private boolean active;

	public Sensor() { this(null, null, false, null, false); } 

	/**
	 * @param id
	 * @param name
	 * @param type
	 * @param unit
	 * @param active
	 */
	public Sensor(Long id, String name, boolean type, String unit, boolean active) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.unit = unit;
		this.active = active;
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
	 * @return the type
	 */
	public boolean isType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(boolean type) {
		this.type = type;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Sensor [id=" + id + ", name=" + name + ", type=" + type + ", unit=" + unit + ", active=" + active + "]";
	}
}
