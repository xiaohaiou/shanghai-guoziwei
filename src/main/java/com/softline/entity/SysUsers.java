package com.softline.entity;

/**
 * SysUsers entity. @author MyEclipse Persistence Tools
 */

public class SysUsers implements java.io.Serializable {

	// Fields

	private String id;
	private String vcEmployeeId;
	private String nnodeId;
	private String vcName;
	private String vcAccount;
	private String csex;
	private String cflag;
	private String doperatorDate;
	private String vcOrganId;
	private String vcFullName;
	private String nadminLevelId;
	private String vcAdminLevelName;
	private String cifManageLeader;
	private String tbLNNodeId;
	private String vcName1;
	private String vcSecondName;
	private String createDate;
	private String createPersonId;
	private String createPersonName;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String isOut;
	private String vcPostName;
	private String imgPath;
	private String quan;

	// Constructors

	/** default constructor */
	public SysUsers() {
	}

	/** minimal constructor */
	public SysUsers(String vcEmployeeId) {
		this.vcEmployeeId = vcEmployeeId;
	}

	/** full constructor */
	public SysUsers(String vcEmployeeId, String nnodeId, String vcName,
			String vcAccount, String csex, String cflag, String doperatorDate,
			String vcOrganId, String vcFullName, String nadminLevelId,
			String vcAdminLevelName, String cifManageLeader, String tbLNNodeId,
			String vcName1, String vcSecondName, String createDate,
			String createPersonId, String createPersonName,
			String lastModifyPersonId, String lastModifyPersonName,String imgPath,
			String lastModifyDate, String isOut,String id,String vcPostName,String quan) {
		this.vcEmployeeId = vcEmployeeId;
		this.nnodeId = nnodeId;
		this.vcName = vcName;
		this.vcAccount = vcAccount;
		this.csex = csex;
		this.cflag = cflag;
		this.doperatorDate = doperatorDate;
		this.vcOrganId = vcOrganId;
		this.vcFullName = vcFullName;
		this.nadminLevelId = nadminLevelId;
		this.vcAdminLevelName = vcAdminLevelName;
		this.cifManageLeader = cifManageLeader;
		this.tbLNNodeId = tbLNNodeId;
		this.vcName1 = vcName1;
		this.vcSecondName = vcSecondName;
		this.createDate = createDate;
		this.createPersonId = createPersonId;
		this.createPersonName = createPersonName;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.isOut = isOut;
		this.id = id;
		this.vcPostName = vcPostName;
		this.imgPath = imgPath;
		this.quan = quan;
	}

	// Property accessors



	public String getVcEmployeeId() {
		return this.vcEmployeeId;
	}

	public String getQuan() {
		return quan;
	}

	public void setQuan(String quan) {
		this.quan = quan;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getVcPostName() {
		return vcPostName;
	}

	public void setVcPostName(String vcPostName) {
		this.vcPostName = vcPostName;
	}

	public String getIsOut() {
		return isOut;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIsOut(String isOut) {
		this.isOut = isOut;
	}

	public void setVcEmployeeId(String vcEmployeeId) {
		this.vcEmployeeId = vcEmployeeId;
	}

	public String getNnodeId() {
		return this.nnodeId;
	}

	public void setNnodeId(String nnodeId) {
		this.nnodeId = nnodeId;
	}

	public String getVcName() {
		return this.vcName;
	}

	public void setVcName(String vcName) {
		this.vcName = vcName;
	}

	public String getVcAccount() {
		return this.vcAccount;
	}

	public void setVcAccount(String vcAccount) {
		this.vcAccount = vcAccount;
	}

	public String getCsex() {
		return this.csex;
	}

	public void setCsex(String csex) {
		this.csex = csex;
	}

	public String getCflag() {
		return this.cflag;
	}

	public void setCflag(String cflag) {
		this.cflag = cflag;
	}

	public String getDoperatorDate() {
		return this.doperatorDate;
	}

	public void setDoperatorDate(String doperatorDate) {
		this.doperatorDate = doperatorDate;
	}

	public String getVcOrganId() {
		return this.vcOrganId;
	}

	public void setVcOrganId(String vcOrganId) {
		this.vcOrganId = vcOrganId;
	}

	public String getVcFullName() {
		return this.vcFullName;
	}

	public void setVcFullName(String vcFullName) {
		this.vcFullName = vcFullName;
	}

	public String getNadminLevelId() {
		return this.nadminLevelId;
	}

	public void setNadminLevelId(String nadminLevelId) {
		this.nadminLevelId = nadminLevelId;
	}

	public String getVcAdminLevelName() {
		return this.vcAdminLevelName;
	}

	public void setVcAdminLevelName(String vcAdminLevelName) {
		this.vcAdminLevelName = vcAdminLevelName;
	}

	public String getCifManageLeader() {
		return this.cifManageLeader;
	}

	public void setCifManageLeader(String cifManageLeader) {
		this.cifManageLeader = cifManageLeader;
	}

	public String getTbLNNodeId() {
		return this.tbLNNodeId;
	}

	public void setTbLNNodeId(String tbLNNodeId) {
		this.tbLNNodeId = tbLNNodeId;
	}

	public String getVcName1() {
		return this.vcName1;
	}

	public void setVcName1(String vcName1) {
		this.vcName1 = vcName1;
	}

	public String getVcSecondName() {
		return this.vcSecondName;
	}

	public void setVcSecondName(String vcSecondName) {
		this.vcSecondName = vcSecondName;
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