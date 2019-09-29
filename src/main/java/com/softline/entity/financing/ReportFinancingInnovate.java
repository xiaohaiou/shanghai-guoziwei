package com.softline.entity.financing;

/**
 * ReportFinancingInnovate entity. @author MyEclipse Persistence Tools
 */

public class ReportFinancingInnovate implements java.io.Serializable {

	// Fields

	private Integer id;
	private String coreOrg;
	private String coreOrgname;
	private String projectName;
	private String modelRequires;
	private String hypothecationInformation;
	private String pattern;
	private String financialInstitution;
	private String scale;
	private String term;
	private String comprehensiveFinancingCostRatio;
	private String operationDepartment;
	private String financingStructure;
	private String financingProgress;
	private String expectAccountDate;
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
	private String problem;
	private Integer projectProgress;
	
	//formula
	private String statusName;
	private String patternName;
	private String projectProgressName;
	private String parentOrgName;

	// Constructors

	/** default constructor */
	public ReportFinancingInnovate() {
	}

	/** minimal constructor */
	public ReportFinancingInnovate(String coreOrg, Integer isdel, Integer status) {
		this.coreOrg = coreOrg;
		this.isdel = isdel;
		this.status = status;
	}

	/** full constructor */
	public ReportFinancingInnovate(String coreOrg, String coreOrgname,
			String projectName, String hypothecationInformation, String pattern,
			String financialInstitution, String scale, String term,
			String comprehensiveFinancingCostRatio, String operationDepartment,
			String financingStructure, String financingProgress,
			String expectAccountDate, String alreadyAccountAmounts,
			String personLiable, String operator, String operatorNum,
			Integer isdel, Integer status, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate, String parentorg,String problem,Integer projectProgress,
			String projectProgressName,String parentOrgName) {
		this.coreOrg = coreOrg;
		this.coreOrgname = coreOrgname;
		this.projectName = projectName;
		this.hypothecationInformation = hypothecationInformation;
		this.pattern = pattern;
		this.financialInstitution = financialInstitution;
		this.scale = scale;
		this.term = term;
		this.comprehensiveFinancingCostRatio = comprehensiveFinancingCostRatio;
		this.operationDepartment = operationDepartment;
		this.financingStructure = financingStructure;
		this.financingProgress = financingProgress;
		this.expectAccountDate = expectAccountDate;
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
		this.problem=problem;
		this.projectProgress = projectProgress;
		this.projectProgressName = projectProgressName;
		this.parentOrgName=parentOrgName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelRequires() {
		return modelRequires;
	}

	public void setModelRequires(String modelRequires) {
		this.modelRequires = modelRequires;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getPatternName() {
		return patternName;
	}

	public void setPatternName(String patternName) {
		this.patternName = patternName;
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getHypothecationInformation() {
		return this.hypothecationInformation;
	}

	public void setHypothecationInformation(String hypothecationInformation) {
		this.hypothecationInformation = hypothecationInformation;
	}

	public String getPattern() {
		return this.pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getFinancialInstitution() {
		return this.financialInstitution;
	}

	public void setFinancialInstitution(String financialInstitution) {
		this.financialInstitution = financialInstitution;
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

	public String getComprehensiveFinancingCostRatio() {
		return this.comprehensiveFinancingCostRatio;
	}

	public void setComprehensiveFinancingCostRatio(
			String comprehensiveFinancingCostRatio) {
		this.comprehensiveFinancingCostRatio = comprehensiveFinancingCostRatio;
	}

	public String getOperationDepartment() {
		return this.operationDepartment;
	}

	public void setOperationDepartment(String operationDepartment) {
		this.operationDepartment = operationDepartment;
	}

	public String getFinancingStructure() {
		return this.financingStructure;
	}

	public void setFinancingStructure(String financingStructure) {
		this.financingStructure = financingStructure;
	}

	public String getFinancingProgress() {
		return this.financingProgress;
	}

	public void setFinancingProgress(String financingProgress) {
		this.financingProgress = financingProgress;
	}

	public String getExpectAccountDate() {
		return this.expectAccountDate;
	}

	public void setExpectAccountDate(String expectAccountDate) {
		this.expectAccountDate = expectAccountDate;
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

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
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