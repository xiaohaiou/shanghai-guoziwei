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
import com.softline.dao.report.IReportFinancingAccountDao;
import com.softline.entity.Purchase;
import com.softline.entity.ReportFinancingAccount;

@Repository(value = "reportFinancingAccountDao")
/**
 * 
 * @author tch
 *
 */
public class ReportFinancingAccountDao implements IReportFinancingAccountDao{
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
	public ReportFinancingAccount getReportFinancingAccount(Integer id)
	{
		if(id==null)
			return new ReportFinancingAccount();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingAccount s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (ReportFinancingAccount) query.uniqueResult();
	}
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportFinancingAccount> getReportFinancingAccountList(ReportFinancingAccount entity ,Integer offsize,Integer pagesize)
	{
		if(entity==null)
			return new ArrayList<ReportFinancingAccount>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingAccount s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
			{
				if(entity.getOrg().contains(",")){
					hql.append(" and s.org in( "+Common.dealinStr(entity.getOrg())+ ") ");	
				}else{
					hql.append(" and s.org in ( select h.id.nnodeId from HhOrganInfoTreeRelation h where locate((select hh.vcOrganId from HhOrganInfoTreeRelation hh where hh.id.nnodeId ='"+
							entity.getOrg()+"'),h.vcOrganId)>0)");
				}
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
			if(Common.notEmpty(entity.getStarttime()))
			{	
				hql.append(" and str_to_date(CONCAT(s.year,s.month),'%Y%m') >= str_to_date(CONCAT("+entity.getStartyear()+","+entity.getStartmonth()+"),'%Y%m') ");
			}
			if(Common.notEmpty(entity.getEndtime()))
			{	
				hql.append(" and str_to_date(CONCAT(s.year,s.month),'%Y%m') <= str_to_date(CONCAT("+entity.getEndyear()+","+entity.getEndmonth()+"),'%Y%m') ");
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
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public int getReportFinancingAccountListCount(ReportFinancingAccount entity)
	{
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportFinancingAccount s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
			{
				if(entity.getOrg().contains(",")){
					hql.append(" and s.org in( "+Common.dealinStr(entity.getOrg())+ ") ");	
				}else{
					hql.append(" and s.org in ( select h.id.nnodeId from HhOrganInfoTreeRelation h where locate((select hh.vcOrganId from HhOrganInfoTreeRelation hh where hh.id.nnodeId ='"+
							entity.getOrg()+"'),h.vcOrganId)>0)");
				}
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
			if(Common.notEmpty(entity.getStarttime()))
			{	
				hql.append(" and str_to_date(CONCAT(s.year,s.month),'%Y%m') >= str_to_date(CONCAT("+entity.getStartyear()+","+entity.getStartmonth()+"),'%Y%m') ");
			}
			if(Common.notEmpty(entity.getEndtime()))
			{	
				hql.append(" and str_to_date(CONCAT(s.year,s.month),'%Y%m') <= str_to_date(CONCAT("+entity.getEndyear()+","+entity.getEndmonth()+"),'%Y%m') ");
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
	public boolean saveReportFinancingAccountRepeatCheck(ReportFinancingAccount entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportFinancingAccount s where 1=1 and isdel=0  ");
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
			
			if(entity.getMonth()!=null)
			{
				hql.append(" and s.month = "+entity.getMonth()+ " ");
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
	public boolean checkCanEdit(ReportFinancingAccount entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportFinancingAccount s where 1=1 and isdel=0 and status in("+Base.examCanEdit+") ");
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
	public List<ReportFinancingAccount> getSummaryData(String org,Integer year,Integer month) {
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
		hql.append("select r from ReportFinancingAccount r, HhOrganInfoTreeRelation h where 1=1 and h.id.nnodeId = r.org and r.isdel=0 and r.status=50115 and h.status in (50500,50502)") ;
		if(null != org  && null != year && null != month){
				hql.append(" and r.year = "+year+ " ");
				hql.append(" and r.month = "+month+"");
				hql.append(" and r.org IN (select id.nnodeId from HhOrganInfoTreeRelation where vcOrganId like '%" + org + "%' ) ");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<ReportFinancingAccount> getSummaryHistoryData(String org,Integer year, Integer month) {
		
		String time = year + "-" + String.format("%02d", month);
		StringBuilder  hql = new StringBuilder();
		hql.append("select r from ReportFinancingAccount r, HhOrganInfoTreeRelationHistory h where 1=1 and h.id.nnodeId = r.org and r.isdel=0 and r.status=50115 and h.status in (50500,50502)") ;
		if(null != org  && null != year && null != month){
				hql.append(" and h.id.updatetime = "+time+ " ");
				hql.append(" and r.org IN (select id.nnodeId from HhOrganInfoTreeRelation where vcOrganId like '%" + org + "%' ) ");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
}
