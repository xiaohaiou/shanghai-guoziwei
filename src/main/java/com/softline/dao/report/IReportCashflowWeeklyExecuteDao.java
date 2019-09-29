package com.softline.dao.report;

import java.util.List;

import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.ReportCashflowMonthlyExecute;
import com.softline.entity.ReportCashflowWeeklyExecute;
import com.softline.entity.ReportWeeklyFinancingIntoDetail;
import com.softline.entity.ReportWeeklyFinancingOutDetail;
import com.softline.entity.ReportWeeklyInvestmentOutDetail;
/**
 * 
 * @author tch
 *
 */
public interface IReportCashflowWeeklyExecuteDao {
	
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportCashflowWeeklyExecute getReportCashflowWeeklyExecute(Integer id);
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportCashflowWeeklyExecute> getReportCashflowWeeklyExecuteList(ReportCashflowWeeklyExecute entity ,Integer offsize,Integer pagesize); 
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getReportCashflowWeeklyExecuteListCount(ReportCashflowWeeklyExecute entity); 
	
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveReportCashflowWeeklyExecuteRepeatCheck(ReportCashflowWeeklyExecute entity);
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(ReportCashflowWeeklyExecute entity);
	
	/**
	 * 查询筹资性流入明细实体
	 * @param id
	 * @return
	 */
	public ReportWeeklyFinancingIntoDetail getReportReportFinancingIntoDetail(
			Integer id);

	public List<ReportWeeklyFinancingIntoDetail> getList1(Integer id);

	public List<ReportWeeklyFinancingOutDetail> getList2(Integer id);

	public List<ReportWeeklyInvestmentOutDetail> getList3(Integer id);

	public HhOrganInfoTreeRelation getCoreCompId(String organalID, int type);

	/**
	 * 汇总数据--查询相关公司信息下一层级公司
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @return
	 */
	public List<ReportCashflowWeeklyExecute> getReportCashflowWeeklyExecuteForSumData(ReportCashflowWeeklyExecute entity ,Integer offsize,Integer pagesize);
	
	/**
	 * 汇总给出虚拟公司下子公司的所有信息
	 * @param info
	 * @return
	 */
	public List<ReportCashflowWeeklyExecute> getSumChildrenData(HhOrganInfoTreeRelation info,ReportCashflowWeeklyExecute entityIn);
	
	public int getReportCashflowWeeklyExecuteForSumDataAllData(ReportCashflowWeeklyExecute entity);
	/**
	 * 查询导出数据
	 * @param info
	 * @return
	 */
	public List<ReportCashflowWeeklyExecute> getList(ReportCashflowWeeklyExecute entity);
	
}
