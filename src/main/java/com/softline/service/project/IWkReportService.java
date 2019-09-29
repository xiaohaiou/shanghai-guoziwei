package com.softline.service.project;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.softline.entity.HhUsers;
import com.softline.entity.project.PjContractTemp;
import com.softline.entity.project.PjPcPayrecordTemp;
import com.softline.entity.project.PjProject;
import com.softline.entity.project.PjWeekreport;
import com.softline.entity.project.PjWeekreportHistory;
import com.softline.entity.project.PjWeekreportTemp;
import com.softline.entityTemp.ProjectWeekReportESBEntity;
import com.softline.util.MsgPage;

public interface IWkReportService {
	
	/**
	 * 临时保存周报
	 * @param user 用户
	 * @param wkReportTemp 项目周报临时表
	 * @param file 周报PDF文件
	 * @param oldEntity 老的实体
	 * @return
	 */
	String saveWkReportTemp(HhUsers user,PjWeekreportTemp wkReportTemp,MultipartFile file,PjWeekreportTemp OldEntity);
	
	/**
	 * 得到周报列表，分页
	 * @param pjId
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	MsgPage getWkReports(Integer pjId,Integer curPageNum,Integer pageSize);
	
	/**
	 * 保存周报
	 * @param user 用户
	 * @param wkReportTemp 项目周报临时表
	 * @param file 周报PDF文件
	 * @param oldEntity 老的实体
	 * @return
	 */
	void saveWkReport(HhUsers user,PjWeekreport wkReport,MultipartFile file,PjWeekreport OldEntity);
	
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
	
	/**
	 * 将重点基建工程的周报删除，删除时保存到历史记录中并将version+1,审核状态改为审核通过。
	 * 解决删除数据不能进行同步的问题。
	 * @param id 周报ID
	 * @param users 操作人
	 */
	void deleteWkReport(Integer id,HhUsers users);
	
	/**
	 * 调用接口
	 * 获取周报的信息
	 * 工程进度   thisProgress
	 * 工程质量  thisQuality
	 * 工程安全  thisSHE
	 * 招标采购   thisPurchase
	 * @param year
	 * @param week
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	ProjectWeekReportESBEntity getWeekReportSubInfo(String year,String week,String Pid,HhUsers users );

}
