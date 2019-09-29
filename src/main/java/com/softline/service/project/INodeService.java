package com.softline.service.project;

import java.util.List;

import com.softline.entity.HhUsers;
import com.softline.entity.project.PjNode;
import com.softline.entity.project.PjNodeHistory;
import com.softline.util.MsgPage;


public interface INodeService {
	
	/**
	 * 得到节点最后的版本号
	 * @param pjId 项目Id
	 * @return 最后的版本号
	 */
	Integer getNodeLastVersion(Integer pjId);
	
	/**
	 * 等到nodes列表，分页
	 * @param pjId
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	MsgPage getNodes(Integer pjId,Integer curPageNum,Integer pageSize);
	
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
	
	/**
	 * 将重点基建工程的节点删除，删除时保存到历史记录中并将version+1,审核状态改为审核通过。
	 * 解决删除数据不能进行同步的问题。
	 * @param id 节点ID
	 * @param users 操作人
	 */
	void deleteNode(Integer id,HhUsers users);

}
