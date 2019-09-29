package com.softline.entity.bimr;

import java.math.BigDecimal;

/**
 * BimrCriminalcaseWeek entity. @author MyEclipse Persistence Tools
 */

public class BimrCriminalcaseWeek implements java.io.Serializable {

	// Fields

	private Integer id;
	private String caseNum;
	private String caseCategory;
	private String version;
	private String approvalState;
	private String isHistory;
    private String vcCompanyId;
    private String vcCompanyName;
	private String suspect;
	private String post;
	private String belongCompany;
	private String accusation;
	private String baseMessage;
	private BigDecimal amount;
	private String latestProgress;
	private String province;
	private String type;
	private String caseHandlingUnit;
	private String caseHandlingPerson;
	private String groupCompany;
	private String lawWork;
	private String isAccountability;
	private String isAnalysisReason;
	private String isDel;
	private String createPersonId;
	private String createPersonName;
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
    private String mainId;
    private String caseDate;
    private String isLastest;
    private String dataauth;
    private String vcOrganID;
    private String remark1;
    private String remark2;
    private String remark3;
    private String remark4;
    //报案书
    private String reportment;
    //判决书
    private String judgment;
    //其它
    private String otherFile;
    //是否是清案遗留案件 1 是 0 否
    private String isLeftoverCases;
    //是否是新增案件（回头看） 1 是 0 否
    private String isNewadd;
    //是否因人员优化工作引发案件 1 是 0 否
    private String isStaffOptimization;
    //案件成因（物流）
    private String analysisCause;
    //问责进展情况（含问责人数及名单）（实业）
    private String accountabilityResults;
    //协助人（物流）
    private String assistingPeople;
    //案件移交公文号
    private String transferredNumber;
    //结案时间及结案报告公文号（实业）
    private String closingNumber;
    //案件规划时间
    private String casePlanDate;
    //案龄
    private Integer caseAge;
  //问责状态
    private Integer accountabilityStatus;
    //拟问责建议（若无需问责，请说明具体原因）（实业）
    private String accountabilitySuggest;
	// Constructors

	/** default constructor */
	public BimrCriminalcaseWeek() {
	}
	/** full constructor */
	public BimrCriminalcaseWeek(Integer id, String caseNum,
			String caseCategory, String version, String approvalState,
			String isHistory, String vcCompanyId, String vcCompanyName,
			String suspect, String post, String belongCompany,
			String accusation, String baseMessage, BigDecimal amount,
			String latestProgress, String province, String type,
			String caseHandlingUnit, String caseHandlingPerson,
			String groupCompany, String lawWork, String isAccountability,
			String isAnalysisReason, String isDel, String createPersonId,
			String createPersonName, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate, String mainId,
			String caseDate, String isLastest, String dataauth,
			String vcOrganID, String remark1, String remark2, String remark3,
			String remark4, String reportment, String judgment,
			String otherFile,String isLeftoverCases,String isNewadd,String isStaffOptimization, String analysisCause,
			String accountabilityResults, String assistingPeople,
			String transferredNumber, String closingNumber,String casePlanDate,Integer caseAge,Integer accountabilityStatus,String accountabilitySuggest) {
		super();
		this.id = id;
		this.caseNum = caseNum;
		this.caseCategory = caseCategory;
		this.version = version;
		this.approvalState = approvalState;
		this.isHistory = isHistory;
		this.vcCompanyId = vcCompanyId;
		this.vcCompanyName = vcCompanyName;
		this.suspect = suspect;
		this.post = post;
		this.belongCompany = belongCompany;
		this.accusation = accusation;
		this.baseMessage = baseMessage;
		this.amount = amount;
		this.latestProgress = latestProgress;
		this.province = province;
		this.type = type;
		this.caseHandlingUnit = caseHandlingUnit;
		this.caseHandlingPerson = caseHandlingPerson;
		this.groupCompany = groupCompany;
		this.lawWork = lawWork;
		this.isAccountability = isAccountability;
		this.isAnalysisReason = isAnalysisReason;
		this.isDel = isDel;
		this.createPersonId = createPersonId;
		this.createPersonName = createPersonName;
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
		this.mainId = mainId;
		this.caseDate = caseDate;
		this.isLastest = isLastest;
		this.dataauth = dataauth;
		this.vcOrganID = vcOrganID;
		this.remark1 = remark1;
		this.remark2 = remark2;
		this.remark3 = remark3;
		this.remark4 = remark4;
		this.reportment = reportment;
		this.judgment = judgment;
		this.otherFile = otherFile;
		this.isLeftoverCases=isLeftoverCases;
	    this.isNewadd=isNewadd;
	    this.isStaffOptimization = isStaffOptimization;
		this.analysisCause = analysisCause;
		this.accountabilityResults = accountabilityResults;
		this.assistingPeople = assistingPeople;
		this.transferredNumber = transferredNumber;
		this.closingNumber = closingNumber;
		this.casePlanDate=casePlanDate;
		this.caseAge=caseAge;
		this.accountabilityResults=accountabilityResults;
		this.accountabilitySuggest=accountabilitySuggest;
	}

	
	
	
	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public String getVcOrganID() {
		return vcOrganID;
	}

	public void setVcOrganID(String vcOrganID) {
		this.vcOrganID = vcOrganID;
	}

	public String getDataauth() {
		return dataauth;
	}

	public void setDataauth(String dataauth) {
		this.dataauth = dataauth;
	}

	public String getIsLastest() {
		return isLastest;
	}

	public void setIsLastest(String isLastest) {
		this.isLastest = isLastest;
	}

	public String getVcCompanyId() {
		return vcCompanyId;
	}

	public void setVcCompanyId(String vcCompanyId) {
		this.vcCompanyId = vcCompanyId;
	}

	public String getVcCompanyName() {
		return vcCompanyName;
	}

	public void setVcCompanyName(String vcCompanyName) {
		this.vcCompanyName = vcCompanyName;
	}

	public String getCaseDate() {
		return caseDate;
	}

	public void setCaseDate(String caseDate) {
		this.caseDate = caseDate;
	}

	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCaseNum() {
		return this.caseNum;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	public String getCaseCategory() {
		return this.caseCategory;
	}

	public void setCaseCategory(String caseCategory) {
		this.caseCategory = caseCategory;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getApprovalState() {
		return this.approvalState;
	}

	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}

	public String getIsHistory() {
		return this.isHistory;
	}

	public void setIsHistory(String isHistory) {
		this.isHistory = isHistory;
	}

	public String getSuspect() {
		return this.suspect;
	}

	public void setSuspect(String suspect) {
		this.suspect = suspect;
	}

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getBelongCompany() {
		return this.belongCompany;
	}

	public void setBelongCompany(String belongCompany) {
		this.belongCompany = belongCompany;
	}

	public String getAccusation() {
		return this.accusation;
	}

	public void setAccusation(String accusation) {
		this.accusation = accusation;
	}

	public String getBaseMessage() {
		return this.baseMessage;
	}

	public void setBaseMessage(String baseMessage) {
		this.baseMessage = baseMessage;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getLatestProgress() {
		return this.latestProgress;
	}

	public void setLatestProgress(String latestProgress) {
		this.latestProgress = latestProgress;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCaseHandlingUnit() {
		return this.caseHandlingUnit;
	}

	public void setCaseHandlingUnit(String caseHandlingUnit) {
		this.caseHandlingUnit = caseHandlingUnit;
	}

	public String getCaseHandlingPerson() {
		return this.caseHandlingPerson;
	}

	public void setCaseHandlingPerson(String caseHandlingPerson) {
		this.caseHandlingPerson = caseHandlingPerson;
	}

	public String getGroupCompany() {
		return this.groupCompany;
	}

	public void setGroupCompany(String groupCompany) {
		this.groupCompany = groupCompany;
	}

	public String getLawWork() {
		return this.lawWork;
	}

	public void setLawWork(String lawWork) {
		this.lawWork = lawWork;
	}

	public String getIsAccountability() {
		return this.isAccountability;
	}

	public void setIsAccountability(String isAccountability) {
		this.isAccountability = isAccountability;
	}

	public String getIsAnalysisReason() {
		return this.isAnalysisReason;
	}

	public void setIsAnalysisReason(String isAnalysisReason) {
		this.isAnalysisReason = isAnalysisReason;
	}

	public String getIsDel() {
		return this.isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getCreatePersonId() {
		return this.createPersonId;
	}

	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}

	public String getCreatePersonName() {
		return this.createPersonName;
	}

	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
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

	

	public String getReportment() {
		return reportment;
	}

	public void setReportment(String reportment) {
		this.reportment = reportment;
	}

	public String getJudgment() {
		return judgment;
	}

	public void setJudgment(String judgment) {
		this.judgment = judgment;
	}

	public String getOtherFile() {
		return otherFile;
	}

	public void setOtherFile(String otherFile) {
		this.otherFile = otherFile;
	}
	public String getAnalysisCause() {
		return analysisCause;
	}
	public void setAnalysisCause(String analysisCause) {
		this.analysisCause = analysisCause;
	}
	public String getAccountabilityResults() {
		return accountabilityResults;
	}
	public void setAccountabilityResults(String accountabilityResults) {
		this.accountabilityResults = accountabilityResults;
	}
	public String getAssistingPeople() {
		return assistingPeople;
	}
	public void setAssistingPeople(String assistingPeople) {
		this.assistingPeople = assistingPeople;
	}
	public String getTransferredNumber() {
		return transferredNumber;
	}
	public void setTransferredNumber(String transferredNumber) {
		this.transferredNumber = transferredNumber;
	}
	public String getClosingNumber() {
		return closingNumber;
	}
	public void setClosingNumber(String closingNumber) {
		this.closingNumber = closingNumber;
	}
	public String getIsLeftoverCases() {
		return isLeftoverCases;
	}
	public void setIsLeftoverCases(String isLeftoverCases) {
		this.isLeftoverCases = isLeftoverCases;
	}
	public String getIsNewadd() {
		return isNewadd;
	}
	public void setIsNewadd(String isNewadd) {
		this.isNewadd = isNewadd;
	}
	public String getIsStaffOptimization() {
		return isStaffOptimization;
	}
	public void setIsStaffOptimization(String isStaffOptimization) {
		this.isStaffOptimization = isStaffOptimization;
	}
	public String getCasePlanDate() {
		return casePlanDate;
	}
	public void setCasePlanDate(String casePlanDate) {
		this.casePlanDate = casePlanDate;
	}
	public Integer getCaseAge() {
		return caseAge;
	}
	public void setCaseAge(Integer caseAge) {
		this.caseAge = caseAge;
	}
	public Integer getAccountabilityStatus() {
		return accountabilityStatus;
	}
	public void setAccountabilityStatus(Integer accountabilityStatus) {
		this.accountabilityStatus = accountabilityStatus;
	}
	public String getAccountabilitySuggest() {
		return accountabilitySuggest;
	}
	public void setAccountabilitySuggest(String accountabilitySuggest) {
		this.accountabilitySuggest = accountabilitySuggest;
	}

	
	
	
}