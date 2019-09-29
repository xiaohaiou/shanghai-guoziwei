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
import com.softline.dao.report.IReportFinancingProjectProgressDao;
import com.softline.entity.ReportFinancingProjectProgress;
import com.softline.entity.ReportFinancingProjectProgressEnclosure;
import com.softline.entity.ReportFinancingProjectProgressLog;
import com.softline.entity.financing.ReportFinancingBond;
import com.softline.entity.financing.ReportFinancingInnovate;
import com.softline.entity.financing.ReportFinancingShare;
import com.softline.entity.taxTask.DataTaxPay;

@Repository(value = "reportFinancingProjectProgressDao")
/**
 * 
 * @author ky_tian
 *
 */
public class ReportFinancingProjectProgressDao implements IReportFinancingProjectProgressDao{
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
	public ReportFinancingProjectProgress getReportFinancingProjectProgress(Integer id)
	{
		if(id==null)
			return new ReportFinancingProjectProgress();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingProjectProgress s where 1=1 and isdel=0 and id="+id);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (ReportFinancingProjectProgress) query.uniqueResult();
	}
	
	/**
	 * 查询
	 * @param name 根据公司名称获取对应的信息。
	 * @return
	 */
	public List<ReportFinancingProjectProgress> getReportFinancingProjectProgressByCompanyName(String companyName){
		
		if(companyName==null)
			return new ArrayList<ReportFinancingProjectProgress>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingProjectProgress s where 1=1 and isdel=0 and s.status = 50115 and s.coreOrgname in ("+Common.dealinStr(companyName)+ ") order by s.expectAccountDate desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			
		return query.list();
	}
	
	
	/**
	 * 根据审核实体ID和审核种类获取
	 * @param examineentityid
	 * @param examinekind
	 * @return
	 */
	public List<ReportFinancingProjectProgress> getReportFinancingProjectProgressList(ReportFinancingProjectProgress entity ,Integer offsize,Integer pagesize,Integer status)
	{
		if(entity==null)
			return new ArrayList<ReportFinancingProjectProgress>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingProjectProgress s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreOrg()) )
			{
				hql.append(" and s.coreOrg in( "+Common.dealinStr(entity.getCoreOrg())+ ") ");
			}
			if(Common.notEmpty(entity.getOperateOrgname()) )
			{
				hql.append(" and s.operateOrgname like '%"+entity.getOperateOrgname()+ "%' ");
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
			if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
			{
				hql.append(" and s.status != "+Base.examstatus_1+ " ");
			}
			if(entity.getCategory()!=null)
			{
				hql.append(" and s.category = "+entity.getCategory()+ " ");
			}
			if(Common.notEmpty(entity.getStarttime()))
			{	
				hql.append(" and s.expectAccountDate >= '"+entity.getStarttime()+"' ");
			}
			if(Common.notEmpty(entity.getEndtime()))
			{	
				hql.append(" and s.expectAccountDate <= '"+entity.getEndtime()+"' ");
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
		hql.append(" order by s.expectAccountDate desc ");
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
	public int getReportFinancingProjectProgressListCount(ReportFinancingProjectProgress entity,Integer status)
	{
		if(entity==null)
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportFinancingProjectProgress s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreOrg()) )
			{
				hql.append(" and s.coreOrg in( "+Common.dealinStr(entity.getCoreOrg())+ ") ");
			}
			if(Common.notEmpty(entity.getOperateOrgname()) )
			{
				hql.append(" and s.operateOrgname like '%"+entity.getOperateOrgname()+ "%' ");
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
			if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
			{
				hql.append(" and s.status != "+Base.examstatus_1+ " ");
			}
			if(entity.getCategory()!=null)
			{
				hql.append(" and s.category like '%"+entity.getOperateOrgname()+ "%' ");
			}
			if(Common.notEmpty(entity.getStarttime()))
			{	
				hql.append(" and s.expectAccountDate >= '"+entity.getStarttime()+"' ");
			}
			if(Common.notEmpty(entity.getEndtime()))
			{	
				hql.append(" and s.expectAccountDate <= '"+entity.getEndtime()+"' ");
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
	public List<ReportFinancingProjectProgress> getEntityForOrg(ReportFinancingProjectProgress entity) {
		if(entity==null)
			return new ArrayList<ReportFinancingProjectProgress>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingProjectProgress s where 1=1 and isdel=0 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreOrg()) )
			{
				hql.append(" and s.coreOrg in( "+Common.dealinStr(entity.getCoreOrg())+ ") ");
			}
			if(Common.notEmpty(entity.getOperateOrgname()) )
			{
				hql.append(" and s.operateOrgname like '%"+entity.getOperateOrgname()+ "%' ");
			}
			if(entity.getProjectProgress()!=null)
			{
				hql.append(" and s.projectProgress = "+entity.getProjectProgress()+ " ");
			}
			if(entity.getStatus()!=null)
			{
				hql.append(" and s.status = "+entity.getStatus()+ " ");
			}
			if(entity.getGetOperateType()!=null && entity.getGetOperateType().equals(Base.fun_examine))
			{
				hql.append(" and s.status != "+Base.examstatus_1+ " ");
			}
			if(entity.getCategory()!=null)
			{
				hql.append(" and s.category = "+entity.getCategory()+ " ");
			}
			if(Common.notEmpty(entity.getStarttime()))
			{	
				hql.append(" and s.expectAccountDate >= '"+entity.getStarttime()+"' ");
			}
			if(Common.notEmpty(entity.getEndtime()))
			{	
				hql.append(" and s.expectAccountDate <= '"+entity.getEndtime()+"' ");
			}
	/*		if(Base.examstatus_2.equals(i)){
				hql.append(" and s.projectProgress = "+i+" ");
			}else{
				hql.append(" and s.projectProgress = "+i+" ");
			}*/
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i1 = 0; i1 < dd.length;i1++){
					if(i1 == (dd.length -1)){
						hql.append(" s.parentorg like '%-"+dd[i1]+ "-%' )");
					}else{
						hql.append(" s.parentorg like '%-"+dd[i1]+ "-%' or ");
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
	public List<ReportFinancingProjectProgressEnclosure> getOldEnclosures(
			Integer id) {
		String hql = "from ReportFinancingProjectProgressEnclosure a where a.financingProjectId = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void deleteLog(Integer id) {
		String sql = " update report_financing_project_progress_log set isdel = 1 where financing_project_id = " + id;
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<ReportFinancingProjectProgressLog> getLog(Integer id) {
		String hql = "from ReportFinancingProjectProgressLog a where a.financingProjectId = " + id + " and a.isdel = 0 order by a.updateDate desc ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	/**
	 * 保存时检查数据是否被能修改
	 * @param entity
	 * @return
	 */
	@Override
	public boolean checkCanEdit(ReportFinancingProjectProgress entity)
	{
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from ReportFinancingProjectProgress s where 1=1 and isdel=0 and status in("+Base.examCanEdit+") ");
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
	
	//海航实业融资项目情况汇总(除债券类)
	@Override
	public List<String> getProjectProgressList(ReportFinancingProjectProgress entity,String org) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" select (select description from hh_base where id = s.project_progress),COUNT(*),SUM(s.scale) from report_financing_project_progress s where 1=1 and s.isdel=0 ");
		if(entity!=null && null!=org)
		{
			if(!Common.notEmpty(entity.getCoreOrg()) ){
				return null;
			}else{
				hql.append(" and s.core_org in( "+Common.dealinStr(org)+ ") ");
			}
			hql.append(" and s.project_progress != '' ");
			hql.append(" and s.parentorg like '%" + entity.getParentorg() + "%' ");
		}
		hql.append(" group by s.project_progress ");
		hql.append(" order by s.expect_account_date desc ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return query.list();
	}
	//海航实业各业态推进中融资项目情况（除债券类）getCategoryList
	@Override
	public List<String> getCategoryList(ReportFinancingProjectProgress entity,String orgList,Integer offset, Integer pageSize) {
		StringBuilder  hql = new StringBuilder();
		hql.append(" SELECT a.core_orgname ");
		if(entity!=null)
		{
			if(!Common.notEmpty(entity.getCoreOrg()) ){
				return null;
			}else{
				hql.append(" ,(SELECT CAST(sum(s.already_account_amounts) AS DECIMAL(19,6)) FROM report_financing_project_progress s WHERE 1 = 1 AND s.isdel = 0 AND s.core_org IN ("+Common.dealinStr(orgList)+") AND s.category  = 351 AND s.parentorg LIKE '%-"+entity.getParentorg()+"-%' AND s.core_org=a.core_org ORDER BY s.expect_account_date DESC ), ");
				hql.append(" (SELECT CAST(sum(r.already_account_amounts) AS DECIMAL(19,6)) FROM report_financing_project_progress r WHERE 1 = 1 AND r.isdel = 0 AND r.core_org IN ("+Common.dealinStr(orgList)+") AND r.category != 351 AND r.parentorg LIKE '%-"+entity.getParentorg()+"-%' AND r.core_org=a.core_org ORDER BY r.expect_account_date DESC ) ");
			}
		}
		hql.append(" FROM report_financing_project_progress a WHERE 1 = 1 AND a.isdel = 0 AND a.core_org IN ("+Common.dealinStr(orgList)+") AND a.parentorg LIKE '%-"+entity.getParentorg()+"-%' ");
		hql.append(" GROUP BY a.core_org ");
		hql.append(" order by a.expect_account_date desc ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		if(offset!=null)
			query.setFirstResult(offset);
		if(pageSize!=null)
			query.setMaxResults(pageSize);
		return query.list();

	}
	
	/**
	 * 汇总
	 * 虚拟公司数据不存在
	 */
	@Override
	public List<ReportFinancingProjectProgress> sumDataForSunCompanys(ReportFinancingProjectProgress beanIn) {
		
		if(null == beanIn || null ==beanIn.getParentorg())
			return new ArrayList<ReportFinancingProjectProgress>();
		
		// TODO Auto-generated method stub
		StringBuilder  hql = new StringBuilder();
	    hql.append(" select sum( a.already_account_amounts ) AS already_account_amounts, sum( a.scale ) AS scale ");
	    hql.append(" FROM report_financing_project_progress a,hh_organInfo_tree_relation b  where 1=1 ");
	    if(null != beanIn.getCategory())
	    	hql.append(" and a.category = "+beanIn.getCategory());    
	    hql.append(" and a.STATUS= 50115 AND a.isdel = 0 AND a.core_org=b.nNodeID and a.core_org IN ( SELECT nNodeID FROM ");
	    hql.append(" hh_organInfo_tree_relation WHERE vcParentID like  ");
	    hql.append(" '%"+beanIn.getParentorg()+"%' AND STATUS in (50500,50502) ) ");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());	
		
		return query.list();
	}
	
	@Override
	public List<ReportFinancingProjectProgress> sumBeansForSunCompanys(ReportFinancingProjectProgress beanIn,Integer offsize,Integer pagesize){
		
		if(null == beanIn || null ==beanIn.getParentorg())
			return new ArrayList<ReportFinancingProjectProgress>();
		StringBuilder  hql = new StringBuilder();
		
		hql.append(" select a from ReportFinancingProjectProgress a, HhOrganInfoTreeRelation b where 1=1 ");
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
	public int sumBeansForSunCompanys(ReportFinancingProjectProgress beanIn){
		
		if(null == beanIn || null ==beanIn.getParentorg())
			return 0;
		StringBuilder  hql = new StringBuilder();
		
		hql.append(" select count(a) from ReportFinancingProjectProgress a,HhOrganInfoTreeRelation b where 1=1 ");
	    if(null != beanIn.getCategory())
	    	hql.append(" and a.category = "+beanIn.getCategory());  
	    hql.append(" and a.status= 50115 AND a.isdel = 0 AND a.coreOrg=b.id.nnodeId and a.coreOrg IN ( SELECT id.nnodeId FROM ");
	    hql.append(" HhOrganInfoTreeRelation WHERE vcParentId like  ");
	    hql.append(" '%"+beanIn.getParentorg()+"%' AND status in (50500,50502) ) ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());	
		
		return Integer.valueOf(query.uniqueResult().toString());		
	}

	@Override
	public List<ReportFinancingBond> getEntityForBond(ReportFinancingProjectProgress entity,String org) {
		if(entity==null)
			return new ArrayList<ReportFinancingBond>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingBond s where 1=1 and isdel=0 and projectProgress=303 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreOrg()) )
			{
				hql.append(" and s.coreOrg in( "+Common.dealinStr(org)+ ") ");
			}
			if(Common.notEmpty(entity.getOperateOrgname()) )
			{
				hql.append(" and s.operateOrgname like '%"+entity.getOperateOrgname()+ "%' ");
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i1 = 0; i1 < dd.length;i1++){
					if(i1 == (dd.length -1)){
						hql.append(" s.parentorg like '%-"+dd[i1]+ "-%' )");
					}else{
						hql.append(" s.parentorg like '%-"+dd[i1]+ "-%' or ");
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
	public List<ReportFinancingInnovate> getEntityForInvocate(ReportFinancingProjectProgress entity,String org) {
		if(entity==null)
			return new ArrayList<ReportFinancingInnovate>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingInnovate s where 1=1 and isdel=0 and projectProgress=303 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreOrg()) )
			{
				hql.append(" and s.coreOrg in( "+Common.dealinStr(org)+ ") ");
			}
			if(Common.notEmpty(entity.getOperateOrgname()) )
			{
				hql.append(" and s.operateOrgname like '%"+entity.getOperateOrgname()+ "%' ");
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i1 = 0; i1 < dd.length;i1++){
					if(i1 == (dd.length -1)){
						hql.append(" s.parentorg like '%-"+dd[i1]+ "-%' )");
					}else{
						hql.append(" s.parentorg like '%-"+dd[i1]+ "-%' or ");
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
	public List<ReportFinancingShare> getEntityForShare(ReportFinancingProjectProgress entity,String org) {
		if(entity==null)
			return new ArrayList<ReportFinancingShare>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingShare s where 1=1 and isdel=0 and projectProgress=303 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreOrg()) )
			{
				hql.append(" and s.coreOrg in( "+Common.dealinStr(org)+ ") ");
			}
			if(Common.notEmpty(entity.getOperateOrgname()) )
			{
				hql.append(" and s.operateOrgname like '%"+entity.getOperateOrgname()+ "%' ");
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i1 = 0; i1 < dd.length;i1++){
					if(i1 == (dd.length -1)){
						hql.append(" s.parentorg like '%-"+dd[i1]+ "-%' )");
					}else{
						hql.append(" s.parentorg like '%-"+dd[i1]+ "-%' or ");
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
	public List<ReportFinancingProjectProgress> getEntityForProgress(ReportFinancingProjectProgress entity,String org) {
		if(entity==null)
			return new ArrayList<ReportFinancingProjectProgress>();
		StringBuilder  hql = new StringBuilder();
		hql.append(" from ReportFinancingProjectProgress s where 1=1 and isdel=0 and projectProgress=303 ");
		if(entity!=null)
		{
			if(Common.notEmpty(entity.getCoreOrg()) )
			{
				hql.append(" and s.coreOrg in( "+Common.dealinStr(org)+ ") ");
			}
			if(Common.notEmpty(entity.getOperateOrgname()) )
			{
				hql.append(" and s.operateOrgname like '%"+entity.getOperateOrgname()+ "%' ");
			}
			if(Common.notEmpty(entity.getParentorg())){
				hql.append(" and (");
				String [] dd = entity.getParentorg().split(",");
				for(int i1 = 0; i1 < dd.length;i1++){
					if(i1 == (dd.length -1)){
						hql.append(" s.parentorg like '%-"+dd[i1]+ "-%' )");
					}else{
						hql.append(" s.parentorg like '%-"+dd[i1]+ "-%' or ");
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
	public int isVirtualCompany(String organalId){
		
		if(null == organalId || "".equals(organalId))
			return 0;
		StringBuilder  hql = new StringBuilder();
		hql.append("select h.status from HhOrganInfoTreeRelation h where h.id.nnodeId = '").append(organalId).append("'");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());	
		
		return Integer.valueOf(query.uniqueResult().toString());			
	}
}
