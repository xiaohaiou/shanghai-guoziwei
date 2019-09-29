package com.softline.entity;

/**
 * SysUserscompany entity. @author MyEclipse Persistence Tools
 */

public class SysUserscompany implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vcEmployeeId;
	private Integer companyId;

	// Constructors

	/** default constructor */
	public SysUserscompany() {
	}

	/** full constructor */
	public SysUserscompany(String vcEmployeeId, Integer companyId) {
		this.vcEmployeeId = vcEmployeeId;
		this.companyId = companyId;
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

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

}