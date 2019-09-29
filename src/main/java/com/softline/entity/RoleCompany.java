package com.softline.entity;

/**
 * RoleCompany entity. @author MyEclipse Persistence Tools
 */

public class RoleCompany implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer companyId;
	private Integer roleId;
	private Integer portalId;
	// Constructors

	/** default constructor */
	public RoleCompany() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPortalId() {
		return portalId;
	}

	public void setPortalId(Integer portalId) {
		this.portalId = portalId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}