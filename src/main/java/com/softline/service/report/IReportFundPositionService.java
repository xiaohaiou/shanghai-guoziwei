package com.softline.service.report;

import com.softline.entity.HhUsers;
import com.softline.entity.fundPosition.DataFundPosition;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
/**
 * 
 * @author ky_tian
 *
 */
public interface IReportFundPositionService {

	/**
	 * 查询
	 */
	public MsgPage findPageList(DataFundPosition entity, Integer curPageNum, Integer pageSize);
	
	public MsgPage findExaminePageList(DataFundPosition entity, Integer curPageNum, Integer pageSize);
	
	/**
	 * 查询
	 */
	public DataFundPosition get(Integer id);
	
	
	public boolean get(DataFundPosition entity);
	
	/**
	 * 获取最近头寸
	 */
	public DataFundPosition getCash(String org);
	
	/**
	 * 修改
	 */
	public CommitResult updateReportFundPosition(DataFundPosition entity,HhUsers use,Integer i);
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFundPosition(DataFundPosition entity,HhUsers use,String op);
	
	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteReportFundPosition(Integer id,HhUsers use);
	
	/**
	 * 审核
	 * @param entityid  人资ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveReportFundPositionExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);
	
	/**
	 *子公司数据汇总
	 */
	public MsgPage findAllchildernCompany(DataFundPosition entity, Integer curPageNum, Integer pageSize);
	
}
