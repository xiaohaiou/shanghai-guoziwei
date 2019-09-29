package com.softline.dao.project;

import java.util.List;

import com.softline.entity.project.PjContract;
import com.softline.entity.project.PjNode;
import com.softline.entity.project.PjProject;
import com.softline.entity.project.PjProjectHistory;
import com.softline.entity.project.PjProjectvideo;
import com.softline.entity.project.PjWeekreport;

public interface IProjectDao {
	
	/**
	 * 查询项目
	 * @param condition 条件实体
	 * @param offset	分页开始位置
	 * @param pageSize	分页最大返回条数
	 * @return
	 */
	List<PjProject> getPjProjects(String ids,String pjDeptId,PjProject condition,Integer offset,Integer pageSize);
	
	/**
	 * 查询项目的历史信息
	 * @param pjId
	 * @return
	 */
	List<PjProjectHistory> getPjProjectHistories(Integer pjId); 
	
	/**
	 * 获取所有没有删除的重点基建工程
	 * @return
	 */
	List<PjProject> getAllPjProjects();
	
	/**
	 * 获取重点基建工程的离线视屏，通过重点基建工程的ID
	 * @param pjId 重点基建工程ID
	 * @return
	 */
	PjProjectvideo getProjectVideoByPjId(Integer pjId);
	
	/**
	 * 获取历史记录最大的记录
	 * @param pjId
	 * @return
	 */
	PjProjectHistory getMaxVersionProject(Integer pjId);
	
}
