package com.softline.service.hr.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opendata.api.ODPRequest;
import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.hr.IHeadCountDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.DataHrHeadcount;
import com.softline.entity.DataHrLaborproduction;
import com.softline.entity.HhInterfaceLog;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.HeadCountLaborProduction;
import com.softline.entityTemp.ResponseInfo;
import com.softline.service.hr.IHeadCountService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Service("headCountService")
public class HeadCountService implements IHeadCountService{
	
	@Autowired
	@Qualifier("headCountDao")
	private IHeadCountDao headCountDao;
	
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	
	@Resource(name = "systemService")
	private ISystemService systemService;	
	
	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;	
	
	@Resource(name = "examineService")
	private IExamineService examineService;	
	/**
	 * id查询
	 */
	public HeadCountLaborProduction get(Integer id) {
		HeadCountLaborProduction entity = headCountDao.get(id);
		return entity;
	}
	
	public boolean get(HeadCountLaborProduction entity) {
		return headCountDao.get(entity);
	}
	
	/**
	 * 查询报表
	 */
	public MsgPage findPageList(HeadCountLaborProduction entity, Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
       //总记录数
        Integer totalRecords = headCountDao.getHrFormsListViewCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<HeadCountLaborProduction> list = headCountDao.getHrFormsListView(entity, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);  
        return msgPage;
	}
	
	@Override
	public MsgPage findPageList(HeadCountLaborProduction entity, Integer curPageNum, Integer pageSize,String isAllCompany){
		if("isall".equals(isAllCompany)){
			
			String nNodeID = entity.getOrganalId();
			if(!Common.notEmpty(nNodeID))
				return new MsgPage();
			String nNodeIDs = systemService.getDataauthNnodeIDs(nNodeID);
			nNodeIDs = dealWithAuthorstr(nNodeIDs);
			entity.setOrganalId(nNodeIDs);
			MsgPage msgPage = new MsgPage();       
	       //总记录数
	        Integer totalRecords = headCountDao.getHrFormsListViewCount(entity);
	    	//当前页开始记录
	    	int offset = msgPage.countOffset(curPageNum,pageSize);  
	    	//分页查询结果集
	    	List<HeadCountLaborProduction> list = headCountDao.getHrFormsListView(entity, offset, pageSize);
	    	entity.setOrganalId(nNodeID);	    	
	    	msgPage.setPageNum(curPageNum);
	    	msgPage.setPageSize(pageSize);
	    	msgPage.setTotalRecords(totalRecords);
	    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
	    	msgPage.setList(list);  
	        return msgPage;		
		}else{
			return findPageList(entity,curPageNum,pageSize);
		}
		
	}
	
	@Override
	public List<HeadCountLaborProduction> findCollectList(
			HeadCountLaborProduction headCount) {
		// TODO Auto-generated method stub
		List<HeadCountLaborProduction> list = headCountDao.getHrFormsListCollectView(headCount);
		return list;
	}
	
	@Override
	public MsgPage findExaminePageList(HeadCountLaborProduction entity, Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
       //总记录数
        Integer totalRecords = headCountDao.getExamineHrFormsListView(entity,null,null).size();
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<HeadCountLaborProduction> list = headCountDao.getExamineHrFormsListView(entity, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);  
        return msgPage;
	}
	
	@Override
	public MsgPage findExaminePageList(HeadCountLaborProduction entity, Integer curPageNum, Integer pageSize,String isAllCompany){
		if("isall".equals(isAllCompany)){
			
			String nNodeID = entity.getOrganalId();
			if(!Common.notEmpty(nNodeID))
				return new MsgPage();
			String nNodeIDs = systemService.getDataauthNnodeIDs(nNodeID);
			nNodeIDs = dealWithAuthorstr(nNodeIDs);
			entity.setOrganalId(nNodeIDs);
			MsgPage msgPage = new MsgPage();       
	       //总记录数
	        Integer totalRecords = headCountDao.getExamineHrFormsListView(entity,null,null).size();
	    	//当前页开始记录
	    	int offset = msgPage.countOffset(curPageNum,pageSize);  
	    	//分页查询结果集
	    	List<HeadCountLaborProduction> list = headCountDao.getExamineHrFormsListView(entity, offset, pageSize);
	    	entity.setOrganalId(nNodeID);
	    	msgPage.setPageNum(curPageNum);
	    	msgPage.setPageSize(pageSize);
	    	msgPage.setTotalRecords(totalRecords);
	    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
	    	msgPage.setList(list);  
	        return msgPage;
		}else{
			return findExaminePageList(entity,curPageNum,pageSize);
		}
	}
	
	/**
	 * 处理查询公司编码格式
	 * @param str
	 * @return
	 */
	private String dealWithAuthorstr(String str){
		if(!Common.notEmpty(str) || !str.contains(","))
			return "";
		StringBuffer sb = new StringBuffer();
		String[] strArr = str.split(",");
		for(int i=0;i<strArr.length-1;i++){
			sb.append("'").append(strArr[i]).append("',");
		}
		sb.append("'").append(strArr[strArr.length-1]).append("'");
		return sb.toString();
	}
	
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	private CommitResult saveHeadCountLaborProductionCheck(HeadCountLaborProduction entity)
	{
		CommitResult result=new CommitResult();
		if(!headCountDao.saveHeadCountLaborProductionRepeatCheck(entity))
		{
			if(entity.getCompany()!=null&&!entity.getCompany().equals("")){
				result=CommitResult.createErrorResult(entity.getCompany()+entity.getYear()+"年"+entity.getMonth()+"月已经有数据，不能再添加");
			}else{
				result=CommitResult.createErrorResult("该数据已经删除，不能保存！");
			}
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	/**
	 * 保存上报校验
	 * @param entity
	 * @return
	 */
	private CommitResult reportHeadCountLaborProductionCheck(HeadCountLaborProduction entity)
	{
		CommitResult result=new CommitResult();
		if(!headCountDao.reporHeadCountLaborProductionRepeatCheck(entity))
		{	
			if(entity.getCompany()!=null&&!entity.getCompany().equals("")){
				result=CommitResult.createErrorResult(entity.getCompany()+entity.getYear()+"年"+entity.getMonth()+"月已经上报，不能再上报");
			}else{
				result=CommitResult.createErrorResult("该数据已经删除，不能上报！");
			}
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	/**
	 * 上报Check
	 * @param entity
	 * @return
	 */
	private CommitResult reportDataCheck(HeadCountLaborProduction entity)
	{
		CommitResult result=new CommitResult();
		if(!headCountDao.reporHeadCountLaborProductionRepeatCheck(entity))
		{
			//result=CommitResult.createErrorResult(entity.getCompany()+entity.getYear()+"年"+entity.getMonth()+"月已经上报，不能再上报");
			if(entity.getCompany()!=null){
				result=CommitResult.createErrorResult(entity.getCompany()+entity.getYear()+"年"+entity.getMonth()+"月已经上报，不能再上报");
			}else{
				result=CommitResult.createErrorResult("该数据已经删除，不能上报！");
			}
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	/**
	 * 修改信息
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult updateHeadCountLaborProduction(HeadCountLaborProduction entity, HhUsers use,Integer i) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DataHrHeadcount dhh = new DataHrHeadcount();
		DataHrLaborproduction dhl = new DataHrLaborproduction();
		entity.setLastModifyDate(df.format(new Date()));
		entity.setLastModifyPersonId(use.getVcEmployeeId());
		entity.setLastModifyPersonName(use.getVcName());
		/*entity.setOrganalId("0001");
		entity.setReportDate(df.format(new Date()));
		entity.setReportPersonId(use.getVcEmployeeId());
		entity.setReportPersonName(use.getVcName());*/
		CommitResult result=new CommitResult();
		if(i==Base.examstatus_1){
			result=saveHeadCountLaborProductionCheck(entity);
			if(!result.isResult())
			{
				return result;
			}
		}
		dhh=this.getDataHrHeadcount(entity);
		dhl=this.getDataHrLaborproduction(entity);
		//保存劳动数
		//commonDao.delete(dhh);
		commonDao.saveOrUpdate(dhh);
		//保存/修改劳动生产率
		//commonDao.delete(dhl);
		commonDao.saveOrUpdate(dhl);
		
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	public CommitResult saveHeadCountLaborProduction(HeadCountLaborProduction entity, HhUsers use,String op) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DataHrHeadcount dhh = new DataHrHeadcount();
		DataHrLaborproduction dhl = new DataHrLaborproduction();
		CommitResult result=new CommitResult();
		
		if(op.equals("report")){
			result=reportDataCheck(entity);
		}else if(op.equals("saveReport")) {
			result=reportHeadCountLaborProductionCheck(entity);
		}else{
			result=saveHeadCountLaborProductionCheck(entity);
		}
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
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			if(!"save".equals(op)){
				entity.setReportDate(df.format(new Date()));
				entity.setReportPersonId(use.getVcEmployeeId());
				entity.setReportPersonName(use.getVcName());
				//entity.setOrganalId("0001");
			}
			//设置UUID
			entity.setForeignKey(UUID.randomUUID().toString().replaceAll("-", ""));
			dhh=this.getDataHrHeadcount(entity);
			dhl=this.getDataHrLaborproduction(entity);
		}
		else
		{
			entity.setIsdel(0);
			entity.setReportDate(df.format(new Date()));
			entity.setReportPersonId(use.getVcEmployeeId());
			entity.setReportPersonName(use.getVcName());
			dhh=this.getDataHrHeadcount(entity);
			dhl=this.getDataHrLaborproduction(entity);
			
		}
		//保存劳动数
		commonDao.saveOrUpdate(dhh);
		//保存/修改劳动生产率
		commonDao.saveOrUpdate(dhl);
		if(op.equals("saveReport")||op.equals("report")){
			potalMsgService.savePortalMsg(new PortalMsg(entity.getCompany()+entity.getYear()+"年"+entity.getMonth()+"月总人数与劳动生产率需要审核","总人数/劳动生产率",df.format(new Date()),0,0,0,
					entity.getCreatePersonName(),entity.getCreateDate(),entity.getLastModifyPersonName(),
					entity.getLastModifyDate(),entity.getOrganalId(),systemService.getParentBynNodeID(entity.getOrganalId(),Base.financetype),
							"bimWork_zrsSh_e","data_hr_headcount",entity.getId().toString(),"/shanghai-gzw/headCount/examinelist?id="+entity.getId()));
			/*potalMsgService.savePortalMsg(new PortalMsg("劳动生产率需要审核","劳动生产率",df.format(new Date()),0,0,0,
					entity.getCreatePersonName(),entity.getCreateDate(),entity.getLastModifyPersonName(),
					entity.getLastModifyDate(),entity.getOrganalId(),systemService.getParentBynNodeID(entity.getOrganalId(),
							Base.financetype),"bimWork_headCountSh","data_hr_laborproduction",entity.getId().toString()));*/
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
		
	}

	public CommitResult deleteHeadCountLaborProduction(Integer id, HhUsers use) {
		HeadCountLaborProduction entity=headCountDao.get(id);
		CommitResult result=new CommitResult();
		DataHrHeadcount dhh = new DataHrHeadcount();
		DataHrLaborproduction dhl = new DataHrLaborproduction();
		if(entity!=null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setIsdel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			dhh=this.getDataHrHeadcount(entity);
			//保存劳动数
			commonDao.delete(dhh);
			commonDao.saveOrUpdate(dhh);
			dhl=this.getDataHrLaborproduction(entity);
			//保存/修改劳动生产率
			commonDao.delete(dhl);
			commonDao.saveOrUpdate(dhl);
			//删除工作提醒
			potalMsgService.updatePortalMsg("data_hr_headcount", entity.getId().toString());
			//删除工作提醒
			//potalMsgService.updatePortalMsg("data_hr_laborproduction", id.toString());
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	/**
	 * 获取DataHrHeadcount
	 * @param entity
	 * @param use
	 * @return DataHrHeadcount
	 */
	public DataHrHeadcount getDataHrHeadcount(HeadCountLaborProduction entity){
		DataHrHeadcount dhh = new DataHrHeadcount();
		if (entity.getId() !=null && !"".equals(entity.getId())) {
			dhh.setId(entity.getId());
		}
		if (entity.getYear() !=null && !"".equals(entity.getYear())) {
			dhh.setYear(entity.getYear());
		}
		if (entity.getMonth() !=null && !"".equals(entity.getMonth())) {
			dhh.setMonth(entity.getMonth());
		}
		if (Common.notEmpty(entity.getCompany())) {
			dhh.setCompany(entity.getCompany());
		}
		if (entity.getOrganalId() !=null && !"".equals(entity.getOrganalId())) {
			dhh.setOrganalId(entity.getOrganalId());
		}
		if (entity.getStatus() !=null && !"".equals(entity.getStatus())) {
			dhh.setStatus(entity.getStatus());
		}
		if (Common.notEmpty(entity.getExamineTime())) {
			dhh.setExamineTime(entity.getExamineTime());
		}
		dhh.setIsdel(0);
		if (entity.getWorkersNumber() !=null && !"".equals(entity.getWorkersNumber())) {
			dhh.setWorkersNumber(entity.getWorkersNumber());
		}
		if (entity.getTworkersNumber() !=null && !"".equals(entity.getTworkersNumber())) {
			dhh.setTworkersNumber(entity.getTworkersNumber());
		}
		if (Common.notEmpty(entity.getReportPersonName())) {
			dhh.setReportPersonName(entity.getReportPersonName());
		}
		if (Common.notEmpty(entity.getReportPersonId())) {
			dhh.setReportPersonId(entity.getReportPersonId());
		}
		if (Common.notEmpty(entity.getReportDate())) {
			dhh.setReportDate(entity.getReportDate());
		}
		if (Common.notEmpty(entity.getAuditorPersonName())) {
			dhh.setAuditorPersonName(entity.getAuditorPersonName());
		}
		if (Common.notEmpty(entity.getAuditorPersonId())) {
			dhh.setAuditorPersonId(entity.getAuditorPersonId());
		}
		if (Common.notEmpty(entity.getAuditorDate())) {
			dhh.setAuditorDate(entity.getAuditorDate());
		}
		if (Common.notEmpty(entity.getCreatePersonName())) {
			dhh.setCreatePersonName (entity.getCreatePersonName());
		}
		if (Common.notEmpty(entity.getCreatePersonId())) {
			dhh.setCreatePersonId(entity.getCreatePersonId());
		}
		if (Common.notEmpty(entity.getCreateDate())) {
			dhh.setCreateDate(entity.getCreateDate());
		}
		if (Common.notEmpty(entity.getLastModifyPersonId())) {
			dhh.setLastModifyPersonId(entity.getLastModifyPersonId());
		}
		if (Common.notEmpty(entity.getLastModifyPersonName())) {
			dhh.setLastModifyPersonName(entity.getLastModifyPersonName());
		}
		if (Common.notEmpty(entity.getLastModifyDate())) {
			dhh.setLastModifyDate(entity.getLastModifyDate());
		}
		if (Common.notEmpty(entity.getForeignKey())) {
			dhh.setForeignKey(entity.getForeignKey());
		}
		if (entity.getIsdel() !=null && !"".equals(entity.getIsdel())) {
			dhh.setIsdel(entity.getIsdel());
		}
		return dhh;
	}
	
	/**
	 * 获取DataHrHeadcount
	 * @param entity
	 * @param use
	 * @return DataHrHeadcount
	 */
	public DataHrLaborproduction getDataHrLaborproduction(HeadCountLaborProduction entity){
		DataHrLaborproduction dhl = new DataHrLaborproduction();
		if (entity.getBid() !=null && !"".equals(entity.getBid())) {
			dhl.setId(entity.getBid());
		}
		if (entity.getYear() !=null && !"".equals(entity.getYear())) {
			dhl.setYear(entity.getYear());
		}
		if (entity.getMonth() !=null && !"".equals(entity.getMonth())) {
			dhl.setMonth(entity.getMonth());
		}
		if (Common.notEmpty(entity.getCompany())) {
			dhl.setCompany(entity.getCompany());
		}
		if (entity.getOrganalId() !=null && !"".equals(entity.getOrganalId())) {
			dhl.setOrganalId(entity.getOrganalId());
		}
		if (entity.getStatus() !=null && !"".equals(entity.getStatus())) {
			dhl.setStatus(entity.getStatus());
		}
		if (Common.notEmpty(entity.getExamineTime())) {
			dhl.setExamineTime(entity.getExamineTime());
		}
		dhl.setIsdel(0);
		if (entity.getWorkersNumber() !=null && !"".equals(entity.getWorkersNumber())) {
			dhl.setWorkersNumber(entity.getWorkersNumber());
		}
		if (entity.getAverageIncome() !=null && !"".equals(entity.getAverageIncome())) {
			dhl.setAverageIncome(Double.parseDouble(entity.getAverageIncome()));
		}
		if (entity.getAverageProfit() !=null && !"".equals(entity.getAverageProfit())) {
			dhl.setAverageProfit(Double.parseDouble(entity.getAverageProfit()));
		}
		if (entity.getBusinessIncome() !=null && !"".equals(entity.getBusinessIncome())) {
			dhl.setBusinessIncome(Double.parseDouble(entity.getBusinessIncome()));
		}
		if (entity.getBusinessProfit() !=null && !"".equals(entity.getBusinessProfit())) {
			dhl.setBusinessProfit(Double.parseDouble(entity.getBusinessProfit()));
		}
		if (Common.notEmpty(entity.getReportPersonName())) {
			dhl.setReportPersonName(entity.getReportPersonName());
		}
		if (Common.notEmpty(entity.getReportPersonId())) {
			dhl.setReportPersonId(entity.getReportPersonId());
		}
		if (Common.notEmpty(entity.getReportDate())) {
			dhl.setReportDate(entity.getReportDate());
		}
		if (Common.notEmpty(entity.getAuditorPersonName())) {
			dhl.setAuditorPersonName(entity.getAuditorPersonName());
		}
		if (Common.notEmpty(entity.getAuditorPersonId())) {
			dhl.setAuditorPersonId(entity.getAuditorPersonId());
		}
		if (Common.notEmpty(entity.getAuditorDate())) {
			dhl.setAuditorDate(entity.getAuditorDate());
		}
		if (Common.notEmpty(entity.getCreatePersonName())) {
			dhl.setCreatePersonName (entity.getCreatePersonName());
		}
		if (Common.notEmpty(entity.getCreatePersonId())) {
			dhl.setCreatePersonId(entity.getCreatePersonId());
		}
		if (Common.notEmpty(entity.getCreateDate())) {
			dhl.setCreateDate(entity.getCreateDate());
		}
		if (Common.notEmpty(entity.getLastModifyPersonId())) {
			dhl.setLastModifyPersonId(entity.getLastModifyPersonId());
		}
		if (Common.notEmpty(entity.getLastModifyPersonName())) {
			dhl.setLastModifyPersonName(entity.getLastModifyPersonName());
		}
		if (Common.notEmpty(entity.getLastModifyDate())) {
			dhl.setLastModifyDate(entity.getLastModifyDate());
		}
		if (Common.notEmpty(entity.getForeignKey())) {
			dhl.setForeignKey(entity.getForeignKey());
		}
		if (entity.getIsdel() !=null && !"".equals(entity.getIsdel())) {
			dhl.setIsdel(entity.getIsdel());
		}
		return dhl;
	}
	
	/**
	 * 审核校验判断是否还能够审核，可能别人已经审核过了
	 * @param entityID 采购ID
	 * @return
	 */
	public CommitResult saveHrExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		HeadCountLaborProduction hr=headCountDao.get(entityID);
		if(hr.getStatus()==null){
			result=CommitResult.createErrorResult("该信息已删除");
			return result;
		}
//		if(!hr.getStatus().equals(Base.examstatus_2))
//		{
//			
//			result=CommitResult.createErrorResult("该信息已不用审核");
//			return result;
//		}
		result.setResult(true);
		return result;
	}
	
	
	/**
	 * 审核
	 * @param entityID 人资ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveHeadCountLaborProductionExamine(Integer entityID,String examStr,Integer examResult,HhUsers use){
		
		HeadCountLaborProduction entity = get(entityID);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=new CommitResult();
		result=saveHrExamineCheck(entityID);
		if(!result.isResult())
		{
			return result;
		}
		/*if(examResult==50116){
			entity.setStatus(Base.examstatus_4);
		}else if(examResult==50117){
			entity.setStatus(Base.examstatus_3);
		}*/
		//审核不通过
		if(examResult.equals(Base.examresult_1)){
			entity.setStatus(Base.examstatus_3);
			entity.setAuditorDate(df.format(new Date()));
			entity.setAuditorPersonId(use.getVcEmployeeId());
			entity.setAuditorPersonName(use.getVcName());
		}
		//审核通过
		else if(examResult.equals(Base.examresult_2)){
			entity.setStatus(Base.examstatus_4);
			entity.setAuditorDate(df.format(new Date()));
			entity.setAuditorPersonId(use.getVcEmployeeId());
			entity.setAuditorPersonName(use.getVcName());
			//删除工作提醒
			potalMsgService.updatePortalMsg("data_hr_headcount", entity.getId().toString());
			//删除工作提醒
			//potalMsgService.updatePortalMsg("data_hr_laborproduction", entityID.toString());
		}else{
			entity.setStatus(examResult);
		}
		entity.setExamineTime(df.format(new Date()));
		
		//保存entity
		CommitResult hrresult= updateHeadCountLaborProduction(entity,use,examResult);
		if(!hrresult.isResult())
			return hrresult;
		
		HeadCountLaborProduction hc= (HeadCountLaborProduction) hrresult.getEntity();
		Integer examineentityid=hc.getId();
		examineService.saveExamine( examineentityid, Base.examkind_hr_headCount, use, examStr, examResult);
		result.setResult(true);
		return result;
	}

	@Override
	public List getOverviewIndustry(String year,String month) {
		List list =headCountDao.getOverviewIndustry(year,month);
		return list;
	}

	@Override
	public CommitResult synHeadCount() {
	
      	CommitResult result=new CommitResult();
      	try{
		HhUsers user = new HhUsers();
		user.setVcName("系统管理员");
		user.setVcEmployeeId("9999999901");
		Calendar calendar = Calendar.getInstance();
		String year = calendar.get(Calendar.YEAR)+"";
		String month = calendar.get(Calendar.MONTH) + "";
		String first=null;
		String last=null;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		calendar.set(Calendar.YEAR,Integer.parseInt(year));
		calendar.set(Calendar.MONTH,Integer.parseInt(month)-1);
    	first = format.format(calendar.getTime());
        last = format.format(calendar.getTime());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List list = getOverviewIndustry(year, month);
		if(list.size() == 0){
			result.setExceptionStr("没有需要同步的数据！");
			result.setResult(false);
			return result;
		}else{
			for(Object obj : list){
				String nnodeId = obj.toString();
				HhInterfaceLog hhInterfaceLog = new HhInterfaceLog();
				hhInterfaceLog.setCallPersonName("系统管理员");
				hhInterfaceLog.setCallVcEmployeeId("9999999901");
				hhInterfaceLog.setCallTime(df.format(new Date()));
				hhInterfaceLog.setIntefaceAlias("总人数与劳动生产率");
				hhInterfaceLog.setRemark(systemDao.getHrOrginfoNameBynnodeID(nnodeId));
					if(nnodeId.equals(Base.HRTop)){
						String res=new ODPRequest(Base.ESB_URL,Base.BYLAW_Appsecret)
						.addTextSysPara("AccessToken", Base.BYLAW_AccessToken)
						.addTextSysPara("Method", Base.HEADCOUNT_METHOD)
						.addTextSysPara("Format","json")
						//应用参数
						.addTextAppPara("StartMonths",first)
						.addTextAppPara("EndMonths",last)
						.addTextAppPara("OrgCode","HNA16")		
						.post();
						System.out.println(res);
						JSONObject o= JSON.parseObject(res);
						Object cc = o.getJSONObject("MsgResponse").get("ResponseInfo");
						ResponseInfo responseInfo = JSON.parseObject(cc.toString(), ResponseInfo.class);
						if(responseInfo.getResult().equals("1")){
							JSONObject obj1 =  o.getJSONObject("MsgResponse").getJSONObject("Data").getJSONObject("NewDataSet").getJSONObject("dtRetu");
							if(obj1.size() > 0){
								   HeadCountLaborProduction headCountLaborProduction = new HeadCountLaborProduction();
								    
								/*    headCountLaborProduction.setCreatePersonName(user.getVcFullName());
								    headCountLaborProduction.setCreatePersonId(user.getVcEmployeeId());
								    headCountLaborProduction.setCreateDate(df.format(new Date()));*/
								    headCountLaborProduction.setCompany(obj1.getString("V_INDUSTRY_NAME"));
								    headCountLaborProduction.setOrganalId(nnodeId);
									headCountLaborProduction.setWorkersNumber(obj1.getInteger("N_STAFF_CONTRAT"));
									headCountLaborProduction.setTworkersNumber(obj1.getInteger("N_STAFF_OTHER"));
									headCountLaborProduction.setYear(Integer.parseInt(year));
									headCountLaborProduction.setStatus(50112);
									/*headCountLaborProduction.setIsdel(0);*/
									headCountLaborProduction.setMonth(Integer.parseInt(month));
									saveHeadCountLaborProduction(headCountLaborProduction, user, "save");
									}
							}
						hhInterfaceLog.setBackResult(responseInfo.getResult());
						hhInterfaceLog.setBackResultInfo(cc.toString());
						hhInterfaceLog.setIntefaceName(Base.HEADCOUNT_METHOD);
					}else{
						String res=new ODPRequest(Base.ESB_URL,Base.BYLAW_Appsecret)
						.addTextSysPara("AccessToken", Base.BYLAW_AccessToken)
						.addTextSysPara("Method", Base.HEADCOUNT_BUSINESS_METHOD)
						.addTextSysPara("Format","json")
						//应用参数
						.addTextAppPara("StartMonths",first)
						.addTextAppPara("EndMonths",last)
						.addTextAppPara("OrgCode","HNA16")		
						.post();
						System.out.println(res);
						JSONObject o= JSON.parseObject(res);
						Object cc = o.getJSONObject("MsgResponse").get("ResponseInfo");
						ResponseInfo responseInfo = JSON.parseObject(cc.toString(), ResponseInfo.class);
						String dataauth=systemService.getCompanyDataByNNodelID(nnodeId);
						if(responseInfo.getResult().equals("1")){
							JSONArray objs =  o.getJSONObject("MsgResponse").getJSONObject("Data").getJSONArray("NewDataSet");
						     for (int i = 0; i < objs.size(); i++) {
						    	   if(dataauth.equals(objs.getJSONObject(i).getString("V_COMP_NAME"))){
						    		    HeadCountLaborProduction headCountLaborProduction = new HeadCountLaborProduction();
						    		    headCountLaborProduction.setCompany(dataauth);
									    headCountLaborProduction.setOrganalId(nnodeId);
										headCountLaborProduction.setWorkersNumber(objs.getJSONObject(i).getInteger("N_STAFF_CONTRAT"));
										headCountLaborProduction.setTworkersNumber(objs.getJSONObject(i).getInteger("N_STAFF_OTHER"));
										headCountLaborProduction.setYear(Integer.parseInt(year));
										headCountLaborProduction.setStatus(50112);
										/*headCountLaborProduction.setIsdel(0);*/
										headCountLaborProduction.setMonth(Integer.parseInt(month));
										saveHeadCountLaborProduction(headCountLaborProduction, user, "save");
						    	   }
								
							}
							
						}
						hhInterfaceLog.setBackResult(responseInfo.getResult());
						hhInterfaceLog.setBackResultInfo(cc.toString());
						hhInterfaceLog.setIntefaceName(Base.HEADCOUNT_BUSINESS_METHOD);
					}
					commonDao.saveOrUpdate(hhInterfaceLog);
				}
			}
			result.setExceptionStr("同步成功！");
			result.setResult(true);
			return result;
      	}catch (Exception e) {
			result.setExceptionStr("同步失败！");
			result.setResult(false);
			return result;
		}
	}

}
