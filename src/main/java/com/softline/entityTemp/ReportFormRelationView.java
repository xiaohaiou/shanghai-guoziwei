package com.softline.entityTemp;

/**
 * ReportFormRelation entity. @author MyEclipse Persistence Tools
 */

public class ReportFormRelationView implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer formId;
	private Integer otherformId;
	private Integer type;
	private Integer isdel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String typeName;
	private String formName;
	private String formfreName;
	private String otherformName;
	private String otherformfreName;
	private String groupName;
	private String othergroupName;
	// Constructors

	/** default constructor */
	public ReportFormRelationView() {
	}

	/** minimal constructor */
	public ReportFormRelationView(Integer formId, Integer otherformId,
			Integer type, Integer isdel) {
		this.formId = formId;
		this.otherformId = otherformId;
		this.type = type;
		this.isdel = isdel;
	}

	/** full constructor */
	public ReportFormRelationView(Integer formId, Integer otherformId,
			Integer type, Integer isdel, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate) {
		this.formId = formId;
		this.otherformId = otherformId;
		this.type = type;
		this.isdel = isdel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFormId() {
		return this.formId;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	public Integer getOtherformId() {
		return this.otherformId;
	}

	public void setOtherformId(Integer otherformId) {
		this.otherformId = otherformId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
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

}