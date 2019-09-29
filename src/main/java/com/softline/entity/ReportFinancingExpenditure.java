package com.softline.entity;

/**
 * ReportFinancingExpenditure entity. @author MyEclipse Persistence Tools
 */

public class ReportFinancingExpenditure implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private Integer month;
	private String org;
	private String orgname;
	private String loanSubject;
	private Integer repaymentBank;
	private Integer financingExpenditureType;
	private Integer currencyKind;
	private String repaymentAmount;
	private String rentCollector;
	private Integer billType;
	private String repaymentTime;
	private Integer sequelNew;
	private String remark;
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
	public ReportFinancingExpenditure() {
	}

	/** minimal constructor */
	public ReportFinancingExpenditure(Integer year, String org, String orgname,
			Integer status, Integer isdel) {
		this.year = year;
		this.org = org;
		this.orgname = orgname;
		this.status = status;
		this.isdel = isdel;
	}

	/** full constructor */
	public ReportFinancingExpenditure(Integer year, Integer month, String org,
			String orgname, String loanSubject, Integer repaymentBank,
			Integer financingExpenditureType, Integer currencyKind,
			String repaymentAmount, String rentCollector, Integer billType,
			String repaymentTime, Integer sequelNew, String remark,
			Integer status, Integer isdel, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate, Integer parentorg) {
		this.year = year;
		this.month = month;
		this.org = org;
		this.orgname = orgname;
		this.loanSubject = loanSubject;
		this.repaymentBank = repaymentBank;
		this.financingExpenditureType = financingExpenditureType;
		this.currencyKind = currencyKind;
		this.repaymentAmount = repaymentAmount;
		this.rentCollector = rentCollector;
		this.billType = billType;
		this.repaymentTime = repaymentTime;
		this.sequelNew = sequelNew;
		this.remark = remark;
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

	public String getLoanSubject() {
		return this.loanSubject;
	}

	public void setLoanSubject(String loanSubject) {
		this.loanSubject = loanSubject;
	}

	public Integer getRepaymentBank() {
		return this.repaymentBank;
	}

	public void setRepaymentBank(Integer repaymentBank) {
		this.repaymentBank = repaymentBank;
	}

	public Integer getFinancingExpenditureType() {
		return this.financingExpenditureType;
	}

	public void setFinancingExpenditureType(Integer financingExpenditureType) {
		this.financingExpenditureType = financingExpenditureType;
	}

	public Integer getCurrencyKind() {
		return this.currencyKind;
	}

	public void setCurrencyKind(Integer currencyKind) {
		this.currencyKind = currencyKind;
	}

	public String getRepaymentAmount() {
		return this.repaymentAmount;
	}

	public void setRepaymentAmount(String repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}

	public String getRentCollector() {
		return this.rentCollector;
	}

	public void setRentCollector(String rentCollector) {
		this.rentCollector = rentCollector;
	}

	public Integer getBillType() {
		return this.billType;
	}

	public void setBillType(Integer billType) {
		this.billType = billType;
	}

	public String getRepaymentTime() {
		return this.repaymentTime;
	}

	public void setRepaymentTime(String repaymentTime) {
		this.repaymentTime = repaymentTime;
	}

	public Integer getSequelNew() {
		return this.sequelNew;
	}

	public void setSequelNew(Integer sequelNew) {
		this.sequelNew = sequelNew;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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