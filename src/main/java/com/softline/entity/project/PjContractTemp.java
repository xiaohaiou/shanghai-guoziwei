package com.softline.entity.project;

/**
 * PjContractTemp entity. @author MyEclipse Persistence Tools
 */

public class PjContractTemp implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer pjId;
	private String pcNo;
	private String pcName;
	private String pcOperator;
	private String pcOperatorName;
	private String pcBodyA;
	private String pcBodyB;
	private Integer pcType;
	private Double pcBdMoney;
	private String bcUnit;
	private Integer pcPayType;
	private String pcSignDate;
	private Double pcPaidMoney;
	private String paidUnit;
	private String pcRemark;
	private Integer isImportant;
	private Integer isdel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String reportTime;
	private String reportPersonId;
	private String reportPersonName;
	private Integer version;
	private Integer reportStatus;
	private Integer approveId;

	// Constructors

	/** default constructor */
	public PjContractTemp() {
	}

	/** minimal constructor */
	public PjContractTemp(Integer isdel) {
		this.isdel = isdel;
	}

	/** full constructor */
	public PjContractTemp(Integer pjId, String pcNo, String pcName,
			String pcOperator, String pcOperatorName, String pcBodyA,
			String pcBodyB, Integer pcType, Double pcBdMoney, String bcUnit,
			Integer pcPayType, String pcSignDate, Double pcPaidMoney,
			String paidUnit, String pcRemark, Integer isImportant,
			Integer isdel, String createPersonName, String createPersonId,
			String createDate, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate,
			String reportTime, String reportPersonId, String reportPersonName,
			Integer version, Integer reportStatus,Integer approveId) {
		this.pjId = pjId;
		this.pcNo = pcNo;
		this.pcName = pcName;
		this.pcOperator = pcOperator;
		this.pcOperatorName = pcOperatorName;
		this.pcBodyA = pcBodyA;
		this.pcBodyB = pcBodyB;
		this.pcType = pcType;
		this.pcBdMoney = pcBdMoney;
		this.bcUnit = bcUnit;
		this.pcPayType = pcPayType;
		this.pcSignDate = pcSignDate;
		this.pcPaidMoney = pcPaidMoney;
		this.paidUnit = paidUnit;
		this.pcRemark = pcRemark;
		this.isImportant = isImportant;
		this.isdel = isdel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.reportTime = reportTime;
		this.reportPersonId = reportPersonId;
		this.reportPersonName = reportPersonName;
		this.version = version;
		this.reportStatus = reportStatus;
		this.approveId = approveId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPjId() {
		return this.pjId;
	}

	public void setPjId(Integer pjId) {
		this.pjId = pjId;
	}

	public String getPcNo() {
		return this.pcNo;
	}

	public void setPcNo(String pcNo) {
		this.pcNo = pcNo;
	}

	public String getPcName() {
		return this.pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public String getPcOperator() {
		return this.pcOperator;
	}

	public void setPcOperator(String pcOperator) {
		this.pcOperator = pcOperator;
	}

	public String getPcOperatorName() {
		return this.pcOperatorName;
	}

	public void setPcOperatorName(String pcOperatorName) {
		this.pcOperatorName = pcOperatorName;
	}

	public String getPcBodyA() {
		return this.pcBodyA;
	}

	public void setPcBodyA(String pcBodyA) {
		this.pcBodyA = pcBodyA;
	}

	public String getPcBodyB() {
		return this.pcBodyB;
	}

	public void setPcBodyB(String pcBodyB) {
		this.pcBodyB = pcBodyB;
	}

	public Integer getPcType() {
		return this.pcType;
	}

	public void setPcType(Integer pcType) {
		this.pcType = pcType;
	}

	public Double getPcBdMoney() {
		return this.pcBdMoney;
	}

	public void setPcBdMoney(Double pcBdMoney) {
		this.pcBdMoney = pcBdMoney;
	}

	public String getBcUnit() {
		return this.bcUnit;
	}

	public void setBcUnit(String bcUnit) {
		this.bcUnit = bcUnit;
	}

	public Integer getPcPayType() {
		return this.pcPayType;
	}

	public void setPcPayType(Integer pcPayType) {
		this.pcPayType = pcPayType;
	}

	public String getPcSignDate() {
		return this.pcSignDate;
	}

	public void setPcSignDate(String pcSignDate) {
		this.pcSignDate = pcSignDate;
	}

	public Double getPcPaidMoney() {
		return this.pcPaidMoney;
	}

	public void setPcPaidMoney(Double pcPaidMoney) {
		this.pcPaidMoney = pcPaidMoney;
	}

	public String getPaidUnit() {
		return this.paidUnit;
	}

	public void setPaidUnit(String paidUnit) {
		this.paidUnit = paidUnit;
	}

	public String getPcRemark() {
		return this.pcRemark;
	}

	public void setPcRemark(String pcRemark) {
		this.pcRemark = pcRemark;
	}

	public Integer getIsImportant() {
		return this.isImportant;
	}

	public void setIsImportant(Integer isImportant) {
		this.isImportant = isImportant;
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

	public String getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getReportPersonId() {
		return this.reportPersonId;
	}

	public void setReportPersonId(String reportPersonId) {
		this.reportPersonId = reportPersonId;
	}

	public String getReportPersonName() {
		return this.reportPersonName;
	}

	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getReportStatus() {
		return this.reportStatus;
	}

	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}

	public Integer getApproveId() {
		return approveId;
	}

	public void setApproveId(Integer approveId) {
		this.approveId = approveId;
	}

}