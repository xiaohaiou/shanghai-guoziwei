package com.softline.entity;

import java.math.BigDecimal;

/**
 * TaxSaveGroup entity. @author MyEclipse Persistence Tools
 */

public class TaxSaveGroup implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private Integer month;
	private String org;
	private String remark;
	private String orgname;
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
	private String statusName;
	private Integer getOperateType;
	private String date;
	private BigDecimal nonFinancialReturnTax;
	private BigDecimal nonFinancialReturnTaxCumulative;
	private BigDecimal taxReturnTaxRevenue;
	private BigDecimal taxReturnTaxRevenueCumulative;
	private BigDecimal taxPlanningTax;
	private BigDecimal taxPlanningTaxCumulative;
	private BigDecimal preferentialPoliciesTax;
	private BigDecimal preferentialPoliciesTaxCumulative;
	private BigDecimal taxPeriod;
	private BigDecimal taxPeriodCumulative;
	private String now_task;
	// Constructors

	/** default constructor */
	public TaxSaveGroup() {
	}

	/** minimal constructor */
	public TaxSaveGroup(Integer year, String org, String orgname,
			Integer status, Integer isdel) {
		this.year = year;
		this.org = org;
		this.orgname = orgname;
		this.status = status;
		this.isdel = isdel;
	}

	/** full constructor */
	public TaxSaveGroup(Integer year, Integer month, String org, String remark,
			String orgname, Integer status, Integer isdel,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate, String parentorg,
			String date, BigDecimal nonFinancialReturnTax,
			BigDecimal nonFinancialReturnTaxCumulative, BigDecimal taxReturnTaxRevenue,
			BigDecimal taxReturnTaxRevenueCumulative, BigDecimal taxPlanningTax,
			BigDecimal taxPlanningTaxCumulative, BigDecimal preferentialPoliciesTax,
			BigDecimal preferentialPoliciesTaxCumulative, BigDecimal taxPeriod,
			BigDecimal taxPeriodCumulative) {
		this.year = year;
		this.month = month;
		this.org = org;
		this.remark = remark;
		this.orgname = orgname;
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
		this.date = date;
		this.nonFinancialReturnTax = nonFinancialReturnTax;
		this.nonFinancialReturnTaxCumulative = nonFinancialReturnTaxCumulative;
		this.taxReturnTaxRevenue = taxReturnTaxRevenue;
		this.taxReturnTaxRevenueCumulative = taxReturnTaxRevenueCumulative;
		this.taxPlanningTax = taxPlanningTax;
		this.taxPlanningTaxCumulative = taxPlanningTaxCumulative;
		this.preferentialPoliciesTax = preferentialPoliciesTax;
		this.preferentialPoliciesTaxCumulative = preferentialPoliciesTaxCumulative;
		this.taxPeriod = taxPeriod;
		this.taxPeriodCumulative = taxPeriodCumulative;
	}

	// Property accessors
	
	public Integer getId() {
		return this.id;
	}

	

	public String getNow_task() {
		return now_task;
	}

	public void setNow_task(String now_task) {
		this.now_task = now_task;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getGetOperateType() {
		return getOperateType;
	}

	public void setGetOperateType(Integer getOperateType) {
		this.getOperateType = getOperateType;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrgname() {
		return this.orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
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

	public String getParentorg() {
		return this.parentorg;
	}

	public void setParentorg(String parentorg) {
		this.parentorg = parentorg;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BigDecimal getNonFinancialReturnTax() {
		return this.nonFinancialReturnTax;
	}

	public void setNonFinancialReturnTax(BigDecimal nonFinancialReturnTax) {
		this.nonFinancialReturnTax = nonFinancialReturnTax;
	}

	public BigDecimal getNonFinancialReturnTaxCumulative() {
		return this.nonFinancialReturnTaxCumulative;
	}

	public void setNonFinancialReturnTaxCumulative(
			BigDecimal nonFinancialReturnTaxCumulative) {
		this.nonFinancialReturnTaxCumulative = nonFinancialReturnTaxCumulative;
	}

	public BigDecimal getTaxReturnTaxRevenue() {
		return this.taxReturnTaxRevenue;
	}

	public void setTaxReturnTaxRevenue(BigDecimal taxReturnTaxRevenue) {
		this.taxReturnTaxRevenue = taxReturnTaxRevenue;
	}

	public BigDecimal getTaxReturnTaxRevenueCumulative() {
		return this.taxReturnTaxRevenueCumulative;
	}

	public void setTaxReturnTaxRevenueCumulative(
			BigDecimal taxReturnTaxRevenueCumulative) {
		this.taxReturnTaxRevenueCumulative = taxReturnTaxRevenueCumulative;
	}

	public BigDecimal getTaxPlanningTax() {
		return this.taxPlanningTax;
	}

	public void setTaxPlanningTax(BigDecimal taxPlanningTax) {
		this.taxPlanningTax = taxPlanningTax;
	}

	public BigDecimal getTaxPlanningTaxCumulative() {
		return this.taxPlanningTaxCumulative;
	}

	public void setTaxPlanningTaxCumulative(BigDecimal taxPlanningTaxCumulative) {
		this.taxPlanningTaxCumulative = taxPlanningTaxCumulative;
	}

	public BigDecimal getPreferentialPoliciesTax() {
		return this.preferentialPoliciesTax;
	}

	public void setPreferentialPoliciesTax(BigDecimal preferentialPoliciesTax) {
		this.preferentialPoliciesTax = preferentialPoliciesTax;
	}

	public BigDecimal getPreferentialPoliciesTaxCumulative() {
		return this.preferentialPoliciesTaxCumulative;
	}

	public void setPreferentialPoliciesTaxCumulative(
			BigDecimal preferentialPoliciesTaxCumulative) {
		this.preferentialPoliciesTaxCumulative = preferentialPoliciesTaxCumulative;
	}

	public BigDecimal getTaxPeriod() {
		return this.taxPeriod;
	}

	public void setTaxPeriod(BigDecimal taxPeriod) {
		this.taxPeriod = taxPeriod;
	}

	public BigDecimal getTaxPeriodCumulative() {
		return this.taxPeriodCumulative;
	}

	public void setTaxPeriodCumulative(BigDecimal taxPeriodCumulative) {
		this.taxPeriodCumulative = taxPeriodCumulative;
	}

}