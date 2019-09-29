package com.softline.entity;

/**
 * RiskControlCase entity.
 */

public class RiskControlCase implements java.io.Serializable {
	// Fields
	private Integer id;
	private Integer caseBelongCompany;
	private Integer caseSubordinateCompany;
	private String plaintiff;
	private String defendant;
	private Integer caseType;
	private String caseHappenDate;
	private String litigationAmountMoney = "0";
	private Integer currentState;
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
	
	private String caseBelongCompanyName;
	private String caseSubordinateCompanyName;
	private String caseTypeName;
	private String currentStateName;
	private String statusName;
	
	public RiskControlCase() {
		super();
	}

	public RiskControlCase(Integer id, Integer caseBelongCompany, Integer caseSubordinateCompany, String plaintiff, 
			String defendant, Integer caseType,  String caseHappenDate, String litigationAmountMoney, 
			Integer currentState, Integer status, Integer isDel, String createPersonName, 
			String createPersonId, String createDate, String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName, String reportPersonId, String reportDate, 
			String auditorPersonName, String auditorPersonId, String auditorDate) {
		super();
		this.id = id;
		this.caseBelongCompany = caseBelongCompany;
		this.caseSubordinateCompany = caseSubordinateCompany;
		this.plaintiff = plaintiff;
		this.defendant = defendant;
		this.caseType = caseType;
		this.caseHappenDate = caseHappenDate;
		this.litigationAmountMoney = litigationAmountMoney;
		this.currentState = currentState;
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

	public Integer getCaseBelongCompany() {
		return caseBelongCompany;
	}

	public void setCaseBelongCompany(Integer caseBelongCompany) {
		this.caseBelongCompany = caseBelongCompany;
	}

	public Integer getCaseSubordinateCompany() {
		return caseSubordinateCompany;
	}

	public void setCaseSubordinateCompany(Integer caseSubordinateCompany) {
		this.caseSubordinateCompany = caseSubordinateCompany;
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

	public Integer getCaseType() {
		return caseType;
	}

	public void setCaseType(Integer caseType) {
		this.caseType = caseType;
	}

	public String getCaseHappenDate() {
		return caseHappenDate;
	}

	public void setCaseHappenDate(String caseHappenDate) {
		this.caseHappenDate = caseHappenDate;
	}

	public String getLitigationAmountMoney() {
		return litigationAmountMoney;
	}

	public void setLitigationAmountMoney(String litigationAmountMoney) {
		this.litigationAmountMoney = litigationAmountMoney;
	}

	public Integer getCurrentState() {
		return currentState;
	}

	public void setCurrentState(Integer currentState) {
		this.currentState = currentState;
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

	public String getCaseBelongCompanyName() {
		return caseBelongCompanyName;
	}

	public void setCaseBelongCompanyName(String caseBelongCompanyName) {
		this.caseBelongCompanyName = caseBelongCompanyName;
	}

	public String getCaseSubordinateCompanyName() {
		return caseSubordinateCompanyName;
	}

	public void setCaseSubordinateCompanyName(String caseSubordinateCompanyName) {
		this.caseSubordinateCompanyName = caseSubordinateCompanyName;
	}

	public String getCaseTypeName() {
		return caseTypeName;
	}

	public void setCaseTypeName(String caseTypeName) {
		this.caseTypeName = caseTypeName;
	}

	public String getCurrentStateName() {
		return currentStateName;
	}

	public void setCurrentStateName(String currentStateName) {
		this.currentStateName = currentStateName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
}