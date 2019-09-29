/*package com.softline.service.riskmanagement.impl;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.softline.client.sso.AuthorityServiceService;
import com.softline.client.sso.IAuthorityService;
import com.softline.client.sso.SsoCheckResult;
import com.softline.common.Base;
import com.softline.dao.riskmanagement.IriskmanagementDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.HhUsers;
import com.softline.entity.riskmanagement;
import com.softline.entity.ReportFormsGroup;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.riskmanagementView;
import com.softline.service.portal.ILogService;
import com.softline.service.riskmanagement.IriskmanagementService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISsoService;
import com.softline.util.MsgPage;

@Service("riskmanagementService")
*//**
 * 
 * @author tch
 *
 *//*
public class RiskManagementService implements IriskmanagementService{

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("riskmanagementDao")
	private IRiskmanagementDao riskmanagementDao;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	
	
	public MsgPage getriskmanagementListView(riskmanagement entity, Integer curPageNum, Integer pageSize)
	{
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = riskmanagementDao.getriskmanagementListCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<riskmanagement> list = riskmanagementDao.getriskmanagementList(entity, offset, pageSize);
    	List<riskmanagementView> View=new ArrayList<riskmanagementView>();
    	for (riskmanagement riskmanagement : list) {
    		riskmanagementView item=new riskmanagementView();
    		Double a=riskmanagement.getEngineeringClass()+riskmanagement.getMaterialCategoryClass()+riskmanagement.getChannelClass()+riskmanagement.getServiceClass();
    		DecimalFormat df =  new   java.text.DecimalFormat("#.0000");  
    		item.setCumulativeriskmanagementamount(df.format(a));
    		Integer b=riskmanagement.getConstructioClass()+riskmanagement.getDesignClass()+riskmanagement.getSupervisionClass()+riskmanagement.getConsultingCostClass()+riskmanagement.getMaterialEquipmentClass();
    		item.setSupportamount(b.toString());
    		item.setId(riskmanagement.getId());
    		item.setSeason(riskmanagement.getSeasonName());
    		item.setYear(riskmanagement.getYear());
    		item.setStatusName(riskmanagement.getStatusName());
    		item.setStatus(riskmanagement.getStatus());
    		item.setRate(riskmanagement.getriskmanagementSaveRate().toString());
    		View.add(item);
		}
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(View);    
        return msgPage;
	}
	
	
	*//**
	 * 查询
	 * @param id 查询ID
	 * @return
	 *//*
	public riskmanagement getriskmanagement(Integer id)
	{
		return riskmanagementDao.getriskmanagement( id);
	}
	
	*//**
	 * 保存校验
	 * @param entity
	 * @return
	 *//*
	private CommitResult saveriskmanagementCheck(riskmanagement entity)
	{
		CommitResult result=new CommitResult();
		if(!riskmanagementDao.saveriskmanagementRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该季度已经有数据，不能再添加");
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	*//**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 *//*
	public CommitResult saveriskmanagement(riskmanagement entity,HhUsers use)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveriskmanagementCheck(entity);
		if(!result.isResult())
		{
			return result;
		}
		if(entity.getId()==null)
		{
			entity.setIsdel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
			
		}
		else
		{
			entity.setIsdel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			
		}
		commonDao.saveOrUpdate(entity);
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}

	*//**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 *//*
	public CommitResult deleteriskmanagement(Integer id,HhUsers use)
	{
		riskmanagement entity=riskmanagementDao.getriskmanagement(id);
		CommitResult result=new CommitResult();
		if(entity!=null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setIsdel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			commonDao.saveOrUpdate(entity);
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	
	*//**
	 * 审核校验判断是否还能够审核，可能别人已经审核过了
	 * @param entityID 采购ID
	 * @return
	 *//*
	public CommitResult saveriskmanagementExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		riskmanagement entity=riskmanagementDao.getriskmanagement(entityID);
		if(!entity.getStatus().equals(Base.examstatus_2))
		{
			result=CommitResult.createErrorResult("该信息已不用审核");
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	*//**
	 * 审核
	 * @param entityID 采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 *//*
	public CommitResult saveriskmanagementExamine(Integer entityID,String examStr,Integer examResult,HhUsers use)
	{
		
		riskmanagement entity=getriskmanagement(entityID);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=new CommitResult();
		result=saveriskmanagementExamineCheck(entityID);
		if(!result.isResult())
		{
			return result;
		}
		//审核不通过
		if(examResult.equals(Base.examresult_1))
			entity.setStatus(Base.examstatus_3);
		//审核通过
		else if(examResult.equals(Base.examresult_2))
			entity.setStatus(Base.examstatus_4);
		entity.setExamineTime(df.format(new Date()));
		//保存riskmanagement
		CommitResult riskmanagementresult= saveriskmanagement(entity,use);
		if(!riskmanagementresult.isResult())
			return riskmanagementresult;
		
		riskmanagement riskmanagement= (riskmanagement) riskmanagementresult.getEntity();
		Integer examineentityid=riskmanagement.getId();
		
		//针对审核的处理，先假删历史审核数据
		SysExamine oldexamine=examineService.getOneExamine( examineentityid, Base.examkind_riskmanagement);
		if(oldexamine!=null)
		{
			oldexamine.setIsdel(1);
			oldexamine.setLastModifyDate(df.format(new Date()));
			oldexamine.setLastModifyPersonId(use.getVcEmployeeId());
			oldexamine.setLastModifyPersonName(use.getVcName());
			commonDao.saveOrUpdate(oldexamine);
		}
		//再插入新的审核数据
		SysExamine	examine=new SysExamine();
		examine.setCreateDate(df.format(new Date()));
		examine.setCreatePersonId(use.getVcEmployeeId());
		examine.setCreatePersonName(use.getVcName());
		examine.setIsdel(0);
		examine.setExamentityid(examineentityid);
		examine.setExaminestr(examStr);
		examine.setExamKind(Base.examkind_riskmanagement);
		examine.setExamresult(examResult);
		result.setResult(true);
		commonDao.saveOrUpdate(examine);
		return result;
	}
	
}
*/