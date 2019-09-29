package com.softline.entity.bimr;

/**
 * BimrInsideAuditFeedback entity. @author MyEclipse Persistence Tools
 */

public class BimrInsideAuditFeedback implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer measuresId;
	private Integer rectifyId;
	private String measuresDesc;
	private String feedbackDesc;
	private String feedbackDate;
	private String status;

	// Constructors

	/** default constructor */
	public BimrInsideAuditFeedback() {
	}

	/** full constructor */
	public BimrInsideAuditFeedback(Integer rectifyId, Integer measuresId, String measuresDesc,
			String feedbackDesc, String feedbackDate, String status) {
		this.measuresId = measuresId;
		this.rectifyId = rectifyId;
		this.measuresDesc = measuresDesc;
		this.feedbackDesc = feedbackDesc;
		this.feedbackDate = feedbackDate;
		this.status = status;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRectifyId() {
		return rectifyId;
	}

	public void setRectifyId(Integer rectifyId) {
		this.rectifyId = rectifyId;
	}

	public Integer getMeasuresId() {
		return this.measuresId;
	}

	public void setMeasuresId(Integer measuresId) {
		this.measuresId = measuresId;
	}

	public String getMeasuresDesc() {
		return this.measuresDesc;
	}

	public void setMeasuresDesc(String measuresDesc) {
		this.measuresDesc = measuresDesc;
	}

	public String getFeedbackDesc() {
		return this.feedbackDesc;
	}

	public void setFeedbackDesc(String feedbackDesc) {
		this.feedbackDesc = feedbackDesc;
	}

	public String getFeedbackDate() {
		return this.feedbackDate;
	}

	public void setFeedbackDate(String feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}