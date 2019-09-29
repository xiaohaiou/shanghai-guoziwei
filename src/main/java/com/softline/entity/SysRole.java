package com.softline.entity;

/**
 * SysRole entity. @author MyEclipse Persistence Tools
 */

public class SysRole implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer isDel;
	private String creater;
	private String createTime;
	private String modifierId;
	private String modifyTime;
	private String name;
	private String description;
	private Integer srcId;

	// Constructors

	/** default constructor */
	public SysRole() {
	}

	/** full constructor */
	public SysRole(Integer isDel, String creater, String createTime,
			String modifierId, String modifyTime, String name,
			String description) {
		this.isDel = isDel;
		this.creater = creater;
		this.createTime = createTime;
		this.modifierId = modifierId;
		this.modifyTime = modifyTime;
		this.name = name;
		this.description = description;
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

	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifierId() {
		return this.modifierId;
	}

	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}

	public String getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSrcId() {
		return srcId;
	}

	public void setSrcId(Integer srcId) {
		this.srcId = srcId;
	}

}