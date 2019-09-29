package com.softline.entity;

/**
 * ReportFinancingAccount entity. @author MyEclipse Persistence Tools
 */

public class ReportFinancingAccount implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private Integer month;
	private String org;
	private String orgname;
	private String domesticAccountAmounts;
	private String overseasAccountAmounts;
	private String monthlyDollarExchangeRate;
	private String financingAccountAmounts;
	private String domesticAccountCostRate;
	private String overseasAccountCostRate;
	private String annualInterest;
	private String financingAccountCost;
	private String equityFinancing;
	private String stockEquityFinancing;
	private String bondFinancing;
	private String type;
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
	private String parentorg;

	
	//formalu
	private Integer getOperateType;
 	private String statusName;
 	private String debtTypeName;
 	
 	
 	private String sequelNewName;
 	private String domesticOverseasName;
 	private String coordinationOperationName;
 	private String revitalizeEquityFinancingName;
 	private String liabilityTypesName;
 	private String currencyKindName;
 	
 	private String startyear;
	private String endyear;
	private String startmonth;
	private String endmonth;
	private String starttime;
	private String endtime;
	
	private String authOrg; // 数据权限控制字段
	// Constructors

	/** default constructor */
	public ReportFinancingAccount() {
	}

	/** minimal constructor */
	public ReportFinancingAccount(Integer year, Integer month, String org, String orgname,
			Integer status, Integer isdel) {
		this.year = year;
		this.month = month;
		this.org = org;
		this.orgname = orgname;
		this.status = status;
		this.isdel = isdel;
	}

	/** full constructor */
	public ReportFinancingAccount(Integer id, Integer year, Integer month,
			String org, String orgname, String domesticAccountAmounts,
			String overseasAccountAmounts, String monthlyDollarExchangeRate,
			String financingAccountAmounts, String domesticAccountCostRate,
			String overseasAccountCostRate, String annualInterest,
			String financingAccountCost, String equityFinancing,
			String stockEquityFinancing, String bondFinancing,
			String type, String remark,Integer status,
			Integer isdel, String createPersonName, String createPersonId,
			String createDate, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate,
			String reportPersonName, String reportPersonId, String reportDate,
			String auditorPersonName, String auditorPersonId,
			String auditorDate, String parentorg, String startyear, String startmonth,
			String starttime, String endyear, String endmonth, String endtime) {
		super();
		this.id = id;
		this.year = year;
		this.month = month;
		this.org = org;
		this.orgname = orgname;
		this.domesticAccountAmounts = domesticAccountAmounts;
		this.overseasAccountAmounts = overseasAccountAmounts;
		this.monthlyDollarExchangeRate = monthlyDollarExchangeRate;
		this.financingAccountAmounts = financingAccountAmounts;
		this.domesticAccountCostRate = domesticAccountCostRate;
		this.overseasAccountCostRate = overseasAccountCostRate;
		this.annualInterest = annualInterest;
		this.financingAccountCost = financingAccountCost;
		this.equityFinancing = equityFinancing;
		this.stockEquityFinancing = stockEquityFinancing;
		this.bondFinancing = bondFinancing;
		this.type=type;
		this.remark=remark;
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
		this.startyear = startyear;
		this.startmonth = startmonth;
		this.starttime = starttime;
		this.endyear = endyear;
		this.endmonth = endmonth;
		this.endtime = endtime;
	}

	public String getStartyear() {
		return startyear;
	}

	public String getEndyear() {
		return endyear;
	}

	public String getStartmonth() {
		return startmonth;
	}

	public String getEndmonth() {
		return endmonth;
	}

	public String getStarttime() {
		return starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setStartyear(String startyear) {
		this.startyear = startyear;
	}

	public void setEndyear(String endyear) {
		this.endyear = endyear;
	}

	public void setStartmonth(String startmonth) {
		this.startmonth = startmonth;
	}

	public void setEndmonth(String endmonth) {
		this.endmonth = endmonth;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	// Property accessors
	public Integer getId() {
		return id;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getMonth() {
		return month;
	}

	public String getOrg() {
		return org;
	}

	public String getOrgname() {
		return orgname;
	}

	public String getDomesticAccountAmounts() {
		return domesticAccountAmounts;
	}

	public String getOverseasAccountAmounts() {
		return overseasAccountAmounts;
	}

	public String getMonthlyDollarExchangeRate() {
		return monthlyDollarExchangeRate;
	}

	public String getFinancingAccountAmounts() {
		return financingAccountAmounts;
	}

	public String getDomesticAccountCostRate() {
		return domesticAccountCostRate;
	}

	public String getOverseasAccountCostRate() {
		return overseasAccountCostRate;
	}

	public String getAnnualInterest() {
		return annualInterest;
	}

	public String getFinancingAccountCost() {
		return financingAccountCost;
	}

	public String getEquityFinancing() {
		return equityFinancing;
	}

	public String getStockEquityFinancing() {
		return stockEquityFinancing;
	}

	public String getBondFinancing() {
		return bondFinancing;
	}

	public Integer getStatus() {
		return status;
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

	public String getParentorg() {
		return parentorg;
	}

	public Integer getGetOperateType() {
		return getOperateType;
	}

	public String getStatusName() {
		return statusName;
	}

	public String getDebtTypeName() {
		return debtTypeName;
	}

	public String getSequelNewName() {
		return sequelNewName;
	}

	public String getDomesticOverseasName() {
		return domesticOverseasName;
	}

	public String getCoordinationOperationName() {
		return coordinationOperationName;
	}

	public String getRevitalizeEquityFinancingName() {
		return revitalizeEquityFinancingName;
	}

	public String getLiabilityTypesName() {
		return liabilityTypesName;
	}

	public String getCurrencyKindName() {
		return currencyKindName;
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

	public void setOrg(String org) {
		this.org = org;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public void setDomesticAccountAmounts(String domesticAccountAmounts) {
		this.domesticAccountAmounts = domesticAccountAmounts;
	}

	public void setOverseasAccountAmounts(String overseasAccountAmounts) {
		this.overseasAccountAmounts = overseasAccountAmounts;
	}

	public void setMonthlyDollarExchangeRate(String monthlyDollarExchangeRate) {
		this.monthlyDollarExchangeRate = monthlyDollarExchangeRate;
	}

	public void setFinancingAccountAmounts(String financingAccountAmounts) {
		this.financingAccountAmounts = financingAccountAmounts;
	}

	public void setDomesticAccountCostRate(String domesticAccountCostRate) {
		this.domesticAccountCostRate = domesticAccountCostRate;
	}

	public void setOverseasAccountCostRate(String overseasAccountCostRate) {
		this.overseasAccountCostRate = overseasAccountCostRate;
	}

	public void setAnnualInterest(String annualInterest) {
		this.annualInterest = annualInterest;
	}

	public void setFinancingAccountCost(String financingAccountCost) {
		this.financingAccountCost = financingAccountCost;
	}

	public void setEquityFinancing(String equityFinancing) {
		this.equityFinancing = equityFinancing;
	}

	public void setStockEquityFinancing(String stockEquityFinancing) {
		this.stockEquityFinancing = stockEquityFinancing;
	}

	public void setBondFinancing(String bondFinancing) {
		this.bondFinancing = bondFinancing;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public void setParentorg(String parentorg) {
		this.parentorg = parentorg;
	}

	public void setGetOperateType(Integer getOperateType) {
		this.getOperateType = getOperateType;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public void setDebtTypeName(String debtTypeName) {
		this.debtTypeName = debtTypeName;
	}

	public void setSequelNewName(String sequelNewName) {
		this.sequelNewName = sequelNewName;
	}

	public void setDomesticOverseasName(String domesticOverseasName) {
		this.domesticOverseasName = domesticOverseasName;
	}

	public void setCoordinationOperationName(String coordinationOperationName) {
		this.coordinationOperationName = coordinationOperationName;
	}

	public void setRevitalizeEquityFinancingName(
			String revitalizeEquityFinancingName) {
		this.revitalizeEquityFinancingName = revitalizeEquityFinancingName;
	}

	public void setLiabilityTypesName(String liabilityTypesName) {
		this.liabilityTypesName = liabilityTypesName;
	}

	public void setCurrencyKindName(String currencyKindName) {
		this.currencyKindName = currencyKindName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAuthOrg() {
		return authOrg;
	}

	public void setAuthOrg(String authOrg) {
		this.authOrg = authOrg;
	}

	
}