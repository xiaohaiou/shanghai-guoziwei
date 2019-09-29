package com.softline.entity;

import java.util.List;

/**
 * AuditProject entity.
 */

public class AuditProject implements java.io.Serializable {
	// Fields
	private Integer id;
	private Integer auditDept;
	private String auditProjectName;
	private Integer auditType;
	private Integer auditType2;
	private String auditStartDate;
	private String auditEndDate;
	private String remark;
	private Integer isQuestion;
	private Integer isAbarbeitung;
	private Integer status;
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
	private String auditTypeName2;
	private Integer findQuestionNum = 0;
	private Integer abarbeitungQuestionNum = 0;
	private String caseTotalAmount = "0";
	private String saveLoss = "0";
	private String auditManage;
	
	private AuditProjectFindQuestion auditProjectFindQuestion;
	private List<AuditProjectAbarbeitungQuestion> auditProjectAbarbeitungQuestionList;
	
	public AuditProject() {
		super();
	}

	public AuditProject(Integer id, Integer auditDept, String auditProjectName,Integer auditType, 
			Integer auditType2, String auditStartDate,  String auditEndDate, String remark, 
			Integer isQuestion, Integer isAbarbeitung, Integer status, Integer isDel, String createPersonName, 
			String createPersonId, String createDate, String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName, String reportPersonId, String reportDate, 
			String auditorPersonName, String auditorPersonId, String auditorDate, String caseTotalAmount, 
			String saveLoss, String auditManage) {
		super();
		this.id = id;
		this.auditDept = auditDept;
		this.auditProjectName = auditProjectName;
		this.auditType = auditType;
		this.auditType2 = auditType2;
		this.auditStartDate = auditStartDate;
		this.auditEndDate = auditEndDate;
		this.remark = remark;
		this.isQuestion = isQuestion;
		this.isAbarbeitung = isAbarbeitung;
		this.status = status;
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
		this.caseTotalAmount = caseTotalAmount;
		this.saveLoss = saveLoss;
		this.auditManage = auditManage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAuditDept() {
		return auditDept;
	}

	public void setAuditDept(Integer auditDept) {
		this.auditDept = auditDept;
	}

	public String getAuditProjectName() {
		return auditProjectName;
	}

	public void setAuditProjectName(String auditProjectName) {
		this.auditProjectName = auditProjectName;
	}

	public Integer getAuditType() {
		return auditType;
	}

	public void setAuditType(Integer auditType) {
		this.auditType = auditType;
	}

	public Integer getAuditType2() {
		return auditType2;
	}

	public void setAuditType2(Integer auditType2) {
		this.auditType2 = auditType2;
	}

	public String getAuditStartDate() {
		return auditStartDate;
	}

	public void setAuditStartDate(String auditStartDate) {
		this.auditStartDate = auditStartDate;
	}

	public String getAuditEndDate() {
		return auditEndDate;
	}

	public void setAuditEndDate(String auditEndDate) {
		this.auditEndDate = auditEndDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsQuestion() {
		return isQuestion;
	}

	public void setIsQuestion(Integer isQuestion) {
		this.isQuestion = isQuestion;
	}

	public Integer getIsAbarbeitung() {
		return isAbarbeitung;
	}

	public void setIsAbarbeitung(Integer isAbarbeitung) {
		this.isAbarbeitung = isAbarbeitung;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getAuditTypeName2() {
		return auditTypeName2;
	}

	public void setAuditTypeName2(String auditTypeName2) {
		this.auditTypeName2 = auditTypeName2;
	}

	public Integer getFindQuestionNum() {
		return findQuestionNum;
	}

	public void setFindQuestionNum(Integer findQuestionNum) {
		this.findQuestionNum = findQuestionNum;
	}

	public Integer getAbarbeitungQuestionNum() {
		return abarbeitungQuestionNum;
	}

	public void setAbarbeitungQuestionNum(Integer abarbeitungQuestionNum) {
		this.abarbeitungQuestionNum = abarbeitungQuestionNum;
	}

	public AuditProjectFindQuestion getAuditProjectFindQuestion() {
		return auditProjectFindQuestion;
	}

	public void setAuditProjectFindQuestion(
			AuditProjectFindQuestion auditProjectFindQuestion) {
		this.auditProjectFindQuestion = auditProjectFindQuestion;
	}

	public List<AuditProjectAbarbeitungQuestion> getAuditProjectAbarbeitungQuestionList() {
		return auditProjectAbarbeitungQuestionList;
	}

	public void setAuditProjectAbarbeitungQuestionList(
			List<AuditProjectAbarbeitungQuestion> auditProjectAbarbeitungQuestionList) {
		this.auditProjectAbarbeitungQuestionList = auditProjectAbarbeitungQuestionList;
	}

	public String getCaseTotalAmount() {
		return caseTotalAmount;
	}

	public void setCaseTotalAmount(String caseTotalAmount) {
		this.caseTotalAmount = caseTotalAmount;
	}

	public String getSaveLoss() {
		return saveLoss;
	}

	public void setSaveLoss(String saveLoss) {
		this.saveLoss = saveLoss;
	}

	public String getAuditManage() {
		return auditManage;
	}

	public void setAuditManage(String auditManage) {
		this.auditManage = auditManage;
	}

}