package com.softline.service.sixrate.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Base;
import com.softline.dao.sixrate.ISixRateDcDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.DataSixRateDc;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entityTemp.CommitResult;
import com.softline.service.sixrate.ISixRateDcService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Service("sixRateDcService")
public class SixRateDcService implements ISixRateDcService{
	
	@Autowired
	@Qualifier("sixRateDcDao")
	private ISixRateDcDao sixRateDcDao;
	
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
	/**
	 * id查询
	 */
	public DataSixRateDc get(Integer id) {
		DataSixRateDc entity = sixRateDcDao.get(id);
		return entity;
	}
	
	public boolean get(DataSixRateDc entity) {
		return sixRateDcDao.get(entity);
	}
	
	/**
	 * 查询报表
	 */
	public MsgPage findPageList(DataSixRateDc entity, Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
       //总记录数
        Integer totalRecords = sixRateDcDao.getHrFormsListViewCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<DataSixRateDc> list = sixRateDcDao.getHrFormsListView(entity, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);  
        return msgPage;
	}

	
	/**
	 * 修改信息
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult updateDataSixRateDc(DataSixRateDc entity, HhUsers use,Integer i) {
		entity.setLastModifyDate(df.format(new Date()));
		entity.setLastModifyPersonId(use.getVcEmployeeId());
		entity.setLastModifyPersonName(use.getVcName());
		CommitResult result=new CommitResult();
		if(i==Base.examstatus_1){
			result=saveDataSixRateDcCheck(entity);
			if(!result.isResult())
				return result;
		}
		//保存劳动数
		//commonDao.delete(entity);
		commonDao.saveOrUpdate(entity);
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	@Override
	public CommitResult saveDataSixRateDc(DataSixRateDc entity,
			HhUsers use,String op) {
		CommitResult result=new CommitResult();
		
		if(op.equals("saveReport")){
			result=reportDataSixRateDcCheck(entity);
		}else if(op.equals("save")){
			result=saveDataSixRateDcCheck(entity);
		}else{
			result=reportDataCheck(entity);
		}
		if(!result.isResult())
			return result;
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
		commonDao.saveOrUpdate(entity);
		if(op.equals("saveReport")||op.equals("report")){
			potalMsgService.savePortalMsg(new PortalMsg(entity.getYear()+"年" + entity.getMonth()+"月地产市场占有率未审核","市场占有率",df.format(new Date()),0,0,0,
					entity.getCreatePersonName(),entity.getCreateDate(),entity.getLastModifyPersonName(),
					entity.getLastModifyDate(),entity.getOrganalId(),systemService.getParentBynNodeID(entity.getOrganalId(),
							Base.financetype),"sixRateDcSh_e","data_six_rate_dc",entity.getId().toString(),"/shanghai-gzw/sixRateDc/examinelist?id="+entity.getId()));
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	public CommitResult deleteDataSixRateDc(Integer id, HhUsers use) {
		DataSixRateDc entity=sixRateDcDao.get(id);
		CommitResult result=new CommitResult();
		if(entity!=null)
		{
			entity.setIsdel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			commonDao.delete(entity);
			commonDao.saveOrUpdate(entity);
			//删除工作提醒
			potalMsgService.updatePortalMsg("data_six_rate_dc", id.toString());
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
	public CommitResult saveHrExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		DataSixRateDc hr=sixRateDcDao.get(entityID);
		if(hr.getStatus()==null){
			result=CommitResult.createErrorResult("该信息已删除");
			return result;
		}
		if(!hr.getStatus().equals(Base.examstatus_2))
		{
			result=CommitResult.createErrorResult("该信息已不用审核");
			return result;
		}
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
	public CommitResult saveDataSixRateDcExamine(Integer entityID,String examStr,Integer examResult,HhUsers use){
		
		DataSixRateDc entity = get(entityID);
		CommitResult result=new CommitResult();
		result=saveHrExamineCheck(entityID);
		if(!result.isResult())
		{
			return result;
		}
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
			potalMsgService.updatePortalMsg("data_six_rate_dc", entityID.toString());
		}else{
			entity.setStatus(examResult);
		}
		entity.setExamineTime(df.format(new Date()));
		//保存entity
		CommitResult hrresult= updateDataSixRateDc(entity,use,examResult);
		if(!hrresult.isResult())
			return hrresult;
		
		DataSixRateDc hc= (DataSixRateDc) hrresult.getEntity();
		Integer examineentityid=hc.getId();
		examineService.saveExamine( examineentityid, Base.examkind_six_rateDc, use, examStr, examResult);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 保存Check
	 * @param entity
	 * @return
	 */
	private CommitResult saveDataSixRateDcCheck(DataSixRateDc entity)
	{
		CommitResult result=new CommitResult();
		if(!sixRateDcDao.saveDataSixRateDcCheck(entity))
		{
			//result=CommitResult.createErrorResult(entity.getCompany()+entity.getYear()+"年"+entity.getMonth()+"月已经有数据，不能再添加");
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
	 * 保存上报Check
	 * @param entity
	 * @return
	 */
	private CommitResult reportDataSixRateDcCheck(DataSixRateDc entity)
	{
		CommitResult result=new CommitResult();
		if(!sixRateDcDao.repeatDataSixRateDcCheck(entity))
		{
			if(entity.getCompany()!=null&&!entity.getCompany().equals("")){
				result=CommitResult.createErrorResult(entity.getCompany()+entity.getYear()+"年"+entity.getMonth()+"月已经有数据，不能再上报");
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
	private CommitResult reportDataCheck(DataSixRateDc entity)
	{
		CommitResult result=new CommitResult();
		if(!sixRateDcDao.repeatDataCheck(entity))
		{
			if(entity.getCompany()!=null&&!entity.getCompany().equals("")){
				result=CommitResult.createErrorResult(entity.getCompany()+entity.getYear()+"年"+entity.getMonth()+"月已经有数据，不能再上报");
			}else{
				result=CommitResult.createErrorResult("该数据已经删除，不能上报！");
			}
			return result;
		}
		result.setResult(true);
		return result;
	}

	@Override
	public MsgPage findExaminePageList(DataSixRateDc entity,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = sixRateDcDao.getExamineHrFormsListViewCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<DataSixRateDc> list = sixRateDcDao.getExamineHrFormsListView(entity, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);  
        return msgPage;
	}

}
