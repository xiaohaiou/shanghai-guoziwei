package com.softline.entity.bimr;

/**
 * BimrRiskEvent entity. @author MyEclipse Persistence Tools
 */

public class BimrRiskEvent implements java.io.Serializable {

	// Fields

	private Integer id;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private Integer isDel;
	private Integer status;
	private String compName;
	private String compId;
	private String eventNum;
	private String reportTime;
	private String eventTitle;
	private String eventDescribe;
	private String eventForRisk;
	private Integer copingStrategy;
	private Integer provinceId;
	private String adoptStrategy;
	private String responsibleCompName;
	private String responsibleCompId;
	private String responsibleDep;
	private String responsiblePerson;
	private String responsiblePersonId;
	private String completeTime;
	private String remark;
	private Integer isImportant;
	private Integer ishighunit;
	private Integer highunitmeasure;
	private String planmeasure;
	private Integer plancloesetime;
	private String trackpersonid;
	private String trackpersonname;
	private String insidetrackpersonid;
	private String insidetrackpersonname;
	private String coreorg;
	private String coreorgname;
	private String relevancePersonId;
	private String relevancePerson;
	private String relevanceauditorId;
	private String relevanceauditor;
	private Integer relevancestatus;

	// Constructors
	private String statusName;
	private String copingStrategyName;
	private String province;
	private String dataauth;
	private String relevancethreeName;
	private String op;
	
	//每日反馈临时字段
	private String feedbacktime;
	private String nowdetailsituation;
	private Integer feedbackstatus;
	private String measuresituation;
	private String nowfinishsituation;
	
	private String vcOrganID;
	
	
	/** default constructor */
	public BimrRiskEvent() {
	}

	/** minimal constructor */
	public BimrRiskEvent(Integer isDel) {
		this.isDel = isDel;
	}

	/** full constructor */
	public BimrRiskEvent(String createPersonName, String createPersonId,
			String createDate, String lastModifyPersonId,
			String lastModifyPersonName, String lastModifyDate, Integer isDel,
			Integer status, String compName, String compId, String eventNum,
			String reportTime, String eventTitle, String eventDescribe,
			String eventForRisk, Integer copingStrategy, Integer provinceId,
			String adoptStrategy, String responsibleCompName,
			String responsibleCompId, String responsibleDep,
			String responsiblePerson, String responsiblePersonId,
			String completeTime, String remark, Integer isImportant,
			Integer ishighunit, Integer highunitmeasure, String planmeasure,
			Integer plancloesetime, String trackpersonid,
			String trackpersonname, String insidetrackpersonid,
			String insidetrackpersonname, String coreorg, String coreorgname,
			String relevancePersonId, String relevancePerson,
			String relevanceauditorId, String relevanceauditor,
			Integer relevancestatus) {
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.isDel = isDel;
		this.status = status;
		this.compName = compName;
		this.compId = compId;
		this.eventNum = eventNum;
		this.reportTime = reportTime;
		this.eventTitle = eventTitle;
		this.eventDescribe = eventDescribe;
		this.eventForRisk = eventForRisk;
		this.copingStrategy = copingStrategy;
		this.provinceId = provinceId;
		this.adoptStrategy = adoptStrategy;
		this.responsibleCompName = responsibleCompName;
		this.responsibleCompId = responsibleCompId;
		this.responsibleDep = responsibleDep;
		this.responsiblePerson = responsiblePerson;
		this.responsiblePersonId = responsiblePersonId;
		this.completeTime = completeTime;
		this.remark = remark;
		this.isImportant = isImportant;
		this.ishighunit = ishighunit;
		this.highunitmeasure = highunitmeasure;
		this.planmeasure = planmeasure;
		this.plancloesetime = plancloesetime;
		this.trackpersonid = trackpersonid;
		this.trackpersonname = trackpersonname;
		this.insidetrackpersonid = insidetrackpersonid;
		this.insidetrackpersonname = insidetrackpersonname;
		this.coreorg = coreorg;
		this.coreorgname = coreorgname;
		this.relevancePersonId = relevancePersonId;
		this.relevancePerson = relevancePerson;
		this.relevanceauditorId = relevanceauditorId;
		this.relevanceauditor = relevanceauditor;
		this.relevancestatus = relevancestatus;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCompName() {
		return this.compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCompId() {
		return this.compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getEventNum() {
		return this.eventNum;
	}

	public void setEventNum(String eventNum) {
		this.eventNum = eventNum;
	}

	public String getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getEventTitle() {
		return this.eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getEventDescribe() {
		return this.eventDescribe;
	}

	public void setEventDescribe(String eventDescribe) {
		this.eventDescribe = eventDescribe;
	}

	public String getEventForRisk() {
		return this.eventForRisk;
	}

	public void setEventForRisk(String eventForRisk) {
		this.eventForRisk = eventForRisk;
	}

	public Integer getCopingStrategy() {
		return this.copingStrategy;
	}

	public void setCopingStrategy(Integer copingStrategy) {
		this.copingStrategy = copingStrategy;
	}

	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getAdoptStrategy() {
		return this.adoptStrategy;
	}

	public void setAdoptStrategy(String adoptStrategy) {
		this.adoptStrategy = adoptStrategy;
	}

	public String getResponsibleCompName() {
		return this.responsibleCompName;
	}

	public void setResponsibleCompName(String responsibleCompName) {
		this.responsibleCompName = responsibleCompName;
	}

	public String getResponsibleCompId() {
		return this.responsibleCompId;
	}

	public void setResponsibleCompId(String responsibleCompId) {
		this.responsibleCompId = responsibleCompId;
	}

	public String getResponsibleDep() {
		return this.responsibleDep;
	}

	public void setResponsibleDep(String responsibleDep) {
		this.responsibleDep = responsibleDep;
	}

	public String getResponsiblePerson() {
		return this.responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public String getResponsiblePersonId() {
		return this.responsiblePersonId;
	}

	public void setResponsiblePersonId(String responsiblePersonId) {
		this.responsiblePersonId = responsiblePersonId;
	}

	public String getCompleteTime() {
		return this.completeTime;
	}

	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsImportant() {
		return this.isImportant;
	}

	public void setIsImportant(Integer isImportant) {
		this.isImportant = isImportant;
	}

	public Integer getIshighunit() {
		return this.ishighunit;
	}

	public void setIshighunit(Integer ishighunit) {
		this.ishighunit = ishighunit;
	}

	public Integer getHighunitmeasure() {
		return this.highunitmeasure;
	}

	public void setHighunitmeasure(Integer highunitmeasure) {
		this.highunitmeasure = highunitmeasure;
	}

	public String getPlanmeasure() {
		return this.planmeasure;
	}

	public void setPlanmeasure(String planmeasure) {
		this.planmeasure = planmeasure;
	}

	public Integer getPlancloesetime() {
		return this.plancloesetime;
	}

	public void setPlancloesetime(Integer plancloesetime) {
		this.plancloesetime = plancloesetime;
	}

	public String getTrackpersonid() {
		return this.trackpersonid;
	}

	public void setTrackpersonid(String trackpersonid) {
		this.trackpersonid = trackpersonid;
	}

	public String getTrackpersonname() {
		return this.trackpersonname;
	}

	public void setTrackpersonname(String trackpersonname) {
		this.trackpersonname = trackpersonname;
	}

	public String getInsidetrackpersonid() {
		return this.insidetrackpersonid;
	}

	public void setInsidetrackpersonid(String insidetrackpersonid) {
		this.insidetrackpersonid = insidetrackpersonid;
	}

	public String getInsidetrackpersonname() {
		return this.insidetrackpersonname;
	}

	public void setInsidetrackpersonname(String insidetrackpersonname) {
		this.insidetrackpersonname = insidetrackpersonname;
	}

	public String getCoreorg() {
		return this.coreorg;
	}

	public void setCoreorg(String coreorg) {
		this.coreorg = coreorg;
	}

	public String getCoreorgname() {
		return this.coreorgname;
	}

	public void setCoreorgname(String coreorgname) {
		this.coreorgname = coreorgname;
	}

	public String getRelevancePersonId() {
		return this.relevancePersonId;
	}

	public void setRelevancePersonId(String relevancePersonId) {
		this.relevancePersonId = relevancePersonId;
	}

	public String getRelevancePerson() {
		return this.relevancePerson;
	}

	public void setRelevancePerson(String relevancePerson) {
		this.relevancePerson = relevancePerson;
	}

	public String getRelevanceauditorId() {
		return this.relevanceauditorId;
	}

	public void setRelevanceauditorId(String relevanceauditorId) {
		this.relevanceauditorId = relevanceauditorId;
	}

	public String getRelevanceauditor() {
		return this.relevanceauditor;
	}

	public void setRelevanceauditor(String relevanceauditor) {
		this.relevanceauditor = relevanceauditor;
	}

	public Integer getRelevancestatus() {
		return this.relevancestatus;
	}

	public void setRelevancestatus(Integer relevancestatus) {
		this.relevancestatus = relevancestatus;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getCopingStrategyName() {
		return copingStrategyName;
	}

	public void setCopingStrategyName(String copingStrategyName) {
		this.copingStrategyName = copingStrategyName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDataauth() {
		return dataauth;
	}

	public void setDataauth(String dataauth) {
		this.dataauth = dataauth;
	}

	public String getRelevancethreeName() {
		return relevancethreeName;
	}

	public void setRelevancethreeName(String relevancethreeName) {
		this.relevancethreeName = relevancethreeName;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getFeedbacktime() {
		return feedbacktime;
	}

	public void setFeedbacktime(String feedbacktime) {
		this.feedbacktime = feedbacktime;
	}

	public String getNowdetailsituation() {
		return nowdetailsituation;
	}

	public void setNowdetailsituation(String nowdetailsituation) {
		this.nowdetailsituation = nowdetailsituation;
	}

	public Integer getFeedbackstatus() {
		return feedbackstatus;
	}

	public void setFeedbackstatus(Integer feedbackstatus) {
		this.feedbackstatus = feedbackstatus;
	}

	public String getMeasuresituation() {
		return measuresituation;
	}

	public void setMeasuresituation(String measuresituation) {
		this.measuresituation = measuresituation;
	}

	public String getNowfinishsituation() {
		return nowfinishsituation;
	}

	public void setNowfinishsituation(String nowfinishsituation) {
		this.nowfinishsituation = nowfinishsituation;
	}

	public String getVcOrganID() {
		return vcOrganID;
	}

	public void setVcOrganID(String vcOrganID) {
		this.vcOrganID = vcOrganID;
	}

}