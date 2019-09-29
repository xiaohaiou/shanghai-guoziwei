package com.softline.entity.project;

/**
 * PjAuthorityPerson entity. @author MyEclipse Persistence Tools
 */

public class PjAuthorityPerson implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer pjId;
	private Integer personType;
	private String personVcEmployeeId;
	private String personName;
	private Integer isdel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	
	//formular
	private String pjName;
	

	// Constructors

	/** default constructor */
	public PjAuthorityPerson() {
	}

	/** minimal constructor */
	public PjAuthorityPerson(Integer isdel) {
		this.isdel = isdel;
	}

	/** full constructor */
	public PjAuthorityPerson(Integer pjId, Integer personType,
			String personVcEmployeeId, String personName, Integer isdel,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate) {
		this.pjId = pjId;
		this.personType = personType;
		this.personVcEmployeeId = personVcEmployeeId;
		this.personName = personName;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPjId() {
		return this.pjId;
	}

	public void setPjId(Integer pjId) {
		this.pjId = pjId;
	}

	public Integer getPersonType() {
		return this.personType;
	}

	public void setPersonType(Integer personType) {
		this.personType = personType;
	}

	public String getPersonVcEmployeeId() {
		return this.personVcEmployeeId;
	}

	public void setPersonVcEmployeeId(String personVcEmployeeId) {
		this.personVcEmployeeId = personVcEmployeeId;
	}

	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
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

	public String getPjName() {
		return pjName;
	}

	public void setPjName(String pjName) {
		this.pjName = pjName;
	}
	
	

}