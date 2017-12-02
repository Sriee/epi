package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Actuator")
public class Actuator {

	@Id
	@Column(name = "Id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "serial_id")
	private String serialId;
	
	@Column(name = "active")
	private boolean active;

	public Actuator() { this(null, null, null, false); }
	
	/**
	 * @param id
	 * @param name
	 * @param serialId
	 * @param active
	 */
	public Actuator(Long id, String name, String serialId, boolean active) {
		this.id = id;
		this.name = name;
		this.serialId = serialId;
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
	 * @return the serialId
	 */
	public String getSerialId() {
		return serialId;
	}

	/**
	 * @param serialId the serialId to set
	 */
	public void setSerialId(String serialId) {
		this.serialId = serialId;
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
		return "Actuator [id=" + id + ", name=" + name + ", serialId=" + serialId + ", active=" + active + "]";
	}	
}
