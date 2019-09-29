package com.softline.service.hr;

import java.util.List;

import com.softline.entity.DataHrManagerialStaff;
import com.softline.entity.HhUsers;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;


public interface IManagerialStaffService {
	
	
	/**
	 * 查询
	 * @param headCount
	 * @return 
	 */
	public MsgPage findPageList(DataHrManagerialStaff entity, Integer curPageNum, Integer pageSize);
	
	public MsgPage findPageList(DataHrManagerialStaff entity, Integer curPageNum, Integer pageSize,String isAllCompany);
	
	public MsgPage findExaminePageList(DataHrManagerialStaff entity, Integer curPageNum, Integer pageSize);
	
	public MsgPage findExaminePageList(DataHrManagerialStaff entity, Integer curPageNum, Integer pageSize,String isAllCompany);
	
	/**
	 * 查询
	 * @param headCount
	 * @return 
	 */
	public DataHrManagerialStaff get(Integer id);
	
	public boolean get(DataHrManagerialStaff entity);
	
	/**
	 * 修改
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult updateDataHrManagerialStaff(DataHrManagerialStaff entity,HhUsers use,Integer i);
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult saveDataHrManagerialStaff(DataHrManagerialStaff entity,HhUsers use,String op);
	
	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteDataHrManagerialstaff(Integer id,HhUsers use);
	
	/**
	 * 审核
	 * @param entityid  人资ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveDataHrManagerialstaffExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);

	public List getOverviewIndustry(String year, String month);

	public CommitResult synManageial();
}
