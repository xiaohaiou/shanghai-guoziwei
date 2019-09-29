package com.softline.service.social;

import com.softline.entity.HhUsers;
import com.softline.entity.DataSocialMedia;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
/**
 * 
 * @author tch
 *
 */
public interface ISocialMediaService {

	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @param fun 页大小
	 * @return
	 */
	public MsgPage getSocialMediaListView(DataSocialMedia entity, Integer curPageNum, Integer pageSize, Integer fun);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public DataSocialMedia getSocialMedia(Integer id);
	
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @param type
	 * @return
	 */
	public CommitResult saveSocialMedia(DataSocialMedia entity,HhUsers use,String type);

	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteSocialMedia(Integer id,HhUsers use);
	
	/**
	 * 审核
	 * @param entityid  采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveSocialMediaExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);
	
}
