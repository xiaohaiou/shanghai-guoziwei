package com.softline.entity.financing;

/**
 * ReportFinancingInnovateLog entity. @author MyEclipse Persistence Tools
 */

public class ReportFinancingInnovateLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer financingProjectId;
	private String updateDate;
	private String updatePerson;
	private String updateDetail;
	private Integer isdel;

	// Constructors

	/** default constructor */
	public ReportFinancingInnovateLog() {
	}

	/** full constructor */
	public ReportFinancingInnovateLog(Integer financingProjectId,
			String updateDate, String updatePerson, String updateDetail,
			Integer isdel) {
		this.financingProjectId = financingProjectId;
		this.updateDate = updateDate;
		this.updatePerson = updatePerson;
		this.updateDetail = updateDetail;
		this.isdel = isdel;
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

	public String getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdatePerson() {
		return this.updatePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	public String getUpdateDetail() {
		return this.updateDetail;
	}

	public void setUpdateDetail(String updateDetail) {
		this.updateDetail = updateDetail;
	}

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

}