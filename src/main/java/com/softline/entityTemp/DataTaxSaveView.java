package com.softline.entityTemp;

import java.util.HashSet;
import java.util.Set;

import com.softline.entity.DataTaxSaveDetail;

/**
 * DataTaxSave entity. @author MyEclipse Persistence Tools
 */

public class DataTaxSaveView implements java.io.Serializable {

	// Fields

	private Integer id;
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
	private Double accTaxSave;
	private String remark;
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
	private Set<DataTaxSaveDetail> dataTaxSaveDetails = new HashSet<DataTaxSaveDetail>();

	// formalu
	private String statusName;
	private Double taxTask;

	// Constructors
	/** default constructor */
	public DataTaxSaveView() {
	}

	/** minimal constructor */
	public DataTaxSaveView(Integer status, Integer isdel) {
		this.status = status;
		this.isdel = isdel;
	}

	/** full constructor */
	public DataTaxSaveView(String year, String month, String org, String orgName,
			String inTaxReturn, String accInTaxReturn, String taxReturn,
			String accTaxReturn, String taxPlan, String accTaxPlan,
			String favouredPolicy, String accFavouredPolicy, String taxSave,
			Double accTaxSave, String remark, Integer status, Integer isdel,
			String reportPersonId, String reportPersonName, String reportDate,
			String auditorPersonId, String auditorPersonName,
			String auditorDate, String createPersonId, String createPersonName,
			String createDate, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate,
			String parentorg, Set<DataTaxSaveDetail> dataTaxSaveDetails) {
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
		this.remark = remark;
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
		this.dataTaxSaveDetails = dataTaxSaveDetails;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Double getAccTaxSave() {
		return this.accTaxSave;
	}

	public void setAccTaxSave(Double accTaxSave) {
		this.accTaxSave = accTaxSave;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Set<DataTaxSaveDetail> getDataTaxSaveDetails() {
		return this.dataTaxSaveDetails;
	}

	public void setDataTaxSaveDetails(Set<DataTaxSaveDetail> dataTaxSaveDetails) {
		this.dataTaxSaveDetails = dataTaxSaveDetails;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Double getTaxTask() {
		return taxTask;
	}

	public void setTaxTask(Double taxTask) {
		this.taxTask = taxTask;
	}
}