package com.softline.entity.financing;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * ReportFinancingWeekNext entity. @author MyEclipse Persistence Tools
 */

public class ReportFinancingWeekNext implements java.io.Serializable {

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
	private List<ReportFinancingWeekNextList> weekNextList;
	private Set<ReportFinancingWeekNextList> weekNextSet= new TreeSet<ReportFinancingWeekNextList>();
	
	private Integer stratYear;
	private Integer endYear;
	private Integer stratWeek;
	private Integer endWeek;
	private String statusName;
	private String sumAlreadyAccountAmounts;
	// Constructors

	/** default constructor */
	public ReportFinancingWeekNext() {
	}

	/** minimal constructor */
	public ReportFinancingWeekNext(String org, String orgname, Integer year,
			Integer month, Integer week, Integer status, Integer isdel) {
		this.org = org;
		this.orgname = orgname;
		this.year = year;
		this.month = month;
		this.week = week;
		this.status = status;
		this.isdel = isdel;
	}

	/** full constructor */
	public ReportFinancingWeekNext(String org, String orgname, Integer year,
			Integer month, Integer week, String weekStratDate,
			String weekEndDate, Integer status, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate, String parentorg,
			Integer isdel) {
		this.org = org;
		this.orgname = orgname;
		this.year = year;
		this.month = month;
		this.week = week;
		this.weekStratDate = weekStratDate;
		this.weekEndDate = weekEndDate;
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
		this.isdel = isdel;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getSumAlreadyAccountAmounts() {
		return sumAlreadyAccountAmounts;
	}

	public void setSumAlreadyAccountAmounts(String sumAlreadyAccountAmounts) {
		this.sumAlreadyAccountAmounts = sumAlreadyAccountAmounts;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOperateOrg() {
		return operateOrg;
	}

	public void setOperateOrg(String operateOrg) {
		this.operateOrg = operateOrg;
	}

	public String getOperateOrgname() {
		return operateOrgname;
	}

	public void setOperateOrgname(String operateOrgname) {
		this.operateOrgname = operateOrgname;
	}

	public String getAlreadyAccountAmounts() {
		return alreadyAccountAmounts;
	}

	public void setAlreadyAccountAmounts(String alreadyAccountAmounts) {
		this.alreadyAccountAmounts = alreadyAccountAmounts;
	}

	public String getAccountDate() {
		return accountDate;
	}

	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}

	public Integer getProjectProgress() {
		return projectProgress;
	}

	public void setProjectProgress(Integer projectProgress) {
		this.projectProgress = projectProgress;
	}

	public List<ReportFinancingWeekNextList> getWeekNextList() {
		return weekNextList;
	}

	public void setWeekNextList(List<ReportFinancingWeekNextList> weekNextList) {
		this.weekNextList = weekNextList;
	}

	public Set<ReportFinancingWeekNextList> getWeekNextSet() {
		return weekNextSet;
	}

	public void setWeekNextSet(Set<ReportFinancingWeekNextList> weekNextSet) {
		this.weekNextSet = weekNextSet;
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

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

}