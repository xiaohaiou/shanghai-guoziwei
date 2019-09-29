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
import com.softline.dao.report.IReportKeyWorkDao;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.ReportKeywork;

@Repository(value = "reportKeyWorkDao")
public class ReportKeyWorkDao implements IReportKeyWorkDao{
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int getKeyWorkCount(ReportKeywork entity) {
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportKeywork s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(entity.getYear() !=null)
			{
				hql.append(" and s.year ="+entity.getYear()+" ");
			}
			if(Common.notEmpty(entity.getCoreOrg()) )
			{
				hql.append(" and s.coreOrg in( "+Common.dealinStr(entity.getCoreOrg())+ ") ");
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
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<ReportKeywork> getKeyWork(ReportKeywork entity,
			Integer offsize, Integer pagesize) {
		if(entity==null)
			return new ArrayList<ReportKeywork>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportKeywork s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(entity.getYear() !=null)
			{
				hql.append(" and s.year ="+entity.getYear()+" ");
			}
			if(Common.notEmpty(entity.getCoreOrg()) )
			{
				hql.append(" and s.coreOrg in( "+Common.dealinStr(entity.getCoreOrg())+ ") ");
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
			hql.append(" order by s.year desc ");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}

	
	@Override
	public ReportKeywork getKeyworkbyID(Integer id) {
		String hql = " from ReportKeywork a where a.isdel=0 and a.id ="+id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (ReportKeywork) query.uniqueResult();
	}
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveKeyworkCheck(ReportKeywork entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportKeywork a where a.isdel=0  ");
		if(entity!=null)
		{
			if(entity.getYear() !=null && Common.notEmpty(entity.getCoreOrg()) && entity.getIndextype() != null)
			{
				hql.append(" and a.year ="+entity.getYear()+"   ");
				hql.append(" and a.coreOrg in( "+Common.dealinStr(entity.getCoreOrg())+ ") ");
				hql.append(" and a.indextype="+entity.getIndextype()+" ");
			}
			//检查编辑保存的时候 除自己外是否存在相同记录
			if(entity.getId()!=null)
			{
				hql.append(" and a.id !='"+entity.getId()+"' ");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		Integer a= Integer.parseInt(query.uniqueResult().toString());
		if(a!=null && a.equals(0))
			return true;//不存在相同记录
		else
			return false;//存在相同记录
	}

	/**
	 * 保存上报或者上报时校验方法
	 */
	@Override
	public boolean reportKeyworkCheck(ReportKeywork entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportKeywork a where a.isdel=0 and a.status in ('50113','50115','50112','50114') ");
		if(entity!=null)
		{
			if(entity.getYear() !=null && Common.notEmpty(entity.getCoreOrg()) && entity.getIndextype() != null)
			{
				hql.append(" and a.year ="+entity.getYear()+"   ");
				hql.append(" and a.coreOrg in( "+Common.dealinStr(entity.getCoreOrg())+ ") ");
				hql.append(" and a.indextype="+entity.getIndextype()+" ");
			}
			if(entity.getId()!=null)
			{
				hql.append(" and a.id !='"+entity.getId()+"' ");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		Integer a= Integer.parseInt(query.uniqueResult().toString());
		if(a!=null && a.equals(0))
			return true;
		else
			return false;
	}

	@Override
	public boolean checkCanEdit(ReportKeywork entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportKeywork s where 1=1 and isdel=0 and status in("+Base.examCanEdit+") ");
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
	
}
