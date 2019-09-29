package com.softline.entity.taxTask;

import java.util.HashSet;
import java.util.Set;

/**
 * DataTaxPay entity. @author MyEclipse Persistence Tools
 */

public class DataTaxPay implements java.io.Serializable {

	// Fields

	private Integer id;
	private String year;
	private String month;
	private String org;
	private String orgName;
	private String nowPay;
	private String prevPay;
	private String moM;
	private String accNowPay;
	private String accPrevPay;
	private String accMoM;
	private String hnTax;
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
	private Set<DataTaxPayDetailNow> dataTaxPayDetailNows = new HashSet<DataTaxPayDetailNow>();
	private Set<DataTaxPayDetailPrev> dataTaxPayDetailPrevs = new HashSet<DataTaxPayDetailPrev>();
	private Set<DataTaxPayDetailAccPrev> dataTaxPayDetailAccPrevs = new HashSet<DataTaxPayDetailAccPrev>();
	private Set<DataTaxPayDetailAccNow> dataTaxPayDetailAccNows = new HashSet<DataTaxPayDetailAccNow>();
	
	private String sumNowpay;
	private String sumPrepay;
	private String starttime;
	private String endtime;

	// formalu
	private String statusName;
	private String authOrg;
	
	// Constructors

	/** default constructor */
	public DataTaxPay() {
	}

	/** minimal constructor */
	public DataTaxPay(Integer status, Integer isdel) {
		this.status = status;
		this.isdel = isdel;
	}

	/** full constructor */
	public DataTaxPay(String year, String month, String org, String orgName,
			String nowPay, String prevPay, String moM, String accNowPay,
			String accPrevPay, String accMoM, String hnTax, String remark,
			Integer status, Integer isdel, String reportPersonId,
			String reportPersonName, String reportDate, String auditorPersonId,
			String auditorPersonName, String auditorDate,
			String createPersonId, String createPersonName, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String parentorg, Set<DataTaxPayDetailNow> dataTaxPayDetailNows,
			Set<DataTaxPayDetailPrev> dataTaxPayDetailPrevs, Set<DataTaxPayDetailAccPrev> dataTaxPayDetailAccPrevs,
			Set<DataTaxPayDetailAccNow> dataTaxPayDetailAccNows,String sumNowpay,String sumPrepay,
			String starttime,String endtime) {
		this.year = year;
		this.month = month;
		this.org = org;
		this.orgName = orgName;
		this.nowPay = nowPay;
		this.prevPay = prevPay;
		this.moM = moM;
		this.accNowPay = accNowPay;
		this.accPrevPay = accPrevPay;
		this.accMoM = accMoM;
		this.hnTax = hnTax;
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
		this.dataTaxPayDetailNows = dataTaxPayDetailNows;
		this.dataTaxPayDetailPrevs = dataTaxPayDetailPrevs;
		this.dataTaxPayDetailAccPrevs = dataTaxPayDetailAccPrevs;
		this.dataTaxPayDetailAccNows = dataTaxPayDetailAccNows;
		this.sumNowpay = sumNowpay;
		this.sumPrepay = sumPrepay;
		this.starttime= starttime;
		this.endtime = endtime;
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

	public String getNowPay() {
		return this.nowPay;
	}

	public void setNowPay(String nowPay) {
		this.nowPay = nowPay;
	}

	public String getPrevPay() {
		return this.prevPay;
	}

	public void setPrevPay(String prevPay) {
		this.prevPay = prevPay;
	}

	public String getMoM() {
		return this.moM;
	}

	public void setMoM(String moM) {
		this.moM = moM;
	}

	public String getAccNowPay() {
		return this.accNowPay;
	}

	public void setAccNowPay(String accNowPay) {
		this.accNowPay = accNowPay;
	}

	public String getAccPrevPay() {
		return this.accPrevPay;
	}

	public void setAccPrevPay(String accPrevPay) {
		this.accPrevPay = accPrevPay;
	}

	public String getAccMoM() {
		return this.accMoM;
	}

	public void setAccMoM(String accMoM) {
		this.accMoM = accMoM;
	}

	public String getHnTax() {
		return this.hnTax;
	}

	public void setHnTax(String hnTax) {
		this.hnTax = hnTax;
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

	public Set<DataTaxPayDetailNow> getDataTaxPayDetailNows() {
		return this.dataTaxPayDetailNows;
	}

	public void setDataTaxPayDetailNows(Set<DataTaxPayDetailNow> dataTaxPayDetailNows) {
		this.dataTaxPayDetailNows = dataTaxPayDetailNows;
	}

	public Set<DataTaxPayDetailPrev> getDataTaxPayDetailPrevs() {
		return this.dataTaxPayDetailPrevs;
	}

	public void setDataTaxPayDetailPrevs(Set<DataTaxPayDetailPrev> dataTaxPayDetailPrevs) {
		this.dataTaxPayDetailPrevs = dataTaxPayDetailPrevs;
	}

	public Set<DataTaxPayDetailAccPrev> getDataTaxPayDetailAccPrevs() {
		return this.dataTaxPayDetailAccPrevs;
	}

	public void setDataTaxPayDetailAccPrevs(Set<DataTaxPayDetailAccPrev> dataTaxPayDetailAccPrevs) {
		this.dataTaxPayDetailAccPrevs = dataTaxPayDetailAccPrevs;
	}

	public Set<DataTaxPayDetailAccNow> getDataTaxPayDetailAccNows() {
		return this.dataTaxPayDetailAccNows;
	}

	public void setDataTaxPayDetailAccNows(Set<DataTaxPayDetailAccNow> dataTaxPayDetailAccNows) {
		this.dataTaxPayDetailAccNows = dataTaxPayDetailAccNows;
	}

	public String getSumNowpay() {
		return sumNowpay;
	}

	public void setSumNowpay(String sumNowpay) {
		this.sumNowpay = sumNowpay;
	}

	public String getSumPrepay() {
		return sumPrepay;
	}

	public void setSumPrepay(String sumPrepay) {
		this.sumPrepay = sumPrepay;
	}

	public String getAuthOrg() {
		return authOrg;
	}

	public void setAuthOrg(String authOrg) {
		this.authOrg = authOrg;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	
}