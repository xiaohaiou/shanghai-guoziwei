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
import com.softline.dao.sixrate.ISixRateKpDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.DataSixRateKp;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entityTemp.CommitResult;
import com.softline.service.sixrate.ISixRateKpService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Service("sixRateKpService")
public class SixRateKpService implements ISixRateKpService{
	
	@Autowired
	@Qualifier("sixRateKpDao")
	private ISixRateKpDao sixRateKpDao;
	
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
	public DataSixRateKp get(Integer id) {
		DataSixRateKp entity = sixRateKpDao.get(id);
		return entity;
	}
	
	public boolean get(DataSixRateKp entity) {
		return sixRateKpDao.get(entity);
	}
	
	/**
	 * 查询报表
	 */
	public MsgPage findPageList(DataSixRateKp entity, Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
       //总记录数
        Integer totalRecords = sixRateKpDao.getHrFormsListViewCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<DataSixRateKp> list = sixRateKpDao.getHrFormsListView(entity, offset, pageSize);
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
	public CommitResult updateDataSixRateKp(DataSixRateKp entity, HhUsers use,Integer i) {
		entity.setLastModifyDate(df.format(new Date()));
		entity.setLastModifyPersonId(use.getVcEmployeeId());
		entity.setLastModifyPersonName(use.getVcName());
		CommitResult result=new CommitResult();
		//entity.setOrganalId("0001");
		if(i==Base.examstatus_1){
			result=saveDataSixRateKpCheck(entity);
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
	public CommitResult saveDataSixRateKp(DataSixRateKp entity,
			HhUsers use,String op) {
		CommitResult result=new CommitResult();
		
		if(op.equals("saveReport")){
			result=reportDataSixRateKpCheck(entity);
		}else if(op.equals("save")){
			result=saveDataSixRateKpCheck(entity);
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
			//entity.setOrganalId("0001");
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
			potalMsgService.savePortalMsg(new PortalMsg(entity.getYear()+"年"+entity.getMonth()+"月酷铺市场占有率未审核","市场占有率",df.format(new Date()),0,0,0,
					entity.getCreatePersonName(),entity.getCreateDate(),entity.getLastModifyPersonName(),
					entity.getLastModifyDate(),entity.getOrganalId(),systemService.getParentBynNodeID(entity.getOrganalId(),
							Base.financetype),"sixRateKpSh_e","data_six_rate_kp",entity.getId().toString(),"/shanghai-gzw/sixRateKp/examinelist?id="+entity.getId()));
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	public CommitResult deleteDataSixRateKp(Integer id, HhUsers use) {
		DataSixRateKp entity=sixRateKpDao.get(id);
		CommitResult result=new CommitResult();
		if(entity!=null)
		{
			entity.setIsdel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			commonDao.delete(entity);
			commonDao.saveOrUpdate(entity);
			//删除工作提醒
			potalMsgService.updatePortalMsg("data_six_rate_kp", id.toString());
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
		DataSixRateKp hr=sixRateKpDao.get(entityID);
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
	public CommitResult saveDataSixRateKpExamine(Integer entityID,String examStr,Integer examResult,HhUsers use){
		
		DataSixRateKp entity = get(entityID);
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
			potalMsgService.updatePortalMsg("data_six_rate_kp", entityID.toString());
		}else{
			entity.setStatus(examResult);
		}
		entity.setExamineTime(df.format(new Date()));
		//保存entity
		CommitResult hrresult= updateDataSixRateKp(entity,use,examResult);
		if(!hrresult.isResult())
			return hrresult;
		
		DataSixRateKp hc= (DataSixRateKp) hrresult.getEntity();
		Integer examineentityid=hc.getId();
		examineService.saveExamine( examineentityid, Base.examkind_six_rateKp, use, examStr, examResult);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 保存Check
	 * @param entity
	 * @return
	 */
	private CommitResult saveDataSixRateKpCheck(DataSixRateKp entity)
	{
		CommitResult result=new CommitResult();
		if(!sixRateKpDao.saveDataSixRateKpCheck(entity))
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
	private CommitResult reportDataSixRateKpCheck(DataSixRateKp entity)
	{
		CommitResult result=new CommitResult();
		if(!sixRateKpDao.repeatDataSixRateKpCheck(entity))
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
	private CommitResult reportDataCheck(DataSixRateKp entity)
	{
		CommitResult result=new CommitResult();
		if(!sixRateKpDao.repeatDataCheck(entity))
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
	public MsgPage findExaminePageList(DataSixRateKp entity,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = sixRateKpDao.getExamineHrFormsListViewCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<DataSixRateKp> list = sixRateKpDao.getExamineHrFormsListView(entity, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);  
        return msgPage;
	}


}
