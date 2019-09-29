package com.softline.entity;

/**
 * SysSuggestion entity. @author MyEclipse Persistence Tools
 */

public class SysSuggestion implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer isDel;
	private String creator;
	private String createName;
	private String createTime;
	private String description;
	private String filePath;
	private String fileName;
	private String modifyId;
	private String modifyName;
	private String modifyTime;

	// Constructors

	/** default constructor */
	public SysSuggestion() {
	}

	/** full constructor */
	public SysSuggestion(Integer isDel, String creator, String createName,
			String createTime, String description, String filePath,
			String fileName, String modifyId, String modifyName,
			String modifyTime) {
		this.isDel = isDel;
		this.creator = creator;
		this.createName = createName;
		this.createTime = createTime;
		this.description = description;
		this.filePath = filePath;
		this.fileName = fileName;
		this.modifyId = modifyId;
		this.modifyName = modifyName;
		this.modifyTime = modifyTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateName() {
		return this.createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getModifyId() {
		return this.modifyId;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}

	public String getModifyName() {
		return this.modifyName;
	}

	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}

	public String getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

}