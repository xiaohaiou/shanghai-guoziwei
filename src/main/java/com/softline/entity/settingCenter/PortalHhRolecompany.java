package com.softline.entity.settingCenter;

/**
 * HhRolecompany entity. @author MyEclipse Persistence Tools
 */

public class PortalHhRolecompany implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer sysId;
	private Integer roleId;
	private Integer roleNum;
	private Integer companyId;

	// Constructors

	/** default constructor */
	public PortalHhRolecompany() {
	}

	/** full constructor */
	public PortalHhRolecompany(Integer sysId, Integer roleId, Integer roleNum,
			Integer companyId) {
		this.sysId = sysId;
		this.roleId = roleId;
		this.roleNum = roleNum;
		this.companyId = companyId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysId() {
		return this.sysId;
	}

	public void setSysId(Integer sysId) {
		this.sysId = sysId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleNum() {
		return this.roleNum;
	}

	public void setRoleNum(Integer roleNum) {
		this.roleNum = roleNum;
	}

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

}