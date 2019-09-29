package com.softline.entity;

import java.io.Serializable;

public class EmployeeAccountabilityViewId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String typename;
	private String projectCode;
	private String projectName;
	private Integer projecttype;
	
	private Integer status;
	
	
	public EmployeeAccountabilityViewId() {
		super();
	}
	public EmployeeAccountabilityViewId(Integer id, String typename,
			String projectCode, String projectName, Integer projecttype,Integer status) {
		super();
		this.id = id;
		this.typename = typename;
		this.projectCode = projectCode;
		this.projectName = projectName;
		this.projecttype = projecttype;
		this.status=status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Integer getProjecttype() {
		return projecttype;
	}
	public void setProjecttype(Integer projecttype) {
		this.projecttype = projecttype;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
	
	
	
	
}
