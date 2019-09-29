package com.softline.service.report;

import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.ReportOverseasAsset;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
/**
 * 
 * @author tch
 *
 */
public interface IReportOverseasAssetService {

	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getReportOverseasAssetListView(ReportOverseasAsset entity, Integer curPageNum, Integer pageSize);
	/**
	 * 导出
	 * @param entity 查询实体
	 
	 * @return
	 */
	public List<ReportOverseasAsset> getEReportOverseasAssetListView(ReportOverseasAsset entity);
	
	
	/**
	 * 境外资产占比数据审核导出
	 * @param entity 查询实体
	 
	 * @return
	 */
	public List<ReportOverseasAsset> getEReportOverseasAssetListViewExport(ReportOverseasAsset entity);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportOverseasAsset getReportOverseasAsset(Integer id);
	
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportOverseasAsset(ReportOverseasAsset entity,HhUsers use);

	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportOverseasAsset(Integer id,HhUsers use);
	
	/**
	 * 审核
	 * @param entityid  采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveReportOverseasAssetExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);
	
	
}
