package com.softline.entity;

/**
 * ReportNonInvestmentExpenditure entity. @author MyEclipse Persistence Tools
 */

public class ReportNonInvestmentExpenditure implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private Integer month;
	private String org;
	private String orgname;
	private String investmentEnterpriseName;
	private Integer costCategory;
	private String investmentAmount;
	private Integer domesticOverseas;
	private String projectIndustry;
	private String investmentArea;
	private Integer status;
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
	private Integer parentorg;

	// Constructors

	/** default constructor */
	public ReportNonInvestmentExpenditure() {
	}

	/** minimal constructor */
	public ReportNonInvestmentExpenditure(Integer year, String org,
			String orgname, Integer status, Integer isdel) {
		this.year = year;
		this.org = org;
		this.orgname = orgname;
		this.status = status;
		this.isdel = isdel;
	}

	/** full constructor */
	public ReportNonInvestmentExpenditure(Integer year, Integer month,
			String org, String orgname, String investmentEnterpriseName,
			Integer costCategory, String investmentAmount,
			Integer domesticOverseas, String projectIndustry,
			String investmentArea, Integer status, Integer isdel,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate, Integer parentorg) {
		this.year = year;
		this.month = month;
		this.org = org;
		this.orgname = orgname;
		this.investmentEnterpriseName = investmentEnterpriseName;
		this.costCategory = costCategory;
		this.investmentAmount = investmentAmount;
		this.domesticOverseas = domesticOverseas;
		this.projectIndustry = projectIndustry;
		this.investmentArea = investmentArea;
		this.status = status;
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
		this.parentorg = parentorg;
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

	public String getOrg() {
		return this.org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getOrgname() {
		return this.orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getInvestmentEnterpriseName() {
		return this.investmentEnterpriseName;
	}

	public void setInvestmentEnterpriseName(String investmentEnterpriseName) {
		this.investmentEnterpriseName = investmentEnterpriseName;
	}

	public Integer getCostCategory() {
		return this.costCategory;
	}

	public void setCostCategory(Integer costCategory) {
		this.costCategory = costCategory;
	}

	public String getInvestmentAmount() {
		return this.investmentAmount;
	}

	public void setInvestmentAmount(String investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	public Integer getDomesticOverseas() {
		return this.domesticOverseas;
	}

	public void setDomesticOverseas(Integer domesticOverseas) {
		this.domesticOverseas = domesticOverseas;
	}

	public String getProjectIndustry() {
		return this.projectIndustry;
	}

	public void setProjectIndustry(String projectIndustry) {
		this.projectIndustry = projectIndustry;
	}

	public String getInvestmentArea() {
		return this.investmentArea;
	}

	public void setInvestmentArea(String investmentArea) {
		this.investmentArea = investmentArea;
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

	public Integer getParentorg() {
		return this.parentorg;
	}

	public void setParentorg(Integer parentorg) {
		this.parentorg = parentorg;
	}

}