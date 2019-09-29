package com.softline.entity;

/**
 * ReportForms entity. @author MyEclipse Persistence Tools
 */

public class AdRiskevent implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private Integer season;
	private String seasonName;
	private String riskCompId;
	private String riskCompName;
	private String coreCompId;
	private String coreCompName;
	private String riskDate;
	private String eventTitle;
	private String eventDescription;
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
	
	public AdRiskevent() {
		super();
	}

	public AdRiskevent(Integer id, Integer year, Integer season,
			String riskCompId, String riskCompName, String riskDate,
			String eventTitle, String eventDescription, Integer status,
			String statusName, Integer isdel, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate, String seasonName, String coreCompId, String coreCompName) {
		super();
		this.id = id;
		this.year = year;
		this.season = season;
		this.riskCompId = riskCompId;
		this.riskCompName = riskCompName;
		this.coreCompId = coreCompId;
		this.coreCompName = coreCompName;
		this.riskDate = riskDate;
		this.eventTitle = eventTitle;
		this.eventDescription = eventDescription;
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
		this.seasonName = seasonName;
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

	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}

	public Integer getId() {
		return id;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getSeason() {
		return season;
	}

	public String getRiskCompId() {
		return riskCompId;
	}

	public String getRiskCompName() {
		return riskCompName;
	}

	public String getRiskDate() {
		return riskDate;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public String getEventDescription() {
		return eventDescription;
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

	public void setSeason(Integer season) {
		this.season = season;
	}

	public void setRiskCompId(String riskCompId) {
		this.riskCompId = riskCompId;
	}

	public void setRiskCompName(String riskCompName) {
		this.riskCompName = riskCompName;
	}

	public void setRiskDate(String riskDate) {
		this.riskDate = riskDate;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
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