package com.softline.dao.project;

import java.util.List;

import com.softline.entity.project.PjApprove;
import com.softline.entity.project.PjProject;
import com.softline.entityTemp.VContractChange;
import com.softline.entityTemp.VNodeChange;
import com.softline.entityTemp.VProjectChange;

public interface IPjApproveDao {
	/**
	 * 查询项目
	 * @param condition 条件实体
	 * @param offset	分页开始位置
	 * @param pageSize	分页最大返回条数
	 * @return
	 */
	List<PjProject> getPjProjects(String ids,String pjDeptId,PjProject condition,Integer offset,Integer pageSize);
	
	/**
	 * 根据项目ID找到改项目基本信息的变化信息
	 * @param pjId 项目ID
	 * @return
	 */
	List<VProjectChange> getProjectChanges(Integer pjId);
	
	/**
	 * 根据项目ID找到改项目节点的变化信息
	 * @param pjId 项目ID
	 * @return
	 */
	List<VNodeChange> getNodeChanges(Integer pjId);
	
	/**
	 * 根据项目ID找到改项目合同信息的变化信息
	 * @param pjId 项目ID
	 * @return
	 */
	List<VContractChange> getContractChanges(Integer pjId);
	
	/**
	 * 查询项目的审核历史
	 * @param pjApprove 封装查询条件的实体
	 * @return
	 */
	List<PjApprove> getPjApproves(PjApprove pjApprove);
	
	
}
