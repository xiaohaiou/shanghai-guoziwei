package com.softline.dao.bimr.impl;

import com.softline.common.Common;
import com.softline.dao.bimr.IBimrInsideAuditProjectDao;
import com.softline.entity.AuditProject;
import com.softline.entity.AuditProjectFindQuestion;
import com.softline.entity.bimr.BimrInsideAuditFeedback;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditQuestion;
import com.softline.entity.bimr.BimrInsideAuditWeekly;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository(value = "iBimrInsideAuditProjectDao")
public class BimrInsideAuditProjectDao implements IBimrInsideAuditProjectDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer getBimrInsideAuditProjectListCount(BimrInsideAuditProject entity, String dataAuthority, String type, String vcEmployeeId) {
        if(entity == null){
            return 0;
        }
        StringBuilder  hql = new StringBuilder();
        hql.append("select count(0) from BimrInsideAuditProject s where 1=1 and s.isDel=0");
        if(entity != null) {
            if(Common.notEmpty(entity.getAuditStartDate()))	{
                hql.append(" and s.auditStartDate >= date('"+entity.getAuditStartDate()+ "')");
            }
            if(Common.notEmpty(entity.getAuditEndDate())) {
                hql.append(" and s.auditEndDate <= date('"+entity.getAuditEndDate()+ "')");
            }
            if (Common.notEmpty(entity.getAuditImplDeptId())) {
            	hql.append(" and  ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+entity.getAuditImplDeptId()+"))");
			}else if(Common.notEmpty(dataAuthority)){
            	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+dataAuthority+"))");
            }
            if(Common.notEmpty(vcEmployeeId)){
                hql.append(" or s.projectPrincipalId = " + vcEmployeeId+" )");
            }
            
            if(Common.notEmpty(entity.getAuditProjectName())) {
                hql.append(" and s.auditProjectName like '%"+entity.getAuditProjectName()+ "%'");
            }
            if (Common.notEmpty(entity.getAuditDeptedName())){
                hql.append(" and s.auditDeptedName like '%"+entity.getAuditDeptedName()+"%'");
            }
            if(entity.getStatus() != null) {
                hql.append(" and s.status = "+entity.getStatus());
            }
            if (Common.notEmpty(entity.getMorestatus())) {
            	 hql.append(" and s.status in ("+entity.getMorestatus()+") ");
			}
            if(entity.getEstatus() != null) {
                hql.append(" and s.estatus = "+entity.getEstatus());
            }
            if (entity.getAuditProjectProp() != null){
                hql.append(" and s.auditProjectProp = " + entity.getAuditProjectProp());
            }
            /*if (Common.notEmpty(entity.getAuditImplDeptId())){
                hql.append(" and s.auditImplDeptId in ( " + entity.getAuditImplDeptId()+") ");
            }
            if (Common.notEmpty(entity.getAuditImplDeptName())){
                hql.append(" and s.auditImplDeptName like '%" + entity.getAuditImplDeptName() + "%'");
            }*/
            
            if (type.equals("0")){
                hql.append(" and s.isChildren = 0 ");
            }
            if (type.equals("1")){
                hql.append(" and s.isChildren = 1 ");
            }
            
            if(entity.getIsImportant()!= null && !entity.getIsImportant().equals("")){
				
				hql.append("and is_important = '"+entity.getIsImportant()+"' ");	
		}
//            if (entity.getIsImportant()!=null) {
//            	hql.append(" and s.is_important =  " + entity.getIsImportant()+" ");
//				
//			}
        }
        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
        return Integer.parseInt(query.uniqueResult().toString());
    }
//查询办结审计项目  
    @Override
    public List<BimrInsideAuditProject> getBimrInsideAuditProjectList(BimrInsideAuditProject entity, Integer offset, Integer pageSize, String dataAuthority, String type, String vcEmployeeId) {
        if(entity==null) {
            return new ArrayList<BimrInsideAuditProject>();
        }
        StringBuilder  hql = new StringBuilder();
        hql.append("from BimrInsideAuditProject s where 1=1 and s.isDel=0");
        if(entity!=null) {
            
//        	创建时间 
        	
        	if(Common.notEmpty(entity.getAuditStartDate()))	{
                hql.append(" and s.auditStartDate >= date('"+entity.getAuditStartDate()+ "')");
            }
            
            if(Common.notEmpty(entity.getAuditEndDate())) {
                hql.append(" and s.auditEndDate <= date('"+entity.getAuditEndDate()+ "')");
            }
            if (Common.notEmpty(entity.getAuditImplDeptId())) {
            	hql.append(" and (  s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+entity.getAuditImplDeptId()+"))");
			}else if(Common.notEmpty(dataAuthority)){
            	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+dataAuthority+"))");
            }
            if(Common.notEmpty(vcEmployeeId)){
                hql.append(" or s.projectPrincipalId = " + vcEmployeeId+" )");
            }
            
         
            if(Common.notEmpty(entity.getAuditProjectName())) {
                hql.append(" and s.auditProjectName like '%"+entity.getAuditProjectName()+ "%'");
            }
//            审计项目名称
            
            if(Common.notEmpty(entity.getAuditProjectCode())) {
                hql.append(" and s.auditProjectCode like '%"+entity.getAuditProjectCode()+ "%'");
            }
            if (Common.notEmpty(entity.getAuditDeptedName())){
                hql.append(" and s.auditDeptedName like '%"+entity.getAuditDeptedName()+"%'");
            }
//            审计实施单位
            if(entity.getStatus() != null) {
                hql.append(" and s.status = "+entity.getStatus());
            }
            if (Common.notEmpty(entity.getMorestatus())) {
           	 hql.append(" and s.status in ("+entity.getMorestatus()+") ");
			}
//            项目状态
            if(entity.getEstatus() != null) {
                hql.append(" and s.estatus = "+entity.getEstatus());
            }
//            审核状态
            if (entity.getAuditProjectProp() != null){
                hql.append(" and s.auditProjectProp = " + entity.getAuditProjectProp());
            }
            /*if (Common.notEmpty(entity.getAuditImplDeptId())){
                hql.append(" and s.auditImplDeptId in (" + entity.getAuditImplDeptId()+") ");
            }*/
            /*if (Common.notEmpty(entity.getAuditImplDeptName())){
                hql.append(" and s.auditImplDeptName like '%" + entity.getAuditImplDeptName()+"%'");
            }*/
//            被审计实施单位
            if (type.equals("0")){
                hql.append(" and s.isChildren = 0 ");
            }
            if (type.equals("1")){
                hql.append(" and s.isChildren = 1 ");
            }
//            项目类别
            if(entity.getIsImportant()!= null && !entity.getIsImportant().equals("")){
				
				hql.append("and is_important = '"+entity.getIsImportant()+"' ");										
		}
//            if (entity.getIsImportant()!=null) {
//            	hql.append(" and s.is_important = " + entity.getIsImportant()+" ");
//				
//			}
           
           
               
            
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
    public Integer saveBimrInsideAuditProject(BimrInsideAuditProject entity) {
        return (Integer) sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void updateBimrInsideAuditProject(BimrInsideAuditProject entity) {
        sessionFactory.getCurrentSession().update(entity);
    }
    
    @Override
    public void deleteBimrInsideAuditProjectAppendix(String projectId){
    	String sql = "delete from bimr_inside_audit_project_appendix where project_id = " + projectId;
		sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
    }

    @Override
    public void deleteBimrInsideAuditProject(Integer id) {
        String hql = "update BimrInsideAuditProject s set s.isDel = 1" + " where s.id = " + id + "";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.executeUpdate();
    }

    @Override
    public BimrInsideAuditProject getBimrInsideAuditProjectById(Integer id) {
        String hql = " from BimrInsideAuditProject s where s.isDel = 0 and s.id = " + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return (BimrInsideAuditProject)query.uniqueResult();
    }

    @Override
    public BimrInsideAuditProject getBimrInsideAuditProject(BimrInsideAuditProject entity) {
        String hql = "from BimrInsideAuditProject s where s.isDel = 0 and s.id = " + entity.getId();
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return (BimrInsideAuditProject)query.uniqueResult();
    }

	@Override
	public List<BimrInsideAuditProject> getBimrInsideAuditProjectHasNoChild(Integer status) {
		String hql = "from BimrInsideAuditProject s where s.isDel = 0 and s.isChildren = 0 and s.status ="+status;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

    @Override
    public List<BimrInsideAuditProject> getBimrInsideAuditProjectsForList(Integer status) {
        StringBuilder  hql = new StringBuilder();
        hql.append("from BimrInsideAuditProject s where 1=1 and s.isDel=0 and s.status="+status);
        hql.append(" order by s.createDate desc ");
        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
        return query.list();
    }

    @Override
    public List<BimrInsideAuditProject> getBimrInsideAuditProjectForChildren(Integer id) {
        StringBuilder hql = new StringBuilder();
        hql.append("from BimrInsideAuditProject s where 1=1 and s.isDel=0 and s.auditParentId = " + id);
        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
        return query.list();
    }

    @Override
    public List<BimrInsideAuditProject> getBimrInsideAuditProjectsIsOrNotChild(Integer isChildProject) {
        String hql = "from BimrInsideAuditProject s where s.isDel = 0 and s.isChildren = " + isChildProject;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<BimrInsideAuditProject> getBimrInsideAuditProjectsByParent(Integer parent_status) {
        StringBuilder sql = new StringBuilder();
        sql.append("(SELECT * FROM bimr_inside_audit_project t WHERE t.is_children = 1 and t.is_del = 0 AND t.audit_parent_id IN");
        sql.append(" (");
        sql.append(" SELECT p.id FROM bimr_inside_audit_project p");
        sql.append(" WHERE p.is_children = 0 and p.is_del = 0 AND p.`status` = ");
        sql.append(parent_status);
        sql.append("))");
        sql.append(" UNION ");
        sql.append("(SELECT * FROM bimr_inside_audit_project t WHERE t.is_del = 0 AND t.`status` = ");
        sql.append(parent_status);
        sql.append(")");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString()).addEntity(BimrInsideAuditProject.class);
        return query.list();
    }
    
    @Override
    public List<BimrInsideAuditProject> getBimrProjectByCode(String code,String type){
    	String hql = "from BimrInsideAuditProject s where s.isDel = 0 and s.isChildren = " + type;
    	hql += " and s.auditProjectCode like '"+code+"%' order by s.auditProjectCode desc";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }
    
    @Override
    public Integer getInsideAuditProjectWeekReportListCount(BimrInsideAuditProject entity, String dataAuthority, String type, String vcEmployeeId) {
        if(entity == null){
            return 0;
        }
        StringBuilder  hql = new StringBuilder();
        hql.append("select count(0) from BimrInsideAuditProject s where 1=1 and s.isDel=0");
        if(entity != null) {
            if(Common.notEmpty(entity.getAuditStartDate()))	{
                hql.append(" and s.auditStartDate >= date('"+entity.getAuditStartDate()+ "')");
            }
            if(Common.notEmpty(entity.getAuditEndDate())) {
                hql.append(" and s.auditEndDate <= date('"+entity.getAuditEndDate()+ "')");
            }
            if(Common.notEmpty(entity.getAuditProjectName())) {
                hql.append(" and s.auditProjectName like '%"+entity.getAuditProjectName()+ "%'");
            }
            if (Common.notEmpty(entity.getAuditImplDeptId())) {
            	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+entity.getAuditImplDeptId()+"))");
			} else if(Common.notEmpty(dataAuthority)){
            	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+dataAuthority+"))");
            }
            if(Common.notEmpty(vcEmployeeId)){
                hql.append(" or s.projectPrincipalId = " + vcEmployeeId +" )");
            }
            
            if (Common.notEmpty(entity.getAuditDeptedName())){
                hql.append(" and s.auditDeptedName like '%"+entity.getAuditDeptedName()+"%'");
            }
            if(entity.getStatus() != null) {
                hql.append(" and s.status = "+entity.getStatus());
            }
            if (Common.notEmpty(entity.getMorestatus())) {
            	 hql.append(" and s.status in ("+entity.getMorestatus()+") ");
			}
            if(entity.getEstatus() != null) {
                hql.append(" and s.estatus = "+entity.getEstatus());
            }
            if (entity.getAuditProjectProp() != null){
                hql.append(" and s.auditProjectProp = " + entity.getAuditProjectProp());
            }
            /*if (Common.notEmpty(entity.getAuditImplDeptId())){
                hql.append(" and s.auditImplDeptId = " + entity.getAuditImplDeptId());
            }
            if (Common.notEmpty(entity.getAuditImplDeptName())){
                hql.append(" and s.auditImplDeptName like '%" + entity.getAuditImplDeptName() + "%'");
            }*/
            if (type.equals("0")){
                hql.append(" and s.isChildren = 0 ");
            }
            if (type.equals("1")){
                hql.append(" and s.isChildren = 1 ");
            }
        }
        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
        return Integer.parseInt(query.uniqueResult().toString());
    }

    @Override
    public List<BimrInsideAuditProject> getInsideAuditProjectWeekReportList(BimrInsideAuditProject entity, Integer offset, Integer pageSize, String dataAuthority, String type, String vcEmployeeId) {
        if(entity==null) {
            return new ArrayList<BimrInsideAuditProject>();
        }
        StringBuilder  hql = new StringBuilder();
        hql.append("from BimrInsideAuditProject s where 1=1 and s.isDel=0");
        if(entity!=null) {
            if(Common.notEmpty(entity.getAuditStartDate()))	{
                hql.append(" and s.auditStartDate >= date('"+entity.getAuditStartDate()+ "')");
            }
            if(Common.notEmpty(entity.getAuditEndDate())) {
                hql.append(" and s.auditEndDate <= date('"+entity.getAuditEndDate()+ "')");
            }
            if (Common.notEmpty(entity.getAuditImplDeptId())) {
            	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+entity.getAuditImplDeptId()+"))");
			}else if(Common.notEmpty(dataAuthority)){
            	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+dataAuthority+"))");
            }
            if(Common.notEmpty(vcEmployeeId)){
                hql.append(" or s.projectPrincipalId = " + vcEmployeeId +" )");
            }
            
            if(Common.notEmpty(entity.getAuditProjectName())) {
                hql.append(" and s.auditProjectName like '%"+entity.getAuditProjectName()+ "%'");
            }
            if(Common.notEmpty(entity.getAuditProjectCode())) {
                hql.append(" and s.auditProjectCode like '%"+entity.getAuditProjectCode()+ "%'");
            }
            if (Common.notEmpty(entity.getAuditDeptedName())){
                hql.append(" and s.auditDeptedName like '%"+entity.getAuditDeptedName()+"%'");
            }
            if(entity.getStatus() != null) {
                hql.append(" and s.status = "+entity.getStatus());
            }
            if (Common.notEmpty(entity.getMorestatus())) {
           	 hql.append(" and s.status in ("+entity.getMorestatus()+") ");
			}
            if(entity.getEstatus() != null) {
                hql.append(" and s.estatus = "+entity.getEstatus());
            }
            if (entity.getAuditProjectProp() != null){
                hql.append(" and s.auditProjectProp = " + entity.getAuditProjectProp());
            }
            if (type.equals("0")){
                hql.append(" and s.isChildren = 0 ");
            }
            if (type.equals("1")){
                hql.append(" and s.isChildren = 1 ");
            }
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
    public Integer getInsideAuditProjectQuestionListCount(BimrInsideAuditProject entity, String dataAuthority, String type, String vcEmployeeId) {
        if(entity == null){
            return 0;
        }
        StringBuilder  hql = new StringBuilder();
        hql.append("select count(0) from BimrInsideAuditProject s where 1=1 and s.isDel=0");
        if(entity != null) {
            if(Common.notEmpty(entity.getAuditStartDate()))	{
                hql.append(" and s.auditStartDate >= date('"+entity.getAuditStartDate()+ "')");
            }
            if(Common.notEmpty(entity.getAuditEndDate())) {
                hql.append(" and s.auditEndDate <= date('"+entity.getAuditEndDate()+ "')");
            }
            if (Common.notEmpty(entity.getAuditImplDeptId())) {
            	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+entity.getAuditImplDeptId()+"))");
			}else if(Common.notEmpty(dataAuthority)){
            	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+dataAuthority+"))");
            }
            if(Common.notEmpty(vcEmployeeId)){
                hql.append(" or s.projectPrincipalId = " + vcEmployeeId +" )");
            }
            
            if(Common.notEmpty(entity.getAuditProjectName())) {
                hql.append(" and s.auditProjectName like '%"+entity.getAuditProjectName()+ "%'");
            }
            if (Common.notEmpty(entity.getAuditDeptedName())){
                hql.append(" and s.auditDeptedName like '%"+entity.getAuditDeptedName()+"%'");
            }
            if(entity.getStatus() != null) {
                hql.append(" and s.status = "+entity.getStatus());
            }
            if (Common.notEmpty(entity.getMorestatus())) {
            	 hql.append(" and s.status in ("+entity.getMorestatus()+") ");
			}
            if(entity.getEstatus() != null) {
                hql.append(" and s.estatus = "+entity.getEstatus());
            }
            if (entity.getAuditProjectProp() != null){
                hql.append(" and s.auditProjectProp = " + entity.getAuditProjectProp());
            }
            /*if (Common.notEmpty(entity.getAuditImplDeptId())){
                hql.append(" and s.auditImplDeptId = " + entity.getAuditImplDeptId());
            }
            if (Common.notEmpty(entity.getAuditImplDeptName())){
                hql.append(" and s.auditImplDeptName like '%" + entity.getAuditImplDeptName() + "%'");
            }*/
            if (type.equals("0")){
                hql.append(" and s.isChildren = 0 ");
            }
            if (type.equals("1")){
                hql.append(" and s.isChildren = 1 ");
            }
        }
        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
        return Integer.parseInt(query.uniqueResult().toString());
    }

    @Override
    public List<BimrInsideAuditProject> getInsideAuditProjectQuestionList(BimrInsideAuditProject entity, Integer offset, Integer pageSize, String dataAuthority, String type, String vcEmployeeId) {
        if(entity==null) {
            return new ArrayList<BimrInsideAuditProject>();
        }
        StringBuilder  hql = new StringBuilder();
        hql.append("from BimrInsideAuditProject s where 1=1 and s.isDel=0");
        if(entity!=null) {
            if(Common.notEmpty(entity.getAuditStartDate()))	{
                hql.append(" and s.auditStartDate >= date('"+entity.getAuditStartDate()+ "')");
            }
            if(Common.notEmpty(entity.getAuditEndDate())) {
                hql.append(" and s.auditEndDate <= date('"+entity.getAuditEndDate()+ "')");
            }
            if (Common.notEmpty(entity.getAuditImplDeptId())) {
            	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+entity.getAuditImplDeptId()+"))");
			}else if(Common.notEmpty(dataAuthority)){
            	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+dataAuthority+"))");
            }
            if(Common.notEmpty(vcEmployeeId)){
                hql.append(" or s.projectPrincipalId = " + vcEmployeeId +" )");
            }
            
            if(Common.notEmpty(entity.getAuditProjectName())) {
                hql.append(" and s.auditProjectName like '%"+entity.getAuditProjectName()+ "%'");
            }
            if(Common.notEmpty(entity.getAuditProjectCode())) {
                hql.append(" and s.auditProjectCode like '%"+entity.getAuditProjectCode()+ "%'");
            }
            if (Common.notEmpty(entity.getAuditDeptedName())){
                hql.append(" and s.auditDeptedName like '%"+entity.getAuditDeptedName()+"%'");
            }
            if(entity.getStatus() != null) {
                hql.append(" and s.status = "+entity.getStatus());
            }
            if (Common.notEmpty(entity.getMorestatus())) {
           	 hql.append(" and s.status in ("+entity.getMorestatus()+") ");
			}
            if(entity.getEstatus() != null) {
                hql.append(" and s.estatus = "+entity.getEstatus());
            }
            if (entity.getAuditProjectProp() != null){
                hql.append(" and s.auditProjectProp = " + entity.getAuditProjectProp());
            }
            /*if (Common.notEmpty(entity.getAuditImplDeptId())){
                hql.append(" and s.auditImplDeptId = " + entity.getAuditImplDeptId());
            }
            if (Common.notEmpty(entity.getAuditImplDeptName())){
                hql.append(" and s.auditImplDeptName like '%" + entity.getAuditImplDeptName()+"%'");
            }*/
            if (type.equals("0")){
                hql.append(" and s.isChildren = 0 ");
            }
            if (type.equals("1")){
                hql.append(" and s.isChildren = 1 ");
            }
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

//	@Override
//	public List<BimrInsideAuditProject> getInsideExport(BimrInsideAuditProject entity, String dataAuthority) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	  导出
	 @Override
	 public List<BimrInsideAuditProject> getInsideExport(BimrInsideAuditProject entity, String dataAuthority,String vcEmployeeId) {
	        if(entity==null) {
	            return new ArrayList<BimrInsideAuditProject>();
	        }
	        StringBuilder  hql = new StringBuilder();

	        hql.append("from BimrInsideAuditProject s where 1=1 and s.isDel=0");
//	        hql.append("select  a.audit_impl_dept_name,s.project_status_progress,a.audit_project_name,a.create_date from  bimr_inside_audit_weekly s,bimr_inside_audit_project a WHERE 1=1 ");
	        if(entity!=null) {
//	            if(Common.notEmpty(entity.getAuditStartDate()))	{
//	                hql.append(" and s.auditStartDate >= date('"+entity.getAuditStartDate()+ "')");
//	            }
//	            if(Common.notEmpty(entity.getAuditEndDate())) {
//	                hql.append(" and s.auditEndDate <= date('"+entity.getAuditEndDate()+ "')");
//	            }
	        	if (Common.notEmpty(entity.getAuditImplDeptId())) {
	            	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+entity.getAuditImplDeptId()+"))");
				}else if(Common.notEmpty(dataAuthority)){
	            	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+dataAuthority+"))");
	            }
	            if(Common.notEmpty(vcEmployeeId)){
	                hql.append(" or s.projectPrincipalId = " + vcEmployeeId+" )");
	            }
	            
	            if(Common.notEmpty(entity.getAuditProjectName())) {
	                hql.append(" and s.auditProjectName like '%"+entity.getAuditProjectName()+ "%'");
	            }
//	            if(Common.notEmpty(entity.getAuditProjectCode())) {
//	                hql.append(" and s.auditProjectCode like '%"+entity.getAuditProjectCode()+ "%'");
//	            }
	            if (Common.notEmpty(entity.getAuditDeptedName())){
	                hql.append(" and s.auditDeptedName like '%"+entity.getAuditDeptedName()+"%'");
	            }
//	            
	            if (Common.notEmpty(entity.getAuditImplDeptName())){
	                hql.append(" and s.auditImplDeptName like '%" + entity.getAuditImplDeptName()+"%'");
	            }

//	            项目类别
	            if(entity.getIsImportant()!= null && !entity.getIsImportant().equals("")){
					
					hql.append("and is_important = '"+entity.getIsImportant()+"' ");										
			}

	           
	           
	               
	            
	        }
	        hql.append(" order by s.createDate desc ");
	        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());

	        return query.list();
	    }
	 
	 
//	  报告台账导出
	 /**
	  *  序号	所属产业集团	单位名称		审计项目开始日期
        	审计现场结束日期	项目负责人		报告最终审批人	报批公文号
	  */
	 @Override
	 public List<Object[]> getInsideExport1(BimrInsideAuditProject entity,BimrInsideAuditQuestion entity1, String dataAuthority,String vcEmployeeId) {
	        if(entity==null) {
	            return new ArrayList<Object[]>();
	        }
	        StringBuilder  hql = new StringBuilder();

//	        hql.append("from BimrInsideAuditProject s where 1=1 and s.isDel=0");
	        hql.append("select s.audit_depted_name," +
	        		"s.audit_impl_dept_name," +
	        		"s.audit_project_prop," +
	        		"s.audit_project_name," +
	        		"s.audit_start_date," +
	        		"s.audit_end_date," +
	        		"s.project_principal," +
	        		"q.is_suspected,s.examine_person_name," +
	        		"s.doc_num,s.create_date,s.audit_project_code" +
	        		" from bimr_inside_audit_project s,bimr_inside_audit_question q where 1=1 and q.project_id=s.id and  q.is_del=0  ");
	        
//	        hql.append("SELECT p.audit_impl_dept_name ," +
//	        		"(SELECT w.project_status_progress " +"from  bimr_inside_audit_weekly w where w.project_id " +
//	        		"= p.id and w.is_del = 0  ORDER BY w.week_start_date DESC LIMIT 1 ) ," +
//	        		" p.audit_project_name,p.create_date,p.audit_impl_dept_id " +
//	        		"from bimr_inside_audit_project p where p.is_del = 0 and 1=1 ");
	        if(entity!=null) {
	            if(Common.notEmpty(entity.getAuditStartDate()))	{
	                hql.append(" and s.audit_start_date >= date('"+entity.getAuditStartDate()+ "')");
	            }
//	            起始时间3
	            if(Common.notEmpty(entity.getAuditEndDate())) {
	                hql.append(" and s.audit_end_date <= date('"+entity.getAuditEndDate()+ "')");
	            }
//	            终止时间4
	            
	            if(Common.notEmpty(entity.getProjectPrincipal())) {
	                hql.append(" and s.project_principal like '%"+entity.getProjectPrincipal()+ "%'");
	            } 
	            
//	            项目负责人projectprincipal
	            
	            if(Common.notEmpty(entity.getExaminePersonName())) {
	                hql.append(" and s.examine_person_name like '%"+entity.getExaminePersonName()+ "%'");
	            }     
//	            报告最终审批人examinePersonName
	            
	            if(Common.notEmpty(entity.getDocNum())) {
	                hql.append(" and s.doc_num like '%"+entity.getDocNum()+ "%'");
	            }    
	            
//	            报批公文号
	            if (Common.notEmpty(entity.getAuditImplDeptId())) {
	            	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+entity.getAuditImplDeptId()+"))");
				}else if(Common.notEmpty(dataAuthority)){
	            	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+dataAuthority+"))");
	            }
	            if(Common.notEmpty(vcEmployeeId)){
	                hql.append(" or s.project_principal_id = " + vcEmployeeId+" )");
	            }
	            
	            if(Common.notEmpty(entity.getAuditProjectName())) {
	                hql.append(" and s.audit_project_name like '%"+entity.getAuditProjectName()+ "%'");
	            }
//	            审计项目名称
	            if(Common.notEmpty(entity.getAuditProjectCode())) {
	                hql.append(" and s.audit_project_code like '%"+entity.getAuditProjectCode()+ "%'");
	            }
	            if (Common.notEmpty(entity.getAuditDeptedName())){
	                hql.append(" and s.audit_depted_name like '%"+entity.getAuditDeptedName()+"%'");
	            }
//	            审计实施单位（产品集团）1
	            
	            if (entity.getAuditProjectProp() != null){
	                hql.append(" and s.audit_project_prop = " + entity.getAuditProjectProp());
	            }
//	            审计项目性质
	            /*if (Common.notEmpty(entity.getAuditImplDeptId())){
	                hql.append(" and audit_impl_dept_id = " + entity.getAuditImplDeptId());
	            }
	            if (Common.notEmpty(entity.getAuditImplDeptName())){
	                hql.append(" and s.audit_impl_dept_name like '%" + entity.getAuditImplDeptName()+"%'");
	            }*/
//	            单位名称2

//	            if (entity.getEstatus()!=null){
//	                hql.append(" and s.estatus like '%" + entity.getEstatus()+"%'");
//	            }
	            if(entity.getIsImportant()!= null && !entity.getIsImportant().equals("")){
	            	
					hql.append("and s.is_important = '"+entity.getIsImportant()+"' ");										
			}

	           
	            if(entity1.getIsSuspected()!=null) {
	                hql.append(" and q.is_suspected like '%"+entity1.getIsSuspected()+ "%'");
	            }
//	            是否涉及舞弊isSuspected111是0000不是
	           
	               
	            
	        }
	        hql.append(" order by s.create_date desc ");
	        Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());

	        return query.list();
	    }

//	@Override
//	public List<Object[]> getInsideExportXMDC(BimrInsideAuditProject entity,
//			BimrInsideAuditWeekly entity1, String dataAuthority,
//			String vcEmployeeId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
		
	
	
	
	@Override
	 public List<Object[]> getInsideExportXMDC(BimrInsideAuditProject entity,BimrInsideAuditWeekly entity1, String dataAuthority,String vcEmployeeId) {
	        if(entity==null) {
	            return new ArrayList<Object[]>();
	        }
	        StringBuilder  hql = new StringBuilder();

	        hql.append("SELECT p.audit_impl_dept_name ," +
	        		"(SELECT w.project_status_progress " +"from  bimr_inside_audit_weekly w where w.project_id " +
	        		"= p.id and w.is_del = 0  ORDER BY w.week_start_date DESC LIMIT 1 ) ," +
	        		" p.audit_project_name,p.create_date,p.audit_impl_dept_id,p.audit_start_date,p.audit_End_date " +
	        		"from bimr_inside_audit_project p where p.is_del = 0 and 1=1 ");
	        if(entity!=null) {

	        	
	        	if(Common.notEmpty(entity.getAuditStartDate()))	{
	                hql.append(" and p.audit_start_date >= date('"+entity.getAuditStartDate()+ "')");
	            }
	            
	            if(Common.notEmpty(entity.getAuditEndDate())) {
	                hql.append(" and p.audit_end_date <= date('"+entity.getAuditEndDate()+ "')");
	            }
	        	
	            if (Common.notEmpty(entity.getAuditImplDeptId())) {
	            	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+entity.getAuditImplDeptId()+"))");
				}else if(Common.notEmpty(dataAuthority)){
	            	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+dataAuthority+"))");
	            }
	            if(Common.notEmpty(vcEmployeeId)){
	                hql.append(" or p.project_principal_id = " + vcEmployeeId+" )");
	            }
	            
	            if(Common.notEmpty(entity.getAuditProjectName())) {
	                hql.append(" and p.audit_project_name like '%"+entity.getAuditProjectName()+ "%'");
	            }
	           
	        
	            if (entity.getAuditProjectProp() != null){
	                hql.append(" and p.audit_project_prop = " + entity.getAuditProjectProp());
	            }

	            /*if (Common.notEmpty(entity.getAuditImplDeptName())){
	                hql.append(" and p.audit_impl_dept_name like '%" + entity.getAuditImplDeptName()+"%'");
	            }
	            if (Common.notEmpty(entity.getAuditImplDeptId())){
	                hql.append(" and p.audit_impl_dept_id like '%" + entity.getAuditImplDeptId()+"%'");
	            }*/

	               
	            
	        }
	        hql.append(" order by p.create_date asc ");
	        Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());

	        return query.list();
	    }

	 
	//审计结果应用反馈表
	@Override
	public List<Object[]> getResultsDC(BimrInsideAuditProject entity,
			BimrInsideAuditQuestion entity1,AuditProjectFindQuestion entity2,AuditProject entity3, String dataAuthority,
			String vcEmployeeId) {
//		if(entity==null) {
//            return new ArrayList<Object[]>();
//        }
        StringBuilder  hql = new StringBuilder();

        hql.append(" SELECT " + 
        		"p.audit_impl_dept_name,"+
        		"p.audit_project_name,"+
        		"count(q.audit_suggest),"+
        	" SUM(CASE m.`status` WHEN 2 THEN 1	ELSE 0 END) / COUNT(m.id),p.doc_num,"+
        	" SUM(CASE q.audit_question_types WHEN 0 THEN 1 ELSE 0 END), "+
			" SUM(CASE q.audit_question_types WHEN 1 THEN 1 ELSE 0 END),"+
			" SUM(CASE q.audit_question_types WHEN 2 THEN 1 ELSE 0 END),"+
			" SUM(CASE q.audit_question_types WHEN 3 THEN 1 ELSE 0 END),"+
			" SUM(CASE q.audit_question_types WHEN 4 THEN 1 ELSE 0 END),"+
			" SUM(CASE q.audit_question_types WHEN 5 THEN 1 ELSE 0 END),"+
			" SUM(CASE q.audit_question_types WHEN 6 THEN 1 ELSE 0 END),"+
			" SUM(CASE q.audit_question_types WHEN 7 THEN 1 ELSE 0 END),"+
			" SUM(CASE q.audit_question_types WHEN 8 THEN 1 ELSE 0 END),"+
			" SUM(CASE q.audit_question_types WHEN '其他' THEN 1 ELSE 0 END),"+
			" SUM(CASE q.risk_driver_types WHEN 0 THEN 1 ELSE 0 END),"+
			" SUM(CASE q.risk_driver_types WHEN 1 THEN 1 ELSE 0 END),"+
			" SUM(CASE q.risk_driver_types WHEN 2 THEN 1 ELSE 0 END),"+
			" SUM(CASE q.risk_driver_types WHEN 3 THEN 1 ELSE 0 END),"+
			" SUM(CASE q.risk_driver_types WHEN 4 THEN 1 ELSE 0 END),"+
			" SUM(CASE q.risk_driver_types WHEN '其他' THEN 1 ELSE 0 END)" +
        	" FROM bimr_inside_audit_project p"+
        	" LEFT JOIN bimr_inside_audit_question q ON p.id = q.project_id"+
        	" AND q.is_del = 0 AND p.is_del = 0 "+
        	" LEFT JOIN bimr_inside_audit_measures m ON q.id = m.project_id " +
        	" WHERE 1=1 " 
        	);
//        单位名称
        if (entity !=null ) {
        	
        	if (Common.notEmpty(entity.getAuditImplDeptName())){
                hql.append(" and p.audit_impl_dept_name like '%" + entity.getAuditImplDeptName()+"%'");
            }
        	if(Common.notEmpty(entity.getAuditStartDate()))	{
                hql.append(" and p.audit_start_date >= date('"+entity.getAuditStartDate()+ "')");
            }
            
            if(Common.notEmpty(entity.getAuditEndDate())) {
                hql.append(" and p.audit_end_date <= date('"+entity.getAuditEndDate()+ "')");
            }
//            时间
            
            if (entity.getAuditProjectProp() != null){
                hql.append(" and p.audit_project_prop = " + entity.getAuditProjectProp());
            }
//			性质
            
            if(Common.notEmpty(entity.getAuditProjectName())) {
                hql.append(" and p.audit_project_name like '%"+entity.getAuditProjectName()+ "%'");
            }
            
//            审计项目名称
            if (Common.notEmpty(entity.getAuditImplDeptId())) {
            	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+entity.getAuditImplDeptId()+"))");
			}else if(Common.notEmpty(dataAuthority)){
            	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+dataAuthority+"))");
            }
            if(Common.notEmpty(vcEmployeeId)){
                hql.append(" or p.project_principal_id = " + vcEmployeeId+" )");
            }
            

            if(Common.notEmpty(entity1.getAuditSuggest())) {
                hql.append(" and q.audit_suggest like '%"+entity1.getAuditSuggest()+ "%'");
            }
//            13
           
		}
        hql.append(" GROUP BY p.id,p.audit_impl_dept_name,p.audit_project_name  " );
     
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());

        return query.list();
	}

	@Override
	public List<Object[]> getProblemTypeCountByID(Integer id) {
		// TODO Auto-generated method stub
		  StringBuilder  sql = new StringBuilder();
			sql.append(" select  ");
			sql.append("SUM(IF(b.audit_question_type = 7, b.count, 0)) audit_question_type1,");
			sql.append("SUM(IF(b.audit_question_type = 8, b.count, 0)) audit_question_type2, ");
			sql.append("SUM(IF(b.audit_question_type = 0, b.count, 0)) audit_question_type3,");
			sql.append("SUM(IF(b.audit_question_type = 1, b.count, 0)) audit_question_type4, ");
			sql.append("SUM(IF(b.audit_question_type = 2, b.count, 0)) audit_question_type5,");
			sql.append("SUM(IF(b.audit_question_type = 3, b.count, 0)) audit_question_type6, ");
			sql.append("SUM(IF(b.audit_question_type = 4, b.count, 0)) audit_question_type7,");
			sql.append("SUM(IF(b.audit_question_type = 5, b.count, 0)) audit_question_type8, ");
			sql.append("SUM(IF(b.audit_question_type = 6, b.count, 0)) audit_question_type9,");
			sql.append("SUM(IF(b.audit_question_type = 9, b.count, 0)) audit_question_type10 from ");
			sql.append("( SELECT  COUNT(id) count,audit_question_type FROM ");
			sql.append("( SELECT t1.id, t1.project_id, SUBSTRING_INDEX(SUBSTRING_INDEX(t1.audit_question_types,',',t2.help_topic_id+1),',',-1) audit_question_type  ");
			sql.append(" FROM bimr_inside_audit_question t1 JOIN mysql.help_topic t2  ");
			sql.append(" ON t2.help_topic_id < (LENGTH(t1.audit_question_types) - LENGTH(REPLACE(t1.audit_question_types,',',''))+1)  ");
			sql.append(" WHERE t1.is_del = 0 and t1.project_id = "+id+" ) a GROUP BY audit_question_type ) b ");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
			return query.list();
	}
	
	@Override
	public List<Object[]> getRiskDriverTypesCountByID(Integer id) {
		// TODO Auto-generated method stub
		  StringBuilder  sql = new StringBuilder();
			sql.append(" select  ");
			sql.append("SUM(IF(b.risk_driver_types = 0, b.count, 0)) risk_driver_types1,");
			sql.append("SUM(IF(b.risk_driver_types = 1, b.count, 0)) risk_driver_types2, ");
			sql.append("SUM(IF(b.risk_driver_types = 2, b.count, 0)) risk_driver_types3,");
			sql.append("SUM(IF(b.risk_driver_types = 3, b.count, 0)) risk_driver_types4, ");
			sql.append("SUM(IF(b.risk_driver_types = 4, b.count, 0)) risk_driver_types5,");
			sql.append("SUM(IF(b.risk_driver_types = 5, b.count, 0)) risk_driver_types6");
			sql.append(" from ( SELECT  COUNT(id) count,risk_driver_types FROM ");
			sql.append("( SELECT t1.id, t1.project_id, SUBSTRING_INDEX(SUBSTRING_INDEX(t1.risk_driver_types,',',t2.help_topic_id+1),',',-1) risk_driver_types  ");
			sql.append(" FROM bimr_inside_audit_question t1 JOIN mysql.help_topic t2  ");
			sql.append(" ON t2.help_topic_id < (LENGTH(t1.risk_driver_types) - LENGTH(REPLACE(t1.risk_driver_types,',',''))+1)  ");
			sql.append(" WHERE t1.is_del = 0 and t1.project_id = "+id+" ) a GROUP BY risk_driver_types ) b ");
			Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
			return query.list();
	}

	@Override
	public Integer getInsideSuggestCount(Integer id) {
		// TODO Auto-generated method stub
		StringBuilder  sql = new StringBuilder();
		sql.append("SELECT COUNT(q.audit_suggest) from bimr_inside_audit_question q  where q.is_del = 0 and q.project_id = "+id);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return query.uniqueResult()==null?0:Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<Object> getfeedbackDesc(Integer id) {
		// TODO Auto-generated method stub
		StringBuilder  sql = new StringBuilder();
		sql.append("SELECT f.feedback_desc from bimr_inside_audit_rectify r ,bimr_inside_audit_measures m ,bimr_inside_audit_feedback f");
		sql.append(" where r.project_id = "+id+" and m.rectify_id = r.id and f.rectify_id = r.id and f.measures_id = m.id and r.is_del = 0");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return query.list();
	}

	@Override
	public Double getInsideRectifySuccessCount(Integer id) {
		// TODO Auto-generated method stub
		StringBuilder  sql = new StringBuilder();
		sql.append("SELECT sum( case m.`status` WHEN 2 THEN 1 ELSE 0 END)/COUNT(m.id) from bimr_inside_audit_rectify r ");
		sql.append("JOIN bimr_inside_audit_measures m on r.id = m.rectify_id and r.is_del = 0 and r.project_id = "+id);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return query.uniqueResult()==null?0:Double.parseDouble(query.uniqueResult().toString());
	}


	
	
}
