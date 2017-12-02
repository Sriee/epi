package com.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "Link")
public class Link {
	
	@EmbeddedId
	private LinkPK linkPK;
	
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@Column(name = "type_id")
	private int typeId;

	public Link() {	this(null, null, -1); }

	/**
	 * @param linkPK
	 * @param type
	 * @param typeId
	 */
	public Link(LinkPK linkPK, Type type, int typeId) {
		this.linkPK = linkPK;
		this.type = type;
		this.typeId = typeId;
	}

	/**
	 * @return the linkPK
	 */
	public LinkPK getLinkPK() {
		return linkPK;
	}

	/**
	 * @param linkPK the linkPK to set
	 */
	public void setLinkPK(LinkPK linkPK) {
		this.linkPK = linkPK;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * @return the typeId
	 */
	public int getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "Link [linkPK=" + linkPK + ", type=" + type + ", typeId=" + typeId + "]";
	}
}		
