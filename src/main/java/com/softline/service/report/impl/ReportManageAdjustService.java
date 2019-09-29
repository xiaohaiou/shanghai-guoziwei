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
import com.softline.dao.report.IReportManageAdjustDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportManageAdjust;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportManageAdjustService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

@Service("reportManageAdjustService")
/**
 * 
 * @author tch
 *
 */
public class ReportManageAdjustService implements IReportManageAdjustService{

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("reportManageAdjustDao")
	private IReportManageAdjustDao reportManageAdjustDao;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	
	public MsgPage getReportManageAdjustListView(ReportManageAdjust entity, Integer curPageNum, Integer pageSize)
	{
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportManageAdjustDao.getReportManageAdjustListCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportManageAdjust> list = reportManageAdjustDao.getReportManageAdjustList(entity, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
        return msgPage;
	}
	/**
	 * 管理口径核算数据填报查询导出数据
	 * 
	 * @return
	 */
	@Override
	public List<ReportManageAdjust> getExportReportManageAdjustListView(ReportManageAdjust entity) {
		// TODO Auto-generated method stub
		List<ReportManageAdjust> list = reportManageAdjustDao.getExportReportManageAdjustListView(entity);
		return list;
	}
	
	
	
	/**
	 * 管理口径核算数据审核查询导出数据
	 * 
	 * @return
	 */
	@Override
	public List<ReportManageAdjust> getExportReportManageAdjustExamineListView(ReportManageAdjust entity) {
		// TODO Auto-generated method stub
		List<ReportManageAdjust> list = reportManageAdjustDao.getExportReportManageAdjustExamineListView(entity);
		return list;
	}
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportManageAdjust getReportManageAdjust(Integer id)
	{
		return reportManageAdjustDao.getReportManageAdjust( id);
	}
	
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	private CommitResult saveReportManageAdjustCheck(ReportManageAdjust entity)
	{
		CommitResult result=new CommitResult();
		if(!reportManageAdjustDao.saveReportManageAdjustRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult("该公司"+entity.getYear()+"年"+entity.getMonth()+"月已经有数据，不能再添加");
			return result;
		}
		if(!reportManageAdjustDao.checkCanEdit(entity))
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
	public CommitResult saveReportManageAdjust(ReportManageAdjust entity,HhUsers use)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveReportManageAdjustCheck(entity);
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
		HhOrganInfoTreeRelation node = systemDao.getTreeOrganInfoNode(Base.financetype, entity.getOrg());
		if(node != null)
			entity.setParentorg(node.getVcOrganId());
		else
			entity.setParentorg(systemDao.getThePublicCompany(entity.getOrg()).getVcOrganId());
		entity.setOrgname(node.getVcFullName());
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
	public CommitResult deleteReportManageAdjust(Integer id,HhUsers use)
	{
		
		ReportManageAdjust entity=reportManageAdjustDao.getReportManageAdjust(id);
		CommitResult result=new CommitResult();
		if(!reportManageAdjustDao.checkCanEdit(entity))
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
	public CommitResult saveReportManageAdjustExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		ReportManageAdjust entity=reportManageAdjustDao.getReportManageAdjust(entityID);
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
			return result;
		}
	/*	if(!entity.getStatus().equals(Base.examstatus_2))
		{
			result=CommitResult.createErrorResult("该信息已不用审核");
			return result;
		}*/
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
	public CommitResult saveReportManageAdjustExamine(Integer entityID,String examStr,Integer examResult,HhUsers use)
	{
		
		ReportManageAdjust entity=getReportManageAdjust(entityID);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=new CommitResult();
		result=saveReportManageAdjustExamineCheck(entityID);
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
		entity.setIsdel(0);
		entity.setAuditorDate(df.format(new Date()));
		entity.setAuditorPersonId(use.getVcEmployeeId());
		entity.setAuditorPersonName(use.getVcName());
		entity.setLastModifyDate(df.format(new Date()));
		entity.setLastModifyPersonId(use.getVcEmployeeId());
		entity.setLastModifyPersonName(use.getVcName());
		//保存ReportManageAdjust
		commonDao.saveOrUpdate(entity);
		
		Integer examineentityid=entity.getId();

		examineService.saveExamine( examineentityid, Base.examkind_reportmanageadjust, use, examStr, examResult);
		
		result.setResult(true);
		return result;
	}


	@Override
	public ReportManageAdjust getBeginningData(String org, Integer year) {
		// TODO Auto-generated method stub
		return reportManageAdjustDao.getBeginningData(org, year);
	}


	
	
	
	
}
