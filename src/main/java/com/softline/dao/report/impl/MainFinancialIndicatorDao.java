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
import com.softline.dao.report.IMainFinancialIndicatorDao;
import com.softline.entity.MainFinancialIndicator;
import com.softline.entity.MainFinancialIndicatorStock;

@Repository(value = "mainFinancialIndicatorDao")
/**
 * 
 * @author tch
 *
 */
public class MainFinancialIndicatorDao implements IMainFinancialIndicatorDao{
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
	public MainFinancialIndicator getMainFinancialIndicator(Integer id)
	{
		if(id==null)
			return new MainFinancialIndicator();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from MainFinancialIndicator s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (MainFinancialIndicator) query.uniqueResult();
	}
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<MainFinancialIndicator> getMainFinancialIndicatorList(MainFinancialIndicator entity ,Integer offsize,Integer pagesize)
	{
		if(entity==null)
			return new ArrayList<MainFinancialIndicator>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from MainFinancialIndicator s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(entity.getReportType()!=null )
			{
				hql.append(" and s.reportType ="+entity.getReportType()+ " ");
			}
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+Common.dealinStr(entity.getOrg()) + ") ");
			}
			if(entity.getYear()!=null)
			{
				hql.append(" and s.year = "+entity.getYear()+ " ");
			}
			if(entity.getMonth()!=null)
			{
				hql.append(" and s.month = "+entity.getMonth()+ " ");
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
		hql.append(" order by year desc,createDate desc");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}
	/**
	 * 导出
	 */
	@Override
	public List<MainFinancialIndicator> getMainFinancialIndicatorExportList(MainFinancialIndicator entity) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append(" from MainFinancialIndicator s where 1=1 and isdel=0 ");
		if(Common.notEmpty(entity.getOrg()) )
		{
			hql.append(" and s.org in( "+Common.dealinStr(entity.getOrg()) + ") ");
		}
		if(entity.getYear()!=null)
		{
			hql.append(" and s.year = "+entity.getYear()+ " ");
		}
		if(entity.getMonth()!=null)
		{
			hql.append(" and s.month = "+entity.getMonth()+ " ");
		}
		hql.append(" order by year desc,createDate desc");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getMainFinancialIndicatorListCount(MainFinancialIndicator entity)
	{
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from MainFinancialIndicator s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(entity.getReportType()!=null )
			{
				hql.append(" and s.reportType ="+entity.getReportType()+ " ");
			}
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+Common.dealinStr(entity.getOrg()) + ") ");
			}
			if(entity.getYear()!=null)
			{
				hql.append(" and s.year = "+entity.getYear()+ " ");
			}
			if(entity.getMonth()!=null)
			{
				hql.append(" and s.month = "+entity.getMonth()+ " ");
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
	public boolean saveMainFinancialIndicatorRepeatCheck(MainFinancialIndicator entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from MainFinancialIndicator s where 1=1 and isdel=0  ");
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
	public boolean checkCanEdit(MainFinancialIndicator entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from MainFinancialIndicator s where 1=1 and isdel=0 and status in("+Base.examCanEdit+") ");
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
	
	
	
	public List<MainFinancialIndicatorStock> getStockData(int parentID)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append(" from MainFinancialIndicatorStock s where 1=1 and isdel=0 and parentId="+parentID+" ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
		
	}

	@Override
	public List<MainFinancialIndicatorStock> getSszbDataFT(
			MainFinancialIndicator condition) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append(" from MainFinancialIndicatorStock s where 1=1 and isdel=0 and type=0 and parentId="+condition.getId()+" ");
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
		
	}

	@Override
	public List<MainFinancialIndicatorStock> getZczbDataFT(
			MainFinancialIndicator condition) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append(" from MainFinancialIndicatorStock s where 1=1 and isdel=0 and type=1 and parentId="+condition.getId()+" ");
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<MainFinancialIndicator> getMainFinancialIndicators(
			Integer year, Integer month, String org, Integer reportType) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" from MainFinancialIndicator s where 1=1 and s.isdel=0 ");
			hql.append(" and s.org = '").append(org).append("'");
			hql.append(" and s.year =").append(year);
			hql.append(" and s.month =").append(month);
			hql.append(" and s.reportType=").append(reportType);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	
	
}
