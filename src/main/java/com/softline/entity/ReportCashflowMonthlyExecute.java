package com.softline.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractReportCashflowMonthlyExecute entity provides the base persistence
 * definition of the ReportCashflowMonthlyExecute entity. @author MyEclipse
 * Persistence Tools
 */

public class ReportCashflowMonthlyExecute implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private Integer month;
	private String org;
	private String orgname;
	private String coreCompName;
	private String coreCompId;
	private String commercialInto;
	private String commercialOut;
	private String commercialProject;
	private String commercialNetInflow;
	private String financingInto;
	private String financingOut;
	private String returnCapitalAmounts;
	private String returnInterestAmounts;
	private String returnNoteAmounts;
	private String returnRentAmounts;
	private String financingNetInflow;
	private String investmentInto;
	private String constructionInProcess;
	private String airplaneInvest;
	private String stockEquityInvest;
	private String landReserve;
	private String fixedAssetsPurchase;
	private String financeInvest;
	private String elseInvestExpense;
	private String investOut;
	private String investNetInflow;
	private String lendingOrContributionInto;
	private String lendingOrContributionOut;
	private String lendingOrContributionNetInflow;
	private String cashInto;
	private String cashOut;
	private String cashNetInflow;
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
	
	//formula
	private String statusName;
	//筹资性流入明细列表集合
	private List<ReportMonthlyFinancingIntoDetail> list1 = new ArrayList<ReportMonthlyFinancingIntoDetail>();
	//筹资性流出明细列表集合
	private List<ReportMonthlyFinancingOutDetail> list2 = new ArrayList<ReportMonthlyFinancingOutDetail>();
	//投资性流出明细列表集合
	private List<ReportMonthlyInvestmentOutDetail> list3 = new ArrayList<ReportMonthlyInvestmentOutDetail>();
	
	//common
	private Integer getOperateType;
	private String startyear;
	private String endyear;
	private String startmonth;
	private String endmonth;
	private String starttime;
	private String endtime;
	
	private String authOrg;
	
	public Integer getGetOperateType() {
		return getOperateType;
	}

	public void setGetOperateType(Integer getOperateType) {
		this.getOperateType = getOperateType;
	}

	// Constructors
	/** default constructor */
	public ReportCashflowMonthlyExecute() {
	}

	/** minimal constructor */
	public ReportCashflowMonthlyExecute(Integer year, String org,
			String orgname, Integer status, Integer isdel) {
		this.year = year;
		this.org = org;
		this.orgname = orgname;
		this.status = status;
		this.isdel = isdel;
	}

	/** full constructor */
	public ReportCashflowMonthlyExecute(Integer year, Integer month,
			String org, String orgname, String coreCompName, String coreCompId, String commercialInto,
			String commercialOut, String commercialProject,
			String commercialNetInflow, String financingInto,
			String financingOut, String returnCapitalAmounts, String returnInterestAmounts,
			String returnNoteAmounts, String returnRentAmounts, String financingNetInflow,
			String investmentInto, String constructionInProcess,
			String airplaneInvest, String stockEquityInvest,
			String landReserve, String fixedAssetsPurchase,
			String financeInvest, String elseInvestExpense, String investOut,
			String investNetInflow, String lendingOrContributionInto,
			String lendingOrContributionOut,
			String lendingOrContributionNetInflow, String cashInto,
			String cashOut, String cashNetInflow, String remark, Integer status,
			Integer isdel, String createPersonName, String createPersonId,
			String createDate, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate,
			String reportPersonName, String reportPersonId, String reportDate,
			String auditorPersonName, String auditorPersonId,
			String auditorDate, String parentorg) {
		this.year = year;
		this.month = month;
		this.org = org;
		this.orgname = orgname;
		this.coreCompName = coreCompName;
		this.coreCompId = coreCompId;
		this.commercialInto = commercialInto;
		this.commercialOut = commercialOut;
		this.commercialProject = commercialProject;
		this.commercialNetInflow = commercialNetInflow;
		this.financingInto = financingInto;
		this.financingOut = financingOut;
		this.returnCapitalAmounts = returnCapitalAmounts;
		this.returnInterestAmounts = returnInterestAmounts;
		this.returnNoteAmounts = returnNoteAmounts;
		this.returnRentAmounts = returnRentAmounts;
		this.financingNetInflow = financingNetInflow;
		this.investmentInto = investmentInto;
		this.constructionInProcess = constructionInProcess;
		this.airplaneInvest = airplaneInvest;
		this.stockEquityInvest = stockEquityInvest;
		this.landReserve = landReserve;
		this.fixedAssetsPurchase = fixedAssetsPurchase;
		this.financeInvest = financeInvest;
		this.elseInvestExpense = elseInvestExpense;
		this.investOut = investOut;
		this.investNetInflow = investNetInflow;
		this.lendingOrContributionInto = lendingOrContributionInto;
		this.lendingOrContributionOut = lendingOrContributionOut;
		this.lendingOrContributionNetInflow = lendingOrContributionNetInflow;
		this.cashInto = cashInto;
		this.cashOut = cashOut;
		this.cashNetInflow = cashNetInflow;
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
		this.parentorg = parentorg;
	}
	
	public String getReturnCapitalAmounts() {
		return returnCapitalAmounts;
	}

	public String getReturnInterestAmounts() {
		return returnInterestAmounts;
	}

	public String getReturnNoteAmounts() {
		return returnNoteAmounts;
	}

	public String getReturnRentAmounts() {
		return returnRentAmounts;
	}

	public void setReturnCapitalAmounts(String returnCapitalAmounts) {
		this.returnCapitalAmounts = returnCapitalAmounts;
	}

	public void setReturnInterestAmounts(String returnInterestAmounts) {
		this.returnInterestAmounts = returnInterestAmounts;
	}

	public void setReturnNoteAmounts(String returnNoteAmounts) {
		this.returnNoteAmounts = returnNoteAmounts;
	}

	public void setReturnRentAmounts(String returnRentAmounts) {
		this.returnRentAmounts = returnRentAmounts;
	}

	public String getCoreCompName() {
		return coreCompName;
	}

	public String getCoreCompId() {
		return coreCompId;
	}

	public void setCoreCompName(String coreCompName) {
		this.coreCompName = coreCompName;
	}

	public void setCoreCompId(String coreCompId) {
		this.coreCompId = coreCompId;
	}

	public List<ReportMonthlyFinancingIntoDetail> getList1() {
		return list1;
	}

	public List<ReportMonthlyFinancingOutDetail> getList2() {
		return list2;
	}

	public List<ReportMonthlyInvestmentOutDetail> getList3() {
		return list3;
	}

	public void setList1(List<ReportMonthlyFinancingIntoDetail> list1) {
		this.list1 = list1;
	}

	public void setList2(List<ReportMonthlyFinancingOutDetail> list2) {
		this.list2 = list2;
	}

	public void setList3(List<ReportMonthlyInvestmentOutDetail> list3) {
		this.list3 = list3;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	// Property accessors
	public String getStartyear() {
		return startyear;
	}

	public String getEndyear() {
		return endyear;
	}

	public String getStartmonth() {
		return startmonth;
	}

	public String getEndmonth() {
		return endmonth;
	}

	public String getStarttime() {
		return starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setStartyear(String startyear) {
		this.startyear = startyear;
	}

	public void setEndyear(String endyear) {
		this.endyear = endyear;
	}

	public void setStartmonth(String startmonth) {
		this.startmonth = startmonth;
	}

	public void setEndmonth(String endmonth) {
		this.endmonth = endmonth;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getId() {
		return this.id;
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

	public String getOrgname() {
		return this.orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getCommercialInto() {
		return this.commercialInto;
	}

	public void setCommercialInto(String commercialInto) {
		this.commercialInto = commercialInto;
	}

	public String getCommercialOut() {
		return this.commercialOut;
	}

	public void setCommercialOut(String commercialOut) {
		this.commercialOut = commercialOut;
	}

	public String getCommercialProject() {
		return this.commercialProject;
	}

	public void setCommercialProject(String commercialProject) {
		this.commercialProject = commercialProject;
	}

	public String getCommercialNetInflow() {
		return this.commercialNetInflow;
	}

	public void setCommercialNetInflow(String commercialNetInflow) {
		this.commercialNetInflow = commercialNetInflow;
	}

	public String getFinancingInto() {
		return this.financingInto;
	}

	public void setFinancingInto(String financingInto) {
		this.financingInto = financingInto;
	}

	public String getFinancingOut() {
		return this.financingOut;
	}

	public void setFinancingOut(String financingOut) {
		this.financingOut = financingOut;
	}

	public String getFinancingNetInflow() {
		return this.financingNetInflow;
	}

	public void setFinancingNetInflow(String financingNetInflow) {
		this.financingNetInflow = financingNetInflow;
	}

	public String getInvestmentInto() {
		return this.investmentInto;
	}

	public void setInvestmentInto(String investmentInto) {
		this.investmentInto = investmentInto;
	}

	public String getConstructionInProcess() {
		return this.constructionInProcess;
	}

	public void setConstructionInProcess(String constructionInProcess) {
		this.constructionInProcess = constructionInProcess;
	}

	public String getAirplaneInvest() {
		return this.airplaneInvest;
	}

	public void setAirplaneInvest(String airplaneInvest) {
		this.airplaneInvest = airplaneInvest;
	}

	public String getStockEquityInvest() {
		return this.stockEquityInvest;
	}

	public void setStockEquityInvest(String stockEquityInvest) {
		this.stockEquityInvest = stockEquityInvest;
	}

	public String getLandReserve() {
		return this.landReserve;
	}

	public void setLandReserve(String landReserve) {
		this.landReserve = landReserve;
	}

	public String getFixedAssetsPurchase() {
		return this.fixedAssetsPurchase;
	}

	public void setFixedAssetsPurchase(String fixedAssetsPurchase) {
		this.fixedAssetsPurchase = fixedAssetsPurchase;
	}

	public String getFinanceInvest() {
		return this.financeInvest;
	}

	public void setFinanceInvest(String financeInvest) {
		this.financeInvest = financeInvest;
	}

	public String getElseInvestExpense() {
		return this.elseInvestExpense;
	}

	public void setElseInvestExpense(String elseInvestExpense) {
		this.elseInvestExpense = elseInvestExpense;
	}

	public String getInvestOut() {
		return this.investOut;
	}

	public void setInvestOut(String investOut) {
		this.investOut = investOut;
	}

	public String getInvestNetInflow() {
		return this.investNetInflow;
	}

	public void setInvestNetInflow(String investNetInflow) {
		this.investNetInflow = investNetInflow;
	}

	public String getLendingOrContributionInto() {
		return this.lendingOrContributionInto;
	}

	public void setLendingOrContributionInto(String lendingOrContributionInto) {
		this.lendingOrContributionInto = lendingOrContributionInto;
	}

	public String getLendingOrContributionOut() {
		return this.lendingOrContributionOut;
	}

	public void setLendingOrContributionOut(String lendingOrContributionOut) {
		this.lendingOrContributionOut = lendingOrContributionOut;
	}

	public String getLendingOrContributionNetInflow() {
		return this.lendingOrContributionNetInflow;
	}

	public void setLendingOrContributionNetInflow(
			String lendingOrContributionNetInflow) {
		this.lendingOrContributionNetInflow = lendingOrContributionNetInflow;
	}

	public String getCashInto() {
		return this.cashInto;
	}

	public void setCashInto(String cashInto) {
		this.cashInto = cashInto;
	}

	public String getCashOut() {
		return this.cashOut;
	}

	public void setCashOut(String cashOut) {
		this.cashOut = cashOut;
	}

	public String getCashNetInflow() {
		return this.cashNetInflow;
	}

	public void setCashNetInflow(String cashNetInflow) {
		this.cashNetInflow = cashNetInflow;
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

	public String getAuthOrg() {
		return authOrg;
	}

	public void setAuthOrg(String authOrg) {
		this.authOrg = authOrg;
	}

}