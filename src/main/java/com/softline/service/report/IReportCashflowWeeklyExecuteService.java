package com.softline.service.report;

import java.util.List;

import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhUsers;
import com.softline.entity.ReportCashflowMonthlyExecute;
import com.softline.entity.ReportCashflowWeeklyExecute;
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
public interface IReportCashflowWeeklyExecuteService {

	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getReportCashflowWeeklyExecuteListView(ReportCashflowWeeklyExecute entity, Integer curPageNum, Integer pageSize);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportCashflowWeeklyExecute getReportCashflowWeeklyExecute(Integer id);
	
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportCashflowWeeklyExecuteAndDetail(ReportCashflowWeeklyExecute entity,HhUsers use,
			List<ReportWeeklyFinancingIntoDetail> list1, List<ReportWeeklyFinancingOutDetail> list2, List<ReportWeeklyInvestmentOutDetail> list3);

	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportCashflowWeeklyExecute(Integer id,HhUsers use);
	
	/**
	 * 审核
	 * @param entityid  采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveReportCashflowWeeklyExecuteExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);
	
	/**
	 * 查询筹资性流入明细实体
	 * @param id
	 * @return
	 */
	public ReportWeeklyFinancingIntoDetail getReportFinancingIntoDetail(Integer id);

	public List<ReportWeeklyFinancingIntoDetail> getList1(Integer id);

	public CommitResult saveReportCashflowWeeklyExecute(
			ReportCashflowWeeklyExecute entity, HhUsers use);

	public List<ReportWeeklyFinancingOutDetail> getList2(Integer id);

	public List<ReportWeeklyInvestmentOutDetail> getList3(Integer id);

	public HhOrganInfoTreeRelation getCoreComp(String organalID);

	public HhOrganInfoTreeRelation getCompanyRelation(String organalID);
	/**
	 * 汇总数据--查询相关公司信息下一层级公司
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @return
	 */
	public MsgPage getReportCashflowWeeklyExecuteForSumData(ReportCashflowWeeklyExecute entity ,Integer curPageNum, Integer pageSize,ReportCashflowWeeklyExecute backTempBean);

	/**
	 * 新增虚拟公司时，汇总查询
	 * @param entityIn
	 * @param entityOut
	 * @return
	 */
	public int sumForAllChildrenEntity(ReportCashflowWeeklyExecute entityIn,ReportCashflowWeeklyExecute entityOut);
	/**
	 * 查询实体类
	 * @param entityIn
	 * @param entityOut
	 * @return
	 */
	public List<ReportCashflowWeeklyExecute> getReportCashflowWeeklyExecuteListView(ReportCashflowWeeklyExecute entity);
}
