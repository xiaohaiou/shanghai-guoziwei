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
import com.softline.dao.report.IReportOutBenchmarkDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportFinancingAccount;
import com.softline.entity.ReportOutBenchmark;
import com.softline.entity.ReportOutCompany;
import com.softline.entity.ReportOverseasCapitalPool;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportOutBenchmarkService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;



@Service("reportOutBenchmarkService")
/**
 * 
 * @author yxk
 *
 */
public class ReportOutBenchmarkService implements IReportOutBenchmarkService {

	
	@Autowired
	@Qualifier("reportOutBenchmarkDao")
	private IReportOutBenchmarkDao reportOutBenchmarkDao;

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;

	@Resource(name = "systemService")
	private ISystemService systemService;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	
	public MsgPage getReportOutBenchmarkListView(ReportOutBenchmark entity,Integer pageNum, Integer pagesize) {
		 MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportOutBenchmarkDao.getReportOutBenchmarkListCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(pageNum,pagesize);  
    	//分页查询结果集
    	List<ReportOutBenchmark> list = reportOutBenchmarkDao.getReportOutBenchmarkList(entity, offset, pagesize);
    	msgPage.setPageNum(pageNum);
    	msgPage.setPageSize(pagesize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pagesize));
    	msgPage.setList(list);    
        return msgPage;
	}


	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportOutBenchmark getReportOutBenchmar(Integer id) {
		// TODO Auto-generated method stub
		return reportOutBenchmarkDao.getReportOutBenchmar(id);
	}

	/**
	 * 维护
	 * @param id 查询ID
	 * @return
	 */
	public CommitResult saveReportOutBenchmark(ReportOutBenchmark entity) {
		CommitResult result=new CommitResult();
		try {
			commonDao.saveOrUpdate(entity);
			result.setEntity(entity);
			result.setResult(true);
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		return result;
	}


	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportOutCompany(ReportOutCompany entity,HhUsers use) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveReportOutCompanyCheck(entity);
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
			entity.setModifyDate(df.format(new Date()));
			entity.setModifyPersonId(use.getVcEmployeeId());
			entity.setModifyPersonName(use.getVcName());
			
		}
		
		HhOrganInfoTreeRelation a= systemService.getTreeOrganInfoNode(Base.financetype, entity.getOrg());
//     	entity.setOrgname(a.getVcFullName());
		entity.setParentorg(a.getVcOrganId());
		commonDao.saveOrUpdate(entity);
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}

	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	private CommitResult saveReportOutCompanyCheck(ReportOutCompany entity) {
		CommitResult result=new CommitResult();
		if(!reportOutBenchmarkDao.saveReportOutCompanyRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该期已经有数据，不能再添加");
			return result;
		}
		if(!reportOutBenchmarkDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}
		result.setResult(true);
		return result;
	}


	@Override
	public MsgPage getReportOutCompanyListView(ReportOutCompany entity,Integer pageNum,String str,Integer pagesize) {
		    MsgPage msgPage = new MsgPage();       
	        //总记录数
	        Integer totalRecords = reportOutBenchmarkDao.getReportOutComapnyListCount(entity,str);
	    	//当前页开始记录
	    	int offset = msgPage.countOffset(pageNum,pagesize);  
	    	//分页查询结果集
	    	List<ReportOutCompany> list = reportOutBenchmarkDao.getReportOutCompanyList(entity, offset, str,pagesize);
	    	msgPage.setPageNum(pageNum);
	    	msgPage.setPageSize(pagesize);
	    	msgPage.setTotalRecords(totalRecords);
	    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pagesize));
	    	msgPage.setList(list);    
	        return msgPage;
	}


	/**
	 * 跳转到查看页面
	 * @param entity
	 * @return
	 */
	public ReportOutCompany getReportOutCompany(Integer id) {
		return reportOutBenchmarkDao.getReportOutCompany(id);
	}


	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportOutCompany(Integer id, HhUsers use) {
		ReportOutCompany entity=reportOutBenchmarkDao.getReportOutCompany(id);
		CommitResult result=new CommitResult();
		if(!reportOutBenchmarkDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}
		if(entity!=null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setIsdel(1);
			entity.setModifyDate(df.format(new Date()));
	/*		entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());*/
			commonDao.saveOrUpdate(entity);
		}
		result.setEntity(entity);
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
	public CommitResult saveReportOutCompanyExamine(Integer entityID,String examStr,Integer examResult,HhUsers use)
	{
		
		ReportOutCompany entity=getReportOutCompany(entityID);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=new CommitResult();
		result=saveReportOutCompanyExamineCheck(entityID);
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

		examineService.saveExamine( examineentityid, Base.examkind_reportoutcompany, use, examStr, examResult);
		
		result.setResult(true);
		return result;
	}


	
	
	/**
	 * 审核校验判断是否还能够审核，可能别人已经审核过了
	 * @param entityID 采购ID
	 * @return
	 */
	private CommitResult saveReportOutCompanyExamineCheck(Integer entityID) {
		CommitResult result=new CommitResult();
		ReportOutCompany entity=reportOutBenchmarkDao.getReportOutCompany(entityID);
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


	@Override
	public List<ReportOutBenchmark> getReportOutBenchmarkListView(String str) {
		List<ReportOutBenchmark> list = reportOutBenchmarkDao.getReportOutBenchmarkList(str);
		return list;
		
	}
}
