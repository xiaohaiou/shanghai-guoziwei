package com.softline.entity.moneyFlow;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;


/**
 * DataMoneyFlowWeek entity. @author MyEclipse Persistence Tools
 */

public class DataMoneyFlowWeek implements java.io.Serializable  {

	// Fields

	private Integer pid;
	private String week;
	private Date weekStart;
	private Date weekEnd;
	private String startDate;
	private String endDate;
	private String date;
	private String tempDate;
	private String orgname;
	private String org;
	private String parentCompany;
	private String parentOrganalId;
	private String operationalInflow;
	private String operationalOutflow;
	private String operationalNetFlow;
	private String capitalInflow;
	private String capitalOutflow;
	private String capitalNetFlow;
	private String investmentInflow;
	private String investmentOutflow;
	private String investmentNetFlow;
	private String cashInflow;
	private String cashOutflow;
	private String netCashFlow;
	private String initialAvailableCash;
	private String finalAvailableCash;
	private String remarks;
	private Integer status;
	private String examineTime;
	private Integer isdel;
	private String reportPersonName;
	private String reportPersonId;
	private String reportDate;
	private String auditorPersonName;
	private String auditorPersonId;
	private String auditorDate;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String parentorg;
	private DataMoneyFlowWeekCi DataMoneyFlowWeekCiTemp= new DataMoneyFlowWeekCi();
	private DataMoneyFlowWeekCo DataMoneyFlowWeekCoTemp = new DataMoneyFlowWeekCo();
	private DataMoneyFlowWeekIo DataMoneyFlowWeekIoTemp = new DataMoneyFlowWeekIo();
	private Set<DataMoneyFlowWeekCi> DataMoneyFlowWeekCi= new TreeSet<DataMoneyFlowWeekCi>();
	private Set<DataMoneyFlowWeekCo> DataMoneyFlowWeekCo= new TreeSet<DataMoneyFlowWeekCo>();
	private Set<DataMoneyFlowWeekIo> DataMoneyFlowWeekIo= new TreeSet<DataMoneyFlowWeekIo>();

	private String authOrg; //数据权限控制字段
	// Constructors

	/** default constructor */
	public DataMoneyFlowWeek() {
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public Date getWeekStart() {
		return weekStart;
	}

	public void setWeekStart(Date weekStart) {
		this.weekStart = weekStart;
	}

	public Date getWeekEnd() {
		return weekEnd;
	}

	public void setWeekEnd(Date weekEnd) {
		this.weekEnd = weekEnd;
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
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTempDate() {
		return tempDate;
	}

	public void setTempDate(String tempDate) {
		this.tempDate = tempDate;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getParentCompany() {
		return parentCompany;
	}

	public void setParentCompany(String parentCompany) {
		this.parentCompany = parentCompany;
	}

	public String getParentOrganalId() {
		return parentOrganalId;
	}

	public void setParentOrganalId(String parentOrganalId) {
		this.parentOrganalId = parentOrganalId;
	}

	public String getOperationalInflow() {
		return operationalInflow;
	}

	public void setOperationalInflow(String operationalInflow) {
		this.operationalInflow = operationalInflow;
	}

	public String getOperationalOutflow() {
		return operationalOutflow;
	}

	public void setOperationalOutflow(String operationalOutflow) {
		this.operationalOutflow = operationalOutflow;
	}

	public String getOperationalNetFlow() {
		return operationalNetFlow;
	}

	public void setOperationalNetFlow(String operationalNetFlow) {
		this.operationalNetFlow = operationalNetFlow;
	}

	public String getCapitalInflow() {
		return capitalInflow;
	}

	public void setCapitalInflow(String capitalInflow) {
		this.capitalInflow = capitalInflow;
	}

	public String getCapitalOutflow() {
		return capitalOutflow;
	}

	public void setCapitalOutflow(String capitalOutflow) {
		this.capitalOutflow = capitalOutflow;
	}

	public String getCapitalNetFlow() {
		return capitalNetFlow;
	}

	public void setCapitalNetFlow(String capitalNetFlow) {
		this.capitalNetFlow = capitalNetFlow;
	}

	public String getInvestmentInflow() {
		return investmentInflow;
	}

	public void setInvestmentInflow(String investmentInflow) {
		this.investmentInflow = investmentInflow;
	}

	public String getInvestmentOutflow() {
		return investmentOutflow;
	}

	public void setInvestmentOutflow(String investmentOutflow) {
		this.investmentOutflow = investmentOutflow;
	}

	public String getInvestmentNetFlow() {
		return investmentNetFlow;
	}

	public void setInvestmentNetFlow(String investmentNetFlow) {
		this.investmentNetFlow = investmentNetFlow;
	}

	public String getCashInflow() {
		return cashInflow;
	}

	public void setCashInflow(String cashInflow) {
		this.cashInflow = cashInflow;
	}

	public String getCashOutflow() {
		return cashOutflow;
	}

	public void setCashOutflow(String cashOutflow) {
		this.cashOutflow = cashOutflow;
	}

	public String getNetCashFlow() {
		return netCashFlow;
	}

	public void setNetCashFlow(String netCashFlow) {
		this.netCashFlow = netCashFlow;
	}

	public String getInitialAvailableCash() {
		return initialAvailableCash;
	}

	public void setInitialAvailableCash(String initialAvailableCash) {
		this.initialAvailableCash = initialAvailableCash;
	}

	public String getFinalAvailableCash() {
		return finalAvailableCash;
	}

	public void setFinalAvailableCash(String finalAvailableCash) {
		this.finalAvailableCash = finalAvailableCash;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getExamineTime() {
		return examineTime;
	}

	public void setExamineTime(String examineTime) {
		this.examineTime = examineTime;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public String getReportPersonName() {
		return reportPersonName;
	}

	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}

	public String getReportPersonId() {
		return reportPersonId;
	}

	public void setReportPersonId(String reportPersonId) {
		this.reportPersonId = reportPersonId;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getAuditorPersonName() {
		return auditorPersonName;
	}

	public void setAuditorPersonName(String auditorPersonName) {
		this.auditorPersonName = auditorPersonName;
	}

	public String getAuditorPersonId() {
		return auditorPersonId;
	}

	public void setAuditorPersonId(String auditorPersonId) {
		this.auditorPersonId = auditorPersonId;
	}

	public String getAuditorDate() {
		return auditorDate;
	}

	public void setAuditorDate(String auditorDate) {
		this.auditorDate = auditorDate;
	}

	public String getCreatePersonName() {
		return createPersonName;
	}

	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}

	public String getCreatePersonId() {
		return createPersonId;
	}

	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLastModifyPersonId() {
		return lastModifyPersonId;
	}

	public void setLastModifyPersonId(String lastModifyPersonId) {
		this.lastModifyPersonId = lastModifyPersonId;
	}

	public String getLastModifyPersonName() {
		return lastModifyPersonName;
	}

	public void setLastModifyPersonName(String lastModifyPersonName) {
		this.lastModifyPersonName = lastModifyPersonName;
	}

	public String getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public String getParentorg() {
		return parentorg;
	}

	public void setParentorg(String parentorg) {
		this.parentorg = parentorg;
	}

	public DataMoneyFlowWeekCi getDataMoneyFlowWeekCiTemp() {
		return DataMoneyFlowWeekCiTemp;
	}

	public void setDataMoneyFlowWeekCiTemp(
			DataMoneyFlowWeekCi dataMoneyFlowWeekCiTemp) {
		DataMoneyFlowWeekCiTemp = dataMoneyFlowWeekCiTemp;
	}

	public DataMoneyFlowWeekCo getDataMoneyFlowWeekCoTemp() {
		return DataMoneyFlowWeekCoTemp;
	}

	public void setDataMoneyFlowWeekCoTemp(
			DataMoneyFlowWeekCo dataMoneyFlowWeekCoTemp) {
		DataMoneyFlowWeekCoTemp = dataMoneyFlowWeekCoTemp;
	}

	public DataMoneyFlowWeekIo getDataMoneyFlowWeekIoTemp() {
		return DataMoneyFlowWeekIoTemp;
	}

	public void setDataMoneyFlowWeekIoTemp(
			DataMoneyFlowWeekIo dataMoneyFlowWeekIoTemp) {
		DataMoneyFlowWeekIoTemp = dataMoneyFlowWeekIoTemp;
	}

	public Set<DataMoneyFlowWeekCi> getDataMoneyFlowWeekCi() {
		return DataMoneyFlowWeekCi;
	}

	public void setDataMoneyFlowWeekCi(Set<DataMoneyFlowWeekCi> dataMoneyFlowWeekCi) {
		DataMoneyFlowWeekCi = dataMoneyFlowWeekCi;
	}

	public Set<DataMoneyFlowWeekCo> getDataMoneyFlowWeekCo() {
		return DataMoneyFlowWeekCo;
	}

	public void setDataMoneyFlowWeekCo(Set<DataMoneyFlowWeekCo> dataMoneyFlowWeekCo) {
		DataMoneyFlowWeekCo = dataMoneyFlowWeekCo;
	}

	public Set<DataMoneyFlowWeekIo> getDataMoneyFlowWeekIo() {
		return DataMoneyFlowWeekIo;
	}

	public void setDataMoneyFlowWeekIo(Set<DataMoneyFlowWeekIo> dataMoneyFlowWeekIo) {
		DataMoneyFlowWeekIo = dataMoneyFlowWeekIo;
	}

	public String getAuthOrg() {
		return authOrg;
	}

	public void setAuthOrg(String authOrg) {
		this.authOrg = authOrg;
	}

}