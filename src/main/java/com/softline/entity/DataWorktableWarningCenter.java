package com.softline.entity;

public class DataWorktableWarningCenter {
	
	private Integer id;
	private String year;
	private String month;
	private String day;
	private String orgname;
	private String org;
	private String createPersonName;
	private int isdel;
	private String reportPersonName;
	private String auditorPersonName;
	private int status;
	private String createDate;
	private String reportDate;
	private String auditorDate;
	private String parentorg;
	private String page_author;
	private String model_id;
	private String model_name;
	private String url;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
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
	public String getCreatePersonName() {
		return createPersonName;
	}
	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	public String getReportPersonName() {
		return reportPersonName;
	}
	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}
	public String getAuditorPersonName() {
		return auditorPersonName;
	}
	public void setAuditorPersonName(String auditorPersonName) {
		this.auditorPersonName = auditorPersonName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getAuditorDate() {
		return auditorDate;
	}
	public void setAuditorDate(String auditorDate) {
		this.auditorDate = auditorDate;
	}
	public String getParentorg() {
		return parentorg;
	}
	public void setParentorg(String parentorg) {
		this.parentorg = parentorg;
	}
	public String getPage_author() {
		return page_author;
	}
	public void setPage_author(String page_author) {
		this.page_author = page_author;
	}
	public String getModel_id() {
		return model_id;
	}
	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}
	public String getModel_name() {
		return model_name;
	}
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "DataWorktableWarningCenter [id=" + id + ", year=" + year
				+ ", month=" + month + ", day=" + day + ", orgname=" + orgname
				+ ", org=" + org + ", createPersonName=" + createPersonName
				+ ", isdel=" + isdel + ", reportPersonName=" + reportPersonName
				+ ", auditorPersonName=" + auditorPersonName + ", status="
				+ status + ", createDate=" + createDate + ", reportDate="
				+ reportDate + ", auditorDate=" + auditorDate + ", parentorg="
				+ parentorg + ", page_author=" + page_author + ", model_id="
				+ model_id + ", model_name=" + model_name + ", url=" + url
				+ "]";
	}
	
}
