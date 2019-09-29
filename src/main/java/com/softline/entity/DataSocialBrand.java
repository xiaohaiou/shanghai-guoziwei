package com.softline.entity;

/**
 * DataSocialBrand entity. @author MyEclipse Persistence Tools
 */

public class DataSocialBrand implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer type;
	private Integer level;
	private String income;
	private String incomeIncrease;
	private String fund;
	private String coverageArea;
	private String position;
	private Integer property;
	private String drawerType;
	private Integer status;
	private Integer isdel;
	private String reportPersonId;
	private String reportPersonName;
	private String reportDate;
	private String auditorPersonId;
	private String auditorPersonName;
	private String auditorDate;
	private String createPersonId;
	private String createPersonName;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;

	//formalu
	private String statusName;
	private String typeName;
	private String levelName;
	private String propertyName;
	// Constructors

	/** default constructor */
	public DataSocialBrand() {
	}

	/** minimal constructor */
	public DataSocialBrand(String name, Integer type, Integer level,
			String income, String incomeIncrease, String fund,
			String coverageArea, String position, Integer property,
			String drawerType, Integer status, Integer isdel) {
		this.name = name;
		this.type = type;
		this.level = level;
		this.income = income;
		this.incomeIncrease = incomeIncrease;
		this.fund = fund;
		this.coverageArea = coverageArea;
		this.position = position;
		this.property = property;
		this.drawerType = drawerType;
		this.status = status;
		this.isdel = isdel;
	}

	/** full constructor */
	public DataSocialBrand(String name, Integer type, Integer level,
			String income, String incomeIncrease, String fund,
			String coverageArea, String position, Integer property,
			String drawerType, Integer status, Integer isdel,
			String reportPersonId, String reportPersonName, String reportDate,
			String auditorPersonId, String auditorPersonName,
			String auditorDate, String createPersonId, String createPersonName,
			String createDate, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate) {
		this.name = name;
		this.type = type;
		this.level = level;
		this.income = income;
		this.incomeIncrease = incomeIncrease;
		this.fund = fund;
		this.coverageArea = coverageArea;
		this.position = position;
		this.property = property;
		this.drawerType = drawerType;
		this.status = status;
		this.isdel = isdel;
		this.reportPersonId = reportPersonId;
		this.reportPersonName = reportPersonName;
		this.reportDate = reportDate;
		this.auditorPersonId = auditorPersonId;
		this.auditorPersonName = auditorPersonName;
		this.auditorDate = auditorDate;
		this.createPersonId = createPersonId;
		this.createPersonName = createPersonName;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getIncome() {
		return this.income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getIncomeIncrease() {
		return this.incomeIncrease;
	}

	public void setIncomeIncrease(String incomeIncrease) {
		this.incomeIncrease = incomeIncrease;
	}

	public String getFund() {
		return this.fund;
	}

	public void setFund(String fund) {
		this.fund = fund;
	}

	public String getCoverageArea() {
		return this.coverageArea;
	}

	public void setCoverageArea(String coverageArea) {
		this.coverageArea = coverageArea;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getProperty() {
		return this.property;
	}

	public void setProperty(Integer property) {
		this.property = property;
	}

	public String getDrawerType() {
		return this.drawerType;
	}

	public void setDrawerType(String drawerType) {
		this.drawerType = drawerType;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
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

	public String getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getAuditorPersonId() {
		return this.auditorPersonId;
	}

	public void setAuditorPersonId(String auditorPersonId) {
		this.auditorPersonId = auditorPersonId;
	}

	public String getAuditorPersonName() {
		return this.auditorPersonName;
	}

	public void setAuditorPersonName(String auditorPersonName) {
		this.auditorPersonName = auditorPersonName;
	}

	public String getAuditorDate() {
		return this.auditorDate;
	}

	public void setAuditorDate(String auditorDate) {
		this.auditorDate = auditorDate;
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

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
}