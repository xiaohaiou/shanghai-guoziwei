package com.softline.entity;

/**
 * SysRoleaction entity. @author MyEclipse Persistence Tools
 */

public class SysRoleaction implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer roleId;
	private Integer actionId;

	// Constructors

	/** default constructor */
	public SysRoleaction() {
	}

	/** full constructor */
	public SysRoleaction(Integer roleId, Integer actionId) {
		this.roleId = roleId;
		this.actionId = actionId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getActionId() {
		return this.actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

}