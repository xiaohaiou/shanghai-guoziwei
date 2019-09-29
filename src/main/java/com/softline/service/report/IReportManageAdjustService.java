package com.softline.service.report;

import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.ReportManageAdjust;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
/**
 * 
 * @author tch
 *
 */
public interface IReportManageAdjustService {

	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getReportManageAdjustListView(ReportManageAdjust entity, Integer curPageNum, Integer pageSize);
	
	/**
	 * 管理口径核算数据填报查询导出数据
	 * @param entity 
	 *
	 * @return
	 */
	public List<ReportManageAdjust> getExportReportManageAdjustListView(ReportManageAdjust entity);
	
	
	/**
	 * 管理口径核算数据审核导出数据
	 * @param entity 
	 *
	 * @return
	 */
	public List<ReportManageAdjust> getExportReportManageAdjustExamineListView(ReportManageAdjust entity);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportManageAdjust getReportManageAdjust(Integer id);
	
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportManageAdjust(ReportManageAdjust entity,HhUsers use);

	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportManageAdjust(Integer id,HhUsers use);
	
	/**
	 * 审核
	 * @param entityid  采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveReportManageAdjustExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);
	
	/**
	 * 获取年初的数据
	 * @param org
	 * @param year
	 * @return
	 */
	public ReportManageAdjust getBeginningData(String org, Integer year);


}
