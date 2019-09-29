package com.softline.entity.bimr;

/**
 * BimrRiskEventFeedback entity. @author MyEclipse Persistence Tools
 */

public class BimrRiskEventFeedback implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer eventid;
	private String feedbacktime;
	private String nowdetailsituation;
	private String measuresituation;
	private Integer status;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String auditorPersonName;
	private String auditorPersonId;
	private String auditorDate;
	private Integer isDel;

	// Constructors

	/** default constructor */
	public BimrRiskEventFeedback() {
	}

	/** full constructor */
	public BimrRiskEventFeedback(Integer eventid, String feedbacktime,
			String nowdetailsituation, String measuresituation, Integer status,
			String createPersonName, String createPersonId, String createDate,
			String auditorPersonName, String auditorPersonId,
			String auditorDate, Integer isDel) {
		this.eventid = eventid;
		this.feedbacktime = feedbacktime;
		this.nowdetailsituation = nowdetailsituation;
		this.measuresituation = measuresituation;
		this.status = status;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.auditorPersonName = auditorPersonName;
		this.auditorPersonId = auditorPersonId;
		this.auditorDate = auditorDate;
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

	public String getFeedbacktime() {
		return this.feedbacktime;
	}

	public void setFeedbacktime(String feedbacktime) {
		this.feedbacktime = feedbacktime;
	}

	public String getNowdetailsituation() {
		return this.nowdetailsituation;
	}

	public void setNowdetailsituation(String nowdetailsituation) {
		this.nowdetailsituation = nowdetailsituation;
	}

	public String getMeasuresituation() {
		return this.measuresituation;
	}

	public void setMeasuresituation(String measuresituation) {
		this.measuresituation = measuresituation;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}