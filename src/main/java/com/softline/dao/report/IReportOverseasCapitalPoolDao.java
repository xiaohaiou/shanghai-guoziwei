package com.softline.dao.report;

import java.util.List;

import com.softline.entity.Purchase;
import com.softline.entity.ReportOverseasCapitalPool;
/**
 * 
 * @author tch
 *
 */
public interface IReportOverseasCapitalPoolDao {
	
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportOverseasCapitalPool getReportOverseasCapitalPool(Integer id);
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportOverseasCapitalPool> getReportOverseasCapitalPoolList(ReportOverseasCapitalPool entity ,Integer offsize,Integer pagesize); 
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getReportOverseasCapitalPoolListCount(ReportOverseasCapitalPool entity); 
	
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveReportOverseasCapitalPoolRepeatCheck(ReportOverseasCapitalPool entity);
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(ReportOverseasCapitalPool entity);
	
	/**
	 * 获取境外下账资金
	 * @param year
	 * @param seaon
	 * @param organID
	 * @return
	 */
	public String getData(int year,String month,String organID);
}
