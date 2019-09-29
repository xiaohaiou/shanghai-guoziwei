package com.softline.dao.report;

import java.util.List;

import com.softline.entity.Purchase;
import com.softline.entity.ReportManageAdjust;
/**
 * 
 * @author tch
 *
 */
public interface IReportManageAdjustDao {
	
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportManageAdjust getReportManageAdjust(Integer id);
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportManageAdjust> getReportManageAdjustList(ReportManageAdjust entity ,Integer offsize,Integer pagesize); 
	/**
	 * 查询导出数据
	 * @param entity 
	 *
	 * @return
	 */
	public List<ReportManageAdjust> getExportReportManageAdjustListView(ReportManageAdjust entity);
	
	/**
	 * 查询导出数据
	 * @param entity 
	 *
	 * @return
	 */
	public List<ReportManageAdjust> getExportReportManageAdjustExamineListView(ReportManageAdjust entity);
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getReportManageAdjustListCount(ReportManageAdjust entity); 
	
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveReportManageAdjustRepeatCheck(ReportManageAdjust entity);
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(ReportManageAdjust entity);
	
	/**
	 * 获取年初数据
	 * @param org
	 * @param year
	 * @return
	 */
	public ReportManageAdjust getBeginningData(String org, Integer year);
}
