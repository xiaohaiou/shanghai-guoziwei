package com.softline.entity.bimr;

import com.softline.entity.HhFile;

/**
 * BimrComplianceProgress entity. @author MyEclipse Persistence Tools
 */

public class BimrComplianceProgress implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer complianceId;
	private Integer week;
	private String content;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private Integer isDel;
	
	private HhFile fileOne;

	// Constructors

	/** default constructor */
	public BimrComplianceProgress() {
	}

	/** minimal constructor */
	public BimrComplianceProgress(Integer isDel) {
		this.isDel = isDel;
	}

	/** full constructor */
	public BimrComplianceProgress(Integer complianceId, Integer week,
			String content, String createPersonName, String createPersonId,
			String createDate, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate, Integer isDel) {
		this.complianceId = complianceId;
		this.week = week;
		this.content = content;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.isDel = isDel;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getComplianceId() {
		return this.complianceId;
	}

	public void setComplianceId(Integer complianceId) {
		this.complianceId = complianceId;
	}

	public Integer getWeek() {
		return this.week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public HhFile getFileOne() {
		return fileOne;
	}

	public void setFileOne(HhFile fileOne) {
		this.fileOne = fileOne;
	}
	
	

}