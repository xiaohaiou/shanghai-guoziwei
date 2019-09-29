package com.softline.entity.financing;

/**
 * ReportFinancingShare entity. @author MyEclipse Persistence Tools
 */

public class ReportFinancingShare implements java.io.Serializable {

	// Fields

	private Integer id;
	private String orgname;	//业态公司
	private String coreOrg;
	private String coreOrgname;	//业态公司
	private Integer category;
	private String hypothecationInformation;
	private String pattern;
	private String financialInstitution;
	private String scale;
	private String term;
	private String comprehensiveFinancingCostRatio;
	private String financingStructure;
	private String purposeToIntroduce;
	private String specificProgress;
	private String spSuggestions;
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
	
	private String interestRatio;
	private String preConsultantFee;
	private Integer projectProgress;
	//formula
	private String statusName;
	private String typeName;
	private String categoryName;
	private String patternName;
	private String projectProgressName;
	private String parentOrgName;

	/** default constructor */
	public ReportFinancingShare() {
	}

	/** minimal constructor */
	public ReportFinancingShare(String coreOrg, Integer isdel, Integer status) {
		this.coreOrg = coreOrg;
		this.isdel = isdel;
		this.status = status;
	}


	// Property accessors

	public ReportFinancingShare(Integer id, String orgname, String coreOrg,
			String coreOrgname, Integer category,
			String hypothecationInformation, String pattern,
			String financialInstitution, String scale, String term,
			String comprehensiveFinancingCostRatio, String financingStructure,
			String purposeToIntroduce, String specificProgress,
			String spSuggestions, String alreadyAccountAmounts,
			String personLiable, String operator, String operatorNum,
			Integer isdel, Integer status, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate, String parentorg,
			String interestRatio, String preConsultantFee, String statusName,
			String typeName, String categoryName, String patternName,Integer projectProgress,
			String projectProgressName,String parentOrgName) {
		super();
		this.id = id;
		this.orgname = orgname;
		this.coreOrg = coreOrg;
		this.coreOrgname = coreOrgname;
		this.category = category;
		this.hypothecationInformation = hypothecationInformation;
		this.pattern = pattern;
		this.financialInstitution = financialInstitution;
		this.scale = scale;
		this.term = term;
		this.comprehensiveFinancingCostRatio = comprehensiveFinancingCostRatio;
		this.financingStructure = financingStructure;
		this.purposeToIntroduce = purposeToIntroduce;
		this.specificProgress = specificProgress;
		this.spSuggestions = spSuggestions;
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
		this.interestRatio = interestRatio;
		this.preConsultantFee = preConsultantFee;
		this.statusName = statusName;
		this.typeName = typeName;
		this.categoryName = categoryName;
		this.patternName = patternName;
		this.projectProgress = projectProgress;
		this.projectProgressName = projectProgressName;
		this.parentOrgName=parentOrgName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPatternName() {
		return patternName;
	}

	public void setPatternName(String patternName) {
		this.patternName = patternName;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public String getFinancingStructure() {
		return this.financingStructure;
	}

	public void setFinancingStructure(String financingStructure) {
		this.financingStructure = financingStructure;
	}

	public String getPurposeToIntroduce() {
		return this.purposeToIntroduce;
	}

	public void setPurposeToIntroduce(String purposeToIntroduce) {
		this.purposeToIntroduce = purposeToIntroduce;
	}

	public String getSpecificProgress() {
		return this.specificProgress;
	}

	public void setSpecificProgress(String specificProgress) {
		this.specificProgress = specificProgress;
	}

	public String getSpSuggestions() {
		return this.spSuggestions;
	}

	public void setSpSuggestions(String spSuggestions) {
		this.spSuggestions = spSuggestions;
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

	public String getInterestRatio() {
		return interestRatio;
	}

	public void setInterestRatio(String interestRatio) {
		this.interestRatio = interestRatio;
	}

	public String getPreConsultantFee() {
		return preConsultantFee;
	}

	public void setPreConsultantFee(String preConsultantFee) {
		this.preConsultantFee = preConsultantFee;
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