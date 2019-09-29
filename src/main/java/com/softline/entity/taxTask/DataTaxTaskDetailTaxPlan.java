package com.softline.entity.taxTask;

/**
 * DataTaxTaskDetailTaxPlan entity. @author MyEclipse Persistence Tools
 */

public class DataTaxTaskDetailTaxPlan implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer pid;
	private DataTaxTask dataTaxTask;
	private Integer year;
	private String org;
	private String orgName;
	private String month1;
	private String month2;
	private String month3;
	private String month4;
	private String month5;
	private String month6;
	private String month7;
	private String month8;
	private String month9;
	private String month10;
	private String month11;
	private String month12;
	private String sum;
	private Integer isdel;
	private String parentorg;

	// Constructors

	/** default constructor */
	public DataTaxTaskDetailTaxPlan() {
	}

	/** minimal constructor */
	public DataTaxTaskDetailTaxPlan(Integer isdel) {
		this.isdel = isdel;
	}

	/** full constructor */
	public DataTaxTaskDetailTaxPlan(Integer pid,DataTaxTask dataTaxTask, Integer year,
			String org, String orgName, String month1, String month2,
			String month3, String month4, String month5, String month6,
			String month7, String month8, String month9, String month10,
			String month11, String month12, String sum, Integer isdel,
			String parentorg) {
		this.pid =pid;
		this.dataTaxTask = dataTaxTask;
		this.year = year;
		this.org = org;
		this.orgName = orgName;
		this.month1 = month1;
		this.month2 = month2;
		this.month3 = month3;
		this.month4 = month4;
		this.month5 = month5;
		this.month6 = month6;
		this.month7 = month7;
		this.month8 = month8;
		this.month9 = month9;
		this.month10 = month10;
		this.month11 = month11;
		this.month12 = month12;
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

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public DataTaxTask getDataTaxTask() {
		return this.dataTaxTask;
	}

	public void setDataTaxTask(DataTaxTask dataTaxTask) {
		this.dataTaxTask = dataTaxTask;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
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

	public String getMonth1() {
		return this.month1;
	}

	public void setMonth1(String month1) {
		this.month1 = month1;
	}

	public String getMonth2() {
		return this.month2;
	}

	public void setMonth2(String month2) {
		this.month2 = month2;
	}

	public String getMonth3() {
		return this.month3;
	}

	public void setMonth3(String month3) {
		this.month3 = month3;
	}

	public String getMonth4() {
		return this.month4;
	}

	public void setMonth4(String month4) {
		this.month4 = month4;
	}

	public String getMonth5() {
		return this.month5;
	}

	public void setMonth5(String month5) {
		this.month5 = month5;
	}

	public String getMonth6() {
		return this.month6;
	}

	public void setMonth6(String month6) {
		this.month6 = month6;
	}

	public String getMonth7() {
		return this.month7;
	}

	public void setMonth7(String month7) {
		this.month7 = month7;
	}

	public String getMonth8() {
		return this.month8;
	}

	public void setMonth8(String month8) {
		this.month8 = month8;
	}

	public String getMonth9() {
		return this.month9;
	}

	public void setMonth9(String month9) {
		this.month9 = month9;
	}

	public String getMonth10() {
		return this.month10;
	}

	public void setMonth10(String month10) {
		this.month10 = month10;
	}

	public String getMonth11() {
		return this.month11;
	}

	public void setMonth11(String month11) {
		this.month11 = month11;
	}

	public String getMonth12() {
		return this.month12;
	}

	public void setMonth12(String month12) {
		this.month12 = month12;
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