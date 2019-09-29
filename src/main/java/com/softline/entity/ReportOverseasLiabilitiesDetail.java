package com.softline.entity;

/**
 * ReportOverseasLiabilitiesDetail entity. @author MyEclipse Persistence Tools
 */

public class ReportOverseasLiabilitiesDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer stockLiabilitiesId;
	private Integer overseasCurrency;
	private String overseasLiabilitiesAmounts;
	private Integer isdel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	
	//formula
	private String overseasCurrencyName;

	// Constructors

	/** default constructor */
	public ReportOverseasLiabilitiesDetail() {
	}

	/** full constructor */
	public ReportOverseasLiabilitiesDetail(Integer stockLiabilitiesId,
			Integer overseasCurrency, String overseasLiabilitiesAmounts,
			Integer isdel, String createPersonName, String createPersonId,
			String createDate, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate, String overseasCurrencyName) {
		this.stockLiabilitiesId = stockLiabilitiesId;
		this.overseasCurrency = overseasCurrency;
		this.overseasLiabilitiesAmounts = overseasLiabilitiesAmounts;
		this.isdel = isdel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.overseasCurrencyName = overseasCurrencyName;
	}

	// Property accessors

	public String getOverseasCurrencyName() {
		return overseasCurrencyName;
	}

	public void setOverseasCurrencyName(String overseasCurrencyName) {
		this.overseasCurrencyName = overseasCurrencyName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStockLiabilitiesId() {
		return this.stockLiabilitiesId;
	}

	public void setStockLiabilitiesId(Integer stockLiabilitiesId) {
		this.stockLiabilitiesId = stockLiabilitiesId;
	}

	public Integer getOverseasCurrency() {
		return this.overseasCurrency;
	}

	public void setOverseasCurrency(Integer overseasCurrency) {
		this.overseasCurrency = overseasCurrency;
	}

	public String getOverseasLiabilitiesAmounts() {
		return this.overseasLiabilitiesAmounts;
	}

	public void setOverseasLiabilitiesAmounts(String overseasLiabilitiesAmounts) {
		this.overseasLiabilitiesAmounts = overseasLiabilitiesAmounts;
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

}