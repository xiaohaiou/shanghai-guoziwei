package com.softline.dao.report;

import java.util.List;

import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.HhOrganInfoTreeRelationHistory;
import com.softline.entity.ReportCashflowMonthlyExecute;
import com.softline.entity.ReportCashflowWeeklyExecute;
import com.softline.entity.ReportMonthlyFinancingIntoDetail;
import com.softline.entity.ReportMonthlyFinancingOutDetail;
import com.softline.entity.ReportMonthlyInvestmentOutDetail;
import com.softline.entity.ReportWeeklyFinancingIntoDetail;
import com.softline.entity.ReportWeeklyFinancingOutDetail;
import com.softline.entity.ReportWeeklyInvestmentOutDetail;
/**
 * 
 * @author tch
 *
 */
public interface IReportCashflowMonthlyExecuteDao {
	
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportCashflowMonthlyExecute getReportCashflowMonthlyExecute(Integer id);
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportCashflowMonthlyExecute> getReportCashflowMonthlyExecuteList(ReportCashflowMonthlyExecute entity ,Integer offsize,Integer pagesize); 
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getReportCashflowMonthlyExecuteListCount(ReportCashflowMonthlyExecute entity); 
	
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveReportCashflowMonthlyExecuteRepeatCheck(ReportCashflowMonthlyExecute entity);
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(ReportCashflowMonthlyExecute entity);

	public List<ReportMonthlyFinancingIntoDetail> getList1(Integer id);

	public List<ReportMonthlyFinancingOutDetail> getList2(Integer id);

	public List<ReportMonthlyInvestmentOutDetail> getList3(Integer id);

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
	public List<ReportCashflowMonthlyExecute> getReportCashflowMonthlyExecuteListForSumData(ReportCashflowMonthlyExecute entity ,Integer offsize,Integer pagesize);

	public List<ReportCashflowMonthlyExecute> getReportCashflowMonthlyExecuteListForSumData(ReportCashflowMonthlyExecute entity);
	
	
	
	/**
	 * 汇总数据--查询相关公司信息下一层级公司信息
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @return
	 */
	public List<HhOrganInfoTreeRelation> getHhOrganInfoTreeRelationSumData(ReportCashflowMonthlyExecute entity ,Integer offsize,Integer pagesize);
	
	
	public HhOrganInfoTreeRelation getCoreCompId(String organalID, int type);
	
	public List<ReportCashflowMonthlyExecute> getSumChildrenData(HhOrganInfoTreeRelationHistory info,ReportCashflowMonthlyExecute entityIn);
	
	public Integer getReportCashflowMonthlyExecuteListForSumDataTotal(ReportCashflowMonthlyExecute entity);

	public List<ReportCashflowMonthlyExecute> getReportCashflowMonthlyExecuteListView(ReportCashflowMonthlyExecute entity);
}



