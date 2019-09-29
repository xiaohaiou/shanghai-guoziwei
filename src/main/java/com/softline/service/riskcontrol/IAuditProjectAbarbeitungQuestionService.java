package com.softline.service.riskcontrol;

import java.util.List;

import com.softline.entity.AuditProjectAbarbeitungQuestion;
import com.softline.util.MsgPage;

public interface IAuditProjectAbarbeitungQuestionService {

	MsgPage getAuditProjectAbarbeitungQuestions(AuditProjectAbarbeitungQuestion entity, Integer curPageNum, Integer pageSize);

	Integer saveAuditProjectAbarbeitungQuestion(AuditProjectAbarbeitungQuestion entity);
	
	void updateAuditProjectAbarbeitungQuestion(AuditProjectAbarbeitungQuestion entity);

	void deleteAuditProjectAbarbeitungQuestion(Integer id);

	AuditProjectAbarbeitungQuestion getAuditProjectAbarbeitungQuestion(Integer id);
		
	List<AuditProjectAbarbeitungQuestion> getAuditProjectAbarbeitungQuestionByAuditProjectId(Integer auditProjectId);
}
