package com.softline.service.report.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.softline.common.Base;
import com.softline.dao.report.IReportFinancingWeekNextDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.financing.ReportFinancingWeekNext;
import com.softline.entity.financing.ReportFinancingWeekNextList;
import com.softline.entity.financing.ReportFinancingWeekThis;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportFinancingWeekNextService;
import com.softline.service.system.IExamineService;
import com.softline.util.MsgPage;

@Service("reportFinancingWeekNextService")
/**
 * 
 * @author ky_tian
 *
 */
public class ReportFinancingWeekNextService implements IReportFinancingWeekNextService{

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	
	@Autowired
	@Qualifier("reportFinancingWeekNextDao")
	private IReportFinancingWeekNextDao reportFinancingWeekNextDao;
	
	/*@Autowired
	@Qualifier("reportFinancingProjectProgressDao")
	private IReportFinancingProjectProgressDao reportFinancingProjectProgressDao;*/
	
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	public MsgPage getReportFinancingWeekNextListView(ReportFinancingWeekNext entity, Integer curPageNum, Integer pageSize, Integer status)
	{
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportFinancingWeekNextDao.getReportFinancingWeekNextListCount(entity,status);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportFinancingWeekNext> list = reportFinancingWeekNextDao.getReportFinancingWeekNextList(entity, offset, pageSize,status);
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
	public ReportFinancingWeekNext getReportFinancingWeekNext(Integer id)
	{
		return reportFinancingWeekNextDao.getReportFinancingWeekNext( id);
	}
	
	@Override
	public List<ReportFinancingProjectProgress> getExportList(Integer id) {
		return reportFinancingWeekNextDao.getExportList(id);
	}
	
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	private CommitResult saveReportFinancingWeekNextCheck(ReportFinancingWeekNext entity)
	{
		CommitResult result=new CommitResult();
		if(!reportFinancingWeekNextDao.checkCanEdit(entity))
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
	public CommitResult saveReportFinancingWeekNextAndUpdate(ReportFinancingWeekNext entity,HhUsers use )
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveReportFinancingWeekNextCheck(entity);
		if(!result.isResult()){
			return result;
		}
		if(entity.getId()==null){
			entity.setIsdel(0);
			entity.setCreateDate(df.format(new Date()));
			entity.setCreatePersonId(use.getVcEmployeeId());
			entity.setCreatePersonName(use.getVcName());
		}else{
			entity.setIsdel(0);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
		}
		//ReportFinancingProjectProgress rfp = null;
		entity.getWeekNextSet().clear();
		if(entity.getWeekNextList()!=null){
			for(ReportFinancingWeekNextList list:entity.getWeekNextList()){
				//修改一般类数据
				/*rfp = reportFinancingProjectProgressDao.getReportFinancingProjectProgress(list.getFid());
				rfp.setAlreadyAccountAmounts(list.getAlreadyAccountAmounts());
				rfp.setExpectAccountDate(list.getAccountDate());
				commonDao.saveOrUpdate(rfp);*/
				entity.getWeekNextSet().add(list);
			}
		}
		//保存本周数据
		HhOrganInfoTreeRelation node = systemDao.getTreeOrganInfoNode(Base.financetype, entity.getOrg());
		entity.setParentorg(node.getVcOrganId());
		entity.setYear(Integer.parseInt(entity.getDate().split("-")[0]));
		entity.setMonth(Integer.parseInt(entity.getDate().split("-")[1]));
		commonDao.saveOrUpdate(entity);
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	/**
	 * list页面上报时，实体更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingWeekNext(ReportFinancingWeekNext entity,HhUsers use)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveReportFinancingWeekNextCheck(entity);
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
		//保存日志信息
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	/**
	 * 审核校验判断是否还能够审核，可能别人已经审核过了
	 * @param entityID 采购ID
	 * @return
	 */
	public CommitResult saveReportFinancingWeekNextExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		ReportFinancingWeekNext entity=reportFinancingWeekNextDao.getReportFinancingWeekNext(entityID);
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
	public CommitResult saveReportFinancingWeekNextExamine(Integer entityID,String examStr,Integer examResult,HhUsers use)
	{
		
		ReportFinancingWeekNext entity=getReportFinancingWeekNext(entityID);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=new CommitResult();
		result=saveReportFinancingWeekNextExamineCheck(entityID);
		if(!result.isResult())
		{
			return result;
		}
		//审核不通过
		if(examResult.equals(Base.examresult_1)) {
			entity.setStatus(Base.examstatus_3);
		}
		//审核通过
		else if(examResult.equals(Base.examresult_2)) {
			entity.setStatus(Base.examstatus_4);
		}
		//String a=df.format(new Date());
		entity.setIsdel(0);
		entity.setAuditorDate(df.format(new Date()));
		entity.setAuditorPersonId(use.getVcEmployeeId());
		entity.setAuditorPersonName(use.getVcName());
		entity.setLastModifyDate(df.format(new Date()));
		entity.setLastModifyPersonId(use.getVcEmployeeId());
		entity.setLastModifyPersonName(use.getVcName());
		//保存
		commonDao.saveOrUpdate(entity);
		Integer examineentityid=entity.getId();

		examineService.saveExamine( examineentityid, Base.examkind_reportFinancingWeekNext, use, examStr, examResult);
		
		result.setResult(true);
		return result;
	}
	
	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportFinancingWeekNext(Integer id,HhUsers use)
	{
		
		ReportFinancingWeekNext entity=reportFinancingWeekNextDao.getReportFinancingWeekNext(id);
		CommitResult result=new CommitResult();
		if(entity!=null)
		{
			//再删除项目记录
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
	 * 查询一般融资对应日期的下账数据
	 */
	@Override
	public String getAccountsData(String orgnm) {
		List<ReportFinancingProjectProgress> list = new ArrayList<ReportFinancingProjectProgress>();
		list=reportFinancingWeekNextDao.getAccountsData(orgnm);
		return JSON.toJSONString(list);
	}

	/**
	 * 汇总数据查询
	 */
	@Override
	public List<String> getDataList(ReportFinancingWeekNext entity) {
		List<String> list = reportFinancingWeekNextDao.getDataList(entity);
		return list;
	}

	/**
	 * 查询-----------融资项目进展数据填报--所有实体类
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	@Override
	public Map getReportFinancingWeekNextListSumDataView(ReportFinancingWeekNext entity,ReportFinancingProjectProgress rfppBean, Integer curPageNum,Integer pageSize, Integer status){
		
		Hashtable<String,Object> resultMap = new Hashtable<String,Object>();  //封装返回结果集
		MsgPage msgPage = new MsgPage();   
		double sumAlreadyAccountAmounts = 0.0d;
		double sumScale = 0.0d;	
		int totalRecords = 0; //总记录数
		List<ReportFinancingProjectProgress> listReportFinacingProjectProgressBeanIN = null;
		StringBuilder ids = new StringBuilder();
		
		//设置组织结构的父类编号
		HhOrganInfoTreeRelation hhOrganInfoTreeRelation =systemDao.getTopTreeOrganInfoNodeStr(Base.financetype,entity.getOrg());
		if(null != hhOrganInfoTreeRelation &&
				null!=hhOrganInfoTreeRelation.getVcOrganId() &&
				!"".equals(hhOrganInfoTreeRelation.getVcOrganId())){		
			//设置组织结构的父类编号
			entity.setParentorg(hhOrganInfoTreeRelation.getVcOrganId());
		}
			
    	//原查询条件,获取总共的周计划记录数据，用于汇总周计划下所有的实体数值信息
    	List<ReportFinancingWeekNext> list = reportFinancingWeekNextDao.
    													getReportFinancingWeekNextListForSumData(entity,
    																					 		 0,
    																					 		 reportFinancingWeekNextDao.getReportFinancingWeekNextListForSumData(entity,status),
    																					 		 status);
    	
    	for(ReportFinancingWeekNext tempBeanRFW:list){    		
    		ids.append("'").append(tempBeanRFW.getId()).append("'").append(",");    		
    	}
    	if(ids.length()>1)
    		ids.setLength(ids.length()-1);
    	   	
    	// 返回总共的记录数量
    	totalRecords = reportFinancingWeekNextDao.getAllListBeansRecords(ids.toString(),rfppBean);  	 	    	
    	// 返回分页结果集对象
    	listReportFinacingProjectProgressBeanIN = reportFinancingWeekNextDao.getAllListBeans(ids.toString(),
    																						 rfppBean,
    																						 msgPage.countOffset(curPageNum, pageSize),
    																						 pageSize);
    	// 返回汇总数据值
    	List<String> listBackInfo = reportFinancingWeekNextDao.getSumData(ids.toString(),rfppBean); 
    	
		for(Object obj : listBackInfo) {
			Object[] item = (Object[])obj;
			sumAlreadyAccountAmounts = Double.valueOf(item[0]==null?"0":item[0].toString());
			sumScale = Double.valueOf(item[1]==null?"0":item[1].toString());
		}

    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(listReportFinacingProjectProgressBeanIN);  

    	resultMap.put("msgPage", msgPage);
    	resultMap.put("sumAlreadyAccountAmounts",new java.text.DecimalFormat("0.000").format(sumAlreadyAccountAmounts));
    	resultMap.put("sumScale", new java.text.DecimalFormat("0.000").format(sumScale));
		
    	// 清空集合对象
    	if(null!=list)
    		list.clear();

		return resultMap;
	}


	@Override
	public List<ReportFinancingProjectProgress> getReportFinancingWeekNextListSumDataView(ReportFinancingWeekNext entity,Integer status) {
		Hashtable<String,Object> resultMap = new Hashtable<String,Object>();  //封装返回结果集
  

		List<ReportFinancingProjectProgress> listReportFinacingProjectProgressBeanIN = null;
		StringBuilder ids = new StringBuilder();
		
		//设置组织结构的父类编号
		HhOrganInfoTreeRelation hhOrganInfoTreeRelation =systemDao.getTopTreeOrganInfoNodeStr(Base.financetype,entity.getOrg());
		if(null != hhOrganInfoTreeRelation &&
				null!=hhOrganInfoTreeRelation.getVcOrganId() &&
				!"".equals(hhOrganInfoTreeRelation.getVcOrganId())){		
			//设置组织结构的父类编号
			entity.setParentorg(hhOrganInfoTreeRelation.getVcOrganId());
		}
			
    	//原查询条件,获取总共的周计划记录数据，用于汇总周计划下所有的实体数值信息
    	List<ReportFinancingWeekNext> list = reportFinancingWeekNextDao.getReportFinancingWeekNextListForSumData(entity,0,
    			reportFinancingWeekNextDao.getReportFinancingWeekNextListForSumData(entity,status),status);
    	
    	for(ReportFinancingWeekNext tempBeanRFW:list){    		
    		ids.append("'").append(tempBeanRFW.getId()).append("'").append(",");    		
    	}
    	if(ids.length()>1)
    		ids.setLength(ids.length()-1);
    	   		 	    	
    	// 返回分页结果集对象
    	listReportFinacingProjectProgressBeanIN = reportFinancingWeekNextDao.getAllListBeans(ids.toString());

    	// 清空集合对象
    	if(null!=list)
    		list.clear();

		return listReportFinacingProjectProgressBeanIN;
	}


	
}
