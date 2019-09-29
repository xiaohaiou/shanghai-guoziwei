package com.softline.dao.bimr.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.search.expression.And;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.bimr.IBimrInsideAuditQuestionDao;
import com.softline.entity.bimr.BimrInsideAuditMeasures;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditQuestion;
import com.softline.entity.bimr.BimrInsideAuditRectify;

@Repository(value = "iBimrInsideAuditQuestionDao")
public class BimrInsideAuditQuestionDao implements IBimrInsideAuditQuestionDao{

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer getBimrInsideAuditQuestionListCount(BimrInsideAuditQuestion entity, String dataAuthority) {
        if(entity == null){
            return 0;
        }
        StringBuilder  hql = new StringBuilder();
        hql.append("select count(0) from BimrInsideAuditQuestion s where 1=1 and s.isDel=0 ");
        if(entity != null) {
            if (entity.getProblemAttrUnitId()!= null){
                hql.append(" and s.problemAttrUnitId = " + entity.getProblemAttrUnitId());
            }
            if (Common.notEmpty(entity.getProblemAttrUnitName())){
                hql.append(" and s.problemAttrUnitName like '%" + entity.getProblemAttrUnitName()+"%'");
            }
            if(Common.notEmpty(entity.getProjectName())) {
                hql.append(" and s.projectName like '%"+entity.getProjectName()+ "%'");
            }
            if(entity.getProjectId() != null) {
                hql.append(" and s.projectId = "+entity.getProjectId());
            }
        }
        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
        return Integer.parseInt(query.uniqueResult().toString());
    }

    @Override
    public List<BimrInsideAuditQuestion> getBimrInsideAuditQuestionList(BimrInsideAuditQuestion entity, Integer offset, Integer pageSize, String dataAuthority) {
        if(entity==null) {
            return new ArrayList<BimrInsideAuditQuestion>();
        }
        StringBuilder  hql = new StringBuilder();
        hql.append("from BimrInsideAuditQuestion s where 1=1 and isDel=0 ");
        if(entity!=null) {
            if (entity.getProblemAttrUnitId()!= null){
                hql.append(" and s.problemAttrUnitId = " + entity.getProblemAttrUnitId());
            }
            if (Common.notEmpty(entity.getProblemAttrUnitName())){
                hql.append(" and s.problemAttrUnitName like '%" + entity.getProblemAttrUnitName()+"%'");
            }
            if(Common.notEmpty(entity.getProjectName())) {
                hql.append(" and s.projectName like '%"+entity.getProjectName()+ "%'");
            }
            if(entity.getProjectId() != null) {
                hql.append(" and s.projectId = "+entity.getProjectId());
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
    public Integer saveBimrInsideAuditQuestion(BimrInsideAuditQuestion entity) {
        return (Integer) sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void updateBimrInsideAuditQuestion(BimrInsideAuditQuestion entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void deleteBimrInsideAuditQuestion(Integer id) {
        String hql = "update BimrInsideAuditQuestion s set s.isDel = 1" + " where s.id = " + id + "";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.executeUpdate();
    }

    @Override
    public BimrInsideAuditQuestion getBimrInsideAuditQuestionById(Integer id) {
        String hql = "from BimrInsideAuditQuestion s where s.isDel = 0 and s.id = " + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return (BimrInsideAuditQuestion)query.uniqueResult();
    }

    @Override
    public BimrInsideAuditQuestion getBimrInsideAuditQuestion(BimrInsideAuditQuestion entity) {
        String hql = "from BimrInsideAuditQuestion s where s.isDel = 0 and s.id = " + entity.getId();
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return (BimrInsideAuditQuestion)query.uniqueResult();
    }

    @Override
    public List<BimrInsideAuditQuestion> getBimrInsideAuditQuestionHasNoChild() {
        String hql = "from BimrInsideAuditQuestion s where s.isDel = 0 and s.isChildren = 0";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<BimrInsideAuditQuestion> getBimrInsideAuditQuestionForList(Integer projectId,String rectifyTrackId) {
        StringBuilder hql = new StringBuilder();
        hql.append(" from BimrInsideAuditQuestion s where s.isDel = 0 and s.projectId = " + projectId);
        if(rectifyTrackId != null && !rectifyTrackId.equals("")){
            hql.append(" and s.rectifyTrackId = " + rectifyTrackId);
        }
        
        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
        return query.list();
    }

	@Override
	public Integer getUnFinishListCount(String yearMonth, BimrInsideAuditProject project,String dataAuthority,String vcEmployeeId) {
		StringBuilder sql = new StringBuilder(200);
		sql.append("SELECT COUNT(t1.id) ");
		buildUnFinishListCondition(sql, yearMonth, project, dataAuthority, vcEmployeeId);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return ((BigInteger)query.uniqueResult()).intValue();
	}
	
	private void buildUnFinishListCondition(StringBuilder sql, String yearMonth, BimrInsideAuditProject project,String dataAuthority,String vcEmployeeId){
		sql.append(" FROM bimr_inside_audit_question t1 INNER JOIN bimr_inside_audit_project t2 ON t1.project_id = t2.id ");
		sql.append(" LEFT JOIN bimr_inside_rectify_question t3 ON t1.id = t3.answer_id ");
		sql.append(" WHERE t1.is_del = 0 AND t2.is_del = 0 AND (t3.is_del IS NULL OR t3.is_del= 0) AND t1.is_rectify = 1 AND (t3.status IS NULL OR t3.status =1) ");
		if(StringUtils.isNotBlank(yearMonth)){
			String from = yearMonth + "-01 00:00:00";
			String to = yearMonth + "-31 23:59:59";
			sql.append( "AND t1.create_date >= '").append(from).append("' AND t1.create_date <='").append(to).append("' ");
		}
		if (Common.notEmpty(project.getAuditImplDeptId())) {
			sql.append(" and ( t2.id in (select project_id from bimr_inside_audit_project_appendix where audit_impl_dept_id in ("+project.getAuditImplDeptId()+"))");
		}else if(Common.notEmpty(dataAuthority)){
			sql.append(" and ( t2.id in (select project_id from bimr_inside_audit_project_appendix where audit_impl_dept_id in ("+dataAuthority+"))");
        }
		if(Common.notEmpty(vcEmployeeId)){
			sql.append(" or t2.project_principal_id = " + vcEmployeeId +" )");
        }
		
		if(StringUtils.isNotBlank(project.getAuditProjectName())){
			sql.append(" AND t2.audit_project_name LIKE '%").append(project.getAuditProjectName()).append("%' ");
		}
		if(project.getAuditProjectProp() != null){
			sql.append(" AND t2.audit_project_prop =").append(project.getAuditProjectProp());
		}
	}
//未完成查询
	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getUnFinishList(String yearMonth,
			BimrInsideAuditProject project, Integer offset, Integer pageSize,String dataAuthority,String vcEmployeeId) {
		StringBuilder sql = new StringBuilder(200);
		sql.append("SELECT t1.id, t1.project_name, t1.problem, t1.audit_suggest, t3.measures, t1.create_date ");
		buildUnFinishListCondition(sql, yearMonth, project, dataAuthority, vcEmployeeId);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		if(offset != null){
            query.setFirstResult(offset);
        }
        if(pageSize != null){
            query.setMaxResults(pageSize);
        }
		return query.list();
	}

	@Override
	public Integer getQuestionAnalyzeListCount(String yearMonth,
			BimrInsideAuditProject project, String dataAuthority,String vcEmployeeId) {
		
		StringBuilder sql = new StringBuilder(200);
		sql.append("SELECT COUNT(0)  FROM ( SELECT  a.audit_depted_name ");
		buildQuestionAnalyzeSql(sql, yearMonth, project,dataAuthority,vcEmployeeId);
		sql.append(" ) t");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return ((BigInteger)query.uniqueResult()).intValue();
	}
	
	private void buildQuestionAnalyzeSql(StringBuilder sql, String yearMonth, BimrInsideAuditProject project, String dataAuthority,String vcEmployeeId){
		sql.append(" FROM bimr_inside_audit_project a, ");
		sql.append(" (SELECT  project_id, audit_question_type, COUNT(id) count FROM ");
		sql.append(" (SELECT t1.id, t1.project_id, SUBSTRING_INDEX(SUBSTRING_INDEX(t1.audit_question_types,',',t2.help_topic_id+1),',',-1) audit_question_type ");
		sql.append(" FROM bimr_inside_audit_question t1 JOIN mysql.help_topic t2 ");
		sql.append(" ON t2.help_topic_id < (LENGTH(t1.audit_question_types) - LENGTH(REPLACE(t1.audit_question_types,',',''))+1) ");
		sql.append(" WHERE t1.is_del = 0 ");
		if(StringUtils.isNotBlank(yearMonth)){
			String from = yearMonth + "-01 00:00:00";
			String to = yearMonth + "-31 23:59:59";
			sql.append( "AND t1.create_date >= '").append(from).append("' AND t1.create_date <='").append(to).append("' ");
		}
		sql.append(" ) s ");
		sql.append(" GROUP BY project_id, audit_question_type) b ");
		sql.append(" WHERE a.id = b.project_id ");
		if (Common.notEmpty(project.getAuditImplDeptId())) {
			sql.append(" and ( a.id in (select project_id from bimr_inside_audit_project_appendix where audit_impl_dept_id in ("+project.getAuditImplDeptId()+"))");
		}else if(Common.notEmpty(dataAuthority)){
			sql.append(" and ( a.id in (select project_id from bimr_inside_audit_project_appendix where audit_impl_dept_id in ("+dataAuthority+"))");
        }
		if(Common.notEmpty(vcEmployeeId)){
			sql.append(" or a.project_principal_id = " + vcEmployeeId +" )");
        }
		
		if(StringUtils.isNotBlank(project.getAuditProjectName())){
			sql.append(" AND a.audit_project_name LIKE '%").append(project.getAuditProjectName()).append("%' ");
		}
		if(project.getAuditProjectProp() != null){
			sql.append(" AND a.audit_project_prop =").append(project.getAuditProjectProp());
		}
		sql.append(" GROUP BY audit_depted_name, audit_project_name ");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getQuestionAnalyzeList(String yearMonth,
			BimrInsideAuditProject project, Integer offset, Integer pageSize, String dataAuthority,String vcEmployeeId) {
		
		StringBuilder sql = new StringBuilder(200);
		sql.append("SELECT a.audit_depted_name, a.audit_project_name, ");
		sql.append("SUM(IF(b.audit_question_type = 0, b.count, 0)) audit_question_type0, ");
		sql.append("SUM(IF(b.audit_question_type = 1, b.count, 0)) audit_question_type1,");
		sql.append("SUM(IF(b.audit_question_type = 2, b.count, 0)) audit_question_type2, ");
		sql.append("SUM(IF(b.audit_question_type = 3, b.count, 0)) audit_question_type3,");
		sql.append("SUM(IF(b.audit_question_type = 4, b.count, 0)) audit_question_type4, ");
		sql.append("SUM(IF(b.audit_question_type = 5, b.count, 0)) audit_question_type5,");
		sql.append("SUM(IF(b.audit_question_type = 6, b.count, 0)) audit_question_type6, ");
		sql.append("SUM(IF(b.audit_question_type = 7, b.count, 0)) audit_question_type7 ");
		buildQuestionAnalyzeSql(sql, yearMonth, project,dataAuthority,vcEmployeeId);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		if(offset != null){
            query.setFirstResult(offset);
        }
        if(pageSize != null){
            query.setMaxResults(pageSize);
        }
		return query.list();
	}

//	未完成查询导出
	@Override
	public List<Object[]> getUnfinishExport(BimrInsideAuditProject entity,BimrInsideAuditQuestion entity1,BimrInsideAuditRectify entity2,BimrInsideAuditMeasures entity3, String yearMonth, String dataAuthority,String vcEmployeeId) {
        StringBuilder  hql = new StringBuilder();
        hql.append(" SELECT * ,(p2.c2/p2.c1) * 100 from ( " +
        		" select p.id,p.audit_project_name,p.audit_depted_name,	"+	 
        		" (SELECT COUNT(0) from bimr_inside_audit_project p1,bimr_inside_audit_question q "+
        		" where p1.id = p.id and p1.id = q.project_id ), "+
        		" (SELECT COUNT(0) from bimr_inside_audit_project p1,bimr_inside_audit_question q, "+
        		"	bimr_inside_audit_rectify r,bimr_inside_audit_measures m where p1.id = p.id and p1.id = q.project_id "+
        		"	and r.answer_id =q.id and r.id = m.rectify_id and r.project_id = p1.id) as c1, "+
        		"	(SELECT COUNT(0) from bimr_inside_audit_project p1,bimr_inside_audit_question q, "+
        		"	bimr_inside_audit_rectify r,bimr_inside_audit_measures m where p1.id = p.id and p1.id = q.project_id "+
        		"	and r.answer_id =q.id and r.id = m.rectify_id and r.project_id = p1.id and m.`status` = 2 ) as c2 "+
        		"	from bimr_inside_audit_project p where p.`status` != 50231 ");
        if(entity!=null) {
        	if(StringUtils.isNotBlank(yearMonth)){
    			String from = yearMonth + "-01 00:00:00";
    			String to = yearMonth + "-31 23:59:59";
    			hql.append( " AND p.create_date >= '").append(from).append("' AND p.create_date <='").append(to).append("' ");
    		}
        	
        	 if(Common.notEmpty(entity.getAuditProjectName())) {
                 hql.append(" and p.audit_project_name like '%"+entity.getAuditProjectName()+ "%'");
             }
//             审计项目名称1
            
             
             if (entity.getAuditProjectProp() != null){
                 hql.append(" and p.audit_project_prop = " + entity.getAuditProjectProp());
             }
//             审计项目性质2
            if(Common.notEmpty(entity.getProjectPrincipal())) {
                hql.append(" and p.project_principal like '%"+entity.getProjectPrincipal()+ "%'");
            } 
//            项目负责人projectprincipal
            if (Common.notEmpty(entity.getAuditImplDeptId())) {
             	hql.append(" and ( p.id in (select project_id from bimr_inside_audit_project_appendix where audit_impl_dept_id in ("+entity.getAuditImplDeptId()+"))");
     		}else if(Common.notEmpty(dataAuthority)){
    			hql.append(" and ( p.id in (select project_id from bimr_inside_audit_project_appendix where audit_impl_dept_id in ("+dataAuthority+"))");
             }
             if(Common.notEmpty(vcEmployeeId)){
            	 hql.append(" or p.project_principal_id = " + vcEmployeeId +" )");
             }
            
            if(Common.notEmpty(entity.getAuditProjectCode())) {
                hql.append(" and p.audit_project_code like '%"+entity.getAuditProjectCode()+ "%'");
            }
            if (Common.notEmpty(entity.getAuditDeptedName())){
                hql.append(" and p.audit_depted_name like '%"+entity.getAuditDeptedName()+"%'");
            }
            hql.append(" ) p2 " );
        }		
        Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());

        return query.list();
	}
    
    
}
