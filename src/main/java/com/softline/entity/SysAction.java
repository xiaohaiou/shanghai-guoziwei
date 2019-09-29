package com.softline.entity;

/**
 * SysAction entity. @author MyEclipse Persistence Tools
 */

public class SysAction implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String description;
	private String path;
	private Integer orderSeq;
	private Integer parentId;
	private Integer isShow;
	private Integer level;
	private String picture;
	private Integer isDel;

	// Constructors

	/** default constructor */
	public SysAction() {
	}

	/** minimal constructor */
	public SysAction(Integer isDel) {
		this.isDel = isDel;
	}

	/** full constructor */
	public SysAction(String name, String description, String path,
			Integer orderSeq, Integer parentId, Integer isShow, Integer level,
			String picture, Integer isDel) {
		this.name = name;
		this.description = description;
		this.path = path;
		this.orderSeq = orderSeq;
		this.parentId = parentId;
		this.isShow = isShow;
		this.level = level;
		this.picture = picture;
		this.isDel = isDel;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getOrderSeq() {
		return this.orderSeq;
	}

	public void setOrderSeq(Integer orderSeq) {
		this.orderSeq = orderSeq;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getIsShow() {
		return this.isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}