package com.softline.entity.bylaw;

/**
 * BylawDept entity. @author MyEclipse Persistence Tools
 */

public class BylawDept implements java.io.Serializable {

	// Fields

	private Integer deptId;
	private String deptNm;

	// Constructors

	/** default constructor */
	public BylawDept() {
	}

	/** full constructor */
	public BylawDept(String deptNm) {
		this.deptNm = deptNm;
	}

	// Property accessors

	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptNm() {
		return this.deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

}