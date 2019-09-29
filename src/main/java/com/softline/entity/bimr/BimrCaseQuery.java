package com.softline.entity.bimr;

import java.math.BigDecimal;



/**
 * BimrCivilcaseWeek entity. @author MyEclipse Persistence Tools
 */

public class BimrCaseQuery  implements java.io.Serializable { 
	 private String year;
	 private String week;
     private String caseNum;
     private String caseCategory;
     private String vcCompanyId;
     private String vcCompanyName;
     private String province;
     private BigDecimal amount;
     private String type;
     private String caseDate;
     private String version;

     public BimrCaseQuery() {
     }

	public BimrCaseQuery(String year, String week, String caseNum,
			String caseCategory, String vcCompanyId, String vcCompanyName,
			String province, BigDecimal amount, String type, String caseDate,
			String version) {
		super();
		this.year = year;
		this.week = week;
		this.caseNum = caseNum;
		this.caseCategory = caseCategory;
		this.vcCompanyId = vcCompanyId;
		this.vcCompanyName = vcCompanyName;
		this.province = province;
		this.amount = amount;
		this.type = type;
		this.caseDate = caseDate;
		this.version = version;
	}

	public String getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	public String getCaseCategory() {
		return caseCategory;
	}

	public void setCaseCategory(String caseCategory) {
		this.caseCategory = caseCategory;
	}

	public String getVcCompanyId() {
		return vcCompanyId;
	}

	public void setVcCompanyId(String vcCompanyId) {
		this.vcCompanyId = vcCompanyId;
	}

	public String getVcCompanyName() {
		return vcCompanyName;
	}

	public void setVcCompanyName(String vcCompanyName) {
		this.vcCompanyName = vcCompanyName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCaseDate() {
		return caseDate;
	}

	public void setCaseDate(String caseDate) {
		this.caseDate = caseDate;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

}