package com.softline.entity;

/**
 * ReportFinancingIntoDetail entity. @author MyEclipse Persistence Tools
 */

public class ReportMonthlyFinancingIntoDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer monthId;
	private String memberCompId;
	private String memberCompName;
	private String coreCompId;
	private String coreCompName;
	private String loanCompId;
	private String loanCompName;
	private String loanBank;
	private String loanAmount;
	private String accountDate;
	private String dueDate;
	private String lengthOfMaturity;
	private String financingAccountCost;
	private Integer type;
	private Integer loanType;
	private Integer isdel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	
	//formula
	private String typeName;
	private String loanTypeName;
	
	// Constructors

	/** default constructor */
	public ReportMonthlyFinancingIntoDetail() {
	}

	/** full constructor */
	public ReportMonthlyFinancingIntoDetail(Integer monthId, String memberCompId,
			String memberCompName, String coreCompId, String coreCompName,
			String loanCompId, String loanCompName, String loanBank,
			String loanAmount, String accountDate, String dueDate,
			String lengthOfMaturity, String financingAccountCost, Integer type,
			Integer loanType, Integer isdel, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate) {
		this.monthId = monthId;
		this.memberCompId = memberCompId;
		this.memberCompName = memberCompName;
		this.coreCompId = coreCompId;
		this.coreCompName = coreCompName;
		this.loanCompId = loanCompId;
		this.loanCompName = loanCompName;
		this.loanBank = loanBank;
		this.loanAmount = loanAmount;
		this.accountDate = accountDate;
		this.dueDate = dueDate;
		this.lengthOfMaturity = lengthOfMaturity;
		this.financingAccountCost = financingAccountCost;
		this.type = type;
		this.loanType = loanType;
		this.isdel = isdel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
	}

	// Property accessors
	public String getTypeName() {
		return typeName;
	}

	public String getLoanTypeName() {
		return loanTypeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setLoanTypeName(String loanTypeName) {
		this.loanTypeName = loanTypeName;
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

	public String getLoanBank() {
		return this.loanBank;
	}

	public void setLoanBank(String loanBank) {
		this.loanBank = loanBank;
	}

	public String getLoanAmount() {
		return this.loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getAccountDate() {
		return this.accountDate;
	}

	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}

	public String getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getLengthOfMaturity() {
		return this.lengthOfMaturity;
	}

	public void setLengthOfMaturity(String lengthOfMaturity) {
		this.lengthOfMaturity = lengthOfMaturity;
	}

	public String getFinancingAccountCost() {
		return this.financingAccountCost;
	}

	public void setFinancingAccountCost(String financingAccountCost) {
		this.financingAccountCost = financingAccountCost;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLoanType() {
		return this.loanType;
	}

	public void setLoanType(Integer loanType) {
		this.loanType = loanType;
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