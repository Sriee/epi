package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class for Environment Table
 * 
 * @author sriee
 *
 */
@Entity
@Table(name = "Environment")
public class Environment {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "previous_state")
	private String previousState;
	
	@Column(name = "next_state")
	private String nextState;

	public Environment() { this(null, null, null, null); }
	/**
	 * @param id
	 * @param name
	 * @param previousState
	 * @param nextState
	 */
	public Environment(Long id, String name, String previousState, String nextState) {
		this.id = id;
		this.name = name;
		this.previousState = previousState;
		this.nextState = nextState;
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
	 * @return the previousState
	 */
	public String getPreviousState() {
		return previousState;
	}

	/**
	 * @param previousState the previousState to set
	 */
	public void setPreviousState(String previousState) {
		this.previousState = previousState;
	}

	/**
	 * @return the nextState
	 */
	public String getNextState() {
		return nextState;
	}

	/**
	 * @param nextState the nextState to set
	 */
	public void setNextState(String nextState) {
		this.nextState = nextState;
	}

	@Override
	public String toString() {
		return "Environment [id=" + id + ", name=" + name + ", previousState=" + previousState + ", nextState="
				+ nextState + "]";
	}
}
