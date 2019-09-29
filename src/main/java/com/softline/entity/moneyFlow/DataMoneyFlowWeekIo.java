package com.softline.entity.moneyFlow;

/**
 * DataMoneyFlowMonthIo entity. @author MyEclipse Persistence Tools
 */

public class DataMoneyFlowWeekIo implements java.io.Serializable,Comparable<DataMoneyFlowWeekIo> {

	// Fields

	private Integer id;
	private Integer isdel;
	private String unitOfInvestment;			//单位投资
	private String dateOfInvestment;			//投资日期
	private String nameOfInvestmentProject;		//投资项目名称
	private String investmentAmount;			//投资金额(万元)
	private String specialFundGuaranteeScheme;	//专项资金保障方案
	private String theInvestmentCurrency;		//投资币种
	private String investmentType;				//投资类型
	private String guaranteeRepaymentPlan;		//是否有保障还款计划

	// Constructors

	/** default constructor */
	public DataMoneyFlowWeekIo() {
	}

	/** full constructor */
	public DataMoneyFlowWeekIo(Integer isdel, String unitOfInvestment,
			String dateOfInvestment, String nameOfInvestmentProject,
			String investmentAmount, String specialFundGuaranteeScheme,
			String theInvestmentCurrency, String investmentType) {
		this.isdel = isdel;
		this.unitOfInvestment = unitOfInvestment;
		this.dateOfInvestment = dateOfInvestment;
		this.nameOfInvestmentProject = nameOfInvestmentProject;
		this.investmentAmount = investmentAmount;
		this.specialFundGuaranteeScheme = specialFundGuaranteeScheme;
		this.theInvestmentCurrency = theInvestmentCurrency;
		this.investmentType = investmentType;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public String getGuaranteeRepaymentPlan() {
		return guaranteeRepaymentPlan;
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

	public String getUnitOfInvestment() {
		return this.unitOfInvestment;
	}

	public void setUnitOfInvestment(String unitOfInvestment) {
		this.unitOfInvestment = unitOfInvestment;
	}

	public String getDateOfInvestment() {
		return this.dateOfInvestment;
	}

	public void setDateOfInvestment(String dateOfInvestment) {
		this.dateOfInvestment = dateOfInvestment;
	}

	public String getNameOfInvestmentProject() {
		return this.nameOfInvestmentProject;
	}

	public void setNameOfInvestmentProject(String nameOfInvestmentProject) {
		this.nameOfInvestmentProject = nameOfInvestmentProject;
	}

	public String getInvestmentAmount() {
		return this.investmentAmount;
	}

	public void setInvestmentAmount(String investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	public String getSpecialFundGuaranteeScheme() {
		return this.specialFundGuaranteeScheme;
	}

	public void setSpecialFundGuaranteeScheme(String specialFundGuaranteeScheme) {
		this.specialFundGuaranteeScheme = specialFundGuaranteeScheme;
	}

	public String getTheInvestmentCurrency() {
		return this.theInvestmentCurrency;
	}

	public void setTheInvestmentCurrency(String theInvestmentCurrency) {
		this.theInvestmentCurrency = theInvestmentCurrency;
	}

	public String getInvestmentType() {
		return this.investmentType;
	}

	public void setInvestmentType(String investmentType) {
		this.investmentType = investmentType;
	}

	@Override
	public int compareTo(DataMoneyFlowWeekIo o) {
		if(o.getId()==null||this.getId()==null){
			return 1;
		}else{
			return (o.getId() - this.getId());
		}
	}

}