package com.softline.entity;

/**
 * ReportIndex entity. @author MyEclipse Persistence Tools
 */

public class ReportIndex implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer isdel;
	private Integer parentId;
	private Integer kind;
	private String allparentId;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private Integer unittype;

	// Constructors

	/** default constructor */
	public ReportIndex() {
	}

	/** minimal constructor */
	public ReportIndex(String name, Integer isdel) {
		this.name = name;
		this.isdel = isdel;
	}

	/** full constructor */
	public ReportIndex(String name, Integer isdel, Integer parentId,
			Integer kind, String allparentId, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, Integer unittype) {
		this.name = name;
		this.isdel = isdel;
		this.parentId = parentId;
		this.kind = kind;
		this.allparentId = allparentId;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.unittype = unittype;
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

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getKind() {
		return this.kind;
	}

	public void setKind(Integer kind) {
		this.kind = kind;
	}

	public String getAllparentId() {
		return this.allparentId;
	}

	public void setAllparentId(String allparentId) {
		this.allparentId = allparentId;
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

	public Integer getUnittype() {
		return this.unittype;
	}

	public void setUnittype(Integer unittype) {
		this.unittype = unittype;
	}

}