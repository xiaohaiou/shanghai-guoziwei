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
import com.softline.dao.report.IReportFinancingWeekThisDao;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.financing.ReportFinancingWeekThis;

@Repository(value = "reportFinancingWeekThisDao")
/**
 * 
 * @author ky_tian
 *
 */
public class ReportFinancingWeekThisDao implements IReportFinancingWeekThisDao{
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
	public ReportFinancingWeekThis getReportFinancingWeekThis(Integer id)
	{
		if(id==null)
			return new ReportFinancingWeekThis();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingWeekThis s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (ReportFinancingWeekThis) query.uniqueResult();
	}
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportFinancingWeekThis> getReportFinancingWeekThisList(ReportFinancingWeekThis entity ,Integer offsize,Integer pagesize,Integer status)
	{
		if(entity==null)
			return new ArrayList<ReportFinancingWeekThis>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingWeekThis s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+Common.dealinStr(entity.getOrg())+ ") ");
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and s.status = "+entity.getStatus()+ " ");
			}
			if(status==Base.examstatus_4)
			{
				hql.append(" and s.status in ('50113','50115') ");
			}
			if(entity.getStratYear()!=null)
			{
				hql.append(" and s.year > "+entity.getStratYear()+" ");
			}
			if(entity.getEndYear()!=null)
			{
				hql.append(" and s.year < "+entity.getEndYear()+" ");
			}
			if(entity.getWeekStratDate()!=null && !"".equals(entity.getWeekStratDate()))
			{
				hql.append(" and s.weekStratDate >='"+entity.getWeekStratDate()+"' ");
			}	
			if(entity.getWeekEndDate()!=null && !"".equals(entity.getWeekEndDate()))
			{
				hql.append(" and s.weekEndDate <= '"+entity.getWeekEndDate()+"' ");
			}	
			if(entity.getEndYear()!=null )
			{
				hql.append(" and s.year < "+entity.getEndYear()+" ");
			}			
			if(entity.getStratWeek()!=null)
			{
				hql.append(" and s.week > "+entity.getStratWeek()+" ");
			}
			if(entity.getEndWeek()!=null)
			{
				hql.append(" and s.week < "+entity.getEndWeek()+" ");
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
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
//			hql.append(" and s.parentorg like '%-" + entity.getParentorg() + "-%' ");
		}
		hql.append(" order by s.lastModifyDate desc ");
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
	public int getReportFinancingWeekThisListCount(ReportFinancingWeekThis entity,Integer status)
	{
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportFinancingWeekThis s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
			{
				hql.append(" and s.org in( "+Common.dealinStr(entity.getOrg())+ ") ");
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and s.status = "+entity.getStatus()+ " ");
			}
			else if(status==Base.examstatus_4)
			{
				hql.append(" and s.status in ('50113','50115') ");
			}
			if(entity.getStratYear()!=null)
			{
				hql.append(" and s.year > "+entity.getStratYear()+" ");
			}
			if(entity.getEndYear()!=null)
			{
				hql.append(" and s.year < "+entity.getEndYear()+" ");
			}
			if(entity.getStratWeek()!=null)
			{
				hql.append(" and s.week > "+entity.getStratWeek()+" ");
			}
			if(entity.getEndWeek()!=null)
			{
				hql.append(" and s.week < "+entity.getEndWeek()+" ");
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
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
//			hql.append(" and s.parentorg like '%-" + entity.getParentorg() + "-%' ");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<ReportFinancingProjectProgress> getExportList(Integer id) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingProjectProgress s where 1=1 and isdel=0 and s.id in (select fid from ReportFinancingWeekThisList where pid = '"+id+"')");
		hql.append(" order by s.lastModifyDate desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	@Override
	public boolean checkCanEdit(ReportFinancingWeekThis entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportFinancingWeekThis s where 1=1 and isdel=0 and status in("+Base.examCanEdit+") ");
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
	public List<ReportFinancingProjectProgress> getAccountsData(String orgnm) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select s.financialInstitution,s.operateOrg,s.operateOrgname,s.alreadyAccountAmounts,s.expectAccountDate,");
		hql.append(" s.projectProgressName,s.lastModifyDate,s.id,s.projectProgress,s.createDate from ReportFinancingProjectProgress s where 1=1 and isdel=0  ");
		hql.append(" and s.financialInstitution like '%"+orgnm+ "%' ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
	//海航实业各业态推进中融资项目情况（除债券类）getCategoryList
	@Override
	public List<String> getDataList(ReportFinancingWeekThis entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT a.orgname,COUNT(*),sum(b.already_account_amounts) FROM report_financing_week_this a LEFT JOIN report_financing_week_this_list b ON a.id=b.pid WHERE 1 = 1 AND a.isdel = 0 ");
		if(Common.notEmpty(entity.getOrg()) )
		{
			hql.append(" and a.org in( "+Common.dealinStr(entity.getOrg())+ ") ");
		}
		if(entity.getYear()!=null)
		{
			hql.append(" and a.year = "+entity.getYear()+" ");
		}
		if(entity.getWeek()!=null)
		{
			hql.append(" and a.week = "+entity.getWeek()+" ");
		}
		hql.append(" GROUP BY a.org ");
		hql.append(" order by b.account_date desc ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return query.list();
	}
	
	/**
	 * 查询 --页面所有实体类的数据
	 * 传递--id值
	 */
	public List<ReportFinancingProjectProgress> getAllListBeans(String ids,ReportFinancingProjectProgress rfppBean){
		
		if(null == ids || !ids.contains("'"))
			return new ArrayList<ReportFinancingProjectProgress>();
		
		StringBuilder  hql = new StringBuilder();
		
		hql.append(" from ReportFinancingProjectProgress s where 1=1 and isdel=0 and s.id in (select fid from ReportFinancingWeekThisList where pid in ("+ids+"))");	
		
		if(null!= rfppBean && null!=rfppBean.getCategory() && !"".equals(rfppBean.getCategory()))
			hql.append(" and s.category ="+rfppBean.getCategory());
		
//		if(null!= rfppBean && null!=rfppBean.getCoreOrg() && !"".equals(rfppBean.getCoreOrg()))
//			hql.append(" and s.coreOrg in ( "+Common.dealinStr(rfppBean.getCoreOrg())+ ") ");
		
		hql.append(" order by s.lastModifyDate desc ");

		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
	/**
	 * 查询 --页面所有实体类的数据
	 * 传递--id值
	 */
	public int getAllListBeansRecords(String ids,ReportFinancingProjectProgress rfppBean){
		
		if(null == ids || !ids.contains("'"))
			return 0;
		
		StringBuilder  hql = new StringBuilder();
		
		hql.append(" select count(*) from ReportFinancingProjectProgress s where 1=1 and isdel=0 and s.id in (select fid from ReportFinancingWeekThisList where pid in ("+ids+"))");	
		
		if(null!= rfppBean && null!=rfppBean.getCategory() && !"".equals(rfppBean.getCategory()))
			hql.append(" and s.category ="+rfppBean.getCategory());
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}	
	
	
	
	
	/**
	 * 查询--汇总页面，根据年月、周数、周日期范围，查询所有符合条件的数据
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportFinancingWeekThis> getReportFinancingWeekThisListForSumData(ReportFinancingWeekThis entity ,Integer offsize,Integer pagesize,Integer status){
		if(entity==null)
			return new ArrayList<ReportFinancingWeekThis>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingWeekThis s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
				hql.append(" and s.org in( "+Common.dealinStr(entity.getOrg())+ ") ");

			if(null !=entity.getStatus() && !"".equals(entity.getStatus()))
				hql.append(" and s.status = "+entity.getStatus()+ " ");
	
			if(status==Base.examstatus_4)
				hql.append(" and s.status in ('50113','50115') ");
		
			if(entity.getYear()!=null && !"".equals(entity.getYear()))
				hql.append(" and s.year = "+entity.getYear()+" ");
		
			if(entity.getMonth()!=null && !"".equals(entity.getMonth()))
				hql.append(" and s.month = "+entity.getMonth()+" ");
			
			if(entity.getWeek()!=null && !"".equals(entity.getWeek()))
				hql.append(" and s.week = "+entity.getWeek()+" ");

			if(Common.notEmpty(entity.getWeekStratDate()))
				hql.append(" and s.weekStratDate > '"+entity.getWeekStratDate()+"' ");		
			
			if(Common.notEmpty(entity.getWeekEndDate()))
				hql.append(" and s.weekEndDate < '"+entity.getWeekEndDate()+"' ");			
			
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
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
//			hql.append(" and s.parentorg like '%-" + entity.getParentorg() + "-%' ");
		}
		hql.append(" order by s.lastModifyDate desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();
	}
	
	public int getReportFinancingWeekThisListForSumData(ReportFinancingWeekThis entity ,Integer status){
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append(" select count(*) from ReportFinancingWeekThis s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) )
				hql.append(" and s.org in( "+Common.dealinStr(entity.getOrg())+ ") ");

			if(null !=entity.getStatus() && !"".equals(entity.getStatus()))
				hql.append(" and s.status = "+entity.getStatus()+ " ");
	
			if(status==Base.examstatus_4)
				hql.append(" and s.status in ('50113','50115') ");
		
			if(entity.getYear()!=null && !"".equals(entity.getYear()))
				hql.append(" and s.year = "+entity.getYear()+" ");
		
			if(entity.getMonth()!=null && !"".equals(entity.getMonth()))
				hql.append(" and s.month = "+entity.getMonth()+" ");
			
			if(entity.getWeek()!=null && !"".equals(entity.getWeek()))
				hql.append(" and s.week = "+entity.getWeek()+" ");

			if(Common.notEmpty(entity.getWeekStratDate()))
				hql.append(" and s.weekStratDate > '"+entity.getWeekStratDate()+"' ");		
			
			if(Common.notEmpty(entity.getWeekEndDate()))
				hql.append(" and s.weekEndDate < '"+entity.getWeekEndDate()+"' ");			
			
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
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
	public List<ReportFinancingProjectProgress> getAllListBeans(String ids) {
		if(null == ids || !ids.contains("'"))
			return new ArrayList<ReportFinancingProjectProgress>();
		
		StringBuilder  hql = new StringBuilder();
		
		hql.append(" from ReportFinancingProjectProgress s where 1=1 and isdel=0 and s.id in (select fid from ReportFinancingWeekThisList where pid in ("+ids+"))");	
		
		
		hql.append(" order by s.lastModifyDate desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		
		return query.list();
	}

	@Override
	public int getReportFinancingWeekThisListForSumDatas(
			ReportFinancingWeekThis entity, Integer examstatus1) {
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append(" select count(*) from ReportFinancingWeekThis s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()) ){
				hql.append(" and s.org in( "+Common.dealinStr(entity.getOrg())+ ") ");
			}
			if(null !=entity.getStatus() && !"".equals(entity.getStatus())){
				hql.append(" and s.status = "+entity.getStatus()+ " ");
			}
			if(examstatus1==Base.examstatus_4){
				hql.append(" and s.status in ('50113','50115') ");
			}
			if(entity.getStratYear()!=null)
			{
				hql.append(" and s.year > "+entity.getStratYear()+" ");
			}
			if(entity.getEndYear()!=null)
			{
				hql.append(" and s.year < "+entity.getEndYear()+" ");
			}
			if(entity.getWeekStratDate()!=null &&
					!"".equals(entity.getWeekStratDate()))
				hql.append(" and s.weekStratDate >= '"+entity.getWeekStratDate()+"' ");
			
			if(entity.getWeekEndDate()!=null &&
					!"".equals(entity.getWeekStratDate()))
				hql.append(" and s.weekEndDate <= '"+entity.getWeekEndDate()+"' ");			
			
			if(entity.getStratWeek()!=null)
			{
				hql.append(" and s.week > "+entity.getStratWeek()+" ");
			}
			if(entity.getEndWeek()!=null)
			{
				hql.append(" and s.week < "+entity.getEndWeek()+" ");
			}	
			
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
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
	
	
	
	
	
}
