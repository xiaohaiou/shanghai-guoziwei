package com.softline.dao.system;

import java.util.List;

import com.softline.entity.HhBase;
import com.softline.entity.SysExamine;
import com.softline.entity.SysExamineReport;

public interface IExamineDao {
	
	/**
	 * 根据审核实体ID和审核种类获取
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
	 * 根据查询实体的ID和审核种类获取审核信息列表
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<SysExamineReport> getListReportExamine(Integer groupID,String reportTime,String organal);
	
	
//	public SysExamineReport getOneReportExamine(Integer groupID,String reportTime,String organal);
	
	/**
	 * 预算执行单体完成后生成汇总时把单体的审核数据迁过去
	 * @param oldgroup
	 * @param newgroup
	 */
	public void saveChangeReportExamine(Integer oldgroup,Integer newgroup,String reportTime,String organalID);
	
	public void deleteExamine(Integer id);
	
	public void deleteExamineByExamentityId(Integer examentityid);
	
	public void deleteListReportExamine(Integer groupID,String reportTime,String organal);
}
