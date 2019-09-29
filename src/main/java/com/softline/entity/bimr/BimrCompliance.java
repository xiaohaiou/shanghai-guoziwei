package com.softline.entity.bimr;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * BimrCompliance entity. @author MyEclipse Persistence Tools
 */

public class BimrCompliance implements java.io.Serializable {

	// Fields

	private Integer id;
	private String projectName;
	private Integer investigationType;
	private String investigationDep;
	private String compId;
	private String compName;
	private String itemName;
	private Integer investigationFrom;
	private Integer isInside;
	private Integer isAnonymous;
	private String accusationTime;
	private String assignLeader;
	private String assignLeaderId;
	private String responsiblePerson;
	private String responsiblePersonId;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private Integer isDel;
	private Integer status;
	private String startTime;
	private String endTime;
	private String examineContent;
	private Integer accountabilityStatus;
	private String investigationName;
	private String investigationId;
	private String statusName;
	private String investigationTypeName;
	private String investigationFromName;
	private String searchType;

	private String followPersionId;
	private String indictment;
	private String remark;
	private String investigationMatters;
	
	
	
	private String projectCode;
	private String vcCompOrganID;
	/*
	 * private List<BimrCompliancePerson> persons =new
	 * ArrayList<BimrCompliancePerson>();
	 */
	// 数据权限
	private String dataauth;

	// Constructors

	/** default constructor */
	public BimrCompliance() {
	}

	/** minimal constructor */
	public BimrCompliance(Integer isDel) {
		this.isDel = isDel;
	}

	/** full constructor */
	public BimrCompliance(String projectName, Integer investigationType,
			String investigationDep, String compId, String compName,
			String itemName, Integer investigationFrom, Integer isInside,
			Integer isAnonymous, String accusationTime, String assignLeader,
			String responsiblePerson, String createPersonName,
			String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, Integer isDel, Integer status,
			String followPersionId, Integer accountabilityStatus,
			String investigationName, String investigationId,
			String indictment, String investigationMatters, String dataauth,String remark,String projectCode,String vcCompOrganID) {
		this.projectName = projectName;
		this.investigationType = investigationType;
		this.investigationDep = investigationDep;
		this.compName = compName;
		this.itemName = itemName;
		this.investigationFrom = investigationFrom;
		this.isInside = isInside;
		this.isAnonymous = isAnonymous;
		this.accusationTime = accusationTime;
		this.assignLeader = assignLeader;
		this.responsiblePerson = responsiblePerson;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.isDel = isDel;
		this.status = status;
		this.followPersionId = followPersionId;
		this.indictment = indictment;
		this.investigationMatters = investigationMatters;
		this.accountabilityStatus = accountabilityStatus;
		this.investigationName = investigationName;
		this.investigationId = investigationId;
		this.dataauth = dataauth;
		this.remark=remark;
		this.projectCode=projectCode;
		this.vcCompOrganID=vcCompOrganID;
	}

	public String getDataauth() {
		return dataauth;
	}

	public void setDataauth(String dataauth) {
		this.dataauth = dataauth;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getInvestigationType() {
		return this.investigationType;
	}

	public void setInvestigationType(Integer investigationType) {
		this.investigationType = investigationType;
	}

	public String getInvestigationDep() {
		return this.investigationDep;
	}

	public void setInvestigationDep(String investigationDep) {
		this.investigationDep = investigationDep;
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getCompName() {
		return this.compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getInvestigationFrom() {
		return this.investigationFrom;
	}

	public void setInvestigationFrom(Integer investigationFrom) {
		this.investigationFrom = investigationFrom;
	}

	public Integer getIsInside() {
		return this.isInside;
	}

	public void setIsInside(Integer isInside) {
		this.isInside = isInside;
	}

	public Integer getIsAnonymous() {
		return this.isAnonymous;
	}

	public void setIsAnonymous(Integer isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public String getAccusationTime() {
		return this.accusationTime;
	}

	public void setAccusationTime(String accusationTime) {
		this.accusationTime = accusationTime;
	}

	public String getAssignLeader() {
		return this.assignLeader;
	}

	public void setAssignLeader(String assignLeader) {
		this.assignLeader = assignLeader;
	}

	public String getResponsiblePerson() {
		return this.responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public String getCreatePersonName() {
		return this.createPersonName;
	}

	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}

	public String getCreatePersonId() {
		return this.createPersonId;
	}

	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getInvestigationTypeName() {
		return investigationTypeName;
	}

	public void setInvestigationTypeName(String investigationTypeName) {
		this.investigationTypeName = investigationTypeName;
	}

	public String getAssignLeaderId() {
		return assignLeaderId;
	}

	public void setAssignLeaderId(String assignLeaderId) {
		this.assignLeaderId = assignLeaderId;
	}

	public String getResponsiblePersonId() {
		return responsiblePersonId;
	}

	public void setResponsiblePersonId(String responsiblePersonId) {
		this.responsiblePersonId = responsiblePersonId;
	}

	public String getInvestigationFromName() {
		return investigationFromName;
	}

	public void setInvestigationFromName(String investigationFromName) {
		this.investigationFromName = investigationFromName;
	}

	public String getExamineContent() {
		return examineContent;
	}

	public void setExamineContent(String examineContent) {
		this.examineContent = examineContent;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	/*
	 * public List<BimrCompliancePerson> getPersons() { return persons; }
	 * 
	 * public void setPersons(List<BimrCompliancePerson> persons) { this.persons
	 * = persons; }
	 */

	public String getFollowPersionId() {
		return followPersionId;
	}

	public void setFollowPersionId(String followPersionId) {
		this.followPersionId = followPersionId;
	}

	public Integer getAccountabilityStatus() {
		return accountabilityStatus;
	}

	public void setAccountabilityStatus(Integer accountabilityStatus) {
		this.accountabilityStatus = accountabilityStatus;
	}

	public String getInvestigationName() {
		return investigationName;
	}

	public void setInvestigationName(String investigationName) {
		this.investigationName = investigationName;
	}

	public String getInvestigationId() {
		return investigationId;
	}

	public void setInvestigationId(String investigationId) {
		this.investigationId = investigationId;
	}

	public String getIndictment() {
		return indictment;
	}

	public void setIndictment(String indictment) {
		this.indictment = indictment;
	}

	public String getInvestigationMatters() {
		return investigationMatters;
	}

	public void setInvestigationMatters(String investigationMatters) {
		this.investigationMatters = investigationMatters;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getVcCompOrganID() {
		return vcCompOrganID;
	}

	public void setVcCompOrganID(String vcCompOrganID) {
		this.vcCompOrganID = vcCompOrganID;
	}
	
	

}