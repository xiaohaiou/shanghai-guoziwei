package com.softline.entity.taxTask;

import java.util.HashSet;
import java.util.Set;

/**
 * DataTaxTask entity. @author MyEclipse Persistence Tools
 */

public class DataTaxTask implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private String org;
	private String orgName;
	private String taxTask;
	private String taxPlan;
	private String favouredPolicy;
	private String taxReturn;
	private String inTaxReturn;
	private Integer status;
	private Integer isdel;
	private String reportPersonId;
	private String reportPersonName;
	private String reportDate;
	private String auditorPersonId;
	private String auditorPersonName;
	private String auditorDate;
	private String createPersonId;
	private String createPersonName;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String parentorg;
	private Set<DataTaxTaskDetailTaxPlan> dataTaxTaskDetailTaxPlans = new HashSet<DataTaxTaskDetailTaxPlan>();
	private Set<DataTaxTaskDetailTaxReturn> dataTaxTaskDetailTaxReturns = new HashSet<DataTaxTaskDetailTaxReturn>();
	private Set<DataTaxTaskDetailFavouredPolicy> dataTaxTaskDetailFavouredPolicies = new HashSet<DataTaxTaskDetailFavouredPolicy>();
	private Set<DataTaxTaskDetailInTaxReturn> dataTaxTaskDetailInTaxReturns = new HashSet<DataTaxTaskDetailInTaxReturn>();

	// formalu
	private String statusName;
	
	private String authOrg;

	// Constructors

	/** default constructor */
	public DataTaxTask() {
	}

	/** minimal constructor */
	public DataTaxTask(Integer status, Integer isdel) {
		this.status = status;
		this.isdel = isdel;
	}

	/** full constructor */
	public DataTaxTask(
			Integer year,
			String org,
			String orgName,
			String taxTask,
			String taxPlan,
			String favouredPolicy,
			String taxReturn,
			String inTaxReturn,
			Integer status,
			Integer isdel,
			String reportPersonId,
			String reportPersonName,
			String reportDate,
			String auditorPersonId,
			String auditorPersonName,
			String auditorDate,
			String createPersonId,
			String createPersonName,
			String createDate,
			String lastModifyPersonId,
			String lastModifyPersonName,
			String lastModifyDate,
			String parentorg,
			Set<DataTaxTaskDetailTaxPlan> dataTaxTaskDetailTaxPlans,
			Set<DataTaxTaskDetailTaxReturn> dataTaxTaskDetailTaxReturns,
			Set<DataTaxTaskDetailFavouredPolicy> dataTaxTaskDetailFavouredPolicies,
			Set<DataTaxTaskDetailInTaxReturn> dataTaxTaskDetailInTaxReturns) {
		this.year = year;
		this.org = org;
		this.orgName = orgName;
		this.taxTask = taxTask;
		this.taxPlan = taxPlan;
		this.favouredPolicy = favouredPolicy;
		this.taxReturn = taxReturn;
		this.inTaxReturn = inTaxReturn;
		this.status = status;
		this.isdel = isdel;
		this.reportPersonId = reportPersonId;
		this.reportPersonName = reportPersonName;
		this.reportDate = reportDate;
		this.auditorPersonId = auditorPersonId;
		this.auditorPersonName = auditorPersonName;
		this.auditorDate = auditorDate;
		this.createPersonId = createPersonId;
		this.createPersonName = createPersonName;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.parentorg = parentorg;
		this.dataTaxTaskDetailTaxPlans = dataTaxTaskDetailTaxPlans;
		this.dataTaxTaskDetailTaxReturns = dataTaxTaskDetailTaxReturns;
		this.dataTaxTaskDetailFavouredPolicies = dataTaxTaskDetailFavouredPolicies;
		this.dataTaxTaskDetailInTaxReturns = dataTaxTaskDetailInTaxReturns;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getTaxTask() {
		return this.taxTask;
	}

	public void setTaxTask(String taxTask) {
		this.taxTask = taxTask;
	}

	public String getTaxPlan() {
		return this.taxPlan;
	}

	public void setTaxPlan(String taxPlan) {
		this.taxPlan = taxPlan;
	}

	public String getFavouredPolicy() {
		return this.favouredPolicy;
	}

	public void setFavouredPolicy(String favouredPolicy) {
		this.favouredPolicy = favouredPolicy;
	}

	public String getTaxReturn() {
		return this.taxReturn;
	}

	public void setTaxReturn(String taxReturn) {
		this.taxReturn = taxReturn;
	}

	public String getInTaxReturn() {
		return this.inTaxReturn;
	}

	public void setInTaxReturn(String inTaxReturn) {
		this.inTaxReturn = inTaxReturn;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public String getReportPersonId() {
		return this.reportPersonId;
	}

	public void setReportPersonId(String reportPersonId) {
		this.reportPersonId = reportPersonId;
	}

	public String getReportPersonName() {
		return this.reportPersonName;
	}

	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}

	public String getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getAuditorPersonId() {
		return this.auditorPersonId;
	}

	public void setAuditorPersonId(String auditorPersonId) {
		this.auditorPersonId = auditorPersonId;
	}

	public String getAuditorPersonName() {
		return this.auditorPersonName;
	}

	public void setAuditorPersonName(String auditorPersonName) {
		this.auditorPersonName = auditorPersonName;
	}

	public String getAuditorDate() {
		return this.auditorDate;
	}

	public void setAuditorDate(String auditorDate) {
		this.auditorDate = auditorDate;
	}

	public String getCreatePersonId() {
		return this.createPersonId;
	}

	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}

	public String getCreatePersonName() {
		return this.createPersonName;
	}

	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
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

	public String getParentorg() {
		return this.parentorg;
	}

	public void setParentorg(String parentorg) {
		this.parentorg = parentorg;
	}

	public Set<DataTaxTaskDetailTaxPlan> getDataTaxTaskDetailTaxPlans() {
		return this.dataTaxTaskDetailTaxPlans;
	}

	public void setDataTaxTaskDetailTaxPlans(
			Set<DataTaxTaskDetailTaxPlan> dataTaxTaskDetailTaxPlans) {
		this.dataTaxTaskDetailTaxPlans = dataTaxTaskDetailTaxPlans;
	}

	public Set<DataTaxTaskDetailTaxReturn> getDataTaxTaskDetailTaxReturns() {
		return this.dataTaxTaskDetailTaxReturns;
	}

	public void setDataTaxTaskDetailTaxReturns(
			Set<DataTaxTaskDetailTaxReturn> dataTaxTaskDetailTaxReturns) {
		this.dataTaxTaskDetailTaxReturns = dataTaxTaskDetailTaxReturns;
	}

	public Set<DataTaxTaskDetailFavouredPolicy> getDataTaxTaskDetailFavouredPolicies() {
		return dataTaxTaskDetailFavouredPolicies;
	}
	
	public void setDataTaxTaskDetailFavouredPolicies(
			Set<DataTaxTaskDetailFavouredPolicy> dataTaxTaskDetailFavouredPolicies) {
		this.dataTaxTaskDetailFavouredPolicies = dataTaxTaskDetailFavouredPolicies;
	}

	public Set<DataTaxTaskDetailInTaxReturn> getDataTaxTaskDetailInTaxReturns() {
		return this.dataTaxTaskDetailInTaxReturns;
	}

	public void setDataTaxTaskDetailInTaxReturns(
			Set<DataTaxTaskDetailInTaxReturn> dataTaxTaskDetailInTaxReturns) {
		this.dataTaxTaskDetailInTaxReturns = dataTaxTaskDetailInTaxReturns;
	}

	public String getAuthOrg() {
		return authOrg;
	}

	public void setAuthOrg(String authOrg) {
		this.authOrg = authOrg;
	}
	
	

}