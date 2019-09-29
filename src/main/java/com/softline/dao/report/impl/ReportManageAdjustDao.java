package com.softline.dao.report.impl;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.report.IReportManageAdjustDao;
import com.softline.entity.Purchase;
import com.softline.entity.ReportManageAdjust;

@Repository(value = "reportManageAdjustDao")
/**
 * 
 * @author tch
 *
 */
public class ReportManageAdjustDao implements IReportManageAdjustDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportManageAdjust getReportManageAdjust(Integer id)
	{
		if(id==null)
			return new ReportManageAdjust();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportManageAdjust s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (ReportManageAdjust) query.uniqueResult();
	}
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportManageAdjust> getReportManageAdjustList(ReportManageAdjust entity ,Integer offsize,Integer pagesize)
	{
		if(entity==null)
			return new ArrayList<ReportManageAdjust>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportManageAdjust s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+Common.dealinStr(entity.getOrg())+ ") ");
			}
			/*if(entity.getFredata()!=null)
			{
				hql.append(" and s.fredata = "+entity.getFredata()+ " ");
			}
			if(entity.getFre()!=null)
			{
				hql.append(" and s.fre = "+entity.getFre()+ " ");
			}
			if(entity.getYear()!=null)
			{
				hql.append(" and s.year = "+entity.getYear()+ " ");
			}*/
			if(Common.notEmpty(entity.getStarttime()))
			{	
				hql.append(" and str_to_date(CONCAT(s.year,s.month),'%Y%m') >= str_to_date(CONCAT("+entity.getStartyear()+","+entity.getStartmonth()+"),'%Y%m') ");
			}
			if(Common.notEmpty(entity.getEndtime()))
			{	
				hql.append(" and str_to_date(CONCAT(s.year,s.month),'%Y%m') <= str_to_date(CONCAT("+entity.getEndyear()+","+entity.getEndmonth()+"),'%Y%m') ");
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and s.status = "+entity.getStatus()+ " ");
			}
			if(entity.getId()!=null)
			{
				hql.append(" and s.id = "+entity.getId()+ " ");
			}
			if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
			{
				hql.append(" and s.status != "+Base.examstatus_1+ " ");
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						hql.append(" s.authOrg like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" s.authOrg like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and s.authOrg like '%--%' ");
			}
//			hql.append(" and s.parentorg like '%-" + entity.getParentorg() + "-%' ");
		}
		hql.append(" order by s.year desc , s.month desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}
	//查询导出数据
	@Override
	public List<ReportManageAdjust> getExportReportManageAdjustListView(ReportManageAdjust entity) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportManageAdjust s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+Common.dealinStr(entity.getOrg())+ ") ");
			}
			if(Common.notEmpty(entity.getStarttime()))
			{	
				hql.append(" and str_to_date(CONCAT(s.year,s.month),'%Y%m') >= str_to_date(CONCAT("+entity.getStartyear()+","+entity.getStartmonth()+"),'%Y%m') ");
			}
			if(Common.notEmpty(entity.getEndtime()))
			{	
				hql.append(" and str_to_date(CONCAT(s.year,s.month),'%Y%m') <= str_to_date(CONCAT("+entity.getEndyear()+","+entity.getEndmonth()+"),'%Y%m') ");
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and s.status = "+entity.getStatus()+ " ");
			}
			if(entity.getId()!=null)
			{
				hql.append(" and s.id = "+entity.getId()+ " ");
			}
			if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
			{
				hql.append(" and s.status != "+Base.examstatus_1+ " ");
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						hql.append(" s.authOrg like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" s.authOrg like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and s.authOrg like '%--%' ");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
	    //管理口径核算数据审核导出数据
		@Override
		public List<ReportManageAdjust> getExportReportManageAdjustExamineListView(ReportManageAdjust entity) {
			// TODO Auto-generated method stub
			StringBuilder  hql = new StringBuilder();
			hql.append(" from ReportManageAdjust s where 1=1 and isdel=0 ");
			if(entity!=null)
			{
				if(Common.notEmpty(entity.getOrg()) )
				{
					hql.append(" and s.org in( "+Common.dealinStr(entity.getOrg())+ ") ");
				}
				if(Common.notEmpty(entity.getStarttime()))
				{	
					hql.append(" and str_to_date(CONCAT(s.year,s.month),'%Y%m') >= str_to_date(CONCAT("+entity.getStartyear()+","+entity.getStartmonth()+"),'%Y%m') ");
				}
				if(Common.notEmpty(entity.getEndtime()))
				{	
					hql.append(" and str_to_date(CONCAT(s.year,s.month),'%Y%m') <= str_to_date(CONCAT("+entity.getEndyear()+","+entity.getEndmonth()+"),'%Y%m') ");
				}
				if(entity.getStatus()!=null)
				{
					hql.append(" and s.status = "+entity.getStatus()+ " ");
				}
				if(entity.getId()!=null)
				{
					hql.append(" and s.id = "+entity.getId()+ " ");
				}
				if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
				{
					hql.append(" and s.status != "+Base.examstatus_1+ " ");
				}
			}
			hql.append(" order by s.year desc , s.month desc ");
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			return query.list();
		}
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getReportManageAdjustListCount(ReportManageAdjust entity)
	{
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportManageAdjust s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+Common.dealinStr(entity.getOrg())+ ") ");
			}
			/*if(entity.getFredata()!=null)
			{
				hql.append(" and s.fredata = "+entity.getFredata()+ " ");
			}
			if(entity.getFre()!=null)
			{
				hql.append(" and s.fre = "+entity.getFre()+ " ");
			}
			if(entity.getYear()!=null)
			{
				hql.append(" and s.year = "+entity.getYear()+ " ");
			}*/
			if(Common.notEmpty(entity.getStarttime()))
			{	
				hql.append(" and str_to_date(CONCAT(s.year,s.month),'%Y%m') >= str_to_date(CONCAT("+entity.getStartyear()+","+entity.getStartmonth()+"),'%Y%m') ");
			}
			if(Common.notEmpty(entity.getEndtime()))
			{	
				hql.append(" and str_to_date(CONCAT(s.year,s.month),'%Y%m') <= str_to_date(CONCAT("+entity.getEndyear()+","+entity.getEndmonth()+"),'%Y%m') ");
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and s.status = "+entity.getStatus()+ " ");
			}
			if(entity.getId()!=null)
			{
				hql.append(" and s.id = "+entity.getId()+ " ");
			}
			if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
			{
				hql.append(" and s.status != "+Base.examstatus_1+ " ");
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						hql.append(" s.authOrg like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" s.authOrg like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and s.authOrg like '%--%' ");
			}
//			hql.append(" and s.parentorg like '%-" + entity.getParentorg() + "-%' ");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveReportManageAdjustRepeatCheck(ReportManageAdjust entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportManageAdjust s where 1=1 and isdel=0  ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()))
			{
				hql.append(" and s.org = '"+entity.getOrg()+ "' ");
			}
			/*if(entity.getFredata()!=null)
			{
				hql.append(" and s.fredata = "+entity.getFredata()+ " ");
			}
			if(entity.getFre()!=null)
			{
				hql.append(" and s.fre = "+entity.getFre()+ " ");
			}*/
			if(entity.getMonth()!=null)
			{
				hql.append(" and s.month = "+entity.getMonth()+ " ");
			}
			
			if(entity.getYear()!=null)
			{
				hql.append(" and s.year = "+entity.getYear()+ " ");
			}
		
			if(entity.getId()!=null)
			{
				hql.append(" and s.id != "+entity.getId()+ " ");
			}
			
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		Integer a= Integer.parseInt(query.uniqueResult().toString());
		if(a!=null && a.equals(0))
			return true;
		else
			return false;
	}
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(ReportManageAdjust entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportManageAdjust s where 1=1 and isdel=0 and status in("+Base.examCanEdit+") ");
		if(entity!=null)
		{
			
			if(entity.getId()!=null)
			{
				hql.append(" and id ="+entity.getId()  +"");
			}
			else
				return true;
		}
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		Integer a= Integer.parseInt(query.uniqueResult().toString());
		if(a!=null && a.equals(0))
			return false;
		else
			return true;
	}

	@Override
	public ReportManageAdjust getBeginningData(String org, Integer year) {
		// TODO Auto-generated method stub
		String hql = " from ReportManageAdjust r where r.org = '" + org + "' and r.year = " + (year-1) + " and r.month = 12 and r.status = 50115 and r.isdel = 0 ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (ReportManageAdjust)query.uniqueResult();
	}

	
	
}
