package com.softline.dao.riskcontrol;

import java.util.List;

import com.softline.entity.AuditProjectFindQuestion;

public interface IAuditProjectFindQuestionDao {
	
	Integer getAuditProjectFindQuestionListCount(AuditProjectFindQuestion entity);
	
	List<AuditProjectFindQuestion> getAuditProjectFindQuestionList(AuditProjectFindQuestion entity, Integer offset,	Integer pageSize);

	Integer saveAuditProjectFindQuestion(AuditProjectFindQuestion entity);
	
    void updateAuditProjectFindQuestion(AuditProjectFindQuestion entity);
	
    void deleteAuditProjectFindQuestion(Integer id);

    AuditProjectFindQuestion getAuditProjectFindQuestionById(Integer id);
	
    AuditProjectFindQuestion getAuditProjectFindQuestionByAuditProjectId(Integer auditProjectId);
}
