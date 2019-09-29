package com.softline.entity.bylaw;

/**
 * BylawSynLock entity. @author MyEclipse Persistence Tools
 */

public class BylawSynLock implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer status;

	// Constructors

	/** default constructor */
	public BylawSynLock() {
	}

	/** full constructor */
	public BylawSynLock(Integer status) {
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}