package com.softline.dao.project;

import java.util.List;

import com.softline.entity.project.PjNode;
import com.softline.entity.project.PjNodeHistory;
import com.softline.entity.project.PjProject;
import com.softline.util.MsgPage;

public interface INodeDao {
	/**
	 * 得到节点最后的版本号
	 * @param pjId 项目Id
	 * @return 最后的版本号
	 */
	Integer getNodeLastVersion(Integer pjId);
	
	/**
	 * 根据项目ID得到节点
	 * @param pjId 重点基建工程的ID
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	List<PjNode> getNodes(Integer pjId,Integer offset,Integer pageSize);
	
	/**
	 * 
	 * @param pjId 重点基建工程的ID
	 * @param reportStauts 审核状态
	 * @return
	 */
	List<PjNode> getNodes(Integer pjId,Integer reportStauts);
	
	/**
	 * 根据nodeId得到历史数据
	 * @param nodeId
	 * @return
	 */
	List<PjNodeHistory> getNodehHistories(Integer nodeId); 
}
