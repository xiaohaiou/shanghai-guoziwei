package com.softline.entity.settingCenter;

/**
 * HhRolecompany entity. @author MyEclipse Persistence Tools
 */

public class PortalHhRolefinancecompany implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer sysId;
	private Integer roleId;
	private Integer roleNum;
	private String companyId;

	// Constructors

	/** default constructor */
	public PortalHhRolefinancecompany() {
	}

	/** full constructor */
	public PortalHhRolefinancecompany(Integer sysId, Integer roleId, Integer roleNum,
			String companyId) {
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

	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

}