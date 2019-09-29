package com.softline.service.purchase.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.softline.client.sso.AuthorityServiceService;
import com.softline.client.sso.IAuthorityService;
import com.softline.client.sso.SsoCheckResult;
import com.softline.common.Base;
import com.softline.dao.purchase.IPurchaseDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhUsers;
import com.softline.entity.PortalMsg;
import com.softline.entity.Purchase;
import com.softline.entity.ReportFormsGroup;
import com.softline.entity.SysExamine;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.PurchaseView;
import com.softline.service.portal.ILogService;
import com.softline.service.purchase.IPurchaseService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.IPortalMsgService;
import com.softline.service.system.ISsoService;
import com.softline.util.MsgPage;

@Service("purchaseService")
/**
 * 
 * @author tch
 *
 */
public class PurchaseService implements IPurchaseService{

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	@Autowired
	@Qualifier("purchaseDao")
	private IPurchaseDao purchaseDao;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	@Resource(name = "potalMsgService")
	private IPortalMsgService potalMsgService;	
	
	public MsgPage getPurchaseListView(Purchase entity, Integer curPageNum, Integer pageSize)
	{
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = purchaseDao.getPurchaseListCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<Purchase> list = purchaseDao.getPurchaseList(entity, offset, pageSize);
    	List<PurchaseView> View=new ArrayList<PurchaseView>();
    	for (Purchase purchase : list) {
    		PurchaseView item=new PurchaseView();
    		BigDecimal a=purchase.getEngineeringClass().add(purchase.getMaterialCategoryClass()).add(purchase.getChannelClass()).add(purchase.getServiceClass());
    		DecimalFormat df =  new   java.text.DecimalFormat("0.0000");  
    		item.setCumulativepurchaseamount(df.format(a));
    		Integer b=purchase.getConstructioClass()+purchase.getDesignClass()+purchase.getSupervisionClass()+purchase.getConsultingCostClass()+purchase.getMaterialEquipmentClass();
    		item.setSupportamount(b.toString());
    		item.setId(purchase.getId());
    		item.setSeason(purchase.getSeasonName());
    		item.setYear(purchase.getYear());
    		item.setStatusName(purchase.getStatusName());
    		item.setStatus(purchase.getStatus());
    		item.setRate(purchase.getPurchaseSaveRate().toString());
    		item.setAuditorDate(purchase.getAuditorDate());
    		item.setAuditorPersonId(purchase.getAuditorPersonId());
    		item.setAuditorPersonName(purchase.getAuditorPersonName());
    		item.setReportDate(purchase.getReportDate());
    		item.setReportPersonId(purchase.getAuditorPersonId());
    		item.setReportPersonName(purchase.getReportPersonName());
    		item.setCreatePersonName(purchase.getCreatePersonName());
    		View.add(item);
		}
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(View);    
        return msgPage;
	}
	
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public Purchase getPurchase(Integer id)
	{
		return purchaseDao.getPurchase( id);
	}
	
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	private CommitResult savePurchaseCheck(Purchase entity)
	{
		CommitResult result=new CommitResult();
		if(!purchaseDao.savePurchaseRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该季度已经有数据，不能再添加");
			return result;
		}
		if(!purchaseDao.checkCanEdit(entity))
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
	public CommitResult savePurchase(Purchase entity,HhUsers use)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=savePurchaseCheck(entity);
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
		if(entity.getStatus().equals(Base.examstatus_2))
		{
			
			potalMsgService.savePortalMsg(new PortalMsg(entity.getYear()+"年" + entity.getSeasonName()+"采购数据未审核","采购",df.format(new Date()),0,0,0,
					entity.getCreatePersonName(),entity.getCreateDate(),entity.getLastModifyPersonName(),
					entity.getLastModifyDate(),Base.HRTop,Base.HRTop,"bimWork_cgsjsh_exam","purchase",entity.getId().toString(),"/shanghai-gzw/purchase/examinelist?id="+entity.getId()));
		}
			
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
	public CommitResult deletePurchase(Integer id,HhUsers use)
	{
		
		Purchase entity=purchaseDao.getPurchase(id);
		if(!purchaseDao.checkCanEdit(entity))
		{
			CommitResult result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}
		CommitResult result=new CommitResult();
		if(entity!=null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setIsdel(1);
			entity.setLastModifyDate(df.format(new Date()));
			entity.setLastModifyPersonId(use.getVcEmployeeId());
			entity.setLastModifyPersonName(use.getVcName());
			commonDao.saveOrUpdate(entity);
			potalMsgService.updatePortalMsg("purchase", entity.getId().toString());
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
	public CommitResult savePurchaseExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		Purchase entity=purchaseDao.getPurchase(entityID);
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
			return result;
		}
		if(!entity.getStatus().equals(Base.examstatus_2))
		{
			result=CommitResult.createErrorResult("该信息已不用审核");
			return result;
		}
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
	public CommitResult savePurchaseExamine(Integer entityID,String examStr,Integer examResult,HhUsers use)
	{
		
		Purchase entity=getPurchase(entityID);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=new CommitResult();
		result=savePurchaseExamineCheck(entityID);
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
		entity.setExamineTime(df.format(new Date()));
		entity.setAuditorDate(df.format(new Date()));
		entity.setAuditorPersonId(use.getVcEmployeeId());
		entity.setAuditorPersonName(use.getVcName());
		//保存purchase
		commonDao.saveOrUpdate(entity);
		
		Integer examineentityid=entity.getId();

		examineService.saveExamine( examineentityid, Base.examkind_purchase, use, examStr, examResult);
		potalMsgService.updatePortalMsg("purchase", entity.getId().toString());
		result.setResult(true);
		return result;
	}
	
	
	
	
}
