package com.softline.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * ReportPersonalloanNearMonth entity. @author MyEclipse Persistence Tools
 */

public class ReportPersonalloanNearMonth implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer groupid;
	private String coreorg;
	private String coreorgname;
	private Integer year;
	private Integer month;
	private String loantotalmoney;
	private Integer loansumperson;
	private Integer loansumoverperson;
	private String loantotalovermoney;
	private Integer monthsumaddperson;
	private Integer monthsumfinishperson;
	private String parentorg;
	private String date;
	private Integer isdel;

	private String org;
	private String orgname;
	private List<ReportPersonalloanNearMonthDetail> detaillist = new ArrayList<ReportPersonalloanNearMonthDetail>(); 
	
	// Constructors

	/** default constructor */
	public ReportPersonalloanNearMonth() {
	}

	/** minimal constructor */
	public ReportPersonalloanNearMonth(Integer isdel) {
		this.isdel = isdel;
	}

	/** full constructor */
	public ReportPersonalloanNearMonth(Integer groupid,String coreorg, String coreorgname,
			Integer year, Integer month, String loantotalmoney,
			Integer loansumperson, Integer loansumoverperson,
			String loantotalovermoney, Integer monthsumaddperson,
			Integer monthsumfinishperson, String parentorg, String date,
			Integer isdel) {
		this.groupid = groupid;
		this.coreorg = coreorg;
		this.coreorgname = coreorgname;
		this.year = year;
		this.month = month;
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
		return groupid;
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

	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
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

	public List<ReportPersonalloanNearMonthDetail> getDetaillist() {
		return detaillist;
	}

	public void setDetaillist(List<ReportPersonalloanNearMonthDetail> detaillist) {
		this.detaillist = detaillist;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}



}