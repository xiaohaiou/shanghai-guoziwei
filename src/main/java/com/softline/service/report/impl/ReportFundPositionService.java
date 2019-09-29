package com.softline.service.report.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Base;
import com.softline.dao.report.IReportFundPositionDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.fundPosition.DataFundPosition;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportFundPositionService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISystemService;
import com.softline.util.MsgPage;

@Service("reportFundPositionService")
/**
 * 
 * @author ky_tian
 *
 */
public class ReportFundPositionService implements IReportFundPositionService{

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("reportFundPositionDao")
	private IReportFundPositionDao reportFundPositionDao;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	
	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;	
	
	@Resource(name = "systemService")
	private ISystemService systemService;	
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * id查询
	 */
	public DataFundPosition get(Integer id) {
		DataFundPosition entity = reportFundPositionDao.get(id);
		return entity;
	}
	
	/**
	 * chack
	 */
	public boolean get(DataFundPosition entity) {
		return reportFundPositionDao.get(entity);
	}
	/**
	 * 查询报表
	 */
	public MsgPage findPageList(DataFundPosition entity, Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
       //总记录数
        Integer totalRecords = reportFundPositionDao.getHrFormsListViewCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<DataFundPosition> list = reportFundPositionDao.getHrFormsListView(entity, offset, pageSize);
    	//拆分显示
    	/*for(int i = 0;i<list.size();i++){
    		String [] cash = list.get(i).getCash().split(",");
    		list.get(i).setCash(cash[0]);
    	}*/
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
	public CommitResult updateReportFundPosition(DataFundPosition entity, HhUsers use,Integer i) {
		entity.setLastModifyDate(df.format(new Date()));
		entity.setLastModifyPersonId(use.getVcEmployeeId());
		entity.setLastModifyPersonName(use.getVcName());
		CommitResult result=new CommitResult();
		if(i==Base.examstatus_1){
			result=saveReportFundPositionCheck(entity);
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
	public CommitResult saveReportFundPosition(DataFundPosition entity,
			HhUsers use,String op) {
		CommitResult result=new CommitResult();
		
		if(op.equals("save")){
			result=saveReportFundPositionCheck(entity);
		}else{
			result=reportFundPositionCheck(entity);
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
		HhOrganInfoTreeRelation node = systemDao.getTreeOrganInfoNode(Base.financetype,entity.getOrg());
		entity.setParentorg(node.getVcOrganId());
		commonDao.saveOrUpdate(entity);
		if(op.equals("saveReport")||op.equals("report")){
			potalMsgService.savePortalMsg(new PortalMsg("资金头寸需要审核","资金头寸",df.format(new Date()),0,0,0,
					entity.getCreatePersonName(),entity.getCreateDate(),entity.getLastModifyPersonName(),
					entity.getLastModifyDate(),entity.getOrg(),systemService.getParentBynNodeID(entity.getOrg(),
							Base.financetype),"bimWork_zjtcsh","report_fund_position",entity.getId().toString()));
		}
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	public CommitResult deleteReportFundPosition(Integer id, HhUsers use) {
		DataFundPosition entity=reportFundPositionDao.get(id);
		CommitResult result=new CommitResult();
		if(entity!=null)
		{
			entity.setIsdel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			//commonDao.delete(entity);
			commonDao.saveOrUpdate(entity);
			//删除工作提醒
			potalMsgService.updatePortalMsg("report_fund_position", id.toString());
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
		DataFundPosition hr=reportFundPositionDao.get(entityID);
	/*	if(!hr.getStatus().equals(Base.examstatus_2))
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
	 */
	public CommitResult saveReportFundPositionExamine(Integer entityID,String examStr,Integer examResult,HhUsers use){
		
		DataFundPosition entity = get(entityID);
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
			potalMsgService.updatePortalMsg("report_fund_position", entityID.toString());
		}else{
			entity.setStatus(examResult);
		}
		//entity.setExamineTime(df.format(new Date()));
		//保存entity
		CommitResult hrresult= updateReportFundPosition(entity,use,examResult);
		if(!hrresult.isResult())
			return hrresult;
		
		DataFundPosition hc= (DataFundPosition) hrresult.getEntity();
		Integer examineentityid=hc.getId();
		examineService.saveExamine( examineentityid, Base.examkind_hr_reportFundPosition, use, examStr, examResult);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 保存Check
	 * @param entity
	 */
	private CommitResult saveReportFundPositionCheck(DataFundPosition entity)
	{
		CommitResult result=new CommitResult();
		if(!reportFundPositionDao.saveReportFundPositionCheck(entity))
		{
			if(entity.getOrgname()!=null&&!entity.getOrgname().equals("")){
				result=CommitResult.createErrorResult(entity.getOrgname()+entity.getDate()+"已经有数据，不能再添加");
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
	 */
	private CommitResult reportFundPositionCheck(DataFundPosition entity)
	{
		CommitResult result=new CommitResult();
		if(!reportFundPositionDao.reportFundPositionCheck(entity))
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
	public MsgPage findExaminePageList(DataFundPosition entity,
			Integer curPageNum, Integer pageSize) {
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportFundPositionDao.getExamineHrFormsListViewCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<DataFundPosition> list = reportFundPositionDao.getExamineHrFormsListView(entity, offset, pageSize);
    	//拆分显示
    	/*for(int i = 0;i<list.size();i++){
    		String [] cash = list.get(i).getCash().split(",");
    		list.get(i).setCash(cash[0]);
    	}*/
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);  
        return msgPage;
	}

	@Override
	public DataFundPosition getCash(String org) {
		return reportFundPositionDao.getCash(org);
	}
	
	/**
	 *子公司数据汇总
	 */
	@Override
	public MsgPage findAllchildernCompany(DataFundPosition entity, Integer curPageNum, Integer pageSize){	

		MsgPage msgPage = new MsgPage();       	
		
		HhOrganInfoTreeRelation node = null;
		//总记录数
        Integer totalRecords = new Integer(0);       
        //分页查询结果集
    	List<DataFundPosition> list = new ArrayList<DataFundPosition>();		
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	
		if(null!=entity)
			node = systemDao.getTreeOrganInfoNode(Base.financetype, entity.getOrg());	
		if(null!=node){
			//总记录数
			totalRecords = reportFundPositionDao.getAllchildernCompanyData(entity,node);
			//分页查询结果集
			list = reportFundPositionDao.findAllchildernCompany(entity, node,offset, pageSize);			
		}		

    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);  
        return msgPage;
	}
}
