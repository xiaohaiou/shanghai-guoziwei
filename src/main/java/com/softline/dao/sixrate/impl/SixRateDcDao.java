package com.softline.dao.sixrate.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.sixrate.ISixRateDcDao;
import com.softline.entity.DataSixRateDc;

@Repository(value = "sixRateDcDao")
public class SixRateDcDao implements ISixRateDcDao{
	
	/*private static final DataHrManagerialStaff List = null;

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;*/
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public Integer getHrFormsListViewCount(DataSixRateDc entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select count(0) ");
		hql.append(" from data_six_rate_dc a where a.isdel=0 ");
		if(entity!=null)
		{
			if(entity.getYear()!=null)
			{
				hql.append(" and a.year = "+entity.getYear()+ " ");
			}
			if(entity.getMonth()!=null)
			{
				hql.append(" and a.month = "+entity.getMonth()+ " ");
			}
			if(Common.notEmpty(entity.getCompany()))
			{
				hql.append(" and a.company = '"+entity.getCompany()+ "' ");
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and a.status = '"+entity.getStatus()+ "' ");
			}
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	public List<DataSixRateDc> getHrFormsListView(DataSixRateDc entity, Integer offset,Integer pageSize) {
		if(entity==null)
			return new ArrayList<DataSixRateDc>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from DataSixRateDc d where 1=1 and isdel=0 ");
		
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrganalId()))
			{
				hql.append(" and d.organalId in ("+entity.getOrganalId()+ ") ");
			}
			if(entity.getYear() != null && !"".equals(entity.getYear()))
			{
				hql.append(" and d.year = "+entity.getYear());
			}
			if(entity.getStatus() !=null && !"".equals(entity.getStatus()))
			{
				hql.append(" and d.status = "+entity.getStatus());
			}
			if(entity.getMonth() !=null && !"".equals(entity.getMonth()))
			{
				hql.append(" and d.month = "+entity.getMonth());
			}
		}
		hql.append(" order by year desc , month desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offset!=null)
			query.setFirstResult(offset);
		if(pageSize!=null)
			query.setMaxResults(pageSize);
		return query.list();
	}
	
	
	public DataSixRateDc get(Integer id) {
		String hql = " from DataSixRateDc a where a.isdel=0 and a.id ="+id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (DataSixRateDc) query.uniqueResult();
	}
	
	public boolean get(DataSixRateDc entity) {
		boolean b=false;
		String hql = " from DataSixRateDc a where a.isdel=0 and a.id ="+entity.getId();
		if(entity.getStatus()!=null&&(entity.getStatus()==50112||entity.getStatus()==50114)){
			hql+=" and a.status="+entity.getStatus();
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(query.uniqueResult()!=null){
			b=true;
		}else{}
		return b;
	}
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveDataSixRateDcCheck(DataSixRateDc entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from DataSixRateDc a where a.isdel=0  ");
		if(entity!=null)
		{
			if(entity.getYear()!=null)
			{
				hql.append(" and a.year = "+entity.getYear()+ " ");
			}
			if(entity.getMonth()!=null)
			{
				hql.append(" and a.month = "+entity.getMonth()+ " ");
			}
			if(Common.notEmpty(entity.getCompany()))
			{
				hql.append(" and a.company = '"+entity.getCompany()+ "' ");
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

	/**
	 * 保存上报时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean repeatDataSixRateDcCheck(DataSixRateDc entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from DataSixRateDc a where a.isdel=0 and a.status in ('50113','50115','50112','50114') ");
		if(entity!=null)
		{
			if(entity.getYear()!=null)
			{
				hql.append(" and a.year = "+entity.getYear()+ " ");
			}
			if(entity.getMonth()!=null)
			{
				hql.append(" and a.month = "+entity.getMonth()+ " ");
			}
			if(Common.notEmpty(entity.getCompany()))
			{
				hql.append(" and a.company = '"+entity.getCompany()+ "' ");
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
	/**
	 * 上报时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean repeatDataCheck(DataSixRateDc entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from DataSixRateDc a where a.isdel=0 and a.status in ('50113','50115','50112','50114') ");
		if(entity!=null)
		{
			if(entity.getYear()!=null)
			{
				hql.append(" and a.year = "+entity.getYear()+ " ");
			}
			if(entity.getMonth()!=null)
			{
				hql.append(" and a.month = "+entity.getMonth()+ " ");
			}
			if(Common.notEmpty(entity.getCompany()))
			{
				hql.append(" and a.company = '"+entity.getCompany()+ "' ");
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
	public Integer getExamineHrFormsListViewCount(DataSixRateDc entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select count(0) ");
		hql.append(" from data_six_rate_dc a where a.isdel=0 and a.status!=50112 ");
		if(entity!=null)
		{
			if(entity.getYear()!=null)
			{
				hql.append(" and a.year = "+entity.getYear()+ " ");
			}
			if(entity.getMonth()!=null)
			{
				hql.append(" and a.month = "+entity.getMonth()+ " ");
			}
			if(Common.notEmpty(entity.getCompany()))
			{
				hql.append(" and a.company = '"+entity.getCompany()+ "' ");
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and a.status = '"+entity.getStatus()+ "' ");
			}
			if(entity.getId() != null){
				hql.append(" and a.id = " + entity.getId());
			}
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public java.util.List<DataSixRateDc> getExamineHrFormsListView(
			DataSixRateDc entity, Integer offset, Integer pageSize) {
		if(entity==null)
			return new ArrayList<DataSixRateDc>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from DataSixRateDc d where 1=1 and isdel=0  and d.status!=50112");
		
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrganalId()))
			{
				hql.append(" and d.organalId in ("+entity.getOrganalId()+ ") ");
			}
			if(entity.getYear() != null && !"".equals(entity.getYear()))
			{
				hql.append(" and d.year = "+entity.getYear());
			}
			if(entity.getStatus() !=null && !"".equals(entity.getStatus()))
			{
				hql.append(" and d.status = "+entity.getStatus());
			}
			if(entity.getMonth() !=null && !"".equals(entity.getMonth()))
			{
				hql.append(" and d.month = "+entity.getMonth());
			}
			if(entity.getId() != null){
				hql.append(" and d.id = " + entity.getId());
			}
		}
		hql.append(" order by year desc , month desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offset!=null)
			query.setFirstResult(offset);
		if(pageSize!=null)
			query.setMaxResults(pageSize);
		return query.list();
	}

}
