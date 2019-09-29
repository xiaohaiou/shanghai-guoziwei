package com.softline.entity;

/**
 * DataSixRateKp entity. @author MyEclipse Persistence Tools
 */

public class DataSixRateKp implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private Integer month;
	private String company;
	private String organalId;
	private Integer shopNumber;
	private Integer shopCardinalNumber;
	private Double marketShare;
	private Double targetValueYears;
	private Integer status;
	private String examineTime;
	private Integer isdel;
	private String lastModifyDate;
	private String reportPersonName;
	private String reportPersonId;
	private String reportDate;
	private String auditorPersonName;
	private String auditorPersonId;
	private String auditorDate;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate_1;

	// Constructors

	/** default constructor */
	public DataSixRateKp() {
	}

	/** minimal constructor */
	public DataSixRateKp(Integer year, Integer month, String company,
			String organalId, Integer isdel) {
		this.year = year;
		this.month = month;
		this.company = company;
		this.organalId = organalId;
		this.isdel = isdel;
	}

	/** full constructor */
	public DataSixRateKp(Integer year, Integer month, String company,
			String organalId, Integer shopNumber, Integer shopCardinalNumber,
			Double marketShare, Integer status,
			String examineTime, Integer isdel, String lastModifyDate,
			String reportPersonName, String reportPersonId, String reportDate,
			String auditorPersonName, String auditorPersonId,
			String auditorDate, String createPersonName, String createPersonId,
			String createDate, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate_1) {
		this.year = year;
		this.month = month;
		this.company = company;
		this.organalId = organalId;
		this.shopNumber = shopNumber;
		this.shopCardinalNumber = shopCardinalNumber;
		this.marketShare = marketShare;
		this.status = status;
		this.examineTime = examineTime;
		this.isdel = isdel;
		this.lastModifyDate = lastModifyDate;
		this.reportPersonName = reportPersonName;
		this.reportPersonId = reportPersonId;
		this.reportDate = reportDate;
		this.auditorPersonName = auditorPersonName;
		this.auditorPersonId = auditorPersonId;
		this.auditorDate = auditorDate;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate_1 = lastModifyDate_1;
	}

	// Property accessors
	
	public Integer getId() {
		return this.id;
	}

	public Double getTargetValueYears() {
		return targetValueYears;
	}

	public void setTargetValueYears(Double targetValueYears) {
		this.targetValueYears = targetValueYears;
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

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getOrganalId() {
		return this.organalId;
	}

	public void setOrganalId(String organalId) {
		this.organalId = organalId;
	}

	public Integer getShopNumber() {
		return this.shopNumber;
	}

	public void setShopNumber(Integer shopNumber) {
		this.shopNumber = shopNumber;
	}

	public Integer getShopCardinalNumber() {
		return this.shopCardinalNumber;
	}

	public void setShopCardinalNumber(Integer shopCardinalNumber) {
		this.shopCardinalNumber = shopCardinalNumber;
	}

	public Double getMarketShare() {
		return this.marketShare;
	}

	public void setMarketShare(Double marketShare) {
		this.marketShare = marketShare;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getExamineTime() {
		return this.examineTime;
	}

	public void setExamineTime(String examineTime) {
		this.examineTime = examineTime;
	}

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public String getLastModifyDate() {
		return this.lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public String getReportPersonName() {
		return this.reportPersonName;
	}

	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}

	public String getReportPersonId() {
		return this.reportPersonId;
	}

	public void setReportPersonId(String reportPersonId) {
		this.reportPersonId = reportPersonId;
	}

	public String getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getAuditorPersonName() {
		return this.auditorPersonName;
	}

	public void setAuditorPersonName(String auditorPersonName) {
		this.auditorPersonName = auditorPersonName;
	}

	public String getAuditorPersonId() {
		return this.auditorPersonId;
	}

	public void setAuditorPersonId(String auditorPersonId) {
		this.auditorPersonId = auditorPersonId;
	}

	public String getAuditorDate() {
		return this.auditorDate;
	}

	public void setAuditorDate(String auditorDate) {
		this.auditorDate = auditorDate;
	}

	public String getCreatePersonName() {
		return this.createPersonName;
	}

	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}

	public String getCreatePersonId() {
		return this.createPersonId;
	}

	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
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

	public String getLastModifyDate_1() {
		return this.lastModifyDate_1;
	}

	public void setLastModifyDate_1(String lastModifyDate_1) {
		this.lastModifyDate_1 = lastModifyDate_1;
	}

}