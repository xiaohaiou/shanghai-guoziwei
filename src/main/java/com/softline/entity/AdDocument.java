package com.softline.entity;

/**
 * ReportForms entity. @author MyEclipse Persistence Tools
 */

public class AdDocument implements java.io.Serializable {

	// Fields

	private Integer id;
	private String compId;
	private String compName;
	private Integer year;
	private Integer month;
	private Integer totalDocument;
	private Integer totalOverTimeDocument;
	private Double avgDocumentTime;
	private Double avgNode;
	private Double examineRatio;
	private Integer status;
	private String statusName;
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
	
	public AdDocument() {
		super();
	}

	public AdDocument(Integer id, String compId, Integer year, Integer month,
			Integer totalDocument, Integer totalOverTimeDocument,
			Double avgDocumentTime, Double avgNode, Double examineRatio,
			Integer status, String statusName, Integer isdel,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate, String compName) {
		super();
		this.id = id;
		this.compId = compId;
		this.year = year;
		this.month = month;
		this.totalDocument = totalDocument;
		this.totalOverTimeDocument = totalOverTimeDocument;
		this.avgDocumentTime = avgDocumentTime;
		this.avgNode = avgNode;
		this.examineRatio = examineRatio;
		this.status = status;
		this.statusName = statusName;
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
		this.compName = compName;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getTotalDocument() {
		return totalDocument;
	}

	public void setTotalDocument(Integer totalDocument) {
		this.totalDocument = totalDocument;
	}

	public Integer getTotalOverTimeDocument() {
		return totalOverTimeDocument;
	}

	public void setTotalOverTimeDocument(Integer totalOverTimeDocument) {
		this.totalOverTimeDocument = totalOverTimeDocument;
	}

	public Double getAvgDocumentTime() {
		return avgDocumentTime;
	}

	public void setAvgDocumentTime(Double avgDocumentTime) {
		this.avgDocumentTime = avgDocumentTime;
	}

	public Double getAvgNode() {
		return avgNode;
	}

	public void setAvgNode(Double avgNode) {
		this.avgNode = avgNode;
	}

	public Double getExamineRatio() {
		return examineRatio;
	}

	public void setExamineRatio(Double examineRatio) {
		this.examineRatio = examineRatio;
	}

	public Integer getStatus() {
		return status;
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
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
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
	

}