package com.softline.service.report;

import java.util.List;
import java.util.Map;

import com.softline.entity.HhUsers;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.financing.ReportFinancingWeekNext;
import com.softline.entity.financing.ReportFinancingWeekThis;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
public interface IReportFinancingWeekNextService {

	/**
	 * 汇总数据查询
	 */
	public List<String> getDataList(ReportFinancingWeekNext entity);
	
	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getReportFinancingWeekNextListView(ReportFinancingWeekNext entity, Integer curPageNum, Integer pageSize, Integer status);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportFinancingWeekNext getReportFinancingWeekNext(Integer id);
	
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
	public CommitResult saveReportFinancingWeekNextAndUpdate(ReportFinancingWeekNext entity,HhUsers use);

	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportFinancingWeekNext(Integer id,HhUsers use);


	/**
	 * list页面上报时，实体更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingWeekNext(
			ReportFinancingWeekNext entity, HhUsers use);

	/**
	 * 提交审核
	 * @param entityid
	 * @param examStr
	 * @param examResult
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFinancingWeekNextExamine(
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
	public Map getReportFinancingWeekNextListSumDataView(ReportFinancingWeekNext entity,ReportFinancingProjectProgress rfppBean, Integer curPageNum,Integer pageSize, Integer status);



	public List<ReportFinancingProjectProgress> getReportFinancingWeekNextListSumDataView(ReportFinancingWeekNext entity, Integer status);
}
