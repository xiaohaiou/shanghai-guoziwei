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
import com.softline.dao.report.IReportFinancingShareDao;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.financing.ReportFinancingShare;
import com.softline.entity.financing.ReportFinancingShareEnclosure;
import com.softline.entity.financing.ReportFinancingShareLog;

@Repository(value = "reportFinancingShareDao")
/**
 * 
 * @author ky_tian
 *
 */
public class ReportFinancingShareDao implements IReportFinancingShareDao{
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
	public ReportFinancingShare getReportFinancingShare(Integer id)
	{
		if(id==null)
			return new ReportFinancingShare();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingShare s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (ReportFinancingShare) query.uniqueResult();
	}
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportFinancingShare> getReportFinancingShareList(ReportFinancingShare entity ,Integer offsize,Integer pagesize,Integer status)
	{
		if(entity==null)
			return new ArrayList<ReportFinancingShare>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingShare s where 1=1 and isdel=0 ");
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
			if(entity.getCategory()!=null)
			{
				hql.append(" and s.category = "+entity.getCategory()+ " ");
			}
			if(entity.getFinancialInstitution()!=null)
			{
				hql.append(" and s.financialInstitution like '%"+entity.getFinancialInstitution()+"%' ");
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
	public int getReportFinancingShareListCount(ReportFinancingShare entity,Integer status)
	{
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportFinancingShare s where 1=1 and isdel=0 ");
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
			if(entity.getCategory()!=null)
			{
				hql.append(" and s.category = "+entity.getCategory()+ " ");
			}
			if(entity.getFinancialInstitution()!=null)
			{
				hql.append(" and s.financialInstitution like '%"+entity.getFinancialInstitution()+"%' ");
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
	public List<ReportFinancingShare> getEntityForOrg(ReportFinancingShare entity) {
		if(entity==null)
			return new ArrayList<ReportFinancingShare>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingShare s where 1=1 and isdel=0 ");
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
			if(entity.getProjectProgress()!=null)
			{
				hql.append(" and s.projectProgress = "+entity.getProjectProgress()+ " ");
			}
			if(entity.getCategory()!=null)
			{
				hql.append(" and s.category = "+entity.getCategory()+ " ");
			}
			if(entity.getFinancialInstitution()!=null)
			{
				hql.append(" and s.financialInstitution like '%"+entity.getFinancialInstitution()+"%' ");
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
	
	//海航实业各业态推进中融资项目情况（除债券类）getCategoryList
	@Override
	public List<String> getDataList(ReportFinancingShare entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append("select s.core_orgname,(select description from hh_base where id = s.category),s.hypothecation_information from report_financing_share s where 1=1 and s.isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreOrg()) )
			{
				hql.append(" and s.core_org in( "+Common.dealinStr(entity.getCoreOrg())+ ") ");
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
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<ReportFinancingShareEnclosure> getOldEnclosures(
			Integer id) {
		String hql = "from ReportFinancingShareEnclosure a where a.financingProjectId = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void deleteLog(Integer id) {
		String sql = " update report_financing_share_log set isdel = 1 where financing_project_id = " + id;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<ReportFinancingShareLog> getLog(Integer id) {
		String hql = "from ReportFinancingShareLog a where a.financingProjectId = " + id + " and a.isdel = 0 order by a.updateDate desc ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	@Override
	public boolean checkCanEdit(ReportFinancingShare entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportFinancingShare s where 1=1 and isdel=0 and status in("+Base.examCanEdit+") ");
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
	public List<ReportFinancingShare> getReportFinancingShareByCompanyName(String companyName){
		
		if(companyName==null)
			return new ArrayList<ReportFinancingShare>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingShare s where 1=1 and isdel=0 and status = 50115 and s.coreOrgname in ("+Common.dealinStr(companyName)+ ") order by s.createDate desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			
		return query.list();
	}
	
	/**
	 * 汇总
	 * 虚拟公司数据不存在
	 */
	@Override
	public List<ReportFinancingShare> sumDataForSunCompanys(ReportFinancingShare beanIn) {
		
		if(null == beanIn || null ==beanIn.getParentorg())
			return new ArrayList<ReportFinancingShare>();
		
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
	    hql.append(" select sum( a.already_account_amounts ) AS already_account_amounts, sum( a.scale ) AS scale ");
	    hql.append(" FROM report_financing_share a,hh_organInfo_tree_relation b  where 1=1 ");
	    if(null != beanIn.getCategory())
	    	hql.append(" and a.category = "+beanIn.getCategory());    
	    hql.append(" and a.STATUS= 50115 AND a.isdel = 0 AND a.core_org=b.nNodeID and a.core_org IN ( SELECT nNodeID FROM ");
	    hql.append(" hh_organInfo_tree_relation WHERE vcParentID like  ");
	    hql.append(" '%"+beanIn.getParentorg()+"%' AND STATUS in (50500,50502) ) ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());	
		
		return query.list();
	}
	
	@Override
	public List<ReportFinancingShare> sumBeansForSunCompanys(ReportFinancingShare beanIn,Integer offsize,Integer pagesize){
		
		if(null == beanIn || null ==beanIn.getParentorg())
			return new ArrayList<ReportFinancingShare>();
		StringBuilder  hql = new StringBuilder();
		
		hql.append(" select a from ReportFinancingShare a, HhOrganInfoTreeRelation b where 1=1 ");
	    if(null != beanIn.getCategory())
	    	hql.append(" and a.category = "+beanIn.getCategory());  
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
	public int sumBeansForSunCompanys(ReportFinancingShare beanIn){
		
		if(null == beanIn || null ==beanIn.getParentorg())
			return 0;
		StringBuilder  hql = new StringBuilder();
		
		hql.append(" select count(a) from ReportFinancingShare a,HhOrganInfoTreeRelation b where 1=1 ");
	    if(null != beanIn.getCategory())
	    	hql.append(" and a.category = "+beanIn.getCategory());  
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
