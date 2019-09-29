package com.softline.entity.bimr;

/**
 * BimrInsideAuditRectify entity. @author MyEclipse Persistence Tools
 */

public class BimrInsideAuditRectify implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer answerId;
	private Integer projectId;
	private String projectName;
	private String status;
	private String rectifyAdvice;
	private String problems;
	private String rectifyTime;
	private String isDel;
	private String rectifyResponseDeptId;
	private String rectifyResponseDept;
	private String rectifyPickerId;
	private String rectifyPickerName;
	private String rectifyResponseId;
	private String rectifyResponseName;
	private String rectifyTracerId;
	private String rectifyTracerName;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String reportPersonName;
	private String reportPersonId;
	private String reportDate;
	private String auditorPersonName;
	private String auditorPersonId;
	private String auditorDate;
	private String problemTopic;

	// Constructors

	/** default constructor */
	public BimrInsideAuditRectify() {
	}

	/** full constructor */
	public BimrInsideAuditRectify(Integer answerId, Integer projectId,
			String projectName, String status, String rectifyAdvice,
			String problems, String rectifyTime, String isDel,
			String rectifyResponseDeptId, String rectifyResponseDept,
			String rectifyPickerId, String rectifyPickerName,
			String rectifyResponseId, String rectifyResponseName,
			String rectifyTracerId, String rectifyTracerName,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String reportPersonName,
			String reportPersonId, String reportDate, String auditorPersonName,
			String auditorPersonId, String auditorDate) {
		this.answerId = answerId;
		this.projectId = projectId;
		this.projectName = projectName;
		this.status = status;
		this.rectifyAdvice = rectifyAdvice;
		this.problems = problems;
		this.rectifyTime = rectifyTime;
		this.isDel = isDel;
		this.rectifyResponseDeptId = rectifyResponseDeptId;
		this.rectifyResponseDept = rectifyResponseDept;
		this.rectifyPickerId = rectifyPickerId;
		this.rectifyPickerName = rectifyPickerName;
		this.rectifyResponseId = rectifyResponseId;
		this.rectifyResponseName = rectifyResponseName;
		this.rectifyTracerId = rectifyTracerId;
		this.rectifyTracerName = rectifyTracerName;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.reportPersonName = reportPersonName;
		this.reportPersonId = reportPersonId;
		this.reportDate = reportDate;
		this.auditorPersonName = auditorPersonName;
		this.auditorPersonId = auditorPersonId;
		this.auditorDate = auditorDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnswerId() {
		return this.answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRectifyAdvice() {
		return this.rectifyAdvice;
	}

	public void setRectifyAdvice(String rectifyAdvice) {
		this.rectifyAdvice = rectifyAdvice;
	}

	public String getProblems() {
		return this.problems;
	}

	public void setProblems(String problems) {
		this.problems = problems;
	}

	public String getRectifyTime() {
		return this.rectifyTime;
	}

	public void setRectifyTime(String rectifyTime) {
		this.rectifyTime = rectifyTime;
	}

	public String getIsDel() {
		return this.isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getRectifyResponseDeptId() {
		return this.rectifyResponseDeptId;
	}

	public void setRectifyResponseDeptId(String rectifyResponseDeptId) {
		this.rectifyResponseDeptId = rectifyResponseDeptId;
	}

	public String getRectifyResponseDept() {
		return this.rectifyResponseDept;
	}

	public void setRectifyResponseDept(String rectifyResponseDept) {
		this.rectifyResponseDept = rectifyResponseDept;
	}

	public String getRectifyPickerId() {
		return this.rectifyPickerId;
	}

	public void setRectifyPickerId(String rectifyPickerId) {
		this.rectifyPickerId = rectifyPickerId;
	}

	public String getRectifyPickerName() {
		return this.rectifyPickerName;
	}

	public void setRectifyPickerName(String rectifyPickerName) {
		this.rectifyPickerName = rectifyPickerName;
	}

	public String getRectifyResponseId() {
		return this.rectifyResponseId;
	}

	public void setRectifyResponseId(String rectifyResponseId) {
		this.rectifyResponseId = rectifyResponseId;
	}

	public String getRectifyResponseName() {
		return this.rectifyResponseName;
	}

	public void setRectifyResponseName(String rectifyResponseName) {
		this.rectifyResponseName = rectifyResponseName;
	}

	public String getRectifyTracerId() {
		return this.rectifyTracerId;
	}

	public void setRectifyTracerId(String rectifyTracerId) {
		this.rectifyTracerId = rectifyTracerId;
	}

	public String getRectifyTracerName() {
		return this.rectifyTracerName;
	}

	public void setRectifyTracerName(String rectifyTracerName) {
		this.rectifyTracerName = rectifyTracerName;
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

	public String getReportPersonName() {
		return this.reportPersonName;
	}

	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}

	public String getReportPersonId() {
		return this.reportPersonId;
	}

	public void setReportPersonId(String reportPersonId) {
		this.reportPersonId = reportPersonId;
	}

	public String getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
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

	public String getProblemTopic() {
		return problemTopic;
	}

	public void setProblemTopic(String problemTopic) {
		this.problemTopic = problemTopic;
	}
	

}