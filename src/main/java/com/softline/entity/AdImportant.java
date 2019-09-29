package com.softline.entity;

/**
 * ReportForms entity. @author MyEclipse Persistence Tools
 */

public class AdImportant implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private Integer month;
	private String importantCompId;
	private String importantCompName;
	private String coreCompId;
	private String coreCompName;
	private Integer importantType;
	private String importantTypeName;
	private String importantDate;
	private String importantTitle;
	private String reportedCoreDate;
	private String reportedDepDate;
	private String reportedGroupDate;
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
	
	public AdImportant() {
		super();
	}

	public AdImportant(Integer id, Integer year, Integer month,
			String importantCompId, String importantCompName,
			Integer importantType, String importantDate, String importantTitle,
			String reportedDepDate, String reportedGroupDate, Integer status,
			String statusName, Integer isdel, String createPersonName,
			String createPersonId, String createDate, String reportedCoreDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate, String importantTypeName, String coreCompId, String coreCompName) {
		super();
		this.id = id;
		this.year = year;
		this.month = month;
		this.importantCompId = importantCompId;
		this.importantCompName = importantCompName;
		this.coreCompId = coreCompId;
		this.coreCompName = coreCompName;
		this.importantType = importantType;
		this.importantDate = importantDate;
		this.importantTitle = importantTitle;
		this.reportedDepDate = reportedDepDate;
		this.reportedGroupDate = reportedGroupDate;
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
		this.importantTypeName = importantTypeName;
		this.reportedCoreDate = reportedCoreDate;
	}

	public String getReportedCoreDate() {
		return reportedCoreDate;
	}

	public void setReportedCoreDate(String reportedCoreDate) {
		this.reportedCoreDate = reportedCoreDate;
	}

	public String getCoreCompId() {
		return coreCompId;
	}

	public String getCoreCompName() {
		return coreCompName;
	}

	public void setCoreCompId(String coreCompId) {
		this.coreCompId = coreCompId;
	}

	public void setCoreCompName(String coreCompName) {
		this.coreCompName = coreCompName;
	}

	public String getImportantTypeName() {
		return importantTypeName;
	}

	public void setImportantTypeName(String importantTypeName) {
		this.importantTypeName = importantTypeName;
	}

	public Integer getId() {
		return id;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getMonth() {
		return month;
	}

	public String getImportantCompId() {
		return importantCompId;
	}

	public String getImportantCompName() {
		return importantCompName;
	}

	public Integer getImportantType() {
		return importantType;
	}

	public String getImportantDate() {
		return importantDate;
	}

	public String getImportantTitle() {
		return importantTitle;
	}

	public String getReportedDepDate() {
		return reportedDepDate;
	}

	public String getReportedGroupDate() {
		return reportedGroupDate;
	}

	public Integer getStatus() {
		return status;
	}

	public String getStatusName() {
		return statusName;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public String getCreatePersonName() {
		return createPersonName;
	}

	public String getCreatePersonId() {
		return createPersonId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getLastModifyPersonId() {
		return lastModifyPersonId;
	}

	public String getLastModifyPersonName() {
		return lastModifyPersonName;
	}

	public String getLastModifyDate() {
		return lastModifyDate;
	}

	public String getReportPersonName() {
		return reportPersonName;
	}

	public String getReportPersonId() {
		return reportPersonId;
	}

	public String getReportDate() {
		return reportDate;
	}

	public String getAuditorPersonName() {
		return auditorPersonName;
	}

	public String getAuditorPersonId() {
		return auditorPersonId;
	}

	public String getAuditorDate() {
		return auditorDate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public void setImportantCompId(String importantCompId) {
		this.importantCompId = importantCompId;
	}

	public void setImportantCompName(String importantCompName) {
		this.importantCompName = importantCompName;
	}

	public void setImportantType(Integer importantType) {
		this.importantType = importantType;
	}

	public void setImportantDate(String importantDate) {
		this.importantDate = importantDate;
	}

	public void setImportantTitle(String importantTitle) {
		this.importantTitle = importantTitle;
	}

	public void setReportedDepDate(String reportedDepDate) {
		this.reportedDepDate = reportedDepDate;
	}

	public void setReportedGroupDate(String reportedGroupDate) {
		this.reportedGroupDate = reportedGroupDate;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}

	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setLastModifyPersonId(String lastModifyPersonId) {
		this.lastModifyPersonId = lastModifyPersonId;
	}

	public void setLastModifyPersonName(String lastModifyPersonName) {
		this.lastModifyPersonName = lastModifyPersonName;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}

	public void setReportPersonId(String reportPersonId) {
		this.reportPersonId = reportPersonId;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public void setAuditorPersonName(String auditorPersonName) {
		this.auditorPersonName = auditorPersonName;
	}

	public void setAuditorPersonId(String auditorPersonId) {
		this.auditorPersonId = auditorPersonId;
	}

	public void setAuditorDate(String auditorDate) {
		this.auditorDate = auditorDate;
	}

}