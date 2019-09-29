package com.softline.service.report;

import java.util.List;
import java.util.Map;

import com.softline.entity.HhUsers;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.financing.ReportFinancingWeekNext;
import com.softline.entity.financing.ReportFinancingWeekThis;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
public interface IReportFinancingWeekThisService {

	/**
	 * 汇总数据查询
	 */
	public List<String> getDataList(ReportFinancingWeekThis entity);
	
	
	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getReportFinancingWeekThisListView(ReportFinancingWeekThis entity, Integer curPageNum, Integer pageSize, Integer status);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportFinancingWeekThis getReportFinancingWeekThis(Integer id);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public List<ReportFinancingProjectProgress> getExportList(Integer id);
	
	
	/**
	 * 编辑页面保存更新，并保存附件
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingWeekThisAndUpdate(ReportFinancingWeekThis entity,HhUsers use);

	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportFinancingWeekThis(Integer id,HhUsers use);


	/**
	 * list页面上报时，实体更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingWeekThis(
			ReportFinancingWeekThis entity, HhUsers use);

	/**
	 * 提交审核
	 * @param entityid
	 * @param examStr
	 * @param examResult
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingWeekThisExamine(
			Integer entityid, String examStr, Integer examResult, HhUsers use);
	
	/**
	 * 查询一般融资对应日期的下账数据
	 */
	public String getAccountsData(String orgnm);

	
	/**
	 * 查询-----------融资项目进展数据填报--所有实体类
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public Map getReportFinancingWeekThisListSumDataView(ReportFinancingWeekThis entity,ReportFinancingProjectProgress rfppBean, Integer curPageNum,Integer pageSize, Integer status);


	public List<ReportFinancingProjectProgress> getReportFinancingWeekThisListSumDataView(
			ReportFinancingWeekThis entity, Integer examstatus1);
	
}
