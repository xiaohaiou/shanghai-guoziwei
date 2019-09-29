package com.softline.service.report;

import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.ReportFinancingAccount;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
/**
 * 
 * @author tch
 *
 */
public interface IReportFinancingAccountService {

	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getReportFinancingAccountListView(ReportFinancingAccount entity, Integer curPageNum, Integer pageSize);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportFinancingAccount getReportFinancingAccount(Integer id);
	
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingAccount(ReportFinancingAccount entity,HhUsers use);

	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportFinancingAccount(Integer id,HhUsers use);
	
	/**
	 * 审核
	 * @param entityid  采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingAccountExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);


	public List<ReportFinancingAccount> getSummaryData(String compId,Integer year,Integer month);

	public List<ReportFinancingAccount> getSummaryHistoryData(String compId,Integer year, Integer month);
	
	/**
	 * 查询汇总公司下面所有子公司的数据		
	 * @param entity
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	public MsgPage getReportFinancingAccountSumDetialList(ReportFinancingAccount entity, Integer curPageNum, Integer pageSize); 
	
}
