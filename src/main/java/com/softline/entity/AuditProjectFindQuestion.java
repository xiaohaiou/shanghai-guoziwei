package com.softline.entity;

/**
 * AuditProjectFindQuestion entity.
 */

public class AuditProjectFindQuestion implements java.io.Serializable {
	// Fields
	private Integer id;
	private Integer auditProjectId;
	private Integer corporateStrategyMakeAndExecute = 0;
	private Integer financialControl = 0;
	private Integer humanResourceManagement = 0;
	private Integer purchaseManagement = 0;
	private Integer infrastructure = 0;
	private Integer projectInvestment = 0;
	private Integer productionOrganizationAndSales = 0;
	private Integer administrativeAffairs = 0;
	private Integer externalEnvironmentAndCompetition = 0;
	private Integer others = 0;
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
	private String statusName;
	private String auditDeptName;
	private String auditTypeName;
	
	public AuditProjectFindQuestion() {
		super();
	}

	public AuditProjectFindQuestion(Integer id, Integer auditProjectId, Integer corporateStrategyMakeAndExecute,
			Integer financialControl, Integer humanResourceManagement, Integer purchaseManagement, Integer infrastructure, 
			Integer projectInvestment, Integer productionOrganizationAndSales,  Integer administrativeAffairs, 
			Integer externalEnvironmentAndCompetition, Integer others, Integer isDel, String createPersonName, 
			String createPersonId, String createDate, String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName, String reportPersonId, String reportDate, 
			String auditorPersonName, String auditorPersonId, String auditorDate) {
		super();
		this.id = id;
		this.auditProjectId = auditProjectId;
		this.corporateStrategyMakeAndExecute = corporateStrategyMakeAndExecute;
		this.financialControl = financialControl;
		this.humanResourceManagement = humanResourceManagement;
		this.purchaseManagement = purchaseManagement;
		this.infrastructure = infrastructure;
		this.projectInvestment = projectInvestment;
		this.productionOrganizationAndSales = productionOrganizationAndSales;
		this.administrativeAffairs = administrativeAffairs;
		this.externalEnvironmentAndCompetition = externalEnvironmentAndCompetition;
		this.others = others;
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

	public Integer getAuditProjectId() {
		return auditProjectId;
	}

	public void setAuditProjectId(Integer auditProjectId) {
		this.auditProjectId = auditProjectId;
	}

	public Integer getCorporateStrategyMakeAndExecute() {
		return corporateStrategyMakeAndExecute;
	}

	public void setCorporateStrategyMakeAndExecute(
			Integer corporateStrategyMakeAndExecute) {
		this.corporateStrategyMakeAndExecute = corporateStrategyMakeAndExecute;
	}

	public Integer getFinancialControl() {
		return financialControl;
	}

	public void setFinancialControl(Integer financialControl) {
		this.financialControl = financialControl;
	}

	public Integer getHumanResourceManagement() {
		return humanResourceManagement;
	}

	public void setHumanResourceManagement(Integer humanResourceManagement) {
		this.humanResourceManagement = humanResourceManagement;
	}

	public Integer getPurchaseManagement() {
		return purchaseManagement;
	}

	public void setPurchaseManagement(Integer purchaseManagement) {
		this.purchaseManagement = purchaseManagement;
	}

	public Integer getInfrastructure() {
		return infrastructure;
	}

	public void setInfrastructure(Integer infrastructure) {
		this.infrastructure = infrastructure;
	}

	public Integer getProjectInvestment() {
		return projectInvestment;
	}

	public void setProjectInvestment(Integer projectInvestment) {
		this.projectInvestment = projectInvestment;
	}

	public Integer getProductionOrganizationAndSales() {
		return productionOrganizationAndSales;
	}

	public void setProductionOrganizationAndSales(
			Integer productionOrganizationAndSales) {
		this.productionOrganizationAndSales = productionOrganizationAndSales;
	}

	public Integer getAdministrativeAffairs() {
		return administrativeAffairs;
	}

	public void setAdministrativeAffairs(Integer administrativeAffairs) {
		this.administrativeAffairs = administrativeAffairs;
	}

	public Integer getExternalEnvironmentAndCompetition() {
		return externalEnvironmentAndCompetition;
	}

	public void setExternalEnvironmentAndCompetition(
			Integer externalEnvironmentAndCompetition) {
		this.externalEnvironmentAndCompetition = externalEnvironmentAndCompetition;
	}

	public Integer getOthers() {
		return others;
	}

	public void setOthers(Integer others) {
		this.others = others;
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getAuditDeptName() {
		return auditDeptName;
	}

	public void setAuditDeptName(String auditDeptName) {
		this.auditDeptName = auditDeptName;
	}

	public String getAuditTypeName() {
		return auditTypeName;
	}

	public void setAuditTypeName(String auditTypeName) {
		this.auditTypeName = auditTypeName;
	}
	
}