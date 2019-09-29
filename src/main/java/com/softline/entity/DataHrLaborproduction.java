package com.softline.entity;

/**
 * DataHrLaborproduction entity. @author MyEclipse Persistence Tools
 */

public class DataHrLaborproduction implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private Integer month;
	private String company;
	private String organalId;
	private Integer workersNumber;
	private Double averageIncome;
	private Double averageProfit;
	private Double businessIncome;
	private Double businessProfit;
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
	public DataHrLaborproduction() {
	}

	/** minimal constructor */
	public DataHrLaborproduction(Integer year, Integer month, String company,
			String organalId, Integer workersNumber, Double averageIncome,
			Double averageProfit, Integer isdel, String foreignKey) {
		this.year = year;
		this.month = month;
		this.company = company;
		this.organalId = organalId;
		this.workersNumber = workersNumber;
		this.averageIncome = averageIncome;
		this.averageProfit = averageProfit;
		this.isdel = isdel;
		this.foreignKey = foreignKey;
	}

	/** full constructor */
	public DataHrLaborproduction(Integer year, Integer month, String company,
			String organalId, Integer workersNumber, Double averageIncome,
			Double averageProfit, Double businessIncome, Double businessProfit,
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
		this.workersNumber = workersNumber;
		this.averageIncome = averageIncome;
		this.averageProfit = averageProfit;
		this.businessIncome = businessIncome;
		this.businessProfit = businessProfit;
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

	// Property accessors

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

	public Integer getWorkersNumber() {
		return this.workersNumber;
	}

	public void setWorkersNumber(Integer workersNumber) {
		this.workersNumber = workersNumber;
	}

	public Double getAverageIncome() {
		return this.averageIncome;
	}

	public void setAverageIncome(Double averageIncome) {
		this.averageIncome = averageIncome;
	}

	public Double getAverageProfit() {
		return this.averageProfit;
	}

	public void setAverageProfit(Double averageProfit) {
		this.averageProfit = averageProfit;
	}

	public Double getBusinessIncome() {
		return this.businessIncome;
	}

	public void setBusinessIncome(Double businessIncome) {
		this.businessIncome = businessIncome;
	}

	public Double getBusinessProfit() {
		return this.businessProfit;
	}

	public void setBusinessProfit(Double businessProfit) {
		this.businessProfit = businessProfit;
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

	public String getLastModifyDate() {
		return this.lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public String getForeignKey() {
		return this.foreignKey;
	}

	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
	}

}