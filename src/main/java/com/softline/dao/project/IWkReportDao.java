package com.softline.dao.project;

import java.util.List;

import com.softline.entity.project.PjProjectHistory;
import com.softline.entity.project.PjWeekreport;
import com.softline.entity.project.PjWeekreportHistory;

public interface IWkReportDao {
	/**
	 * 根据项目ID得到节点
	 * @param pjId 重点基建工程的ID
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	List<PjWeekreport> getWkReports(Integer pjId,Integer offset,Integer pageSize);
	
	/**
	 * 得到周报列表
	 * @param pjId 项目ID
	 * @param reportStatus 上报审核状态（0未上报 1待审核  2已审核  3已退回）
	 * @return
	 */
	List<PjWeekreport> getWkReports(Integer pjId,Integer reportStatus);
	
	/**
	 * 查询项目的历史信息
	 * @param pjId
	 * @return
	 */
	List<PjWeekreportHistory> getPjWeekreportHistories(Integer wkId); 
}
