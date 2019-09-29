package com.softline.dao.report;

import java.util.List;

import com.softline.entity.ReportOverseasAsset;
/**
 * 
 * @author tch
 *
 */
public interface IReportOverseasAssetDao {
	
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportOverseasAsset getReportOverseasAsset(Integer id);
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportOverseasAsset> getReportOverseasAssetList(ReportOverseasAsset entity ,Integer offsize,Integer pagesize); 
	
	/**
	 * 数据导出
	 * @param entity 
	 
	 * @return
	 */
	public List<ReportOverseasAsset> getExportReportOverseasAssetList(ReportOverseasAsset entity);
	
	
	/**
	 * 境外资产占比数据审核导出
	 * @param entity 
	 
	 * @return
	 */
	public List<ReportOverseasAsset> getEReportOverseasAssetListViewExport(ReportOverseasAsset entity);
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getReportOverseasAssetListCount(ReportOverseasAsset entity); 
	
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveReportOverseasAssetRepeatCheck(ReportOverseasAsset entity);
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(ReportOverseasAsset entity);
	

}
