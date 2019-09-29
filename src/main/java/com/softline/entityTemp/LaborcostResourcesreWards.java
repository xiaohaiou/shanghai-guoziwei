package com.softline.entityTemp;

/**
 * DataHrLaborcost entity. @author MyEclipse Persistence Tools
 */

public class LaborcostResourcesreWards implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer bid;
	private Integer parentId;
	private Integer year;
	private Integer month;
	private String company;
	private String organalId;
	
	private String yearlyBudget;
	private Double laborCost;
	private String monthlyBudget;
	private String monthlyPerform;
	
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
	private String ForeignKey;
	// Constructors

	/** default constructor */
	public LaborcostResourcesreWards() {
	}

	/** minimal constructor */
	public LaborcostResourcesreWards(Integer year, Integer month, String company,
			String organalId, Double laborCost, Double hrRateReturn,
			Integer isdel) {
		this.year = year;
		this.month = month;
		this.company = company;
		this.organalId = organalId;
		this.laborCost = laborCost;
		this.hrRateReturn = hrRateReturn;
		this.isdel = isdel;
	}

	/** full constructor */
	public LaborcostResourcesreWards(Integer year, Integer month, String company,
			String organalId, String businessIncome, Double laborCost,
			Double hrRateReturn, Integer status, String examineTime,
			Integer isdel, String createPersonName, String createPersonId,
			String createDate, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate,
			String reportPersonName, String reportPersonId, String reportDate) {
		this.year = year;
		this.month = month;
		this.company = company;
		this.organalId = organalId;
		this.laborCost = laborCost;
		this.hrRateReturn = hrRateReturn;
		this.status = status;
		this.examineTime = examineTime;
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
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

	public String getYearlyBudget() {
		return yearlyBudget;
	}

	public void setYearlyBudget(String yearlyBudget) {
		this.yearlyBudget = yearlyBudget;
	}

	public Double getLaborCost() {
		return laborCost;
	}

	public void setLaborCost(Double laborCost) {
		this.laborCost = laborCost;
	}

	public String getMonthlyBudget() {
		return monthlyBudget;
	}

	public void setMonthlyBudget(String monthlyBudget) {
		this.monthlyBudget = monthlyBudget;
	}

	public String getMonthlyPerform() {
		return monthlyPerform;
	}

	public void setMonthlyPerform(String monthlyPerform) {
		this.monthlyPerform = monthlyPerform;
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
		return ForeignKey;
	}

	public void setForeignKey(String foreignKey) {
		ForeignKey = foreignKey;
	}
	
	// Property accessors
	
}