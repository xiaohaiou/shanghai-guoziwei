package com.softline.service.report;

import com.softline.entity.HhUsers;
import com.softline.entity.ReportOverseasCapitalPool;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
/**
 * 
 * @author tch
 *
 */
public interface IReportOverseasCapitalPoolService {

	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getReportOverseasCapitalPoolListView(ReportOverseasCapitalPool entity, Integer curPageNum, Integer pageSize);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportOverseasCapitalPool getReportOverseasCapitalPool(Integer id);
	
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportOverseasCapitalPool(ReportOverseasCapitalPool entity,HhUsers use);

	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportOverseasCapitalPool(Integer id,HhUsers use);
	
	/**
	 * 审核
	 * @param entityid  采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveReportOverseasCapitalPoolExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);
	
	/**
	 * 获取境外下账资金
	 * @param year
	 * @param seaon
	 * @return
	 */
	public String getData(int year,int seaon,String organID);
}
