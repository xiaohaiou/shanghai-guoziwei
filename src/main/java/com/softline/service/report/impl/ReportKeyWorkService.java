package com.softline.service.report.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Base;
import com.softline.dao.report.IReportKeyWorkDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.ReportKeywork;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportKeyWorkService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Service("reportKeyWorkService")
public class ReportKeyWorkService implements IReportKeyWorkService{
	
	@Autowired
	@Qualifier("reportKeyWorkDao")
	private IReportKeyWorkDao reportKeyWorkDao;
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;	
	
	@Resource(name = "systemService")
	private ISystemService systemService;

	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public MsgPage getKeyWorkYear(ReportKeywork entity, Integer curPageNum,
			Integer pageSize) {
		MsgPage msgPage = new MsgPage();
		 //总记录数
        Integer totalRecords = reportKeyWorkDao.getKeyWorkCount(entity);//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportKeywork> list = reportKeyWorkDao.getKeyWork(entity, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
		
	
	}

	
	@Override
	public ReportKeywork getKeyworkbyID(Integer id) {
		ReportKeywork keywork =reportKeyWorkDao.getKeyworkbyID(id);
		return keywork;
	}



	
	@Override
	public CommitResult saveKeywork(ReportKeywork entity,HhUsers use,String op) {
		CommitResult result=new CommitResult();
		result=saveKeyworkCheck(entity);
		if(!result.isResult())
			return result;
		//新增保存  新增保存并上报  编辑保存
		if(entity.getId()==null)
		{
			//设置创建信息
			entity.setIsdel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			if(op.equals("saveReport")){
				//设置上报信息
				entity.setReportDate(df.format(new Date()));
				entity.setReportPersonId(use.getVcEmployeeId());
				entity.setReportPersonName(use.getVcName());
			}
		}else{
			//设置上报信息
			entity.setReportDate(df.format(new Date()));
			entity.setReportPersonId(use.getVcEmployeeId());
			entity.setReportPersonName(use.getVcName());
			//设置修改信息
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
		}
		HhOrganInfoTreeRelation a= systemService.getTreeOrganInfoNode(Base.financetype, entity.getCoreOrg());
		entity.setParentorg(a.getVcOrganId());
		commonDao.saveOrUpdate(entity);
		if(op.equals("saveReport")||op.equals("report")){
			potalMsgService.savePortalMsg(new PortalMsg("年度重点任务需要审核","年度重点任务",df.format(new Date()),0,0,0,
					entity.getCreatePersonName(),entity.getCreateDate(),entity.getLastModifyPersonName(),
					entity.getLastModifyDate(),entity.getCoreOrg(),systemService.getParentBynNodeID(entity.getCoreOrg(),
							Base.financetype),"bimWork_keywork","report_keywork",entity.getId().toString()));
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	
	/**
	 * 保存Check
	 * 新增保存检查是否存在相同年度 相同核心企业 相同指标名称的记录
	 * 编辑保存检查除自己外是否存在相同年度 相同核心企业 相同指标名称的记录
	 * @param entity
	 * @return
	 */
	private CommitResult saveKeyworkCheck(ReportKeywork entity)
	{
		CommitResult result=new CommitResult();
		if(!reportKeyWorkDao.saveKeyworkCheck(entity))//存在相同记录
		{
			result=CommitResult.createErrorResult(entity.getCoreOrgname()+entity.getYear()+"年"+"已经存在指标名称为‘"+entity.getIndextypeName()+"’记录，不能再保存。");
			return result;
		}
		result.setResult(true);
		return result;
	}


	@Override
	public CommitResult deleteKeywork(Integer id, HhUsers use) {
		ReportKeywork entity=reportKeyWorkDao.getKeyworkbyID(id);
		CommitResult result=new CommitResult();
		if(!reportKeyWorkDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}
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


	@Override
	public CommitResult saveKeyWorkExamine(Integer entityid, String examStr,
			Integer examResult, HhUsers use) {
		ReportKeywork entity=getKeyworkbyID(entityid);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=new CommitResult();
		result=saveKeyWorkExamineCheck(entityid);
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
		String a=df.format(new Date());
		entity.setAuditorDate(df.format(new Date()));
		entity.setAuditorPersonId(use.getVcEmployeeId());
		entity.setAuditorPersonName(use.getVcName());
		//保存ReportOverseasCapitalPool
		commonDao.saveOrUpdate(entity);
		
		Integer examineentityid=entity.getId();

		examineService.saveExamine( examineentityid, Base.examkind_keywork, use, examStr, examResult);
		
		result.setResult(true);
		return result;
	}
	
	/**
	 * 审核校验判断是否还能够审核，可能别人已经审核过了
	 * @param entityID 采购ID
	 * @return
	 */
	public CommitResult saveKeyWorkExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		ReportKeywork entity=reportKeyWorkDao.getKeyworkbyID(entityID);
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
			return result;
		}
		if(!entity.getStatus().equals(Base.examstatus_2))
		{
			result=CommitResult.createErrorResult("该信息已不用审核");
			return result;
		}
		result.setResult(true);
		return result;
	}


}
