package com.softline.entity.moneyFlow;

/**
 * DataMoneyFlowMonthCi entity. @author MyEclipse Persistence Tools
 */

public class DataMoneyFlowWeekCi implements java.io.Serializable,Comparable<DataMoneyFlowWeekCi> {

	// Fields

	private Integer id;
	private Integer isdel;
	private String commitmentShallSubject;
	private String lendingBank;
	private String loanAmount;
	private String placeOrderDate;
	private String financingType;
	private String newOrRenewed;
	private String placeOrderCurrency;
	private String compositeCost;
	private String equityFinancing;
	// Constructors

	/** default constructor */
	public DataMoneyFlowWeekCi() {
	}

	/** full constructor */
	public DataMoneyFlowWeekCi(Integer isdel, String commitmentShallSubject,
			String lendingBank, String loanAmount, String placeOrderDate,
			String financingType, String newOrRenewed,
			String placeOrderCurrency, String compositeCost,
			String equityFinancing) {
		this.isdel = isdel;
		this.commitmentShallSubject = commitmentShallSubject;
		this.lendingBank = lendingBank;
		this.loanAmount = loanAmount;
		this.placeOrderDate = placeOrderDate;
		this.financingType = financingType;
		this.newOrRenewed = newOrRenewed;
		this.placeOrderCurrency = placeOrderCurrency;
		this.compositeCost = compositeCost;
		this.equityFinancing = equityFinancing;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public String getCommitmentShallSubject() {
		return this.commitmentShallSubject;
	}

	public void setCommitmentShallSubject(String commitmentShallSubject) {
		this.commitmentShallSubject = commitmentShallSubject;
	}

	public String getLendingBank() {
		return this.lendingBank;
	}

	public void setLendingBank(String lendingBank) {
		this.lendingBank = lendingBank;
	}

	public String getLoanAmount() {
		return this.loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getPlaceOrderDate() {
		return this.placeOrderDate;
	}

	public void setPlaceOrderDate(String placeOrderDate) {
		this.placeOrderDate = placeOrderDate;
	}

	public String getFinancingType() {
		return this.financingType;
	}

	public void setFinancingType(String financingType) {
		this.financingType = financingType;
	}

	public String getNewOrRenewed() {
		return this.newOrRenewed;
	}

	public void setNewOrRenewed(String newOrRenewed) {
		this.newOrRenewed = newOrRenewed;
	}

	public String getPlaceOrderCurrency() {
		return this.placeOrderCurrency;
	}

	public void setPlaceOrderCurrency(String placeOrderCurrency) {
		this.placeOrderCurrency = placeOrderCurrency;
	}

	public String getCompositeCost() {
		return this.compositeCost;
	}

	public void setCompositeCost(String compositeCost) {
		this.compositeCost = compositeCost;
	}

	public String getEquityFinancing() {
		return this.equityFinancing;
	}

	public void setEquityFinancing(String equityFinancing) {
		this.equityFinancing = equityFinancing;
	}

	public int compareTo(DataMoneyFlowWeekCi o) {
		if(o.getId()==null||this.getId()==null){
			return 1;
		}else{
			return (o.getId() - this.getId());
		}
	}

}