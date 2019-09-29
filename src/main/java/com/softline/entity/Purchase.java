package com.softline.entity;

import java.math.BigDecimal;

/**
 * Purchase entity. @author MyEclipse Persistence Tools
 */

public class Purchase implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer year;
	private Integer season;
	private Integer constructioClass;
	private Integer designClass;
	private Integer supervisionClass;
	private Integer consultingCostClass;
	private Integer materialEquipmentClass;
	private BigDecimal engineeringClass;
	private BigDecimal materialCategoryClass;
	private BigDecimal channelClass;
	private BigDecimal serviceClass;
	private BigDecimal incursTotalAmount;
	private BigDecimal savingsTotalAmount;
	private BigDecimal purchaseSaveRate;
	private Integer status;
	private Integer isdel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String examineTime;
	private String reportPersonName;
	private String reportPersonId;
	private String reportDate;
	private String auditorPersonName;
	private String auditorPersonId;
	private String auditorDate;
	
	//formalu
	private String statusName;
	private String seasonName;
	private Integer getOperateType;
	
	
	// Constructors

	public Integer getGetOperateType() {
		return getOperateType;
	}

	public void setGetOperateType(Integer getOperateType) {
		this.getOperateType = getOperateType;
	}

	/** default constructor */
	public Purchase() {
	}

	/** minimal constructor */
	public Purchase(Integer year, Integer season, Integer constructioClass,
			Integer designClass, Integer supervisionClass,
			Integer consultingCostClass, Integer materialEquipmentClass,
			BigDecimal engineeringClass, BigDecimal materialCategoryClass,
			BigDecimal channelClass, BigDecimal serviceClass, BigDecimal incursTotalAmount,
			BigDecimal savingsTotalAmount, BigDecimal purchaseSaveRate, Integer status,
			Integer isdel) {
		this.year = year;
		this.season = season;
		this.constructioClass = constructioClass;
		this.designClass = designClass;
		this.supervisionClass = supervisionClass;
		this.consultingCostClass = consultingCostClass;
		this.materialEquipmentClass = materialEquipmentClass;
		this.engineeringClass = engineeringClass;
		this.materialCategoryClass = materialCategoryClass;
		this.channelClass = channelClass;
		this.serviceClass = serviceClass;
		this.incursTotalAmount = incursTotalAmount;
		this.savingsTotalAmount = savingsTotalAmount;
		this.purchaseSaveRate = purchaseSaveRate;
		this.status = status;
		this.isdel = isdel;
	}

	/** full constructor */
	public Purchase(Integer year, Integer season, Integer constructioClass,
			Integer designClass, Integer supervisionClass,
			Integer consultingCostClass, Integer materialEquipmentClass,
			BigDecimal engineeringClass, BigDecimal materialCategoryClass,
			BigDecimal channelClass, BigDecimal serviceClass, BigDecimal incursTotalAmount,
			BigDecimal savingsTotalAmount, BigDecimal purchaseSaveRate, Integer status,
			Integer isdel, String createPersonName, String createPersonId,
			String createDate, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate) {
		this.year = year;
		this.season = season;
		this.constructioClass = constructioClass;
		this.designClass = designClass;
		this.supervisionClass = supervisionClass;
		this.consultingCostClass = consultingCostClass;
		this.materialEquipmentClass = materialEquipmentClass;
		this.engineeringClass = engineeringClass;
		this.materialCategoryClass = materialCategoryClass;
		this.channelClass = channelClass;
		this.serviceClass = serviceClass;
		this.incursTotalAmount = incursTotalAmount;
		this.savingsTotalAmount = savingsTotalAmount;
		this.purchaseSaveRate = purchaseSaveRate;
		this.status = status;
		this.isdel = isdel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExamineTime() {
		return examineTime;
	}

	public void setExamineTime(String examineTime) {
		this.examineTime = examineTime;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getSeason() {
		return this.season;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

	public Integer getConstructioClass() {
		return this.constructioClass;
	}

	public void setConstructioClass(Integer constructioClass) {
		this.constructioClass = constructioClass;
	}

	public Integer getDesignClass() {
		return this.designClass;
	}

	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}

	public void setDesignClass(Integer designClass) {
		this.designClass = designClass;
	}

	public Integer getSupervisionClass() {
		return this.supervisionClass;
	}

	public void setSupervisionClass(Integer supervisionClass) {
		this.supervisionClass = supervisionClass;
	}

	public Integer getConsultingCostClass() {
		return this.consultingCostClass;
	}

	public void setConsultingCostClass(Integer consultingCostClass) {
		this.consultingCostClass = consultingCostClass;
	}

	public Integer getMaterialEquipmentClass() {
		return this.materialEquipmentClass;
	}

	public void setMaterialEquipmentClass(Integer materialEquipmentClass) {
		this.materialEquipmentClass = materialEquipmentClass;
	}

	public BigDecimal getEngineeringClass() {
		return this.engineeringClass;
	}

	public void setEngineeringClass(BigDecimal engineeringClass) {
		this.engineeringClass = engineeringClass;
	}

	public BigDecimal getMaterialCategoryClass() {
		return this.materialCategoryClass;
	}

	public void setMaterialCategoryClass(BigDecimal materialCategoryClass) {
		this.materialCategoryClass = materialCategoryClass;
	}

	public BigDecimal getChannelClass() {
		return this.channelClass;
	}

	public void setChannelClass(BigDecimal channelClass) {
		this.channelClass = channelClass;
	}

	public BigDecimal getServiceClass() {
		return this.serviceClass;
	}

	public void setServiceClass(BigDecimal serviceClass) {
		this.serviceClass = serviceClass;
	}

	public BigDecimal getIncursTotalAmount() {
		return this.incursTotalAmount;
	}

	public void setIncursTotalAmount(BigDecimal incursTotalAmount) {
		this.incursTotalAmount = incursTotalAmount;
	}

	public BigDecimal getSavingsTotalAmount() {
		return this.savingsTotalAmount;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public void setSavingsTotalAmount(BigDecimal savingsTotalAmount) {
		this.savingsTotalAmount = savingsTotalAmount;
	}

	public BigDecimal getPurchaseSaveRate() {
		return this.purchaseSaveRate;
	}

	public void setPurchaseSaveRate(BigDecimal purchaseSaveRate) {
		this.purchaseSaveRate = purchaseSaveRate;
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

}