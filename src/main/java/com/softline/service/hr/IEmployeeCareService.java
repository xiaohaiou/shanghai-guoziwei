package com.softline.service.hr;

import com.softline.entity.HhEmployeeCare;
import com.softline.entity.HhUsers;
import com.softline.entity.DataSocialVoluntary;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
/**
 * 
 * @author tch
 *
 */
public interface IEmployeeCareService {

	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @param fun 页大小
	 * @return
	 */
	public MsgPage getSocialVoluntaryListView(HhEmployeeCare entity, Integer curPageNum, Integer pageSize, Integer fun);
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public HhEmployeeCare getSocialVoluntary(Integer id);
	
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @param type
	 * @return
	 */
	public CommitResult saveSocialVoluntary(HhEmployeeCare entity,HhUsers use,String type);

	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteSocialVoluntary(Integer id,HhUsers use);
	
	/**
	 * 审核
	 * @param entityid  采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveSocialVoluntaryExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);
	
}
