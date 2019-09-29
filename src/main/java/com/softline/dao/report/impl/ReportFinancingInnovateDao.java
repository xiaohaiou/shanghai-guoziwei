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
import com.softline.dao.report.IReportFinancingInnovateDao;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.financing.ReportFinancingBond;
import com.softline.entity.financing.ReportFinancingInnovate;
import com.softline.entity.financing.ReportFinancingInnovateEnclosure;
import com.softline.entity.financing.ReportFinancingInnovateLog;

@Repository(value = "reportFinancingInnovateDao")
/**
 * 
 * @author ky_tian
 *
 */
public class ReportFinancingInnovateDao implements IReportFinancingInnovateDao{
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
	public ReportFinancingInnovate getReportFinancingInnovate(Integer id)
	{
		if(id==null)
			return new ReportFinancingInnovate();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingInnovate s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (ReportFinancingInnovate) query.uniqueResult();
	}
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportFinancingInnovate> getReportFinancingInnovateList(ReportFinancingInnovate entity ,Integer offsize,Integer pagesize,Integer status)
	{
		if(entity==null)
			return new ArrayList<ReportFinancingInnovate>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingInnovate s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreOrg()) )
			{
				hql.append(" and s.coreOrg in( "+Common.dealinStr(entity.getCoreOrg())+ ") ");
			}
			if(entity.getProjectProgress()!=null)
			{
				hql.append(" and s.projectProgress = "+entity.getProjectProgress()+ " ");
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and s.status = "+entity.getStatus()+ " ");
			}
			if(status==Base.examstatus_4)
			{
				hql.append(" and s.status in ('50113','50115') ");
			}
			if(entity.getProjectName()!=null)
			{
				hql.append(" and s.projectName like '%"+entity.getProjectName()+"%' ");
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
	public int getReportFinancingInnovateListCount(ReportFinancingInnovate entity,Integer status)
	{
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportFinancingInnovate s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreOrg()) )
			{
				hql.append(" and s.coreOrg in( "+Common.dealinStr(entity.getCoreOrg())+ ") ");
			}
			if(entity.getProjectProgress()!=null)
			{
				hql.append(" and s.projectProgress = "+entity.getProjectProgress()+ " ");
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and s.status = "+entity.getStatus()+ " ");
			}
			if(status==Base.examstatus_4)
			{
				hql.append(" and s.status in ('50113','50115') ");
			}
			if(entity.getProjectName()!=null)
			{
				hql.append(" and s.projectName like '%"+entity.getProjectName()+"%' ");
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
	
	/**
	 * 根据org获取导出数据
	 */
	public List<ReportFinancingInnovate> getEntityForOrg(ReportFinancingInnovate entity)
	{
		if(entity==null)
			return new ArrayList<ReportFinancingInnovate>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingInnovate s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreOrg()) )
			{
				hql.append(" and s.coreOrg in( "+Common.dealinStr(entity.getCoreOrg())+ ") ");
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and s.status = "+entity.getStatus()+ " ");
			}
			if(entity.getProjectName()!=null)
			{
				hql.append(" and s.projectName like '%"+entity.getProjectName()+"%' ");
			}
			hql.append(" and s.parentorg like '%" + entity.getParentorg() + "%' ");
		}
		hql.append(" order by s.lastModifyDate desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
	/**
	 * 根据org获取导出数据
	 */
	@Override
	public List<ReportFinancingInnovate> getEntityForOrgForRevise(ReportFinancingInnovate entity)
	{
		if(entity==null)
			return new ArrayList<ReportFinancingInnovate>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingInnovate s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreOrg()) )
			{
				hql.append(" and s.coreOrg in( "+Common.dealinStr(entity.getCoreOrg())+ ") ");
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and s.status = "+entity.getStatus()+ " ");
			}
			if(entity.getProjectName()!=null)
			{
				hql.append(" and s.projectName like '%"+entity.getProjectName()+"%' ");
			}
			if(entity.getProjectProgress()!=null)
			{
				hql.append(" and s.projectProgress = "+entity.getProjectProgress()+ " ");
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
		hql.append(" order by s.lastModifyDate desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
	
	

	@Override
	public List<ReportFinancingInnovateEnclosure> getOldEnclosures(
			Integer id) {
		String hql = "from ReportFinancingInnovateEnclosure a where a.financingProjectId = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void deleteLog(Integer id) {
		String sql = " update report_financing_innovate_log set isdel = 1 where financing_project_id = " + id;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<ReportFinancingInnovateLog> getLog(Integer id) {
		String hql = "from ReportFinancingInnovateLog a where a.financingProjectId = " + id + " and a.isdel = 0 order by a.updateDate desc ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	@Override
	public boolean checkCanEdit(ReportFinancingInnovate entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportFinancingInnovate s where 1=1 and isdel=0 and status in("+Base.examCanEdit+") ");
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
	 * 查询
	 * @param name 根据公司名称获取对应的信息。
	 * @return
	 */
	public List<ReportFinancingInnovate> getReportFinancingInnovateByCompanyName(String companyName){
		
		if(companyName==null)
			return new ArrayList<ReportFinancingInnovate>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingInnovate s where 1=1 and isdel=0 and s.status = 50115 and s.coreOrgname in ("+Common.dealinStr(companyName)+ ") order by s.expectAccountDate desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			
		return query.list();
	}
	
	/**
	 * 汇总
	 * 虚拟公司数据不存在
	 */
	@Override
	public List<ReportFinancingInnovate> sumDataForSunCompanys(ReportFinancingInnovate beanIn) {
		
		if(null == beanIn || null ==beanIn.getParentorg())
			return new ArrayList<ReportFinancingInnovate>();
		
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
	    hql.append(" select sum( a.already_account_amounts ) AS already_account_amounts, sum( a.scale ) AS scale ");
	    hql.append(" FROM report_financing_innovate a,hh_organInfo_tree_relation b  where 1=1 ");   
	    hql.append(" and a.STATUS= 50115 AND a.isdel = 0 AND a.core_org=b.nNodeID and a.core_org IN ( SELECT nNodeID FROM ");
	    hql.append(" hh_organInfo_tree_relation WHERE vcParentID like  ");
	    hql.append(" '%"+beanIn.getParentorg()+"%' AND STATUS in (50500,50502) ) ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());	
		
		return query.list();
	}
	
	@Override
	public List<ReportFinancingInnovate> sumBeansForSunCompanys(ReportFinancingInnovate beanIn,Integer offsize,Integer pagesize){
		
		if(null == beanIn || null ==beanIn.getParentorg())
			return new ArrayList<ReportFinancingInnovate>();
		StringBuilder  hql = new StringBuilder();
		
		hql.append(" select a from ReportFinancingInnovate a, HhOrganInfoTreeRelation b where 1=1 ");
	    hql.append(" and a.status= 50115 AND a.isdel = 0 AND a.coreOrg=b.id.nnodeId and a.coreOrg IN ( SELECT id.nnodeId FROM ");
	    hql.append(" HhOrganInfoTreeRelation WHERE vcParentId like  ");
	    hql.append(" '%"+beanIn.getParentorg()+"%' AND status in (50500,50502) ) ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());	
		
		if(offsize!=null)
			query.setFirstResult(offsize);
		if(pagesize!=null)
			query.setMaxResults(pagesize);
		return query.list();		
	}
	
	@Override
	public int sumBeansForSunCompanys(ReportFinancingInnovate beanIn){
		
		if(null == beanIn || null ==beanIn.getParentorg())
			return 0;
		StringBuilder  hql = new StringBuilder();
		
		hql.append(" select count(a) from ReportFinancingInnovate a,HhOrganInfoTreeRelation b where 1=1 ");
	    hql.append(" and a.status= 50115 AND a.isdel = 0 AND a.coreOrg=b.id.nnodeId and a.coreOrg IN ( SELECT id.nnodeId FROM ");
	    hql.append(" HhOrganInfoTreeRelation WHERE vcParentId like  ");
	    hql.append(" '%"+beanIn.getParentorg()+"%' AND status in (50500,50502) ) ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());	
		
		return Integer.valueOf(query.uniqueResult().toString());		
	}
	
	@Override
	public int isVirtualCompany(String organalId){
		
		if(null == organalId || "".equals(organalId))
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select h.status from HhOrganInfoTreeRelation h where h.id.nnodeId = '").append(organalId).append("'");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());	
		
		return Integer.valueOf(query.uniqueResult().toString());			
	}
	
}
