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
import com.softline.dao.report.IReportFinancingBondDao;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.financing.ReportFinancingBond;
import com.softline.entity.financing.ReportFinancingBondEnclosure;
import com.softline.entity.financing.ReportFinancingBondLog;

@Repository(value = "reportFinancingBondDao")
/**
 * 
 * @author ky_tian
 *
 */
public class ReportFinancingBondDao implements IReportFinancingBondDao{
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
	public ReportFinancingBond getReportFinancingBond(Integer id)
	{
		if(id==null)
			return new ReportFinancingBond();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingBond s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (ReportFinancingBond) query.uniqueResult();
	}
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportFinancingBond> getReportFinancingBondList(ReportFinancingBond entity ,Integer offsize,Integer pagesize,Integer status)
	{
		if(entity==null)
			return new ArrayList<ReportFinancingBond>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingBond s where 1=1 and isdel=0 ");
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
			if(Common.notEmpty(entity.getStarttime()))
			{	
				hql.append(" and s.issuanceOfTime >= '"+entity.getStarttime()+"' ");
			}
			if(Common.notEmpty(entity.getEndtime()))
			{	
				hql.append(" and s.issuanceOfTime <= '"+entity.getEndtime()+"' ");
			}
			
			if(Common.notEmpty(entity.getResponsibleOrganization()) )
			{
				hql.append(" and s.responsibleOrganization like '%"+entity.getResponsibleOrganization()+"%' ");
			}
			if(Common.notEmpty(entity.getCommitmentShallSubject()) )
			{
				hql.append(" and s.commitmentShallSubject like '%"+entity.getCommitmentShallSubject()+"%' ");
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
	public int getReportFinancingBondListCount(ReportFinancingBond entity,Integer status)
	{
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportFinancingBond s where 1=1 and isdel=0 ");
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
			if(Common.notEmpty(entity.getStarttime()))
			{	
				hql.append(" and s.issuanceOfTime >= '"+entity.getStarttime()+"' ");
			}
			if(Common.notEmpty(entity.getEndtime()))
			{	
				hql.append(" and s.issuanceOfTime <= '"+entity.getEndtime()+"' ");
			}
			
			if(Common.notEmpty(entity.getResponsibleOrganization()) )
			{
				hql.append(" and s.responsibleOrganization like '%"+entity.getResponsibleOrganization()+"%' ");
			}
			if(Common.notEmpty(entity.getCommitmentShallSubject()) )
			{
				hql.append(" and s.commitmentShallSubject like '%"+entity.getCommitmentShallSubject()+"%' ");
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
	
	//海航实业各业态推进中融资项目情况（除债券类）getCategoryList
	@Override
	public List<String> getDataList(ReportFinancingBond entity) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT a.core_orgname,COUNT(*),sum(scale),sum(a.already_account_amounts) FROM report_financing_bond a WHERE 1 = 1 AND a.isdel = 0  ");
		if(Common.notEmpty(entity.getCoreOrg()) )
		{
			hql.append(" and a.core_org in( "+Common.dealinStr(entity.getCoreOrg())+ ") ");
		}
		hql.append(" GROUP BY a.core_org ");
		hql.append(" order by a.issuance_of_time desc ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return query.list();
	}
	
	/**
	 * 根据org获取导出数据
	 */
	public List<ReportFinancingBond> getEntityForOrg(ReportFinancingBond entity)
	{
		if(entity==null)
			return new ArrayList<ReportFinancingBond>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingBond s where 1=1 and isdel=0 ");
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
			if(Common.notEmpty(entity.getStarttime()))
			{	
				hql.append(" and s.issuanceOfTime >= '"+entity.getStarttime()+"' ");
			}
			if(Common.notEmpty(entity.getEndtime()))
			{	
				hql.append(" and s.issuanceOfTime <= '"+entity.getEndtime()+"' ");
			}
			
			if(Common.notEmpty(entity.getResponsibleOrganization()) )
			{
				hql.append(" and s.responsibleOrganization like '%"+entity.getResponsibleOrganization()+"%' ");
			}
			if(Common.notEmpty(entity.getCommitmentShallSubject()) )
			{
				hql.append(" and s.commitmentShallSubject like '%"+entity.getCommitmentShallSubject()+"%' ");
			}
			if(null!= entity.getParentorg() && Common.notEmpty(entity.getParentorg())){
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
				//hql.append(" and s.parentorg like '%--%' ");
			}
//			hql.append(" and s.parentorg like '%" + entity.getParentorg() + "%' ");
		}
		hql.append(" order by s.lastModifyDate desc ");

		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<ReportFinancingBondEnclosure> getOldEnclosures(
			Integer id) {
		String hql = "from ReportFinancingBondEnclosure a where a.financingProjectId = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void deleteLog(Integer id) {
		String sql = " update report_financing_bond_log set isdel = 1 where financing_project_id = " + id;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<ReportFinancingBondLog> getLog(Integer id) {
		String hql = "from ReportFinancingBondLog a where a.financingProjectId = " + id + " and a.isdel = 0 order by a.updateDate desc ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	@Override
	public boolean checkCanEdit(ReportFinancingBond entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportFinancingBond s where 1=1 and isdel=0 and status in("+Base.examCanEdit+") ");
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
	public List<ReportFinancingBond> getReportFinancingBondByCompanyName(String companyName){
		
		if(companyName==null)
			return new ArrayList<ReportFinancingBond>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingBond s where 1=1 and isdel=0 and status = 50115 and s.coreOrgname in ("+Common.dealinStr(companyName)+ ") order by s.lastModifyDate desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			
		return query.list();
	}
	
	/**
	 * 汇总
	 * 虚拟公司数据不存在
	 */
	@Override
	public List<ReportFinancingBond> sumDataForSunCompanys(ReportFinancingBond beanIn) {
		
		if(null == beanIn || null ==beanIn.getParentorg())
			return new ArrayList<ReportFinancingBond>();
		
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
	    hql.append(" select sum( a.already_account_amounts ) AS already_account_amounts, sum( a.scale ) AS scale ");
	    hql.append(" FROM report_financing_bond a,hh_organInfo_tree_relation b  where 1=1 ");
	    hql.append(" and a.STATUS= 50115 AND a.isdel = 0 AND a.core_org=b.nNodeID and a.core_org IN ( SELECT nNodeID FROM ");
	    hql.append(" hh_organInfo_tree_relation WHERE vcParentID like  ");
	    hql.append(" '%"+beanIn.getParentorg()+"%' AND STATUS in (50500,50502) ) ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());	
		
		return query.list();
	}
	
	@Override
	public List<ReportFinancingBond> sumBeansForSunCompanys(ReportFinancingBond beanIn,Integer offsize,Integer pagesize){
		
		if(null == beanIn || null ==beanIn.getParentorg())
			return new ArrayList<ReportFinancingBond>();
		StringBuilder  hql = new StringBuilder();
		
		hql.append(" select a from ReportFinancingBond a, HhOrganInfoTreeRelation b where 1=1 ");  
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
	public int sumBeansForSunCompanys(ReportFinancingBond beanIn){
		
		if(null == beanIn || null ==beanIn.getParentorg())
			return 0;
		StringBuilder  hql = new StringBuilder();
		
		hql.append(" select count(a) from ReportFinancingBond a,HhOrganInfoTreeRelation b where 1=1 "); 
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
