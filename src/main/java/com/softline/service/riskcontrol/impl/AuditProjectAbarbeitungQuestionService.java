package com.softline.service.riskcontrol.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.dao.riskcontrol.IAuditProjectAbarbeitungQuestionDao;
import com.softline.entity.AuditProjectAbarbeitungQuestion;
import com.softline.service.riskcontrol.IAuditProjectAbarbeitungQuestionService;
import com.softline.util.MsgPage;

@Service("auditProjectAbarbeitungQuestionService")
public class AuditProjectAbarbeitungQuestionService implements IAuditProjectAbarbeitungQuestionService {

	@Autowired
	@Qualifier("auditProjectAbarbeitungQuestionDao")
	private IAuditProjectAbarbeitungQuestionDao auditProjectAbarbeitungQuestionDao;

	@Override
	public MsgPage getAuditProjectAbarbeitungQuestions(AuditProjectAbarbeitungQuestion entity, Integer curPageNum, Integer pageSize) {
			MsgPage msgPage = new MsgPage();       
	        //总记录数
	        Integer totalRecords = auditProjectAbarbeitungQuestionDao.getAuditProjectAbarbeitungQuestionListCount(entity);
	    	//当前页开始记录
	    	int offset = msgPage.countOffset(curPageNum,pageSize);  
	    	//分页查询结果集
	    	List<AuditProjectAbarbeitungQuestion> list = auditProjectAbarbeitungQuestionDao.getAuditProjectAbarbeitungQuestionList(entity, offset, pageSize);
	    	msgPage.setPageNum(curPageNum);
	    	msgPage.setPageSize(pageSize);
	    	msgPage.setTotalRecords(totalRecords);
	    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
	    	msgPage.setList(list);    
	        return msgPage;
	}

	@Override
	public Integer saveAuditProjectAbarbeitungQuestion(AuditProjectAbarbeitungQuestion entity) {
		return auditProjectAbarbeitungQuestionDao.saveAuditProjectAbarbeitungQuestion(entity);
	}

	@Override
	public void deleteAuditProjectAbarbeitungQuestion(Integer id) {
		auditProjectAbarbeitungQuestionDao.deleteAuditProjectAbarbeitungQuestion(id);
	}

	@Override
	public AuditProjectAbarbeitungQuestion getAuditProjectAbarbeitungQuestion(Integer id) {
		AuditProjectAbarbeitungQuestion auditProjectAbarbeitungQuestion = new AuditProjectAbarbeitungQuestion();
		auditProjectAbarbeitungQuestion = auditProjectAbarbeitungQuestionDao.getAuditProjectAbarbeitungQuestionById(id);
		return auditProjectAbarbeitungQuestion;
	}

	@Override
	public void updateAuditProjectAbarbeitungQuestion(AuditProjectAbarbeitungQuestion entity) {
		auditProjectAbarbeitungQuestionDao.updateAuditProjectAbarbeitungQuestion(entity);
	}

	@Override
	public List<AuditProjectAbarbeitungQuestion> getAuditProjectAbarbeitungQuestionByAuditProjectId(
			Integer auditProjectId) {
		return auditProjectAbarbeitungQuestionDao.getAuditProjectAbarbeitungQuestionByAuditProjectId(auditProjectId);
	}
}
