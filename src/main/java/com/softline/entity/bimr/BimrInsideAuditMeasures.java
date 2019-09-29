package com.softline.entity.bimr;

/**
 * BimrInsideAuditMeasures entity. @author MyEclipse Persistence Tools
 */

public class BimrInsideAuditMeasures implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer rectifyId;
	private Integer projectId;
	private String rectifyMeasure;
//	整改措施
	private String status;
//	整改措施状态
	private String feedbackDesc;
//	跟踪描述
	private String rectifyMeasureTime;
//	整改完成时限

	// Constructors

	/** default constructor */
	public BimrInsideAuditMeasures() {
	}

	/** full constructor */
	public BimrInsideAuditMeasures(Integer id, Integer rectifyId,
			Integer projectId, String rectifyMeasure, String status,
			String feedbackDesc,String rectifyMeasureTime) {
		this.id = id;
		this.rectifyId = rectifyId;
		this.projectId = projectId;
		this.rectifyMeasure = rectifyMeasure;
		this.status = status;
		this.feedbackDesc = feedbackDesc;
		this.rectifyMeasureTime = rectifyMeasureTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRectifyId() {
		return this.rectifyId;
	}

	public void setRectifyId(Integer rectifyId) {
		this.rectifyId = rectifyId;
	}

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getRectifyMeasure() {
		return this.rectifyMeasure;
	}

	public void setRectifyMeasure(String rectifyMeasure) {
		this.rectifyMeasure = rectifyMeasure;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFeedbackDesc() {
		return feedbackDesc;
	}

	public void setFeedbackDesc(String feedbackDesc) {
		this.feedbackDesc = feedbackDesc;
	}

	public String getRectifyMeasureTime() {
		return rectifyMeasureTime;
	}

	public void setRectifyMeasureTime(String rectifyMeasureTime) {
		this.rectifyMeasureTime = rectifyMeasureTime;
	}

}