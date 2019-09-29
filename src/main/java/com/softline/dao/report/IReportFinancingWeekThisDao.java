package com.softline.dao.report;

import java.util.List;

import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.financing.ReportFinancingWeekThis;
/**
 * 
 * @author ky_tian
 *
 */
public interface IReportFinancingWeekThisDao {
	
	
	/**
	 * 汇总数据查询
	 */
	public List<String> getDataList(ReportFinancingWeekThis entity);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportFinancingWeekThis getReportFinancingWeekThis(Integer id);
	
	/**
	 * 查询
	 */
	public List<ReportFinancingProjectProgress> getExportList(Integer id);
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportFinancingWeekThis> getReportFinancingWeekThisList(ReportFinancingWeekThis entity ,Integer offsize,Integer pagesize,Integer status); 
	
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getReportFinancingWeekThisListCount(ReportFinancingWeekThis entity,Integer status);

	/**
	 * 校验数据是否能更改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(ReportFinancingWeekThis entity); 
	
	/**
	 * 查询一般融资对应日期的下账数据
	 */
	public List<ReportFinancingProjectProgress> getAccountsData(String orgnm);
	
	/**
	 * 查询 --汇总页面所有实体类的数据
	 */
	public List<ReportFinancingProjectProgress> getAllListBeans(String ids,ReportFinancingProjectProgress rfppBean);
	
	/**
	 * 查询--汇总页面，根据年月、周数、周日期范围，查询所有符合条件的数据
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportFinancingWeekThis> getReportFinancingWeekThisListForSumData(ReportFinancingWeekThis entity ,Integer offsize,Integer pagesize,Integer status); 
	
	/**
	 * 获取总记录数
	 * @param entity
	 * @param status
	 * @return
	 */
	public int getReportFinancingWeekThisListForSumData(ReportFinancingWeekThis entity ,Integer status);
	
	public int getAllListBeansRecords(String ids,ReportFinancingProjectProgress rfppBean);

	public List<ReportFinancingProjectProgress> getAllListBeans(String ids);

	public int getReportFinancingWeekThisListForSumDatas(
			ReportFinancingWeekThis entity, Integer examstatus1);
}
