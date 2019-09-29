package com.softline.entity;

/**
 * ReportForms entity. @author MyEclipse Persistence Tools
 */

public class AdSupervise implements java.io.Serializable {

	// Fields

	private Integer id;
	private String compId;
	private String compName;
	private Integer year;
	private Integer totalDivision;
	private Integer propelDivision;
	private Integer finishDivision;
	private Double finishDivisionRatio;
	private Integer status;
	private String statusName;
	private Integer isdel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String reportPersonName;
	private String reportPersonId;
	private String reportDate;
	private String auditorPersonName;
	private String auditorPersonId;
	private String auditorDate;
	private String auditorStartDate;
	private String auditorEndDate;
	private String reportEndDate;
	
	private String vcOrganID;
	
	public AdSupervise() {
		super();
	}
	
	public AdSupervise(Integer id, String compId, String compName,
			Integer year, Integer totalDivision, Integer propelDivision,
			Integer finishDivision, Double finishDivisionRatio, Integer status,
			String statusName, Integer isdel, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate, String auditorEndDate,
			String reportEndDate, String vcOrganID,String auditorStartDate) {
		super();
		this.id = id;
		this.compId = compId;
		this.compName = compName;
		this.year = year;
		this.totalDivision = totalDivision;
		this.propelDivision = propelDivision;
		this.finishDivision = finishDivision;
		this.finishDivisionRatio = finishDivisionRatio;
		this.status = status;
		this.statusName = statusName;
		this.isdel = isdel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.reportPersonName = reportPersonName;
		this.reportPersonId = reportPersonId;
		this.reportDate = reportDate;
		this.auditorPersonName = auditorPersonName;
		this.auditorPersonId = auditorPersonId;
		this.auditorDate = auditorDate;
		this.auditorEndDate = auditorEndDate;
		this.reportEndDate = reportEndDate;
		this.vcOrganID = vcOrganID;
		this.auditorStartDate = auditorStartDate;
	}






	public String getAuditorStartDate() {
		return auditorStartDate;
	}

	public void setAuditorStartDate(String auditorStartDate) {
		this.auditorStartDate = auditorStartDate;
	}

	public String getAuditorEndDate() {
		return auditorEndDate;
	}

	public void setAuditorEndDate(String auditorEndDate) {
		this.auditorEndDate = auditorEndDate;
	}

	public String getReportEndDate() {
		return reportEndDate;
	}

	public void setReportEndDate(String reportEndDate) {
		this.reportEndDate = reportEndDate;
	}

	public String getVcOrganID() {
		return vcOrganID;
	}

	public void setVcOrganID(String vcOrganID) {
		this.vcOrganID = vcOrganID;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getTotalDivision() {
		return totalDivision;
	}

	public void setTotalDivision(Integer totalDivision) {
		this.totalDivision = totalDivision;
	}

	public Integer getPropelDivision() {
		return propelDivision;
	}

	public void setPropelDivision(Integer propelDivision) {
		this.propelDivision = propelDivision;
	}

	public Integer getFinishDivision() {
		return finishDivision;
	}

	public void setFinishDivision(Integer finishDivision) {
		this.finishDivision = finishDivision;
	}

	public Double getFinishDivisionRatio() {
		return finishDivisionRatio;
	}

	public void setFinishDivisionRatio(Double finishDivisionRatio) {
		this.finishDivisionRatio = finishDivisionRatio;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public String getCreatePersonName() {
		return createPersonName;
	}

	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}

	public String getCreatePersonId() {
		return createPersonId;
	}

	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLastModifyPersonId() {
		return lastModifyPersonId;
	}

	public void setLastModifyPersonId(String lastModifyPersonId) {
		this.lastModifyPersonId = lastModifyPersonId;
	}

	public String getLastModifyPersonName() {
		return lastModifyPersonName;
	}

	public void setLastModifyPersonName(String lastModifyPersonName) {
		this.lastModifyPersonName = lastModifyPersonName;
	}

	public String getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public String getReportPersonName() {
		return reportPersonName;
	}

	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}

	public String getReportPersonId() {
		return reportPersonId;
	}

	public void setReportPersonId(String reportPersonId) {
		this.reportPersonId = reportPersonId;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getAuditorPersonName() {
		return auditorPersonName;
	}

	public void setAuditorPersonName(String auditorPersonName) {
		this.auditorPersonName = auditorPersonName;
	}

	public String getAuditorPersonId() {
		return auditorPersonId;
	}

	public void setAuditorPersonId(String auditorPersonId) {
		this.auditorPersonId = auditorPersonId;
	}

	public String getAuditorDate() {
		return auditorDate;
	}

	public void setAuditorDate(String auditorDate) {
		this.auditorDate = auditorDate;
	}

}