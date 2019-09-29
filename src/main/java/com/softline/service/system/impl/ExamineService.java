package com.softline.service.system.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.softline.client.sso.AuthorityServiceService;
import com.softline.client.sso.IAuthorityService;
import com.softline.client.sso.SsoCheckResult;
import com.softline.common.Base;
import com.softline.dao.system.ICommonDao;
import com.softline.dao.system.IExamineDao;
import com.softline.dao.system.ISystemDao;
import com.softline.entity.HhUsers;
import com.softline.entity.SysExamine;
import com.softline.entity.SysExamineReport;
import com.softline.service.system.IExamineService;
import com.softline.service.system.ISsoService;

@Service("examineService")
public class ExamineService implements IExamineService{

	@Autowired
	@Qualifier("examineDao")
	private IExamineDao examineDao;
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	/**
	 * 根据查询实体的ID和审核种类获取审核信息列表
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<SysExamine> getListExamine(Integer examineentityid,int examinekind)
	{
		return examineDao.getListExamine( examineentityid, examinekind);
	}
	
	
	public SysExamine getOneExamine(Integer examineentityid,int examinekind)
	{
		return examineDao.getOneExamine( examineentityid, examinekind);
	}
	
	public void saveExamine(Integer examineentityid,int examinekind,HhUsers use,String examStr,Integer examResult)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//针对审核的处理，先假删历史审核数据
		SysExamine oldexamine=getOneExamine( examineentityid, examinekind);
		if(oldexamine!=null)
		{
			oldexamine.setIsdel(1);
			oldexamine.setLastModifyDate(df.format(new Date()));
			oldexamine.setLastModifyPersonId(use.getVcEmployeeId());
			oldexamine.setLastModifyPersonName(use.getVcName());
			commonDao.saveOrUpdate(oldexamine);
		}
		//再插入新的审核数据
		SysExamine	examine=new SysExamine();
		examine.setCreateDate(df.format(new Date()));
		examine.setCreatePersonId(use.getVcEmployeeId());
		examine.setCreatePersonName(use.getVcName());
		examine.setIsdel(0);
		examine.setExamentityid(examineentityid);
		examine.setExaminestr(examStr);
		examine.setExamKind(examinekind);
		examine.setExamresult(examResult);
		commonDao.saveOrUpdate(examine);
	}
	
	
	/**
	 * 根据查询实体的ID和审核种类获取审核信息列表
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<SysExamineReport> getListReportExamine(Integer groupID,String reportTime,String organal)
	{
		return examineDao.getListReportExamine( groupID, reportTime, organal);
	}
	
	/**
	 * 此处有bug,可能返回多条数据
	 *   Author by zl
	 */
	
//	public SysExamineReport getOneReportExamine(Integer groupID,String reportTime,String organal)
//	{
//		return examineDao.getOneReportExamine( groupID, reportTime, organal);
//	}
	
	public void saveReportExamine(Integer groupID,String reportTime,String organal,HhUsers use,String examStr,Integer examResult)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//针对审核的处理，先假删历史审核数据
		List<SysExamineReport> oldexamine=getListReportExamine(  groupID, reportTime, organal);
		if(oldexamine!=null && oldexamine.size()>0)
		{
			for(SysExamineReport tempExamine:oldexamine){
				tempExamine.setIsdel(1);
				tempExamine.setLastModifyDate(df.format(new Date()));
				tempExamine.setLastModifyPersonId(use.getVcEmployeeId());
				tempExamine.setLastModifyPersonName(use.getVcName());
				commonDao.saveOrUpdate(tempExamine);
			}
		}
		//再插入新的审核数据
		SysExamineReport	examine=new SysExamineReport();
		examine.setCreateDate(df.format(new Date()));
		examine.setCreatePersonId(use.getVcEmployeeId());
		examine.setCreatePersonName(use.getVcName());
		examine.setIsdel(0);
		examine.setGroupId(groupID);
		examine.setReportTime(reportTime);
		examine.setOrganalId(organal);
		examine.setExaminestr(examStr);
		examine.setExamresult(examResult);
		commonDao.saveOrUpdate(examine);
	}

	/**
	 * 预算执行单体完成后生成汇总时把单体的审核数据迁过去
	 * @param oldgroup
	 * @param newgroup
	 */
	public void saveChangeReportExamine(Integer oldgroup,Integer newgroup,String reportTime,String organalID)
	{
		 examineDao.saveChangeReportExamine(oldgroup, newgroup,reportTime,organalID);
	}

	@Override
	public void deleteExamine(Integer id) {
		examineDao.deleteExamine(id);		
	}
	
	@Override
	public void deleteExamineByExamentityId(Integer examentityid){
		examineDao.deleteExamineByExamentityId(examentityid);	
	}


}
