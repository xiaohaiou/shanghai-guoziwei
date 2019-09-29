package com.softline.dao.report.impl;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.report.IReportFundPositionDao;
import com.softline.entity.HhOrganInfoTreeRelation;
import com.softline.entity.fundPosition.DataFundPosition;
import com.softline.util.MsgPage;

@Repository(value = "reportFundPositionDao")
/**
 * 
 * @author ky_tian
 *
 */
public class ReportFundPositionDao implements IReportFundPositionDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public Integer getHrFormsListViewCount(DataFundPosition entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select count(0) ");
		hql.append(" from data_fund_position a left join hh_organInfo_tree_relation hort on a.org = hort.nNodeID where a.isdel=0 ");
		if(entity!=null)
		{
			if(entity.getStartDate()!=null&&!"".equals(entity.getStartDate()))
			{
				hql.append(" and str_to_date(a.date,'%Y-%m-%d') >= str_to_date('"+entity.getStartDate()+"','%Y-%m-%d') ");
			}
			if(entity.getEndDate()!=null&&!"".equals(entity.getEndDate()))
			{
				hql.append(" and str_to_date(a.date,'%Y-%m-%d') <= str_to_date('"+entity.getEndDate()+"','%Y-%m-%d') ");
			}
			if(Common.notEmpty(entity.getOrg()))
			{
				if(entity.getOrg().contains(",")){
					hql.append(" and a.org = '"+entity.getOrg()+"' ");
				}else{
					hql.append(" and a.org in ( select nNodeID from hh_organInfo_tree_relation where locate((select vcOrganID from hh_organInfo_tree_relation where nNodeID ='"+
							entity.getOrg()+"'),vcOrganID)>0)");
				}
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and a.status = '"+entity.getStatus()+ "' ");
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						hql.append(" hort.vcOrganID like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" hort.vcOrganID like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and hort.vcOrganID like '%--%' ");
			}
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	public List<DataFundPosition> getHrFormsListView(DataFundPosition entity, Integer offset,Integer pageSize) {
		if(entity==null)
			return new ArrayList<DataFundPosition>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from DataFundPosition d where 1=1 and isdel=0 ");
		
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()))
			{
				if(entity.getOrg().contains(",")){
					hql.append(" and d.org = '"+entity.getOrg()+"' ");
				}else{
					hql.append(" and d.org in ( select h.id.nnodeId from HhOrganInfoTreeRelation h where locate((select hh.vcOrganId from HhOrganInfoTreeRelation hh where hh.id.nnodeId ='"+
							entity.getOrg()+"'),h.vcOrganId)>0)");
				}
			}
			if(entity.getStartDate()!=null&&!"".equals(entity.getStartDate()))
			{
				hql.append(" and str_to_date(d.date,'%Y-%m-%d') >= str_to_date('"+entity.getStartDate()+"','%Y-%m-%d') ");
			}
			if(entity.getEndDate()!=null&&!"".equals(entity.getEndDate()))
			{
				hql.append(" and str_to_date(d.date,'%Y-%m-%d') <= str_to_date('"+entity.getEndDate()+"','%Y-%m-%d') ");
			}
			if(entity.getStatus() !=null && !"".equals(entity.getStatus()))
			{
				hql.append(" and d.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						hql.append(" d.authOrg like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" d.authOrg like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and d.authOrg like '%--%' ");
			}
		}
		hql.append(" order by date desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offset!=null)
			query.setFirstResult(offset);
		if(pageSize!=null)
			query.setMaxResults(pageSize);
		return query.list();
	}
	
	public DataFundPosition get(Integer id) {
		String hql = " from DataFundPosition a where a.isdel=0 and a.id ="+id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (DataFundPosition) query.uniqueResult();
	}
	
	public boolean get(DataFundPosition entity) {
		boolean b=false;
		String hql = " from DataFundPosition a where a.isdel=0 and a.id ="+entity.getId();
		if(entity.getId()!=null){
			hql+=" and a.id = "+entity.getId();
		}
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
	public boolean saveReportFundPositionCheck(DataFundPosition entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from DataFundPosition a where a.isdel=0  ");
		if(entity!=null)
		{
			if(entity.getDate()!=null&&!"".equals(entity.getDate()))
			{
				hql.append(" and str_to_date(a.date,'%Y-%m-%d') =  str_to_date('"+entity.getDate()+"','%Y-%m-%d') ");
			}
			if(Common.notEmpty(entity.getOrg()))
			{
				hql.append(" and a.org = '"+entity.getOrg()+"' ");
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
	public boolean reportFundPositionCheck(DataFundPosition entity)
	{

		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from DataFundPosition a where a.isdel=0 and a.status in ('50113','50115','50112','50114') ");
		if(entity!=null)
		{
			if(entity.getDate()!=null&&!"".equals(entity.getDate()))
			{
				hql.append(" and str_to_date(a.date,'%Y-%m-%d') =  str_to_date('"+entity.getDate()+"','%Y-%m-%d') ");
			}
			if(Common.notEmpty(entity.getOrg()))
			{
				hql.append(" and a.org = '"+entity.getOrg()+"' ");
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
	public Integer getExamineHrFormsListViewCount(DataFundPosition entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select count(0) ");
		hql.append(" from data_fund_position a left join hh_organInfo_tree_relation hort on a.org = hort.nNodeID where a.isdel=0 and a.status!=50112 ");
		if(entity!=null)
		{
			if(entity.getStartDate()!=null&&!"".equals(entity.getStartDate()))
			{
				hql.append(" and str_to_date(a.date,'%Y-%m-%d') >=  str_to_date('"+entity.getStartDate()+"','%Y-%m-%d') ");
			}
			if(entity.getEndDate()!=null&&!"".equals(entity.getEndDate()))
			{
				hql.append(" and str_to_date(a.date,'%Y-%m-%d') <=  str_to_date('"+entity.getEndDate()+"','%Y-%m-%d') ");
			}
			if(Common.notEmpty(entity.getOrg()))
			{
				if(entity.getOrg().contains(",")){
					hql.append(" and a.org = '"+entity.getOrg()+"' ");
				}else{
					hql.append(" and a.org in ( select nNodeID from hh_organInfo_tree_relation where locate((select vcOrganID from hh_organInfo_tree_relation where nNodeID ='"+
							entity.getOrg()+"'),vcOrganID)>0)");
				}
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and a.status = '"+entity.getStatus()+ "' ");
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						hql.append(" hort.vcOrganID like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" hort.vcOrganID like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and hort.vcOrganID like '%--%' ");
			}
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public java.util.List<DataFundPosition> getExamineHrFormsListView(
			DataFundPosition entity, Integer offset, Integer pageSize) {
		if(entity==null)
			return new ArrayList<DataFundPosition>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from DataFundPosition d where 1=1 and isdel=0  and d.status!=50112");
		
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getOrg()))
			{
				if(entity.getOrg().contains(",")){
					hql.append(" and d.org = '"+entity.getOrg()+"' ");
				}else{
					hql.append(" and d.org in ( select h.id.nnodeId from HhOrganInfoTreeRelation h where locate((select hh.vcOrganId from HhOrganInfoTreeRelation hh where hh.id.nnodeId ='"+
							entity.getOrg()+"'),h.vcOrganId)>0)");
				}
			}
			if(entity.getStartDate()!=null&&!"".equals(entity.getStartDate()))
			{
				hql.append(" and str_to_date(d.date,'%Y-%m-%d') >=  str_to_date('"+entity.getStartDate()+"','%Y-%m-%d') ");
			}
			if(entity.getEndDate()!=null&&!"".equals(entity.getEndDate()))
			{
				hql.append(" and str_to_date(d.date,'%Y-%m-%d') <=  str_to_date('"+entity.getEndDate()+"','%Y-%m-%d') ");
			}
			if(entity.getStatus() !=null && !"".equals(entity.getStatus()))
			{
				hql.append(" and d.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i = 0; i < dd.length;i++){
					if(i == (dd.length -1)){
						hql.append(" d.authOrg like '%-"+dd[i]+ "-%' )");
					}else{
						hql.append(" d.authOrg like '%-"+dd[i]+ "-%' or ");
					}
				}
			}else{
				hql.append(" and d.authOrg like '%--%' ");
			}
		}
		hql.append(" order by date desc  ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offset!=null)
			query.setFirstResult(offset);
		if(pageSize!=null)
			query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public DataFundPosition getCash(String org) {
		String hql = " FROM DataFundPosition a where a.isdel=0 and a.org = '"+org+"' ORDER BY str_to_date(a.date,'%Y-%m-%d') DESC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.setMaxResults(1);
		return (DataFundPosition) query.uniqueResult();
	}
	
	
	@Override
	public Integer getAllchildernCompanyData(DataFundPosition entity,HhOrganInfoTreeRelation node){
		
		if(entity==null)
			return new Integer(0);
		
		StringBuilder  hql = new StringBuilder();
	
		if(entity!=null && null!=node)
		{
			hql.append(" SELECT count(*) FROM DataFundPosition a,HhOrganInfoTreeRelation b").
				append(" WHERE a.org = b.id.nnodeId and a.status=50115 and a.isdel=0 and b.status in (50500,50502)").
				append(" AND a.parentorg like '%").
				append(node.getVcOrganId()).
				append("%'");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());

		return Integer.parseInt(query.uniqueResult().toString());
		
	}
	
	/**
	 *子公司数据汇总
	 */
	@Override
	public List<DataFundPosition> findAllchildernCompany(DataFundPosition entity,HhOrganInfoTreeRelation node,Integer offset,Integer pageSize){
		
		if(entity==null)
			return new ArrayList<DataFundPosition>();
		
		StringBuilder  hql = new StringBuilder();
	
		if(entity!=null && null!=node)
		{
			hql.append(" SELECT a FROM DataFundPosition a,HhOrganInfoTreeRelation b").
				append(" WHERE a.org = b.id.nnodeId and a.status=50115 and a.isdel=0 and b.status in (50500,50502)").
				append(" AND a.parentorg like '%").
				append(node.getVcOrganId()).
				append("%'");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		
		if(offset!=null)
			query.setFirstResult(offset);
		if(pageSize!=null)
			query.setMaxResults(pageSize);
		return query.list();
	}
	
	
	
}
