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
import com.softline.dao.riskcontrol.IAuditProjectDao;
import com.softline.entity.AdDocument;
import com.softline.entity.AuditProject;
import com.softline.entity.AuditProjectAbarbeitungQuestion;
import com.softline.entity.AuditProjectFindQuestion;
import com.softline.entity.HhUsers;
import com.softline.entityTemp.CommitResult;
import com.softline.service.riskcontrol.IAuditProjectAbarbeitungQuestionService;
import com.softline.service.riskcontrol.IAuditProjectFindQuestionService;
import com.softline.service.riskcontrol.IAuditProjectService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

@Service("auditProjectService")
public class AuditProjectService implements IAuditProjectService {

	@Autowired
	@Qualifier("auditProjectDao")
	private IAuditProjectDao auditProjectDao;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	@Resource(name = "auditProjectFindQuestionService")
	private IAuditProjectFindQuestionService auditProjectFindQuestionService;
	
	@Resource(name = "auditProjectAbarbeitungQuestionService")
	private IAuditProjectAbarbeitungQuestionService auditProjectAbarbeitungQuestionService;

	@Override
	public MsgPage getAuditProjects(AuditProject entity, Integer curPageNum, Integer pageSize, String dataAuthority) {
			MsgPage msgPage = new MsgPage();       
	        //总记录数
	        Integer totalRecords = auditProjectDao.getAuditProjectListCount(entity,dataAuthority);
	    	//当前页开始记录
	    	int offset = msgPage.countOffset(curPageNum,pageSize);  
	    	//分页查询结果集
	    	List<AuditProject> list = auditProjectDao.getAuditProjectList(entity, offset, pageSize, dataAuthority);
	    	for (AuditProject auditProject : list) {
	    		Integer findQuestionNum;
	            AuditProjectFindQuestion AuditProjectFindQuestion = auditProjectFindQuestionService.getAuditProjectFindQuestionByAuditProjectId(auditProject.getId());
	            if (AuditProjectFindQuestion != null) {
	            	findQuestionNum = (AuditProjectFindQuestion.getCorporateStrategyMakeAndExecute() + AuditProjectFindQuestion.getFinancialControl()
		    				+ AuditProjectFindQuestion.getHumanResourceManagement() + AuditProjectFindQuestion.getPurchaseManagement()
		    				+ AuditProjectFindQuestion.getInfrastructure() + AuditProjectFindQuestion.getProjectInvestment()
		    				+ AuditProjectFindQuestion.getProductionOrganizationAndSales() + AuditProjectFindQuestion.getAdministrativeAffairs()
		    				+ AuditProjectFindQuestion.getExternalEnvironmentAndCompetition() + AuditProjectFindQuestion.getOthers());
		    		auditProject.setFindQuestionNum(findQuestionNum);
				}
	    		List<AuditProjectAbarbeitungQuestion> abarbeitungQuestionList = auditProjectAbarbeitungQuestionService.getAuditProjectAbarbeitungQuestionByAuditProjectId(auditProject.getId());
	    		auditProject.setAbarbeitungQuestionNum(abarbeitungQuestionList.size());
			}
	    	msgPage.setPageNum(curPageNum);
	    	msgPage.setPageSize(pageSize);
	    	msgPage.setTotalRecords(totalRecords);
	    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
	    	msgPage.setList(list);    
	        return msgPage;
	}

	@Override
	public Integer saveAuditProject(AuditProject entity) {
		return auditProjectDao.saveAuditProject(entity);
	}

	@Override
	public void deleteAuditProject(Integer id) {
		auditProjectDao.deleteAuditProject(id);
	}

	@Override
	public AuditProject getAuditProject(Integer id) {
		return auditProjectDao.getAuditProjectById(id);
	}

	@Override
	public void updateAuditProject(AuditProject entity) {
		auditProjectDao.updateAuditProject(entity);
	}

	@Override
	public AuditProject getAuditProject(AuditProject entity) {
		return auditProjectDao.getAuditProject(entity);
	}

	@Override
	public void saveAuditProjectExamine(Integer entityId, String examStr, 
			Integer examResult, HhUsers user) {
		AuditProject entity = getAuditProject(entityId);
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
		updateAuditProject(entity);
		examineService.saveExamine(entity.getId(), Base.examkind_riskcontrol_auditProject, user, examStr, examResult);
	}

	@Override
	public MsgPage getExamineAuditProjects(AuditProject entity,
			Integer curPageNum, Integer pageSize, String dataAuthority) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = auditProjectDao.getExamineAuditProjectListCount(entity,dataAuthority);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<AuditProject> list = auditProjectDao.getExamineAuditProjectList(entity, offset, pageSize, dataAuthority);
    	for (AuditProject auditProject : list) {
    		Integer findQuestionNum;
            AuditProjectFindQuestion AuditProjectFindQuestion = auditProjectFindQuestionService.getAuditProjectFindQuestionByAuditProjectId(auditProject.getId());
            if (AuditProjectFindQuestion != null) {
            	findQuestionNum = (AuditProjectFindQuestion.getCorporateStrategyMakeAndExecute() + AuditProjectFindQuestion.getFinancialControl()
	    				+ AuditProjectFindQuestion.getHumanResourceManagement() + AuditProjectFindQuestion.getPurchaseManagement()
	    				+ AuditProjectFindQuestion.getInfrastructure() + AuditProjectFindQuestion.getProjectInvestment()
	    				+ AuditProjectFindQuestion.getProductionOrganizationAndSales() + AuditProjectFindQuestion.getAdministrativeAffairs()
	    				+ AuditProjectFindQuestion.getExternalEnvironmentAndCompetition() + AuditProjectFindQuestion.getOthers());
	    		auditProject.setFindQuestionNum(findQuestionNum);
			}
    		List<AuditProjectAbarbeitungQuestion> abarbeitungQuestionList = auditProjectAbarbeitungQuestionService.getAuditProjectAbarbeitungQuestionByAuditProjectId(auditProject.getId());
    		auditProject.setAbarbeitungQuestionNum(abarbeitungQuestionList.size());
		}
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
}
