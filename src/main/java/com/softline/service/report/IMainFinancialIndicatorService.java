package com.softline.service.report;

import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.MainFinancialIndicator;
import com.softline.entity.MainFinancialIndicatorStock;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.ReportIndexAndData;
import com.softline.util.MsgPage;
/**
 * 
 * @author tch
 *
 */
public interface IMainFinancialIndicatorService {

	
	/**
	 * 保存校验
	 * @param entity
	 * @return
	 */
	public CommitResult saveMainFinancialIndicatorCheck(MainFinancialIndicator entity);
	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getMainFinancialIndicatorListView(MainFinancialIndicator entity, Integer curPageNum, Integer pageSize);
	
	/**
	 * 导出
	 * 
	 */
	public List<MainFinancialIndicator> getMainFinancialIndicatorExportList(MainFinancialIndicator entity);
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public MainFinancialIndicator getMainFinancialIndicator(Integer id);
	
	
	/**
	 * 保存更新
	 * @param entity
	 * @param stock
	 * @param use
	 * @return
	 */
	public MainFinancialIndicator saveMainFinancialIndicator(List<ReportIndexAndData> data,String organl,String Date,HhUsers use,int type);
	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 *//*
	public CommitResult deleteMainFinancialIndicator(Integer id,HhUsers use);
	
	*//**
	 * 审核
	 * @param entityid  采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 *//*
	public CommitResult saveMainFinancialIndicatorExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);*/
	
	
	/**
	 * 获取谋个主要财务指标数据数据的股东数据
	 * @param parentID
	 * @return
	 */
	public List<MainFinancialIndicatorStock> getStockData(int parentID);
	
	/**
	 * 获取实收资本对应的股东占比信息
	 * @param condition  提取MainFinancialIndicator中的年、月、公司ID
	 * @return
	 */
	public List<MainFinancialIndicatorStock> getSszbData(MainFinancialIndicator condition);
	
	
	/**
	 * 获取注册资本对应的股东占比信息
	 * @param condition  提取MainFinancialIndicator中的年、月、公司ID
	 * @return
	 */
	public List<MainFinancialIndicatorStock> getZczbData(MainFinancialIndicator condition);
	
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
	 * 删除主要指标数据
	 * @param data
	 * @param organl
	 * @param Date
	 * @param use
	 * @param type
	 * @return
	 */
	public void stepBackMainFinancialIndicator(List<ReportIndexAndData> data,String organl,String Date,HhUsers use,int type);
	
}
