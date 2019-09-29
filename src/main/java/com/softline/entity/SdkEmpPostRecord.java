package com.softline.entity;

import com.softline.common.Common;
import com.softline.entityTemp.SDK_empPostRecord;

/**
 * SdkEmpPostRecord entity. @author MyEclipse Persistence Tools
 */

public class SdkEmpPostRecord implements java.io.Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6070891669408173089L;
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
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	// Constructors

	/** default constructor */
	public SdkEmpPostRecord() {
	}

	/** minimal constructor */
	public SdkEmpPostRecord(String vcEmployeeId) {
		this.vcEmployeeId = vcEmployeeId;
	}


	// Property accessors

	
	
	public Integer getId() {
		return id;
	}

	public SdkEmpPostRecord(Integer id, String vcEmployeeId,
			String ipostRecordId, String vcOrganId, String vcPostName,
			String nnodeId, String vcAdminLevelName, String vcHoldPost,
			String cifMost, String cifSideLine, String doperatorDate,
			String cifMorB, String createPersonName, String createPersonId,
			String createDate, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate) {
		this.id = id;
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
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
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
	
	public static SdkEmpPostRecord ConvertTo(SDK_empPostRecord empPostRecord)
	{
		SdkEmpPostRecord sdkEmpPostRecord=new SdkEmpPostRecord();
		 sdkEmpPostRecord.setVcEmployeeId(Common.notEmpty(empPostRecord.getVcEmployeeID())?empPostRecord.getVcEmployeeID():"");
		 sdkEmpPostRecord.setIpostRecordId(Common.notEmpty(empPostRecord.getiPostRecordID())?empPostRecord.getiPostRecordID():"");
		 sdkEmpPostRecord.setVcOrganId(Common.notEmpty(empPostRecord.getVcOrganID())?empPostRecord.getVcOrganID():"");
		 sdkEmpPostRecord.setVcPostName(Common.notEmpty(empPostRecord.getVcPostName())?empPostRecord.getVcPostName():"");
		 sdkEmpPostRecord.setNnodeId(Common.notEmpty(empPostRecord.getnNodeID())?empPostRecord.getnNodeID():"");
		 sdkEmpPostRecord.setVcAdminLevelName(Common.notEmpty(empPostRecord.getVcAdminLevelName())?empPostRecord.getVcAdminLevelName():"");
		 sdkEmpPostRecord.setVcHoldPost(Common.notEmpty(empPostRecord.getVcHoldPost())?empPostRecord.getVcHoldPost():"");
		 sdkEmpPostRecord.setCifMost(Common.notEmpty(empPostRecord.getcIfMost())?empPostRecord.getcIfMost():"");
		 sdkEmpPostRecord.setCifSideLine(Common.notEmpty(empPostRecord.getcIfSideLine())?empPostRecord.getcIfSideLine():"");
		 sdkEmpPostRecord.setDoperatorDate(Common.notEmpty(empPostRecord.getdOperatorDate())?empPostRecord.getdOperatorDate():"");
		 sdkEmpPostRecord.setCifMorB(Common.notEmpty(empPostRecord.getcIfMorB())?empPostRecord.getcIfMorB():"");
		 return sdkEmpPostRecord;
	}
}