package com.softline.service.hr.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.softline.dao.hr.IManagerialStaffDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.DataHrManagerialStaff;
import com.softline.entity.HhInterfaceLog;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.HeadCountLaborProduction;
import com.softline.entityTemp.ResponseInfo;
import com.softline.service.hr.IManagerialStaffService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Service("managerialStaffService")
public class ManagerialStaffService implements IManagerialStaffService{
	
	@Autowired
	@Qualifier("managerialStaffDao")
	private IManagerialStaffDao managerialStaffDao;
	
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
	public DataHrManagerialStaff get(Integer id) {
		DataHrManagerialStaff entity = managerialStaffDao.get(id);
		return entity;
	}
	
	public boolean get(DataHrManagerialStaff entity) {
		return managerialStaffDao.get(entity);
	}
	/**
	 * 查询报表
	 */
	public MsgPage findPageList(DataHrManagerialStaff entity, Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
       //总记录数
        Integer totalRecords = managerialStaffDao.getHrFormsListView(entity,null,null).size();
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<DataHrManagerialStaff> list = managerialStaffDao.getHrFormsListView(entity, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);  
        return msgPage;
	}

	public MsgPage findPageList(DataHrManagerialStaff entity, Integer curPageNum, Integer pageSize,String isAllCompany) {
		
		if("isall".equals(isAllCompany)){
			
			String nNodeID = entity.getOrganalId();
			if(!Common.notEmpty(nNodeID))
				return new MsgPage();
			String nNodeIDs = systemService.getDataauthNnodeIDs(nNodeID);
			nNodeIDs = dealWithAuthorstr(nNodeIDs);
			entity.setOrganalId(nNodeIDs);
			MsgPage msgPage = new MsgPage();       
	       //总记录数
	        Integer totalRecords = managerialStaffDao.getHrFormsListView(entity,null,null).size();
	    	//当前页开始记录
	    	int offset = msgPage.countOffset(curPageNum,pageSize);  
	    	//分页查询结果集
	    	List<DataHrManagerialStaff> list = managerialStaffDao.getHrFormsListView(entity, offset, pageSize);
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
	 * 修改信息
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult updateDataHrManagerialStaff(DataHrManagerialStaff entity, HhUsers use,Integer i) {
		entity.setLastModifyDate(df.format(new Date()));
		entity.setLastModifyPersonId(use.getVcEmployeeId());
		entity.setLastModifyPersonName(use.getVcName());
		CommitResult result=new CommitResult();
		//entity.setOrganalId("0001");
		if(i==Base.examstatus_1){
			result=saveDataHrManagerialStaffCheck(entity);
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
	public CommitResult saveDataHrManagerialStaff(DataHrManagerialStaff entity,
			HhUsers use,String op) {
		CommitResult result=new CommitResult();
		
		if(op.equals("saveReport")){
			result=reportDataHrManagerialStaffCheck(entity);
		}else if(op.equals("save")){
			result=saveDataHrManagerialStaffCheck(entity);
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
			potalMsgService.savePortalMsg(new PortalMsg(entity.getCompany()+entity.getYear()+"年"+entity.getMonth()+"月管理干部数未审核","管理干部数",df.format(new Date()),0,0,0,
					entity.getCreatePersonName(),entity.getCreateDate(),entity.getLastModifyPersonName(),
					entity.getLastModifyDate(),entity.getOrganalId(),systemService.getParentBynNodeID(entity.getOrganalId(),
							Base.financetype),"bimWork_glgbsSh_e","data_hr_managerialStaff",entity.getId().toString(),"/shanghai-gzw/managerialStaff/examinelist?id="+entity.getId()));
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	public CommitResult deleteDataHrManagerialstaff(Integer id, HhUsers use) {
		DataHrManagerialStaff entity=managerialStaffDao.get(id);
		CommitResult result=new CommitResult();
		if(entity!=null)
		{
			entity.setIsdel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			commonDao.delete(entity);
			commonDao.saveOrUpdate(entity);
			//删除工作提醒
			potalMsgService.updatePortalMsg("data_hr_managerialStaff", id.toString());
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
		DataHrManagerialStaff hr=managerialStaffDao.get(entityID);
		if(hr.getStatus()==null){
			result=CommitResult.createErrorResult("该信息已删除");
			return result;
		}
//		if(!hr.getStatus().equals(Base.examstatus_2))
//		{
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
	public CommitResult saveDataHrManagerialstaffExamine(Integer entityID,String examStr,Integer examResult,HhUsers use){
		
		DataHrManagerialStaff entity = get(entityID);
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
			potalMsgService.updatePortalMsg("data_hr_managerialStaff", entityID.toString());
		}else{
			entity.setStatus(examResult);
		}
		entity.setExamineTime(df.format(new Date()));
		//保存entity
		CommitResult hrresult= updateDataHrManagerialStaff(entity,use,examResult);
		if(!hrresult.isResult())
			return hrresult;
		
		DataHrManagerialStaff hc= (DataHrManagerialStaff) hrresult.getEntity();
		Integer examineentityid=hc.getId();
		examineService.saveExamine( examineentityid, Base.examkind_hr_managerialstaff, use, examStr, examResult);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 保存Check
	 * @param entity
	 * @return
	 */
	private CommitResult saveDataHrManagerialStaffCheck(DataHrManagerialStaff entity)
	{
		CommitResult result=new CommitResult();
		if(!managerialStaffDao.saveDataHrManagerialStaffRepeatCheck(entity))
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
	 * 保存上报Check
	 * @param entity
	 * @return
	 */
	private CommitResult reportDataHrManagerialStaffCheck(DataHrManagerialStaff entity)
	{
		CommitResult result=new CommitResult();
		if(!managerialStaffDao.repeatDataHrManagerialStaffCheck(entity))
		{
			//result=CommitResult.createErrorResult(entity.getCompany()+entity.getYear()+"年"+entity.getMonth()+"月已经有数据，不能再上报");
			if(!entity.getCompany().equals("")&&entity.getCompany()!=null){
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
	private CommitResult reportDataCheck(DataHrManagerialStaff entity)
	{
		CommitResult result=new CommitResult();
		if(!managerialStaffDao.repeatDataCheck(entity))
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
	public MsgPage findExaminePageList(DataHrManagerialStaff entity,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = managerialStaffDao.getExamineHrFormsListViewCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<DataHrManagerialStaff> list = managerialStaffDao.getExamineHrFormsListView(entity, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);  
        return msgPage;
	}
	
	@Override
	public MsgPage findExaminePageList(DataHrManagerialStaff entity,
			Integer curPageNum, Integer pageSize,String isAllCompany){
		if("isall".equals(isAllCompany)){
			
			String nNodeID = entity.getOrganalId();
			if(!Common.notEmpty(nNodeID))
				return new MsgPage();
			String nNodeIDs = systemService.getDataauthNnodeIDs(nNodeID);
			nNodeIDs = dealWithAuthorstr(nNodeIDs);
			entity.setOrganalId(nNodeIDs);
			MsgPage msgPage = new MsgPage();       
	        //总记录数
	        Integer totalRecords = managerialStaffDao.getExamineHrFormsListViewCount(entity);
	    	//当前页开始记录
	    	int offset = msgPage.countOffset(curPageNum,pageSize);  
	    	//分页查询结果集
	    	List<DataHrManagerialStaff> list = managerialStaffDao.getExamineHrFormsListView(entity, offset, pageSize);
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
	
	@Override
	public List getOverviewIndustry(String year,String month) {
		List list =managerialStaffDao.getOverviewIndustry(year,month);
		return list;
	}

	@Override
	public CommitResult synManageial() {
		CommitResult result=new CommitResult();
      	try{
		HhUsers user = new HhUsers();
		user.setVcName("系统管理员");
		user.setVcEmployeeId("9999999901");
		String year = null;
		String month = null;
		Calendar calendar = Calendar.getInstance();
		if("0".equals(calendar.get(Calendar.MONTH))){
			month = "12";
			year = (calendar.get(Calendar.YEAR)-1)+"";
		}else{
			month = calendar.get(Calendar.MONTH) + "";
			year = calendar.get(Calendar.YEAR) + "";
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
    	//获取前月的第一天：
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
    	String first = format.format(calendar.getTime());
    	//获取前月的最后一天
    	calendar.add(Calendar.MONTH, 1);
    	calendar.set(Calendar.DAY_OF_MONTH,0);
    	String last = format.format(calendar.getTime());
		
		boolean flag=true;
		List list = getOverviewIndustry(year, month);
		if(list.size() == 0){
			result.setExceptionStr("没有需要同步的数据！");
			result.setResult(false);
			return result;
		}else{
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int i = 0;
			for(Object obj : list){
				String nnodeId = obj.toString();
				HhInterfaceLog hhInterfaceLog=new HhInterfaceLog();
				hhInterfaceLog.setCallPersonName(user.getVcName());
				hhInterfaceLog.setCallVcEmployeeId(user.getVcEmployeeId());
				hhInterfaceLog.setRemark(systemService.getHrOrginfoNameBynnodeID(nnodeId));
				hhInterfaceLog.setCallTime(df.format(new Date()));
				hhInterfaceLog.setIntefaceAlias("管理人员数");
					//传入url与秘钥
					String ress=new ODPRequest(Base.ESB_URL,Base.BYLAW_Appsecret)
					.addTextSysPara("AccessToken", Base.BYLAW_AccessToken)
					.addTextSysPara("Method", Base.MANAGE_METHOD)
					.addTextSysPara("Format","json")
					//应用参数
					.addTextAppPara("StartDate",first)
					.addTextAppPara("EndDate",last)
					.addTextAppPara("OrganID",nnodeId)	
					.post();
					System.out.println(ress);
					JSONObject os= JSON.parseObject(ress);
					Object ccs = os.getJSONObject("MsgResponse").get("ResponseInfo");
					ResponseInfo responseInfos = JSON.parseObject(ccs.toString(), ResponseInfo.class);
					DataHrManagerialStaff lists = new DataHrManagerialStaff();
					String dataauth=systemService.getCompanyDataByNNodelID(nnodeId);
					lists.setYear(Integer.parseInt(year));
					lists.setStatus(50112);
					lists.setMonth(Integer.parseInt(month));
					lists.setOrganalId(nnodeId);
					lists.setCompany(dataauth);
					if(responseInfos.getResult().equals("1")){
						JSONObject objs =  os.getJSONObject("MsgResponse").getJSONObject("Data").getJSONObject("NewDataSet").getJSONObject("dtRetu");
						if(objs.size() > 0){
							   lists.setSequenceM(objs.getInteger("MGR_LEVEL"));
							   lists.setUnSequenceM(objs.getInteger("MGR_NOLEVEL"));
							   lists.setManNumber(objs.getInteger("MGR_MALE"));
							   lists.setWomanNumber(objs.getInteger("MGR_FEMALE"));		
						}
					}
					hhInterfaceLog.setBackResult(responseInfos.getResult());
					hhInterfaceLog.setBackResultInfo(ccs.toString());
					hhInterfaceLog.setIntefaceName(Base.MANAGE_METHOD);
					saveDataHrManagerialStaff(lists, user,"save");	
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
