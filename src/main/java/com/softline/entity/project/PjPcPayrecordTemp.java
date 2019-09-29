package com.softline.entity.project;

/**
 * PjPcPayrecordTemp entity. @author MyEclipse Persistence Tools
 */

public class PjPcPayrecordTemp implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer pcId;
	private Integer payCount;
	private Integer payPercent;
	private Double paidMoney;
	private String pmUnit;
	private String planPayDate;
	private String remark;
	private Integer isdel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;

	// Constructors

	/** default constructor */
	public PjPcPayrecordTemp() {
	}

	/** minimal constructor */
	public PjPcPayrecordTemp(Integer isdel) {
		this.isdel = isdel;
	}

	/** full constructor */
	public PjPcPayrecordTemp(Integer pcId,Integer payCount, Integer payPercent,
			Double paidMoney, String pmUnit, String planPayDate, String remark,
			Integer isdel, String createPersonName, String createPersonId,
			String createDate, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate) {
		this.pcId = pcId;
		this.payCount = payCount;
		this.payPercent = payPercent;
		this.paidMoney = paidMoney;
		this.pmUnit = pmUnit;
		this.planPayDate = planPayDate;
		this.remark = remark;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPayCount() {
		return this.payCount;
	}

	public void setPayCount(Integer payCount) {
		this.payCount = payCount;
	}

	public Integer getPayPercent() {
		return this.payPercent;
	}

	public void setPayPercent(Integer payPercent) {
		this.payPercent = payPercent;
	}

	public Double getPaidMoney() {
		return this.paidMoney;
	}

	public void setPaidMoney(Double paidMoney) {
		this.paidMoney = paidMoney;
	}

	public String getPmUnit() {
		return this.pmUnit;
	}

	public void setPmUnit(String pmUnit) {
		this.pmUnit = pmUnit;
	}

	public String getPlanPayDate() {
		return this.planPayDate;
	}

	public void setPlanPayDate(String planPayDate) {
		this.planPayDate = planPayDate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getPcId() {
		return pcId;
	}

	public void setPcId(Integer pcId) {
		this.pcId = pcId;
	}

}