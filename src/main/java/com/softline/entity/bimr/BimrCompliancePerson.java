package com.softline.entity.bimr;

/**
 * BimrCompliancePerson entity. @author MyEclipse Persistence Tools
 */

public class BimrCompliancePerson implements java.io.Serializable {

	// Fields

	private Integer id;
	private String employeeName;
	private String employeePostion;
	private String employeeNumber;
	private Integer isDel;
	private Integer complianceId;

	// Constructors

	/** default constructor */
	public BimrCompliancePerson() {
	}

	/** minimal constructor */
	public BimrCompliancePerson(Integer isDel) {
		this.isDel = isDel;
	}

	/** full constructor */
	public BimrCompliancePerson(String employeeName, String employeePostion,
			String employeeNumber, Integer isDel) {
		this.employeeName = employeeName;
		this.employeePostion = employeePostion;
		this.employeeNumber = employeeNumber;
		this.isDel = isDel;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return this.employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeePostion() {
		return this.employeePostion;
	}

	public void setEmployeePostion(String employeePostion) {
		this.employeePostion = employeePostion;
	}

	public String getEmployeeNumber() {
		return this.employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getComplianceId() {
		return complianceId;
	}

	public void setComplianceId(Integer complianceId) {
		this.complianceId = complianceId;
	}

	
}