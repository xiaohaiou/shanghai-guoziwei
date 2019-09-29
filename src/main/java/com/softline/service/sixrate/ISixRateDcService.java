package com.softline.service.sixrate;

import com.softline.entity.DataSixRateDc;
import com.softline.entity.HhUsers;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;


public interface ISixRateDcService {
	
	
	/**
	 * 查询
	 * @param headCount
	 * @return 
	 */
	public MsgPage findPageList(DataSixRateDc entity, Integer curPageNum, Integer pageSize);
	
	public MsgPage findExaminePageList(DataSixRateDc entity, Integer curPageNum, Integer pageSize);
	
	/**
	 * 查询
	 * @param headCount
	 * @return 
	 */
	public DataSixRateDc get(Integer id);
	
	public boolean get(DataSixRateDc entity);
	
	/**
	 * 修改
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult updateDataSixRateDc(DataSixRateDc entity,HhUsers use,Integer i);
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveDataSixRateDc(DataSixRateDc entity,HhUsers use,String op);
	
	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteDataSixRateDc(Integer id,HhUsers use);
	
	/**
	 * 审核
	 * @param entityid  人资ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveDataSixRateDcExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);
}
