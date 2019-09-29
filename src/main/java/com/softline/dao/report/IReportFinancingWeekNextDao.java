package com.softline.dao.report;

import java.util.List;

import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.financing.ReportFinancingWeekNext;
import com.softline.entity.financing.ReportFinancingWeekThis;
/**
 * 
 * @author ky_tian
 *
 */
public interface IReportFinancingWeekNextDao {
	
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportFinancingWeekNext getReportFinancingWeekNext(Integer id);
	
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
	public List<ReportFinancingWeekNext> getReportFinancingWeekNextList(ReportFinancingWeekNext entity ,Integer offsize,Integer pagesize,Integer status); 
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getReportFinancingWeekNextListCount(ReportFinancingWeekNext entity,Integer status);

	/**
	 * 校验数据是否能更改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(ReportFinancingWeekNext entity); 
	
	/**
	 * 查询一般融资对应日期的下账数据
	 */
	public List<ReportFinancingProjectProgress> getAccountsData(String orgnm);
	
	/**
	 * 汇总数据查询
	 */
	public List<String> getDataList(ReportFinancingWeekNext entity);
	
	/**
	 * 查询 --汇总页面所有实体类的数据
	 */
	public List<ReportFinancingProjectProgress> getAllListBeans(String ids,ReportFinancingProjectProgress rfppBean,Integer offsize,Integer pagesize);
	
	public List<String> getSumData(String ids,ReportFinancingProjectProgress rfppBean);
	
	/**
	 * 查询--汇总页面，根据年月、周数、周日期范围，查询所有符合条件的数据
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportFinancingWeekNext> getReportFinancingWeekNextListForSumData(ReportFinancingWeekNext entity ,Integer offsize,Integer pagesize,Integer status); 

	public int getReportFinancingWeekNextListForSumData(ReportFinancingWeekNext entity ,Integer status);
	
	public int getAllListBeansRecords(String ids,ReportFinancingProjectProgress rfppBean);

	public List<ReportFinancingProjectProgress> getAllListBeans(String ids);

}
