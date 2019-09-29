package com.softline.service.system;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.softline.client.sso.SsoCheckResult;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.entity.SysExamineReport;


public interface IExamineService {

	/**
	 * 根据查询实体的ID和审核种类获取审核信息
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public SysExamine getOneExamine(Integer examineentityid,int examinekind);
	
	/**
	 * 根据查询实体的ID和审核种类获取审核信息列表
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<SysExamine> getListExamine(Integer examineentityid,int examinekind);
	
	
	/**
	 * 进行审核（过程是先把之前的审核意见isdel=1 ,再插入一条）
	 * @param examineentityid 需要审核的实体ID
	 * @param examinekind 审核种类
	 * @param use 审核人
	 * @param examStr 审核意见
	 * @param examResult 审核结果
	 */
	public void saveExamine(Integer examineentityid,int examinekind,HhUsers use,String examStr,Integer examResult);
	
	
	
	/**
	 * 根据查询实体的ID和审核种类获取审核信息列表
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<SysExamineReport> getListReportExamine(Integer groupID,String reportTime,String organal);
	
	/**
	 * 获取一个报表审核的实体
	 * @param groupID
	 * @param reportTime
	 * @param organal
	 * @return
	 */
//	public SysExamineReport getOneReportExamine(Integer groupID,String reportTime,String organal);
	
	/**
	 * 报存报表审核
	 * @param groupID
	 * @param reportTime
	 * @param organal
	 * @param use
	 * @param examStr
	 * @param examResult
	 */
	public void saveReportExamine(Integer groupID,String reportTime,String organal,HhUsers use,String examStr,Integer examResult);
	
	/**
	 * 预算执行单体完成后生成汇总时把单体的审核数据迁过去
	 * @param oldgroup
	 * @param newgroup
	 */
	public void saveChangeReportExamine(Integer oldgroup,Integer newgroup,String reportTime,String organalID);

	/**
	 * 删除报表审核信息
	 * @param id
	 */
	public void deleteExamine(Integer id);
	
	public void deleteExamineByExamentityId(Integer examentityid);
}
