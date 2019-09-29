package com.softline.entity.bimr;

import java.math.BigDecimal;



/**
 * BimrCivilcaseWeek entity. @author MyEclipse Persistence Tools
 */

public class BimrCivilcaseWeek  implements java.io.Serializable { 

     private Integer id;
     private String caseNum;
     private String caseCategory;
     private String version;
     private String approvalState;
     private String isHistory;
     private String vcCompanyId;
     private String vcCompanyName;
     private String plaintiff;
     private String defendant;
     private String litigation;
     private String admissibleCourt;
     private String province;
     private String judge;
     private BigDecimal amount;
     private String type;
     private String reason;
     private String baseMessage;
     private String isMajorCase;
     private String mediatingStage;
     private String cooperationDate;
     private String disputeDate;
     private String dealDisputeProcess;
     private String caseCause;
     private String caseDate;
     private String lawWork;
     private String responsiblePerson;
     private String responsiblePersonId;
     private String externalLaws;
     private String lawsAmount;
     private BigDecimal payAmount;
     private String foreignLaw;
     private String predictionResluts;
     private String planTime;
     private String expectedResults;
     private String isAccountability;
     private String isAnalysis;
     private String verdictResult;
     private BigDecimal verdictAmount;
     private BigDecimal implementMoney;
     private BigDecimal saveLoss;
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
     private String isLastest;
     private String vcOrganID;
     private String dataauth;
     private String lastProgress;
     private String remark1;
     private String remark2;
     private String remark3;
     private String remark4;

     //起诉状
     private String indictment;
     //调解书/判决书
     private String judgment;
     //其它
     private String otherFile;
     //是否是清案遗留案件 1 是 0 否
     private String isLeftoverCases;
     //是否是新增案件（回头看） 1 是 0 否
     private String isNewadd;
     //是否因人员优化工作引发案件 1 是 0 否
     private String isStaffOptimization;
     //案件成因(实业)
     private String analysisCause;
     //问责进展情况（含问责人数及名单）（实业）
     private String accountabilityResults;
     //协助人(实业)
     private String assistingPeople;
     //双方保全情况(实业)
     private String bothPreserveSituation;
     //案龄
     private Integer caseAge;
     //办案思路、调处重点及工作计划（实业）
     private String caseThoughtFocalPoint;
     //裁判/调解/和解主文（物流）
     private String reconciliationDocument;
     //案件规划时间
     private String casePlanDate;
     //案件移交公文号
     private String transferredNumber;
     //案件结案公文号 
     private String closingNumber;
     //问责状态
     private Integer accountabilityStatus;
     //拟问责建议（若无需问责，请说明具体原因）（实业）
     private String accountabilitySuggest;
     //一案双处实施情况 （实业）
     private String twoCasesImplementation;
     public BimrCivilcaseWeek() {
     }

     public BimrCivilcaseWeek(String caseNum, String caseCategory, String version, String approvalState, String isHistory, 
    		 String vcCompanyId, String vcCompanyName, String plaintiff, String defendant, String litigation, String admissibleCourt, 
    		 String province, String judge, BigDecimal amount, String type, String reason, String baseMessage, String isMajorCase, 
    		 String mediatingStage, String cooperationDate, String disputeDate, String dealDisputeProcess, String caseCause, String caseDate, 
    		 String lawWork, String responsiblePerson, String responsiblePersonId, String externalLaws, String lawsAmount, BigDecimal payAmount, String foreignLaw, 
    		 String predictionResluts, String planTime, String expectedResults, String isAccountability, String isAnalysis, String verdictResult, 
    		 BigDecimal verdictAmount, BigDecimal implementMoney, BigDecimal saveLoss, String createPersonId, String createPersonName, 
    		 String createDate, String lastModifyPersonId, String lastModifyPersonName, String lastModifyDate, String reportPersonName, 
    		 String reportPersonId, String reportDate, String auditorPersonName, String auditorPersonId, String auditorDate, String isDel, 
    		 String mainId, String isLastest, String vcOrganID, String dataauth, String lastProgress, String remark1, String remark2,
    		 String remark3, String remark4,String indictment,String judgment,String otherFile,String isLeftoverCases,String isNewadd,String isStaffOptimization,
    		 String analysisCause,String accountabilityResults,String assistingPeople,String bothPreserveSituation,Integer caseAge,String caseThoughtFocalPoint,String reconciliationDocument,String casePlanDate,
    		 String transferredNumber,String closingNumber,Integer accountabilityStatus,String accountabilitySuggest,String twoCasesImplementation) {
        this.caseNum = caseNum;
        this.caseCategory = caseCategory;
        this.version = version;
        this.approvalState = approvalState;
        this.isHistory = isHistory;
        this.vcCompanyId = vcCompanyId;
        this.vcCompanyName = vcCompanyName;
        this.plaintiff = plaintiff;
        this.defendant = defendant;
        this.litigation = litigation;
        this.admissibleCourt = admissibleCourt;
        this.province = province;
        this.judge = judge;
        this.amount = amount;
        this.type = type;
        this.reason = reason;
        this.baseMessage = baseMessage;
        this.isMajorCase = isMajorCase;
        this.mediatingStage = mediatingStage;
        this.cooperationDate = cooperationDate;
        this.disputeDate = disputeDate;
        this.dealDisputeProcess = dealDisputeProcess;
        this.caseCause = caseCause;
        this.caseDate = caseDate;
        this.lawWork = lawWork;
        this.responsiblePerson = responsiblePerson;
        this.responsiblePersonId = responsiblePersonId;
        this.externalLaws = externalLaws;
        this.lawsAmount = lawsAmount;
        this.payAmount = payAmount;
        this.foreignLaw = foreignLaw;
        this.predictionResluts = predictionResluts;
        this.planTime = planTime;
        this.expectedResults = expectedResults;
        this.isAccountability = isAccountability;
        this.isAnalysis = isAnalysis;
        this.verdictResult = verdictResult;
        this.verdictAmount = verdictAmount;
        this.implementMoney = implementMoney;
        this.saveLoss = saveLoss;
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
        this.isDel = isDel;
        this.mainId = mainId;
        this.isLastest = isLastest;
        this.vcOrganID = vcOrganID;
        this.dataauth = dataauth;
        this.lastProgress = lastProgress;
        this.remark1 = remark1;
        this.remark2 = remark2;
        this.remark3 = remark3;
        this.remark4 = remark4;
        this.indictment=indictment;
        this.judgment=judgment;
        this.otherFile=otherFile;
        this.isLeftoverCases=isLeftoverCases;
        this.isNewadd=isNewadd;
        this.isStaffOptimization = isStaffOptimization;
        this.analysisCause=analysisCause;
        this.accountabilityResults=accountabilityResults;
        this.assistingPeople=assistingPeople;
        this.bothPreserveSituation=bothPreserveSituation;
        this.caseAge=caseAge;
        this.caseThoughtFocalPoint=caseThoughtFocalPoint;
        this.reconciliationDocument=reconciliationDocument;
        this.casePlanDate=casePlanDate;
        this.transferredNumber=transferredNumber;
        this.closingNumber=closingNumber;
        this.accountabilityStatus=accountabilityStatus;
        this.accountabilitySuggest=accountabilitySuggest;
        this.twoCasesImplementation=twoCasesImplementation;
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

	public String getLastProgress() {
		return lastProgress;
	}

	public void setLastProgress(String lastProgress) {
		this.lastProgress = lastProgress;
	}

	public String getResponsiblePersonId() {
		return responsiblePersonId;
	}

	public void setResponsiblePersonId(String responsiblePersonId) {
		this.responsiblePersonId = responsiblePersonId;
	}

	public String getDataauth() {
		return dataauth;
	}

	public void setDataauth(String dataauth) {
		this.dataauth = dataauth;
	}

	public String getVcOrganID() {
		return vcOrganID;
	}

	public void setVcOrganID(String vcOrganID) {
		this.vcOrganID = vcOrganID;
	}

	public String getIsLastest() {
		return isLastest;
	}

	public void setIsLastest(String isLastest) {
		this.isLastest = isLastest;
	}

	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	public String getCaseCategory() {
		return caseCategory;
	}

	public void setCaseCategory(String caseCategory) {
		this.caseCategory = caseCategory;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getApprovalState() {
		return approvalState;
	}

	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}

	public String getIsHistory() {
		return isHistory;
	}

	public void setIsHistory(String isHistory) {
		this.isHistory = isHistory;
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

	public String getPlaintiff() {
		return plaintiff;
	}

	public void setPlaintiff(String plaintiff) {
		this.plaintiff = plaintiff;
	}

	public String getDefendant() {
		return defendant;
	}

	public void setDefendant(String defendant) {
		this.defendant = defendant;
	}

	public String getLitigation() {
		return litigation;
	}

	public void setLitigation(String litigation) {
		this.litigation = litigation;
	}

	public String getAdmissibleCourt() {
		return admissibleCourt;
	}

	public void setAdmissibleCourt(String admissibleCourt) {
		this.admissibleCourt = admissibleCourt;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getJudge() {
		return judge;
	}

	public void setJudge(String judge) {
		this.judge = judge;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getBaseMessage() {
		return baseMessage;
	}

	public void setBaseMessage(String baseMessage) {
		this.baseMessage = baseMessage;
	}

	public String getIsMajorCase() {
		return isMajorCase;
	}

	public void setIsMajorCase(String isMajorCase) {
		this.isMajorCase = isMajorCase;
	}

	public String getMediatingStage() {
		return mediatingStage;
	}

	public void setMediatingStage(String mediatingStage) {
		this.mediatingStage = mediatingStage;
	}

	public String getCooperationDate() {
		return cooperationDate;
	}

	public void setCooperationDate(String cooperationDate) {
		this.cooperationDate = cooperationDate;
	}

	public String getDisputeDate() {
		return disputeDate;
	}

	public void setDisputeDate(String disputeDate) {
		this.disputeDate = disputeDate;
	}

	public String getDealDisputeProcess() {
		return dealDisputeProcess;
	}

	public void setDealDisputeProcess(String dealDisputeProcess) {
		this.dealDisputeProcess = dealDisputeProcess;
	}

	public String getCaseCause() {
		return caseCause;
	}

	public void setCaseCause(String caseCause) {
		this.caseCause = caseCause;
	}

	public String getCaseDate() {
		return caseDate;
	}

	public void setCaseDate(String caseDate) {
		this.caseDate = caseDate;
	}

	public String getLawWork() {
		return lawWork;
	}

	public void setLawWork(String lawWork) {
		this.lawWork = lawWork;
	}

	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public String getExternalLaws() {
		return externalLaws;
	}

	public void setExternalLaws(String externalLaws) {
		this.externalLaws = externalLaws;
	}

	public String getLawsAmount() {
		return lawsAmount;
	}

	public void setLawsAmount(String lawsAmount) {
		this.lawsAmount = lawsAmount;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public String getForeignLaw() {
		return foreignLaw;
	}

	public void setForeignLaw(String foreignLaw) {
		this.foreignLaw = foreignLaw;
	}

	public String getPredictionResluts() {
		return predictionResluts;
	}

	public void setPredictionResluts(String predictionResluts) {
		this.predictionResluts = predictionResluts;
	}

	public String getPlanTime() {
		return planTime;
	}

	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}

	public String getExpectedResults() {
		return expectedResults;
	}

	public void setExpectedResults(String expectedResults) {
		this.expectedResults = expectedResults;
	}

	public String getIsAccountability() {
		return isAccountability;
	}

	public void setIsAccountability(String isAccountability) {
		this.isAccountability = isAccountability;
	}

	public String getIsAnalysis() {
		return isAnalysis;
	}

	public void setIsAnalysis(String isAnalysis) {
		this.isAnalysis = isAnalysis;
	}

	public String getVerdictResult() {
		return verdictResult;
	}

	public void setVerdictResult(String verdictResult) {
		this.verdictResult = verdictResult;
	}

	public BigDecimal getVerdictAmount() {
		return verdictAmount;
	}

	public void setVerdictAmount(BigDecimal verdictAmount) {
		this.verdictAmount = verdictAmount;
	}

	public BigDecimal getImplementMoney() {
		return implementMoney;
	}

	public void setImplementMoney(BigDecimal implementMoney) {
		this.implementMoney = implementMoney;
	}

	public BigDecimal getSaveLoss() {
		return saveLoss;
	}

	public void setSaveLoss(BigDecimal saveLoss) {
		this.saveLoss = saveLoss;
	}

	public String getCreatePersonId() {
		return createPersonId;
	}

	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}

	public String getCreatePersonName() {
		return createPersonName;
	}

	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
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

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getIndictment() {
		return indictment;
	}

	public void setIndictment(String indictment) {
		this.indictment = indictment;
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

	public String getBothPreserveSituation() {
		return bothPreserveSituation;
	}

	public void setBothPreserveSituation(String bothPreserveSituation) {
		this.bothPreserveSituation = bothPreserveSituation;
	}

	public Integer getCaseAge() {
		return caseAge;
	}

	public void setCaseAge(Integer caseAge) {
		this.caseAge = caseAge;
	}

	public String getCaseThoughtFocalPoint() {
		return caseThoughtFocalPoint;
	}

	public void setCaseThoughtFocalPoint(String caseThoughtFocalPoint) {
		this.caseThoughtFocalPoint = caseThoughtFocalPoint;
	}

	public String getReconciliationDocument() {
		return reconciliationDocument;
	}

	public void setReconciliationDocument(String reconciliationDocument) {
		this.reconciliationDocument = reconciliationDocument;
	}

	public String getCasePlanDate() {
		return casePlanDate;
	}

	public void setCasePlanDate(String casePlanDate) {
		this.casePlanDate = casePlanDate;
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

	public String getTwoCasesImplementation() {
		return twoCasesImplementation;
	}

	public void setTwoCasesImplementation(String twoCasesImplementation) {
		this.twoCasesImplementation = twoCasesImplementation;
	}
	
	
}