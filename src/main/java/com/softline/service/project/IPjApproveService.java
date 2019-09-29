package com.softline.service.project;

import java.util.List;

import com.softline.entity.project.PjApprove;
import com.softline.entity.project.PjProject;
import com.softline.entityTemp.VContractChange;
import com.softline.entityTemp.VNodeChange;
import com.softline.entityTemp.VProjectChange;

import com.softline.util.MsgPage;

public interface IPjApproveService {
	/**
	 * 查询项目
	 * @param condition 条件实体
	 * @param offset	分页开始位置
	 * @param pageSize	分页最大返回条数
	 * @return
	 */
	MsgPage getPjProjects(String vcEmployeeId,String pjDeptId,PjProject condition,Integer curPageNum,Integer pageSize);
	
	/**
	 * 获取重点项目工程数量
	 * @param vcEmployeeId
	 * @param pjDeptId
	 * @param condition
	 * @return
	 */
	Integer countPjProjects(String vcEmployeeId,String pjDeptId,PjProject condition);
	
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
	 * 基本信息审核
	 * @param approve
	 */
	String saveProjectApprove(PjApprove approve);
	
	/**
	 * 节点信息审核
	 * @param nodeIds 节点IDs
	 * @param approve  审核信息
	 */
	String saveNodeApprove(String nodeIds,PjApprove approve);
	
	/**
	 * 周报信息审核
	 * @param wkReportIds 周报Ids
	 * @param approve 审核信息
	 */
	String saveWkReportApprove(String wkReportIds,PjApprove approve);
	
	/**
	 * 
	 * @param ContractIds 合同IDs
	 * @param approve  审核信息
	 */
	String saveContractApprove(String ContractIds,PjApprove approve);
	
	/**
	 * 查询项目的审核历史
	 * @param pjApprove 封装查询条件的实体
	 * @return
	 */
	List<PjApprove> getPjApproves(PjApprove pjApprove);
}
