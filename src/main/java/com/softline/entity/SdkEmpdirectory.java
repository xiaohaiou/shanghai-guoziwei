package com.softline.entity;

import com.softline.common.Common;
import com.softline.entityTemp.SDK_empdirector;

/**
 * SdkEmpdirector entity. @author MyEclipse Persistence Tools
 */

public class SdkEmpdirectory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5298653967765579479L;
	// Fields
	private Integer id;
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
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	// Constructors
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getCreatePersonName() {
		return createPersonName;
	}

	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}

	public String getCreatePersonId() {
		return createPersonId;
	}

	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLastModifyPersonId() {
		return lastModifyPersonId;
	}

	public void setLastModifyPersonId(String lastModifyPersonId) {
		this.lastModifyPersonId = lastModifyPersonId;
	}

	public String getLastModifyPersonName() {
		return lastModifyPersonName;
	}

	public void setLastModifyPersonName(String lastModifyPersonName) {
		this.lastModifyPersonName = lastModifyPersonName;
	}

	public String getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	/** default constructor */
	public SdkEmpdirectory() {
	}

	/** minimal constructor */
	public SdkEmpdirectory(String vcEmployeeId) {
		this.vcEmployeeId = vcEmployeeId;
	}



	// Property accessors

	
	public String getVcEmployeeId() {
		return this.vcEmployeeId;
	}

	public SdkEmpdirectory(Integer id, String vcEmployeeId, String nnodeId,
			String vcName, String vcAccount, String csex, String cflag,
			String doperatorDate, String vcOrganId, String vcFullName,
			String nadminLevelId, String vcAdminLevelName,
			String cifManageLeader, String tbLNNodeId, String vcName1,
			String vcSecondName, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate) {
		super();
		this.id = id;
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
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
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

	public static SdkEmpdirectory ConvertTo(SDK_empdirector data)
	{
		 SdkEmpdirectory sdkEmpdirector = new SdkEmpdirectory();
		 sdkEmpdirector.setVcEmployeeId(Common.notEmpty(data.getVcEmployeeID())?data.getVcEmployeeID():"");//员工编号
		 sdkEmpdirector.setNnodeId(Common.notEmpty(data.getnNodeID())?data.getnNodeID():"");//所属机构ID
		 sdkEmpdirector.setVcName(Common.notEmpty(data.getVcName())?data.getVcName():"");//姓名
		 sdkEmpdirector.setVcAccount(Common.notEmpty(data.getVcAccount())?data.getVcAccount():"");//账号
		 sdkEmpdirector.setCsex(Common.notEmpty(data.getcSex())?data.getcSex():"");//性别
		 sdkEmpdirector.setCflag(Common.notEmpty(data.getcFlag())?data.getcFlag():"");//是否在岗、在职标示
		 sdkEmpdirector.setDoperatorDate(Common.notEmpty(data.getdOperatorDate())?data.getdOperatorDate():"");//操作时间
		 sdkEmpdirector.setVcOrganId(Common.notEmpty(data.getVcOrganID())?data.getVcOrganID():"");//机构节点编号
		 sdkEmpdirector.setVcFullName(Common.notEmpty(data.getVcFullName())?data.getVcFullName():"");//机构全称
		 sdkEmpdirector.setNadminLevelId(Common.notEmpty(data.getnAdminLevelID())?data.getnAdminLevelID():"");//管理级别编号
		 sdkEmpdirector.setVcAdminLevelName(Common.notEmpty(data.getVcAdminLevelName())?data.getVcAdminLevelName():"");//管理级别名称
		 sdkEmpdirector.setCifManageLeader(Common.notEmpty(data.getcIfManageLeader())?data.getcIfManageLeader():"");//是否是管理干部
		 sdkEmpdirector.setTbLNNodeId(Common.notEmpty(data.getTbL_nNodeID())?data.getTbL_nNodeID():"");//所属公司编号
		 sdkEmpdirector.setVcName1(Common.notEmpty(data.getVcName1())?data.getVcName1():"");//机构名称
		 sdkEmpdirector.setVcSecondName(Common.notEmpty(data.getVcSecondName())?data.getVcSecondName():"");//别名
		 return sdkEmpdirector;
	}
}