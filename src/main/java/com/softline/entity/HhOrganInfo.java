package com.softline.entity;

import com.softline.common.Common;
import com.softline.entityTemp.SDK_organinfo;

/**
 * HhOrganInfo entity. @author MyEclipse Persistence Tools
 */

public class HhOrganInfo implements java.io.Serializable {

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
	public HhOrganInfo() {
	}
	

	public HhOrganInfo(String nnodeId, String vcOrganId, String vcParentId,
			String vcFullName) {
		super();
		this.nnodeId = nnodeId;
		this.vcOrganId = vcOrganId;
		this.vcParentId = vcParentId;
		this.vcFullName = vcFullName;
	}


	/** minimal constructor */
	public HhOrganInfo(String nnodeId) {
		this.nnodeId = nnodeId;
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
	

	// Property accessors
	
	public HhOrganInfo(String nnodeId, String vcOrganId, String vcParentId,
			String cflag, Integer ishowOrder, String vcFullName,
			String vcShortName, String vcshoworder, String doperatorDate,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate,String vcCompany,String dStartDate,String vcCreatorID,String dCreatorDate,String cLevel) {
		super();
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

	public String getNnodeId() {
		return this.nnodeId;
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
	
	public static HhOrganInfo ConvertTo(HhOrganInfo organinfo, SDK_organinfo sdkorganinfo )
	{
		if(organinfo==null)
		{	
			organinfo = new HhOrganInfo();
		}
		
		organinfo.setVcOrganId(Common.notEmpty(sdkorganinfo.getVcOrganID())?sdkorganinfo.getVcOrganID():"");
		organinfo.setVcParentId(Common.notEmpty(sdkorganinfo.getVcParentID())?sdkorganinfo.getVcParentID():"");
		organinfo.setCflag(Common.notEmpty(sdkorganinfo.getcFlag())?sdkorganinfo.getcFlag():"");
		organinfo.setIshowOrder((sdkorganinfo.getiShowOrder()==null)? 0:sdkorganinfo.getiShowOrder());
		organinfo.setVcFullName(Common.notEmpty(sdkorganinfo.getVcFullName())?sdkorganinfo.getVcFullName():"");
		organinfo.setVcShortName(Common.notEmpty(sdkorganinfo.getVcShortName())?sdkorganinfo.getVcShortName():"");
		organinfo.setVcshoworder(Common.notEmpty(sdkorganinfo.getVcshoworder())?sdkorganinfo.getVcshoworder():"");
		organinfo.setDoperatorDate(Common.notEmpty(sdkorganinfo.getdOperatorDate())?sdkorganinfo.getdOperatorDate():"");
		
		organinfo.setcLevel(sdkorganinfo.getcLevel());
		organinfo.setdStartDate(sdkorganinfo.getdStartDate());
		organinfo.setVcCompany(sdkorganinfo.getVcCompany());
		organinfo.setVcCreatorID(sdkorganinfo.getVcCreatorID());
		organinfo.setdCreatorDate(sdkorganinfo.getdCreatorDate());
		 return organinfo;
	}
}