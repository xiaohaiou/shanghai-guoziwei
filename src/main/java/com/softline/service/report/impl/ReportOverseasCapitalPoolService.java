package com.softline.service.report.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Base;
import com.softline.dao.report.IReportOverseasCapitalPoolDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportOverseasCapitalPool;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportOverseasCapitalPoolService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Service("reportOverseasCapitalPoolService")
/**
 * 
 * @author tch
 *
 */
public class ReportOverseasCapitalPoolService implements IReportOverseasCapitalPoolService{

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("reportOverseasCapitalPoolDao")
	private IReportOverseasCapitalPoolDao reportOverseasCapitalPoolDao;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	@Resource(name = "systemService")
	private ISystemService systemService;
	
	public MsgPage getReportOverseasCapitalPoolListView(ReportOverseasCapitalPool entity, Integer curPageNum, Integer pageSize)
	{
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportOverseasCapitalPoolDao.getReportOverseasCapitalPoolListCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportOverseasCapitalPool> list = reportOverseasCapitalPoolDao.getReportOverseasCapitalPoolList(entity, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportOverseasCapitalPool getReportOverseasCapitalPool(Integer id)
	{
		return reportOverseasCapitalPoolDao.getReportOverseasCapitalPool( id);
	}
	
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	private CommitResult saveReportOverseasCapitalPoolCheck(ReportOverseasCapitalPool entity)
	{
		CommitResult result=new CommitResult();
		if(!reportOverseasCapitalPoolDao.saveReportOverseasCapitalPoolRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该期已经有数据，不能再添加");
			return result;
		}
		if(!reportOverseasCapitalPoolDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportOverseasCapitalPool(ReportOverseasCapitalPool entity,HhUsers use)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveReportOverseasCapitalPoolCheck(entity);
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
		
		HhOrganInfoTreeRelation a= systemService.getTreeOrganInfoNode(Base.financetype, entity.getOrg());
		entity.setOrgname(a.getVcFullName());
		entity.setParentorg(a.getVcOrganId());
		commonDao.saveOrUpdate(entity);
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}

	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportOverseasCapitalPool(Integer id,HhUsers use)
	{
		
		ReportOverseasCapitalPool entity=reportOverseasCapitalPoolDao.getReportOverseasCapitalPool(id);
		CommitResult result=new CommitResult();
		if(!reportOverseasCapitalPoolDao.checkCanEdit(entity))
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
	
	
	/**
	 * 审核校验判断是否还能够审核，可能别人已经审核过了
	 * @param entityID 采购ID
	 * @return
	 */
	public CommitResult saveReportOverseasCapitalPoolExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		ReportOverseasCapitalPool entity=reportOverseasCapitalPoolDao.getReportOverseasCapitalPool(entityID);
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
	
	/**
	 * 审核
	 * @param entityID 采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveReportOverseasCapitalPoolExamine(Integer entityID,String examStr,Integer examResult,HhUsers use)
	{
		
		ReportOverseasCapitalPool entity=getReportOverseasCapitalPool(entityID);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=new CommitResult();
		result=saveReportOverseasCapitalPoolExamineCheck(entityID);
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

		examineService.saveExamine( examineentityid, Base.examkind_reportoverseascapitalpool, use, examStr, examResult);
		
		result.setResult(true);
		return result;
	}
	
	/**
	 * 获取境外下账资金
	 * @param year
	 * @param seaon
	 * @return
	 */
	public String getData(int year,int seaon,String organID)
	{
		String month ="";
		switch(seaon)
		{
			
			case 50108:
				month="1,2,3";
				break;
			case 50109:
				month="4,5,6";
				break;
			case 50110:
				month="7,8,9";
				break;
			case 50111:
				month="10,11,12";
				break;
			default:
				return "";
		}
		return	reportOverseasCapitalPoolDao.getData( year, month,organID);
	}
	
	
}
