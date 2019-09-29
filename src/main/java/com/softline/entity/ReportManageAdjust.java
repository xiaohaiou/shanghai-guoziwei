package com.softline.entity;


/**
 * ReportManageAdjust entity. @author MyEclipse Persistence Tools
 */

public class ReportManageAdjust implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private Integer month;
	private String org;
	private String orgname;
	private Integer fre;
	private Integer fredata;
	private String totalAssets;
	private String netAssets;
	private String paidInCapital;
	private String netProfit;
	private String assetLiabilityRatio;
	private String assetTurnover;
	private String capitalProfitMargin;
	private String returnOnEquity;
	private String totalLiabilities;
	private String capital;
	private String businessIncome;
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

	private String ebitdaMargins;
//	EBITDA利润率
	private String interestCover;
//	EBITDA利息保障倍数
	private String repaymentRate;
//	偿债备付率
//	新增的三个字段
	private String auditorPersonId;
	private String auditorDate;
	private String parentorg;
	//formalu准则
 	private String statusName;
 	private String freName;
 	private Integer getOperateType;
 	private String startyear;
	private String endyear;
	private String startmonth;
	private String endmonth;
	private String starttime;
	private String endtime;
	private String authOrg;// 数据权限控制字段
	

//新增字段EBITDA
	private String ebitda;
	// Constructors

	/** default constructor */
	public ReportManageAdjust() {
	}

	/** minimal constructor */
	public ReportManageAdjust(Integer year, Integer month, String org, String orgname,
			Integer fre, Integer fredata, String totalAssets, String netAssets,
			String paidInCapital, String netProfit, String assetLiabilityRatio,
			String assetTurnover, String capitalProfitMargin,
			String returnOnEquity, String totalLiabilities, String capital,
			String businessIncome, Integer status, Integer isdel,String ebitdaMargins,String interestCover,String ebitda) {
		this.year = year;
		this.month = month;
		this.org = org;
		this.orgname = orgname;
		this.fre = fre;
		this.fredata = fredata;
		this.totalAssets = totalAssets;
		this.netAssets = netAssets;
		this.paidInCapital = paidInCapital;
		this.netProfit = netProfit;
		this.assetLiabilityRatio = assetLiabilityRatio;
		this.assetTurnover = assetTurnover;
		this.capitalProfitMargin = capitalProfitMargin;
		this.returnOnEquity = returnOnEquity;
		this.totalLiabilities = totalLiabilities;
		this.capital = capital;
		this.businessIncome = businessIncome;
		this.status = status;
		this.isdel = isdel;
		this.ebitdaMargins=ebitdaMargins;
		this.interestCover=interestCover;
		this.ebitda = ebitda;
	}

	/** full constructor */

	
	
	
	

	

	public ReportManageAdjust(Integer id, Integer year, Integer month,
			String org, String orgname, Integer fre, Integer fredata,
			String totalAssets, String netAssets, String paidInCapital,
			String netProfit, String assetLiabilityRatio, String assetTurnover,
			String capitalProfitMargin, String returnOnEquity,
			String totalLiabilities, String capital, String businessIncome,
			Integer status, Integer isdel, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String ebitdaMargins, String interestCover, String auditorPersonId,
			String auditorDate, String parentorg, String statusName,
			String freName, Integer getOperateType, String startyear,
			String endyear, String startmonth, String endmonth,
			String starttime, String endtime,String repaymentRate,String ebitda) {
		this.id = id;
		this.year = year;
		this.month = month;
		this.org = org;
		this.orgname = orgname;
		this.fre = fre;
		this.fredata = fredata;
		this.totalAssets = totalAssets;
		this.netAssets = netAssets;
		this.paidInCapital = paidInCapital;
		this.netProfit = netProfit;
		this.assetLiabilityRatio = assetLiabilityRatio;
		this.assetTurnover = assetTurnover;
		this.capitalProfitMargin = capitalProfitMargin;
		this.returnOnEquity = returnOnEquity;
		this.totalLiabilities = totalLiabilities;
		this.capital = capital;
		this.businessIncome = businessIncome;
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
		this.ebitdaMargins = ebitdaMargins;
		this.interestCover = interestCover;
		this.auditorPersonId = auditorPersonId;
		this.auditorDate = auditorDate;
		this.parentorg = parentorg;
		this.statusName = statusName;
		this.freName = freName;
		this.getOperateType = getOperateType;
		this.startyear = startyear;
		this.endyear = endyear;
		this.startmonth = startmonth;
		this.endmonth = endmonth;
		this.starttime = starttime;
		this.endtime = endtime;
		this.repaymentRate=repaymentRate;
		this.ebitda = ebitda;
	}
// Property accessors
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
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
	
	public Integer getId() {
		return this.id;
	}

	public String getParentorg() {
		return parentorg;
	}

	public void setParentorg(String parentorg) {
		this.parentorg = parentorg;
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

	public Integer getFre() {
		return this.fre;
	}

	public void setFre(Integer fre) {
		this.fre = fre;
	}

	public Integer getFredata() {
		return this.fredata;
	}

	public void setFredata(Integer fredata) {
		this.fredata = fredata;
	}

	public String getTotalAssets() {
		return this.totalAssets;
	}

	public void setTotalAssets(String totalAssets) {
		this.totalAssets = totalAssets;
	}

	public String getNetAssets() {
		return this.netAssets;
	}

	public void setNetAssets(String netAssets) {
		this.netAssets = netAssets;
	}

	public String getPaidInCapital() {
		return this.paidInCapital;
	}

	public void setPaidInCapital(String paidInCapital) {
		this.paidInCapital = paidInCapital;
	}

	public String getNetProfit() {
		return this.netProfit;
	}

	public void setNetProfit(String netProfit) {
		this.netProfit = netProfit;
	}

	public String getAssetLiabilityRatio() {
		return this.assetLiabilityRatio;
	}

	public void setAssetLiabilityRatio(String assetLiabilityRatio) {
		this.assetLiabilityRatio = assetLiabilityRatio;
	}

	public String getAssetTurnover() {
		return this.assetTurnover;
	}

	public void setAssetTurnover(String assetTurnover) {
		this.assetTurnover = assetTurnover;
	}

	public String getCapitalProfitMargin() {
		return this.capitalProfitMargin;
	}

	public void setCapitalProfitMargin(String capitalProfitMargin) {
		this.capitalProfitMargin = capitalProfitMargin;
	}

	public String getReturnOnEquity() {
		return this.returnOnEquity;
	}

	public void setReturnOnEquity(String returnOnEquity) {
		this.returnOnEquity = returnOnEquity;
	}

	public String getTotalLiabilities() {
		return this.totalLiabilities;
	}

	public void setTotalLiabilities(String totalLiabilities) {
		this.totalLiabilities = totalLiabilities;
	}

	public String getCapital() {
		return this.capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getBusinessIncome() {
		return this.businessIncome;
	}

	public void setBusinessIncome(String businessIncome) {
		this.businessIncome = businessIncome;
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getFreName() {
		return freName;
	}

	public void setFreName(String freDataName) {
		this.freName = freDataName;
	}

	public Integer getGetOperateType() {
		return getOperateType;
	}

	public void setGetOperateType(Integer getOperateType) {
		this.getOperateType = getOperateType;
	}

	public String getEbitdaMargins() {
		return ebitdaMargins;
	}

	public void setEbitdaMargins(String ebitdaMargins) {
		this.ebitdaMargins = ebitdaMargins;
	}

	public String getInterestCover() {
		return interestCover;
	}

	public void setInterestCover(String interestCover) {
		this.interestCover = interestCover;
	}

	public String getRepaymentRate() {
		return repaymentRate;
	}

	public void setRepaymentRate(String repaymentRate) {
		this.repaymentRate = repaymentRate;
	}

	public String getEbitda() {
		return ebitda;
	}

	public void setEbitda(String ebitda) {
		this.ebitda = ebitda;
	}

	public String getAuthOrg() {
		return authOrg;
	}

	public void setAuthOrg(String authOrg) {
		this.authOrg = authOrg;
	}
	

}