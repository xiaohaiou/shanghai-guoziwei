package com.softline.service.report.impl;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.report.IMoneyFlowPlanMonthDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.IFinanceHistroyTreeDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhOrganInfoTreeRelationHistory;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.moneyFlow.DataMoneyFlowMonth;
import com.softline.entity.moneyFlow.DataMoneyFlowMonthCi;
import com.softline.entity.moneyFlow.DataMoneyFlowMonthCo;
import com.softline.entity.moneyFlow.DataMoneyFlowMonthIo;
import com.softline.entity.moneyFlow.DataMoneyFlowWeek;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IMoneyFlowPlanMonthService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.service.system.impl.CommonService;
import com.softline.util.MsgPage;


@Service("moneyFlowPlanMonthService")
/**
 * 
 * @author ky_tian
 *
 */
public class MoneyFlowPlanMonthService implements IMoneyFlowPlanMonthService{

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;

	@Autowired
	@Qualifier("moneyFlowPlanMonthDao")
	private IMoneyFlowPlanMonthDao moneyFlowPlanMonthDao;

	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	
	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;	
	
	@Resource(name = "systemService")
	private ISystemService systemService;	
	
	@Resource(name = "financeHistroyTreeDao")
	private IFinanceHistroyTreeDao financeHistroyTreeDao;	
	
	
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * id查询
	 */
	public DataMoneyFlowMonth get(Integer id) {
		DataMoneyFlowMonth entity = moneyFlowPlanMonthDao.get(id);
		return entity;
	}

	public boolean get(DataMoneyFlowMonth entity) {
		return moneyFlowPlanMonthDao.get(entity);
	}

	/**
	 * 查询报表
	 */
	public MsgPage findPageList(DataMoneyFlowMonth entity, Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
       //总记录数
        Integer totalRecords = moneyFlowPlanMonthDao.getHrFormsListViewCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<DataMoneyFlowMonth> list = moneyFlowPlanMonthDao.getHrFormsListView(entity, offset, pageSize);
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
	public CommitResult updateMoneyFlowPlanMonth(DataMoneyFlowMonth entity, HhUsers use,Integer i) {
		entity.setLastModifyDate(df.format(new Date()));
		entity.setLastModifyPersonId(use.getVcEmployeeId());
		entity.setLastModifyPersonName(use.getVcName());
		CommitResult result=new CommitResult();
		if(i==Base.examstatus_1){
			result=saveMoneyFlowPlanMonthCheck(entity);
			if(!result.isResult())
				return result;
		}
		//保存劳动数
		//commonDao.delete(entity);
		HhOrganInfoTreeRelation node = systemDao.getTreeOrganInfoNode(Base.financetype,entity.getOrg());
		entity.setParentorg(node.getVcOrganId());
		commonDao.saveOrUpdate(entity);
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}

	@Override
	public CommitResult saveMoneyFlowPlanMonth(DataMoneyFlowMonth entity,
			HhUsers use,String op) {
		CommitResult result=new CommitResult();
		
		if(op.equals("save")){
			result=saveMoneyFlowPlanMonthCheck(entity);
		}else{
			result=moneyFlowPlanMonthCheck(entity);
		}
		if(!result.isResult())
			return result;
		if(entity.getPid()==null)
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
		if(entity.getTempDate()!=null)entity.setDate(entity.getTempDate());
		HhOrganInfoTreeRelation node = systemDao.getTreeOrganInfoNode(Base.financetype,entity.getOrg());
		entity.setParentorg(node.getVcOrganId());
		commonDao.saveOrUpdate(entity);
		if(op.equals("saveReport")||op.equals("report")){
			potalMsgService.savePortalMsg(new PortalMsg("现金流月计划需要审核","现金流月计划",df.format(new Date()),0,0,0,
					entity.getCreatePersonName(),entity.getCreateDate(),entity.getLastModifyPersonName(),
					entity.getLastModifyDate(),entity.getOrg(),systemService.getParentBynNodeID(entity.getOrg(),
							Base.financetype),"bimWork_xjlyjhSh","data_money_flow_month",entity.getPid().toString()));
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}

	public CommitResult deleteMoneyFlowPlanMonth(Integer id, HhUsers use) {
		DataMoneyFlowMonth entity=moneyFlowPlanMonthDao.get(id);
		CommitResult result=new CommitResult();
		if(entity!=null)
		{
			entity.setIsdel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			commonDao.delete(entity);
			//删除工作提醒
			potalMsgService.updatePortalMsg("data_money_flow_month", id.toString());
			//commonDao.saveOrUpdate(entity);
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
		DataMoneyFlowMonth hr=moneyFlowPlanMonthDao.get(entityID);
/*		if(!hr.getStatus().equals(Base.examstatus_2))
		{
			result=CommitResult.createErrorResult("该信息已不用审核");
			return result;
		}*/
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
	public CommitResult saveMoneyFlowPlanMonthExamine(Integer entityID,String examStr,Integer examResult,HhUsers use){
		
		DataMoneyFlowMonth entity = get(entityID);
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
			potalMsgService.updatePortalMsg("data_money_flow_month", entityID.toString());
		}else{
			entity.setStatus(examResult);
		}
		//entity.setExamineTime(df.format(new Date()));
		//保存entity
		CommitResult hrresult= updateMoneyFlowPlanMonth(entity,use,examResult);
		if(!hrresult.isResult())
			return hrresult;
		
		DataMoneyFlowMonth hc= (DataMoneyFlowMonth) hrresult.getEntity();
		Integer examineentityid=hc.getPid();
		examineService.saveExamine( examineentityid, Base.examkind_hr_moneyFlowPlanMonth, use, examStr, examResult);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 保存Check
	 * @param entity
	 * @return
	 */
	private CommitResult saveMoneyFlowPlanMonthCheck(DataMoneyFlowMonth entity)
	{
		CommitResult result=new CommitResult();
		if(!moneyFlowPlanMonthDao.saveMoneyFlowPlanMonthCheck(entity))
		{
			if(entity.getOrgname()!=null&&!entity.getOrgname().equals("")){
				result=CommitResult.createErrorResult(entity.getOrgname()+entity.getDate()+"月已经有数据，不能再添加");
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
	private CommitResult moneyFlowPlanMonthCheck(DataMoneyFlowMonth entity)
	{
		CommitResult result=new CommitResult();
		if(!moneyFlowPlanMonthDao.moneyFlowPlanMonthCheck(entity))
		{
			//result=CommitResult.createErrorResult(entity.getCompany()+entity.getYear()+"年"+entity.getMonth()+"月已经有数据，不能再上报");
			if(!entity.getOrgname().equals("")&&entity.getOrgname()!=null){
				result=CommitResult.createErrorResult(entity.getOrgname()+entity.getDate()+"已经上报，不能再上报");
			}else{
				result=CommitResult.createErrorResult("该数据已经删除，不能上报！");
			}
			return result;
		}
		result.setResult(true);
		return result;
	}

	@Override
	public MsgPage findExaminePageList(DataMoneyFlowMonth entity,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = moneyFlowPlanMonthDao.getExamineHrFormsListViewCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<DataMoneyFlowMonth> list = moneyFlowPlanMonthDao.getExamineHrFormsListView(entity, offset, pageSize);
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);  
        return msgPage;
	}

	/**
	 * 汇总数据--查询相关公司信息下一层级公司
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @return
	 */
	@Override
	public MsgPage getDataMoneyFlowMonthForSumData(DataMoneyFlowMonth entity ,Integer curPageNum, Integer pageSize,DataMoneyFlowMonth backTempBean){
		MsgPage msgPage = new MsgPage();   
		List<DataMoneyFlowMonth> listtotal;
		
		// 周计划时间，设置为上一个月的时间
		setDateForLastMonth(entity);
		
        //总记录数
		listtotal = moneyFlowPlanMonthDao.getDataMoneyFlowMonthForSumData(entity,
																		  0,
																		  moneyFlowPlanMonthDao.getDataMoneyFlowMonthForSumDataNumber(entity));
        Integer totalRecords =listtotal.size();
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<DataMoneyFlowMonth> list = moneyFlowPlanMonthDao.getDataMoneyFlowMonthForSumData(entity, offset, pageSize);
    	
    	if(null != listtotal && listtotal.size()>0){	  		
    		double operationalInflow = 0.0d;
    		double operationalOutflow = 0.0d;
    		double operationalNetFlow = 0.0d;
    		double investmentInflow = 0.0d;
    		double investmentOutflow = 0.0d;
    		double investmentNetFlow = 0.0d;
    		double capitalInflow = 0.0d;
    		double capitalOutflow = 0.0d;
    		double capitalNetFlow = 0.0d;
    		DecimalFormat df = new java.text.DecimalFormat("0.000");
    		
    		for (DataMoneyFlowMonth tempBean:listtotal){ 
    			if(tempBean.getOperationalInflow()!=null && 
    					CommonService.isStrParseToNum(tempBean.getOperationalInflow()))
    				operationalInflow+=Double.valueOf(tempBean.getOperationalInflow());
    			if(tempBean.getOperationalOutflow() !=null && 
    					CommonService.isStrParseToNum(tempBean.getOperationalOutflow()))
    				operationalOutflow+=Double.valueOf(tempBean.getOperationalOutflow());
    			if(tempBean.getOperationalNetFlow() !=null && 
    					CommonService.isStrParseToNum(tempBean.getOperationalNetFlow()))	
    				operationalNetFlow+=Double.valueOf(tempBean.getOperationalNetFlow());
    			if(tempBean.getInvestmentInflow() !=null && 
    					CommonService.isStrParseToNum(tempBean.getInvestmentInflow()))
    				investmentInflow+=Double.valueOf(tempBean.getInvestmentInflow());
    			if(tempBean.getInvestmentOutflow() !=null && 
    					CommonService.isStrParseToNum(tempBean.getInvestmentOutflow()))
    				investmentOutflow+=Double.valueOf(tempBean.getInvestmentOutflow());
    			if(tempBean.getInvestmentNetFlow() !=null && 
    					CommonService.isStrParseToNum(tempBean.getInvestmentNetFlow()))
    				investmentNetFlow+=Double.valueOf(tempBean.getInvestmentNetFlow());
    			if(tempBean.getCapitalInflow() !=null && 
    					CommonService.isStrParseToNum(tempBean.getCapitalInflow()))
    				capitalInflow+=Double.valueOf(tempBean.getCapitalInflow());
    			if(tempBean.getCapitalOutflow() !=null && 
    					CommonService.isStrParseToNum(tempBean.getCapitalOutflow()))
    				capitalOutflow+=Double.valueOf(tempBean.getCapitalOutflow());
    			if(tempBean.getCapitalNetFlow() !=null && 
    					CommonService.isStrParseToNum(tempBean.getCapitalNetFlow()))
    				capitalNetFlow+=Double.valueOf(tempBean.getCapitalNetFlow());
    		}   		
    		
    		backTempBean.setOperationalInflow(df.format(operationalInflow));
    		backTempBean.setOperationalOutflow(df.format(operationalOutflow));
    		backTempBean.setOperationalNetFlow(df.format(operationalNetFlow));
    		backTempBean.setInvestmentInflow(df.format(investmentInflow));
    		backTempBean.setInvestmentOutflow(df.format(investmentOutflow));
    		backTempBean.setInvestmentNetFlow(df.format(investmentNetFlow));
    		backTempBean.setCapitalInflow(df.format(capitalInflow));
    		backTempBean.setCapitalOutflow(df.format(capitalOutflow));
    		backTempBean.setCapitalNetFlow(df.format(capitalNetFlow));
    	}
    	  	
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);  
    	
    	// 清空结果集
    	if(null!=listtotal)
    		listtotal.clear();
    	
        return msgPage;

	}
	
	/**
	 * 新增虚拟公司时，汇总查询
	 * @param entityIn
	 * @param entityOut
	 * @return
	 */
	@Override
	public int sumForAllChildrenEntity(DataMoneyFlowMonth entityIn,DataMoneyFlowMonth entityOut,LinkedHashMap<String,List<LinkedHashMap<String, Object>>> MonthPlanData){
		
		if(null ==entityIn || 
				null==entityIn.getOrg() || 
				"".equals(entityIn.getOrg()) ||
				null == entityIn.getDate())
			return -1;
	
		// 周计划时间，设置为上一个月的时间
		setDateForLastMonth(entityIn);
	
		HhOrganInfoTreeRelationHistory info = financeHistroyTreeDao.getTreeOrganInfoNode(Base.financetype,
																						 entityIn.getOrg(),
																						 entityIn.getDate());
		List<DataMoneyFlowMonth> entityOut_tempList = null;
		if(null != info)
			entityOut_tempList = moneyFlowPlanMonthDao.getSumChildrenData(info, entityIn);
		
		return this.getSumBean(entityOut_tempList,entityOut,MonthPlanData);
	}
	
	public void setDateForLastMonth(DataMoneyFlowMonth entity){
		
		if(null==entity || 
				null==entity.getDate() ||
				"".equals(entity.getDate()))
			return;
			
		String date = null;
				
		String tempDate = entity.getDate(); //yyyy-mm
		
		String year = tempDate.substring(0, 4);
		
		String month = tempDate.substring(5, 7);
		
		if("01".equals(month))
			date = (Integer.valueOf(year)-1)+"-12";
		else
			date = year+"-"+((Integer.valueOf(month)-1+"").length()==2
															?(Integer.valueOf(month)-1+""):
																"0"+(Integer.valueOf(month)-1+""));
		
		entity.setDate(date);
	}
	
	
	public int getSumBean(List<DataMoneyFlowMonth> list,DataMoneyFlowMonth outBean,LinkedHashMap<String,List<LinkedHashMap<String, Object>>> MonthPlanData){
		
		if(null ==list || list.size()==0 )
			return -1;
		
		double operationalInflow = 0.0d;
		double operationalOutflow = 0.0d;
		double operationalNetFlow = 0.0d;
		double investmentInflow = 0.0d;
		double investmentOutflow = 0.0d;
		double investmentNetFlow = 0.0d;
		double capitalInflow = 0.0d;
		double capitalOutflow = 0.0d;
		double capitalNetFlow = 0.0d;
		double cashInflow = 0.0d;		
		double cashOutflow = 0.0d;			
		double netCashFlow = 0.0d;			
		double initialAvailableCash = 0.0d;	
		double finalAvailableCash = 0.0d;					
		
		Set<DataMoneyFlowMonthCi> set1 = new HashSet<DataMoneyFlowMonthCi>();
		Set<DataMoneyFlowMonthCo> set2 = new HashSet<DataMoneyFlowMonthCo>();
		Set<DataMoneyFlowMonthIo> set3 = new HashSet<DataMoneyFlowMonthIo>();
		
		for(DataMoneyFlowMonth tempBean:list){
			
			tempBean = this.getReportCashflowWeeklyExecuteAllInfo(tempBean);
			this.getAlldetailInfoForCashMonthlyExecuteAll(tempBean,MonthPlanData);
			
			if(tempBean.getOperationalInflow()!=null && 
					CommonService.isStrParseToNum(tempBean.getOperationalInflow()))
				operationalInflow+=Double.valueOf(tempBean.getOperationalInflow());
			if(tempBean.getOperationalOutflow() !=null && 
					CommonService.isStrParseToNum(tempBean.getOperationalOutflow()))
				operationalOutflow+=Double.valueOf(tempBean.getOperationalOutflow());
			if(tempBean.getOperationalNetFlow() !=null && 
					CommonService.isStrParseToNum(tempBean.getOperationalNetFlow()))	
				operationalNetFlow+=Double.valueOf(tempBean.getOperationalNetFlow());
			if(tempBean.getInvestmentInflow() !=null && 
					CommonService.isStrParseToNum(tempBean.getInvestmentInflow()))
				investmentInflow+=Double.valueOf(tempBean.getInvestmentInflow());
			if(tempBean.getInvestmentOutflow() !=null && 
					CommonService.isStrParseToNum(tempBean.getInvestmentOutflow()))
				investmentOutflow+=Double.valueOf(tempBean.getInvestmentOutflow());
			if(tempBean.getInvestmentNetFlow() !=null && 
					CommonService.isStrParseToNum(tempBean.getInvestmentNetFlow()))
				investmentNetFlow+=Double.valueOf(tempBean.getInvestmentNetFlow());
			if(tempBean.getCapitalInflow() !=null && 
					CommonService.isStrParseToNum(tempBean.getCapitalInflow()))
				capitalInflow+=Double.valueOf(tempBean.getCapitalInflow());
			if(tempBean.getCapitalOutflow() !=null && 
					CommonService.isStrParseToNum(tempBean.getCapitalOutflow()))
				capitalOutflow+=Double.valueOf(tempBean.getCapitalOutflow());
			if(tempBean.getCapitalNetFlow() !=null && 
					CommonService.isStrParseToNum(tempBean.getCapitalNetFlow()))
				capitalNetFlow+=Double.valueOf(tempBean.getCapitalNetFlow());
			if(Common.notEmpty(tempBean.getCashInflow()))
				cashInflow+=Double.valueOf(tempBean.getCashInflow());
			if(Common.notEmpty(tempBean.getCashOutflow()))
				cashOutflow+=Double.valueOf(tempBean.getCashOutflow());
			if(Common.notEmpty(tempBean.getNetCashFlow()))
				netCashFlow+=Double.valueOf(tempBean.getNetCashFlow());
			if(Common.notEmpty(tempBean.getInitialAvailableCash()))
				initialAvailableCash+=Double.valueOf(tempBean.getInitialAvailableCash());
			if(Common.notEmpty(tempBean.getFinalAvailableCash()))
				finalAvailableCash+=Double.valueOf(tempBean.getFinalAvailableCash());

			set1.addAll(tempBean.getDataMoneyFlowMonthCi());
			set2.addAll(tempBean.getDataMoneyFlowMonthCo());
			set3.addAll(tempBean.getDataMoneyFlowMonthIo());
		}
		
		DecimalFormat df = new java.text.DecimalFormat("0.000");
		outBean.setOperationalInflow(df.format(operationalInflow));
		outBean.setOperationalOutflow(df.format(operationalOutflow));
		outBean.setOperationalNetFlow(df.format(operationalNetFlow));
		outBean.setInvestmentInflow(df.format(investmentInflow));
		outBean.setInvestmentOutflow(df.format(investmentOutflow));
		outBean.setInvestmentNetFlow(df.format(investmentNetFlow));
		outBean.setCapitalInflow(df.format(capitalInflow));
		outBean.setCapitalOutflow(df.format(capitalOutflow));
		outBean.setCapitalNetFlow(df.format(capitalNetFlow));		
		outBean.setCashInflow(df.format(cashInflow));
		outBean.setCashOutflow(df.format(cashOutflow));		
		outBean.setNetCashFlow(df.format(netCashFlow));
		outBean.setInitialAvailableCash(df.format(initialAvailableCash));		
		outBean.setFinalAvailableCash(df.format(finalAvailableCash));				
		outBean.setDataMoneyFlowMonthCi(set1);
		outBean.setDataMoneyFlowMonthCo(set2);
		outBean.setDataMoneyFlowMonthIo(set3);
		
		// 清空结果集
		if(null!=list)
			list.clear();
		
		return 0;
	}
	
	/**
	 * 获取所有的信息
	 * @param entity
	 * @return
	 */
	public DataMoneyFlowMonth getReportCashflowWeeklyExecuteAllInfo(DataMoneyFlowMonth entity){
	
		if(null == entity)
			return entity;
		
		return this.get(entity.getPid());
	}
	
	/**
	 * 获取所有的详细信息
	 * @param tempBean
	 * @param MonthPlanData
	 */
	public void getAlldetailInfoForCashMonthlyExecuteAll(DataMoneyFlowMonth tempBean,LinkedHashMap<String,List<LinkedHashMap<String, Object>>> MonthPlanData){
		
		LinkedHashMap<String,List<LinkedHashMap<String, Object>>> temp_MonthPlanData = null;		
		List<LinkedHashMap<String, Object>> ciList = null;
		List<LinkedHashMap<String, Object>> coList = null;
		List<LinkedHashMap<String, Object>> ioList = null;
		
		temp_MonthPlanData = moneyFlowPlanMonthDao.getMonthPlanForDetialInfo(tempBean.getPid());
			
		if(temp_MonthPlanData.containsKey("ci")){		
			if(MonthPlanData.containsKey("ci"))	
				ciList = MonthPlanData.get("ci");
			else
				ciList = new ArrayList<LinkedHashMap<String, Object>>();
			ciList.addAll(temp_MonthPlanData.get("ci"));
			
			MonthPlanData.put("ci", ciList);		
		}
		
		if(temp_MonthPlanData.containsKey("co")){		
			if(MonthPlanData.containsKey("co"))	
				coList = MonthPlanData.get("co");
			else
				coList = new ArrayList<LinkedHashMap<String, Object>>();
			coList.addAll(temp_MonthPlanData.get("co"));
			MonthPlanData.put("co", coList);		
		}
		
		if(temp_MonthPlanData.containsKey("io")){		
			if(MonthPlanData.containsKey("io"))	
				ioList = MonthPlanData.get("io");
			else
				ioList = new ArrayList<LinkedHashMap<String, Object>>();
			ioList.addAll(temp_MonthPlanData.get("io"));
			MonthPlanData.put("io", ioList);		
		}
	}

	@Override
	public List<DataMoneyFlowMonth> findPageList(DataMoneyFlowMonth entity) {
		// TODO Auto-generated method stub
		return moneyFlowPlanMonthDao.findPageList(entity);
	}
	
	
	
	
}
