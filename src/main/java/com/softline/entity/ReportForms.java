package com.softline.entity;

/**
 * ReportForms entity. @author MyEclipse Persistence Tools
 */

public class ReportForms implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer formkind;
	private Integer groupId;
	private Integer fre;
	private Integer isdel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private Integer fileId;
	private Integer sort;
	//虚拟字段
	private String formkindName;
	private String freName;
	
	//是否已上传
	private Integer isUpload;
	
	// Constructors

	/** default constructor */
	public ReportForms() {
	}

	/** minimal constructor */
	public ReportForms(Integer formkind, Integer groupId, Integer fre, Integer isdel) {
		this.formkind = formkind;
		this.groupId = groupId;
		this.fre = fre;
		this.isdel = isdel;
	}

	/** full constructor */
	public ReportForms(Integer formkind, Integer groupId, Integer fre,
			Integer isdel, String createPersonName, String createPersonId,
			String createDate, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate, Integer fileId,Integer sort) {
		this.formkind = formkind;
		this.groupId = groupId;
		this.fre = fre;
		this.isdel = isdel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.fileId = fileId;
		this.sort=sort;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public String getFreName() {
		return freName;
	}

	public void setFreName(String freName) {
		this.freName = freName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getFormkindName() {
		return formkindName;
	}

	public void setFormkindName(String formkindName) {
		this.formkindName = formkindName;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public Integer getFormkind() {
		return formkind;
	}

	public void setFormkind(Integer formkind) {
		this.formkind = formkind;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getFre() {
		return this.fre;
	}

	public void setFre(Integer fre) {
		this.fre = fre;
	}

	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
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

	public Integer getFileId() {
		return this.fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public Integer getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(Integer isUpload) {
		this.isUpload = isUpload;
	}

	

}