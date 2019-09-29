package com.softline.dao.hr.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.hr.IManagerialStaffDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entity.DataHrManagerialStaff;

@Repository(value = "managerialStaffDao")
public class ManagerialStaffDao implements IManagerialStaffDao{
	
	private static final DataHrManagerialStaff List = null;

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public Integer getHrFormsListViewCount(DataHrManagerialStaff entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select count(0) ");
		hql.append(" from data_hr_managerialStaff a where a.isdel=0 ");
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

	public List<DataHrManagerialStaff> getHrFormsListView(DataHrManagerialStaff entity, Integer offset,Integer pageSize) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select a.id,a.year,a.month,a.company,a.status,a.createPersonName,a.reportPersonName,a.auditorPersonName,a.organal_id,a.isdel, "); 
		hql.append(" a.man_number,a.woman_number,a.sequence_m,a.un_sequence_m,a.createDate,a.reportDate,a.auditorDate,a.createPersonID,a.reportPersonID,a.auditorPersonID ");
		hql.append(" from data_hr_managerialStaff a where a.isdel=0 "); 		
		
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
			if(Common.notEmpty(entity.getOrganalId()))
			{
				hql.append(" and a.organal_id in( "+entity.getOrganalId()+ ") ");
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and a.status = '"+entity.getStatus()+ "' ");
			}
		}
		
		hql.append(" order by a.year desc,a.month desc");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		if(offset!=null)
			query.setFirstResult(offset);
		if(pageSize!=null)
			query.setMaxResults(pageSize);
		List a= query.list();
		return getDataHrManagerialStaff(a);
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public List<DataHrManagerialStaff> getDataHrManagerialStaff(List list){
		List<DataHrManagerialStaff> returnData=new ArrayList<DataHrManagerialStaff>();
		for (Object object : list) {
			int i=0;
			DataHrManagerialStaff dh=new DataHrManagerialStaff();
			Object[] row= (Object[]) object;
			dh.setId(row[i]==null ? 0: Integer.parseInt(row[i].toString()));i++;
			dh.setYear(row[i]==null ? 0: Integer.parseInt(row[i].toString()));i++;
			dh.setMonth(row[i]==null ? 0: Integer.parseInt(row[i].toString()));i++;
			dh.setCompany(row[i]==null ?"":row[i].toString());i++;
			dh.setStatus(row[i]==null ? 0: Integer.parseInt(row[i].toString()));i++;
			dh.setCreatePersonName(row[i]==null ?"": row[i].toString());i++;
			dh.setReportPersonName(row[i]==null ?"": row[i].toString());i++;
			dh.setAuditorPersonName(row[i]==null ?"": row[i].toString());i++;
			dh.setOrganalId(row[i]==null ?"": row[i].toString());i++;
			dh.setIsdel(row[i]==null ? 0: Integer.parseInt(row[i].toString()));i++;
			dh.setManNumber(row[i]==null ? 0: Integer.parseInt(row[i].toString()));i++;
			dh.setWomanNumber(row[i]==null ? 0: Integer.parseInt(row[i].toString()));i++;
			dh.setSequenceM(row[i]==null ? 0: Integer.parseInt(row[i].toString()));i++;
			dh.setUnSequenceM(row[i]==null ? 0: Integer.parseInt(row[i].toString()));i++;
			dh.setCreateDate(row[i]==null ?"":row[i].toString());i++;
			dh.setReportDate(row[i]==null ?"":row[i].toString());i++;
			dh.setAuditorDate(row[i]==null ?"":row[i].toString());i++;
			returnData.add(dh);
		}
		return returnData;
	}
	
	
	public DataHrManagerialStaff get(Integer id) {
		String hql = " from DataHrManagerialStaff a where a.isdel=0 and a.id ="+id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (DataHrManagerialStaff) query.uniqueResult();
	}
	
	public boolean get(DataHrManagerialStaff entity) {
		boolean b=false;
		String hql = " from DataHrManagerialStaff a where a.isdel=0 and a.id ="+entity.getId();
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
	public boolean saveDataHrManagerialStaffRepeatCheck(DataHrManagerialStaff entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from DataHrManagerialStaff a where a.isdel=0  ");
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
	public boolean repeatDataHrManagerialStaffCheck(DataHrManagerialStaff entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from DataHrManagerialStaff a where a.isdel=0 and a.status in ('50113','50115','50112','50114') ");
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
	public boolean repeatDataCheck(DataHrManagerialStaff entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from DataHrManagerialStaff a where a.isdel=0 and a.status in ('50113','50115','50112','50114') ");
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
	public Integer getExamineHrFormsListViewCount(DataHrManagerialStaff entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select count(0) ");
		hql.append(" from data_hr_managerialStaff a where a.isdel=0 and a.status!=50112 ");
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
			if(Common.notEmpty(entity.getOrganalId()))
			{
				hql.append(" and a.organal_id in( "+entity.getOrganalId()+ ") ");
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
	public java.util.List<DataHrManagerialStaff> getExamineHrFormsListView(
			DataHrManagerialStaff entity, Integer offset, Integer pageSize) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select a.id,a.year,a.month,a.company,a.status,a.createPersonName,a.reportPersonName,a.auditorPersonName,a.organal_id,a.isdel, "); 
		hql.append(" a.man_number,a.woman_number,a.sequence_m,a.un_sequence_m,a.createDate,a.reportDate,a.auditorDate,a.createPersonID,a.reportPersonID,a.auditorPersonID ");
		hql.append(" from data_hr_managerialStaff a where a.isdel=0 and a.status!=50112"); 		
		
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
			if(Common.notEmpty(entity.getOrganalId()))
			{
				hql.append(" and a.organal_id in( "+entity.getOrganalId()+ ") ");
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and a.status = '"+entity.getStatus()+ "' ");
			}
			if(entity.getId() != null){
				hql.append(" and a.id = " + entity.getId());
			}
		}
		
		hql.append(" order by a.year,a.month desc");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		if(offset!=null)
			query.setFirstResult(offset);
		if(pageSize!=null)
			query.setMaxResults(pageSize);
		List a= query.list();
		return getDataHrManagerialStaff(a);
	}
	
	public List getOverviewIndustry(String year,String month) {
		StringBuilder hql = new StringBuilder();
		hql.append("select org.nNodeID from hh_organInfo org ");
		hql.append("where org.cLevel <= 3 and org.nNodeID not in ( ");
		hql.append("select doc.organal_id from data_hr_managerialStaff doc where doc.isdel = 0 and doc.year = " + year + " and doc.month = " + month);
		hql.append(")");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return query.list();
	}
}
