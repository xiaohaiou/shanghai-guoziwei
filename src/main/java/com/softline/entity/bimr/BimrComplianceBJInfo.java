package com.softline.entity.bimr;

import java.io.Serializable;

/**
 * 合规调查办结信息
 * 
 * @author liu
 */
@SuppressWarnings("serial")
public class BimrComplianceBJInfo implements Serializable{
	private Integer id;
	private Integer complianceId;
	private String  officeId;
	private String submitPersonName;
	private String auditPersonName;
	private String name;
	private String submitTime;
	private String auditContent;
	private String createDate;
	private String createPersonId;
	private String createPersonName;
	private Integer isDel;
	
	public BimrComplianceBJInfo(){
		//none instance
	}
	
	public BimrComplianceBJInfo(Integer id, Integer complianceId,
			String officeId, String submitPersonName,
			String auditPersonName, String name, String submitTime,
			String auditContent, String createDate, String createPersonId,
			String createPersonName, Integer isDel) {
		
		super();
		this.id = id;
		this.complianceId = complianceId;
		this.officeId = officeId;
		this.submitPersonName = submitPersonName;
		this.auditPersonName = auditPersonName;
		this.name = name;
		this.submitTime = submitTime;
		this.auditContent = auditContent;
		this.createDate = createDate;
		this.createPersonId = createPersonId;
		this.createPersonName = createPersonName;
		this.isDel = isDel;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getComplianceId() {
		return complianceId;
	}
	public void setComplianceId(Integer complianceId) {
		this.complianceId = complianceId;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getSubmitPersonName() {
		return submitPersonName;
	}
	public void setSubmitPersonName(String submitPersonName) {
		this.submitPersonName = submitPersonName;
	}
	public String getAuditPersonName() {
		return auditPersonName;
	}
	public void setAuditPersonName(String auditPersonName) {
		this.auditPersonName = auditPersonName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public String getAuditContent() {
		return auditContent;
	}
	public void setAuditContent(String auditContent) {
		this.auditContent = auditContent;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreatePersonId() {
		return createPersonId;
	}
	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}
	public String getCreatePersonName() {
		return createPersonName;
	}
	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
}
