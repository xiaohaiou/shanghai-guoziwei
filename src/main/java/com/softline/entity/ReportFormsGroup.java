package com.softline.entity;

/**
 * ReportFormsGroup entity. @author MyEclipse Persistence Tools
 */

public class ReportFormsGroup implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer isdel;
	private String time;
	private Integer nameID;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private Integer fileID;
	private Integer type;
	private String typeName;
	private String exportoutpath;
	private String month;
	// Constructors

	/** default constructor */
	public ReportFormsGroup() {
	}


	/** full constructor */
	public ReportFormsGroup( Integer isdel, String time,
			 Integer nameID, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate,String month) {
		this.isdel = isdel;
		this.time = time;
		this.nameID = nameID;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.month = month;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public Integer getFileID() {
		return fileID;
	}


	public void setFileID(Integer fileID) {
		this.fileID = fileID;
	}




	public String getExportoutpath() {
		return exportoutpath;
	}


	public void setExportoutpath(String exportoutpath) {
		this.exportoutpath = exportoutpath;
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

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	

	public Integer getNameID() {
		return nameID;
	}

	public void setNameID(Integer nameID) {
		this.nameID = nameID;
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


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}

}