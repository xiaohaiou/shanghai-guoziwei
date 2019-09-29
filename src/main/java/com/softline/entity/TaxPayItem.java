package com.softline.entity;

import java.math.BigDecimal;

/**
 * TaxPayItem entity. @author MyEclipse Persistence Tools
 */

public class TaxPayItem implements java.io.Serializable {

	// Fields

	private Integer id;
	private String org;
	private String orgname;
	private Integer groupId;
	private BigDecimal nowPay;
	private BigDecimal prevPay;
	private BigDecimal simultaneousComparison;
	private BigDecimal cumulativeAnnualSubscription;
	private BigDecimal prevCumulativeAnnualSubscription;
	private BigDecimal ratioIncreaseDecrease;
	private Integer isdel;
	private Integer sort;

	// Constructors

	/** default constructor */
	public TaxPayItem() {
	}

	/** minimal constructor */
	public TaxPayItem(String org, String orgname, Integer isdel) {
		this.org = org;
		this.orgname = orgname;
		this.isdel = isdel;
	}

	/** full constructor */
	public TaxPayItem(String org, String orgname, Integer groupId,
			BigDecimal nowPay, BigDecimal prevPay, BigDecimal simultaneousComparison,
			BigDecimal cumulativeAnnualSubscription,
			BigDecimal prevCumulativeAnnualSubscription,
			BigDecimal ratioIncreaseDecrease, Integer isdel, Integer sort) {
		this.org = org;
		this.orgname = orgname;
		this.groupId = groupId;
		this.nowPay = nowPay;
		this.prevPay = prevPay;
		this.simultaneousComparison = simultaneousComparison;
		this.cumulativeAnnualSubscription = cumulativeAnnualSubscription;
		this.prevCumulativeAnnualSubscription = prevCumulativeAnnualSubscription;
		this.ratioIncreaseDecrease = ratioIncreaseDecrease;
		this.isdel = isdel;
		this.sort = sort;
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

	public BigDecimal getNowPay() {
		return this.nowPay;
	}

	public void setNowPay(BigDecimal nowPay) {
		this.nowPay = nowPay;
	}

	public BigDecimal getPrevPay() {
		return this.prevPay;
	}

	public void setPrevPay(BigDecimal prevPay) {
		this.prevPay = prevPay;
	}

	public BigDecimal getSimultaneousComparison() {
		return this.simultaneousComparison;
	}

	public void setSimultaneousComparison(BigDecimal simultaneousComparison) {
		this.simultaneousComparison = simultaneousComparison;
	}

	public BigDecimal getCumulativeAnnualSubscription() {
		return this.cumulativeAnnualSubscription;
	}

	public void setCumulativeAnnualSubscription(
			BigDecimal cumulativeAnnualSubscription) {
		this.cumulativeAnnualSubscription = cumulativeAnnualSubscription;
	}

	public BigDecimal getPrevCumulativeAnnualSubscription() {
		return this.prevCumulativeAnnualSubscription;
	}

	public void setPrevCumulativeAnnualSubscription(
			BigDecimal prevCumulativeAnnualSubscription) {
		this.prevCumulativeAnnualSubscription = prevCumulativeAnnualSubscription;
	}

	public BigDecimal getRatioIncreaseDecrease() {
		return this.ratioIncreaseDecrease;
	}

	public void setRatioIncreaseDecrease(BigDecimal ratioIncreaseDecrease) {
		this.ratioIncreaseDecrease = ratioIncreaseDecrease;
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

}