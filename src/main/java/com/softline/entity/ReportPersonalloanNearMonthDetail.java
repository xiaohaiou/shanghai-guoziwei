package com.softline.entity;

/**
 * ReportPersonalloanNearMonthDetail entity. @author MyEclipse Persistence Tools
 */

public class ReportPersonalloanNearMonthDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer groupid;
	private String coreorg;
	private String coreorgname;
	private String org;
	private String orgname;
	private Integer year;
	private Integer month;
	private String personname;
	private String beginningbalance;
	private String monthaddmoney;
	private String monthreturnmoney;
	private String endingbalance;
	private Integer isdel;
	private String parentorg;

	// Constructors

	/** default constructor */
	public ReportPersonalloanNearMonthDetail() {
	}

	/** minimal constructor */
	public ReportPersonalloanNearMonthDetail(Integer isdel) {
		this.isdel = isdel;
	}

	/** full constructor */
	public ReportPersonalloanNearMonthDetail(Integer groupid, String coreorg,
			String coreorgname, String org, String orgname, Integer year,
			Integer month, String personname, String beginningbalance,
			String monthaddmoney, String monthreturnmoney,
			String endingbalance, Integer isdel, String parentorg) {
		this.groupid = groupid;
		this.coreorg = coreorg;
		this.coreorgname = coreorgname;
		this.org = org;
		this.orgname = orgname;
		this.year = year;
		this.month = month;
		this.personname = personname;
		this.beginningbalance = beginningbalance;
		this.monthaddmoney = monthaddmoney;
		this.monthreturnmoney = monthreturnmoney;
		this.endingbalance = endingbalance;
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

	public Integer getGroupid() {
		return this.groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public String getCoreorg() {
		return this.coreorg;
	}

	public void setCoreorg(String coreorg) {
		this.coreorg = coreorg;
	}

	public String getCoreorgname() {
		return this.coreorgname;
	}

	public void setCoreorgname(String coreorgname) {
		this.coreorgname = coreorgname;
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

	public String getPersonname() {
		return this.personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public String getBeginningbalance() {
		return this.beginningbalance;
	}

	public void setBeginningbalance(String beginningbalance) {
		this.beginningbalance = beginningbalance;
	}

	public String getMonthaddmoney() {
		return this.monthaddmoney;
	}

	public void setMonthaddmoney(String monthaddmoney) {
		this.monthaddmoney = monthaddmoney;
	}

	public String getMonthreturnmoney() {
		return this.monthreturnmoney;
	}

	public void setMonthreturnmoney(String monthreturnmoney) {
		this.monthreturnmoney = monthreturnmoney;
	}

	public String getEndingbalance() {
		return this.endingbalance;
	}

	public void setEndingbalance(String endingbalance) {
		this.endingbalance = endingbalance;
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