package com.softline.entity;

/**
 * ReportReceivabledebtinfoOuter entity. @author MyEclipse Persistence Tools
 */

public class ReportReceivabledebtinfoOuter implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer groupid;
	private Integer year;
	private Integer week;
	private String coreorg;
	private String coreorgname;
	private String org;
	private String orgname;
	private String debtOrgname;
	private Integer debtType;
	private String jtsy;
	private String loanMoney;
	private String cqLoanMoney;
	private String loanTime;
	private Integer isOvertime;
	private String overtimeMonth;
	private Integer isCuishou;
	private Integer isInlaw;
	private String weekBackMoney;
	private String totalBackMoney;
	private String yjhz;
	private String cuishouPerson;
	private String jzDescription;
	private String remark;
	private Integer isdel;
	private String parentorg;
	private String noCuishoureason;
	private String hkjh;
	
	//formula
	private String debtTypeName;
	private Integer status;
	private String authOrg;// 数据权限控制字段
	

	// Constructors

	/** default constructor */
	public ReportReceivabledebtinfoOuter() {
	}

	/** minimal constructor */
	public ReportReceivabledebtinfoOuter(Integer year, Integer isdel) {
		this.year = year;
		this.isdel = isdel;
	}

	/** full constructor */
	public ReportReceivabledebtinfoOuter(Integer groupid, Integer year,
			Integer week, String coreorg, String coreorgname, String org,
			String orgname, String debtOrgname, Integer debtType, String jtsy,
			String loanMoney, String cqLoanMoney, String loanTime,
			Integer isOvertime, String overtimeMonth, Integer isCuishou,
			Integer isInlaw, String weekBackMoney, String totalBackMoney,
			String yjhz, String cuishouPerson, String jzDescription,
			String remark, Integer isdel, String parentorg) {
		this.groupid = groupid;
		this.year = year;
		this.week = week;
		this.coreorg = coreorg;
		this.coreorgname = coreorgname;
		this.org = org;
		this.orgname = orgname;
		this.debtOrgname = debtOrgname;
		this.debtType = debtType;
		this.jtsy = jtsy;
		this.loanMoney = loanMoney;
		this.cqLoanMoney = cqLoanMoney;
		this.loanTime = loanTime;
		this.isOvertime = isOvertime;
		this.overtimeMonth = overtimeMonth;
		this.isCuishou = isCuishou;
		this.isInlaw = isInlaw;
		this.weekBackMoney = weekBackMoney;
		this.totalBackMoney = totalBackMoney;
		this.yjhz = yjhz;
		this.cuishouPerson = cuishouPerson;
		this.jzDescription = jzDescription;
		this.remark = remark;
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

	public String getDebtOrgname() {
		return this.debtOrgname;
	}

	public void setDebtOrgname(String debtOrgname) {
		this.debtOrgname = debtOrgname;
	}

	public Integer getDebtType() {
		return this.debtType;
	}

	public void setDebtType(Integer debtType) {
		this.debtType = debtType;
	}

	public String getJtsy() {
		return this.jtsy;
	}

	public void setJtsy(String jtsy) {
		this.jtsy = jtsy;
	}

	public String getLoanMoney() {
		return this.loanMoney;
	}

	public void setLoanMoney(String loanMoney) {
		this.loanMoney = loanMoney;
	}

	public String getCqLoanMoney() {
		return this.cqLoanMoney;
	}

	public void setCqLoanMoney(String cqLoanMoney) {
		this.cqLoanMoney = cqLoanMoney;
	}

	public String getLoanTime() {
		return this.loanTime;
	}

	public void setLoanTime(String loanTime) {
		this.loanTime = loanTime;
	}

	public Integer getIsOvertime() {
		return this.isOvertime;
	}

	public void setIsOvertime(Integer isOvertime) {
		this.isOvertime = isOvertime;
	}

	public String getOvertimeMonth() {
		return this.overtimeMonth;
	}

	public void setOvertimeMonth(String overtimeMonth) {
		this.overtimeMonth = overtimeMonth;
	}

	public Integer getIsCuishou() {
		return this.isCuishou;
	}

	public void setIsCuishou(Integer isCuishou) {
		this.isCuishou = isCuishou;
	}

	public Integer getIsInlaw() {
		return this.isInlaw;
	}

	public void setIsInlaw(Integer isInlaw) {
		this.isInlaw = isInlaw;
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

	public String getCuishouPerson() {
		return this.cuishouPerson;
	}

	public void setCuishouPerson(String cuishouPerson) {
		this.cuishouPerson = cuishouPerson;
	}

	public String getJzDescription() {
		return this.jzDescription;
	}

	public void setJzDescription(String jzDescription) {
		this.jzDescription = jzDescription;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getNoCuishoureason() {
		return noCuishoureason;
	}

	public void setNoCuishoureason(String noCuishoureason) {
		this.noCuishoureason = noCuishoureason;
	}

	public String getHkjh() {
		return hkjh;
	}

	public void setHkjh(String hkjh) {
		this.hkjh = hkjh;
	}

	public String getDebtTypeName() {
		return debtTypeName;
	}

	public void setDebtTypeName(String debtTypeName) {
		this.debtTypeName = debtTypeName;
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