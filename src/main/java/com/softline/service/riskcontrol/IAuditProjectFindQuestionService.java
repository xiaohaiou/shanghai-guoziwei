package com.softline.service.riskcontrol;

import com.softline.entity.AuditProjectFindQuestion;
import com.softline.util.MsgPage;

public interface IAuditProjectFindQuestionService {

	MsgPage getAuditProjectFindQuestions(AuditProjectFindQuestion entity, Integer curPageNum, Integer pageSize);

	Integer saveAuditProjectFindQuestion(AuditProjectFindQuestion entity);
	
	void updateAuditProjectFindQuestion(AuditProjectFindQuestion entity);

	void deleteAuditProjectFindQuestion(Integer id);

	AuditProjectFindQuestion getAuditProjectFindQuestion(Integer id);
	
	AuditProjectFindQuestion getAuditProjectFindQuestionByAuditProjectId(Integer auditProjectId);
}
