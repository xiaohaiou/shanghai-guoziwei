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
import com.softline.controller.common.commonController;
import com.softline.dao.report.IReportOverseasCapitalPoolDao;
import com.softline.entity.Purchase;
import com.softline.entity.ReportOverseasCapitalPool;

@Repository(value = "reportOverseasCapitalPoolDao")
/**
 * 
 * @author tch
 *
 */
public class ReportOverseasCapitalPoolDao implements IReportOverseasCapitalPoolDao{
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
	public ReportOverseasCapitalPool getReportOverseasCapitalPool(Integer id)
	{
		if(id==null)
			return new ReportOverseasCapitalPool();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportOverseasCapitalPool s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (ReportOverseasCapitalPool) query.uniqueResult();
	}
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportOverseasCapitalPool> getReportOverseasCapitalPoolList(ReportOverseasCapitalPool entity ,Integer offsize,Integer pagesize)
	{
		if(entity==null)
			return new ArrayList<ReportOverseasCapitalPool>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportOverseasCapitalPool s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+Common.dealinStr(entity.getOrg())+ ") ");
			}
			if(entity.getSeason()!=null )
			{
				hql.append(" and s.season = "+entity.getSeason()+ " ");
			}
			if(Common.notEmpty(entity.getStarttime()))
			{
				hql.append(" and s.year >= '"+entity.getStarttime()+ "' ");
			}
			if(Common.notEmpty(entity.getEndtime()))
			{
				hql.append(" and s.year <= '"+entity.getEndtime()+ "' ");
			}
			if(entity.getYear()!=null)
			{
				hql.append(" and s.year = "+entity.getYear()+ " ");
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
		hql.append(" order by year desc,season desc");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getReportOverseasCapitalPoolListCount(ReportOverseasCapitalPool entity)
	{
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportOverseasCapitalPool s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+Common.dealinStr(entity.getOrg())+ ") ");
			}
			if(Common.notEmpty(entity.getStarttime()))
			{
				hql.append(" and s.year >= '"+entity.getStarttime()+ "' ");
			}
			if(Common.notEmpty(entity.getEndtime()))
			{
				hql.append(" and s.year <= '"+entity.getEndtime()+ "' ");
			}
			if(entity.getSeason()!=null )
			{
				hql.append(" and s.season = "+entity.getSeason()+ " ");
			}
			if(entity.getYear()!=null)
			{
				hql.append(" and s.year = "+entity.getYear()+ " ");
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
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	
	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveReportOverseasCapitalPoolRepeatCheck(ReportOverseasCapitalPool entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportOverseasCapitalPool s where 1=1 and isdel=0  ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()))
			{
				hql.append(" and s.org = '"+entity.getOrg()+ "' ");
			}
			if(entity.getSeason()!=null )
			{
				hql.append(" and s.season = "+entity.getSeason()+ " ");
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
	public boolean checkCanEdit(ReportOverseasCapitalPool entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportOverseasCapitalPool s where 1=1 and isdel=0 and status in("+Base.examCanEdit+") ");
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
	
	
	/**
	 * 获取境外下账资金
	 * @param year
	 * @param seaon
	 * @param organID
	 * @return
	 */
	public String getData(int year,String month,String organID)
	{
		if(!Common.notEmpty(month) || !Common.notEmpty(organID))
			return "";
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT sum(overseas_account_amounts) FROM `report_financing_account` where ");
		hql.append(" month in ("+month+") and year='"+year+"' and org ='"+organID+"' and isdel=0 ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		Object obj=query.uniqueResult();
		if(obj==null)
			return "";
		
		return String.valueOf(obj) ;
	}
}
