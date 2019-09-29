package com.softline.entity;

/**
 * PortalMsg entity. @author MyEclipse Persistence Tools
 */

public class PortalNews implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String description;
	private String date;
	private Integer type;
	private Integer isIssue;
	private Integer delFlag;
	private String creator;
	private String createTime;
	private String modifier;
	private String modifyTime;
	
	private String typeName;

	// Constructors

	/** default constructor */
	public PortalNews() {
	}

	/** minimal constructor */
	public PortalNews(Integer type, Integer delFlag) {
		this.type = type;
		this.delFlag = delFlag;
	}

	/** full constructor */
	public PortalNews(String title, String description, String date,
			Integer type, Integer isIssue, Integer delFlag, String creator,
			String createTime, String modifier, String modifyTime) {
		this.title = title;
		this.description = description;
		this.date = date;
		this.type = type;
		this.isIssue = isIssue;
		this.delFlag = delFlag;
		this.creator = creator;
		this.createTime = createTime;
		this.modifier = modifier;
		this.modifyTime = modifyTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsIssue() {
		return this.isIssue;
	}

	public void setIsIssue(Integer isIssue) {
		this.isIssue = isIssue;
	}

	public Integer getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}