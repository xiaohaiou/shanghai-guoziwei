package com.softline.service.hr;

import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.HeadCountLaborProduction;
import com.softline.util.MsgPage;


public interface IHeadCountService {
	
	
	/**
	 * 查询
	 * @param headCount
	 * @return 
	 */
	public MsgPage findPageList(HeadCountLaborProduction headCount, Integer curPageNum, Integer pageSize);
	
	public MsgPage findPageList(HeadCountLaborProduction entity, Integer curPageNum, Integer pageSize,String isAllCompany);
	
	public MsgPage findExaminePageList(HeadCountLaborProduction headCount, Integer curPageNum, Integer pageSize);
	
	public MsgPage findExaminePageList(HeadCountLaborProduction entity, Integer curPageNum, Integer pageSize,String isAllCompany);
	
	/**
	 * 查询
	 * @param headCount
	 * @return 
	 */
	public HeadCountLaborProduction get(Integer id);
	
	/**
	 * 查询汇总数据
	 * @param headCount
	 * @return 
	 */
	public List<HeadCountLaborProduction> findCollectList(HeadCountLaborProduction headCount);
	
	public boolean get(HeadCountLaborProduction entity);
	
	/**
	 * 修改
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult updateHeadCountLaborProduction(HeadCountLaborProduction entity,HhUsers use,Integer i);
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveHeadCountLaborProduction(HeadCountLaborProduction headCount,HhUsers use,String op);
	
	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteHeadCountLaborProduction(Integer id,HhUsers use);
	
	/**
	 * 审核
	 * @param entityid  人资ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveHeadCountLaborProductionExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);

	public List getOverviewIndustry(String year,String month);

	public CommitResult synHeadCount();
}
