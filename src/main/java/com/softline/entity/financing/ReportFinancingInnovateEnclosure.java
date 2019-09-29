package com.softline.entity.financing;

/**
 * ReportFinancingInnovateEnclosure entity. @author MyEclipse Persistence Tools
 */

public class ReportFinancingInnovateEnclosure implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer financingProjectId;
	private String enclosureName;
	private String enclosureAddress;

	// Constructors

	/** default constructor */
	public ReportFinancingInnovateEnclosure() {
	}

	/** full constructor */
	public ReportFinancingInnovateEnclosure(Integer financingProjectId,
			String enclosureName, String enclosureAddress) {
		this.financingProjectId = financingProjectId;
		this.enclosureName = enclosureName;
		this.enclosureAddress = enclosureAddress;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFinancingProjectId() {
		return this.financingProjectId;
	}

	public void setFinancingProjectId(Integer financingProjectId) {
		this.financingProjectId = financingProjectId;
	}

	public String getEnclosureName() {
		return this.enclosureName;
	}

	public void setEnclosureName(String enclosureName) {
		this.enclosureName = enclosureName;
	}

	public String getEnclosureAddress() {
		return this.enclosureAddress;
	}

	public void setEnclosureAddress(String enclosureAddress) {
		this.enclosureAddress = enclosureAddress;
	}

}