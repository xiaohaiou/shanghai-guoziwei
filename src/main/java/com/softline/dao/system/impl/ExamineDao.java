package com.softline.dao.system.impl;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.system.IBaseDao;
import com.softline.dao.system.IExamineDao;
import com.softline.entity.HhBase;
import com.softline.entity.SysExamine;
import com.softline.entity.SysExamineReport;
import com.softline.service.system.ISystemService;

@Repository(value = "examineDao")
public class ExamineDao implements IExamineDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Resource(name = "systemService")
	private ISystemService systemService;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public SysExamine getOneExamine(Integer examineentityid,int examinekind)
	{
		StringBuffer hql = new StringBuffer();
		if(examineentityid != null){
			
			hql.append("from SysExamine t where t.isdel = 0 and t.examentityid = "+examineentityid);
			hql.append(" and t.examKind ="+examinekind+" ");
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			return (SysExamine) query.uniqueResult();
		}else{
			return new SysExamine();
		}
	}
	
	/**
	 * 根据查询实体的ID和审核种类获取审核信息列表
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<SysExamine> getListExamine(Integer examineentityid,int examinekind)
	{
		StringBuffer hql = new StringBuffer();
		if(examineentityid != null){
			
			hql.append("from SysExamine t where t.isdel = 0 and t.examentityid = "+examineentityid);
			hql.append(" and t.examKind ="+examinekind+" ");
			hql.append(" order by  createDate desc ");
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			return query.list();
		}else{
			return new ArrayList<SysExamine>();
		}
	}
	
	
	/**
	 * 根据查询实体的ID和审核种类获取审核信息列表
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<SysExamineReport> getListReportExamine(Integer groupID,String reportTime,String organal)
	{
		StringBuffer hql = new StringBuffer();
		if(groupID != null && Common.notEmpty(reportTime) && Common.notEmpty(organal)){
			hql.append("from SysExamineReport t where t.groupId = "+groupID);
			hql.append(" and t.reportTime ='"+reportTime+"' ");
			hql.append(" and t.organalId ='"+organal+"' ");
			hql.append(" and t.isdel = 0");
			hql.append(" order by  createDate desc ");
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			return query.list();
		}else{
			return new ArrayList<SysExamineReport>();
		}
	}
	
	public void deleteListReportExamine(Integer groupID,String reportTime,String organal)
	{
		StringBuffer hql = new StringBuffer();
		if(groupID != null && Common.notEmpty(reportTime) && Common.notEmpty(organal)){
			hql.append("update SysExamineReport t set t.isdel=1 where t.groupId = "+groupID);
			hql.append(" and t.reportTime ='"+reportTime+"' ");
			hql.append(" and t.organalId ='"+organal+"' ");
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			query.executeUpdate();		
		}
	}
	
//	public SysExamineReport getOneReportExamine(Integer groupID,String reportTime,String organal)
//	{
//		StringBuffer hql = new StringBuffer();
//		if(groupID != null && Common.notEmpty(reportTime) && Common.notEmpty(organal)){
//			
//			hql.append("from SysExamineReport t where t.isdel = 0 and t.groupId = "+groupID);
//			hql.append(" and t.reportTime ='"+reportTime+"' ");
//			hql.append(" and t.organalId ='"+organal+"' ");
//			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
//			return (SysExamineReport) query.uniqueResult();
//		}else{
//			return new SysExamineReport();
//		}
//	}
	
	/**
	 * 预算执行单体完成后生成汇总时把单体的审核数据迁过去
	 * @param oldgroup
	 * @param newgroup
	 */
	public void saveChangeReportExamine(Integer oldgroup,Integer newgroup,String reportTime,String organalID)
	{
		if(oldgroup!=null && newgroup!=null)
		{
			StringBuilder ReportCell = new StringBuilder();
			
			ReportCell.append("INSERT INTO `sys_examine_report` (`examinestr`, `examresult`, `reportTime`, `groupID`, `examKind`, `isdel`, `createPersonName`, `createPersonID`, `createDate`, `lastModifyPersonID`, `lastModifyPersonName`, `lastModifyDate`, `organalID`) ");
			ReportCell.append(" SELECT `examinestr`, `examresult`, `reportTime`, "+newgroup+", `examKind`, `isdel`, `createPersonName`, `createPersonID`, `createDate`, `lastModifyPersonID`, `lastModifyPersonName`, `lastModifyDate`, `organalID` FROM `sys_examine_report` where groupID="+oldgroup+" and organalID='"+organalID+"' and reportTime='"+reportTime+"'");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(ReportCell.toString());
			query.executeUpdate();
		}
	}

	@Override
	public void deleteExamine(Integer id) {
		
		String hql = "update SysExamine s set s.isdel=1 where s.id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();		
	}
	
	@Override
	public void deleteExamineByExamentityId(Integer examentityid){
		String hql = "update SysExamine s set s.isdel=1 where s.examentityid=" + examentityid;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();	
	}
}
