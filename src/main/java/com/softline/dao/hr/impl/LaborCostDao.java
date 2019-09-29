package com.softline.dao.hr.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.hr.ILaborCostDao;
import com.softline.dao.system.ICommonDao;
import com.softline.entityTemp.LaborcostResourcesreWards;

@Repository(value = "laborCostDao")
public class LaborCostDao implements ILaborCostDao{
	
	private static final LaborcostResourcesreWards List = null;

	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public Integer getHrFormsListViewCount(LaborcostResourcesreWards entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select count(0) ");
		hql.append(" from data_hr_laborcost a where a.isdel=0 ");
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

	public List<LaborcostResourcesreWards> getHrFormsListView(LaborcostResourcesreWards entity, Integer offset,Integer pageSize) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select a.id,a.year,a.month,a.company,a.status,a.createPersonName,a.reportPersonName,a.auditorPersonName,a.organal_id,a.foreignKey,a.isdel, "); 
		hql.append(" a.labor_cost,b.hr_rate_return,a.createDate,a.reportDate,a.auditorDate,a.yearly_budget,a.monthly_budget,a.monthly_perform,b.capitalization,b.retained_profits,b.id as bid ");
		hql.append(" from data_hr_laborcost a inner join data_hr_resourcesrewards b where a.foreignKey=b.foreignKey and a.isdel=0 and b.isdel=0 "); 		
		
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
		return getLaborcostResourcesreWards(a);
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public List<LaborcostResourcesreWards> getLaborcostResourcesreWards(List list){
		List<LaborcostResourcesreWards> returnData=new ArrayList<LaborcostResourcesreWards>();
		DecimalFormat df = new DecimalFormat("#");  
	    // System.out.println(df.format(d));
		for (Object object : list) {
			int i=0;
			LaborcostResourcesreWards dh=new LaborcostResourcesreWards();
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
			dh.setForeignKey(row[i]==null ?"": row[i].toString());i++;
			dh.setIsdel(row[i]==null ? 0: Integer.parseInt(row[i].toString()));i++;
			dh.setLaborCost(row[i]==null ? 0: Double.parseDouble(row[i].toString()));i++;
			dh.setHrRateReturn(row[i]==null ? 0: Double.parseDouble(row[i].toString()));i++;
			dh.setCreateDate(row[i]==null ?"": row[i].toString());i++;
			dh.setReportDate(row[i]==null ?"": row[i].toString());i++;
			dh.setAuditorDate(row[i]==null ?"": row[i].toString());i++;
			
			dh.setYearlyBudget(row[i]==null ? "": row[i].toString());i++;
			dh.setMonthlyBudget(row[i]==null ? "": row[i].toString());i++;
			dh.setMonthlyPerform(row[i]==null ? "": row[i].toString());i++;

			dh.setCapitalization(row[i]==null ? "": row[i].toString());i++;
			dh.setRetainedProfits(row[i]==null ? "": row[i].toString());i++;
			dh.setBid(row[i]==null ? 0: Integer.parseInt(row[i].toString()));i++;
			returnData.add(dh);
		}
		return returnData;
	}
	
	
	public LaborcostResourcesreWards get(Integer id) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select a.id,a.year,a.month,a.company,a.status,a.createPersonName,a.reportPersonName,a.auditorPersonName,a.organal_id,a.foreignKey,a.isdel, "); 
		hql.append(" a.labor_cost,b.hr_rate_return,a.createDate,a.reportDate,a.auditorDate,a.yearly_budget,a.monthly_budget,a.monthly_perform,b.capitalization,b.retained_profits,b.id as bid ");
		hql.append(" from data_hr_laborcost a inner join data_hr_resourcesrewards b where a.foreignKey=b.foreignKey and a.isdel=0 and b.isdel=0 and a.id ="+id);
		LaborcostResourcesreWards entity = new LaborcostResourcesreWards();
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<LaborcostResourcesreWards> tempList = getLaborcostResourcesreWards(a);
		for(LaborcostResourcesreWards h:tempList){
			if(h!=null){
				entity=h;
			}else{
			}
		}
		return entity;
	}
	
	@Override
	public boolean get(LaborcostResourcesreWards entity) {
		boolean b=false;
		StringBuilder  hql = new StringBuilder();
		hql.append(" select a.id,a.year,a.month,a.company,a.status,a.createPersonName,a.reportPersonName,a.auditorPersonName,a.organal_id,a.foreignKey,a.isdel, "); 
		hql.append(" a.labor_cost,b.hr_rate_return,a.createDate,a.reportDate,a.auditorDate,a.yearly_budget,a.monthly_budget,a.monthly_perform,b.capitalization,b.retained_profits,b.id as bid ");
		hql.append(" from data_hr_laborcost a inner join data_hr_resourcesrewards b where a.foreignKey=b.foreignKey and a.isdel=0 and b.isdel=0 and a.id ="+entity.getId());
		if(entity.getStatus()!=null&&(entity.getStatus()==50112||entity.getStatus()==50114)){
			hql.append(" and a.status="+entity.getStatus());
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		List a= query.list();
		List<LaborcostResourcesreWards> tempList = getLaborcostResourcesreWards(a);
		for(LaborcostResourcesreWards h:tempList){
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
	public boolean saveLaborcostResourcesreWardsCheck(LaborcostResourcesreWards entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from data_hr_laborcost a where a.isdel=0 ");
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
		//Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		Integer a= Integer.parseInt(query.uniqueResult().toString());
		if(a!=null && a.equals(0))
			return true;
		else
			return false;
	}

	/**
	 * 保存上报检验
	 */
	public boolean reportLaborcostResourcesreWardsCheck(LaborcostResourcesreWards entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from data_hr_laborcost a where a.isdel=0  and a.status in ('50113','50115','50112','50114')");
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
		//Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		Integer a= Integer.parseInt(query.uniqueResult().toString());
		if(a!=null && a.equals(0))
			return true;
		else
			return false;
	}

	/**
	 * 上报检验
	 */
	public boolean reportDataCheck(LaborcostResourcesreWards entity){
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from data_hr_laborcost a where a.isdel=0  and a.status in ('50113','50115','50112','50114')");
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
		//Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		Integer a= Integer.parseInt(query.uniqueResult().toString());
		if(a!=null && !a.equals(0))
			return true;
		else
			return false;
	}

	@Override
	public Integer getExamineHrFormsListViewCount(
			LaborcostResourcesreWards entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select count(0) ");
		hql.append(" from data_hr_laborcost a where a.isdel=0 and a.status!=50112 ");
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
	public java.util.List<LaborcostResourcesreWards> getExamineHrFormsListView(
			LaborcostResourcesreWards entity, Integer offset, Integer pageSize) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select a.id,a.year,a.month,a.company,a.status,a.createPersonName,a.reportPersonName,a.auditorPersonName,a.organal_id,a.foreignKey,a.isdel, "); 
		hql.append(" a.labor_cost,b.hr_rate_return,a.createDate,a.reportDate,a.auditorDate,a.yearly_budget,a.monthly_budget,a.monthly_perform,b.capitalization,b.retained_profits,b.id as bid ");
		hql.append(" from data_hr_laborcost a inner join data_hr_resourcesrewards b where a.foreignKey=b.foreignKey and a.isdel=0 and a.status!=50112 and b.isdel=0 "); 		
		
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
		return getLaborcostResourcesreWards(a);
	}

	
	public List getOverviewIndustry(String year,String month) {
		StringBuilder hql = new StringBuilder();
		hql.append("select org.nNodeID from hh_organInfo org ");
		hql.append("where org.cLevel <= 3 and org.nNodeID not in ( ");
		hql.append("select doc.organal_id from data_hr_laborcost doc where doc.isdel = 0 and doc.year = " + year + " and doc.month = " + month);
		hql.append(")");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return query.list();
	}
	
}
