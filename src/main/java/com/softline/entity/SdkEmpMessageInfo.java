package com.softline.entity;

import com.softline.common.Common;
import com.softline.entityTemp.SDK_empMessageInfo;
import com.softline.util.MD5;

/**
 * SdkEmpMessageInfo entity. @author MyEclipse Persistence Tools
 */

public class SdkEmpMessageInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7282987324849003906L;
	// Fields
	private Integer id;
	private String vcEmployeeId;
	private String vcEmail;
	private String vcWorkPhone;
	private String vcMobile;
	private String doperatorDate;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	// Constructors

	
	/** default constructor */
	public SdkEmpMessageInfo() {
	}

	/** minimal constructor */
	public SdkEmpMessageInfo(String vcEmployeeId) {
		this.vcEmployeeId = vcEmployeeId;
	}

	public SdkEmpMessageInfo(Integer id, String vcEmployeeId, String vcEmail,
			String vcWorkPhone, String vcMobile, String doperatorDate,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate) {
		this.id = id;
		this.vcEmployeeId = vcEmployeeId;
		this.vcEmail = vcEmail;
		this.vcWorkPhone = vcWorkPhone;
		this.vcMobile = vcMobile;
		this.doperatorDate = doperatorDate;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// Property accessors
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

	public String getVcEmail() {
		return this.vcEmail;
	}

	public void setVcEmail(String vcEmail) {
		this.vcEmail = vcEmail;
	}

	public String getVcWorkPhone() {
		return this.vcWorkPhone;
	}

	public void setVcWorkPhone(String vcWorkPhone) {
		this.vcWorkPhone = vcWorkPhone;
	}

	public String getVcMobile() {
		return this.vcMobile;
	}

	public void setVcMobile(String vcMobile) {
		this.vcMobile = vcMobile;
	}

	public String getDoperatorDate() {
		return this.doperatorDate;
	}

	public void setDoperatorDate(String doperatorDate) {
		this.doperatorDate = doperatorDate;
	}
	public static SdkEmpMessageInfo ConvertTo(SDK_empMessageInfo empMessageInfo)
	{
		 SdkEmpMessageInfo sdkEmpMessageInfo = new SdkEmpMessageInfo();
		 sdkEmpMessageInfo.setVcEmployeeId(Common.notEmpty(empMessageInfo.getVcEmployeeID())?empMessageInfo.getVcEmployeeID():"");
		 sdkEmpMessageInfo.setVcEmail(Common.notEmpty(empMessageInfo.getVcEmail())?empMessageInfo.getVcEmail():"");
		 sdkEmpMessageInfo.setVcWorkPhone(Common.notEmpty(empMessageInfo.getVcWorkPhone())?MD5.getMD5(empMessageInfo.getVcWorkPhone()):"");
		 sdkEmpMessageInfo.setVcMobile(Common.notEmpty(empMessageInfo.getVcMobile())?MD5.getMD5(empMessageInfo.getVcMobile()):"");
		 sdkEmpMessageInfo.setDoperatorDate(Common.notEmpty(empMessageInfo.getdOperatorDate())?empMessageInfo.getdOperatorDate():"");
		 return sdkEmpMessageInfo;
	}
}