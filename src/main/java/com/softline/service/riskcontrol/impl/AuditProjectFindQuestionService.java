package com.softline.service.riskcontrol.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.dao.riskcontrol.IAuditProjectFindQuestionDao;
import com.softline.entity.AuditProjectFindQuestion;
import com.softline.service.riskcontrol.IAuditProjectFindQuestionService;
import com.softline.util.MsgPage;

@Service("auditProjectFindQuestionService")
public class AuditProjectFindQuestionService implements IAuditProjectFindQuestionService {

	@Autowired
	@Qualifier("auditProjectFindQuestionDao")
	private IAuditProjectFindQuestionDao auditProjectFindQuestionDao;

	@Override
	public MsgPage getAuditProjectFindQuestions(AuditProjectFindQuestion entity, Integer curPageNum, Integer pageSize) {
			MsgPage msgPage = new MsgPage();       
	        //总记录数
	        Integer totalRecords = auditProjectFindQuestionDao.getAuditProjectFindQuestionListCount(entity);
	    	//当前页开始记录
	    	int offset = msgPage.countOffset(curPageNum,pageSize);  
	    	//分页查询结果集
	    	List<AuditProjectFindQuestion> list = auditProjectFindQuestionDao.getAuditProjectFindQuestionList(entity, offset, pageSize);
	    	msgPage.setPageNum(curPageNum);
	    	msgPage.setPageSize(pageSize);
	    	msgPage.setTotalRecords(totalRecords);
	    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
	    	msgPage.setList(list);    
	        return msgPage;
	}

	@Override
	public Integer saveAuditProjectFindQuestion(AuditProjectFindQuestion entity) {
		return auditProjectFindQuestionDao.saveAuditProjectFindQuestion(entity);
	}

	@Override
	public void deleteAuditProjectFindQuestion(Integer id) {
		auditProjectFindQuestionDao.deleteAuditProjectFindQuestion(id);
	}

	@Override
	public AuditProjectFindQuestion getAuditProjectFindQuestion(Integer id) {
		AuditProjectFindQuestion auditProjectFindQuestion = new AuditProjectFindQuestion();
		auditProjectFindQuestion = auditProjectFindQuestionDao.getAuditProjectFindQuestionById(id);
		return auditProjectFindQuestion;
	}

	@Override
	public void updateAuditProjectFindQuestion(AuditProjectFindQuestion entity) {
		auditProjectFindQuestionDao.updateAuditProjectFindQuestion(entity);
	}

	@Override
	public AuditProjectFindQuestion getAuditProjectFindQuestionByAuditProjectId(Integer auditProjectId) {
		return auditProjectFindQuestionDao.getAuditProjectFindQuestionByAuditProjectId(auditProjectId);
	}
}
