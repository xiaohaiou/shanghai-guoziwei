package com.softline.entity;

/**
 * ReportFormsIndex entity. @author MyEclipse Persistence Tools
 */

public class ReportFormsIndex implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer formsKindID;
	private Integer indexId;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private Integer isdel;
	private Integer groupKindID;
	private String  groupTime;
	
	
	// Constructors

	public String getGroupTime() {
		return groupTime;
	}



	public void setGroupTime(String groupTime) {
		this.groupTime = groupTime;
	}



	/** default constructor */
	public ReportFormsIndex() {
	}



	// Property accessors

	public ReportFormsIndex(Integer id, Integer formsKindID, Integer indexId,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, Integer isdel, Integer groupKindID) {
		super();
		this.id = id;
		this.formsKindID = formsKindID;
		this.indexId = indexId;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.isdel = isdel;
		this.groupKindID = groupKindID;
	}



	public Integer getId() {
		return this.id;
	}

	

	public Integer getGroupKindID() {
		return groupKindID;
	}

	public void setGroupKindID(Integer groupKindID) {
		this.groupKindID = groupKindID;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Integer getFormsKindID() {
		return formsKindID;
	}

	public void setFormsKindID(Integer formsKindID) {
		this.formsKindID = formsKindID;
	}

	public Integer getIndexId() {
		return this.indexId;
	}

	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
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

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

}