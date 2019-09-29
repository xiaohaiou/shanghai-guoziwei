package com.softline.dao.riskcontrol;

import java.util.List;

import com.softline.entity.AuditProjectAbarbeitungQuestion;

public interface IAuditProjectAbarbeitungQuestionDao {
	
	Integer getAuditProjectAbarbeitungQuestionListCount(AuditProjectAbarbeitungQuestion entity);
	
	List<AuditProjectAbarbeitungQuestion> getAuditProjectAbarbeitungQuestionList(AuditProjectAbarbeitungQuestion entity, Integer offset,	Integer pageSize);

	Integer saveAuditProjectAbarbeitungQuestion(AuditProjectAbarbeitungQuestion entity);
	
    void updateAuditProjectAbarbeitungQuestion(AuditProjectAbarbeitungQuestion entity);
	
    void deleteAuditProjectAbarbeitungQuestion(Integer id);

    AuditProjectAbarbeitungQuestion getAuditProjectAbarbeitungQuestionById(Integer id);
	
    List<AuditProjectAbarbeitungQuestion> getAuditProjectAbarbeitungQuestionByAuditProjectId(Integer auditProjectId);
}
