package com.softline.entity;

import com.softline.common.Common;
import com.softline.entityTemp.SDK_organinfo;

/**
 * SdkOrganInfo entity. @author MyEclipse Persistence Tools
 */

public class SdkOrganInfo implements java.io.Serializable {

	// Fields
	private Integer id;
	private String nnodeId;
	private String vcOrganId;
	private String vcParentId;
	private String cflag;
	private Integer ishowOrder;
	private String vcFullName;
	private String vcShortName;
	private String vcshoworder;
	private String doperatorDate;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String vcCompany;
	private String dStartDate;
	private String vcCreatorID;
	private String dCreatorDate;
	private String cLevel;
	// Constructors

	

	/** default constructor */
	public SdkOrganInfo() {
	}


	public String getVcCompany() {
		return vcCompany;
	}


	public void setVcCompany(String vcCompany) {
		this.vcCompany = vcCompany;
	}


	public String getdStartDate() {
		return dStartDate;
	}


	public void setdStartDate(String dStartDate) {
		this.dStartDate = dStartDate;
	}


	public String getVcCreatorID() {
		return vcCreatorID;
	}


	public void setVcCreatorID(String vcCreatorID) {
		this.vcCreatorID = vcCreatorID;
	}


	public String getdCreatorDate() {
		return dCreatorDate;
	}


	public void setdCreatorDate(String dCreatorDate) {
		this.dCreatorDate = dCreatorDate;
	}


	public String getcLevel() {
		return cLevel;
	}


	public void setcLevel(String cLevel) {
		this.cLevel = cLevel;
	}


	/** minimal constructor */
	public SdkOrganInfo(String nnodeId) {
		this.nnodeId = nnodeId;
	}
	
	public SdkOrganInfo(Integer id, String nnodeId, String vcOrganId,
			String vcParentId, String cflag, Integer ishowOrder,
			String vcFullName, String vcShortName, String vcshoworder,
			String doperatorDate, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate,String vcCompany,String dStartDate,String vcCreatorID,String dCreatorDate,String cLevel) {
		this.id = id;
		this.nnodeId = nnodeId;
		this.vcOrganId = vcOrganId;
		this.vcParentId = vcParentId;
		this.cflag = cflag;
		this.ishowOrder = ishowOrder;
		this.vcFullName = vcFullName;
		this.vcShortName = vcShortName;
		this.vcshoworder = vcshoworder;
		this.doperatorDate = doperatorDate;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.vcCompany=vcCompany;
		this.dStartDate=dStartDate;
		this.vcCreatorID=vcCreatorID;
		this.dCreatorDate=dCreatorDate;
		this.cLevel=cLevel;
	}

	// Property accessors
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
	public static SdkOrganInfo ConvertTo(SDK_organinfo organinfo )
	{
		 SdkOrganInfo organInfo = new SdkOrganInfo();
		 organInfo.setNnodeId(Common.notEmpty(organinfo.getnNodeID())?organinfo.getnNodeID():"");
		 organInfo.setVcOrganId(Common.notEmpty(organinfo.getVcOrganID())?organinfo.getVcOrganID():"");
		 organInfo.setVcParentId(Common.notEmpty(organinfo.getVcParentID())?organinfo.getVcParentID():"");
		 organInfo.setCflag(Common.notEmpty(organinfo.getcFlag())?organinfo.getcFlag():"");
		 organInfo.setIshowOrder((organinfo.getiShowOrder()==null)? 0:organinfo.getiShowOrder());
		 organInfo.setVcFullName(Common.notEmpty(organinfo.getVcFullName())?organinfo.getVcFullName():"");
		 organInfo.setVcShortName(Common.notEmpty(organinfo.getVcShortName())?organinfo.getVcShortName():"");
		 organInfo.setVcshoworder(Common.notEmpty(organinfo.getVcshoworder())?organinfo.getVcshoworder():"");
		 organInfo.setDoperatorDate(Common.notEmpty(organinfo.getdOperatorDate())?organinfo.getdOperatorDate():"");
		 organInfo.setcLevel(organinfo.getcLevel());
		 organInfo.setdStartDate(organinfo.getdStartDate());
		 organinfo.setVcCompany(organinfo.getVcCompany());
		 organinfo.setVcCreatorID(organinfo.getVcCreatorID());
		 organinfo.setdCreatorDate(organinfo.getdCreatorDate());
		 return organInfo;
	}
}