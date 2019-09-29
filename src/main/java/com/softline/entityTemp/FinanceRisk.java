package com.softline.entityTemp;

import java.util.List;

public class FinanceRisk {
	
	private String org;
	private String orgNm;
	private String year;
	private String month;
	
	private Double assetLiabilities;//资产负债率
	private Double quickRatio;//速动比率
	private Double netAssetsRatio;//担保净资产比
	private Double interestMultiplier;//已获利息倍数
	private Double cashTotalAssetRatio;//现金总资产比
	private Double inventoryTurnover;//存货周转率
	private Double accountsReceivableTurnover;//应收账款周转率
	private Double surplusCashProtectionMltiple;//盈余现金保障倍数
	private Double roe;//净资产收益率
	private Double operatingProfit;//经营性营业利润占总利润的比重
	private Double ope;//主营业务利润率
	private Double financialInvestmentYield;//金融类投资收益率
	private Double assetBasedInvestmentYield;//资产类投资收益率
	private Double investmentCashYield;//投资现金收益率
	public FinanceRisk() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FinanceRisk(String org, String orgNm, String year, String month, Double assetLiabilities, Double quickRatio,
			Double netAssetsRatio, Double interestMultiplier, Double cashTotalAssetRatio, Double inventoryTurnover,
			Double accountsReceivableTurnover, Double surplusCashProtectionMltiple, Double roe, Double operatingProfit,
			Double ope, Double financialInvestmentYield, Double assetBasedInvestmentYield, Double investmentCashYield) {
		super();
		this.org = org;
		this.orgNm = orgNm;
		this.year = year;
		this.month = month;
		this.assetLiabilities = assetLiabilities;
		this.quickRatio = quickRatio;
		this.netAssetsRatio = netAssetsRatio;
		this.interestMultiplier = interestMultiplier;
		this.cashTotalAssetRatio = cashTotalAssetRatio;
		this.inventoryTurnover = inventoryTurnover;
		this.accountsReceivableTurnover = accountsReceivableTurnover;
		this.surplusCashProtectionMltiple = surplusCashProtectionMltiple;
		this.roe = roe;
		this.operatingProfit = operatingProfit;
		this.ope = ope;
		this.financialInvestmentYield = financialInvestmentYield;
		this.assetBasedInvestmentYield = assetBasedInvestmentYield;
		this.investmentCashYield = investmentCashYield;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Double getAssetLiabilities() {
		return assetLiabilities;
	}
	public void setAssetLiabilities(Double assetLiabilities) {
		this.assetLiabilities = assetLiabilities;
	}
	public Double getQuickRatio() {
		return quickRatio;
	}
	public void setQuickRatio(Double quickRatio) {
		this.quickRatio = quickRatio;
	}
	public Double getNetAssetsRatio() {
		return netAssetsRatio;
	}
	public void setNetAssetsRatio(Double netAssetsRatio) {
		this.netAssetsRatio = netAssetsRatio;
	}
	public Double getInterestMultiplier() {
		return interestMultiplier;
	}
	public void setInterestMultiplier(Double interestMultiplier) {
		this.interestMultiplier = interestMultiplier;
	}
	public Double getCashTotalAssetRatio() {
		return cashTotalAssetRatio;
	}
	public void setCashTotalAssetRatio(Double cashTotalAssetRatio) {
		this.cashTotalAssetRatio = cashTotalAssetRatio;
	}
	public Double getInventoryTurnover() {
		return inventoryTurnover;
	}
	public void setInventoryTurnover(Double inventoryTurnover) {
		this.inventoryTurnover = inventoryTurnover;
	}
	public Double getAccountsReceivableTurnover() {
		return accountsReceivableTurnover;
	}
	public void setAccountsReceivableTurnover(Double accountsReceivableTurnover) {
		this.accountsReceivableTurnover = accountsReceivableTurnover;
	}
	public Double getSurplusCashProtectionMltiple() {
		return surplusCashProtectionMltiple;
	}
	public void setSurplusCashProtectionMltiple(Double surplusCashProtectionMltiple) {
		this.surplusCashProtectionMltiple = surplusCashProtectionMltiple;
	}
	public Double getRoe() {
		return roe;
	}
	public void setRoe(Double roe) {
		this.roe = roe;
	}
	public Double getOperatingProfit() {
		return operatingProfit;
	}
	public void setOperatingProfit(Double operatingProfit) {
		this.operatingProfit = operatingProfit;
	}
	public Double getOpe() {
		return ope;
	}
	public void setOpe(Double ope) {
		this.ope = ope;
	}
	public Double getFinancialInvestmentYield() {
		return financialInvestmentYield;
	}
	public void setFinancialInvestmentYield(Double financialInvestmentYield) {
		this.financialInvestmentYield = financialInvestmentYield;
	}
	public Double getAssetBasedInvestmentYield() {
		return assetBasedInvestmentYield;
	}
	public void setAssetBasedInvestmentYield(Double assetBasedInvestmentYield) {
		this.assetBasedInvestmentYield = assetBasedInvestmentYield;
	}
	public Double getInvestmentCashYield() {
		return investmentCashYield;
	}
	public void setInvestmentCashYield(Double investmentCashYield) {
		this.investmentCashYield = investmentCashYield;
	}
	
	
}
