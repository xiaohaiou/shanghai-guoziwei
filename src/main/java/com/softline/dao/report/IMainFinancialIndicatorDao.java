package com.softline.dao.report;

import java.util.List;

import com.softline.entity.MainFinancialIndicator;
import com.softline.entity.MainFinancialIndicatorStock;
/**
 * 
 * @author tch
 *
 */
public interface IMainFinancialIndicatorDao {
	
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public MainFinancialIndicator getMainFinancialIndicator(Integer id);
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<MainFinancialIndicator> getMainFinancialIndicatorList(MainFinancialIndicator entity ,Integer offsize,Integer pagesize); 
	
	/**
	 * 导出
	 */
	public List<MainFinancialIndicator> getMainFinancialIndicatorExportList(MainFinancialIndicator entity);
	
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getMainFinancialIndicatorListCount(MainFinancialIndicator entity); 
	
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveMainFinancialIndicatorRepeatCheck(MainFinancialIndicator entity);
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(MainFinancialIndicator entity);
	
	
	/**
	 * 获取谋个主要财务指标数据数据的股东数据
	 * @param parentID
	 * @return
	 */
	public List<MainFinancialIndicatorStock> getStockData(int parentID);
	
	/**
	 * 获取实收资本对应的股东占比信息 从表MainFinancialIndicatorStock中取
	 * @param condition  
	 * @return
	 */
	public List<MainFinancialIndicatorStock> getSszbDataFT(MainFinancialIndicator condition);
	
	
	/**
	 * 获取注册资本对应的股东占比信息 从表MainFinancialIndicatorStock中取
	 * @param condition  
	 * @return
	 */
	public List<MainFinancialIndicatorStock> getZczbDataFT(MainFinancialIndicator condition);
	
	/**
	 * 获取某个公司在某年某月的单体或合并报表
	 * @param year   年
	 * @param month  月
	 * @param org    公司ID
	 * @param reportType 单体OR合并
	 * @return
	 */
	public List<MainFinancialIndicator> getMainFinancialIndicators(Integer year,Integer month,String org,Integer reportType);
	
}
