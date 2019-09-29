package com.softline.entity.bimr;

import java.io.Serializable;

public class BimrInsideAuditQuestion implements Serializable{
	
	private Integer id;
	private Integer projectId;
	private String projectName;
	private String problem;
	private Integer problemAttrUnitId;
	private String problemAttrUnitName;
	private Integer isImportant;
	private Integer isSuspected;
	private Integer isBlamed;
	private String auditSuggest;
	private Integer isRectify;
	private String rectifyTrackName;
	private String rectifyPicker;
	private String rectifyResponseName;
	private String rectifyTime;
	private String auditQuestionTypes;
	private String riskDriverTypes;
	private Integer isDel;
	private String createPersonName;
	private String createPersonId;
	private String createDate;
	private String lastModifyPersonId;
	private String lastModifyPersonName;
	private String lastModifyDate;
	private String rectifyResponseDept;
	private String rectifyTrackId;
	private String problemTopic;

	// Constructors

	/** default constructor */
	public BimrInsideAuditQuestion() {
	}

	/** full constructor */
	public BimrInsideAuditQuestion(Integer projectId, String projectName,
			String problem, Integer problemAttrUnitId,
			String problemAttrUnitName, Integer isImportant, Integer isSuspected,
			Integer isBlamed, String auditSuggest, Integer isRectify,
			String rectifyTrackName, String rectifyPicker,
			String rectifyResponseName, String rectifyTime,
			String auditQuestionTypes, String riskDriverTypes, Integer isDel,
			String createPersonName, String createPersonId, String createDate,
			String lastModifyPersonId, String lastModifyPersonName,
			String lastModifyDate, String rectifyResponseDept,
			String rectifyTrackId,String problemTopic) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.problem = problem;
		this.problemAttrUnitId = problemAttrUnitId;
		this.problemAttrUnitName = problemAttrUnitName;
		this.isImportant = isImportant;
		this.isSuspected = isSuspected;
		this.isBlamed = isBlamed;
		this.auditSuggest = auditSuggest;
		this.isRectify = isRectify;
		this.rectifyTrackName = rectifyTrackName;
		this.rectifyPicker = rectifyPicker;
		this.rectifyResponseName = rectifyResponseName;
		this.rectifyTime = rectifyTime;
		this.auditQuestionTypes = auditQuestionTypes;
		this.riskDriverTypes = riskDriverTypes;
		this.isDel = isDel;
		this.createPersonName = createPersonName;
		this.createPersonId = createPersonId;
		this.createDate = createDate;
		this.lastModifyPersonId = lastModifyPersonId;
		this.lastModifyPersonName = lastModifyPersonName;
		this.lastModifyDate = lastModifyDate;
		this.rectifyResponseDept = rectifyResponseDept;
		this.rectifyTrackId = rectifyTrackId;
		this.problemTopic=problemTopic;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getProblem() {
		return this.problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public Integer getProblemAttrUnitId() {
		return this.problemAttrUnitId;
	}

	public void setProblemAttrUnitId(Integer problemAttrUnitId) {
		this.problemAttrUnitId = problemAttrUnitId;
	}

	public String getProblemAttrUnitName() {
		return this.problemAttrUnitName;
	}

	public void setProblemAttrUnitName(String problemAttrUnitName) {
		this.problemAttrUnitName = problemAttrUnitName;
	}

	public Integer getIsImportant() {
		return this.isImportant;
	}

	public void setIsImportant(Integer isImportant) {
		this.isImportant = isImportant;
	}

	public Integer getIsSuspected() {
		return this.isSuspected;
	}

	public void setIsSuspected(Integer isSuspected) {
		this.isSuspected = isSuspected;
	}

	public Integer getIsBlamed() {
		return this.isBlamed;
	}

	public void setIsBlamed(Integer isBlamed) {
		this.isBlamed = isBlamed;
	}

	public String getAuditSuggest() {
		return this.auditSuggest;
	}

	public void setAuditSuggest(String auditSuggest) {
		this.auditSuggest = auditSuggest;
	}

	public Integer getIsRectify() {
		return this.isRectify;
	}

	public void setIsRectify(Integer isRectify) {
		this.isRectify = isRectify;
	}

	public String getRectifyTrackName() {
		return this.rectifyTrackName;
	}

	public void setRectifyTrackName(String rectifyTrackName) {
		this.rectifyTrackName = rectifyTrackName;
	}

	public String getRectifyPicker() {
		return this.rectifyPicker;
	}

	public void setRectifyPicker(String rectifyPicker) {
		this.rectifyPicker = rectifyPicker;
	}

	public String getRectifyResponseName() {
		return this.rectifyResponseName;
	}

	public void setRectifyResponseName(String rectifyResponseName) {
		this.rectifyResponseName = rectifyResponseName;
	}

	public String getRectifyTime() {
		return this.rectifyTime;
	}

	public void setRectifyTime(String rectifyTime) {
		this.rectifyTime = rectifyTime;
	}

	public String getAuditQuestionTypes() {
		return this.auditQuestionTypes;
	}

	public void setAuditQuestionTypes(String auditQuestionTypes) {
		this.auditQuestionTypes = auditQuestionTypes;
	}

	public String getRiskDriverTypes() {
		return this.riskDriverTypes;
	}

	public void setRiskDriverTypes(String riskDriverTypes) {
		this.riskDriverTypes = riskDriverTypes;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
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

	public String getRectifyResponseDept() {
		return this.rectifyResponseDept;
	}

	public void setRectifyResponseDept(String rectifyResponseDept) {
		this.rectifyResponseDept = rectifyResponseDept;
	}

	public String getRectifyTrackId() {
		return this.rectifyTrackId;
	}

	public void setRectifyTrackId(String rectifyTrackId) {
		this.rectifyTrackId = rectifyTrackId;
	}

	public String getProblemTopic() {
		return problemTopic;
	}

	public void setProblemTopic(String problemTopic) {
		this.problemTopic = problemTopic;
	}
}
