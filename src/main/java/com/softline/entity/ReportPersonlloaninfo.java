package com.softline.entity;

/**
 * ReportPersonlloaninfo entity. @author MyEclipse Persistence Tools
 */

public class ReportPersonlloaninfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer groupid;
	private Integer year;
	private Integer month;
	private String coreorg;
	private String coreorgname;
	private String org;
	private String orgname;
	private String fillcompanyname;
	private String responsibleperson;
	private String useway;
	private String loantime;
	private String loanmoney;
	private Integer loanmonth;
	private String remark;
	private String checktxt;
	private String loannum;
	private Integer isdel;
	private String parentorg;
	private Integer loanType1;
	private Integer loanType2;
	private String loanType1Name;
	private String loanType2Name;

	// Constructors
	private String date;
	private String loanovermoney;
	
	//用来差异分析的借款金额
	private String loanmoneyforcompare;

	/** default constructor */
	public ReportPersonlloaninfo() {
	}

	/** minimal constructor */
	public ReportPersonlloaninfo(Integer isdel) {
		this.isdel = isdel;
	}

	/** full constructor */
	public ReportPersonlloaninfo(Integer groupid,Integer year, Integer month,String coreorg,
			String coreorgname, String org, String orgname,String fillcompanyname,
			String responsibleperson, String useway, String loantime,
			String loanmoney, Integer loanmonth, String remark, String checktxt,
			String loannum, Integer isdel, String parentorg) {
		this.groupid = groupid;
		this.year = year;
		this.month = month;
		this.coreorg = coreorg;
		this.coreorgname = coreorgname;
		this.org = org;
		this.orgname = orgname;
		this.fillcompanyname = fillcompanyname;
		this.responsibleperson = responsibleperson;
		this.useway = useway;
		this.loantime = loantime;
		this.loanmoney = loanmoney;
		this.loanmonth = loanmonth;
		this.remark = remark;
		this.checktxt = checktxt;
		this.loannum = loannum;
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
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getCoreorg() {
		return coreorg;
	}

	public void setCoreorg(String coreorg) {
		this.coreorg = coreorg;
	}

	public String getCoreorgname() {
		return coreorgname;
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

	
	public String getFillcompanyname() {
		return fillcompanyname;
	}

	public void setFillcompanyname(String fillcompanyname) {
		this.fillcompanyname = fillcompanyname;
	}

	public String getResponsibleperson() {
		return this.responsibleperson;
	}

	public void setResponsibleperson(String responsibleperson) {
		this.responsibleperson = responsibleperson;
	}

	public String getUseway() {
		return this.useway;
	}

	public void setUseway(String useway) {
		this.useway = useway;
	}

	public String getLoantime() {
		return this.loantime;
	}

	public void setLoantime(String loantime) {
		this.loantime = loantime;
	}

	public String getLoanmoney() {
		return this.loanmoney;
	}

	public void setLoanmoney(String loanmoney) {
		this.loanmoney = loanmoney;
	}

	public Integer getLoanmonth() {
		return this.loanmonth;
	}

	public void setLoanmonth(Integer loanmonth) {
		this.loanmonth = loanmonth;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getChecktxt() {
		return checktxt;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLoanovermoney() {
		return loanovermoney;
	}

	public void setLoanovermoney(String loanovermoney) {
		this.loanovermoney = loanovermoney;
	}

	public String getParentorg() {
		return parentorg;
	}

	public void setParentorg(String parentorg) {
		this.parentorg = parentorg;
	}

	public String getLoanmoneyforcompare() {
		return loanmoneyforcompare;
	}

	public void setLoanmoneyforcompare(String loanmoneyforcompare) {
		this.loanmoneyforcompare = loanmoneyforcompare;
	}

	public Integer getLoanType1() {
		return loanType1;
	}

	public void setLoanType1(Integer loanType1) {
		this.loanType1 = loanType1;
	}

	public Integer getLoanType2() {
		return loanType2;
	}

	public void setLoanType2(Integer loanType2) {
		this.loanType2 = loanType2;
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

}