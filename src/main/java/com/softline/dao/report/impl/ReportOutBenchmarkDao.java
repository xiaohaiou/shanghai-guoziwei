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
import com.softline.dao.report.IReportOutBenchmarkDao;
import com.softline.entity.ReportFinancingAccount;
import com.softline.entity.ReportOutBenchmark;
import com.softline.entity.ReportOutCompany;
import com.softline.entity.ReportOverseasCapitalPool;



@Repository(value = "reportOutBenchmarkDao")
/**
 * 
 * @author yxk
 *
 */
public class ReportOutBenchmarkDao implements IReportOutBenchmarkDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	@Override
	public int getReportOutBenchmarkListCount(ReportOutBenchmark entity) {
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportOutBenchmark s where 1=1 ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<ReportOutBenchmark> getReportOutBenchmarkList(ReportOutBenchmark entity, Integer offset, Integer pagesize) {
		if(entity==null)
			return new ArrayList<ReportOutBenchmark>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportOutBenchmark s where 1=1 ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offset!=null)
			query.setFirstResult(offset);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}



	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public ReportOutBenchmark getReportOutBenchmar(Integer id) {
		if(id==null)
			return new ReportOutBenchmark();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportOutBenchmark s where 1=1 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (ReportOutBenchmark) query.uniqueResult();
	}



	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveReportOverseasCapitalPoolRepeatCheck(ReportOutCompany entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportOutCompany s where 1=1 and isdel=0  ");
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
	public boolean checkCanEdit(ReportOutCompany entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportOutCompany s where 1=1 and isdel=0 and status in("+Base.examCanEdit+") ");
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
	public Integer getReportOutComapnyListCount(ReportOutCompany entity,String str) {
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportOutCompany s where 1=1 and isdel=0");
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
			if(entity.getYear()!=null)
			{
				hql.append(" and s.year = "+entity.getYear()+ " ");
			}
			if(Common.notEmpty(entity.getOrgname()))
			{
				hql.append(" and s.orgname = '"+entity.getOrgname()+ "' ");
			}
			if(entity.getId()!=null)
			{
				hql.append(" and s.id = "+entity.getId()+ " ");
			}
			if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
			{
				hql.append(" and s.status != "+Base.examstatus_1+ " ");
			}
	    	if(Common.notEmpty(str)){
				hql.append(" and (");
				String [] dd = str.split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						hql.append(" s.parentorg like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" s.parentorg like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and s.parentorg like '%--%' ");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}



	@Override
	public List<ReportOutCompany> getReportOutCompanyList(ReportOutCompany entity, Integer offset,String str,Integer pagesize) {
		if(entity==null)
			return new ArrayList<ReportOutCompany>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportOutCompany s where 1=1 and isdel=0 ");
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
		/*	if(Common.notEmpty(entity.getStarttime()))
			{
				hql.append(" and s.year >= '"+entity.getStarttime()+ "' ");
			}
			if(Common.notEmpty(entity.getEndtime()))
			{
				hql.append(" and s.year <= '"+entity.getEndtime()+ "' ");
			}*/
			if(entity.getYear()!=null)
			{
				hql.append(" and s.year = "+entity.getYear()+ " ");
			}
			if(Common.notEmpty(entity.getOrgname()))
			{
				hql.append(" and s.orgname = '"+entity.getOrgname()+ "' ");
			}
			if(entity.getId()!=null)
			{
				hql.append(" and s.id = "+entity.getId()+ " ");
			}
			if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
			{
				hql.append(" and s.status != "+Base.examstatus_1+ " ");
			}
	    	if(Common.notEmpty(str)){
				hql.append(" and (");
				String [] dd = str.split(",");
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
		if(offset!=null)
			query.setFirstResult(offset);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}



	/**
	 * 跳转到查看页面
	 * @param entity
	 * @return
	 */
	public ReportOutCompany getReportOutCompany(Integer id) {
		if(id==null)
			return new ReportOutCompany();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportOutCompany s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (ReportOutCompany) query.uniqueResult();
	}



	/**
	 * 保存时校验重复的方法
	 * @param entity
	 * @return
	 */
	public boolean saveReportOutCompanyRepeatCheck(ReportOutCompany entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportOutCompany s where 1=1 and isdel=0  ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()))
			{
				hql.append(" and s.org = '"+entity.getOrg()+ "' ");
			}
			
			if(entity.getYear()!=null)
			{
				hql.append(" and s.year = "+entity.getYear()+ " ");
			}	
			if(entity.getSeason()!=null )
			{
				hql.append(" and s.season = "+entity.getSeason()+ " ");
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


	@Override
	public List<ReportOutBenchmark> getReportOutBenchmarkList(String str) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportOutBenchmark s where 1=1 ");
		if(Common.notEmpty(str)){
				hql.append(" and (");
				String [] dd = str.split(",");
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
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return  query.list();
	}

}
