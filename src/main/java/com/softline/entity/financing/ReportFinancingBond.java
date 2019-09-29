package com.softline.entity.financing;

/**
 * ReportFinancingBond entity. @author MyEclipse Persistence Tools
 */

public class ReportFinancingBond implements java.io.Serializable {

	// Fields

	private Integer id;
	private String coreOrg;
	private String coreOrgname;
	private String debt;
	private String responsibleOrganizationId;
	private String responsibleOrganization;
	private String commitmentShallSubjectId;
	private String commitmentShallSubject;
	private String guaranteeConditions;
	private String theUnderwritingInstitution;
	private String approvalAgencies;
	private String scale;
	private String term;
	private String expectedInterestRate;
	private String annualizedUnderwritingRate;
	private String compositeCost;
	private String addOrContinue;
	private String prepareACaseReport;
	private String underwritersApproval;
	private String approvalAgenciesOpinion;
	private String auditReportOpinion;
	private String legalOpinion;
	private String bondRatingOpinion;
	private String prospectusOpinion;
	private String issuanceOfPhase;
	private String issuanceOfTime;
	private String alreadyAccountAmounts;
	private String personLiable;
	private String operator;
	private String operatorNum;
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
	private Integer projectProgress;
	
	private String starttime;
	private String endtime;
	
	private String statusName;
	private String projectProgressName;
	
	private String parentOrgName;

	// Constructors

	/** default constructor */
	public ReportFinancingBond() {
	}

	/** minimal constructor */
	public ReportFinancingBond(String coreOrg,
			String responsibleOrganizationId,
			String commitmentShallSubjectId, String guaranteeConditions,
			String theUnderwritingInstitution, String approvalAgencies,
			String scale, String term, String compositeCost,
			String addOrContinue, String prepareACaseReport,
			String personLiable, String operator, String operatorNum,
			Integer isdel, Integer status,Integer projectProgress,
			String projectProgressName) {
		this.coreOrg = coreOrg;
		this.responsibleOrganizationId = responsibleOrganizationId;
		this.commitmentShallSubjectId = commitmentShallSubjectId;
		this.guaranteeConditions = guaranteeConditions;
		this.theUnderwritingInstitution = theUnderwritingInstitution;
		this.approvalAgencies = approvalAgencies;
		this.scale = scale;
		this.term = term;
		this.compositeCost = compositeCost;
		this.addOrContinue = addOrContinue;
		this.prepareACaseReport = prepareACaseReport;
		this.personLiable = personLiable;
		this.operator = operator;
		this.operatorNum = operatorNum;
		this.isdel = isdel;
		this.status = status;
		this.projectProgress = projectProgress;
		this.projectProgressName = projectProgressName;
	}

	/** full constructor */
	public ReportFinancingBond(String coreOrg, String coreOrgname, String debt,
			String responsibleOrganizationId, String responsibleOrganization,
			String commitmentShallSubjectId, String commitmentShallSubject,
			String guaranteeConditions, String theUnderwritingInstitution,
			String approvalAgencies, String scale, String term,
			String expectedInterestRate, String annualizedUnderwritingRate,
			String compositeCost, String addOrContinue,
			String prepareACaseReport, String underwritersApproval,
			String approvalAgenciesOpinion, String auditReportOpinion,
			String legalOpinion, String bondRatingOpinion,
			String prospectusOpinion, String issuanceOfPhase,
			String issuanceOfTime, String alreadyAccountAmounts,
			String personLiable, String operator, String operatorNum,
			Integer isdel, Integer status, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate, String parentorg,
			String parentOrgName) {
		this.coreOrg = coreOrg;
		this.coreOrgname = coreOrgname;
		this.debt = debt;
		this.responsibleOrganizationId = responsibleOrganizationId;
		this.responsibleOrganization = responsibleOrganization;
		this.commitmentShallSubjectId = commitmentShallSubjectId;
		this.commitmentShallSubject = commitmentShallSubject;
		this.guaranteeConditions = guaranteeConditions;
		this.theUnderwritingInstitution = theUnderwritingInstitution;
		this.approvalAgencies = approvalAgencies;
		this.scale = scale;
		this.term = term;
		this.expectedInterestRate = expectedInterestRate;
		this.annualizedUnderwritingRate = annualizedUnderwritingRate;
		this.compositeCost = compositeCost;
		this.addOrContinue = addOrContinue;
		this.prepareACaseReport = prepareACaseReport;
		this.underwritersApproval = underwritersApproval;
		this.approvalAgenciesOpinion = approvalAgenciesOpinion;
		this.auditReportOpinion = auditReportOpinion;
		this.legalOpinion = legalOpinion;
		this.bondRatingOpinion = bondRatingOpinion;
		this.prospectusOpinion = prospectusOpinion;
		this.issuanceOfPhase = issuanceOfPhase;
		this.issuanceOfTime = issuanceOfTime;
		this.alreadyAccountAmounts = alreadyAccountAmounts;
		this.personLiable = personLiable;
		this.operator = operator;
		this.operatorNum = operatorNum;
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
		this.parentOrgName=parentOrgName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}


	public String getCoreOrg() {
		return this.coreOrg;
	}

	public void setCoreOrg(String coreOrg) {
		this.coreOrg = coreOrg;
	}

	public String getCoreOrgname() {
		return this.coreOrgname;
	}

	public void setCoreOrgname(String coreOrgname) {
		this.coreOrgname = coreOrgname;
	}

	public String getDebt() {
		return this.debt;
	}

	public void setDebt(String debt) {
		this.debt = debt;
	}

	public String getResponsibleOrganizationId() {
		return this.responsibleOrganizationId;
	}

	public void setResponsibleOrganizationId(String responsibleOrganizationId) {
		this.responsibleOrganizationId = responsibleOrganizationId;
	}

	public String getResponsibleOrganization() {
		return this.responsibleOrganization;
	}

	public void setResponsibleOrganization(String responsibleOrganization) {
		this.responsibleOrganization = responsibleOrganization;
	}

	public String getCommitmentShallSubjectId() {
		return this.commitmentShallSubjectId;
	}

	public void setCommitmentShallSubjectId(String commitmentShallSubjectId) {
		this.commitmentShallSubjectId = commitmentShallSubjectId;
	}

	public String getCommitmentShallSubject() {
		return this.commitmentShallSubject;
	}

	public void setCommitmentShallSubject(String commitmentShallSubject) {
		this.commitmentShallSubject = commitmentShallSubject;
	}

	public String getGuaranteeConditions() {
		return this.guaranteeConditions;
	}

	public void setGuaranteeConditions(String guaranteeConditions) {
		this.guaranteeConditions = guaranteeConditions;
	}

	public String getTheUnderwritingInstitution() {
		return this.theUnderwritingInstitution;
	}

	public void setTheUnderwritingInstitution(String theUnderwritingInstitution) {
		this.theUnderwritingInstitution = theUnderwritingInstitution;
	}

	public String getApprovalAgencies() {
		return this.approvalAgencies;
	}

	public void setApprovalAgencies(String approvalAgencies) {
		this.approvalAgencies = approvalAgencies;
	}

	public String getScale() {
		return this.scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getTerm() {
		return this.term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getExpectedInterestRate() {
		return this.expectedInterestRate;
	}

	public void setExpectedInterestRate(String expectedInterestRate) {
		this.expectedInterestRate = expectedInterestRate;
	}

	public String getAnnualizedUnderwritingRate() {
		return this.annualizedUnderwritingRate;
	}

	public void setAnnualizedUnderwritingRate(String annualizedUnderwritingRate) {
		this.annualizedUnderwritingRate = annualizedUnderwritingRate;
	}

	public String getCompositeCost() {
		return this.compositeCost;
	}

	public void setCompositeCost(String compositeCost) {
		this.compositeCost = compositeCost;
	}

	public String getAddOrContinue() {
		return this.addOrContinue;
	}

	public void setAddOrContinue(String addOrContinue) {
		this.addOrContinue = addOrContinue;
	}

	public String getPrepareACaseReport() {
		return this.prepareACaseReport;
	}

	public void setPrepareACaseReport(String prepareACaseReport) {
		this.prepareACaseReport = prepareACaseReport;
	}

	public String getUnderwritersApproval() {
		return this.underwritersApproval;
	}

	public void setUnderwritersApproval(String underwritersApproval) {
		this.underwritersApproval = underwritersApproval;
	}

	public String getApprovalAgenciesOpinion() {
		return this.approvalAgenciesOpinion;
	}

	public void setApprovalAgenciesOpinion(String approvalAgenciesOpinion) {
		this.approvalAgenciesOpinion = approvalAgenciesOpinion;
	}

	public String getAuditReportOpinion() {
		return this.auditReportOpinion;
	}

	public void setAuditReportOpinion(String auditReportOpinion) {
		this.auditReportOpinion = auditReportOpinion;
	}

	public String getLegalOpinion() {
		return this.legalOpinion;
	}

	public void setLegalOpinion(String legalOpinion) {
		this.legalOpinion = legalOpinion;
	}

	public String getBondRatingOpinion() {
		return this.bondRatingOpinion;
	}

	public void setBondRatingOpinion(String bondRatingOpinion) {
		this.bondRatingOpinion = bondRatingOpinion;
	}

	public String getProspectusOpinion() {
		return this.prospectusOpinion;
	}

	public void setProspectusOpinion(String prospectusOpinion) {
		this.prospectusOpinion = prospectusOpinion;
	}

	public String getIssuanceOfPhase() {
		return this.issuanceOfPhase;
	}

	public void setIssuanceOfPhase(String issuanceOfPhase) {
		this.issuanceOfPhase = issuanceOfPhase;
	}

	public String getIssuanceOfTime() {
		return this.issuanceOfTime;
	}

	public void setIssuanceOfTime(String issuanceOfTime) {
		this.issuanceOfTime = issuanceOfTime;
	}

	public String getAlreadyAccountAmounts() {
		return this.alreadyAccountAmounts;
	}

	public void setAlreadyAccountAmounts(String alreadyAccountAmounts) {
		this.alreadyAccountAmounts = alreadyAccountAmounts;
	}

	public String getPersonLiable() {
		return this.personLiable;
	}

	public void setPersonLiable(String personLiable) {
		this.personLiable = personLiable;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatorNum() {
		return this.operatorNum;
	}

	public void setOperatorNum(String operatorNum) {
		this.operatorNum = operatorNum;
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
	public Integer getProjectProgress() {
		return projectProgress;
	}

	public void setProjectProgress(Integer projectProgress) {
		this.projectProgress = projectProgress;
	}

	public String getProjectProgressName() {
		return projectProgressName;
	}

	public void setProjectProgressName(String projectProgressName) {
		this.projectProgressName = projectProgressName;
	}

	public String getParentOrgName() {
		return parentOrgName;
	}

	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}
	
}