package com.softline.entity.taxTask;

/**
 * DataTaxPayDetailAccNow entity. @author MyEclipse Persistence Tools
 */

public class DataTaxPayDetailAccNow implements java.io.Serializable {

	// Fields

	private Integer id;
	private DataTaxPay dataTaxPay;
	private String year;
	private String month;
	private String org;
	private String orgName;
	private String businessTax;
	private String valueAddedTax;
	private String consumptionTax;
	private String cesTax;
	private String importVat;
	private String tariff;
	private String eincomeTax;
	private String withholdTax;
	private String pincomeTax;
	private String housingTax;
	private String deedTax;
	private String landTax;
	private String landVat;
	private String stampTax;
	private String otherTax;
	private String sum;
	private Integer isdel;
	private String parentorg;

	// Constructors

	/** default constructor */
	public DataTaxPayDetailAccNow() {
	}

	/** minimal constructor */
	public DataTaxPayDetailAccNow(Integer isdel) {
		this.isdel = isdel;
	}

	/** full constructor */
	public DataTaxPayDetailAccNow(DataTaxPay dataTaxPay, String year,
			String month, String org, String orgName, String businessTax,
			String valueAddedTax, String consumptionTax, String cesTax,
			String importVat, String tariff, String eincomeTax,
			String withholdTax, String pincomeTax, String housingTax,
			String deedTax, String landTax, String landVat, String stampTax,
			String otherTax, String sum, Integer isdel, String parentorg) {
		this.dataTaxPay = dataTaxPay;
		this.year = year;
		this.month = month;
		this.org = org;
		this.orgName = orgName;
		this.businessTax = businessTax;
		this.valueAddedTax = valueAddedTax;
		this.consumptionTax = consumptionTax;
		this.cesTax = cesTax;
		this.importVat = importVat;
		this.tariff = tariff;
		this.eincomeTax = eincomeTax;
		this.withholdTax = withholdTax;
		this.pincomeTax = pincomeTax;
		this.housingTax = housingTax;
		this.deedTax = deedTax;
		this.landTax = landTax;
		this.landVat = landVat;
		this.stampTax = stampTax;
		this.otherTax = otherTax;
		this.sum = sum;
		this.isdel = isdel;
		this.parentorg = parentorg;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DataTaxPay getDataTaxPay() {
		return this.dataTaxPay;
	}

	public void setDataTaxPay(DataTaxPay dataTaxPay) {
		this.dataTaxPay = dataTaxPay;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getOrg() {
		return this.org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getBusinessTax() {
		return this.businessTax;
	}

	public void setBusinessTax(String businessTax) {
		this.businessTax = businessTax;
	}

	public String getValueAddedTax() {
		return this.valueAddedTax;
	}

	public void setValueAddedTax(String valueAddedTax) {
		this.valueAddedTax = valueAddedTax;
	}

	public String getConsumptionTax() {
		return this.consumptionTax;
	}

	public void setConsumptionTax(String consumptionTax) {
		this.consumptionTax = consumptionTax;
	}

	public String getCesTax() {
		return this.cesTax;
	}

	public void setCesTax(String cesTax) {
		this.cesTax = cesTax;
	}

	public String getImportVat() {
		return this.importVat;
	}

	public void setImportVat(String importVat) {
		this.importVat = importVat;
	}

	public String getTariff() {
		return this.tariff;
	}

	public void setTariff(String tariff) {
		this.tariff = tariff;
	}

	public String getEincomeTax() {
		return this.eincomeTax;
	}

	public void setEincomeTax(String eincomeTax) {
		this.eincomeTax = eincomeTax;
	}

	public String getWithholdTax() {
		return this.withholdTax;
	}

	public void setWithholdTax(String withholdTax) {
		this.withholdTax = withholdTax;
	}

	public String getPincomeTax() {
		return this.pincomeTax;
	}

	public void setPincomeTax(String pincomeTax) {
		this.pincomeTax = pincomeTax;
	}

	public String getHousingTax() {
		return this.housingTax;
	}

	public void setHousingTax(String housingTax) {
		this.housingTax = housingTax;
	}

	public String getDeedTax() {
		return this.deedTax;
	}

	public void setDeedTax(String deedTax) {
		this.deedTax = deedTax;
	}

	public String getLandTax() {
		return this.landTax;
	}

	public void setLandTax(String landTax) {
		this.landTax = landTax;
	}

	public String getLandVat() {
		return this.landVat;
	}

	public void setLandVat(String landVat) {
		this.landVat = landVat;
	}

	public String getStampTax() {
		return this.stampTax;
	}

	public void setStampTax(String stampTax) {
		this.stampTax = stampTax;
	}

	public String getOtherTax() {
		return this.otherTax;
	}

	public void setOtherTax(String otherTax) {
		this.otherTax = otherTax;
	}

	public String getSum() {
		return this.sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public String getParentorg() {
		return this.parentorg;
	}

	public void setParentorg(String parentorg) {
		this.parentorg = parentorg;
	}

}