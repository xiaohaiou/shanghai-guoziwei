package com.softline.entity;

/**
 * SysOrganInfo entity. @author MyEclipse Persistence Tools
 */

public class SysOrganInfo implements java.io.Serializable {

	// Fields

	private String nnodeId;
	private String vcOrganId;
	private String vcParentId;
	private String cflag;
	private Integer ishowOrder;
	private String vcFullName;
	private String vcShortName;
	private String vcshoworder;
	private String doperatorDate;
	private String createDate;
	private String createPersonId;
	private String createPersonName;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;

	// Constructors

	/** default constructor */
	public SysOrganInfo() {
	}

	/** minimal constructor */
	public SysOrganInfo(String nnodeId) {
		this.nnodeId = nnodeId;
	}

	/** full constructor */
	public SysOrganInfo(String nnodeId, String vcOrganId, String vcParentId,
			String cflag, Integer ishowOrder, String vcFullName,
			String vcShortName, String vcshoworder, String doperatorDate,
			String createDate, String createPersonId, String createPersonName,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate) {
		this.nnodeId = nnodeId;
		this.vcOrganId = vcOrganId;
		this.vcParentId = vcParentId;
		this.cflag = cflag;
		this.ishowOrder = ishowOrder;
		this.vcFullName = vcFullName;
		this.vcShortName = vcShortName;
		this.vcshoworder = vcshoworder;
		this.doperatorDate = doperatorDate;
		this.createDate = createDate;
		this.createPersonId = createPersonId;
		this.createPersonName = createPersonName;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
	}

	// Property accessors

	public String getNnodeId() {
		return this.nnodeId;
	}

	public void setNnodeId(String nnodeId) {
		this.nnodeId = nnodeId;
	}

	public String getVcOrganId() {
		return this.vcOrganId;
	}

	public void setVcOrganId(String vcOrganId) {
		this.vcOrganId = vcOrganId;
	}

	public String getVcParentId() {
		return this.vcParentId;
	}

	public void setVcParentId(String vcParentId) {
		this.vcParentId = vcParentId;
	}

	public String getCflag() {
		return this.cflag;
	}

	public void setCflag(String cflag) {
		this.cflag = cflag;
	}

	public Integer getIshowOrder() {
		return this.ishowOrder;
	}

	public void setIshowOrder(Integer ishowOrder) {
		this.ishowOrder = ishowOrder;
	}

	public String getVcFullName() {
		return this.vcFullName;
	}

	public void setVcFullName(String vcFullName) {
		this.vcFullName = vcFullName;
	}

	public String getVcShortName() {
		return this.vcShortName;
	}

	public void setVcShortName(String vcShortName) {
		this.vcShortName = vcShortName;
	}

	public String getVcshoworder() {
		return this.vcshoworder;
	}

	public void setVcshoworder(String vcshoworder) {
		this.vcshoworder = vcshoworder;
	}

	public String getDoperatorDate() {
		return this.doperatorDate;
	}

	public void setDoperatorDate(String doperatorDate) {
		this.doperatorDate = doperatorDate;
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