package com.softline.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * ReportPersonalloanNew entity. @author MyEclipse Persistence Tools
 */

public class ReportPersonalloanNew implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private Integer week;
	private String org;
	private String orgname;
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
	
	private List<ReportPersonlloaninfoNew> infolist = new ArrayList<ReportPersonlloaninfoNew>(); 
	private Integer getOperateType;
	private String statusName;
	private String coreorg;
	private String coreorgname;
	
	//formula
	private String authOrg;  //数据权限字段，子查询方式

	// Constructors

	/** default constructor */
	public ReportPersonalloanNew() {
	}

	/** minimal constructor */
	public ReportPersonalloanNew(Integer year, Integer status, Integer isdel) {
		this.year = year;
		this.status = status;
		this.isdel = isdel;
	}

	/** full constructor */
	public ReportPersonalloanNew(Integer year, Integer week, String org,
			String orgname, Integer status, Integer isdel,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String recheckPersonName,
			String recheckPersonId, String recheckDate,
			String auditorPersonName, String auditorPersonId,
			String auditorDate, String parentorg, String date) {
		this.year = year;
		this.week = week;
		this.org = org;
		this.orgname = orgname;
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
	}

	// Propestrty accessors

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

	public Integer getWeek() {
		return this.week;
	}

	public void setWeek(Integer week) {
		this.week = week;
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

	public List<ReportPersonlloaninfoNew> getInfolist() {
		return infolist;
	}

	public void setInfolist(List<ReportPersonlloaninfoNew> b) {
		this.infolist = b;
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

	public String getAuthOrg() {
		return authOrg;
	}

	public void setAuthOrg(String authOrg) {
		this.authOrg = authOrg;
	}

	

}