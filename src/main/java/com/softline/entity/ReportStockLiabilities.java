package com.softline.entity;

import java.util.List;

/**
 * ReportStockLiabilities entity. @author MyEclipse Persistence Tools
 */

public class ReportStockLiabilities implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private Integer month;
	private String org;
	private String orgname;
	private String totalStockLiabilities;
	private String overseasStockLiabilities;
	private String proportionForeignLiabilities;
	private String financialCostStockLiabilities;
	private String othersStockLiabilities;
	private String costStockLiabilities;
	private String longTermLiabilities;
	private String proportionLongTermLiabilities;
	private String domesticStockLiabilities;
	private String domesticRmbLiabilities;
	private String proportionRmbLiabilities;
	private String bondFinancing;
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
	private String bankLoanStockLiabilities;
	private String entrustStockLiabilities;
	private String billStockLiabilities;
	private String fundStockLiabilities;
	private String bondStockLiabilities;
	private String financingRentStockLiabilities;
	
	//formalu
	private String statusName;
	private Integer getOperateType;
	private String startyear;
	private String endyear;
	private String startmonth;
	private String endmonth;
	private String starttime;
	private String endtime;
	
	private List<ReportOverseasLiabilitiesDetail> list1;
	
	private String authOrg;
	
	// Constructors

	/** default constructor */
	public ReportStockLiabilities() {
	}

	/** minimal constructor */
	public ReportStockLiabilities(Integer year, String org, String orgname,
			Integer status, Integer isdel) {
		this.year = year;
		this.org = org;
		this.orgname = orgname;
		this.status = status;
		this.isdel = isdel;
	}

	/** full constructor */
	public ReportStockLiabilities(Integer year, Integer month, String org,
			String orgname, String totalStockLiabilities,
			String overseasStockLiabilities,
			String proportionForeignLiabilities,
			String financialCostStockLiabilities, String othersStockLiabilities, String costStockLiabilities,
			String longTermLiabilities, String proportionLongTermLiabilities,
			String domesticStockLiabilities, String domesticRmbLiabilities,
			String proportionRmbLiabilities, String bondFinancing,
			Integer status, Integer isdel, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate, String parentorg,
			String startyear, String endyear, String startmonth, String endmonth,
			String statusName, String starttime, String endtime,
			String bankLoanStockLiabilities, String entrustStockLiabilities,
			String billStockLiabilities, String fundStockLiabilities,
			String bondStockLiabilities, String financingRentStockLiabilities) {
		this.year = year;
		this.month = month;
		this.org = org;
		this.orgname = orgname;
		this.totalStockLiabilities = totalStockLiabilities;
		this.overseasStockLiabilities = overseasStockLiabilities;
		this.proportionForeignLiabilities = proportionForeignLiabilities;
		this.financialCostStockLiabilities = financialCostStockLiabilities;
		this.othersStockLiabilities = othersStockLiabilities;
		this.costStockLiabilities = costStockLiabilities;
		this.longTermLiabilities = longTermLiabilities;
		this.proportionLongTermLiabilities = proportionLongTermLiabilities;
		this.domesticStockLiabilities = domesticStockLiabilities;
		this.domesticRmbLiabilities = domesticRmbLiabilities;
		this.proportionRmbLiabilities = proportionRmbLiabilities;
		this.bondFinancing = bondFinancing;
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
		this.startyear = startyear;
		this.endyear = endyear;
		this.startmonth = startmonth;
		this.endmonth = endmonth;
		this.statusName = statusName;
		this.starttime = starttime;
		this.endtime = endtime;
		this.bankLoanStockLiabilities = bankLoanStockLiabilities;
		this.entrustStockLiabilities = entrustStockLiabilities;
		this.billStockLiabilities = billStockLiabilities;
		this.fundStockLiabilities = fundStockLiabilities;
		this.bondStockLiabilities = bondStockLiabilities;
		this.financingRentStockLiabilities = financingRentStockLiabilities;
	}
	
	public List<ReportOverseasLiabilitiesDetail> getList1() {
		return list1;
	}

	public void setList1(List<ReportOverseasLiabilitiesDetail> list1) {
		this.list1 = list1;
	}
	
	public String getOthersStockLiabilities() {
		return othersStockLiabilities;
	}

	public void setOthersStockLiabilities(String othersStockLiabilities) {
		this.othersStockLiabilities = othersStockLiabilities;
	}

	public String getBankLoanStockLiabilities() {
		return bankLoanStockLiabilities;
	}

	public String getEntrustStockLiabilities() {
		return entrustStockLiabilities;
	}

	public String getBillStockLiabilities() {
		return billStockLiabilities;
	}

	public String getFundStockLiabilities() {
		return fundStockLiabilities;
	}

	public String getBondStockLiabilities() {
		return bondStockLiabilities;
	}

	public String getFinancingRentStockLiabilities() {
		return financingRentStockLiabilities;
	}

	public void setBankLoanStockLiabilities(String bankLoanStockLiabilities) {
		this.bankLoanStockLiabilities = bankLoanStockLiabilities;
	}

	public void setEntrustStockLiabilities(String entrustStockLiabilities) {
		this.entrustStockLiabilities = entrustStockLiabilities;
	}

	public void setBillStockLiabilities(String billStockLiabilities) {
		this.billStockLiabilities = billStockLiabilities;
	}

	public void setFundStockLiabilities(String fundStockLiabilities) {
		this.fundStockLiabilities = fundStockLiabilities;
	}

	public void setBondStockLiabilities(String bondStockLiabilities) {
		this.bondStockLiabilities = bondStockLiabilities;
	}

	public void setFinancingRentStockLiabilities(
			String financingRentStockLiabilities) {
		this.financingRentStockLiabilities = financingRentStockLiabilities;
	}

	public String getStarttime() {
		return starttime;
	}

	public String getEndtime() {
		return endtime;
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

	// Property accessors
	public Integer getGetOperateType() {
		return getOperateType;
	}

	public void setGetOperateType(Integer getOperateType) {
		this.getOperateType = getOperateType;
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

	public String getTotalStockLiabilities() {
		return this.totalStockLiabilities;
	}

	public void setTotalStockLiabilities(String totalStockLiabilities) {
		this.totalStockLiabilities = totalStockLiabilities;
	}

	public String getOverseasStockLiabilities() {
		return this.overseasStockLiabilities;
	}

	public void setOverseasStockLiabilities(String overseasStockLiabilities) {
		this.overseasStockLiabilities = overseasStockLiabilities;
	}

	public String getProportionForeignLiabilities() {
		return this.proportionForeignLiabilities;
	}

	public void setProportionForeignLiabilities(
			String proportionForeignLiabilities) {
		this.proportionForeignLiabilities = proportionForeignLiabilities;
	}

	public String getFinancialCostStockLiabilities() {
		return this.financialCostStockLiabilities;
	}

	public void setFinancialCostStockLiabilities(
			String financialCostStockLiabilities) {
		this.financialCostStockLiabilities = financialCostStockLiabilities;
	}

	public String getCostStockLiabilities() {
		return this.costStockLiabilities;
	}

	public void setCostStockLiabilities(String costStockLiabilities) {
		this.costStockLiabilities = costStockLiabilities;
	}

	public String getLongTermLiabilities() {
		return this.longTermLiabilities;
	}

	public void setLongTermLiabilities(String longTermLiabilities) {
		this.longTermLiabilities = longTermLiabilities;
	}

	public String getProportionLongTermLiabilities() {
		return this.proportionLongTermLiabilities;
	}

	public void setProportionLongTermLiabilities(
			String proportionLongTermLiabilities) {
		this.proportionLongTermLiabilities = proportionLongTermLiabilities;
	}

	public String getDomesticStockLiabilities() {
		return this.domesticStockLiabilities;
	}

	public void setDomesticStockLiabilities(String domesticStockLiabilities) {
		this.domesticStockLiabilities = domesticStockLiabilities;
	}

	public String getDomesticRmbLiabilities() {
		return this.domesticRmbLiabilities;
	}

	public void setDomesticRmbLiabilities(String domesticRmbLiabilities) {
		this.domesticRmbLiabilities = domesticRmbLiabilities;
	}

	public String getProportionRmbLiabilities() {
		return this.proportionRmbLiabilities;
	}

	public void setProportionRmbLiabilities(String proportionRmbLiabilities) {
		this.proportionRmbLiabilities = proportionRmbLiabilities;
	}

	public String getBondFinancing() {
		return this.bondFinancing;
	}

	public void setBondFinancing(String bondFinancing) {
		this.bondFinancing = bondFinancing;
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