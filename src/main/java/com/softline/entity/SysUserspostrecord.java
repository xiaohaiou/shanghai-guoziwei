package com.softline.entity;

/**
 * SysUserspostrecord entity. @author MyEclipse Persistence Tools
 */

public class SysUserspostrecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vcEmployeeId;
	private String ipostRecordId;
	private String vcOrganId;
	private String vcPostName;
	private String nnodeId;
	private String vcAdminLevelName;
	private String vcHoldPost;
	private String cifMost;
	private String cifSideLine;
	private String doperatorDate;
	private String cifMorB;
	private String createDate;
	private String createPersonId;
	private String createPersonName;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;

	// Constructors

	/** default constructor */
	public SysUserspostrecord() {
	}

	/** minimal constructor */
	public SysUserspostrecord(String vcEmployeeId) {
		this.vcEmployeeId = vcEmployeeId;
	}

	/** full constructor */
	public SysUserspostrecord(String vcEmployeeId, String ipostRecordId,
			String vcOrganId, String vcPostName, String nnodeId,
			String vcAdminLevelName, String vcHoldPost, String cifMost,
			String cifSideLine, String doperatorDate, String cifMorB,
			String createDate, String createPersonId, String createPersonName,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate) {
		this.vcEmployeeId = vcEmployeeId;
		this.ipostRecordId = ipostRecordId;
		this.vcOrganId = vcOrganId;
		this.vcPostName = vcPostName;
		this.nnodeId = nnodeId;
		this.vcAdminLevelName = vcAdminLevelName;
		this.vcHoldPost = vcHoldPost;
		this.cifMost = cifMost;
		this.cifSideLine = cifSideLine;
		this.doperatorDate = doperatorDate;
		this.cifMorB = cifMorB;
		this.createDate = createDate;
		this.createPersonId = createPersonId;
		this.createPersonName = createPersonName;
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

	public String getVcEmployeeId() {
		return this.vcEmployeeId;
	}

	public void setVcEmployeeId(String vcEmployeeId) {
		this.vcEmployeeId = vcEmployeeId;
	}

	public String getIpostRecordId() {
		return this.ipostRecordId;
	}

	public void setIpostRecordId(String ipostRecordId) {
		this.ipostRecordId = ipostRecordId;
	}

	public String getVcOrganId() {
		return this.vcOrganId;
	}

	public void setVcOrganId(String vcOrganId) {
		this.vcOrganId = vcOrganId;
	}

	public String getVcPostName() {
		return this.vcPostName;
	}

	public void setVcPostName(String vcPostName) {
		this.vcPostName = vcPostName;
	}

	public String getNnodeId() {
		return this.nnodeId;
	}

	public void setNnodeId(String nnodeId) {
		this.nnodeId = nnodeId;
	}

	public String getVcAdminLevelName() {
		return this.vcAdminLevelName;
	}

	public void setVcAdminLevelName(String vcAdminLevelName) {
		this.vcAdminLevelName = vcAdminLevelName;
	}

	public String getVcHoldPost() {
		return this.vcHoldPost;
	}

	public void setVcHoldPost(String vcHoldPost) {
		this.vcHoldPost = vcHoldPost;
	}

	public String getCifMost() {
		return this.cifMost;
	}

	public void setCifMost(String cifMost) {
		this.cifMost = cifMost;
	}

	public String getCifSideLine() {
		return this.cifSideLine;
	}

	public void setCifSideLine(String cifSideLine) {
		this.cifSideLine = cifSideLine;
	}

	public String getDoperatorDate() {
		return this.doperatorDate;
	}

	public void setDoperatorDate(String doperatorDate) {
		this.doperatorDate = doperatorDate;
	}

	public String getCifMorB() {
		return this.cifMorB;
	}

	public void setCifMorB(String cifMorB) {
		this.cifMorB = cifMorB;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreatePersonId() {
		return this.createPersonId;
	}

	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}

	public String getCreatePersonName() {
		return this.createPersonName;
	}

	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
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