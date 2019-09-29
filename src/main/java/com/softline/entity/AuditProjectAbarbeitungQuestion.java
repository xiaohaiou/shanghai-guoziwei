package com.softline.entity;

/**
 * AuditProjectAbarbeitungQuestion entity.
 */

public class AuditProjectAbarbeitungQuestion implements java.io.Serializable {
	// Fields
	private Integer id;
	private Integer auditProjectId;
	private String auditProjectName;
	private Integer abarbeitungStatus = 0;
	private String abarbeitungOfficer;
	private String abarbeitungTimeLimit;
	private String existentialQuestion;
	private String auditSuggestion;
	private String abarbeitungMeasures;
	private String remark;
	private Integer isDel;
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
	private String statusName;
	private String auditDeptName;
	private String auditTypeName;
	
	public AuditProjectAbarbeitungQuestion() {
		super();
	}

	public AuditProjectAbarbeitungQuestion(Integer id, Integer auditProjectId, String auditProjectName,Integer abarbeitungStatus, 
			String abarbeitungOfficer, String abarbeitungTimeLimit,  String existentialQuestion, String auditSuggestion, 
			String abarbeitungMeasures, String remark, Integer isDel, String createPersonName, 
			String createPersonId, String createDate, String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName, String reportPersonId, String reportDate, 
			String auditorPersonName, String auditorPersonId, String auditorDate) {
		super();
		this.id = id;
		this.auditProjectId = auditProjectId;
		this.auditProjectName = auditProjectName;
		this.abarbeitungStatus = abarbeitungStatus;
		this.abarbeitungOfficer = abarbeitungOfficer;
		this.abarbeitungTimeLimit = abarbeitungTimeLimit;
		this.existentialQuestion = existentialQuestion;
		this.auditSuggestion = auditSuggestion;
		this.abarbeitungMeasures = abarbeitungMeasures;
		this.remark = remark;
		this.isDel = isDel;
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
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAuditProjectId() {
		return auditProjectId;
	}

	public void setAuditProjectId(Integer auditProjectId) {
		this.auditProjectId = auditProjectId;
	}

	public String getAuditProjectName() {
		return auditProjectName;
	}

	public void setAuditProjectName(String auditProjectName) {
		this.auditProjectName = auditProjectName;
	}

	public Integer getAbarbeitungStatus() {
		return abarbeitungStatus;
	}

	public void setAbarbeitungStatus(Integer abarbeitungStatus) {
		this.abarbeitungStatus = abarbeitungStatus;
	}

	public String getAbarbeitungOfficer() {
		return abarbeitungOfficer;
	}

	public void setAbarbeitungOfficer(String abarbeitungOfficer) {
		this.abarbeitungOfficer = abarbeitungOfficer;
	}

	public String getAbarbeitungTimeLimit() {
		return abarbeitungTimeLimit;
	}

	public void setAbarbeitungTimeLimit(String abarbeitungTimeLimit) {
		this.abarbeitungTimeLimit = abarbeitungTimeLimit;
	}

	public String getExistentialQuestion() {
		return existentialQuestion;
	}

	public void setExistentialQuestion(String existentialQuestion) {
		this.existentialQuestion = existentialQuestion;
	}

	public String getAuditSuggestion() {
		return auditSuggestion;
	}

	public void setAuditSuggestion(String auditSuggestion) {
		this.auditSuggestion = auditSuggestion;
	}

	public String getAbarbeitungMeasures() {
		return abarbeitungMeasures;
	}

	public void setAbarbeitungMeasures(String abarbeitungMeasures) {
		this.abarbeitungMeasures = abarbeitungMeasures;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getAuditDeptName() {
		return auditDeptName;
	}

	public void setAuditDeptName(String auditDeptName) {
		this.auditDeptName = auditDeptName;
	}

	public String getAuditTypeName() {
		return auditTypeName;
	}

	public void setAuditTypeName(String auditTypeName) {
		this.auditTypeName = auditTypeName;
	}
	
}