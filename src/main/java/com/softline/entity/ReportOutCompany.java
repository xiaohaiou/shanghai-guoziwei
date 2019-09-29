package com.softline.entity;

public class ReportOutCompany {
	  
	private Integer id;
	private Integer year;
	private Integer season;
	private  String orgname;
	private String org;
	private String orgcode;
    private String createDate;
    private String modifyDate;
    
    private String basicPerEarning;
    private String perNetAsset;
    private String totalOperatingIncome;
    private String grossProfit;
    private String attributableNetProfit;
    private String revenueIncrease;
    private String revenueGrowth;
    private String netAssetYield;
    private String grossInterestRate;
    private String netInterestRate;
    private String totalAssetTurnover;
    private String inventoryTurnoverDays;
    private String accountReceivableDays;
    private String accountLiabilityRatio;
    private String flowRate;
    
    private Integer status;
    private Integer isdel;
    
    private String createPersonName;
    private String  createPersonId;
    private String modifyPersonName;
    private String  modifyPersonId;
    private String reportPersonName;
    private String  reportPersonId;
    private String reportDate;
    private String  auditorPersonName;
    private String auditorPersonId;
    private String  auditorDate;
    private String parentorg;
    
	private Integer getOperateType;

    
	//formula
	private String authOrg; // 数据权限控制字段
    
    
	public ReportOutCompany() {
	}
    



	public ReportOutCompany(Integer id, Integer year, Integer season,
			String orgname, String org, String orgcode, String createDate,
			String modifyDate, String basicPerEarning, String perNetAsset,
			String totalOperatingIncome, String grossProfit,
			String attributableNetProfit, String revenueIncrease,
			String revenueGrowth, String netAssetYield,
			String grossInterestRate, String netInterestRate,
			String totalAssetTurnover, String inventoryTurnoverDays,
			String accountReceivableDays, String accountLiabilityRatio,
			String flowRate, Integer status, Integer isdel,
			String createPersonName, String createPersonId,
			String modifyPersonName, String modifyPersonId,
			String reportPersonName, String reportPersonId, String reportDate,
			String auditorPersonName, String auditorPersonId,
			String auditorDate, String parentorg, Integer getOperateType) {
		super();
		this.id = id;
		this.year = year;
		this.season = season;
		this.orgname = orgname;
		this.org = org;
		this.orgcode = orgcode;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.basicPerEarning = basicPerEarning;
		this.perNetAsset = perNetAsset;
		this.totalOperatingIncome = totalOperatingIncome;
		this.grossProfit = grossProfit;
		this.attributableNetProfit = attributableNetProfit;
		this.revenueIncrease = revenueIncrease;
		this.revenueGrowth = revenueGrowth;
		this.netAssetYield = netAssetYield;
		this.grossInterestRate = grossInterestRate;
		this.netInterestRate = netInterestRate;
		this.totalAssetTurnover = totalAssetTurnover;
		this.inventoryTurnoverDays = inventoryTurnoverDays;
		this.accountReceivableDays = accountReceivableDays;
		this.accountLiabilityRatio = accountLiabilityRatio;
		this.flowRate = flowRate;
		this.status = status;
		this.isdel = isdel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.modifyPersonName = modifyPersonName;
		this.modifyPersonId = modifyPersonId;
		this.reportPersonName = reportPersonName;
		this.reportPersonId = reportPersonId;
		this.reportDate = reportDate;
		this.auditorPersonName = auditorPersonName;
		this.auditorPersonId = auditorPersonId;
		this.auditorDate = auditorDate;
		this.parentorg = parentorg;
		this.getOperateType = getOperateType;
	}




	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getSeason() {
		return season;
	}
	public void setSeason(Integer season) {
		this.season = season;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}


	public String getBasicPerEarning() {
		return basicPerEarning;
	}


	public void setBasicPerEarning(String basicPerEarning) {
		this.basicPerEarning = basicPerEarning;
	}


	public String getPerNetAsset() {
		return perNetAsset;
	}


	public void setPerNetAsset(String perNetAsset) {
		this.perNetAsset = perNetAsset;
	}


	public String getTotalOperatingIncome() {
		return totalOperatingIncome;
	}


	public void setTotalOperatingIncome(String totalOperatingIncome) {
		this.totalOperatingIncome = totalOperatingIncome;
	}


	public String getGrossProfit() {
		return grossProfit;
	}


	public void setGrossProfit(String grossProfit) {
		this.grossProfit = grossProfit;
	}


	public String getAttributableNetProfit() {
		return attributableNetProfit;
	}


	public void setAttributableNetProfit(String attributableNetProfit) {
		this.attributableNetProfit = attributableNetProfit;
	}


	public String getRevenueIncrease() {
		return revenueIncrease;
	}


	public void setRevenueIncrease(String revenueIncrease) {
		this.revenueIncrease = revenueIncrease;
	}


	public String getRevenueGrowth() {
		return revenueGrowth;
	}


	public void setRevenueGrowth(String revenueGrowth) {
		this.revenueGrowth = revenueGrowth;
	}


	public String getNetAssetYield() {
		return netAssetYield;
	}


	public void setNetAssetYield(String netAssetYield) {
		this.netAssetYield = netAssetYield;
	}


	public String getGrossInterestRate() {
		return grossInterestRate;
	}


	public void setGrossInterestRate(String grossInterestRate) {
		this.grossInterestRate = grossInterestRate;
	}


	public String getNetInterestRate() {
		return netInterestRate;
	}


	public void setNetInterestRate(String netInterestRate) {
		this.netInterestRate = netInterestRate;
	}


	public String getTotalAssetTurnover() {
		return totalAssetTurnover;
	}


	public void setTotalAssetTurnover(String totalAssetTurnover) {
		this.totalAssetTurnover = totalAssetTurnover;
	}


	public String getInventoryTurnoverDays() {
		return inventoryTurnoverDays;
	}


	public void setInventoryTurnoverDays(String inventoryTurnoverDays) {
		this.inventoryTurnoverDays = inventoryTurnoverDays;
	}


	public String getAccountReceivableDays() {
		return accountReceivableDays;
	}


	public void setAccountReceivableDays(String accountReceivableDays) {
		this.accountReceivableDays = accountReceivableDays;
	}


	public String getAccountLiabilityRatio() {
		return accountLiabilityRatio;
	}


	public void setAccountLiabilityRatio(String accountLiabilityRatio) {
		this.accountLiabilityRatio = accountLiabilityRatio;
	}


	public String getFlowRate() {
		return flowRate;
	}


	public void setFlowRate(String flowRate) {
		this.flowRate = flowRate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}


	public String getCreatePersonName() {
		return createPersonName;
	}


	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}


	public String getCreatePersonId() {
		return createPersonId;
	}


	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}


	public String getModifyPersonName() {
		return modifyPersonName;
	}


	public void setModifyPersonName(String modifyPersonName) {
		this.modifyPersonName = modifyPersonName;
	}


	public String getModifyPersonId() {
		return modifyPersonId;
	}


	public void setModifyPersonId(String modifyPersonId) {
		this.modifyPersonId = modifyPersonId;
	}


	public String getReportPersonName() {
		return reportPersonName;
	}


	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}


	public String getReportPersonId() {
		return reportPersonId;
	}


	public void setReportPersonId(String reportPersonId) {
		this.reportPersonId = reportPersonId;
	}


	public String getReportDate() {
		return reportDate;
	}


	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}


	public String getAuditorPersonName() {
		return auditorPersonName;
	}


	public void setAuditorPersonName(String auditorPersonName) {
		this.auditorPersonName = auditorPersonName;
	}


	public String getAuditorPersonId() {
		return auditorPersonId;
	}


	public void setAuditorPersonId(String auditorPersonId) {
		this.auditorPersonId = auditorPersonId;
	}


	public String getAuditorDate() {
		return auditorDate;
	}


	public void setAuditorDate(String auditorDate) {
		this.auditorDate = auditorDate;
	}


	public String getParentorg() {
		return parentorg;
	}


	public void setParentorg(String parentorg) {
		this.parentorg = parentorg;
	}




	public Integer getGetOperateType() {
		return getOperateType;
	}




	public void setGetOperateType(Integer getOperateType) {
		this.getOperateType = getOperateType;
	}




	public String getAuthOrg() {
		return authOrg;
	}




	public void setAuthOrg(String authOrg) {
		this.authOrg = authOrg;
	}

}
