package com.softline.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * ReportReceivabledebt entity. @author MyEclipse Persistence Tools
 */

public class ReportReceivabledebt implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private Integer month;
	private String coreorg;
	private String coreorgname;
	private String debtorg;
	private String debtorgname;
	private String beginningbalance;
	private String endingbalance;
	private Integer status;
	private Integer isdel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String recheckPersonName;
	private String recheckPersonId;
	private String recheckDate;
	private String auditorPersonName;
	private String auditorPersonId;
	private String auditorDate;
	private String parentorg;
	private String date;
	private Integer type;
	
	private List<ReportReceivabledebtinfo> infolist = new ArrayList<ReportReceivabledebtinfo>(); 
	private Integer getOperateType;
	private String statusName;
	
	//formula
	private String authOrg; // 数据权限字段
	
	
	// Constructors

	/** default constructor */
	public ReportReceivabledebt() {
	}

	/** minimal constructor */
	public ReportReceivabledebt(Integer year, Integer status, Integer isdel) {
		this.year = year;
		this.status = status;
		this.isdel = isdel;
	}

	/** full constructor */
	public ReportReceivabledebt(Integer year, Integer month, String coreorg,
			String coreorgname, String debtorg, String debtorgname,
			String beginningbalance, String endingbalance, Integer status,
			Integer isdel, String createPersonName, String createPersonId,
			String createDate, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate,
			String recheckPersonName, String recheckPersonId,
			String recheckDate, String auditorPersonName,
			String auditorPersonId, String auditorDate, String parentorg,
			String date, Integer type) {
		this.year = year;
		this.month = month;
		this.coreorg = coreorg;
		this.coreorgname = coreorgname;
		this.debtorg = debtorg;
		this.debtorgname = debtorgname;
		this.beginningbalance = beginningbalance;
		this.endingbalance = endingbalance;
		this.status = status;
		this.isdel = isdel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.recheckPersonName = recheckPersonName;
		this.recheckPersonId = recheckPersonId;
		this.recheckDate = recheckDate;
		this.auditorPersonName = auditorPersonName;
		this.auditorPersonId = auditorPersonId;
		this.auditorDate = auditorDate;
		this.parentorg = parentorg;
		this.date = date;
		this.type = type;
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

	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
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

	public String getDebtorg() {
		return this.debtorg;
	}

	public void setDebtorg(String debtorg) {
		this.debtorg = debtorg;
	}

	public String getDebtorgname() {
		return this.debtorgname;
	}

	public void setDebtorgname(String debtorgname) {
		this.debtorgname = debtorgname;
	}

	public String getBeginningbalance() {
		return this.beginningbalance;
	}

	public void setBeginningbalance(String beginningbalance) {
		this.beginningbalance = beginningbalance;
	}

	public String getEndingbalance() {
		return this.endingbalance;
	}

	public void setEndingbalance(String endingbalance) {
		this.endingbalance = endingbalance;
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

	public String getCreatePersonName() {
		return this.createPersonName;
	}

	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}

	public String getCreatePersonId() {
		return this.createPersonId;
	}

	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
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

	public String getRecheckPersonName() {
		return this.recheckPersonName;
	}

	public void setRecheckPersonName(String recheckPersonName) {
		this.recheckPersonName = recheckPersonName;
	}

	public String getRecheckPersonId() {
		return this.recheckPersonId;
	}

	public void setRecheckPersonId(String recheckPersonId) {
		this.recheckPersonId = recheckPersonId;
	}

	public String getRecheckDate() {
		return this.recheckDate;
	}

	public void setRecheckDate(String recheckDate) {
		this.recheckDate = recheckDate;
	}

	public String getAuditorPersonName() {
		return this.auditorPersonName;
	}

	public void setAuditorPersonName(String auditorPersonName) {
		this.auditorPersonName = auditorPersonName;
	}

	public String getAuditorPersonId() {
		return this.auditorPersonId;
	}

	public void setAuditorPersonId(String auditorPersonId) {
		this.auditorPersonId = auditorPersonId;
	}

	public String getAuditorDate() {
		return this.auditorDate;
	}

	public void setAuditorDate(String auditorDate) {
		this.auditorDate = auditorDate;
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

	public List<ReportReceivabledebtinfo> getInfolist() {
		return infolist;
	}

	public void setInfolist(List<ReportReceivabledebtinfo> infolist) {
		this.infolist = infolist;
	}

	public Integer getGetOperateType() {
		return getOperateType;
	}

	public void setGetOperateType(Integer getOperateType) {
		this.getOperateType = getOperateType;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAuthOrg() {
		return authOrg;
	}

	public void setAuthOrg(String authOrg) {
		this.authOrg = authOrg;
	}

}