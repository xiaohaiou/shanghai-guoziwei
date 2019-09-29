package com.softline.entity;

/**
 * DataTaxSaveDetail entity. @author MyEclipse Persistence Tools
 */

public class DataTaxSaveDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer pid;
	private DataTaxSave dataTaxSave;
	private String year;
	private String month;
	private String org;
	private String orgName;
	private String inTaxReturn;
	private String accInTaxReturn;
	private String taxReturn;
	private String accTaxReturn;
	private String taxPlan;
	private String accTaxPlan;
	private String favouredPolicy;
	private String accFavouredPolicy;
	private String taxSave;
	private String accTaxSave;
	private Integer isdel;
	private String parentorg;

	// Constructors

	/** default constructor */
	public DataTaxSaveDetail() {
	}

	/** minimal constructor */
	public DataTaxSaveDetail(Integer isdel) {
		this.isdel = isdel;
	}

	/** full constructor */
	public DataTaxSaveDetail(DataTaxSave dataTaxSave, String year,Integer pid,
			String month, String org, String orgName, String inTaxReturn,
			String accInTaxReturn, String taxReturn, String accTaxReturn,
			String taxPlan, String accTaxPlan, String favouredPolicy,
			String accFavouredPolicy, String taxSave, String accTaxSave,
			Integer isdel, String parentorg) {
		this.dataTaxSave = dataTaxSave;
		this.pid = pid;
		this.year = year;
		this.month = month;
		this.org = org;
		this.orgName = orgName;
		this.inTaxReturn = inTaxReturn;
		this.accInTaxReturn = accInTaxReturn;
		this.taxReturn = taxReturn;
		this.accTaxReturn = accTaxReturn;
		this.taxPlan = taxPlan;
		this.accTaxPlan = accTaxPlan;
		this.favouredPolicy = favouredPolicy;
		this.accFavouredPolicy = accFavouredPolicy;
		this.taxSave = taxSave;
		this.accTaxSave = accTaxSave;
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

	public DataTaxSave getDataTaxSave() {
		return this.dataTaxSave;
	}

	public void setDataTaxSave(DataTaxSave dataTaxSave) {
		this.dataTaxSave = dataTaxSave;
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

	public String getInTaxReturn() {
		return this.inTaxReturn;
	}

	public void setInTaxReturn(String inTaxReturn) {
		this.inTaxReturn = inTaxReturn;
	}

	public String getAccInTaxReturn() {
		return this.accInTaxReturn;
	}

	public void setAccInTaxReturn(String accInTaxReturn) {
		this.accInTaxReturn = accInTaxReturn;
	}

	public String getTaxReturn() {
		return this.taxReturn;
	}

	public void setTaxReturn(String taxReturn) {
		this.taxReturn = taxReturn;
	}

	public String getAccTaxReturn() {
		return this.accTaxReturn;
	}

	public void setAccTaxReturn(String accTaxReturn) {
		this.accTaxReturn = accTaxReturn;
	}

	public String getTaxPlan() {
		return this.taxPlan;
	}

	public void setTaxPlan(String taxPlan) {
		this.taxPlan = taxPlan;
	}

	public String getAccTaxPlan() {
		return this.accTaxPlan;
	}

	public void setAccTaxPlan(String accTaxPlan) {
		this.accTaxPlan = accTaxPlan;
	}

	public String getFavouredPolicy() {
		return this.favouredPolicy;
	}

	public void setFavouredPolicy(String favouredPolicy) {
		this.favouredPolicy = favouredPolicy;
	}

	public String getAccFavouredPolicy() {
		return this.accFavouredPolicy;
	}

	public void setAccFavouredPolicy(String accFavouredPolicy) {
		this.accFavouredPolicy = accFavouredPolicy;
	}

	public String getTaxSave() {
		return this.taxSave;
	}

	public void setTaxSave(String taxSave) {
		this.taxSave = taxSave;
	}

	public String getAccTaxSave() {
		return this.accTaxSave;
	}

	public void setAccTaxSave(String accTaxSave) {
		this.accTaxSave = accTaxSave;
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