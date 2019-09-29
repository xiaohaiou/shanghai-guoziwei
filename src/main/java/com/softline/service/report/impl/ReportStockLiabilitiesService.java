package com.softline.service.report.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softline.common.Base;
import com.softline.dao.report.IReportStockLiabilitiesDao;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.IFinanceHistroyTreeDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhOrganInfoTreeRelationHistory;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportOverseasLiabilitiesDetail;
import com.softline.entity.ReportStockLiabilities;
import com.softline.entityTemp.CommitResult;
import com.softline.service.report.IReportStockLiabilitiesService;
import com.softline.service.system.IExamineService;
import com.softline.service.system.impl.CommonService;
import com.softline.util.MsgPage;

@Service("reportStockLiabilitiesService")
/**
 * 
 * @author tch
 *
 */
public class ReportStockLiabilitiesService implements IReportStockLiabilitiesService{

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("systemDao")
	private ISystemDao systemDao;
	
	@Autowired
	@Qualifier("reportStockLiabilitiesDao")
	private IReportStockLiabilitiesDao reportStockLiabilitiesDao;
	
	@Resource(name = "examineService")
	private IExamineService examineService;	
	
	@Resource(name = "financeHistroyTreeDao")
	private IFinanceHistroyTreeDao financeHistroyTreeDao;	
	
	public MsgPage getReportStockLiabilitiesListView(ReportStockLiabilities entity, Integer curPageNum, Integer pageSize)
	{
		MsgPage msgPage = new MsgPage();       
        //总记录数
        Integer totalRecords = reportStockLiabilitiesDao.getReportStockLiabilitiesListCount(entity);
    	//当前页开始记录
    	int offset = msgPage.countOffset(curPageNum,pageSize);  
    	//分页查询结果集
    	List<ReportStockLiabilities> list = reportStockLiabilitiesDao.getReportStockLiabilitiesList(entity, offset, pageSize);
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
	public ReportStockLiabilities getReportStockLiabilities(Integer id)
	{
		return reportStockLiabilitiesDao.getReportStockLiabilities( id);
	}
	
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	private CommitResult saveReportStockLiabilitiesCheck(ReportStockLiabilities entity)
	{
		CommitResult result=new CommitResult();
		if(!reportStockLiabilitiesDao.saveReportStockLiabilitiesRepeatCheck(entity))
		{
			result=CommitResult.createErrorResult("此公司"+entity.getYear()+"年"+entity.getMonth()+"月已经有数据，不能再添加");
			return result;
		}
		if(!reportStockLiabilitiesDao.checkCanEdit(entity))
		{
			result=CommitResult.createErrorResult("该数据已经被上报不能修改");
			return result;
		}
		result.setResult(true);
		return result;
	}
	
	/**
	 * 保存更新（含有列表）
	 */
	@Override
	public CommitResult saveReportStockLiabilitiesAndDetail(
			ReportStockLiabilities entity, HhUsers use,
			List<ReportOverseasLiabilitiesDetail> list1) {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveReportStockLiabilitiesCheck(entity);
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
		//保存最新的境外负债按币种分类明细表信息
		if(!entity.getList1().isEmpty()) {
			for (ReportOverseasLiabilitiesDetail detail : entity.getList1()) {
				detail.setIsdel(0);
				if(detail.getId() == null) {
					detail.setStockLiabilitiesId(entity.getId());
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
			for (ReportOverseasLiabilitiesDetail delDetail : list1) {
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
	 * 保存更新（list页面上报）
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportStockLiabilities(ReportStockLiabilities entity,HhUsers use)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=saveReportStockLiabilitiesCheck(entity);
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
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportStockLiabilities(Integer id,HhUsers use)
	{
		
		ReportStockLiabilities entity=reportStockLiabilitiesDao.getReportStockLiabilities(id);
		CommitResult result=new CommitResult();
		if(!reportStockLiabilitiesDao.checkCanEdit(entity))
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
			//删除境外负债按币种分类明细列表
			commonDao.deleteChildDetail(id, "stockLiabilities", "report_overseas_liabilities_detail", df.format(new Date()), use.getVcEmployeeId(), use.getVcName());
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
	public CommitResult saveReportStockLiabilitiesExamineCheck(Integer entityID)
	{
		CommitResult result=new CommitResult();
		ReportStockLiabilities entity=reportStockLiabilitiesDao.getReportStockLiabilities(entityID);
		if(entity==null)
		{
			result=CommitResult.createErrorResult("该数据已被删除");
			return result;
		}
		/*if(!entity.getStatus().equals(Base.examstatus_2))
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
	public CommitResult saveReportStockLiabilitiesExamine(Integer entityID,String examStr,Integer examResult,HhUsers use)
	{
		
		ReportStockLiabilities entity=getReportStockLiabilities(entityID);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CommitResult result=new CommitResult();
		result=saveReportStockLiabilitiesExamineCheck(entityID);
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

		examineService.saveExamine( examineentityid, Base.examkind_stockliabilities, use, examStr, examResult);
		
		result.setResult(true);
		return result;
	}


	@Override
	public List<ReportOverseasLiabilitiesDetail> getList1(Integer id) {
		// TODO Auto-generated method stub
		return reportStockLiabilitiesDao.getList1(id);
	}

	/**
	 * 汇总查询负载数据
	 */
	@Override
	public List<ReportStockLiabilities> getSumBeanList(ReportStockLiabilities entity,Integer year,Integer month,String status){
		//String allCompanyName = systemDao.getAllChildrenFinanceOrganal(entity.getOrg(),Base.financetype);	
		if(null == year ||
				null == month)
			return new ArrayList<ReportStockLiabilities>();
		
		StringBuilder allCompanyNameSB = new StringBuilder();
		String updateTime_temp = String.valueOf(year)+"-"+(String.valueOf(month).length()==2?
															(String.valueOf(month)):
																("0"+String.valueOf(month))); // 历史树查找日期

		HhOrganInfoTreeRelationHistory hhOrganInfoTreeRelationHistory = financeHistroyTreeDao.getTreeOrganInfoNode(Base.financetype,
																												   entity.getOrg(),
																												   updateTime_temp);
		if(null == hhOrganInfoTreeRelationHistory)
			return new ArrayList<ReportStockLiabilities>();
		
		//获取历史信息
		List<HhOrganInfoTreeRelationHistory> listCompanyInfo = 
				financeHistroyTreeDao.getChildrenAllTreeOrganInfos(Base.financetype,
																hhOrganInfoTreeRelationHistory.getVcOrganId(),
															    updateTime_temp);
		
		if(null == listCompanyInfo || listCompanyInfo.size()==0)
			return new ArrayList<ReportStockLiabilities>();
		
		for(HhOrganInfoTreeRelationHistory tempBean:listCompanyInfo){
			allCompanyNameSB.append(tempBean.getId().getNnodeId()).append(",");
		}
		// 去除最后都逗号
		if(allCompanyNameSB.length()>0)
			allCompanyNameSB.setLength(allCompanyNameSB.length()-1);

		return reportStockLiabilitiesDao.getSumBeanList(allCompanyNameSB.toString(),year,month,status);
	}
	
	public int getSumResult(List<ReportStockLiabilities> reportStockLiabilitiesList,Hashtable<String,Object> backMap){

		if(null == reportStockLiabilitiesList || reportStockLiabilitiesList.size() ==0){
			backMap.put("bean",new ReportStockLiabilities());			
			backMap.put("list",new ArrayList<ReportOverseasLiabilitiesDetail>());			
			return -1;
		}
		
		 List<ReportOverseasLiabilitiesDetail> detailList;
		 ReportStockLiabilities reportStockLiabilities;
					
		 double domesticStockLiabilities = 0.0d;
		 double	domesticRmbLiabilities = 0.0d;
		 double	proportionRmbLiabilities = 0.0d;
		 double overseasStockLiabilities = 0.0d;
		 double	totalStockLiabilities = 0.0d;
		 double	proportionForeignLiabilities = 0.0d;
		 double	bankLoanStockLiabilities = 0.0d;
		 double	entrustStockLiabilities = 0.0d;
		 double	billStockLiabilities = 0.0d;
		 double	fundStockLiabilities = 0.0d;
		 double	bondStockLiabilities = 0.0d;
		 double	financingRentStockLiabilities = 0.0d;
		 double	othersStockLiabilities = 0.0d;
		 double	costStockLiabilities = 0.0d;
		 double	longTermLiabilities = 0.0d;
		 double	proportionLongTermLiabilities = 0.0d;
		 StringBuilder id_SB = new StringBuilder();
		 int i = 0;
		
	 
		for(ReportStockLiabilities tempBean:reportStockLiabilitiesList){
			if(tempBean.getDomesticStockLiabilities()!=null && 
					CommonService.isStrParseToNum(tempBean.getDomesticStockLiabilities()))
				domesticStockLiabilities+=Double.valueOf(tempBean.getDomesticStockLiabilities());
			
			if(tempBean.getDomesticRmbLiabilities()!=null && 
					CommonService.isStrParseToNum(tempBean.getDomesticRmbLiabilities()))
				domesticRmbLiabilities+=Double.valueOf(tempBean.getDomesticRmbLiabilities());				
				
//			if(tempBean.getProportionRmbLiabilities()!=null && 
//					CommonService.isStrParseToNum(tempBean.getProportionRmbLiabilities()))
//				proportionRmbLiabilities+=Double.valueOf(tempBean.getProportionRmbLiabilities());				
			
			if(tempBean.getOverseasStockLiabilities()!=null && 
					CommonService.isStrParseToNum(tempBean.getOverseasStockLiabilities()))
				overseasStockLiabilities+=Double.valueOf(tempBean.getOverseasStockLiabilities());			
			
			if(tempBean.getTotalStockLiabilities()!=null && 
					CommonService.isStrParseToNum(tempBean.getTotalStockLiabilities()))
				totalStockLiabilities+=Double.valueOf(tempBean.getTotalStockLiabilities());				
			
//			if(tempBean.getProportionForeignLiabilities()!=null && 
//					CommonService.isStrParseToNum(tempBean.getProportionForeignLiabilities()))
//				proportionForeignLiabilities+=Double.valueOf(tempBean.getProportionForeignLiabilities());				
			
			if(tempBean.getBankLoanStockLiabilities()!=null && 
					CommonService.isStrParseToNum(tempBean.getBankLoanStockLiabilities()))
				bankLoanStockLiabilities+=Double.valueOf(tempBean.getBankLoanStockLiabilities());				

			if(tempBean.getEntrustStockLiabilities()!=null && 
					CommonService.isStrParseToNum(tempBean.getEntrustStockLiabilities()))
				entrustStockLiabilities+=Double.valueOf(tempBean.getEntrustStockLiabilities());				
			
			if(tempBean.getBillStockLiabilities()!=null && 
					CommonService.isStrParseToNum(tempBean.getBillStockLiabilities()))
				billStockLiabilities+=Double.valueOf(tempBean.getBillStockLiabilities());	
			
			if(tempBean.getFundStockLiabilities()!=null && 
					CommonService.isStrParseToNum(tempBean.getFundStockLiabilities()))
				fundStockLiabilities+=Double.valueOf(tempBean.getFundStockLiabilities());				
			
			if(tempBean.getBondStockLiabilities()!=null && 
					CommonService.isStrParseToNum(tempBean.getBondStockLiabilities()))
				bondStockLiabilities+=Double.valueOf(tempBean.getBondStockLiabilities());				
			
			if(tempBean.getFinancingRentStockLiabilities()!=null && 
					CommonService.isStrParseToNum(tempBean.getFinancingRentStockLiabilities()))
				financingRentStockLiabilities+=Double.valueOf(tempBean.getFinancingRentStockLiabilities());				
			
			if(tempBean.getOthersStockLiabilities()!=null && 
					CommonService.isStrParseToNum(tempBean.getOthersStockLiabilities()))
				othersStockLiabilities+=Double.valueOf(tempBean.getOthersStockLiabilities());				
			
			if(tempBean.getCostStockLiabilities()!=null && 
					CommonService.isStrParseToNum(tempBean.getCostStockLiabilities()))
				costStockLiabilities+=Double.valueOf(tempBean.getCostStockLiabilities());				
	
			if(tempBean.getLongTermLiabilities()!=null && 
					CommonService.isStrParseToNum(tempBean.getLongTermLiabilities()))
				longTermLiabilities+=Double.valueOf(tempBean.getLongTermLiabilities());		

//			if(tempBean.getProportionLongTermLiabilities()!=null && 
//					CommonService.isStrParseToNum(tempBean.getProportionLongTermLiabilities()))
//				proportionLongTermLiabilities+=Double.valueOf(tempBean.getProportionLongTermLiabilities());			

			id_SB.append(tempBean.getId()).append(",");
			i++;
		}
	
		reportStockLiabilities = new ReportStockLiabilities();
		reportStockLiabilities.setDomesticStockLiabilities(String.valueOf(domesticStockLiabilities));
		reportStockLiabilities.setDomesticRmbLiabilities(String.valueOf(domesticRmbLiabilities));
//		if(domesticStockLiabilities!=0.0d)
//			reportStockLiabilities.setProportionRmbLiabilities(String.valueOf(domesticRmbLiabilities/domesticStockLiabilities));
		reportStockLiabilities.setOverseasStockLiabilities(String.valueOf(overseasStockLiabilities));
		reportStockLiabilities.setTotalStockLiabilities(String.valueOf(totalStockLiabilities));
//		if(totalStockLiabilities!=0.0d)
//			reportStockLiabilities.setProportionForeignLiabilities(String.valueOf(overseasStockLiabilities/totalStockLiabilities));
		reportStockLiabilities.setBankLoanStockLiabilities(String.valueOf(bankLoanStockLiabilities));
		reportStockLiabilities.setEntrustStockLiabilities(String.valueOf(entrustStockLiabilities));
		reportStockLiabilities.setBillStockLiabilities(String.valueOf(billStockLiabilities));
		reportStockLiabilities.setFundStockLiabilities(String.valueOf(fundStockLiabilities));
		reportStockLiabilities.setBondStockLiabilities(String.valueOf(bondStockLiabilities));
		reportStockLiabilities.setFinancingRentStockLiabilities(String.valueOf(financingRentStockLiabilities));
		reportStockLiabilities.setOthersStockLiabilities(String.valueOf(othersStockLiabilities));
		reportStockLiabilities.setCostStockLiabilities(String.valueOf(costStockLiabilities));
		reportStockLiabilities.setLongTermLiabilities(String.valueOf(longTermLiabilities));
//		if(i!=0)
//			reportStockLiabilities.setProportionLongTermLiabilities(String.valueOf(proportionLongTermLiabilities/i));
		backMap.put("bean", reportStockLiabilities);
		
		//按幣種類別分类汇总，并设置到新的实体类中		
		if(null == id_SB.toString())
			detailList = new ArrayList<ReportOverseasLiabilitiesDetail>();
		
		if (id_SB.length() > 0)
			id_SB.setLength(id_SB.length()-1);
		
		detailList = reportStockLiabilitiesDao.getList1(id_SB.toString());
		backMap.put("list", detailList);
		
		return 0;		
	}
}
