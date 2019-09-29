package com.softline.entity.bimr;

/**
 * BimrRiskEventRelevancetrack entity. @author MyEclipse Persistence Tools
 */

public class BimrRiskEventRelevancetrack implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer eventid;
	private Integer takestrategy;
	private String takestrategytxt;
	private String takestrategyremark;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String auditorPersonName;
	private String auditorPersonId;
	private String auditorDate;
	private Integer status;
	private Integer isDel;

	private String statusName;
	// Constructors
	private String reportTime;
	private String compId;
	private String eventNum;
	private String eventTitle;
	private String dataauth;
	private String compName;


	/** default constructor */
	public BimrRiskEventRelevancetrack() {
	}

	/** full constructor */
	public BimrRiskEventRelevancetrack(Integer eventid, Integer takestrategy,
			String takestrategytxt, String takestrategyremark,
			String createPersonName, String createPersonId, String createDate,
			String auditorPersonName, String auditorPersonId,
			String auditorDate, Integer status, Integer isDel) {
		this.eventid = eventid;
		this.takestrategy = takestrategy;
		this.takestrategytxt = takestrategytxt;
		this.takestrategyremark = takestrategyremark;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.auditorPersonName = auditorPersonName;
		this.auditorPersonId = auditorPersonId;
		this.auditorDate = auditorDate;
		this.status = status;
		this.isDel = isDel;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEventid() {
		return this.eventid;
	}

	public void setEventid(Integer eventid) {
		this.eventid = eventid;
	}

	public Integer getTakestrategy() {
		return this.takestrategy;
	}

	public void setTakestrategy(Integer takestrategy) {
		this.takestrategy = takestrategy;
	}

	public String getTakestrategytxt() {
		return this.takestrategytxt;
	}

	public void setTakestrategytxt(String takestrategytxt) {
		this.takestrategytxt = takestrategytxt;
	}

	public String getTakestrategyremark() {
		return this.takestrategyremark;
	}

	public void setTakestrategyremark(String takestrategyremark) {
		this.takestrategyremark = takestrategyremark;
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

	public String getAuditorPersonName() {
		return this.auditorPersonName;
	}

	public void setAuditorPersonName(String auditorPersonName) {
		this.auditorPersonName = auditorPersonName;
	}

	public String getAuditorPersonId() {
		return this.auditorPersonId;
	}

	public void setAuditorPersonId(String auditorPersonId) {
		this.auditorPersonId = auditorPersonId;
	}

	public String getAuditorDate() {
		return this.auditorDate;
	}

	public void setAuditorDate(String auditorDate) {
		this.auditorDate = auditorDate;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getEventNum() {
		return eventNum;
	}

	public void setEventNum(String eventNum) {
		this.eventNum = eventNum;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getDataauth() {
		return dataauth;
	}

	public void setDataauth(String dataauth) {
		this.dataauth = dataauth;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

}