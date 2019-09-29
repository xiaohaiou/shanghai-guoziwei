package com.softline.entity;

/**
 * RiskControlAccount entity.
 */

public class RiskControlAccount implements java.io.Serializable {
	// Fields
	private Integer id;
	private String complainProjectName;
	private String undertakeDept;
	private Integer isGroupInternalComplain;
	private Integer complainChannel;
	private String complainedPersonNo;
	private Integer complainContentType;
	private Integer complainedPersonDept;
	private Integer isAnonymityComplain;
	private String complainedPersonName;
	private String complainManageDate;
	private String complainedPersonDuty;
	private String complainEscalateDate;
	private String complainedPersonPhone;
	private String complainFinalApprover;
	private String complainReceiveDate;
	private Integer isFollowupInvestigation;
	private String reportInvestigation;
	private String noFollowupInvestigationReason;
	//调查信息
	private String investigateHeader;
	private String responsiblePerson;
	private String surveyStartupTime;
	private String suggestPersonBlameCondition;
	private String surveyEndTime;
	private Integer investigateInvolveDept;
	private String raiseSuggestCondition;
	private String investigatePersonName;
	private String investigatePersonDuty;
	private String investigateReportNo;
	private Integer investigatedPersonLevel;
	private Integer reportBelongCompany;
	private Integer isInvestigatedCooperate;
	private String reportName;
	private String responsibilityFor;
	private String reportType;
	private String reportWriter;
	private String investigateConclusion;
	private String reportReviewer;
	private String reportCommitTime;
	private String reportReviewTime;
	private String reportFinalExaminer;
	private String investigateFindOtherInfraction;
	private Integer isInvolveAbarbeitung;
	//整改信息
	private String punishReportNo;
	private String abarbeitungNoticeTime;
	private String punishReportNo2;
	private Integer isAbarbeitungAccept;
	private String abarbeitungOfficer;
	private String abarbeitungPassTime;
	private String requireAbarbeitungTime;
	private String abarbeitungAccepter;
	private String suggestWorkCondition;
	private String abarbeitungResult;
	//必须字段
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
	//关联相关表查询
	private String complainedPersonDeptName;
	private String statusName;
	private String investigateInvolveDeptName;
	private String reportBelongCompanyName;
	private String investigatedPersonLevelName;
	
	public RiskControlAccount() {
		super();
	}

	public RiskControlAccount(Integer id, String complainProjectName, String undertakeDept, Integer isGroupInternalComplain, 
			Integer complainChannel, String complainedPersonNo, Integer complainContentType, Integer complainedPersonDept, 
			Integer isAnonymityComplain, String complainedPersonName, String complainManageDate, String complainedPersonDuty,
			String complainEscalateDate, String complainedPersonPhone, String complainFinalApprover, String complainReceiveDate,
			Integer isFollowupInvestigation, String reportInvestigation, String noFollowupInvestigationReason, String investigateHeader,
			String responsiblePerson, String surveyStartupTime, String suggestPersonBlameCondition,String surveyEndTime, Integer investigateInvolveDept,
			String raiseSuggestCondition, String investigatePersonName, String investigatePersonDuty, String investigateReportNo,
			Integer investigatedPersonLevel, Integer reportBelongCompany, Integer isInvestigatedCooperate, String reportName, String responsibilityFor,
			String reportType, String reportWriter, String investigateConclusion, String reportReviewer, String reportCommitTime,
			String reportReviewTime, String reportFinalExaminer, String investigateFindOtherInfraction, Integer isInvolveAbarbeitung,
			String punishReportNo, String abarbeitungNoticeTime, String punishReportNo2, Integer isAbarbeitungAccept,String abarbeitungOfficer,
			String abarbeitungPassTime, String requireAbarbeitungTime, String abarbeitungAccepter, String suggestWorkCondition, String abarbeitungResult,
			Integer status, Integer isDel, String createPersonName, String createPersonId, String createDate, String lastModifyPersonId, 
			String lastModifyPersonName, String lastModifyDate, String reportPersonName, String reportPersonId, String reportDate, 
			String auditorPersonName, String auditorPersonId, String auditorDate) {
		super();
		this.id = id;
		this.complainProjectName = complainProjectName;
		this.undertakeDept = undertakeDept;
		this.isGroupInternalComplain = isGroupInternalComplain;
		this.complainChannel = complainChannel;
		this.complainedPersonNo = complainedPersonNo;
		this.complainContentType = complainContentType;
		this.complainedPersonDept = complainedPersonDept;
		this.isAnonymityComplain = isAnonymityComplain;
		this.complainedPersonName = complainedPersonName;
		this.complainManageDate = complainManageDate;
		this.complainedPersonDuty = complainedPersonDuty;
		this.complainEscalateDate = complainEscalateDate;
		this.complainedPersonPhone = complainedPersonPhone;
		this.complainFinalApprover = complainFinalApprover;
		this.complainReceiveDate = complainReceiveDate;
		this.isFollowupInvestigation = isFollowupInvestigation;
		this.reportInvestigation = reportInvestigation;
		this.noFollowupInvestigationReason = noFollowupInvestigationReason;
		this.investigateHeader = investigateHeader;
		this.responsiblePerson = responsiblePerson;
		this.surveyStartupTime = surveyStartupTime;
		this.surveyEndTime = surveyEndTime;
		this.investigateInvolveDept = investigateInvolveDept;
		this.raiseSuggestCondition = raiseSuggestCondition;
		this.investigatePersonName = investigatePersonName;
		this.investigatePersonDuty = investigatePersonDuty;
		this.investigateReportNo = investigateReportNo;
		this.investigatedPersonLevel = investigatedPersonLevel;
		this.reportBelongCompany = reportBelongCompany;
		this.isInvestigatedCooperate = isInvestigatedCooperate;
		this.reportName = reportName;
		this.responsibilityFor = responsibilityFor;
		this.reportType = reportType;
		this.reportWriter = reportWriter;
		this.investigateConclusion = investigateConclusion;
		this.reportReviewer = reportReviewer;
		this.reportCommitTime = reportCommitTime;
		this.reportReviewTime = reportReviewTime;
		this.reportFinalExaminer = reportFinalExaminer;
		this.investigateFindOtherInfraction = investigateFindOtherInfraction;
		this.isInvolveAbarbeitung = isInvolveAbarbeitung;
		this.punishReportNo = punishReportNo;
		this.abarbeitungNoticeTime = abarbeitungNoticeTime;
		this.punishReportNo2 = punishReportNo2;
		this.isAbarbeitungAccept = isAbarbeitungAccept;
		this.abarbeitungOfficer = abarbeitungOfficer;
		this.abarbeitungPassTime = abarbeitungPassTime;
		this.requireAbarbeitungTime = requireAbarbeitungTime;
		this.abarbeitungAccepter = abarbeitungAccepter;
		this.suggestWorkCondition = suggestWorkCondition;
		this.abarbeitungResult = abarbeitungResult;
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
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComplainProjectName() {
		return complainProjectName;
	}

	public void setComplainProjectName(String complainProjectName) {
		this.complainProjectName = complainProjectName;
	}

	public String getUndertakeDept() {
		return undertakeDept;
	}

	public void setUndertakeDept(String undertakeDept) {
		this.undertakeDept = undertakeDept;
	}

	public Integer getIsGroupInternalComplain() {
		return isGroupInternalComplain;
	}

	public void setIsGroupInternalComplain(Integer isGroupInternalComplain) {
		this.isGroupInternalComplain = isGroupInternalComplain;
	}

	public Integer getComplainChannel() {
		return complainChannel;
	}

	public void setComplainChannel(Integer complainChannel) {
		this.complainChannel = complainChannel;
	}

	public String getComplainedPersonNo() {
		return complainedPersonNo;
	}

	public void setComplainedPersonNo(String complainedPersonNo) {
		this.complainedPersonNo = complainedPersonNo;
	}

	public Integer getComplainContentType() {
		return complainContentType;
	}

	public void setComplainContentType(Integer complainContentType) {
		this.complainContentType = complainContentType;
	}

	public Integer getComplainedPersonDept() {
		return complainedPersonDept;
	}

	public void setComplainedPersonDept(Integer complainedPersonDept) {
		this.complainedPersonDept = complainedPersonDept;
	}

	public Integer getIsAnonymityComplain() {
		return isAnonymityComplain;
	}

	public void setIsAnonymityComplain(Integer isAnonymityComplain) {
		this.isAnonymityComplain = isAnonymityComplain;
	}

	public String getComplainedPersonName() {
		return complainedPersonName;
	}

	public void setComplainedPersonName(String complainedPersonName) {
		this.complainedPersonName = complainedPersonName;
	}

	public String getComplainManageDate() {
		return complainManageDate;
	}

	public void setComplainManageDate(String complainManageDate) {
		this.complainManageDate = complainManageDate;
	}

	public String getComplainedPersonDuty() {
		return complainedPersonDuty;
	}

	public void setComplainedPersonDuty(String complainedPersonDuty) {
		this.complainedPersonDuty = complainedPersonDuty;
	}

	public String getComplainEscalateDate() {
		return complainEscalateDate;
	}

	public void setComplainEscalateDate(String complainEscalateDate) {
		this.complainEscalateDate = complainEscalateDate;
	}

	public String getComplainedPersonPhone() {
		return complainedPersonPhone;
	}

	public void setComplainedPersonPhone(String complainedPersonPhone) {
		this.complainedPersonPhone = complainedPersonPhone;
	}

	public String getComplainFinalApprover() {
		return complainFinalApprover;
	}

	public void setComplainFinalApprover(String complainFinalApprover) {
		this.complainFinalApprover = complainFinalApprover;
	}

	public String getComplainReceiveDate() {
		return complainReceiveDate;
	}

	public void setComplainReceiveDate(String complainReceiveDate) {
		this.complainReceiveDate = complainReceiveDate;
	}

	public Integer getIsFollowupInvestigation() {
		return isFollowupInvestigation;
	}

	public void setIsFollowupInvestigation(Integer isFollowupInvestigation) {
		this.isFollowupInvestigation = isFollowupInvestigation;
	}

	public String getReportInvestigation() {
		return reportInvestigation;
	}

	public void setReportInvestigation(String reportInvestigation) {
		this.reportInvestigation = reportInvestigation;
	}

	public String getNoFollowupInvestigationReason() {
		return noFollowupInvestigationReason;
	}

	public void setNoFollowupInvestigationReason(
			String noFollowupInvestigationReason) {
		this.noFollowupInvestigationReason = noFollowupInvestigationReason;
	}

	public String getInvestigateHeader() {
		return investigateHeader;
	}

	public void setInvestigateHeader(String investigateHeader) {
		this.investigateHeader = investigateHeader;
	}

	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public String getSurveyStartupTime() {
		return surveyStartupTime;
	}

	public void setSurveyStartupTime(String surveyStartupTime) {
		this.surveyStartupTime = surveyStartupTime;
	}

	public String getSuggestPersonBlameCondition() {
		return suggestPersonBlameCondition;
	}

	public void setSuggestPersonBlameCondition(String suggestPersonBlameCondition) {
		this.suggestPersonBlameCondition = suggestPersonBlameCondition;
	}

	public String getSurveyEndTime() {
		return surveyEndTime;
	}

	public void setSurveyEndTime(String surveyEndTime) {
		this.surveyEndTime = surveyEndTime;
	}

	public Integer getInvestigateInvolveDept() {
		return investigateInvolveDept;
	}

	public void setInvestigateInvolveDept(Integer investigateInvolveDept) {
		this.investigateInvolveDept = investigateInvolveDept;
	}

	public String getRaiseSuggestCondition() {
		return raiseSuggestCondition;
	}

	public void setRaiseSuggestCondition(String raiseSuggestCondition) {
		this.raiseSuggestCondition = raiseSuggestCondition;
	}

	public String getInvestigatePersonName() {
		return investigatePersonName;
	}

	public void setInvestigatePersonName(String investigatePersonName) {
		this.investigatePersonName = investigatePersonName;
	}

	public String getInvestigatePersonDuty() {
		return investigatePersonDuty;
	}

	public void setInvestigatePersonDuty(String investigatePersonDuty) {
		this.investigatePersonDuty = investigatePersonDuty;
	}

	public String getInvestigateReportNo() {
		return investigateReportNo;
	}

	public void setInvestigateReportNo(String investigateReportNo) {
		this.investigateReportNo = investigateReportNo;
	}

	public Integer getInvestigatedPersonLevel() {
		return investigatedPersonLevel;
	}

	public void setInvestigatedPersonLevel(Integer investigatedPersonLevel) {
		this.investigatedPersonLevel = investigatedPersonLevel;
	}

	public Integer getReportBelongCompany() {
		return reportBelongCompany;
	}

	public void setReportBelongCompany(Integer reportBelongCompany) {
		this.reportBelongCompany = reportBelongCompany;
	}

	public Integer getIsInvestigatedCooperate() {
		return isInvestigatedCooperate;
	}

	public void setIsInvestigatedCooperate(Integer isInvestigatedCooperate) {
		this.isInvestigatedCooperate = isInvestigatedCooperate;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getResponsibilityFor() {
		return responsibilityFor;
	}

	public void setResponsibilityFor(String responsibilityFor) {
		this.responsibilityFor = responsibilityFor;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getReportWriter() {
		return reportWriter;
	}

	public void setReportWriter(String reportWriter) {
		this.reportWriter = reportWriter;
	}

	public String getInvestigateConclusion() {
		return investigateConclusion;
	}

	public void setInvestigateConclusion(String investigateConclusion) {
		this.investigateConclusion = investigateConclusion;
	}

	public String getReportReviewer() {
		return reportReviewer;
	}

	public void setReportReviewer(String reportReviewer) {
		this.reportReviewer = reportReviewer;
	}

	public String getReportCommitTime() {
		return reportCommitTime;
	}

	public void setReportCommitTime(String reportCommitTime) {
		this.reportCommitTime = reportCommitTime;
	}

	public String getReportReviewTime() {
		return reportReviewTime;
	}

	public void setReportReviewTime(String reportReviewTime) {
		this.reportReviewTime = reportReviewTime;
	}

	public String getReportFinalExaminer() {
		return reportFinalExaminer;
	}

	public void setReportFinalExaminer(String reportFinalExaminer) {
		this.reportFinalExaminer = reportFinalExaminer;
	}

	public String getInvestigateFindOtherInfraction() {
		return investigateFindOtherInfraction;
	}

	public void setInvestigateFindOtherInfraction(
			String investigateFindOtherInfraction) {
		this.investigateFindOtherInfraction = investigateFindOtherInfraction;
	}

	public Integer getIsInvolveAbarbeitung() {
		return isInvolveAbarbeitung;
	}

	public void setIsInvolveAbarbeitung(Integer isInvolveAbarbeitung) {
		this.isInvolveAbarbeitung = isInvolveAbarbeitung;
	}

	public String getPunishReportNo() {
		return punishReportNo;
	}

	public void setPunishReportNo(String punishReportNo) {
		this.punishReportNo = punishReportNo;
	}

	public String getAbarbeitungNoticeTime() {
		return abarbeitungNoticeTime;
	}

	public void setAbarbeitungNoticeTime(String abarbeitungNoticeTime) {
		this.abarbeitungNoticeTime = abarbeitungNoticeTime;
	}

	public String getPunishReportNo2() {
		return punishReportNo2;
	}

	public void setPunishReportNo2(String punishReportNo2) {
		this.punishReportNo2 = punishReportNo2;
	}

	public Integer getIsAbarbeitungAccept() {
		return isAbarbeitungAccept;
	}

	public void setIsAbarbeitungAccept(Integer isAbarbeitungAccept) {
		this.isAbarbeitungAccept = isAbarbeitungAccept;
	}

	public String getAbarbeitungOfficer() {
		return abarbeitungOfficer;
	}

	public void setAbarbeitungOfficer(String abarbeitungOfficer) {
		this.abarbeitungOfficer = abarbeitungOfficer;
	}

	public String getAbarbeitungPassTime() {
		return abarbeitungPassTime;
	}

	public void setAbarbeitungPassTime(String abarbeitungPassTime) {
		this.abarbeitungPassTime = abarbeitungPassTime;
	}

	public String getRequireAbarbeitungTime() {
		return requireAbarbeitungTime;
	}

	public void setRequireAbarbeitungTime(String requireAbarbeitungTime) {
		this.requireAbarbeitungTime = requireAbarbeitungTime;
	}

	public String getAbarbeitungAccepter() {
		return abarbeitungAccepter;
	}

	public void setAbarbeitungAccepter(String abarbeitungAccepter) {
		this.abarbeitungAccepter = abarbeitungAccepter;
	}

	public String getSuggestWorkCondition() {
		return suggestWorkCondition;
	}

	public void setSuggestWorkCondition(String suggestWorkCondition) {
		this.suggestWorkCondition = suggestWorkCondition;
	}

	public String getAbarbeitungResult() {
		return abarbeitungResult;
	}

	public void setAbarbeitungResult(String abarbeitungResult) {
		this.abarbeitungResult = abarbeitungResult;
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

	public String getComplainedPersonDeptName() {
		return complainedPersonDeptName;
	}

	public void setComplainedPersonDeptName(String complainedPersonDeptName) {
		this.complainedPersonDeptName = complainedPersonDeptName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getInvestigateInvolveDeptName() {
		return investigateInvolveDeptName;
	}

	public void setInvestigateInvolveDeptName(String investigateInvolveDeptName) {
		this.investigateInvolveDeptName = investigateInvolveDeptName;
	}

	public String getReportBelongCompanyName() {
		return reportBelongCompanyName;
	}

	public void setReportBelongCompanyName(String reportBelongCompanyName) {
		this.reportBelongCompanyName = reportBelongCompanyName;
	}

	public String getInvestigatedPersonLevelName() {
		return investigatedPersonLevelName;
	}

	public void setInvestigatedPersonLevelName(String investigatedPersonLevelName) {
		this.investigatedPersonLevelName = investigatedPersonLevelName;
	}
	
}