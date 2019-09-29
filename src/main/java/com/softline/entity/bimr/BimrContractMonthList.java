package com.softline.entity.bimr;

import java.math.BigDecimal;

import com.softline.common.Validator;

/**
 * BimrContractMonthList entity. @author MyEclipse Persistence Tools
 */

public class BimrContractMonthList implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer contractMonthId;
	private String contractBelongCompany;
	private String contractSubordinateCompany;
	private String contractDocumentNo;
	private String contractName;
	private String contractTypeText;
	private String contractKindText;
	private String ourContractSubject;
	private String otherContractSubject;
	private Boolean isCom;
	private String trackingMan;
	private BigDecimal contractTotalAmount;
	private Boolean isMajorContract;
	private String contractContent;
	private String contractNode;
	private String legalPerson;
	private String contractSignDate;
	private Boolean isDefault;
	private String ourDefault;
	private String otherDefault;
	private String riskGrade;
	private String riskManage;
	private BigDecimal ourOverPay;
	private BigDecimal ourDefaultPay;
	private BigDecimal otherOverPay;
	private BigDecimal otherDefaultPay;
	private Integer status;
	private Integer version;
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
	
	private String startTime;
	private String endTime;
	private String compName;
	private String compId;
	private String dataauth;
	
	private String projectName;
	private String industrySector;
	private String undertakeDepartment;
	private String contractEffectiveDate;
	private String contractDeadlineDate;
	private String performancePhase;
	private String overdueDefaultClauseAgreement;
	private BigDecimal overduePay1;
	private BigDecimal overduePay2;
	private BigDecimal overduePay3;
	private BigDecimal overduePay4;
	private BigDecimal overduePay5;
	private BigDecimal overdueReceivables1;
	private BigDecimal overdueReceivables2;
	private BigDecimal overdueReceivables3;
	private BigDecimal overdueReceivables4;
	private BigDecimal overdueReceivables5;
	private BigDecimal currentBadLosses;
	private Boolean isInvolving;
	private BigDecimal involvingAccount;
	private Boolean isFocus;
	private String riskDescription;
	private String riskCause;
	private String riskType;
	private String warningDateForm;
	private String riskSteategy;
	private String riskCountermeasures;
	private String riskExcluded;
	private String businessOwner;
	private String legalResponsibilities;
	private String remark;
	// Constructors

	/** default constructor */
	public BimrContractMonthList() {
	}

	/** minimal constructor */
	public BimrContractMonthList(Integer contractMonthId, Boolean isDefault,
			Integer status, Integer version, Integer isDel) {
		this.contractMonthId = contractMonthId;
		this.isDefault = isDefault;
		this.status = status;
		this.version = version;
		this.isDel = isDel;
	}

	/** full constructor */
	

	public BimrContractMonthList(Integer id, Integer contractMonthId,
			String contractBelongCompany, String contractSubordinateCompany,
			String contractDocumentNo, String contractName,
			String contractTypeText, String contractKindText,
			String ourContractSubject, String otherContractSubject,
			Boolean isCom, String trackingMan, BigDecimal contractTotalAmount,
			Boolean isMajorContract, String contractContent,
			String contractNode, String legalPerson, String contractSignDate,
			Boolean isDefault, String ourDefault, String otherDefault,
			String riskGrade, String riskManage, BigDecimal ourOverPay,
			BigDecimal ourDefaultPay, BigDecimal otherOverPay,
			BigDecimal otherDefaultPay, Integer status, Integer version,
			Integer isDel, String createPersonName, String createPersonId,
			String createDate, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate,
			String reportPersonName, String reportPersonId, String reportDate,
			String auditorPersonName, String auditorPersonId,String dataauth,
			String auditorDate, String startTime, String endTime,
			String compName, String compId, String projectName,
			String industrySector, String undertakeDepartment,
			String contractEffectiveDate, String contractDeadlineDate,
			String performancePhase, String overdueDefaultClauseAgreement,
			BigDecimal overduePay1, BigDecimal overduePay2,
			BigDecimal overduePay3, BigDecimal overduePay4,
			BigDecimal overduePay5, BigDecimal overdueReceivables1,
			BigDecimal overdueReceivables2, BigDecimal overdueReceivables3,
			BigDecimal overdueReceivables4, BigDecimal overdueReceivables5,
			BigDecimal currentBadLosses, Boolean isInvolving,
			BigDecimal involvingAccount, Boolean isFocus,
			String riskDescription, String riskCause, String riskType,
			String warningDateForm, String riskSteategy,
			String riskCountermeasures, String riskExcluded,
			String businessOwner, String legalResponsibilities, String remark) {
		super();
		this.id = id;
		this.contractMonthId = contractMonthId;
		this.contractBelongCompany = contractBelongCompany;
		this.contractSubordinateCompany = contractSubordinateCompany;
		this.contractDocumentNo = contractDocumentNo;
		this.contractName = contractName;
		this.contractTypeText = contractTypeText;
		this.contractKindText = contractKindText;
		this.ourContractSubject = ourContractSubject;
		this.otherContractSubject = otherContractSubject;
		this.isCom = isCom;
		this.trackingMan = trackingMan;
		this.contractTotalAmount = contractTotalAmount;
		this.isMajorContract = isMajorContract;
		this.contractContent = contractContent;
		this.contractNode = contractNode;
		this.legalPerson = legalPerson;
		this.contractSignDate = contractSignDate;
		this.isDefault = isDefault;
		this.ourDefault = ourDefault;
		this.otherDefault = otherDefault;
		this.riskGrade = riskGrade;
		this.riskManage = riskManage;
		this.ourOverPay = ourOverPay;
		this.ourDefaultPay = ourDefaultPay;
		this.otherOverPay = otherOverPay;
		this.otherDefaultPay = otherDefaultPay;
		this.status = status;
		this.version = version;
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
		this.startTime = startTime;
		this.endTime = endTime;
		this.compName = compName;
		this.compId = compId;
		this.dataauth = dataauth;
		this.projectName = projectName;
		this.industrySector = industrySector;
		this.undertakeDepartment = undertakeDepartment;
		this.contractEffectiveDate = contractEffectiveDate;
		this.contractDeadlineDate = contractDeadlineDate;
		this.performancePhase = performancePhase;
		this.overdueDefaultClauseAgreement = overdueDefaultClauseAgreement;
		this.overduePay1 = overduePay1;
		this.overduePay2 = overduePay2;
		this.overduePay3 = overduePay3;
		this.overduePay4 = overduePay4;
		this.overduePay5 = overduePay5;
		this.overdueReceivables1 = overdueReceivables1;
		this.overdueReceivables2 = overdueReceivables2;
		this.overdueReceivables3 = overdueReceivables3;
		this.overdueReceivables4 = overdueReceivables4;
		this.overdueReceivables5 = overdueReceivables5;
		this.currentBadLosses = currentBadLosses;
		this.isInvolving = isInvolving;
		this.involvingAccount = involvingAccount;
		this.isFocus = isFocus;
		this.riskDescription = riskDescription;
		this.riskCause = riskCause;
		this.riskType = riskType;
		this.warningDateForm = warningDateForm;
		this.riskSteategy = riskSteategy;
		this.riskCountermeasures = riskCountermeasures;
		this.riskExcluded = riskExcluded;
		this.businessOwner = businessOwner;
		this.legalResponsibilities = legalResponsibilities;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getDataauth() {
		return dataauth;
	}

	public void setDataauth(String dataauth) {
		this.dataauth = dataauth;
	}
	public Integer getContractMonthId() {
		return this.contractMonthId;
	}

	public void setContractMonthId(Integer contractMonthId) {
		this.contractMonthId = contractMonthId;
	}

	public String getContractBelongCompany() {
		return this.contractBelongCompany;
	}

	public void setContractBelongCompany(String contractBelongCompany) {
		this.contractBelongCompany = contractBelongCompany;
	}

	public String getContractSubordinateCompany() {
		return this.contractSubordinateCompany;
	}

	public void setContractSubordinateCompany(String contractSubordinateCompany) {
		this.contractSubordinateCompany = contractSubordinateCompany;
	}

	public String getContractDocumentNo() {
		return this.contractDocumentNo;
	}

	public void setContractDocumentNo(String contractDocumentNo) {
		this.contractDocumentNo = contractDocumentNo;
	}

	public String getContractName() {
		return this.contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractTypeText() {
		return this.contractTypeText;
	}

	public void setContractTypeText(String contractTypeText) {
		this.contractTypeText = contractTypeText;
	}

	public String getContractKindText() {
		return this.contractKindText;
	}

	public void setContractKindText(String contractKindText) {
		this.contractKindText = contractKindText;
	}

	public String getOurContractSubject() {
		return this.ourContractSubject;
	}

	public void setOurContractSubject(String ourContractSubject) {
		this.ourContractSubject = ourContractSubject;
	}

	public String getOtherContractSubject() {
		return this.otherContractSubject;
	}

	public void setOtherContractSubject(String otherContractSubject) {
		this.otherContractSubject = otherContractSubject;
	}

	public Boolean getIsCom() {
		return this.isCom;
	}

	public void setIsCom(Boolean isCom) {
		this.isCom = isCom;
	}

	public String getTrackingMan() {
		return this.trackingMan;
	}

	public void setTrackingMan(String trackingMan) {
		this.trackingMan = trackingMan;
	}

	public BigDecimal getContractTotalAmount() {
		return this.contractTotalAmount;
	}

	public void setContractTotalAmount(BigDecimal contractTotalAmount) {
		this.contractTotalAmount = contractTotalAmount;
	}

	public Boolean getIsMajorContract() {
		return this.isMajorContract;
	}

	public void setIsMajorContract(Boolean isMajorContract) {
		this.isMajorContract = isMajorContract;
	}

	public String getContractContent() {
		return this.contractContent;
	}

	public void setContractContent(String contractContent) {
		this.contractContent = contractContent;
	}

	public String getContractNode() {
		return this.contractNode;
	}

	public void setContractNode(String contractNode) {
		this.contractNode = contractNode;
	}

	public String getLegalPerson() {
		return this.legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getContractSignDate() {
		return this.contractSignDate;
	}

	public void setContractSignDate(String contractSignDate) {
		Validator.checkDateStringFormat(contractSignDate);
		this.contractSignDate = contractSignDate;
	}

	public Boolean getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public String getOurDefault() {
		return this.ourDefault;
	}

	public void setOurDefault(String ourDefault) {
		this.ourDefault = ourDefault;
	}

	public String getOtherDefault() {
		return this.otherDefault;
	}

	public void setOtherDefault(String otherDefault) {
		this.otherDefault = otherDefault;
	}

	public String getRiskGrade() {
		return this.riskGrade;
	}

	public void setRiskGrade(String riskGrade) {
		this.riskGrade = riskGrade;
	}

	public String getRiskManage() {
		return this.riskManage;
	}

	public void setRiskManage(String riskManage) {
		this.riskManage = riskManage;
	}

	public BigDecimal getOurOverPay() {
		return this.ourOverPay;
	}

	public void setOurOverPay(BigDecimal ourOverPay) {
		this.ourOverPay = ourOverPay;
	}

	public BigDecimal getOurDefaultPay() {
		return this.ourDefaultPay;
	}

	public void setOurDefaultPay(BigDecimal ourDefaultPay) {
		this.ourDefaultPay = ourDefaultPay;
	}

	public BigDecimal getOtherOverPay() {
		return this.otherOverPay;
	}

	public void setOtherOverPay(BigDecimal otherOverPay) {
		this.otherOverPay = otherOverPay;
	}

	public BigDecimal getOtherDefaultPay() {
		return this.otherDefaultPay;
	}

	public void setOtherDefaultPay(BigDecimal otherDefaultPay) {
		this.otherDefaultPay = otherDefaultPay;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
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
		Validator.checkDateStringFormat(contractSignDate);
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
		Validator.checkDateStringFormat(contractSignDate);
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
		Validator.checkDateStringFormat(contractSignDate);
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getIndustrySector() {
		return industrySector;
	}

	public void setIndustrySector(String industrySector) {
		this.industrySector = industrySector;
	}

	public String getUndertakeDepartment() {
		return undertakeDepartment;
	}

	public void setUndertakeDepartment(String undertakeDepartment) {
		this.undertakeDepartment = undertakeDepartment;
	}

	public String getContractEffectiveDate() {
		return contractEffectiveDate;
	}

	public void setContractEffectiveDate(String contractEffectiveDate) {
		this.contractEffectiveDate = contractEffectiveDate;
	}

	public String getContractDeadlineDate() {
		return contractDeadlineDate;
	}

	public void setContractDeadlineDate(String contractDeadlineDate) {
		this.contractDeadlineDate = contractDeadlineDate;
	}

	public String getPerformancePhase() {
		return performancePhase;
	}

	public void setPerformancePhase(String performancePhase) {
		this.performancePhase = performancePhase;
	}

	public String getOverdueDefaultClauseAgreement() {
		return overdueDefaultClauseAgreement;
	}

	public void setOverdueDefaultClauseAgreement(
			String overdueDefaultClauseAgreement) {
		this.overdueDefaultClauseAgreement = overdueDefaultClauseAgreement;
	}

	public BigDecimal getOverduePay1() {
		return overduePay1;
	}

	public void setOverduePay1(BigDecimal overduePay1) {
		this.overduePay1 = overduePay1;
	}

	public BigDecimal getOverduePay2() {
		return overduePay2;
	}

	public void setOverduePay2(BigDecimal overduePay2) {
		this.overduePay2 = overduePay2;
	}

	public BigDecimal getOverduePay3() {
		return overduePay3;
	}

	public void setOverduePay3(BigDecimal overduePay3) {
		this.overduePay3 = overduePay3;
	}

	public BigDecimal getOverduePay4() {
		return overduePay4;
	}

	public void setOverduePay4(BigDecimal overduePay4) {
		this.overduePay4 = overduePay4;
	}

	public BigDecimal getOverduePay5() {
		return overduePay5;
	}

	public void setOverduePay5(BigDecimal overduePay5) {
		this.overduePay5 = overduePay5;
	}

	public BigDecimal getOverdueReceivables1() {
		return overdueReceivables1;
	}

	public void setOverdueReceivables1(BigDecimal overdueReceivables1) {
		this.overdueReceivables1 = overdueReceivables1;
	}

	public BigDecimal getOverdueReceivables2() {
		return overdueReceivables2;
	}

	public void setOverdueReceivables2(BigDecimal overdueReceivables2) {
		this.overdueReceivables2 = overdueReceivables2;
	}

	public BigDecimal getOverdueReceivables3() {
		return overdueReceivables3;
	}

	public void setOverdueReceivables3(BigDecimal overdueReceivables3) {
		this.overdueReceivables3 = overdueReceivables3;
	}

	public BigDecimal getOverdueReceivables4() {
		return overdueReceivables4;
	}

	public void setOverdueReceivables4(BigDecimal overdueReceivables4) {
		this.overdueReceivables4 = overdueReceivables4;
	}

	public BigDecimal getOverdueReceivables5() {
		return overdueReceivables5;
	}

	public void setOverdueReceivables5(BigDecimal overdueReceivables5) {
		this.overdueReceivables5 = overdueReceivables5;
	}

	public BigDecimal getCurrentBadLosses() {
		return currentBadLosses;
	}

	public void setCurrentBadLosses(BigDecimal currentBadLosses) {
		this.currentBadLosses = currentBadLosses;
	}

	public Boolean getIsInvolving() {
		return isInvolving;
	}

	public void setIsInvolving(Boolean isInvolving) {
		this.isInvolving = isInvolving;
	}

	public BigDecimal getInvolvingAccount() {
		return involvingAccount;
	}

	public void setInvolvingAccount(BigDecimal involvingAccount) {
		this.involvingAccount = involvingAccount;
	}

	public Boolean getIsFocus() {
		return isFocus;
	}

	public void setIsFocus(Boolean isFocus) {
		this.isFocus = isFocus;
	}

	public String getRiskDescription() {
		return riskDescription;
	}

	public void setRiskDescription(String riskDescription) {
		this.riskDescription = riskDescription;
	}

	public String getRiskCause() {
		return riskCause;
	}

	public void setRiskCause(String riskCause) {
		this.riskCause = riskCause;
	}

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getWarningDateForm() {
		return warningDateForm;
	}

	public void setWarningDateForm(String warningDateForm) {
		this.warningDateForm = warningDateForm;
	}

	public String getRiskSteategy() {
		return riskSteategy;
	}

	public void setRiskSteategy(String riskSteategy) {
		this.riskSteategy = riskSteategy;
	}

	public String getRiskCountermeasures() {
		return riskCountermeasures;
	}

	public void setRiskCountermeasures(String riskCountermeasures) {
		this.riskCountermeasures = riskCountermeasures;
	}

	public String getRiskExcluded() {
		return riskExcluded;
	}

	public void setRiskExcluded(String riskExcluded) {
		this.riskExcluded = riskExcluded;
	}

	public String getBusinessOwner() {
		return businessOwner;
	}

	public void setBusinessOwner(String businessOwner) {
		this.businessOwner = businessOwner;
	}

	public String getLegalResponsibilities() {
		return legalResponsibilities;
	}

	public void setLegalResponsibilities(String legalResponsibilities) {
		this.legalResponsibilities = legalResponsibilities;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}