package com.softline.entity.moneyFlow;

/**
 * DataMoneyFlowMonthCo entity. @author MyEclipse Persistence Tools
 */

public class DataMoneyFlowMonthCo implements java.io.Serializable,Comparable<DataMoneyFlowMonthCo> {

	// Fields

	private Integer id;
	private Integer isdel;
	private String theLoanBusiness;
	private String repaymentAmount;
	private String dateOfPayment;
	private String paymentType;
	private String financingInstitution;
	private String newOrRenewedCo;
	private String paymentCurrency;
	private String guaranteeRepaymentProject;
	private String guaranteeRepaymentPlan;
	private String date;
	private String org;
	private String orgname;
	// Constructors

	/** default constructor */
	public DataMoneyFlowMonthCo() {
	}

	/** full constructor */
	public DataMoneyFlowMonthCo(Integer isdel, String theLoanBusiness,
			String repaymentAmount, String dateOfPayment, String paymentType,
			String financingInstitution, String newOrRenewedCo,
			String paymentCurrency, String guaranteeRepaymentProject) {
		this.isdel = isdel;
		this.theLoanBusiness = theLoanBusiness;
		this.repaymentAmount = repaymentAmount;
		this.dateOfPayment = dateOfPayment;
		this.paymentType = paymentType;
		this.financingInstitution = financingInstitution;
		this.newOrRenewedCo = newOrRenewedCo;
		this.paymentCurrency = paymentCurrency;
		this.guaranteeRepaymentProject = guaranteeRepaymentProject;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public String getGuaranteeRepaymentPlan() {
		return guaranteeRepaymentPlan;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public void setGuaranteeRepaymentPlan(String guaranteeRepaymentPlan) {
		this.guaranteeRepaymentPlan = guaranteeRepaymentPlan;
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

	public String getTheLoanBusiness() {
		return this.theLoanBusiness;
	}

	public void setTheLoanBusiness(String theLoanBusiness) {
		this.theLoanBusiness = theLoanBusiness;
	}

	public String getRepaymentAmount() {
		return this.repaymentAmount;
	}

	public void setRepaymentAmount(String repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}

	public String getDateOfPayment() {
		return this.dateOfPayment;
	}

	public void setDateOfPayment(String dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getFinancingInstitution() {
		return this.financingInstitution;
	}

	public void setFinancingInstitution(String financingInstitution) {
		this.financingInstitution = financingInstitution;
	}

	public String getNewOrRenewedCo() {
		return this.newOrRenewedCo;
	}

	public void setNewOrRenewedCo(String newOrRenewedCo) {
		this.newOrRenewedCo = newOrRenewedCo;
	}

	public String getPaymentCurrency() {
		return this.paymentCurrency;
	}

	public void setPaymentCurrency(String paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}

	public String getGuaranteeRepaymentProject() {
		return this.guaranteeRepaymentProject;
	}

	public void setGuaranteeRepaymentProject(String guaranteeRepaymentProject) {
		this.guaranteeRepaymentProject = guaranteeRepaymentProject;
	}

	@Override
	public int compareTo(DataMoneyFlowMonthCo o) {
		if(o.getId()==null||this.getId()==null){
			return 1;
		}else{
			return (o.getId() - this.getId());
		}
	}

}