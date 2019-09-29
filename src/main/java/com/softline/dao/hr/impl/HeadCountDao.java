package com.softline.dao.hr.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.hr.IHeadCountDao;
import com.softline.entityTemp.HeadCountLaborProduction;

@Repository(value = "headCountDao")
public class HeadCountDao implements IHeadCountDao{
	
	/*private static final HeadCountLaborProduction List = null;

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;*/
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public Integer getHrFormsListViewCount(HeadCountLaborProduction hr) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select count(0) ");
		hql.append(" from data_hr_headcount a where a.isdel=0 ");
		if(hr!=null)
		{
			if(hr.getYear()!=null)
			{
				hql.append(" and a.year = "+hr.getYear()+ " ");
			}
			if(hr.getMonth()!=null)
			{
				hql.append(" and a.month = "+hr.getMonth()+ " ");
			}
			if(Common.notEmpty(hr.getOrganalId()))
			{
				hql.append(" and a.organal_id in( "+hr.getOrganalId()+ ") ");
			}
			if(hr.getStatus()!=null)
			{
				hql.append(" and a.status = '"+hr.getStatus()+ "' ");
			}
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	public List<HeadCountLaborProduction> getHrFormsListView(HeadCountLaborProduction hr, Integer offset,Integer pageSize) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select a.id,a.year,a.month,a.company,a.workers_number,a.tworkers_number,b.average_income,b.average_profit,b.business_income,b.business_profit, "); 
		hql.append(" a.status,a.createPersonName,a.reportPersonName,a.auditorPersonName,a.organal_id,a.foreignKey,a.isdel,a.createDate,a.reportDate,a.auditorDate,a.createPersonID,a.reportPersonID,a.auditorPersonID,b.id as bid ");
		hql.append(" from data_hr_headcount a inner join data_hr_laborproduction b on  a.foreignKey=b.foreignKey where a.isdel=0 and b.isdel=0 "); 		
		
		if(hr!=null)
		{
			if(hr.getYear()!=null)
			{
				hql.append(" and a.year = "+hr.getYear()+ " ");
			}
			if(hr.getMonth()!=null)
			{
				hql.append(" and a.month = "+hr.getMonth()+ " ");
			}
			if(Common.notEmpty(hr.getOrganalId()))
			{
				hql.append(" and a.organal_id in( "+hr.getOrganalId()+ ") ");
			}
			if(hr.getStatus()!=null)
			{
				hql.append(" and a.status = '"+hr.getStatus()+ "' ");
			}
		}

		hql.append(" order by a.year desc,a.month desc,a.createDate desc");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		if(offset!=null)
			query.setFirstResult(offset);
		if(pageSize!=null)
			query.setMaxResults(pageSize);
		List a= query.list();
		return getHeadCountLaborProduction(a);
	}
	
	
	@Override
	public List<HeadCountLaborProduction> getHrFormsListCollectView(
			HeadCountLaborProduction hr) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("select a.id,a.year,a.month,c.vcFullName,SUM(a.workers_number),SUM(a.tworkers_number),AVG(b.average_income),AVG(b.average_profit),SUM(b.business_income),SUM(b.business_profit),");
		hql.append(" a.status,a.createPersonName,a.reportPersonName,a.auditorPersonName,c.nNodeID,a.foreignKey,a.isdel,a.createDate,a.reportDate,a.auditorDate,a.createPersonID,a.reportPersonID,a.auditorPersonID,b.id as bid ");
		hql.append(" from data_hr_headcount a inner join data_hr_laborproduction b on  a.foreignKey=b.foreignKey inner join hh_organInfo c on c.nNodeID="+hr.getOrganalId()+" where a.isdel=0 and b.isdel=0" ); 		
		if(hr.getYear()!=null)
		{
			hql.append(" and a.year = "+hr.getYear()+ " ");
		}
		if(hr.getMonth()!=null)
		{
			hql.append(" and a.month = "+hr.getMonth()+ " ");
		}
		if(Common.notEmpty(hr.getOrganalId()))
		{
			//(select h.nNodeID from (SELECT vcOrganID from hh_organinfo where nNodeID = "+hr.getOrganalId()+") a,hh_organinfo h where h.vcOrganID like concat(a.vcOrganID,'%'))
			hql.append(" and a.organal_id IN (select h.nNodeID from (SELECT vcOrganID from hh_organInfo where nNodeID = "+hr.getOrganalId()+") a,hh_organInfo h where h.vcParentID = a.vcOrganID) ");
		}
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		
		List a= query.list();
		
		return getHeadCountLaborProduction(a);
	}
	/**
	 * 
	 * @param list
	 * @return
	 */
	public List<HeadCountLaborProduction> getHeadCountLaborProduction(List list){
		List<HeadCountLaborProduction> returnData=new ArrayList<HeadCountLaborProduction>();
		for (Object object : list) {
			int i=0;
			HeadCountLaborProduction dh=new HeadCountLaborProduction();
			Object[] row= (Object[]) object;
			dh.setId(row[i]==null ? 0: Integer.parseInt(row[i].toString()));i++;
			dh.setYear(row[i]==null ? 0: Integer.parseInt(row[i].toString()));i++;
			dh.setMonth(row[i]==null ? 0: Integer.parseInt(row[i].toString()));i++;
			dh.setCompany(row[i]==null ?"":row[i].toString());i++;
			dh.setWorkersNumber(row[i]==null ? 0: Integer.parseInt(row[i].toString()));i++;
			dh.setTworkersNumber(row[i]==null ? 0: Integer.parseInt(row[i].toString()));i++;
			dh.setAverageIncome(row[i]==null ? "": row[i].toString());i++;
			dh.setAverageProfit(row[i]==null ? "": row[i].toString());i++;
			dh.setBusinessIncome(row[i]==null ? "": row[i].toString());i++;
			dh.setBusinessProfit(row[i]==null ? "":  row[i].toString());i++;
			dh.setStatus(row[i]==null ? 0: Integer.parseInt(row[i].toString()));i++;
			dh.setCreatePersonName(row[i]==null ?"": row[i].toString());i++;
			dh.setReportPersonName(row[i]==null ?"": row[i].toString());i++;
			dh.setAuditorPersonName(row[i]==null ?"": row[i].toString());i++;
			dh.setOrganalId(row[i]==null ?"": row[i].toString());i++;
			dh.setForeignKey(row[i]==null ?"": row[i].toString());i++;
			dh.setIsdel(row[i]==null ? 0: Integer.parseInt(row[i].toString()));i++;
			dh.setCreateDate(row[i]==null ?"":row[i].toString());i++;
			dh.setReportDate(row[i]==null ?"":row[i].toString());i++;
			dh.setAuditorDate(row[i]==null ?"":row[i].toString());i++;
			dh.setCreatePersonId(row[i]==null ?"":row[i].toString());i++;
			dh.setReportPersonId(row[i]==null ?"":row[i].toString());i++;
			dh.setAuditorPersonId(row[i]==null ?"":row[i].toString());i++;
			dh.setBid(row[i]==null ? 0: Integer.parseInt(row[i].toString()));i++;
			returnData.add(dh);
		}
	
		return returnData;
	}
	
	
	public HeadCountLaborProduction get(Integer id) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select a.id,a.year,a.month,a.company,a.workers_number,a.tworkers_number,b.average_income,b.average_profit,b.business_income,b.business_profit, "); 
		hql.append(" a.status,a.createPersonName,a.reportPersonName,a.auditorPersonName,a.organal_id,a.foreignKey,a.isdel,a.createDate,a.reportDate,a.auditorDate,a.createPersonID,a.reportPersonID,a.auditorPersonID,b.id as bid ");
		hql.append(" from data_hr_headcount a inner join data_hr_laborproduction b where a.foreignKey=b.foreignKey and a.isdel=0 and b.isdel=0 and a.id ="+id);
		HeadCountLaborProduction entity = new HeadCountLaborProduction();
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<HeadCountLaborProduction> tempList = getHeadCountLaborProduction(a);
		for(HeadCountLaborProduction h:tempList){
			if(h!=null){
				entity=h;
			}else{
			}
		}
		return entity;
	}
	
	public boolean get(HeadCountLaborProduction entity) {
		boolean b=false;
		StringBuilder  hql = new StringBuilder();
		hql.append(" select a.id,a.year,a.month,a.company,a.workers_number,a.tworkers_number,b.average_income,b.average_profit,b.business_income,b.business_profit, "); 
		hql.append(" a.status,a.createPersonName,a.reportPersonName,a.auditorPersonName,a.organal_id,a.foreignKey,a.isdel,a.createDate,a.reportDate,a.auditorDate,a.createPersonID,a.reportPersonID,a.auditorPersonID,b.id as bid ");
		hql.append(" from data_hr_headcount a inner join data_hr_laborproduction b where a.foreignKey=b.foreignKey and a.isdel=0 and b.isdel=0 and a.id ="+entity.getId());
		if(entity.getStatus()!=null&&(entity.getStatus()==50112||entity.getStatus()==50114)){
			hql.append(" and a.status="+entity.getStatus());
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<HeadCountLaborProduction> tempList = getHeadCountLaborProduction(a);
		for(HeadCountLaborProduction h:tempList){
			if(h!=null){
				b=true;
			}else{
			}
		}
		return b;
	}
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveHeadCountLaborProductionRepeatCheck(HeadCountLaborProduction hr)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from data_hr_headcount a where a.isdel=0  ");
		if(hr!=null)
		{
			if(hr.getYear()!=null)
			{
				hql.append(" and a.year = "+hr.getYear()+ " ");
			}
			if(hr.getMonth()!=null)
			{
				hql.append(" and a.month = "+hr.getMonth()+ " ");
			}
			if(Common.notEmpty(hr.getCompany()))
			{
				hql.append(" and a.company = '"+hr.getCompany()+ "' ");
			}
			if(hr.getId()!=null)
			{
				hql.append(" and a.id !='"+hr.getId()+"' ");
			}
		}
		//Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		Integer a= Integer.parseInt(query.uniqueResult().toString());
		if(a!=null && a.equals(0))
			return true;
		else
			return false;
	}
	
	//保存上报校验
	@Override
	public boolean reporHeadCountLaborProductionRepeatCheck(HeadCountLaborProduction hr) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from DataHrHeadcount a where a.isdel=0 and a.status in ('50113','50115','50112','50114') ");
		if(hr!=null)
		{
			if(hr.getYear()!=null)
			{
				hql.append(" and a.year = "+hr.getYear()+ " ");
			}
			if(hr.getMonth()!=null)
			{
				hql.append(" and a.month = "+hr.getMonth()+ " ");
			}
			if(Common.notEmpty(hr.getCompany()))
			{
				hql.append(" and a.company = '"+hr.getCompany()+ "' ");
			}
			if(hr.getId()!=null)
			{
				hql.append(" and a.id !='"+hr.getId()+"' ");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		//Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		Integer a= Integer.parseInt(query.uniqueResult().toString());
		if(a!=null && a==0)
			return true;
		else
			return false;
	}
	
	/**
	 * 上报时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean repeatDataCheck(HeadCountLaborProduction entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from DataHrHeadcount a where a.isdel=0 and a.status in ('50113','50115','50112','50114') ");
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
		if(a!=null && a>0)
			return true;
		else
			return false;
	}

	@Override
	public Integer getExamineHrFormsListViewCount(HeadCountLaborProduction hr) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select count(0) ");
		hql.append(" from data_hr_headcount a where a.isdel=0 and status!=50112 ");
		if(hr!=null)
		{
			if(hr.getYear()!=null)
			{
				hql.append(" and a.year = "+hr.getYear()+ " ");
			}
			if(hr.getMonth()!=null)
			{
				hql.append(" and a.month = "+hr.getMonth()+ " ");
			}
			if(Common.notEmpty(hr.getCompany()))
			{
				hql.append(" and a.company = '"+hr.getCompany()+ "' ");
			}
			if(hr.getStatus()!=null)
			{
				hql.append(" and a.status = '"+hr.getStatus()+ "' ");
			}
			if(hr.getId() != null){
				hql.append(" and a.id = " + hr.getId());
			}
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public java.util.List<HeadCountLaborProduction> getExamineHrFormsListView(
			HeadCountLaborProduction hr, Integer offset, Integer pageSize) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select a.id,a.year,a.month,a.company,a.workers_number,a.tworkers_number,b.average_income,b.average_profit,b.business_income,b.business_profit, "); 
		hql.append(" a.status,a.createPersonName,a.reportPersonName,a.auditorPersonName,a.organal_id,a.foreignKey,a.isdel,a.createDate,a.reportDate,a.auditorDate,a.createPersonID,a.reportPersonID,a.auditorPersonID,b.id as bid ");
		hql.append(" from data_hr_headcount a inner join data_hr_laborproduction b on  a.foreignKey=b.foreignKey where a.isdel=0 and a.status !=50112 and b.isdel=0 "); 		
		
		if(hr!=null)
		{
			if(hr.getYear()!=null)
			{
				hql.append(" and a.year = "+hr.getYear()+ " ");
			}
			if(hr.getMonth()!=null)
			{
				hql.append(" and a.month = "+hr.getMonth()+ " ");
			}
			if(Common.notEmpty(hr.getOrganalId()))
			{
				hql.append(" and a.organal_id in( "+hr.getOrganalId()+ ") ");
			}
			if(hr.getStatus()!=null)
			{
				hql.append(" and a.status = '"+hr.getStatus()+ "' ");
			}
			if(hr.getId() != null){
				hql.append(" and a.id = " + hr.getId());
			}
		}

		hql.append(" order by a.year,a.month desc,a.createDate desc");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		if(offset!=null)
			query.setFirstResult(offset);
		if(pageSize!=null)
			query.setMaxResults(pageSize);
		List a= query.list();
		return getHeadCountLaborProduction(a);
	}

	
	public List getOverviewIndustry(String year,String month) {
		StringBuilder hql = new StringBuilder();
		hql.append("select org.nNodeID from hh_organInfo org ");
		hql.append("where org.cLevel <= 3 and org.nNodeID not in ( ");
		hql.append("select doc.organal_id from data_hr_headcount doc where doc.isdel = 0 and doc.year = " + year + " and doc.month = " + month);
		hql.append(")");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return query.list();
	}
	
	
}
