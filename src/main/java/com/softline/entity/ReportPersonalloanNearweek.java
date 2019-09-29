package com.softline.entity;

/**
 * ReportPersonalloanNearweek entity. @author MyEclipse Persistence Tools
 */

public class ReportPersonalloanNearweek implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer groupid;
	private String coreorg;
	private String coreorgname;
	private String org;
	private String orgname;
	private Integer year;
	private Integer week;
	private String loantotalmoney;
	private Integer loansumperson;
	private Integer loansumoverperson;
	private String loantotalovermoney;
	private Integer monthsumaddperson;
	private Integer monthsumfinishperson;
	private String parentorg;
	private String date;
	private Integer isdel;
	
	//formula
	private String authOrg;  // 数据权限字段

	// Constructors

	/** default constructor */
	public ReportPersonalloanNearweek() {
	}

	/** minimal constructor */
	public ReportPersonalloanNearweek(Integer isdel) {
		this.isdel = isdel;
	}

	/** full constructor */
	public ReportPersonalloanNearweek(Integer groupid, String coreorg,
			String coreorgname, Integer year, Integer week,
			String loantotalmoney, Integer loansumperson,
			Integer loansumoverperson, String loantotalovermoney,
			Integer monthsumaddperson, Integer monthsumfinishperson,
			String parentorg, String date, Integer isdel) {
		this.groupid = groupid;
		this.coreorg = coreorg;
		this.coreorgname = coreorgname;
		this.year = year;
		this.week = week;
		this.loantotalmoney = loantotalmoney;
		this.loansumperson = loansumperson;
		this.loansumoverperson = loansumoverperson;
		this.loantotalovermoney = loantotalovermoney;
		this.monthsumaddperson = monthsumaddperson;
		this.monthsumfinishperson = monthsumfinishperson;
		this.parentorg = parentorg;
		this.date = date;
		this.isdel = isdel;
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

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getWeek() {
		return this.week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public String getLoantotalmoney() {
		return this.loantotalmoney;
	}

	public void setLoantotalmoney(String loantotalmoney) {
		this.loantotalmoney = loantotalmoney;
	}

	public Integer getLoansumperson() {
		return this.loansumperson;
	}

	public void setLoansumperson(Integer loansumperson) {
		this.loansumperson = loansumperson;
	}

	public Integer getLoansumoverperson() {
		return this.loansumoverperson;
	}

	public void setLoansumoverperson(Integer loansumoverperson) {
		this.loansumoverperson = loansumoverperson;
	}

	public String getLoantotalovermoney() {
		return this.loantotalovermoney;
	}

	public void setLoantotalovermoney(String loantotalovermoney) {
		this.loantotalovermoney = loantotalovermoney;
	}

	public Integer getMonthsumaddperson() {
		return this.monthsumaddperson;
	}

	public void setMonthsumaddperson(Integer monthsumaddperson) {
		this.monthsumaddperson = monthsumaddperson;
	}

	public Integer getMonthsumfinishperson() {
		return this.monthsumfinishperson;
	}

	public void setMonthsumfinishperson(Integer monthsumfinishperson) {
		this.monthsumfinishperson = monthsumfinishperson;
	}

	public String getParentorg() {
		return this.parentorg;
	}

	public void setParentorg(String parentorg) {
		this.parentorg = parentorg;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getAuthOrg() {
		return authOrg;
	}

	public void setAuthOrg(String authOrg) {
		this.authOrg = authOrg;
	}

}