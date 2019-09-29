package com.softline.entity;

/**
 * RiskControlContract entity.
 */

public class RiskControlContract implements java.io.Serializable {
	
	// Fields
	private Integer id;
	private Integer contractBelongCompany;
	private Integer contractSubordinateCompany;
	private String contractDocumentNo;
	private String contractName;
	private Integer contractType;
	private String ourContractSubject;
	private String otherContractSubject;
	private String trackingMan;
	private String contractTotalAmount = "0";
	private Integer isMajorContract;
	private String contractContent;
	private String contractNode;
	private String legalPerson;
	private String contractSignDate;
	private Integer isDefault;
	private String ourDefault;
	private String otherDefault;
	private String riskGrade;
	private String riskManage;
	private String ourOverPay = "0";
	private String ourDefaultPay = "0";
	private String otherOverPay = "0";
	private String otherDefaultPay = "0";
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
	
	private String contractBelongCompanyName;
	private String contractSubordinateCompanyName;
	private String contractTypeName;
	private String statusName;
	
	public RiskControlContract() {
		super();
	}

	public RiskControlContract(Integer id, Integer contractBelongCompany, Integer contractSubordinateCompany, String contractDocumentNo, 
			String contractName, Integer contractType,  String ourContractSubject, String otherContractSubject, 
			String trackingMan, String contractTotalAmount, Integer isMajorContract, String contractContent,
			String contractNode, String legalPerson, String contractSignDate, Integer isDefault, String ourDefault,
			String otherDefault, String riskGrade, String riskManage, String ourOverPay, String ourDefaultPay,
			String otherOverPay, String otherDefaultPay, Integer status, Integer isDel, String createPersonName, 
			String createPersonId, String createDate, String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName, String reportPersonId, String reportDate, 
			String auditorPersonName, String auditorPersonId, String auditorDate) {
		super();
		this.id = id;
		this.contractBelongCompany = contractBelongCompany;
		this.contractSubordinateCompany = contractSubordinateCompany;
		this.contractDocumentNo = contractDocumentNo;
		this.contractName = contractName;
		this.contractType = contractType;
		this.ourContractSubject = ourContractSubject;
		this.otherContractSubject = otherContractSubject;
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

	public Integer getContractBelongCompany() {
		return contractBelongCompany;
	}

	public void setContractBelongCompany(Integer contractBelongCompany) {
		this.contractBelongCompany = contractBelongCompany;
	}

	public Integer getContractSubordinateCompany() {
		return contractSubordinateCompany;
	}

	public void setContractSubordinateCompany(Integer contractSubordinateCompany) {
		this.contractSubordinateCompany = contractSubordinateCompany;
	}

	public String getContractDocumentNo() {
		return contractDocumentNo;
	}

	public void setContractDocumentNo(String contractDocumentNo) {
		this.contractDocumentNo = contractDocumentNo;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public Integer getContractType() {
		return contractType;
	}

	public void setContractType(Integer contractType) {
		this.contractType = contractType;
	}

	public String getOurContractSubject() {
		return ourContractSubject;
	}

	public void setOurContractSubject(String ourContractSubject) {
		this.ourContractSubject = ourContractSubject;
	}

	public String getOtherContractSubject() {
		return otherContractSubject;
	}

	public void setOtherContractSubject(String otherContractSubject) {
		this.otherContractSubject = otherContractSubject;
	}

	public String getTrackingMan() {
		return trackingMan;
	}

	public void setTrackingMan(String trackingMan) {
		this.trackingMan = trackingMan;
	}

	public String getContractTotalAmount() {
		return contractTotalAmount;
	}

	public void setContractTotalAmount(String contractTotalAmount) {
		this.contractTotalAmount = contractTotalAmount;
	}

	public Integer getIsMajorContract() {
		return isMajorContract;
	}

	public void setIsMajorContract(Integer isMajorContract) {
		this.isMajorContract = isMajorContract;
	}

	public String getContractContent() {
		return contractContent;
	}

	public void setContractContent(String contractContent) {
		this.contractContent = contractContent;
	}

	public String getContractNode() {
		return contractNode;
	}

	public void setContractNode(String contractNode) {
		this.contractNode = contractNode;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getContractSignDate() {
		return contractSignDate;
	}

	public void setContractSignDate(String contractSignDate) {
		this.contractSignDate = contractSignDate;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public String getOurDefault() {
		return ourDefault;
	}

	public void setOurDefault(String ourDefault) {
		this.ourDefault = ourDefault;
	}

	public String getOtherDefault() {
		return otherDefault;
	}

	public void setOtherDefault(String otherDefault) {
		this.otherDefault = otherDefault;
	}

	public String getRiskGrade() {
		return riskGrade;
	}

	public void setRiskGrade(String riskGrade) {
		this.riskGrade = riskGrade;
	}

	public String getRiskManage() {
		return riskManage;
	}

	public void setRiskManage(String riskManage) {
		this.riskManage = riskManage;
	}

	public String getOurOverPay() {
		return ourOverPay;
	}

	public void setOurOverPay(String ourOverPay) {
		this.ourOverPay = ourOverPay;
	}

	public String getOurDefaultPay() {
		return ourDefaultPay;
	}

	public void setOurDefaultPay(String ourDefaultPay) {
		this.ourDefaultPay = ourDefaultPay;
	}

	public String getOtherOverPay() {
		return otherOverPay;
	}

	public void setOtherOverPay(String otherOverPay) {
		this.otherOverPay = otherOverPay;
	}

	public String getOtherDefaultPay() {
		return otherDefaultPay;
	}

	public void setOtherDefaultPay(String otherDefaultPay) {
		this.otherDefaultPay = otherDefaultPay;
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

	public String getContractBelongCompanyName() {
		return contractBelongCompanyName;
	}

	public void setContractBelongCompanyName(String contractBelongCompanyName) {
		this.contractBelongCompanyName = contractBelongCompanyName;
	}

	public String getContractSubordinateCompanyName() {
		return contractSubordinateCompanyName;
	}

	public void setContractSubordinateCompanyName(
			String contractSubordinateCompanyName) {
		this.contractSubordinateCompanyName = contractSubordinateCompanyName;
	}

	public String getContractTypeName() {
		return contractTypeName;
	}

	public void setContractTypeName(String contractTypeName) {
		this.contractTypeName = contractTypeName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
}