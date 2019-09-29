package com.softline.entity.fundPosition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DataFundPosition entity. @author MyEclipse Persistence Tools
 */

public class DataFundPosition implements java.io.Serializable {

	// Fields

	private Integer id;
	private String date;
	private String startDate;
	private String endDate;
	private String org;
	private String orgname;
	private String dailyBalance;
	
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
	private Set<DataFundPositionOther> dataFundPositionOthers = new HashSet<DataFundPositionOther>();
	private List<DataFundPositionOther> dataFundPositionOthersTemp = new ArrayList<DataFundPositionOther>();
	private DataFundPositionRmb dataFundPositionRmbs = new DataFundPositionRmb();
	
	//formula
	private String authOrg; // 数据权限控制字段

	// Constructors

	/** default constructor */
	public DataFundPosition() {
	}

	/** minimal constructor */
	public DataFundPosition(String date, String org, String orgname,
			String dailyBalance, Integer status, Integer isdel) {
		this.date = date;
		this.org = org;
		this.orgname = orgname;
		this.dailyBalance = dailyBalance;
		this.status = status;
		this.isdel = isdel;
	}

	/** full constructor */
	public DataFundPosition(String date, String org, String orgname,
			String dailyBalance, Integer status, Integer isdel,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate, String parentorg,
			Set<DataFundPositionOther> dataFundPositionOthers, DataFundPositionRmb dataFundPositionRmbs) {
		this.date = date;
		this.org = org;
		this.orgname = orgname;
		this.dailyBalance = dailyBalance;
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
		this.dataFundPositionOthers = dataFundPositionOthers;
		this.dataFundPositionRmbs = dataFundPositionRmbs;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getDailyBalance() {
		return this.dailyBalance;
	}

	public void setDailyBalance(String dailyBalance) {
		this.dailyBalance = dailyBalance;
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

	public Set<DataFundPositionOther> getDataFundPositionOthers() {
		return this.dataFundPositionOthers;
	}

	public void setDataFundPositionOthers(Set<DataFundPositionOther> dataFundPositionOthers) {
		this.dataFundPositionOthers = dataFundPositionOthers;
	}

	public DataFundPositionRmb getDataFundPositionRmbs() {
		return this.dataFundPositionRmbs;
	}

	public void setDataFundPositionRmbs(DataFundPositionRmb dataFundPositionRmbs) {
		this.dataFundPositionRmbs = dataFundPositionRmbs;
	}

	public List<DataFundPositionOther> getDataFundPositionOthersTemp() {
		return dataFundPositionOthersTemp;
	}

	public void setDataFundPositionOthersTemp(
			List<DataFundPositionOther> dataFundPositionOthersTemp) {
		this.dataFundPositionOthersTemp = dataFundPositionOthersTemp;
	}

	public String getAuthOrg() {
		return authOrg;
	}

	public void setAuthOrg(String authOrg) {
		this.authOrg = authOrg;
	}

}