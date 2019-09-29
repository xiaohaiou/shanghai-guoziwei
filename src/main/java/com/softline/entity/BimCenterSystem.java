package com.softline.entity;

import com.softline.common.Common;

/**
 * BimCenterSystem entity. @author MyEclipse Persistence Tools
 */

public class BimCenterSystem implements java.io.Serializable {

	// Fields

	private Integer id;
	private String sysFulName;
	private String sysShortName;
	private String sysDecription;
	private String sysLogoPath;
	private String sysVideoPath;
	private String sysVideoName;
	private String sysImgPath;
	private String sysUrl;
	private Integer sysCompanyId;
	private Integer sysOrder;
	private Integer isDel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String sysLogoName;
	private String sysImgName;
	
	
	//formular
	private String companyName;
	

	// Constructors

	/** default constructor */
	public BimCenterSystem() {
	}

	/** full constructor */
	public BimCenterSystem(String sysFulName, String sysShortName,
			String sysDecription, String sysLogoPath, String sysVideoPath,
			String sysVideoName, String sysImgPath, String sysUrl,
			Integer sysCompanyId, Integer sysOrder, Integer isDel,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate) {
		this.sysFulName = sysFulName;
		this.sysShortName = sysShortName;
		this.sysDecription = sysDecription;
		this.sysLogoPath = sysLogoPath;
		this.sysVideoPath = sysVideoPath;
		this.sysVideoName = sysVideoName;
		this.sysImgPath = sysImgPath;
		this.sysUrl = sysUrl;
		this.sysCompanyId = sysCompanyId;
		this.sysOrder = sysOrder;
		this.isDel = isDel;
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

	public String getSysFulName() {
		return this.sysFulName;
	}

	public void setSysFulName(String sysFulName) {
		this.sysFulName = sysFulName;
	}

	public String getSysShortName() {
		return this.sysShortName;
	}

	public void setSysShortName(String sysShortName) {
		this.sysShortName = sysShortName;
	}

	public String getSysDecription() {
		return this.sysDecription;
	}

	public void setSysDecription(String sysDecription) {
		this.sysDecription = sysDecription;
	}

	public String getSysLogoPath() {
		return this.sysLogoPath;
	}

	public void setSysLogoPath(String sysLogoPath) {
		this.sysLogoPath = sysLogoPath;
	}

	public String getSysVideoPath() {
		return this.sysVideoPath;
	}

	public void setSysVideoPath(String sysVideoPath) {
		this.sysVideoPath = sysVideoPath;
	}

	public String getSysVideoName() {
		return this.sysVideoName;
	}

	public void setSysVideoName(String sysVideoName) {
		this.sysVideoName = sysVideoName;
	}

	public String getSysImgPath() {
		return this.sysImgPath;
	}

	public void setSysImgPath(String sysImgPath) {
		this.sysImgPath = sysImgPath;
	}

	public String getSysUrl() {
		return this.sysUrl;
	}

	public void setSysUrl(String sysUrl) {
		this.sysUrl = sysUrl;
	}

	public Integer getSysCompanyId() {
		return this.sysCompanyId;
	}

	public void setSysCompanyId(Integer sysCompanyId) {
		this.sysCompanyId = sysCompanyId;
	}

	public Integer getSysOrder() {
		return this.sysOrder;
	}

	public void setSysOrder(Integer sysOrder) {
		this.sysOrder = sysOrder;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSysLogoName() {
		return sysLogoName;
	}

	public void setSysLogoName(String sysLogoName) {
		this.sysLogoName = sysLogoName;
	}

	public String getSysImgName() {
		return sysImgName;
	}

	public void setSysImgName(String sysImgName) {
		this.sysImgName = sysImgName;
	}

}