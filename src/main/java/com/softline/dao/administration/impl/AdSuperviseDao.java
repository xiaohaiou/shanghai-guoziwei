package com.softline.dao.administration.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.administration.IAdSuperviseDao;
import com.softline.entity.AdSupervise;
import com.softline.entity.Purchase;
import com.softline.entity.ReportFormsGroup;
@Repository("adSuperviseDao")
public class AdSuperviseDao implements IAdSuperviseDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public Integer getSuperviseListCount(AdSupervise entity, Integer fun,String audiorDateStart,
			String audiorDateEnd,String reportDateStart,String reportDateEnd) {
		// TODO Auto-generated method stub
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from AdSupervise s where 1=1 and isdel=0 ");
		if (fun.equals(Base.fun_examine)) {
			hql.append(" and s.status != 50112 ");
		}
		if(entity!=null)
		{
			if(audiorDateStart != null && !audiorDateStart.equals("")&&audiorDateEnd != null && !audiorDateEnd.equals(""))
			{
				hql.append("and auditorDate BETWEEN '"+audiorDateStart+"' And '"+audiorDateEnd+"' ");
			}
//			上报
			if(reportDateStart != null && !reportDateStart.equals("")&&reportDateEnd != null && !reportDateEnd.equals(""))
			{
				hql.append("and reportDate BETWEEN '"+reportDateStart+"' And '"+reportDateEnd+"' ");
			}
			if(Common.notEmpty(entity.getCompId()))
			{
				hql.append(" and s.compId in ("+entity.getCompId()+ ") ");
			}
			if(entity.getYear() != null && !"".equals(entity.getYear()))
			{
				hql.append(" and s.year = "+entity.getYear());
			}
			if(entity.getStatus() !=null && !"".equals(entity.getStatus()))
			{
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(entity.getId() != null){
				hql.append(" and s.id = "+entity.getId());
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<AdSupervise> getSuperviseList(AdSupervise entity,
			Integer offset, Integer pageSize, Integer fun,
			String audiorDateStart, String audiorDateEnd,String reportDateStart,String reportDateEnd) {
		if(entity==null)
			return new ArrayList<AdSupervise>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from AdSupervise s where 1=1 and isdel=0 ");
		if (fun.equals(Base.fun_examine)) {
			hql.append(" and s.status != 50112 ");
		}
		if(entity!=null)
		{
			if(audiorDateStart != null && !audiorDateStart.equals("")&&audiorDateEnd != null && !audiorDateEnd.equals(""))
			{
				hql.append("and auditorDate BETWEEN '"+audiorDateStart+"' And '"+audiorDateEnd+"' ");
			}
//			上报
			if(reportDateStart != null && !reportDateStart.equals("")&&reportDateEnd != null && !reportDateEnd.equals(""))
			{
				hql.append("and reportDate BETWEEN '"+reportDateStart+"' And '"+reportDateEnd+"' ");
			}
			if(Common.notEmpty(entity.getCompId()))
			{
				hql.append(" and s.compId in ("+entity.getCompId()+ ") ");
			}
			if(entity.getYear() != null && !"".equals(entity.getYear()))
			{
				hql.append(" and s.year = "+entity.getYear());
			}
			if(entity.getStatus() !=null && !"".equals(entity.getStatus()))
			{
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(entity.getId() != null){
				hql.append(" and s.id = "+entity.getId());
			}
			
		}
		hql.append(" order by year desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offset!=null)
			query.setFirstResult(offset);
		if(pageSize!=null)
			query.setMaxResults(pageSize);
		return query.list();
		
	}
	
//	public List<AdSupervise> getSuperviseList(AdSupervise entity,
//			Integer offset, Integer pageSize, Integer fun) {
//		// TODO Auto-generated method stub
//		if(entity==null)
//			return new ArrayList<AdSupervise>();
//		StringBuilder  hql = new StringBuilder();
//		hql.append("from AdSupervise s where 1=1 and isdel=0 ");
//		if (fun.equals(Base.fun_examine)) {
//			hql.append(" and s.status != 50112 ");
//		}
//		if(entity!=null)
//		{
//			if(Common.notEmpty(entity.getCompId()))
//			{
//				hql.append(" and s.compId in ("+entity.getCompId()+ ") ");
//			}
//			if(entity.getYear() != null && !"".equals(entity.getYear()))
//			{
//				hql.append(" and s.year = "+entity.getYear());
//			}
//			if(entity.getStatus() !=null && !"".equals(entity.getStatus()))
//			{
//				hql.append(" and s.status = "+entity.getStatus());
//			}
//			if (Common.notEmpty(entity.getAuditorDate()) ) {
//				hql.append(" and s.auditorDate >= '" + entity.getAuditorDate() + "' ");
//			}
//			/*
//			if (Common.notEmpty(entity.getAuditorEndDate())) {
//				hql.append(" and s.auditorEndDate <= '" + entity.getAuditorEndDate() + "' ");
//			}*/
//		}
//		hql.append(" order by year desc ");
//		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
//		if(offset!=null)
//			query.setFirstResult(offset);
//		if(pageSize!=null)
//			query.setMaxResults(pageSize);
//		return query.list();
//	}

	@Override
	public AdSupervise getSupervise(AdSupervise entity) {
		// TODO Auto-generated method stub
		String hql = " from AdSupervise s where isdel = 0 and s.id = " + entity.getId();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (AdSupervise)query.uniqueResult();
	}

	@Override
	public boolean saveSuperviseCheck(AdSupervise entity) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from AdSupervise s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(entity.getYear()!=null)
			{
				hql.append(" and s.year = "+entity.getYear()+ " ");
			}
			if(entity.getId()!=null)
			{
				hql.append(" and s.id !="+entity.getId()  +"");
			}
			if(entity.getCompId()!=null)
			{
				hql.append(" and s.compId ="+entity.getCompId()  +"");
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
	public AdSupervise getSupervise(Integer id) {
		// TODO Auto-generated method stub
		String hql = " from AdSupervise s where s.isdel = 0 and s.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (AdSupervise)query.uniqueResult();
	}

	@Override
	public boolean theTimeSuperviseCheck(AdSupervise entity) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("select lastModifyDate,isdel from AdSupervise s where 1=1 ");
		if(entity!=null)
		{
			if(entity.getId()!=null)
			{
				hql.append(" and s.id = " + entity.getId() +"");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		Object[] dateAndStatus = (Object[])query.uniqueResult();
		String newModifyDate = dateAndStatus[0] == null?"":dateAndStatus[0].toString();
		String newStatus = dateAndStatus[1].toString();
		String oldModifyDate = entity.getLastModifyDate() == null?"":entity.getLastModifyDate();
		if(newStatus.equals("0")) {
			if (!"".equals(newModifyDate)) {
				if(oldModifyDate.equals(newModifyDate)) {
					return true;
				}else {
					return false;
				}
			}else {
				if("".equals(oldModifyDate)) {
					return true;
				}else {
					return false;
				}
			}
		}else {
			return false;
		}
	}
	
	@Override
	public List<String> getSuperviseList(String year, String month){
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT vcOrganID FROM `v_organInfo`");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<AdSupervise> getSuperviseExistList(String year) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder();
		hql.append("from AdSupervise where isdel = 0 and year = " + year);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}


}
