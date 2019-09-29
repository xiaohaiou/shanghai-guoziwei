package com.softline.entity;

/**
 * ReportFinancingOutDetail entity. @author MyEclipse Persistence Tools
 */

public class ReportMonthlyFinancingOutDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer monthId;
	private String memberCompId;
	private String memberCompName;
	private String coreCompId;
	private String coreCompName;
	private String loanCompId;
	private String loanCompName;
	private String repayBank;
	private Integer financingType;
	private Integer financingTypeDetail;
	private String repayAmount;
	private Integer currency;
	private Integer isdel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String repayDate;
	
	//formula
	private String financingTypeName;
	private String financingTypeDetailName;
	private String currencyName;

	// Constructors

	/** default constructor */
	public ReportMonthlyFinancingOutDetail() {
	}

	/** full constructor */
	public ReportMonthlyFinancingOutDetail(Integer monthId, String memberCompId,
			String memberCompName, String coreCompId, String coreCompName,
			String loanCompId, String loanCompName, String repayBank,
			Integer financingType, Integer financingTypeDetail,
			String repayAmount, Integer currency, Integer isdel,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String repayDate) {
		this.monthId = monthId;
		this.memberCompId = memberCompId;
		this.memberCompName = memberCompName;
		this.coreCompId = coreCompId;
		this.coreCompName = coreCompName;
		this.loanCompId = loanCompId;
		this.loanCompName = loanCompName;
		this.repayBank = repayBank;
		this.financingType = financingType;
		this.financingTypeDetail = financingTypeDetail;
		this.repayAmount = repayAmount;
		this.currency = currency;
		this.isdel = isdel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.repayDate = repayDate;
	}

	public String getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

	// Property accessors
	public String getFinancingTypeName() {
		return financingTypeName;
	}

	public String getFinancingTypeDetailName() {
		return financingTypeDetailName;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setFinancingTypeName(String financingTypeName) {
		this.financingTypeName = financingTypeName;
	}

	public void setFinancingTypeDetailName(String financingTypeDetailName) {
		this.financingTypeDetailName = financingTypeDetailName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMonthId() {
		return this.monthId;
	}

	public void setMonthId(Integer monthId) {
		this.monthId = monthId;
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

	public String getLoanCompId() {
		return this.loanCompId;
	}

	public void setLoanCompId(String loanCompId) {
		this.loanCompId = loanCompId;
	}

	public String getLoanCompName() {
		return this.loanCompName;
	}

	public void setLoanCompName(String loanCompName) {
		this.loanCompName = loanCompName;
	}

	public String getRepayBank() {
		return this.repayBank;
	}

	public void setRepayBank(String repayBank) {
		this.repayBank = repayBank;
	}

	public Integer getFinancingType() {
		return this.financingType;
	}

	public void setFinancingType(Integer financingType) {
		this.financingType = financingType;
	}

	public Integer getFinancingTypeDetail() {
		return this.financingTypeDetail;
	}

	public void setFinancingTypeDetail(Integer financingTypeDetail) {
		this.financingTypeDetail = financingTypeDetail;
	}

	public String getRepayAmount() {
		return this.repayAmount;
	}

	public void setRepayAmount(String repayAmount) {
		this.repayAmount = repayAmount;
	}

	public Integer getCurrency() {
		return this.currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
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