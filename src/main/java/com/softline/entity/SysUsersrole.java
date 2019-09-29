package com.softline.entity;

/**
 * HhUsersrole entity. @author MyEclipse Persistence Tools
 */

public class SysUsersrole implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vcEmployeeId;
	private Integer roleId;

	// Constructors

	/** default constructor */
	public SysUsersrole() {
	}

	/** full constructor */
	public SysUsersrole(String vcEmployeeId, Integer roleId) {
		this.vcEmployeeId = vcEmployeeId;
		this.roleId = roleId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVcEmployeeId() {
		return this.vcEmployeeId;
	}

	public void setVcEmployeeId(String vcEmployeeId) {
		this.vcEmployeeId = vcEmployeeId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}