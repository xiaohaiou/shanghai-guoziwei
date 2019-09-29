package com.softline.entityTemp;

import java.util.List;

import com.softline.entity.ReportForms;

public class PurchaseView {
	//年
	private int year;
	//季度
	private String season;
	//库内供应数量总数(个)
	private String supportamount;
	//累计完成采购金额总数(万元)
	private String cumulativepurchaseamount;
	//工程采购节约率(%)
	private String rate;
	private String statusName;
	private Integer status;
	
	
	private String createPersonName;
	private String reportPersonName;
	private String reportPersonId;
	private String reportDate;
	private String auditorPersonName;
	private String auditorPersonId;
	private String auditorDate;
	
	//主键ID
	private int id;
	
	
	public String getCreatePersonName() {
		return createPersonName;
	}
	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getSupportamount() {
		return supportamount;
	}
	public void setSupportamount(String supportamount) {
		this.supportamount = supportamount;
	}
	public String getCumulativepurchaseamount() {
		return cumulativepurchaseamount;
	}
	public void setCumulativepurchaseamount(String cumulativepurchaseamount) {
		this.cumulativepurchaseamount = cumulativepurchaseamount;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public PurchaseView(int year, String season, String supportamount,
			String cumulativepurchaseamount, String rate, String statusName,
			Integer status, int id) {
		super();
		this.year = year;
		this.season = season;
		this.supportamount = supportamount;
		this.cumulativepurchaseamount = cumulativepurchaseamount;
		this.rate = rate;
		this.statusName = statusName;
		this.status = status;
		this.id = id;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public PurchaseView() {
		super();
	}
	
	
}
