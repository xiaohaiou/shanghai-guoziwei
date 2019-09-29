package com.softline.entity;

import java.math.BigDecimal;

/**
 * TaxSaveItem entity. @author MyEclipse Persistence Tools
 */

public class TaxSaveItem implements java.io.Serializable {

	// Fields

	private Integer id;
	private String org;
	private String orgname;
	private Integer groupId;
	private Integer isdel;
	private Integer sort;
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

	// Constructors

	/** default constructor */
	public TaxSaveItem() {
	}

	/** minimal constructor */
	public TaxSaveItem(String orgname, Integer isdel) {
		this.orgname = orgname;
		this.isdel = isdel;
	}

	/** full constructor */
	public TaxSaveItem(String org, String orgname, Integer groupId,
			Integer isdel, Integer sort, BigDecimal nonFinancialReturnTax,
			BigDecimal nonFinancialReturnTaxCumulative, BigDecimal taxReturnTaxRevenue,
			BigDecimal taxReturnTaxRevenueCumulative, BigDecimal taxPlanningTax,
			BigDecimal taxPlanningTaxCumulative, BigDecimal preferentialPoliciesTax,
			BigDecimal preferentialPoliciesTaxCumulative, BigDecimal taxPeriod,
			BigDecimal taxPeriodCumulative) {
		this.org = org;
		this.orgname = orgname;
		this.groupId = groupId;
		this.isdel = isdel;
		this.sort = sort;
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

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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