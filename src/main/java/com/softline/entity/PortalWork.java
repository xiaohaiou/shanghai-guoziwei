package com.softline.entity;

import java.sql.Timestamp;

/**
 * PortalWork entity. @author MyEclipse Persistence Tools
 */

public class PortalWork implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String description;
	private String hhusers;
	private String date;
	private String company;
	private Integer delFlag;
	private String creator;
	private String createTime;
	private String modifier;
	private String modifyTime;

	// Constructors

	/** default constructor */
	public PortalWork() {
	}

	/** minimal constructor */
	public PortalWork(String title, String date, Integer delFlag) {
		this.title = title;
		this.date = date;
		this.delFlag = delFlag;
	}

	/** full constructor */
	public PortalWork(String title, String description, String hhusers,
			String date, String company, Integer delFlag, String creator,
			String createTime, String modifier, String modifyTime) {
		this.title = title;
		this.description = description;
		this.hhusers = hhusers;
		this.date = date;
		this.company = company;
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

	public String getHhusers() {
		return this.hhusers;
	}

	public void setHhusers(String hhusers) {
		this.hhusers = hhusers;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
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

}