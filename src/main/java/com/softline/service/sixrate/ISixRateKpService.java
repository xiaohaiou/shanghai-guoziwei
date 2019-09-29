package com.softline.service.sixrate;

import com.softline.entity.DataSixRateKp;
import com.softline.entity.HhUsers;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;


public interface ISixRateKpService {
	
	
	/**
	 * 查询
	 * @param headCount
	 * @return 
	 */
	public MsgPage findPageList(DataSixRateKp entity, Integer curPageNum, Integer pageSize);
	
	public MsgPage findExaminePageList(DataSixRateKp entity, Integer curPageNum, Integer pageSize);
	
	/**
	 * 查询
	 * @param headCount
	 * @return 
	 */
	public DataSixRateKp get(Integer id);
	
	public boolean get(DataSixRateKp entity);
	
	/**
	 * 修改
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult updateDataSixRateKp(DataSixRateKp entity,HhUsers use,Integer i);
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveDataSixRateKp(DataSixRateKp entity,HhUsers use,String op);
	
	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteDataSixRateKp(Integer id,HhUsers use);
	
	/**
	 * 审核
	 * @param entityid  人资ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveDataSixRateKpExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);
}
