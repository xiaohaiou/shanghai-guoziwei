package com.softline.entity;

import java.util.List;

/**
 * MainFinancialIndicator entity. @author MyEclipse Persistence Tools
 */

public class MainFinancialIndicator implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private Integer month;
	private String org;
	private String orgname;
	private String businessorgname;
	
	private String chargePerson;
	private String address;
	private String mainCamp;
	private String accountingFirm;
	private Integer reportType;
	private String totalAssets;
	private String totalLiabilities;
	private String ownerEquity;
	private String paidCapital;
	private String assetLiabilityRatio;
	private String totalRevenue;
	private String operatingCost;
	private String totalProfit;
	private String netProfit;
	private String monetaryFund;
	private String stock;
	private String otherReceivables;
	private String investmentTypeRealEstate;
	private String fixedAssets;
	private String underConstructionProject;
	private String intangibleAssets;
	private String otherPayable;
	private String shortTermLoan;
	private String nonCurrentLiabilities;
	private String longTermLoan;
	private String longTermPayables;
	private String undistributedProfit;
	private String ownerEquityAttributableToTheParentCompany;
	private String minorityShareholdersEquity;
	private String businessTaxesAndSurcharges;
	private String sellingExpenses;
	private String managementExpenses;
	private String financialExpenses;
	private String fairValueChangeIncome;
	private String incomeFromInvestment;
	private String incomeTaxExpense;
	private String registeredCapital;
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
	
	private String bondsPayable;
	private String notessPayable;
	
	private String phoneNumber;
	//formalu
	
	private Integer getOperateType;
	private String reportTypeName;
	private String statusName;
	private String authOrg; // 权限控制字段
	
	//temp
	private List<MainFinancialIndicatorStock> stick1;//注册资本的股东信息列表
	private List<MainFinancialIndicatorStock> stick2;//实收资本的股东信息列表
	// Constructors

	/** default constructor */
	public MainFinancialIndicator() {
	}

	/** minimal constructor */
	public MainFinancialIndicator(Integer year, String org, String orgname,
			String chargePerson, String address, String mainCamp,
			Integer status, Integer isdel) {
		this.year = year;
		this.org = org;
		this.orgname = orgname;
		this.chargePerson = chargePerson;
		this.address = address;
		this.mainCamp = mainCamp;
		this.status = status;
		this.isdel = isdel;
	}

	/** full constructor */
	public MainFinancialIndicator(Integer year, String org, String orgname,
			String chargePerson, String address, String mainCamp,
			String accountingFirm, Integer reportType, String totalAssets,
			String totalLiabilities, String ownerEquity, String paidCapital,
			String assetLiabilityRatio, String totalRevenue,
			String operatingCost, String totalProfit, String netProfit,
			String monetaryFund, String stock, String otherReceivables,
			String investmentTypeRealEstate, String fixedAssets,
			String underConstructionProject, String intangibleAssets,
			String otherPayable, String shortTermLoan,
			String nonCurrentLiabilities, String longTermLoan,
			String longTermPayables, String undistributedProfit,
			String ownerEquityAttributableToTheParentCompany,
			String minorityShareholdersEquity,
			String businessTaxesAndSurcharges, String sellingExpenses,
			String managementExpenses, String financialExpenses,
			String fairValueChangeIncome, String incomeFromInvestment,
			String incomeTaxExpense, String registeredCapital, String remark,
			Integer status, Integer isdel, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate,String bondsPayable,String notessPayable,String phoneNumber) {
		this.year = year;
		this.org = org;
		this.orgname = orgname;
		this.chargePerson = chargePerson;
		this.address = address;
		this.mainCamp = mainCamp;
		this.accountingFirm = accountingFirm;
		this.reportType = reportType;
		this.totalAssets = totalAssets;
		this.totalLiabilities = totalLiabilities;
		this.ownerEquity = ownerEquity;
		this.paidCapital = paidCapital;
		this.assetLiabilityRatio = assetLiabilityRatio;
		this.totalRevenue = totalRevenue;
		this.operatingCost = operatingCost;
		this.totalProfit = totalProfit;
		this.netProfit = netProfit;
		this.monetaryFund = monetaryFund;
		this.stock = stock;
		this.otherReceivables = otherReceivables;
		this.investmentTypeRealEstate = investmentTypeRealEstate;
		this.fixedAssets = fixedAssets;
		this.underConstructionProject = underConstructionProject;
		this.intangibleAssets = intangibleAssets;
		this.otherPayable = otherPayable;
		this.shortTermLoan = shortTermLoan;
		this.nonCurrentLiabilities = nonCurrentLiabilities;
		this.longTermLoan = longTermLoan;
		this.longTermPayables = longTermPayables;
		this.undistributedProfit = undistributedProfit;
		this.ownerEquityAttributableToTheParentCompany = ownerEquityAttributableToTheParentCompany;
		this.minorityShareholdersEquity = minorityShareholdersEquity;
		this.businessTaxesAndSurcharges = businessTaxesAndSurcharges;
		this.sellingExpenses = sellingExpenses;
		this.managementExpenses = managementExpenses;
		this.financialExpenses = financialExpenses;
		this.fairValueChangeIncome = fairValueChangeIncome;
		this.incomeFromInvestment = incomeFromInvestment;
		this.incomeTaxExpense = incomeTaxExpense;
		this.registeredCapital = registeredCapital;
		this.remark = remark;
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
		this.notessPayable=notessPayable;
		this.bondsPayable=bondsPayable;
		this.phoneNumber=phoneNumber;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}




	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYear() {
		return this.year;
	}

	public String getReportTypeName() {
		return reportTypeName;
	}

	public void setReportTypeName(String reportTypeName) {
		this.reportTypeName = reportTypeName;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getOrg() {
		return this.org;
	}

	public Integer getGetOperateType() {
		return getOperateType;
	}

	public void setGetOperateType(Integer getOperateType) {
		this.getOperateType = getOperateType;
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

	public String getChargePerson() {
		return this.chargePerson;
	}

	public void setChargePerson(String chargePerson) {
		this.chargePerson = chargePerson;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMainCamp() {
		return this.mainCamp;
	}

	public void setMainCamp(String mainCamp) {
		this.mainCamp = mainCamp;
	}

	public String getAccountingFirm() {
		return this.accountingFirm;
	}

	public void setAccountingFirm(String accountingFirm) {
		this.accountingFirm = accountingFirm;
	}

	public Integer getReportType() {
		return this.reportType;
	}

	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}

	public String getTotalAssets() {
		return this.totalAssets;
	}

	public void setTotalAssets(String totalAssets) {
		this.totalAssets = totalAssets;
	}

	public String getTotalLiabilities() {
		return this.totalLiabilities;
	}

	public void setTotalLiabilities(String totalLiabilities) {
		this.totalLiabilities = totalLiabilities;
	}

	public String getOwnerEquity() {
		return this.ownerEquity;
	}

	public void setOwnerEquity(String ownerEquity) {
		this.ownerEquity = ownerEquity;
	}

	public String getPaidCapital() {
		return this.paidCapital;
	}

	public void setPaidCapital(String paidCapital) {
		this.paidCapital = paidCapital;
	}

	public String getAssetLiabilityRatio() {
		return this.assetLiabilityRatio;
	}

	public void setAssetLiabilityRatio(String assetLiabilityRatio) {
		this.assetLiabilityRatio = assetLiabilityRatio;
	}

	public String getTotalRevenue() {
		return this.totalRevenue;
	}

	public void setTotalRevenue(String totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public String getOperatingCost() {
		return this.operatingCost;
	}

	public void setOperatingCost(String operatingCost) {
		this.operatingCost = operatingCost;
	}

	public String getTotalProfit() {
		return this.totalProfit;
	}

	public void setTotalProfit(String totalProfit) {
		this.totalProfit = totalProfit;
	}

	public String getNetProfit() {
		return this.netProfit;
	}

	public void setNetProfit(String netProfit) {
		this.netProfit = netProfit;
	}

	public String getMonetaryFund() {
		return this.monetaryFund;
	}

	public void setMonetaryFund(String monetaryFund) {
		this.monetaryFund = monetaryFund;
	}

	public String getStock() {
		return this.stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getOtherReceivables() {
		return this.otherReceivables;
	}

	public void setOtherReceivables(String otherReceivables) {
		this.otherReceivables = otherReceivables;
	}

	public String getInvestmentTypeRealEstate() {
		return this.investmentTypeRealEstate;
	}

	public void setInvestmentTypeRealEstate(String investmentTypeRealEstate) {
		this.investmentTypeRealEstate = investmentTypeRealEstate;
	}

	public String getFixedAssets() {
		return this.fixedAssets;
	}

	public void setFixedAssets(String fixedAssets) {
		this.fixedAssets = fixedAssets;
	}

	public String getUnderConstructionProject() {
		return this.underConstructionProject;
	}

	public void setUnderConstructionProject(String underConstructionProject) {
		this.underConstructionProject = underConstructionProject;
	}

	public String getIntangibleAssets() {
		return this.intangibleAssets;
	}

	public void setIntangibleAssets(String intangibleAssets) {
		this.intangibleAssets = intangibleAssets;
	}

	public String getOtherPayable() {
		return this.otherPayable;
	}

	public void setOtherPayable(String otherPayable) {
		this.otherPayable = otherPayable;
	}

	public String getShortTermLoan() {
		return this.shortTermLoan;
	}

	public void setShortTermLoan(String shortTermLoan) {
		this.shortTermLoan = shortTermLoan;
	}

	public String getNonCurrentLiabilities() {
		return this.nonCurrentLiabilities;
	}

	public void setNonCurrentLiabilities(String nonCurrentLiabilities) {
		this.nonCurrentLiabilities = nonCurrentLiabilities;
	}

	public String getLongTermLoan() {
		return this.longTermLoan;
	}

	public void setLongTermLoan(String longTermLoan) {
		this.longTermLoan = longTermLoan;
	}

	public String getLongTermPayables() {
		return this.longTermPayables;
	}

	public void setLongTermPayables(String longTermPayables) {
		this.longTermPayables = longTermPayables;
	}

	public String getUndistributedProfit() {
		return this.undistributedProfit;
	}

	public void setUndistributedProfit(String undistributedProfit) {
		this.undistributedProfit = undistributedProfit;
	}

	public String getOwnerEquityAttributableToTheParentCompany() {
		return this.ownerEquityAttributableToTheParentCompany;
	}

	public void setOwnerEquityAttributableToTheParentCompany(
			String ownerEquityAttributableToTheParentCompany) {
		this.ownerEquityAttributableToTheParentCompany = ownerEquityAttributableToTheParentCompany;
	}

	public String getMinorityShareholdersEquity() {
		return this.minorityShareholdersEquity;
	}

	public void setMinorityShareholdersEquity(String minorityShareholdersEquity) {
		this.minorityShareholdersEquity = minorityShareholdersEquity;
	}

	public String getBusinessTaxesAndSurcharges() {
		return this.businessTaxesAndSurcharges;
	}

	public void setBusinessTaxesAndSurcharges(String businessTaxesAndSurcharges) {
		this.businessTaxesAndSurcharges = businessTaxesAndSurcharges;
	}

	public String getSellingExpenses() {
		return this.sellingExpenses;
	}

	public void setSellingExpenses(String sellingExpenses) {
		this.sellingExpenses = sellingExpenses;
	}

	public String getManagementExpenses() {
		return this.managementExpenses;
	}

	public void setManagementExpenses(String managementExpenses) {
		this.managementExpenses = managementExpenses;
	}

	public String getFinancialExpenses() {
		return this.financialExpenses;
	}

	public void setFinancialExpenses(String financialExpenses) {
		this.financialExpenses = financialExpenses;
	}

	public String getFairValueChangeIncome() {
		return this.fairValueChangeIncome;
	}

	public void setFairValueChangeIncome(String fairValueChangeIncome) {
		this.fairValueChangeIncome = fairValueChangeIncome;
	}

	public String getIncomeFromInvestment() {
		return this.incomeFromInvestment;
	}

	public void setIncomeFromInvestment(String incomeFromInvestment) {
		this.incomeFromInvestment = incomeFromInvestment;
	}

	public String getIncomeTaxExpense() {
		return this.incomeTaxExpense;
	}

	public void setIncomeTaxExpense(String incomeTaxExpense) {
		this.incomeTaxExpense = incomeTaxExpense;
	}

	public String getRegisteredCapital() {
		return this.registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getBusinessorgname() {
		return businessorgname;
	}

	public void setBusinessorgname(String businessorgname) {
		this.businessorgname = businessorgname;
	}

	public String getParentorg() {
		return parentorg;
	}

	public void setParentorg(String parentorg) {
		this.parentorg = parentorg;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public List<MainFinancialIndicatorStock> getStick1() {
		return stick1;
	}

	public void setStick1(List<MainFinancialIndicatorStock> stick1) {
		this.stick1 = stick1;
	}

	public List<MainFinancialIndicatorStock> getStick2() {
		return stick2;
	}

	public void setStick2(List<MainFinancialIndicatorStock> stick2) {
		this.stick2 = stick2;
	}

	public String getBondsPayable() {
		return bondsPayable;
	}

	public void setBondsPayable(String bondsPayable) {
		this.bondsPayable = bondsPayable;
	}

	public String getNotessPayable() {
		return notessPayable;
	}

	public void setNotessPayable(String notessPayable) {
		this.notessPayable = notessPayable;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAuthOrg() {
		return authOrg;
	}

	public void setAuthOrg(String authOrg) {
		this.authOrg = authOrg;
	}
	
	
	

}