package com.softline.service.hr;

import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entityTemp.CommitResult;
import com.softline.entityTemp.LaborcostResourcesreWards;
import com.softline.util.MsgPage;


public interface ILaborCostService {
	
	
	/**
	 * 查询
	 * @param headCount
	 * @return 
	 */
	public MsgPage findPageList(LaborcostResourcesreWards entity, Integer curPageNum, Integer pageSize);
	
	public MsgPage findPageList(LaborcostResourcesreWards entity, Integer curPageNum, Integer pageSize,String isAllCompany);
	
	public MsgPage findExaminePageList(LaborcostResourcesreWards entity, Integer curPageNum, Integer pageSize);
	
	public MsgPage findExaminePageList(LaborcostResourcesreWards entity, Integer curPageNum, Integer pageSize,String isAllCompany);
	
	/**
	 * 查询
	 * @param headCount
	 * @return 
	 */
	public LaborcostResourcesreWards get(Integer id);
	
	public boolean  get(LaborcostResourcesreWards entity);
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveLaborcostResourcesreWards(LaborcostResourcesreWards headCount,HhUsers use,String op);
	
	/**
	 * 修改
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult updateLaborcostResourcesreWards(LaborcostResourcesreWards entity,HhUsers use,Integer i);
	
	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteLaborcostResourcesreWards(Integer id,HhUsers use);
	
	/**
	 * 审核
	 * @param entityid  人资ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveLaborcostResourcesreWardsExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);

	public List getOverviewIndustry(String year, String month);

	public CommitResult synLaborCost();
}
