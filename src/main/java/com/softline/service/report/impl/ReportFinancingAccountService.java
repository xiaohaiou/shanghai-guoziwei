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
import com.softline.dao.report.IReportFinancingAccountDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportFinancingAccount;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportFinancingAccountService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

@Service("reportFinancingAccountService")
/**
 * 
 * @author tch
 *
 */
public class ReportFinancingAccountService implements IReportFinancingAccountService{

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	
	@Autowired
	@Qualifier("reportFinancingAccountDao")
	private IReportFinancingAccountDao reportFinancingAccountDao;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	
	
	public MsgPage getReportFinancingAccountListView(ReportFinancingAccount entity, Integer curPageNum, Integer pageSize)
	{
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportFinancingAccountDao.getReportFinancingAccountListCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportFinancingAccount> list = reportFinancingAccountDao.getReportFinancingAccountList(entity, offset, pageSize);
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
	public ReportFinancingAccount getReportFinancingAccount(Integer id)
	{
		return reportFinancingAccountDao.getReportFinancingAccount( id);
	}
	
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	private CommitResult saveReportFinancingAccountCheck(ReportFinancingAccount entity)
	{
		CommitResult result=new CommitResult();
		if(!reportFinancingAccountDao.saveReportFinancingAccountRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该期已经有数据，不能再添加");
			return result;
		}
		if(!reportFinancingAccountDao.checkCanEdit(entity))
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
	public CommitResult saveReportFinancingAccount(ReportFinancingAccount entity,HhUsers use)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveReportFinancingAccountCheck(entity);
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
		entity.setParentorg(node.getVcOrganId());
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
	public CommitResult deleteReportFinancingAccount(Integer id,HhUsers use)
	{
		
		ReportFinancingAccount entity=reportFinancingAccountDao.getReportFinancingAccount(id);
		CommitResult result=new CommitResult();
		if(!reportFinancingAccountDao.checkCanEdit(entity))
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
	public CommitResult saveReportFinancingAccountExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		ReportFinancingAccount entity=reportFinancingAccountDao.getReportFinancingAccount(entityID);
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
			return result;
		}
		/*if(!entity.getStatus().equals(Base.examstatus_2))
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
	public CommitResult saveReportFinancingAccountExamine(Integer entityID,String examStr,Integer examResult,HhUsers use)
	{
		
		ReportFinancingAccount entity=getReportFinancingAccount(entityID);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=new CommitResult();
		result=saveReportFinancingAccountExamineCheck(entityID);
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
		//保存ReportFinancingAccount
		commonDao.saveOrUpdate(entity);
		
		Integer examineentityid=entity.getId();

		examineService.saveExamine( examineentityid, Base.examkind_financeaccount, use, examStr, examResult);
		
		result.setResult(true);
		return result;
	}


	@Override
	public List<ReportFinancingAccount> getSummaryData(String compId,Integer year,Integer month) {
		// TODO Auto-generated method stub
		
//		HhOrganInfoTreeRelation node = systemDao.getTreeOrganInfoNode(Base.financetype, compId);

		return reportFinancingAccountDao.getSummaryData(compId,year,month);
	}


	@Override
	public List<ReportFinancingAccount> getSummaryHistoryData(String compId,
			Integer year, Integer month) {
		
/*		HhOrganInfoTreeRelation node = systemDao.getTreeOrganInfoNode(Base.financetype, compId);*/

		return reportFinancingAccountDao.getSummaryHistoryData(compId,year,month);
	}

	/**
	 * 查询汇总公司下面所有子公司的数据		
	 * @param entity
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	@Override	
	public MsgPage getReportFinancingAccountSumDetialList(ReportFinancingAccount entity, Integer curPageNum, Integer pageSize){
				
		MsgPage msgPage = new MsgPage();       
		
		List<ReportFinancingAccount> totalSumDataList = null ;
		List<ReportFinancingAccount> currentDataList = null;
		
		if(null==entity.getOrg())
			totalSumDataList = new ArrayList<ReportFinancingAccount>();
		
		totalSumDataList = this.getSummaryData(entity.getOrg(), entity.getYear(), entity.getMonth());
				
        //总记录数
        Integer totalRecords = totalSumDataList.size();
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	
    	//分页查询结果集   	
    	if(offset>totalRecords)
    		currentDataList = null;   	
    	if(totalRecords-pageSize<=offset && offset<=totalRecords)
    		currentDataList = totalSumDataList.subList(offset, totalRecords);
    	if(offset<totalRecords-pageSize)
    		currentDataList = totalSumDataList.subList(offset, offset+pageSize);   	
    	
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(currentDataList);    
        return msgPage;
		
	}
	
	
	
}
