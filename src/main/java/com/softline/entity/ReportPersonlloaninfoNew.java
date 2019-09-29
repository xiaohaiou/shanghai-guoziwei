package com.softline.entity;

import java.math.BigDecimal;

import com.softline.common.Common;

/**
 * ReportPersonlloaninfoNew entity. @author MyEclipse Persistence Tools
 */

public class ReportPersonlloaninfoNew implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer groupid;
	private Integer year;
	private Integer week;
	private String coreorg;
	private String coreorgname;
	private String org;
	private String orgname;
	private String responsibleperson;
	private Integer loanType1;
	private Integer loanType2;
	private String useway;
	private String loanmoney;
	private String loantime;
	private String loanmonth;
	private Integer isCuishou;
	private Integer isInLaw;
	private String weekBackMoney;
	private String totalBackMoney;
	private String yjhz;
	private String cszrr;
	private String zjms;
	private String remark;
	private String checktxt;
	private String loannum;
	private Integer isdel;
	private String parentorg;
	private String fillcompanyname;
	private String respersonAccount;
	private Integer status;//审核状态
	
	//formula
	private String loanType1Name;
	private String loanType2Name;
	private String authOrg;      // 数据权限字段
	
	//temp
	private String loanovermoney;

	// Constructors

	/** default constructor */
	public ReportPersonlloaninfoNew() {
	}

	/** minimal constructor */
	public ReportPersonlloaninfoNew(Integer year, Integer isdel) {
		this.year = year;
		this.isdel = isdel;
	}

	/** full constructor */
	public ReportPersonlloaninfoNew(Integer groupid, Integer year,
			Integer week, String coreorg, String coreorgname, String org,
			String orgname, String responsibleperson, Integer loanType1,
			Integer loanType2, String useway, String loanmoney,
			String loantime, String loanmonth, Integer isCuishou,
			Integer isInLaw, String weekBackMoney, String totalBackMoney,
			String yjhz, String cszrr, String zjms, String remark,
			String checktxt, String loannum, Integer isdel, String parentorg,
			String fillcompanyname) {
		this.groupid = groupid;
		this.year = year;
		this.week = week;
		this.coreorg = coreorg;
		this.coreorgname = coreorgname;
		this.org = org;
		this.orgname = orgname;
		this.responsibleperson = responsibleperson;
		this.loanType1 = loanType1;
		this.loanType2 = loanType2;
		this.useway = useway;
		this.loanmoney = loanmoney;
		this.loantime = loantime;
		this.loanmonth = loanmonth;
		this.isCuishou = isCuishou;
		this.isInLaw = isInLaw;
		this.weekBackMoney = weekBackMoney;
		this.totalBackMoney = totalBackMoney;
		this.yjhz = yjhz;
		this.cszrr = cszrr;
		this.zjms = zjms;
		this.remark = remark;
		this.checktxt = checktxt;
		this.loannum = loannum;
		this.isdel = isdel;
		this.parentorg = parentorg;
		this.fillcompanyname = fillcompanyname;
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

	public String getResponsibleperson() {
		return this.responsibleperson;
	}

	public void setResponsibleperson(String responsibleperson) {
		this.responsibleperson = responsibleperson;
	}

	public Integer getLoanType1() {
		return this.loanType1;
	}

	public void setLoanType1(Integer loanType1) {
		this.loanType1 = loanType1;
	}

	public Integer getLoanType2() {
		return this.loanType2;
	}

	public void setLoanType2(Integer loanType2) {
		this.loanType2 = loanType2;
	}

	public String getUseway() {
		return this.useway;
	}

	public void setUseway(String useway) {
		this.useway = useway;
	}

	public String getLoanmoney() {
		return this.loanmoney;
	}

	public void setLoanmoney(String loanmoney) {
		this.loanmoney = loanmoney;
	}

	public String getLoantime() {
		return this.loantime;
	}

	public void setLoantime(String loantime) {
		this.loantime = loantime;
	}

	public String getLoanmonth() {
		return this.loanmonth;
	}

	public void setLoanmonth(String loanmonth) {
		this.loanmonth = loanmonth;
	}

	public Integer getIsCuishou() {
		return this.isCuishou;
	}

	public void setIsCuishou(Integer isCuishou) {
		this.isCuishou = isCuishou;
	}

	public Integer getIsInLaw() {
		return this.isInLaw;
	}

	public void setIsInLaw(Integer isInLaw) {
		this.isInLaw = isInLaw;
	}

	public String getWeekBackMoney() {
		return this.weekBackMoney;
	}

	public void setWeekBackMoney(String weekBackMoney) {
		this.weekBackMoney = weekBackMoney;
	}

	public String getTotalBackMoney() {
		return this.totalBackMoney;
	}

	public void setTotalBackMoney(String totalBackMoney) {
		this.totalBackMoney = totalBackMoney;
	}

	public String getYjhz() {
		return this.yjhz;
	}

	public void setYjhz(String yjhz) {
		this.yjhz = yjhz;
	}

	public String getCszrr() {
		return this.cszrr;
	}

	public void setCszrr(String cszrr) {
		this.cszrr = cszrr;
	}

	public String getZjms() {
		return this.zjms;
	}

	public void setZjms(String zjms) {
		this.zjms = zjms;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getChecktxt() {
		return this.checktxt;
	}

	public void setChecktxt(String checktxt) {
		this.checktxt = checktxt;
	}

	public String getLoannum() {
		return this.loannum;
	}

	public void setLoannum(String loannum) {
		this.loannum = loannum;
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

	public String getFillcompanyname() {
		return this.fillcompanyname;
	}

	public void setFillcompanyname(String fillcompanyname) {
		this.fillcompanyname = fillcompanyname;
	}

	public String getLoanType1Name() {
		return loanType1Name;
	}

	public void setLoanType1Name(String loanType1Name) {
		this.loanType1Name = loanType1Name;
	}

	public String getLoanType2Name() {
		return loanType2Name;
	}

	public void setLoanType2Name(String loanType2Name) {
		this.loanType2Name = loanType2Name;
	}

	public String getRespersonAccount() {
		return respersonAccount;
	}

	public void setRespersonAccount(String respersonAccount) {
		this.respersonAccount = respersonAccount;
	}

	public String getLoanovermoney() {
		if(!Common.notEmpty(loanovermoney)){
			
			loanovermoney = (new BigDecimal(loanmoney).doubleValue() - new BigDecimal(totalBackMoney).doubleValue())+"";
		}
		return loanovermoney;
	}

	public void setLoanovermoney(String loanovermoney) {
		this.loanovermoney = loanovermoney;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAuthOrg() {
		return authOrg;
	}

	public void setAuthOrg(String authOrg) {
		this.authOrg = authOrg;
	}

}