package com.softline.entity.financing;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * ReportFinancingWeekThis entity. @author MyEclipse Persistence Tools
 */

public class ReportFinancingWeekThis implements java.io.Serializable {

	// Fields

	private Integer id;
	private String org;
	private String orgname;
	private String date;
	private Integer year;
	private Integer month;
	private Integer week;
	private String weekStratDate;
	private String weekEndDate;
	private String operateOrg;
	private String operateOrgname;
	private String alreadyAccountAmounts;
	private String accountDate;
	private Integer projectProgress;
	private Integer isdel;
	private Integer status;
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
	private List<ReportFinancingWeekThisList> weekThisList;
	private Set<ReportFinancingWeekThisList> weekThisSet= new TreeSet<ReportFinancingWeekThisList>();
	
	private Integer stratYear;
	private Integer endYear;
	private Integer stratWeek;
	private Integer endWeek;
	private String statusName;
	private String sumAlreadyAccountAmounts;

	/** default constructor */
	public ReportFinancingWeekThis() {
	}

	/** minimal constructor */
	public ReportFinancingWeekThis(String org, String orgname, Integer year,
			Integer month, Integer week, Integer isdel, Integer status) {
		this.org = org;
		this.orgname = orgname;
		this.year = year;
		this.month = month;
		this.week = week;
		this.isdel = isdel;
		this.status = status;
	}

	/** full constructor */
	public ReportFinancingWeekThis(String org, String orgname, Integer year,
			Integer month, Integer week, String weekStratDate,
			String weekEndDate, String operateOrg, String operateOrgname,
			String alreadyAccountAmounts, String accountDate,
			Integer projectProgress, Integer isdel, Integer status,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate, String parentorg) {
		this.org = org;
		this.orgname = orgname;
		this.year = year;
		this.month = month;
		this.week = week;
		this.weekStratDate = weekStratDate;
		this.weekEndDate = weekEndDate;
		this.operateOrg = operateOrg;
		this.operateOrgname = operateOrgname;
		this.alreadyAccountAmounts = alreadyAccountAmounts;
		this.accountDate = accountDate;
		this.projectProgress = projectProgress;
		this.isdel = isdel;
		this.status = status;
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

	public String getSumAlreadyAccountAmounts() {
		return sumAlreadyAccountAmounts;
	}

	public void setSumAlreadyAccountAmounts(String sumAlreadyAccountAmounts) {
		this.sumAlreadyAccountAmounts = sumAlreadyAccountAmounts;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getStratYear() {
		return stratYear;
	}

	public void setStratYear(Integer stratYear) {
		this.stratYear = stratYear;
	}

	public Integer getEndYear() {
		return endYear;
	}

	public void setEndYear(Integer endYear) {
		this.endYear = endYear;
	}

	public Integer getStratWeek() {
		return stratWeek;
	}

	public void setStratWeek(Integer stratWeek) {
		this.stratWeek = stratWeek;
	}

	public Integer getEndWeek() {
		return endWeek;
	}

	public void setEndWeek(Integer endWeek) {
		this.endWeek = endWeek;
	}

	public Set<ReportFinancingWeekThisList> getWeekThisSet() {
		return weekThisSet;
	}

	public void setWeekThisSet(Set<ReportFinancingWeekThisList> weekThisSet) {
		this.weekThisSet = weekThisSet;
	}

	public List<ReportFinancingWeekThisList> getWeekThisList() {
		return weekThisList;
	}

	public void setWeekThisList(List<ReportFinancingWeekThisList> weekThisList) {
		this.weekThisList = weekThisList;
	}

	public String getOrg() {
		return this.org;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public Integer getWeek() {
		return this.week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public String getWeekStratDate() {
		return this.weekStratDate;
	}

	public void setWeekStratDate(String weekStratDate) {
		this.weekStratDate = weekStratDate;
	}

	public String getWeekEndDate() {
		return this.weekEndDate;
	}

	public void setWeekEndDate(String weekEndDate) {
		this.weekEndDate = weekEndDate;
	}

	public String getOperateOrg() {
		return this.operateOrg;
	}

	public void setOperateOrg(String operateOrg) {
		this.operateOrg = operateOrg;
	}

	public String getOperateOrgname() {
		return this.operateOrgname;
	}

	public void setOperateOrgname(String operateOrgname) {
		this.operateOrgname = operateOrgname;
	}

	public String getAlreadyAccountAmounts() {
		return this.alreadyAccountAmounts;
	}

	public void setAlreadyAccountAmounts(String alreadyAccountAmounts) {
		this.alreadyAccountAmounts = alreadyAccountAmounts;
	}

	public String getAccountDate() {
		return this.accountDate;
	}

	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}

	public Integer getProjectProgress() {
		return this.projectProgress;
	}

	public void setProjectProgress(Integer projectProgress) {
		this.projectProgress = projectProgress;
	}

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

}