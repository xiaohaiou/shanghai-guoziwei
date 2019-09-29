package com.softline.service.report;

import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.ReportCashflowMonthlyExecute;
import com.softline.entity.ReportCashflowWeeklyExecute;
import com.softline.entity.ReportMonthlyFinancingIntoDetail;
import com.softline.entity.ReportMonthlyFinancingOutDetail;
import com.softline.entity.ReportMonthlyInvestmentOutDetail;
import com.softline.entity.ReportWeeklyFinancingIntoDetail;
import com.softline.entity.ReportWeeklyFinancingOutDetail;
import com.softline.entity.ReportWeeklyInvestmentOutDetail;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
/**
 * 
 * @author tch
 *
 */
public interface IReportCashflowMonthlyExecuteService {

	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getReportCashflowMonthlyExecuteListView(ReportCashflowMonthlyExecute entity, Integer curPageNum, Integer pageSize);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportCashflowMonthlyExecute getReportCashflowMonthlyExecute(Integer id);
	
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportCashflowMonthlyExecute(ReportCashflowMonthlyExecute entity,HhUsers use);

	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportCashflowMonthlyExecute(Integer id,HhUsers use);
	
	/**
	 * 审核
	 * @param entityid  采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveReportCashflowMonthlyExecuteExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);

	public List<ReportMonthlyFinancingIntoDetail> getList1(Integer id);

	public List<ReportMonthlyFinancingOutDetail> getList2(Integer id);

	public List<ReportMonthlyInvestmentOutDetail> getList3(Integer id);

	public CommitResult saveReportCashflowMonthlyExecuteAndDetail(
			ReportCashflowMonthlyExecute entity, HhUsers use,
			List<ReportMonthlyFinancingIntoDetail> list1,
			List<ReportMonthlyFinancingOutDetail> list2,
			List<ReportMonthlyInvestmentOutDetail> list3);

	public List<ReportWeeklyFinancingIntoDetail> getWeeksList1(String compId,
			Integer year, Integer month);

	public List<ReportWeeklyFinancingOutDetail> getWeeksList2(String compId,
			Integer year, Integer month);

	public List<ReportWeeklyInvestmentOutDetail> getWeeksList3(String compId,
			Integer year, Integer month);
	
	/**
	 * 汇总数据--查询相关公司信息下一层级公司
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @return
	 */
	public MsgPage getReportCashflowMonthlyExecuteListForSumData(ReportCashflowMonthlyExecute entity ,Integer curPageNum, Integer pageSize,ReportCashflowMonthlyExecute backTempBean);

	public int sumForAllChildrenEntity(ReportCashflowMonthlyExecute entityIn,ReportCashflowMonthlyExecute entityOut);

	public List<ReportCashflowMonthlyExecute> getReportCashflowMonthlyExecuteListView(ReportCashflowMonthlyExecute entity);
}
