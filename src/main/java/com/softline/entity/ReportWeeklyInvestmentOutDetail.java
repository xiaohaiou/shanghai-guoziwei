package com.softline.entity;

/**
 * ReportInvestmentOutDetail entity. @author MyEclipse Persistence Tools
 */

public class ReportWeeklyInvestmentOutDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer weekId;
	private String memberCompId;
	private String memberCompName;
	private String coreCompId;
	private String coreCompName;
	private String payProjectName;
	private String payAmount;
	private Integer currency;
	private String specialFundSupportPlan;
	private Integer investType;
	private Integer isdel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String payDate;
	
	//formula
	private String currencyName;
	private String investTypeName;
	
	// Constructors

	/** default constructor */
	public ReportWeeklyInvestmentOutDetail() {
	}

	/** full constructor */
	public ReportWeeklyInvestmentOutDetail(Integer weekId, String memberCompId,
			String memberCompName, String coreCompId, String coreCompName,
			String payProjectName, String payAmount, Integer currency,
			String specialFundSupportPlan, Integer investType, Integer isdel,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String payDate) {
		this.weekId = weekId;
		this.memberCompId = memberCompId;
		this.memberCompName = memberCompName;
		this.coreCompId = coreCompId;
		this.coreCompName = coreCompName;
		this.payProjectName = payProjectName;
		this.payAmount = payAmount;
		this.currency = currency;
		this.specialFundSupportPlan = specialFundSupportPlan;
		this.investType = investType;
		this.isdel = isdel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.payDate = payDate;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	// Property accessors
	public String getCurrencyName() {
		return currencyName;
	}

	public String getInvestTypeName() {
		return investTypeName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public void setInvestTypeName(String investTypeName) {
		this.investTypeName = investTypeName;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWeekId() {
		return this.weekId;
	}

	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
	}

	public String getMemberCompId() {
		return this.memberCompId;
	}

	public void setMemberCompId(String memberCompId) {
		this.memberCompId = memberCompId;
	}

	public String getMemberCompName() {
		return this.memberCompName;
	}

	public void setMemberCompName(String memberCompName) {
		this.memberCompName = memberCompName;
	}

	public String getCoreCompId() {
		return this.coreCompId;
	}

	public void setCoreCompId(String coreCompId) {
		this.coreCompId = coreCompId;
	}

	public String getCoreCompName() {
		return this.coreCompName;
	}

	public void setCoreCompName(String coreCompName) {
		this.coreCompName = coreCompName;
	}

	public String getPayProjectName() {
		return this.payProjectName;
	}

	public void setPayProjectName(String payProjectName) {
		this.payProjectName = payProjectName;
	}

	public String getPayAmount() {
		return this.payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public Integer getCurrency() {
		return this.currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public String getSpecialFundSupportPlan() {
		return this.specialFundSupportPlan;
	}

	public void setSpecialFundSupportPlan(String specialFundSupportPlan) {
		this.specialFundSupportPlan = specialFundSupportPlan;
	}

	public Integer getInvestType() {
		return this.investType;
	}

	public void setInvestType(Integer investType) {
		this.investType = investType;
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

}