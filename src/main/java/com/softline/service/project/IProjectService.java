package com.softline.service.project;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.softline.entity.HhUsers;
import com.softline.entity.project.PjContractTemp;
import com.softline.entity.project.PjPcPayrecordTemp;
import com.softline.entity.project.PjProject;
import com.softline.entity.project.PjProjectHistory;
import com.softline.entity.project.PjProjectvideo;
import com.softline.entity.project.PjWeekreport;
import com.softline.entity.project.PjWeekreportTemp;
import com.softline.util.MsgPage;

public interface IProjectService {
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
	 * 保存重大项目 新增
	 * @param user 用户
	 * @param project 重大项目
	 * @param file 项目图片
	 * @param isReprot 是否上报 0不上报 1上报
	 */
	void saveAddProject(HhUsers user,PjProject project,String nodeIds,String wkReportIds,String contractIds,MultipartFile file,MultipartFile videoFile,Integer isReport);
	
	
	/**
	 * 项目编辑页面的保存
	 * @param user
	 * @param project
	 * @param file
	 */
	void saveModifyProject(HhUsers user,PjProject project,PjProject oldProject,MultipartFile file,MultipartFile videoFile);
	
	/**
	 * 
	 * @param user 操作人
	 * @param id 项目ID
	 * @return
	 */
	String saveReport(HhUsers user,Integer id);
	
	/**
	 * 新增页面的保存和上报
	 * @param user
	 * @param project
	 * @param nodeIds
	 * @param wkReportIds
	 * @param contractIds
	 * @param file
	 * @param isReport
	 */
	void saveandreport1(HhUsers user,PjProject project,String nodeIds,String wkReportIds,String contractIds,MultipartFile file,MultipartFile videoFile,Integer isReport);

	/**
	 * 编辑页面的上报
	 * @param user
	 * @param project
	 * @param oldProject
	 * @param file
	 */
	void saveandreport2(HhUsers user,PjProject project,PjProject oldProject,MultipartFile file,MultipartFile videoFile);
	
	/**
	 * 查询项目的历史信息
	 * @param pjId
	 * @return
	 */
	List<PjProjectHistory> getPjProjectHistories(Integer pjId); 
	
	/**
	 * 将重点基建工程删除，删除时保存到历史记录中并将version+1,审核状态改为审核通过。
	 * 解决删除数据不能进行同步的问题。
	 * @param id 重点项目ID
	 * @param users 操作人
	 */
	void deleteProject(Integer id,HhUsers users);
	
	/**
	 * 根据条件获取所有的重点基建工程
	 * @param condition 查询条件
	 * @return
	 */
	List<PjProject> getAllPjProjects(PjProject condition);
	
	/**
	 * 获取重点基建工程的离线视屏，通过重点基建工程的ID
	 * @param pjId 重点基建工程ID
	 * @return
	 */
	PjProjectvideo getProjectVideoByPjId(Integer pjId);
	
	/**
	 * 管理操作重点基建工程并保存
	 * @param user
	 * @param project
	 * @param file
	 */
	void saveAdminOPProject(HhUsers user,Integer pjId,Integer pjSort,String outerPjId,MultipartFile videoFile);
	
	/**
	 * 同步重点基建工程的基本信息以及视频
	 * @param users
	 * @return
	 */
	String saveProjectAdminSyn(HhUsers users);
}
