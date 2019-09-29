package com.softline.service.report;

import java.util.Hashtable;
import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.ReportOverseasLiabilitiesDetail;
import com.softline.entity.ReportStockLiabilities;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
/**
 * 
 * @author tch
 *
 */
public interface IReportStockLiabilitiesService {

	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getReportStockLiabilitiesListView(ReportStockLiabilities entity, Integer curPageNum, Integer pageSize);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportStockLiabilities getReportStockLiabilities(Integer id);
	
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportStockLiabilities(ReportStockLiabilities entity,HhUsers use);

	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportStockLiabilities(Integer id,HhUsers use);
	
	/**
	 * 审核
	 * @param entityid  采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveReportStockLiabilitiesExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);

	public List<ReportOverseasLiabilitiesDetail> getList1(Integer id);

	public CommitResult saveReportStockLiabilitiesAndDetail(
			ReportStockLiabilities reportStockLiabilities, HhUsers use,
			List<ReportOverseasLiabilitiesDetail> list1);
	
	/**
	 * 汇总查询负载数据
	 */
	public List<ReportStockLiabilities> getSumBeanList(ReportStockLiabilities entity,Integer year,Integer month,String status);
	
	public int getSumResult(List<ReportStockLiabilities> reportStockLiabilitiesList,Hashtable<String,Object> backMap);
	
	
}
