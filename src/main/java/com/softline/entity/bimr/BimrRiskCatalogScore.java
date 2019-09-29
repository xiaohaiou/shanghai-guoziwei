package com.softline.entity.bimr;

import javax.persistence.Transient;

/**
 * 二级风险目录评分
 * 
 * @author liu
 */
@SuppressWarnings("serial")
public class BimrRiskCatalogScore implements java.io.Serializable {
	
	private Integer id;
	private Integer ctgId;
	private String ctgName;
	private Integer year;
	private Integer month;
	@Transient
	private Integer eventNum;
	private Integer hapScore;
	private Integer criScore;
	private String createDate;
	private String createPersonId;
	private String createPersonName;
	private String lastModifyDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private Integer isDel;
	private Integer eventImportantNum;
	private String coreOrg; // 核心企业ID
	private String CoreOrgName; // 核心企业的名字
	
	
	public BimrRiskCatalogScore() {
		super();
	}

	public BimrRiskCatalogScore(Integer id, Integer ctgId, String ctgName,
			Integer year, Integer month, Integer hapScore, Integer criScore,
			String createDate, String createPersonId, String createPersonName,
			String lastModifyDate, String lastModifyPersonId,
			String lastModifyPersonName, Integer isDel) {
		super();
		this.id = id;
		this.ctgId = ctgId;
		this.ctgName = ctgName;
		this.year = year;
		this.month = month;
		this.hapScore = hapScore;
		this.criScore = criScore;
		this.createDate = createDate;
		this.createPersonId = createPersonId;
		this.createPersonName = createPersonName;
		this.lastModifyDate = lastModifyDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.isDel = isDel;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCtgId() {
		return ctgId;
	}
	public void setCtgId(Integer ctgId) {
		this.ctgId = ctgId;
	}
	public String getCtgName() {
		return ctgName;
	}
	public void setCtgName(String ctgName) {
		this.ctgName = ctgName;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getEventNum() {
		return eventNum;
	}

	public void setEventNum(Integer eventNum) {
		this.eventNum = eventNum;
	}

	public Integer getHapScore() {
		return hapScore;
	}
	public void setHapScore(Integer hapScore) {
		this.hapScore = hapScore;
	}
	public Integer getCriScore() {
		return criScore;
	}
	public void setCriScore(Integer criScore) {
		this.criScore = criScore;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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
	public String getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
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
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getEventImportantNum() {
		return eventImportantNum;
	}

	public void setEventImportantNum(Integer eventImportantNum) {
		this.eventImportantNum = eventImportantNum;
	}

	public String getCoreOrg() {
		return coreOrg;
	}

	public void setCoreOrg(String coreOrg) {
		this.coreOrg = coreOrg;
	}

	public String getCoreOrgName() {
		return CoreOrgName;
	}

	public void setCoreOrgName(String coreOrgName) {
		CoreOrgName = coreOrgName;
	}

	
	
	
}
