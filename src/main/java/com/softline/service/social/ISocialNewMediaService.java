package com.softline.service.social;

import org.springframework.web.multipart.MultipartFile;

import com.softline.entity.HhUsers;
import com.softline.entity.DataSocialNewMedia;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
/**
 * 
 * @author tch
 *
 */
public interface ISocialNewMediaService {

	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @param fun 页大小
	 * @return
	 */
	public MsgPage getSocialNewMediaListView(DataSocialNewMedia entity, Integer curPageNum, Integer pageSize, Integer fun);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public DataSocialNewMedia getSocialNewMedia(Integer id);
	
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @param type
	 * @return
	 */
	public CommitResult saveSocialNewMedia(DataSocialNewMedia entity,HhUsers use,String type,MultipartFile file);

	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteSocialNewMedia(Integer id,HhUsers use);
	
	/**
	 * 审核
	 * @param entityid  采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveSocialNewMediaExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);
	
}
