package com.softline.service.report.impl;

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
import com.softline.common.Common;
import com.softline.dao.report.IReportCashflowMonthlyExecuteDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.IFinanceHistroyTreeDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhOrganInfoTreeRelationHistory;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportCashflowMonthlyExecute;
import com.softline.entity.ReportMonthlyFinancingIntoDetail;
import com.softline.entity.ReportMonthlyFinancingOutDetail;
import com.softline.entity.ReportMonthlyInvestmentOutDetail;
import com.softline.entity.ReportWeeklyFinancingIntoDetail;
import com.softline.entity.ReportWeeklyFinancingOutDetail;
import com.softline.entity.ReportWeeklyInvestmentOutDetail;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportCashflowMonthlyExecuteService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.impl.CommonService;
import com.softline.util.MsgPage;

@Service("reportCashflowMonthlyExecuteService")
/**
 * 
 * @author tch
 *
 */
public class ReportCashflowMonthlyExecuteService implements IReportCashflowMonthlyExecuteService{

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	
	@Autowired
	@Qualifier("reportCashflowMonthlyExecuteDao")
	private IReportCashflowMonthlyExecuteDao reportCashflowMonthlyExecuteDao;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	@Resource(name = "financeHistroyTreeDao")
	private IFinanceHistroyTreeDao financeHistroyTreeDao;	
	
	
	
	public MsgPage getReportCashflowMonthlyExecuteListView(ReportCashflowMonthlyExecute entity, Integer curPageNum, Integer pageSize)
	{
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportCashflowMonthlyExecuteDao.getReportCashflowMonthlyExecuteListCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportCashflowMonthlyExecute> list = reportCashflowMonthlyExecuteDao.getReportCashflowMonthlyExecuteList(entity, offset, pageSize);
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
	public ReportCashflowMonthlyExecute getReportCashflowMonthlyExecute(Integer id)
	{
		return reportCashflowMonthlyExecuteDao.getReportCashflowMonthlyExecute( id);
	}
	
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	private CommitResult saveReportCashflowMonthlyExecuteCheck(ReportCashflowMonthlyExecute entity)
	{
		CommitResult result=new CommitResult();
		if(!reportCashflowMonthlyExecuteDao.saveReportCashflowMonthlyExecuteRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult(entity.getYear()+"年该期已经有数据，不能再添加");
			return result;
		}
		if(!reportCashflowMonthlyExecuteDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	/**
	 * list页面上报时，实体更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportCashflowMonthlyExecute(ReportCashflowMonthlyExecute entity,HhUsers use)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveReportCashflowMonthlyExecuteCheck(entity);
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
		result.setResult(true);
		result.setEntity(entity);
		return result;
	}
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportCashflowMonthlyExecuteAndDetail(ReportCashflowMonthlyExecute entity,HhUsers use,
			List<ReportMonthlyFinancingIntoDetail> list1, List<ReportMonthlyFinancingOutDetail> list2, List<ReportMonthlyInvestmentOutDetail> list3)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveReportCashflowMonthlyExecuteCheck(entity);
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
		
		//保存最新的筹资性流入明细列表信息
		if(!entity.getList1().isEmpty()) {
			for (ReportMonthlyFinancingIntoDetail detail : entity.getList1()) {
				detail.setIsdel(0);
				if(detail.getId() == null) {
					detail.setMonthId(entity.getId());
					detail.setCreateDate(df.format(new Date()));
					detail.setCreatePersonId(use.getVcEmployeeId());
					detail.setCreatePersonName(use.getVcName());
				}else {
					detail.setLastModifyDate(df.format(new Date()));
					detail.setLastModifyPersonId(use.getVcEmployeeId());
					detail.setLastModifyPersonName(use.getVcName());
					//从之前所保存的列表信息的list1中将这次仍然有的detail移除，剩下的list1中则是需要删除的
					if(!list1.isEmpty()) {
						for (int i = 0; i < list1.size(); i++) {
							if(list1.get(i).getId().equals(detail.getId())) {
								list1.remove(i);
								break;
							}
						}
						
					}
				}
				//增加或更新
				commonDao.saveOrUpdate(detail);
			}
		}
		//删除
		if(!list1.isEmpty()) {
			for (ReportMonthlyFinancingIntoDetail delDetail : list1) {
				delDetail.setLastModifyDate(df.format(new Date()));
				delDetail.setLastModifyPersonId(use.getVcEmployeeId());
				delDetail.setLastModifyPersonName(use.getVcName());
				delDetail.setIsdel(1);
				commonDao.saveOrUpdate(delDetail);
			}
		}
		
		//保存最新的筹资性流出明细列表信息
		if(!entity.getList2().isEmpty()) {
			for (ReportMonthlyFinancingOutDetail detail : entity.getList2()) {
				detail.setIsdel(0);
				if(detail.getId() == null) {
					detail.setMonthId(entity.getId());
					detail.setCreateDate(df.format(new Date()));
					detail.setCreatePersonId(use.getVcEmployeeId());
					detail.setCreatePersonName(use.getVcName());
				}else {
					detail.setLastModifyDate(df.format(new Date()));
					detail.setLastModifyPersonId(use.getVcEmployeeId());
					detail.setLastModifyPersonName(use.getVcName());
					//从之前所保存的列表信息的list2中将这次仍然有的detail移除，剩下的list2中则是需要删除的
					if(!list2.isEmpty()) {
						for (int i = 0; i < list2.size(); i++) {
							if(list2.get(i).getId().equals(detail.getId())) {
								list2.remove(i);
								break;
							}
						}
						
					}
				}
				//增加或更新
				commonDao.saveOrUpdate(detail);
			}
		}
		//删除
		if(!list2.isEmpty()) {
			for (ReportMonthlyFinancingOutDetail delDetail : list2) {
				delDetail.setLastModifyDate(df.format(new Date()));
				delDetail.setLastModifyPersonId(use.getVcEmployeeId());
				delDetail.setLastModifyPersonName(use.getVcName());
				delDetail.setIsdel(1);
				commonDao.saveOrUpdate(delDetail);
			}
		}
		
		//保存最新的筹资性流出明细列表信息
		if(!entity.getList3().isEmpty()) {
			for (ReportMonthlyInvestmentOutDetail detail : entity.getList3()) {
				detail.setIsdel(0);
				if(detail.getId() == null) {
					detail.setMonthId(entity.getId());
					detail.setCreateDate(df.format(new Date()));
					detail.setCreatePersonId(use.getVcEmployeeId());
					detail.setCreatePersonName(use.getVcName());
				}else {
					detail.setLastModifyDate(df.format(new Date()));
					detail.setLastModifyPersonId(use.getVcEmployeeId());
					detail.setLastModifyPersonName(use.getVcName());
					//从之前所保存的列表信息的list3中将这次仍然有的detail移除，剩下的list3中则是需要删除的
					if(!list3.isEmpty()) {
						for (int i = 0; i < list3.size(); i++) {
							if(list3.get(i).getId().equals(detail.getId())) {
								list3.remove(i);
								break;
							}
						}
						
					}
				}
				//增加或更新
				commonDao.saveOrUpdate(detail);
			}
		}
		//删除
		if(!list3.isEmpty()) {
			for (ReportMonthlyInvestmentOutDetail delDetail : list3) {
				delDetail.setLastModifyDate(df.format(new Date()));
				delDetail.setLastModifyPersonId(use.getVcEmployeeId());
				delDetail.setLastModifyPersonName(use.getVcName());
				delDetail.setIsdel(1);
				commonDao.saveOrUpdate(delDetail);
			}
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
	public CommitResult deleteReportCashflowMonthlyExecute(Integer id,HhUsers use)
	{
		
		ReportCashflowMonthlyExecute entity=reportCashflowMonthlyExecuteDao.getReportCashflowMonthlyExecute(id);
		CommitResult result=new CommitResult();
		if(!reportCashflowMonthlyExecuteDao.checkCanEdit(entity))
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
			//再删除此id对应的三个子详情信息（筹资性流入明细列表、筹资性流出明细列表、投资性流出明细列表）
			//删除筹资性流入明细列表
			commonDao.deleteChildDetail(id, "monthly", "report_monthly_financing_into_detail", df.format(new Date()), use.getVcEmployeeId(), use.getVcName());
			//删除筹资性流出明细列表
			commonDao.deleteChildDetail(id, "monthly", "report_monthly_financing_out_detail", df.format(new Date()), use.getVcEmployeeId(), use.getVcName());
			//删除投资性流出明细列表
			commonDao.deleteChildDetail(id, "monthly", "report_monthly_investment_out_detail", df.format(new Date()), use.getVcEmployeeId(), use.getVcName());
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
	public CommitResult saveReportCashflowMonthlyExecuteExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		ReportCashflowMonthlyExecute entity=reportCashflowMonthlyExecuteDao.getReportCashflowMonthlyExecute(entityID);
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
			return result;
		}
//		if(!entity.getStatus().equals(Base.examstatus_2))
//		{
//			result=CommitResult.createErrorResult("该信息已不用审核");
//			return result;
//		}
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
	public CommitResult saveReportCashflowMonthlyExecuteExamine(Integer entityID,String examStr,Integer examResult,HhUsers use)
	{
		
		ReportCashflowMonthlyExecute entity=getReportCashflowMonthlyExecute(entityID);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=new CommitResult();
		result=saveReportCashflowMonthlyExecuteExamineCheck(entityID);
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

		examineService.saveExamine( examineentityid, Base.examkind_cashflowmonthlyexecute, use, examStr, examResult);
		
		result.setResult(true);
		return result;
	}


	@Override
	public List<ReportMonthlyFinancingIntoDetail> getList1(Integer id) {
		// TODO Auto-generated method stub
		return reportCashflowMonthlyExecuteDao.getList1(id);
	}


	@Override
	public List<ReportMonthlyFinancingOutDetail> getList2(Integer id) {
		// TODO Auto-generated method stub
		return reportCashflowMonthlyExecuteDao.getList2(id);
	}


	@Override
	public List<ReportMonthlyInvestmentOutDetail> getList3(Integer id) {
		// TODO Auto-generated method stub
		return reportCashflowMonthlyExecuteDao.getList3(id);
	}


	@Override
	public List<ReportWeeklyFinancingIntoDetail> getWeeksList1(String compId,
			Integer year, Integer month) {
		// TODO Auto-generated method stub
		return reportCashflowMonthlyExecuteDao.getWeeksList1(compId, year, month);
	}


	@Override
	public List<ReportWeeklyFinancingOutDetail> getWeeksList2(String compId,
			Integer year, Integer month) {
		// TODO Auto-generated method stub
		return reportCashflowMonthlyExecuteDao.getWeeksList2(compId, year, month);
	}


	@Override
	public List<ReportWeeklyInvestmentOutDetail> getWeeksList3(String compId,
			Integer year, Integer month) {
		// TODO Auto-generated method stub
		return reportCashflowMonthlyExecuteDao.getWeeksList3(compId, year, month);
	}
	
	/**
	 * 汇总数据--查询相关公司信息下一层级公司
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @return
	 */
	public MsgPage getReportCashflowMonthlyExecuteListForSumData(ReportCashflowMonthlyExecute entity ,Integer curPageNum, Integer pageSize,ReportCashflowMonthlyExecute backTempBean){
		MsgPage msgPage = new MsgPage();   
		List<ReportCashflowMonthlyExecute> listtotal;
        //总记录数
		Integer totalRecords= reportCashflowMonthlyExecuteDao.getReportCashflowMonthlyExecuteListForSumDataTotal(entity);

		//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	
    	List<ReportCashflowMonthlyExecute> totalList = reportCashflowMonthlyExecuteDao.getReportCashflowMonthlyExecuteListForSumData(entity, 0, totalRecords);
    	//分页查询结果集
    	List<ReportCashflowMonthlyExecute> list = reportCashflowMonthlyExecuteDao.getReportCashflowMonthlyExecuteListForSumData(entity, offset, pageSize);
    	
    	if(totalRecords>0){	  		
    		double commercialInto = 0.0d;
    		double commercialOut = 0.0d;
    		double commercialNetInflow = 0.0d;
    		double investmentInto = 0.0d;
    		double investOut = 0.0d;
    		double investNetInflow = 0.0d;
    		double financingInto = 0.0d;
    		double financingOut = 0.0d;
    		double financingNetInflow = 0.0d;
    		double lendingOrContributionInto = 0.0d;
    		double lendingOrContributionOut = 0.0d;
    		DecimalFormat df = new java.text.DecimalFormat("0.000");
    		
    		for (ReportCashflowMonthlyExecute tempBean:totalList){
    			if(tempBean.getCommercialInto() !=null && 
    					CommonService.isStrParseToNum(tempBean.getCommercialInto()))
    				commercialInto+=Double.valueOf(tempBean.getCommercialInto());
    			if(tempBean.getCommercialOut() !=null && 
    					CommonService.isStrParseToNum(tempBean.getCommercialOut()))
    				commercialOut+=Double.valueOf(tempBean.getCommercialOut());
    			if(tempBean.getCommercialNetInflow() !=null && 
    					CommonService.isStrParseToNum(tempBean.getCommercialNetInflow()))
    				commercialNetInflow+=Double.valueOf(tempBean.getCommercialNetInflow());
    			if(tempBean.getInvestmentInto() !=null && 
    					CommonService.isStrParseToNum(tempBean.getInvestmentInto()))
    				investmentInto+=Double.valueOf(tempBean.getInvestmentInto());
    			if(tempBean.getInvestOut() !=null && 
    					CommonService.isStrParseToNum(tempBean.getInvestOut()))
    				investOut+=Double.valueOf(tempBean.getInvestOut());
    			if(tempBean.getInvestNetInflow() !=null && 
    					CommonService.isStrParseToNum(tempBean.getInvestNetInflow()))
    				investNetInflow+=Double.valueOf(tempBean.getInvestNetInflow());
    			if(tempBean.getFinancingInto() !=null && 
    					CommonService.isStrParseToNum(tempBean.getFinancingInto()))
    				financingInto+=Double.valueOf(tempBean.getFinancingInto());
    			if(tempBean.getFinancingOut() !=null && 
    					CommonService.isStrParseToNum(tempBean.getFinancingOut()))
    				financingOut+=Double.valueOf(tempBean.getFinancingOut());
    			if(tempBean.getFinancingNetInflow() !=null && 
    					CommonService.isStrParseToNum(tempBean.getFinancingNetInflow()))
    				financingNetInflow+=Double.valueOf(tempBean.getFinancingNetInflow());
    			if(tempBean.getLendingOrContributionInto() !=null && 
    					CommonService.isStrParseToNum(tempBean.getLendingOrContributionInto()))
    				lendingOrContributionInto+=Double.valueOf(tempBean.getLendingOrContributionInto());
    			if(tempBean.getLendingOrContributionOut() !=null && 
    					CommonService.isStrParseToNum(tempBean.getLendingOrContributionOut()))
    				lendingOrContributionOut+=Double.valueOf(tempBean.getLendingOrContributionOut());
    		}   		
    		backTempBean.setCommercialInto(df.format(commercialInto));
    		backTempBean.setCommercialOut(df.format(commercialOut));
    		backTempBean.setCommercialNetInflow(df.format(commercialNetInflow));
    		backTempBean.setInvestmentInto(df.format(investmentInto));
    		backTempBean.setInvestOut(df.format(investOut));
    		backTempBean.setInvestNetInflow(df.format(investNetInflow));
    		backTempBean.setFinancingInto(df.format(financingInto));
    		backTempBean.setFinancingOut(df.format(financingOut));
    		backTempBean.setFinancingNetInflow(df.format(financingNetInflow));
    		backTempBean.setLendingOrContributionInto(df.format(lendingOrContributionInto));
    		backTempBean.setLendingOrContributionOut(df.format(lendingOrContributionOut));	
    	}
    	  	
    	msgPage.setPageNum(curPageNum);
    	msgPage.setPageSize(pageSize);
    	msgPage.setTotalRecords(totalRecords);
    	msgPage.setTotalPage(msgPage.countTotalPage(totalRecords, pageSize));
    	msgPage.setList(list);    
    	
    	if(null!=totalList)
    		totalList.clear();
		
        return msgPage;

	}
		
	/**
	 * 新增虚拟公司时，汇总查询
	 * @param entityIn
	 * @param entityOut
	 * @return
	 */
	@Override
	public int sumForAllChildrenEntity(ReportCashflowMonthlyExecute entityIn,ReportCashflowMonthlyExecute entityOut){
		
		if(null ==entityIn || 
				null==entityIn.getOrg() || 
				"".equals(entityIn.getOrg()))
			return -1;
		
		String date = null;
		if(null == entityIn.getYear() || null == entityIn.getMonth())
			return -1;
		
		date =  String.valueOf(entityIn.getYear())+"-"+(String.valueOf(entityIn.getMonth()).length()==2?
														String.valueOf(entityIn.getMonth()):
															"0"+String.valueOf(entityIn.getMonth()));

		HhOrganInfoTreeRelationHistory info = financeHistroyTreeDao.getTreeOrganInfoNode(Base.financetype,
																						 entityIn.getOrg(),
																						 date);	
		List<ReportCashflowMonthlyExecute> entityOut_tempList = null;
		if(null != info)
			entityOut_tempList = reportCashflowMonthlyExecuteDao.getSumChildrenData(info, entityIn);
		
		return this.getSumBean(entityOut_tempList,entityOut);
	}
	
	
	public int getSumBean(List<ReportCashflowMonthlyExecute> list,ReportCashflowMonthlyExecute outBean){
		
		if(null ==list )
			return -1;
		
		double commercialInto = 0.0d;
		double commercialOut = 0.0d;
		double commercialProject = 0.0d;			
		double commercialNetInflow = 0.0d;			
		double financingInto = 0.0d;			
		double financingOut = 0.0d;			
		double financingNetInflow = 0.0d;
		double investmentInto = 0.0d;			
		double constructionInProcess = 0.0d;			
		double airplaneInvest = 0.0d;			
		double stockEquityInvest = 0.0d;			
		double landReserve = 0.0d;
		double fixedAssetsPurchase = 0.0d;			
		double financeInvest = 0.0d;
		double elseInvestExpense = 0.0d;			
		double investOut = 0.0d;
		double investNetInflow = 0.0d;			
		double lendingOrContributionInto = 0.0d;
		double lendingOrContributionOut = 0.0d;			
		double lendingOrContributionNetInflow = 0.0d;
		double cashInto = 0.0d;			
		double cashOut = 0.0d;
		double cashNetInflow = 0.0d;
		double returnCapitalAmounts = 0.0d;
		double returnInterestAmounts= 0.0d;
		double returnNoteAmounts = 0.0d;
		double returnRentAmounts =0.0d;
		
		List<ReportMonthlyFinancingIntoDetail> list1 = new ArrayList<ReportMonthlyFinancingIntoDetail>();
		List<ReportMonthlyFinancingOutDetail> list2 = new ArrayList<ReportMonthlyFinancingOutDetail>();
		List<ReportMonthlyInvestmentOutDetail> list3 = new ArrayList<ReportMonthlyInvestmentOutDetail>();
		
		for(ReportCashflowMonthlyExecute tempBean:list){
			
			int backInfo = this.getReportCashflowWeeklyExecuteAllInfo(tempBean);
			
			if(backInfo == 0){
				if(Common.notEmpty(tempBean.getCommercialInto()))
					commercialInto += Double.valueOf(tempBean.getCommercialInto());
				if(Common.notEmpty(tempBean.getCommercialOut()))
					commercialOut += Double.valueOf(tempBean.getCommercialOut());
				if(Common.notEmpty(tempBean.getCommercialProject()))
					commercialProject += Double.valueOf(tempBean.getCommercialProject());			
				if(Common.notEmpty(tempBean.getCommercialNetInflow()))
					commercialNetInflow += Double.valueOf(tempBean.getCommercialNetInflow());			
				if(Common.notEmpty(tempBean.getFinancingInto()))	
					financingInto += Double.valueOf(tempBean.getFinancingInto());			
				if(Common.notEmpty(tempBean.getFinancingOut()))
					financingOut += Double.valueOf(tempBean.getFinancingOut());			
				if(Common.notEmpty(tempBean.getFinancingNetInflow()))
					financingNetInflow += Double.valueOf(tempBean.getFinancingNetInflow());
				if(Common.notEmpty(tempBean.getInvestmentInto()))
					investmentInto += Double.valueOf(tempBean.getInvestmentInto());			
				if(Common.notEmpty(tempBean.getConstructionInProcess()))
					constructionInProcess += Double.valueOf(tempBean.getConstructionInProcess());			
				if(Common.notEmpty(tempBean.getAirplaneInvest()))
					airplaneInvest += Double.valueOf(tempBean.getAirplaneInvest());			
				if(Common.notEmpty(tempBean.getStockEquityInvest()))
					stockEquityInvest += Double.valueOf(tempBean.getStockEquityInvest());			
				if(Common.notEmpty(tempBean.getLandReserve()))	
					landReserve += Double.valueOf(tempBean.getLandReserve());
				if(Common.notEmpty(tempBean.getFixedAssetsPurchase()))	
					fixedAssetsPurchase += Double.valueOf(tempBean.getFixedAssetsPurchase());			
				if(Common.notEmpty(tempBean.getFinanceInvest()))
					financeInvest += Double.valueOf(tempBean.getFinanceInvest());
				if(Common.notEmpty(tempBean.getElseInvestExpense()))
					elseInvestExpense += Double.valueOf(tempBean.getElseInvestExpense());			
				if(Common.notEmpty(tempBean.getInvestOut()))
					investOut += Double.valueOf(tempBean.getInvestOut());
				if(Common.notEmpty(tempBean.getInvestNetInflow()))
					investNetInflow += Double.valueOf(tempBean.getInvestNetInflow());			
				if(Common.notEmpty(tempBean.getLendingOrContributionInto()))
					lendingOrContributionInto += Double.valueOf(tempBean.getLendingOrContributionInto());
				if(Common.notEmpty(tempBean.getLendingOrContributionOut()))
					lendingOrContributionOut += Double.valueOf(tempBean.getLendingOrContributionOut());			
				if(Common.notEmpty(tempBean.getLendingOrContributionNetInflow()))
					lendingOrContributionNetInflow += Double.valueOf(tempBean.getLendingOrContributionNetInflow());
				if(Common.notEmpty(tempBean.getCashInto()))
					cashInto += Double.valueOf(tempBean.getCashInto());			
				if(Common.notEmpty(tempBean.getCashOut()))
					cashOut += Double.valueOf(tempBean.getCashOut());
				if(Common.notEmpty(tempBean.getCashNetInflow()))	
					cashNetInflow += Double.valueOf(tempBean.getCashNetInflow());		
				if(Common.notEmpty(tempBean.getReturnCapitalAmounts()))	
					returnCapitalAmounts += Double.valueOf(tempBean.getReturnCapitalAmounts());				
				if(Common.notEmpty(tempBean.getReturnInterestAmounts()))	
					returnInterestAmounts += Double.valueOf(tempBean.getReturnInterestAmounts());			
				if(Common.notEmpty(tempBean.getReturnNoteAmounts()))	
					returnNoteAmounts += Double.valueOf(tempBean.getReturnNoteAmounts());
				if(Common.notEmpty(tempBean.getReturnRentAmounts()))	
					returnRentAmounts += Double.valueOf(tempBean.getReturnRentAmounts());				
				list1.addAll(tempBean.getList1());
				list2.addAll(tempBean.getList2());
				list3.addAll(tempBean.getList3());	
			}	
		}
		
		outBean.setCommercialInto(String.valueOf(commercialInto));
		outBean.setCommercialOut(String.valueOf(commercialOut));
		outBean.setCommercialProject(String.valueOf(commercialProject));			
		outBean.setCommercialNetInflow(String.valueOf(commercialNetInflow));			
		outBean.setFinancingInto(String.valueOf(financingInto));			
		outBean.setFinancingOut(String.valueOf(financingOut));			
		outBean.setFinancingNetInflow(String.valueOf(financingNetInflow));
		outBean.setInvestmentInto(String.valueOf(investmentInto));		
		outBean.setConstructionInProcess(String.valueOf(constructionInProcess));			
		outBean.setAirplaneInvest(String.valueOf(airplaneInvest));			
		outBean.setStockEquityInvest(String.valueOf(stockEquityInvest));			
		outBean.setLandReserve(String.valueOf(landReserve));
		outBean.setFixedAssetsPurchase(String.valueOf(fixedAssetsPurchase));			
		outBean.setFinanceInvest(String.valueOf(financeInvest));
		outBean.setElseInvestExpense(String.valueOf(elseInvestExpense));			
		outBean.setInvestOut(String.valueOf(investOut));
		outBean.setInvestNetInflow(String.valueOf(investNetInflow));			
		outBean.setLendingOrContributionInto(String.valueOf(lendingOrContributionInto));
		outBean.setLendingOrContributionOut(String.valueOf(lendingOrContributionOut));			
		outBean.setLendingOrContributionNetInflow(String.valueOf(lendingOrContributionNetInflow));
		outBean.setCashInto(String.valueOf(cashInto));			
		outBean.setCashOut(String.valueOf(cashOut));
		outBean.setCashNetInflow(String.valueOf(cashNetInflow));
		outBean.setReturnCapitalAmounts(String.valueOf(returnCapitalAmounts));
		outBean.setReturnInterestAmounts(String.valueOf(returnInterestAmounts));
		outBean.setReturnNoteAmounts(String.valueOf(returnNoteAmounts));
		outBean.setReturnRentAmounts(String.valueOf(returnRentAmounts));

		outBean.setList1(list1);
		outBean.setList2(list2);
		outBean.setList3(list3);
		
		// 清空汇总结果集
		if(null != list)
			list.clear();
		
		return 0;
	}
	
	/**
	 * 获取所有的信息
	 * @param entity
	 * @return
	 */
	public int getReportCashflowWeeklyExecuteAllInfo(ReportCashflowMonthlyExecute entity){
	
		if(null == entity)
			return -1;
	
		List<ReportMonthlyFinancingIntoDetail> list1 = this.getList1(entity.getId());
		List<ReportMonthlyFinancingOutDetail> list2 = this.getList2(entity.getId());
		List<ReportMonthlyInvestmentOutDetail> list3 = this.getList3(entity.getId());
		isBigDetialInfoData(list1,list2,list3);
		entity.setList1(list1);
		entity.setList2(list2);
		entity.setList3(list3);
		
		return 0;
	}
	
	/**
	 * 月情况，去除大于3亿的数据
	 */
	public int isBigDetialInfoData(List<ReportMonthlyFinancingIntoDetail> list1,
											List<ReportMonthlyFinancingOutDetail> list2,
												List<ReportMonthlyInvestmentOutDetail> list3){
		int temp = 0;
		
		if(null == list1 || list1.size()==0)
			temp=-1;
		else{		
			for(int i=list1.size()-1;i>=0;i--){
				ReportMonthlyFinancingIntoDetail tenpBean1=list1.get(i);
				if(tenpBean1.getId() != null){
					tenpBean1.setId(null);
					list1.set(i, tenpBean1);
				}
				if(Double.valueOf(tenpBean1.getLoanAmount())<1000)
					list1.remove(tenpBean1);
			}
		}
				
		if(null == list2 || list2.size()==0)
			temp=-1;
		else{
			for(int i=list2.size()-1;i>=0;i--){
				ReportMonthlyFinancingOutDetail tempBean2=list2.get(i);
				if(tempBean2.getId() != null){
					tempBean2.setId(null);
					list2.set(i, tempBean2);
				}
				if(Double.valueOf(tempBean2.getRepayAmount())<1000)
					list2.remove(tempBean2);
			}
		}	
		
		if(null == list3 || list3.size()==0)
			temp=-1;
		else{
			for(int i=list3.size()-1;i>=0;i--){
				ReportMonthlyInvestmentOutDetail tempBean3=list3.get(i);
				if(tempBean3.getId() != null){
					tempBean3.setId(null);
					list3.set(i, tempBean3);
				}
				if(Double.valueOf(tempBean3.getPayAmount())<1000)
					list3.remove(tempBean3);
			}
		}
		return temp;
	}


	@Override
	public List<ReportCashflowMonthlyExecute> getReportCashflowMonthlyExecuteListView(
			ReportCashflowMonthlyExecute entity) {
		// TODO Auto-generated method stub
		return reportCashflowMonthlyExecuteDao.getReportCashflowMonthlyExecuteListView(entity);
	}
	
}
