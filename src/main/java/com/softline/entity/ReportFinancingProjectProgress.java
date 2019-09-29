package com.softline.entity;

/**
 * ReportFinancingProjectProgress entity. @author MyEclipse Persistence Tools
 */

public class ReportFinancingProjectProgress implements java.io.Serializable {

	// Fields

	private Integer id;
	private String org;
	private String coreOrg;
	private String coreOrgname;
	private Integer category;
	private String operateOrg;
	private String operateOrgname;
	private String financingOrg;
	private String financingOrgname;
	private String hypothecationInformation;
	private String pattern;
	private String financialInstitution;
	private String scale;
	private String term;
	private Integer projectProgress;
	private Integer type;
	private String expectAccountDate;
	private String conditionStructure;
	private String alreadyAccountAmounts;
	private String personLiable;
	private String operator;
	private String operatorNum;
	private String interestRatio;
	private String serviceChargeRatio;
	private String comprehensiveFinancingCostRatio;
	private String projectProgressDescribe;
	private String projectFollowupAdvance;
	private String problem;
	private String projectDifficulty;
	private String solution;
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

	//formula
	private String statusName;
	private String typeName;
	private String categoryName;
	private String patternName;
	private String projectProgressName;
	
	//common
	private String starttime;
	private String endtime;
	private Integer getOperateType;
	
	private String parentOrgName;

	public Integer getGetOperateType() {
		return getOperateType;
	}

	public void setGetOperateType(Integer getOperateType) {
		this.getOperateType = getOperateType;
	}
	
	// Constructors

	/** default constructor */
	public ReportFinancingProjectProgress() {
	}

	/** minimal constructor */
	public ReportFinancingProjectProgress(String coreOrg, String operateOrg,
			String operateOrgname, String financingOrg,
			String financingOrgname, Integer isdel, Integer status) {
		this.coreOrg = coreOrg;
		this.operateOrg = operateOrg;
		this.operateOrgname = operateOrgname;
		this.financingOrg = financingOrg;
		this.financingOrgname = financingOrgname;
		this.isdel = isdel;
		this.status = status;
	}

	/** full constructor */
	public ReportFinancingProjectProgress(String coreOrg, String coreOrgname,
			Integer category, String operateOrg, String operateOrgname,
			String financingOrg, String financingOrgname,
			String hypothecationInformation, String pattern,
			String financialInstitution, String scale,
			String term, Integer projectProgress,
			Integer type, String expectAccountDate, String conditionStructure,
			String alreadyAccountAmounts, String personLiable, String operator,
			String operatorNum, String interestRatio, String serviceChargeRatio,
			String comprehensiveFinancingCostRatio,
			String projectProgressDescribe, String projectFollowupAdvance,
			String problem, String projectDifficulty, String solution,
			Integer isdel, Integer status, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate,
			String auditorPersonName, String auditorPersonId,
			String auditorDate, String parentorg,
			String typeName, String categoryName,
			String patternName, String projectProgressName,
			String starttime, String endtime, String statusName,String parentOrgName) {
		this.coreOrg = coreOrg;
		this.coreOrgname = coreOrgname;
		this.category = category;
		this.operateOrg = operateOrg;
		this.operateOrgname = operateOrgname;
		this.financingOrg = financingOrg;
		this.financingOrgname = financingOrgname;
		this.hypothecationInformation = hypothecationInformation;
		this.pattern = pattern;
		this.financialInstitution = financialInstitution;
		this.scale = scale;
		this.term = term;
		this.projectProgress = projectProgress;
		this.type = type;
		this.expectAccountDate = expectAccountDate;
		this.conditionStructure = conditionStructure;
		this.alreadyAccountAmounts = alreadyAccountAmounts;
		this.personLiable = personLiable;
		this.operator = operator;
		this.operatorNum = operatorNum;
		this.interestRatio = interestRatio;
		this.serviceChargeRatio = serviceChargeRatio;
		this.comprehensiveFinancingCostRatio = comprehensiveFinancingCostRatio;
		this.projectProgressDescribe = projectProgressDescribe;
		this.projectFollowupAdvance = projectFollowupAdvance;
		this.problem = problem;
		this.projectDifficulty = projectDifficulty;
		this.solution = solution;
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
		this.typeName = typeName;
		this.categoryName = categoryName;
		this.patternName = patternName;
		this.projectProgressName = projectProgressName;
		this.starttime = starttime;
		this.endtime = endtime;
		this.statusName = statusName;
		this.parentOrgName=parentOrgName;
	}

	// Property accessors

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
	
	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getReportPersonName() {
		return reportPersonName;
	}

	public String getReportPersonId() {
		return reportPersonId;
	}

	public String getReportDate() {
		return reportDate;
	}

	public String getAuditorPersonName() {
		return auditorPersonName;
	}

	public String getAuditorPersonId() {
		return auditorPersonId;
	}

	public String getAuditorDate() {
		return auditorDate;
	}

	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}

	public void setReportPersonId(String reportPersonId) {
		this.reportPersonId = reportPersonId;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public void setAuditorPersonName(String auditorPersonName) {
		this.auditorPersonName = auditorPersonName;
	}

	public void setAuditorPersonId(String auditorPersonId) {
		this.auditorPersonId = auditorPersonId;
	}

	public void setAuditorDate(String auditorDate) {
		this.auditorDate = auditorDate;
	}
	
	public String getOperatorNum() {
		return operatorNum;
	}

	public void setOperatorNum(String operatorNum) {
		this.operatorNum = operatorNum;
	}
	
	public String getStarttime() {
		return starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	public String getTypeName() {
		return typeName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getPatternName() {
		return patternName;
	}

	public String getProjectProgressName() {
		return projectProgressName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setPatternName(String patternName) {
		this.patternName = patternName;
	}

	public void setProjectProgressName(String projectProgressName) {
		this.projectProgressName = projectProgressName;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getOperateOrg() {
		return this.operateOrg;
	}

	public void setOperateOrg(String operateOrg) {
		this.operateOrg = operateOrg;
	}

	public String getOperateOrgname() {
		return this.operateOrgname;
	}

	public void setOperateOrgname(String operateOrgname) {
		this.operateOrgname = operateOrgname;
	}

	public String getFinancingOrg() {
		return this.financingOrg;
	}

	public void setFinancingOrg(String financingOrg) {
		this.financingOrg = financingOrg;
	}

	public String getFinancingOrgname() {
		return this.financingOrgname;
	}

	public void setFinancingOrgname(String financingOrgname) {
		this.financingOrgname = financingOrgname;
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

	public Integer getProjectProgress() {
		return this.projectProgress;
	}

	public void setProjectProgress(Integer projectProgress) {
		this.projectProgress = projectProgress;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getExpectAccountDate() {
		return this.expectAccountDate;
	}

	public void setExpectAccountDate(String expectAccountDate) {
		this.expectAccountDate = expectAccountDate;
	}

	public String getConditionStructure() {
		return this.conditionStructure;
	}

	public void setConditionStructure(String conditionStructure) {
		this.conditionStructure = conditionStructure;
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

	public String getInterestRatio() {
		return this.interestRatio;
	}

	public void setInterestRatio(String interestRatio) {
		this.interestRatio = interestRatio;
	}

	public String getServiceChargeRatio() {
		return this.serviceChargeRatio;
	}

	public void setServiceChargeRatio(String serviceChargeRatio) {
		this.serviceChargeRatio = serviceChargeRatio;
	}

	public String getComprehensiveFinancingCostRatio() {
		return this.comprehensiveFinancingCostRatio;
	}

	public void setComprehensiveFinancingCostRatio(
			String comprehensiveFinancingCostRatio) {
		this.comprehensiveFinancingCostRatio = comprehensiveFinancingCostRatio;
	}

	public String getProjectProgressDescribe() {
		return this.projectProgressDescribe;
	}

	public void setProjectProgressDescribe(String projectProgressDescribe) {
		this.projectProgressDescribe = projectProgressDescribe;
	}

	public String getProjectFollowupAdvance() {
		return this.projectFollowupAdvance;
	}

	public void setProjectFollowupAdvance(String projectFollowupAdvance) {
		this.projectFollowupAdvance = projectFollowupAdvance;
	}

	public String getProblem() {
		return this.problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getProjectDifficulty() {
		return this.projectDifficulty;
	}

	public void setProjectDifficulty(String projectDifficulty) {
		this.projectDifficulty = projectDifficulty;
	}

	public String getSolution() {
		return this.solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
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

	public String getParentorg() {
		return this.parentorg;
	}

	public void setParentorg(String parentorg) {
		this.parentorg = parentorg;
	}
	

	public String getParentOrgName() {
		return parentOrgName;
	}

	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}

	@Override
	public String toString() {
		return "ReportFinancingProjectProgress [id=" + id + ", org=" + org
				+ ", coreOrg=" + coreOrg + ", coreOrgname=" + coreOrgname
				+ ", category=" + category + ", operateOrg=" + operateOrg
				+ ", operateOrgname=" + operateOrgname + ", financingOrg="
				+ financingOrg + ", financingOrgname=" + financingOrgname
				+ ", hypothecationInformation=" + hypothecationInformation
				+ ", pattern=" + pattern + ", financialInstitution="
				+ financialInstitution + ", scale=" + scale + ", term=" + term
				+ ", projectProgress=" + projectProgress + ", type=" + type
				+ ", expectAccountDate=" + expectAccountDate
				+ ", conditionStructure=" + conditionStructure
				+ ", alreadyAccountAmounts=" + alreadyAccountAmounts
				+ ", personLiable=" + personLiable + ", operator=" + operator
				+ ", operatorNum=" + operatorNum + ", interestRatio="
				+ interestRatio + ", serviceChargeRatio=" + serviceChargeRatio
				+ ", comprehensiveFinancingCostRatio="
				+ comprehensiveFinancingCostRatio
				+ ", projectProgressDescribe=" + projectProgressDescribe
				+ ", projectFollowupAdvance=" + projectFollowupAdvance
				+ ", problem=" + problem + ", projectDifficulty="
				+ projectDifficulty + ", solution=" + solution + ", isdel="
				+ isdel + ", status=" + status + ", createPersonName="
				+ createPersonName + ", createPersonId=" + createPersonId
				+ ", createDate=" + createDate + ", lastModifyPersonId="
				+ lastModifyPersonId + ", lastModifyPersonName="
				+ lastModifyPersonName + ", lastModifyDate=" + lastModifyDate
				+ ", reportPersonName=" + reportPersonName
				+ ", reportPersonId=" + reportPersonId + ", reportDate="
				+ reportDate + ", auditorPersonName=" + auditorPersonName
				+ ", auditorPersonId=" + auditorPersonId + ", auditorDate="
				+ auditorDate + ", parentorg=" + parentorg + ", statusName="
				+ statusName + ", typeName=" + typeName + ", categoryName="
				+ categoryName + ", patternName=" + patternName
				+ ", projectProgressName=" + projectProgressName
				+ ", starttime=" + starttime + ", endtime=" + endtime
				+ ", getOperateType=" + getOperateType 
				+ ", parentOrgName=" + parentOrgName+ "]";
	}

	
}