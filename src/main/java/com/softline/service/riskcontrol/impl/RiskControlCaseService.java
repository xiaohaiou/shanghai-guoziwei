package com.softline.service.riskcontrol.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Base;
import com.softline.dao.riskcontrol.IRiskControlCaseDao;
import com.softline.entity.RiskControlCase;
import com.softline.entity.HhUsers;
import com.softline.service.riskcontrol.IRiskControlCaseService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

@Service("riskControlCaseService")
public class RiskControlCaseService implements IRiskControlCaseService {

	@Autowired
	@Qualifier("riskControlCaseDao")
	private IRiskControlCaseDao riskControlCaseDao;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	

	@Override
	public MsgPage getCases(RiskControlCase entity, Integer curPageNum,
			Integer pageSize, String caseHappenDate2, String dataAuthority) {
			MsgPage msgPage = new MsgPage();       
	        //总记录数
	        Integer totalRecords = riskControlCaseDao.getCaseListCount(entity, caseHappenDate2, dataAuthority);
	    	//当前页开始记录
	    	int offset = msgPage.countOffset(curPageNum,pageSize);  
	    	//分页查询结果集
	    	List<RiskControlCase> list = riskControlCaseDao.getCaseList(entity, offset, pageSize, caseHappenDate2, dataAuthority);
	    	msgPage.setPageNum(curPageNum);
	    	msgPage.setPageSize(pageSize);
	    	msgPage.setTotalRecords(totalRecords);
	    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
	    	msgPage.setList(list);    
	        return msgPage;
	}

	@Override
	public Integer saveCase(RiskControlCase entity) {
		return riskControlCaseDao.saveCase(entity);
	}

	@Override
	public void deleteCase(Integer id) {
		riskControlCaseDao.deleteCase(id);
	}

	@Override
	public RiskControlCase getCase(Integer id) {
		return riskControlCaseDao.getCaseById(id);
	}

	@Override
	public void updateCase(RiskControlCase entity) {
		riskControlCaseDao.updateCase(entity);
	}

	@Override
	public RiskControlCase getCase(RiskControlCase entity) {
		return riskControlCaseDao.getCase(entity);
	}

	@Override
	public void saveCaseExamine(Integer entityId, String examStr, 
			Integer examResult, HhUsers user) {
		RiskControlCase entity = getCase(entityId);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//审核不通过
		if(examResult.equals(Base.examresult_1)){
			entity.setStatus(Base.examstatus_3);
		}else if(examResult.equals(Base.examresult_2)){ //审核通过
			entity.setStatus(Base.examstatus_4);
		}
		entity.setAuditorDate(df.format(new Date()));
		entity.setAuditorPersonId(user.getVcEmployeeId());
		entity.setAuditorPersonName(user.getVcName());
		updateCase(entity);
		examineService.saveExamine(entity.getId(), Base.examkind_riskcontrol_case, user, examStr, examResult);
	}

	@Override
	public MsgPage getExamineCases(RiskControlCase entity, Integer curPageNum,
			Integer pageSize, String caseHappenDate2, String dataAuthority) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = riskControlCaseDao.getExamineCaseListCount(entity, caseHappenDate2, dataAuthority);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<RiskControlCase> list = riskControlCaseDao.getExamineCaseList(entity, offset, pageSize, caseHappenDate2, dataAuthority);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
}
