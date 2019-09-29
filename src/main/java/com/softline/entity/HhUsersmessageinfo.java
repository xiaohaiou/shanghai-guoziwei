package com.softline.entity;

import com.softline.common.Common;
import com.softline.entityTemp.SDK_empMessageInfo;
import com.softline.util.MD5;

/**
 * HhUsersmessageinfo entity. @author MyEclipse Persistence Tools
 */

public class HhUsersmessageinfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vcEmployeeId;
	private String vcEmail;
	private String vcWorkPhone;
	private String vcMobile;
	private String doperatorDate;
	private String createDate;
	private String createPersonId;
	private String createPersonName;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String department;
	// Constructors

	/** default constructor */
	public HhUsersmessageinfo() {
	}

	/** minimal constructor */
	public HhUsersmessageinfo(String vcEmployeeId) {
		this.vcEmployeeId = vcEmployeeId;
	}

	/** full constructor */
	public HhUsersmessageinfo(String vcEmployeeId, String vcEmail,
			String vcWorkPhone, String vcMobile, String doperatorDate,
			String createDate, String createPersonId, String createPersonName,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate) {
		this.vcEmployeeId = vcEmployeeId;
		this.vcEmail = vcEmail;
		this.vcWorkPhone = vcWorkPhone;
		this.vcMobile = vcMobile;
		this.doperatorDate = doperatorDate;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public static HhUsersmessageinfo ConvertTo(HhUsersmessageinfo hhUsersmessageinfo, SDK_empMessageInfo empMessageInfo)
	{
		if(hhUsersmessageinfo==null)
			 hhUsersmessageinfo = new HhUsersmessageinfo();
		hhUsersmessageinfo.setVcEmployeeId(Common.notEmpty(empMessageInfo.getVcEmployeeID())?empMessageInfo.getVcEmployeeID():"");
		hhUsersmessageinfo.setVcEmail(Common.notEmpty(empMessageInfo.getVcEmail())?empMessageInfo.getVcEmail():"");
		hhUsersmessageinfo.setVcWorkPhone(Common.notEmpty(empMessageInfo.getVcWorkPhone())?MD5.getMD5(empMessageInfo.getVcWorkPhone()):"");
		hhUsersmessageinfo.setVcMobile(Common.notEmpty(empMessageInfo.getVcMobile())?MD5.getMD5(empMessageInfo.getVcMobile()):"");
		hhUsersmessageinfo.setDoperatorDate(Common.notEmpty(empMessageInfo.getdOperatorDate())?empMessageInfo.getdOperatorDate():"");
		 return hhUsersmessageinfo;
	}
}