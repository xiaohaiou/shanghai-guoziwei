package com.softline.dao.bimr.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.bimr.IBimrInsideAuditWeeklyDao;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditWeekly;

@Repository(value = "iBimrInsideAuditWeeklyDao")
public class BimrInsideAuditWeeklyDao implements IBimrInsideAuditWeeklyDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer getBimrInsideAuditWeeklyListCount(BimrInsideAuditWeekly entity, String dataAuthority) {
        if(entity == null){
            return 0;
        }
        StringBuilder  hql = new StringBuilder();
        hql.append("select count(0) from BimrInsideAuditWeekly s where 1=1 and s.isDel=0");
        if(entity != null) {
           /* if(Common.notEmpty(entity.getAuditStartDate()))	{
                hql.append(" and s.auditStartDate >= date('"+entity.getAuditStartDate()+ "')");
            }
            if(Common.notEmpty(entity.getAuditEndDate())) {
                hql.append(" and s.auditEndDate <= date('"+entity.getAuditEndDate()+ "')");
            }*/
            /*if(entity.getAuditDept() == null) {
                hql.append(" and s.auditDept in( "+dataAuthority+")");
            }else {
                hql.append(" and s.auditDept = "+entity.getAuditDept());
            }*/
            if(Common.notEmpty(entity.getProjectName())) {
                hql.append(" and s.projectName like '%"+entity.getProjectName()+ "%'");
            }
            if(entity.getProjectId() != null) {
                hql.append(" and s.projectId = "+entity.getProjectId());
            }
           /* if (Common.notEmpty(entity.getAuditDeptedName())){
                hql.append(" and s.auditDeptedName like '%"+entity.getAuditDeptedName()+"%'");
            }
            if(entity.getStatus() != null) {
                hql.append(" and s.status = "+entity.getStatus());
            }
            if(entity.getEstatus() != null) {
                hql.append(" and s.estatus = "+entity.getEstatus());
            }
            if (entity.getAuditProjectProp() != null){
                hql.append(" and s.auditProjectProp = " + entity.getAuditProjectProp());
            }
            if (entity.getAuditImplDeptId() != null){
                hql.append(" and s.auditImplDeptId = " + entity.getAuditImplDeptId());
            }
            if (Common.notEmpty(entity.getAuditImplDeptName())){
                hql.append(" and s.auditImplDeptName like '%" + entity.getAuditImplDeptName() + "%'");
            }
            if (entity.getIsChildren() != null){
                hql.append(" and s.isChildren = " + entity.getIsChildren());
            }*/
        }
        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
        return Integer.parseInt(query.uniqueResult().toString());
    }
	@Override
	public List<BimrInsideAuditWeekly> getBimrInsideAuditWeeklyList(
			int projectid) {
		if(projectid==0) {
            return new ArrayList<BimrInsideAuditWeekly>();
        }
        StringBuilder  hql = new StringBuilder();
        hql.append("from BimrInsideAuditWeekly s where 1=1 and s.isDel=0");
        if(projectid != 0) {
            hql.append(" and s.projectId = "+projectid);
        }
        hql.append(" order by s.createDate desc ");
        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
        return query.list();
	}

    @Override
    public List<BimrInsideAuditWeekly> getBimrInsideAuditWeeklyList(BimrInsideAuditWeekly entity, Integer offset, Integer pageSize, String dataAuthority) {
        if(entity==null) {
            return new ArrayList<BimrInsideAuditWeekly>();
        }
        StringBuilder  hql = new StringBuilder();
        hql.append("from BimrInsideAuditWeekly s where 1=1 and s.isDel=0");
        if(entity!=null) {
            /*if(Common.notEmpty(entity.getAuditStartDate()))	{
                hql.append(" and s.auditStartDate >= date('"+entity.getAuditStartDate()+ "')");
            }
            if(Common.notEmpty(entity.getAuditEndDate())) {
                hql.append(" and s.auditEndDate <= date('"+entity.getAuditEndDate()+ "')");
            }*/
            /*if(entity.getAuditDept() == null) {
                hql.append(" and s.auditDept in( "+dataAuthority+")");
            }else {
                hql.append(" and s.auditDept = "+entity.getAuditDept());
            }*/
            if(Common.notEmpty(entity.getProjectName())) {
                hql.append(" and s.projectName like '%"+entity.getProjectName()+ "%'");
            }
            if(entity.getProjectId() != null) {
                hql.append(" and s.projectId = "+entity.getProjectId());
            }
            /*if (Common.notEmpty(entity.getAuditDeptedName())){
                hql.append(" and s.auditDeptedName like '%"+entity.getAuditDeptedName()+"%'");
            }
            if(entity.getStatus() != null) {
                hql.append(" and s.status = "+entity.getStatus());
            }
            if(entity.getEstatus() != null) {
                hql.append(" and s.estatus = "+entity.getEstatus());
            }
            if (entity.getAuditProjectProp() != null){
                hql.append(" and s.auditProjectProp = " + entity.getAuditProjectProp());
            }
            if (entity.getAuditImplDeptId() != null){
                hql.append(" and s.auditImplDeptId = " + entity.getAuditImplDeptId());
            }
            if (Common.notEmpty(entity.getAuditImplDeptName())){
                hql.append(" and s.auditImplDeptName like '%" + entity.getAuditImplDeptName()+"%'");
            }
            if (entity.getIsChildren() != null){
                hql.append(" and s.isChildren = " + entity.getIsChildren());
            }*/
        }
        hql.append(" order by s.createDate desc ");
        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
        if(offset != null){
            query.setFirstResult(offset);
        }
        if(pageSize != null){
            query.setMaxResults(pageSize);
        }
        return query.list();
    }

    @Override
    public Integer saveBimrInsideAuditWeekly(BimrInsideAuditWeekly entity) {
        return (Integer) sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void updateBimrInsideAuditWeekly(BimrInsideAuditWeekly entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void deleteBimrInsideAuditWeekly(Integer id) {
        String hql = "update BimrInsideAuditWeekly s set s.isDel = 1" + " where s.id = " + id + "";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.executeUpdate();
    }

    @Override
    public BimrInsideAuditWeekly getBimrInsideAuditWeeklyById(Integer id) {
        String hql = " from BimrInsideAuditWeekly s where s.isDel = 0 and s.id = " + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return (BimrInsideAuditWeekly)query.uniqueResult();
    }

    @Override
    public BimrInsideAuditWeekly getBimrInsideAuditWeekly(BimrInsideAuditWeekly entity) {
        String hql = "from BimrInsideAuditWeekly s where s.isDel = 0 and s.id = " + entity.getId();
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return (BimrInsideAuditWeekly)query.uniqueResult();
    }

	@Override
	public List<BimrInsideAuditWeekly> getBimrInsideAuditWeeklyHasNoChild() {
		String hql = "from BimrInsideAuditWeekly s where s.isDel = 0 and s.isChildren = 0";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

    @Override
    public List<BimrInsideAuditWeekly> getBimrInsideAuditWeeklyForList(Integer status) {
        StringBuilder  hql = new StringBuilder();
        hql.append("from BimrInsideAuditWeekly s where 1=1 and s.isDel=0 and s.status="+status);
        hql.append(" order by s.createDate desc ");
        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
        return query.list();
    }

    @Override
    public List<BimrInsideAuditWeekly> getBimrInsideAuditWeeklyForChildren(Integer id) {
        StringBuilder hql = new StringBuilder();
        hql.append("from BimrInsideAuditWeekly s where 1=1 and s.isDel=0 and s.auditParentId = " + id);
        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
        return query.list();
    }

//	@Override
//	public List<BimrInsideAuditWeekly> getInsideExport1(BimrInsideAuditWeekly entity, String dataAuthority,String vcEmployeeId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	 @Override
	 public List<Object[]> getInsideExport1(BimrInsideAuditWeekly entity,BimrInsideAuditProject entity1, String dataAuthority,String vcEmployeeId) {
//	        if(entity==null) {
//	            return new ArrayList<object[]>();
//	        }
	        StringBuilder  hql = new StringBuilder();
//	        hql.append("from BimrInsideAuditWeekly s,rqrqr h where 1=1 and s.isDel=0");
//	        hql.append("select  project_name,project_status_progress from  bimr_inside_audit_weekly  where 1=1 and is_del=0");
//	        hql.append("select  s.project_name,s.project_status_progress,a.is_important,a.create_date from  bimr_inside_audit_weekly s,bimr_inside_audit_project a WHERE 1=1 ");
	        hql.append("SELECT p.is_important ,p.audit_project_prop,p.audit_project_name," +
	        		"(SELECT w.project_status_progress " +"from  bimr_inside_audit_weekly w where w.project_id " +
	        		"= p.id and w.is_del = 0  ORDER BY w.week_start_date DESC LIMIT 1 ) ," +
	        		" p.create_date,p.audit_start_date,p.audit_end_date " +
	        		"from bimr_inside_audit_project p where p.is_del = 0 and 1=1 ");
	        if(entity!=null&& entity1!=null) {
	           
	        	
	        	if(Common.notEmpty(entity1.getAuditStartDate()))	{
	                hql.append(" and p.audit_start_date >= date('"+entity1.getAuditStartDate()+ "')");
	            }
	            
	            if(Common.notEmpty(entity1.getAuditEndDate())) {
	                hql.append(" and p.audit_end_date <= date('"+entity1.getAuditEndDate()+ "')");
	            }
	        	
	        	if(entity1.getIsImportant()!=null) {
	        		
	        		 hql.append(" and p.is_important like '%"+entity1.getIsImportant()+ "%'");
//	                 hql.append(" and s.projectName like '%"+entity.getProjectName()+ "%'");
	             }
	        	 if(Common.notEmpty(entity1.getAuditProjectName())) {
	                 hql.append(" and p.audit_project_name like '%"+entity1.getAuditProjectName()+ "%'");
	             }
//	        	 项目名称
//	        	 if(Common.notEmpty(entity.getAuditProjectName())) {
//		                hql.append(" and p.audit_project_name like '%"+entity.getAuditProjectName()+ "%'");
//		            }
	        	 /*if(entity1.getAuditImplDeptName() != null) {
	                 hql.append(" and p.audit_impl_dept_name like '%"+entity1.getAuditImplDeptName()+ "%'");
	             }*/
	        	 
	        	 
	        	 if(entity.getProjectStatusProgress()  != null) {
	                 hql.append(" and w.project_status_progress like '%"+entity.getProjectStatusProgress()+ "%'");
	             }
	        	 
//	        	 项目状态
//	             if(entity.getProjectId() != null) {
//	                 hql.append(" and s.projectId = "+entity.getProjectId());
//	             }
	        	 if (Common.notEmpty(entity1.getAuditImplDeptId())) {
	             	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+entity1.getAuditImplDeptId()+"))");
	 			}else if(Common.notEmpty(dataAuthority)){
	             	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+dataAuthority+"))");
	             }
	            if(Common.notEmpty(vcEmployeeId)){
	                hql.append(" or p.project_principal_id = " + vcEmployeeId+" )");
	            }
	            
	            if (entity1.getAuditProjectProp() != null){
	                hql.append(" and p.audit_project_prop = " + entity1.getAuditProjectProp());
	            }
	           
	               
	            
	        }
	        hql.append(" order by create_date desc ");
	        Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());

	        return query.list();
	    }
	
	
}
