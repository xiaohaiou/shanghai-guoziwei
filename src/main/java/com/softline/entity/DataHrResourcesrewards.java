package com.softline.entity;

/**
 * DataHrResourcesrewards entity. @author MyEclipse Persistence Tools
 */

public class DataHrResourcesrewards implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private Integer month;
	private String company;
	private String organalId;
	private String capitalization;
	private String retainedProfits;
	private Double hrRateReturn;
	private Integer status;
	private String examineTime;
	private Integer isdel;
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
	private String lastModifyDate;
	private String foreignKey;

	// Constructors

	/** default constructor */
	public DataHrResourcesrewards() {
	}

	/** minimal constructor */
	public DataHrResourcesrewards(Integer year, Integer month, String company,
			String organalId, Double hrRateReturn, Integer isdel,
			String foreignKey) {
		this.year = year;
		this.month = month;
		this.company = company;
		this.organalId = organalId;
		this.hrRateReturn = hrRateReturn;
		this.isdel = isdel;
		this.foreignKey = foreignKey;
	}

	/** full constructor */
	public DataHrResourcesrewards(Integer year, Integer month, String company,
			String organalId, Double businessProfit, Double hrRateReturn,
			Integer status, String examineTime, Integer isdel,
			String reportPersonName, String reportPersonId, String reportDate,
			String auditorPersonName, String auditorPersonId,
			String auditorDate, String createPersonName, String createPersonId,
			String createDate, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate,
			String foreignKey) {
		this.year = year;
		this.month = month;
		this.company = company;
		this.organalId = organalId;
		this.hrRateReturn = hrRateReturn;
		this.status = status;
		this.examineTime = examineTime;
		this.isdel = isdel;
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
		this.lastModifyDate = lastModifyDate;
		this.foreignKey = foreignKey;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getOrganalId() {
		return organalId;
	}

	public void setOrganalId(String organalId) {
		this.organalId = organalId;
	}

	public String getCapitalization() {
		return capitalization;
	}

	public void setCapitalization(String capitalization) {
		this.capitalization = capitalization;
	}

	public String getRetainedProfits() {
		return retainedProfits;
	}

	public void setRetainedProfits(String retainedProfits) {
		this.retainedProfits = retainedProfits;
	}

	public Double getHrRateReturn() {
		return hrRateReturn;
	}

	public void setHrRateReturn(Double hrRateReturn) {
		this.hrRateReturn = hrRateReturn;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getExamineTime() {
		return examineTime;
	}

	public void setExamineTime(String examineTime) {
		this.examineTime = examineTime;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
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

	public String getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
	}

	// Property accessors

}