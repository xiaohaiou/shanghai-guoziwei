package com.softline.entity;

import java.util.ArrayList;
import java.util.List;

import com.softline.entity.ReportPersonlloaninfo;

/**
 * ReportKeywork entity. @author MyEclipse Persistence Tools
 */

public class ReportKeywork implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private Integer month;
	private Integer indextype;
	private String targetcount;
	private String coreOrg;
	private String coreOrgname;
	private Integer status;
	private Integer isdel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String reportPersonName;
	private String reportPersonId;
	private String reportDate;
	private String auditorPersonName;
	private String auditorPersonId;
	private String auditorDate;
	private String parentorg;
	
	//formula
	private String authOrg;

	// Constructors
	
	
	//formula
	private Integer getOperateType;
	private String statusName;
	private String indextypeName;

	/** default constructor */
	public ReportKeywork() {
	}

	/** minimal constructor */
	public ReportKeywork(Integer year, String coreOrg, Integer status,
			Integer isdel) {
		this.year = year;
		this.coreOrg = coreOrg;
		this.status = status;
		this.isdel = isdel;
	}

	/** full constructor */
	public ReportKeywork(Integer year, Integer month, Integer indextype,
			String targetcount, String coreOrg, String coreOrgname,
			Integer status, Integer isdel, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate, String parentorg) {
		this.year = year;
		this.month = month;
		this.indextype = indextype;
		this.targetcount = targetcount;
		this.coreOrg = coreOrg;
		this.coreOrgname = coreOrgname;
		this.status = status;
		this.isdel = isdel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.reportPersonName = reportPersonName;
		this.reportPersonId = reportPersonId;
		this.reportDate = reportDate;
		this.auditorPersonName = auditorPersonName;
		this.auditorPersonId = auditorPersonId;
		this.auditorDate = auditorDate;
		this.parentorg = parentorg;
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

	public Integer getIndextype() {
		return this.indextype;
	}

	public void setIndextype(Integer indextype) {
		this.indextype = indextype;
	}

	public String getTargetcount() {
		return this.targetcount;
	}

	public void setTargetcount(String targetcount) {
		this.targetcount = targetcount;
	}

	public String getCoreOrg() {
		return this.coreOrg;
	}

	public void setCoreOrg(String coreOrg) {
		this.coreOrg = coreOrg;
	}

	public String getCoreOrgname() {
		return this.coreOrgname;
	}

	public void setCoreOrgname(String coreOrgname) {
		this.coreOrgname = coreOrgname;
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

	public String getReportPersonName() {
		return this.reportPersonName;
	}

	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}

	public String getReportPersonId() {
		return this.reportPersonId;
	}

	public void setReportPersonId(String reportPersonId) {
		this.reportPersonId = reportPersonId;
	}

	public String getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
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


	public String getIndextypeName() {
		return indextypeName;
	}

	public void setIndextypeName(String indextypeName) {
		this.indextypeName = indextypeName;
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

	public String getAuthOrg() {
		return authOrg;
	}

	public void setAuthOrg(String authOrg) {
		this.authOrg = authOrg;
	}

}