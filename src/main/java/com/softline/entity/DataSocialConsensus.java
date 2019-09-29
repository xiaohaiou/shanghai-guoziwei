package com.softline.entity;

/**
 * DataSocialConsensus entity. @author MyEclipse Persistence Tools
 */

public class DataSocialConsensus implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private Integer month;
	private Integer week;
	private String dateFrom;
	private String dateTo;
	private Integer consensusCount;
	private Integer reportConut;
	private Integer status;
	private Integer isdel;
	private String reportPersonId;
	private String reportPersonName;
	private String reportDate;
	private String auditorPersonId;
	private String auditorPersonName;
	private String auditorDate;
	private String createPersonId;
	private String createPersonName;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String attachAdress;

	// Constructors

	/** default constructor */
	public DataSocialConsensus() {
	}

	/** minimal constructor */
	public DataSocialConsensus(Integer year, Integer month, Integer week,
			Integer consensusCount, Integer reportConut, Integer status,
			Integer isdel, String attachAdress) {
		this.year = year;
		this.month = month;
		this.week = week;
		this.consensusCount = consensusCount;
		this.reportConut = reportConut;
		this.status = status;
		this.isdel = isdel;
		this.attachAdress = attachAdress;
	}

	/** full constructor */
	public DataSocialConsensus(Integer year, Integer month, Integer week,
			String dateFrom, String dateTo, Integer consensusCount,
			Integer reportConut, Integer status, Integer isdel,
			String reportPersonId, String reportPersonName, String reportDate,
			String auditorPersonId, String auditorPersonName,
			String auditorDate, String createPersonId, String createPersonName,
			String createDate, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate, String attachAdress) {
		this.year = year;
		this.month = month;
		this.week = week;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.consensusCount = consensusCount;
		this.reportConut = reportConut;
		this.status = status;
		this.isdel = isdel;
		this.reportPersonId = reportPersonId;
		this.reportPersonName = reportPersonName;
		this.reportDate = reportDate;
		this.auditorPersonId = auditorPersonId;
		this.auditorPersonName = auditorPersonName;
		this.auditorDate = auditorDate;
		this.createPersonId = createPersonId;
		this.createPersonName = createPersonName;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.attachAdress = attachAdress;
	}

	// Property accessors

	public String getAttachAdress() {
		return attachAdress;
	}

	public void setAttachAdress(String attachAdress) {
		this.attachAdress = attachAdress;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getWeek() {
		return this.week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public String getDateFrom() {
		return this.dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return this.dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public Integer getConsensusCount() {
		return this.consensusCount;
	}

	public void setConsensusCount(Integer consensusCount) {
		this.consensusCount = consensusCount;
	}

	public Integer getReportConut() {
		return this.reportConut;
	}

	public void setReportConut(Integer reportConut) {
		this.reportConut = reportConut;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public String getReportPersonId() {
		return this.reportPersonId;
	}

	public void setReportPersonId(String reportPersonId) {
		this.reportPersonId = reportPersonId;
	}

	public String getReportPersonName() {
		return this.reportPersonName;
	}

	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}

	public String getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getAuditorPersonId() {
		return this.auditorPersonId;
	}

	public void setAuditorPersonId(String auditorPersonId) {
		this.auditorPersonId = auditorPersonId;
	}

	public String getAuditorPersonName() {
		return this.auditorPersonName;
	}

	public void setAuditorPersonName(String auditorPersonName) {
		this.auditorPersonName = auditorPersonName;
	}

	public String getAuditorDate() {
		return this.auditorDate;
	}

	public void setAuditorDate(String auditorDate) {
		this.auditorDate = auditorDate;
	}

	public String getCreatePersonId() {
		return this.createPersonId;
	}

	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}

	public String getCreatePersonName() {
		return this.createPersonName;
	}

	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLastModifyPersonId() {
		return this.lastModifyPersonId;
	}

	public void setLastModifyPersonId(String lastModifyPersonId) {
		this.lastModifyPersonId = lastModifyPersonId;
	}

	public String getLastModifyPersonName() {
		return this.lastModifyPersonName;
	}

	public void setLastModifyPersonName(String lastModifyPersonName) {
		this.lastModifyPersonName = lastModifyPersonName;
	}

	public String getLastModifyDate() {
		return this.lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

}