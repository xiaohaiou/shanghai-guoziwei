package com.softline.entity.bimr;

import java.math.BigDecimal;



/**
 * BimrCivilcaseWeek entity. @author MyEclipse Persistence Tools
 */

public class BimrAccountable  implements java.io.Serializable { 

     private Integer id;
     private Integer projectId;
     private Integer questionId;
     private String accountabilityPersion;
     private String accountabilityPersionId;
     private Integer reponsibilityLevel;
     private Integer status;
     private String examineContent;
    private Integer projectType;
     private Integer isDel;
     
     private String projectName;
     private String projectCode;
     private String questionTopic;
	public BimrAccountable() {
		super();
	}
	public BimrAccountable(Integer id, Integer projectId, Integer questionId,
			String accountabilityPersion, Integer reponsibilityLevel,
			Integer status, String examineContent, String projectName,Integer isDel,
			String projectCode, String questionTopic,String accountabilityPersionId,Integer projectType) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.questionId = questionId;
		this.accountabilityPersion = accountabilityPersion;
		this.reponsibilityLevel = reponsibilityLevel;
		this.status = status;
		this.examineContent = examineContent;
		this.projectName = projectName;
		this.projectCode = projectCode;
		this.questionTopic = questionTopic;
		this.accountabilityPersionId=accountabilityPersionId;
		this.projectType=projectType;
		this.isDel=isDel;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getAccountabilityPersion() {
		return accountabilityPersion;
	}
	public void setAccountabilityPersion(String accountabilityPersion) {
		this.accountabilityPersion = accountabilityPersion;
	}
	public Integer getReponsibilityLevel() {
		return reponsibilityLevel;
	}
	public void setReponsibilityLevel(Integer reponsibilityLevel) {
		this.reponsibilityLevel = reponsibilityLevel;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getExamineContent() {
		return examineContent;
	}
	public void setExamineContent(String examineContent) {
		this.examineContent = examineContent;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getQuestionTopic() {
		return questionTopic;
	}
	public void setQuestionTopic(String questionTopic) {
		this.questionTopic = questionTopic;
	}
	public String getAccountabilityPersionId() {
		return accountabilityPersionId;
	}
	public void setAccountabilityPersionId(String accountabilityPersionId) {
		this.accountabilityPersionId = accountabilityPersionId;
	}
	public Integer getProjectType() {
		return projectType;
	}
	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
    
     
     

}