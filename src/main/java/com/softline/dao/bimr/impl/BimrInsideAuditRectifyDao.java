package com.softline.dao.bimr.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.bimr.IBimrInsideAuditRectifyDao;
import com.softline.entity.bimr.BimrInsideAuditFeedback;
import com.softline.entity.bimr.BimrInsideAuditMeasures;
import com.softline.entity.bimr.BimrInsideAuditProject;
import com.softline.entity.bimr.BimrInsideAuditQuestion;
import com.softline.entity.bimr.BimrInsideAuditRectify;

/**
 * @author rz078
 *
 */
@Repository(value = "bimrInsideAuditRectifyDao")
public class BimrInsideAuditRectifyDao implements IBimrInsideAuditRectifyDao{

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer getBimrInsideRectifyListCount(BimrInsideAuditRectify entity, String dataAuthority) {
        if(entity == null){
            return 0;
        }
        StringBuilder  hql = new StringBuilder();
        hql.append("select count(*) from BimrInsideAuditRectify s where 1=1 and s.isDel=0 ");
        if(entity != null) {
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
    public List<BimrInsideAuditRectify> getBimrInsideRectifyList(BimrInsideAuditRectify entity, Integer offset, Integer pageSize, String dataAuthority) {
        if(entity==null) {
            return new ArrayList<BimrInsideAuditRectify>();
        }
        StringBuilder  hql = new StringBuilder();
        hql.append("from BimrInsideAuditRectify s where 1=1 and isDel=0 ");
        if(entity!=null) {
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

    public List<BimrInsideAuditRectify> getBimrInsideRectifyQuestionList(int projectId) {
        if(projectId==0) {
            return new ArrayList<BimrInsideAuditRectify>();
        }
        StringBuilder  hql = new StringBuilder();
        hql.append("from BimrInsideAuditRectify s where 1=1 and isDel=0 ");
        if(projectId!=0) {
            
                hql.append(" and s.projectId = "+projectId);
            
        }
        hql.append(" order by s.createDate desc ");
        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
       
        return query.list();
    }
    @Override
    public Integer saveBimrInsideRectifyQuestion(BimrInsideAuditRectify entity) {
        return (Integer) sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void updateBimrInsideRectifyQuestion(BimrInsideAuditRectify entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void deleteBimrInsideRectifyQuestion(Integer id) {
        String hql = "update BimrInsideAuditRectify s set s.isDel = 1" + " where s.id = " + id + "";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.executeUpdate();
    }

    @Override
    public BimrInsideAuditRectify getBimrInsideRectifyById(Integer id) {
        String hql = "from BimrInsideAuditRectify s where s.isDel = 0 and s.id = " + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return (BimrInsideAuditRectify)query.uniqueResult();
    }

    @Override
    public BimrInsideAuditRectify getBimrInsideRectifyQuestion(BimrInsideAuditRectify entity) {
        String hql = "from BimrInsideAuditRectify s where s.isDel = 0 and s.id = " + entity.getId();
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return (BimrInsideAuditRectify)query.uniqueResult();
    }

    @Override
    public List<BimrInsideAuditRectify> getBimrInsideRectifyQuestionHasNoChild() {
        String hql = "from BimrInsideAuditRectify s where s.isDel = 0 and s.isChildren = 0";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

	
	@Override
	@SuppressWarnings("unchecked")
	public BimrInsideAuditRectify getRectifyQuestionByAnswerId(Integer answerId) {
		List<BimrInsideAuditRectify> list = sessionFactory.getCurrentSession().
				createCriteria(BimrInsideAuditRectify.class)
				.add(Restrictions.eq("answerId", answerId))
				.list();
		
		return list.isEmpty()? null : list.get(0);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<BimrInsideAuditRectify> getRectifyQuestionByAnswerIdList(Integer answerId) {
		 String hql = "from BimrInsideAuditRectify s where s.isDel = 0 and s.answerId = " + answerId;
	        Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}
	
	@Override
	public List<BimrInsideAuditMeasures> getBimrInsideRectifyMeasures(Integer rectifyId){
		String hql = "from BimrInsideAuditMeasures s where s.rectifyId = " + rectifyId;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
	}
	
	@Override
	public void deleteBimrInsideMesaures(Integer rectifyId){
		String sql = "delete from bimr_inside_audit_measures where rectify_id = " + rectifyId;
		sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
	
	@Override
	public void updateBimrInsideMeasuresStateById(String id, String state){
		String sql = "update bimr_inside_audit_measures set status = '" + state + "' where id = " + id;
		sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
	
	@Override
	public void deleteBimrInsideFeedbackByMonth(String month, String rectifyId){
		String sql = "delete from bimr_inside_audit_feedback where substr(feedback_date,1,7) = '" + month + "' and rectify_id = " + rectifyId;
		sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
    
    @Override
    public Integer getFeedbackMsgCount(String rectifyId){
    	StringBuilder  hql = new StringBuilder();
        hql.append("select count(*) from BimrInsideAuditFeedback s where 1=1 and s.rectifyId = " + rectifyId);
        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
        return Integer.parseInt(query.uniqueResult().toString());
    }
    
    @Override
    public List<BimrInsideAuditFeedback> getFeedbackMsgList(String rectifyId, Integer offset, Integer pageSize){
    	StringBuilder  hql = new StringBuilder();
        hql.append("from BimrInsideAuditFeedback s where 1=1 and rectifyId = " + rectifyId);
        hql.append(" order by s.feedbackDate desc ");
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
    public List<BimrInsideAuditFeedback> getBimrInsideAuditFeedbackByRectifyId(String rectifyId){
    	StringBuilder  hql = new StringBuilder();
        hql.append("from BimrInsideAuditFeedback s where 1=1 and rectifyId = " + rectifyId);
        hql.append(" order by s.feedbackDate desc ");
        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
        return query.list();
    }
//审计整改验收单----时点维度  条件getProjectDimensionExport
	@Override
	public List<Object[]> getTimeDimensionExport(BimrInsideAuditRectify entity,
			BimrInsideAuditMeasures entity1,BimrInsideAuditProject entity2,BimrInsideAuditQuestion entity4, String dataAuthority,
			String vcEmployeeId) {
		
//		 if(entity==null) {
//	            return new ArrayList<Object[]>();
//	        }
	        StringBuilder  hql = new StringBuilder();
	        

//	        hql.append("SELECT b.audit_project_code,b.audit_project_name,q.problem , w.rectify_advice,p.rectify_measure, " +
//	        		" w.rectify_response_name,w.rectify_time,p.`status`,b.audit_project_prop,b.audit_start_date,b.audit_end_date " +
//	        		" FROM bimr_inside_audit_rectify w "+
//	        		" JOIN bimr_inside_audit_measures p "+
//	        		" ON w.id=p.id "+
//	        		" JOIN bimr_inside_audit_project b "+
//	        		" ON w.id=b.id "+
//	        		" JOIN bimr_inside_audit_question q"+
//	        		" ON w.id=q.id" );
	        
	        hql.append("SELECT p.audit_project_code,p.audit_project_name,q.problem , r.rectify_advice,m.rectify_measure, " +
	        		" r.rectify_response_name,r.rectify_time,m.`status`,p.audit_project_prop,p.audit_start_date,p.audit_end_date   " +
	        		" FROM bimr_inside_audit_project p "+
	        		" JOIN bimr_inside_audit_question q "+
	        		" ON p.id = q.project_id"+
	        		" AND q.is_del = 0"+
	        		" AND p.is_del = 0"+
	        		" JOIN bimr_inside_audit_rectify r"+ 
	        		" ON p.id = r.project_id " +
	        		" AND r.answer_id =q.id "+
	        		" AND r.is_del = 0"+
	        		" JOIN bimr_inside_audit_measures m"+ 
	        		" ON p.id = m.project_id"+
	        		" and m.rectify_id = r.id " );
//	        " from bimr_inside_audit_project p  " +
//	        		" JOIN bimr_inside_audit_question q " +
//	        		" on p.id = q.project_id and p.is_del = 0 and q.is_del = 0 " +
//	        		" JOIN bimr_inside_audit_rectify r on r.project_id = p.id and r.answer_id = q.id and r.is_del = 0 " +
//	        		" JOIN bimr_inside_audit_measures m on m.rectify_id = r.id and m.project_id = p.id "
	        
	        
	        if(entity!=null) {
	        	
	        	if(Common.notEmpty(entity2.getAuditProjectCode())) {
	                hql.append(" and p.audit_project_code like '%"+entity2.getAuditProjectCode()+ "%'");
	            }
//	        	审计项目编号
	        	
	        	if(Common.notEmpty(entity2.getAuditStartDate()))	{
	                hql.append(" and p.audit_start_date >= date('"+entity2.getAuditStartDate()+ "')");
	            }
//	            起始时间
	            if(Common.notEmpty(entity2.getAuditEndDate())) {
	                hql.append(" and p.audit_end_date <= date('"+entity2.getAuditEndDate()+ "')");
	            }
//	            截止时间
	            
//	            if(Common.notEmpty(entity2.getAuditStartDate())) {
//	                hql.append(" and b.audit_start_date like '%"+entity2.getAuditStartDate()+ "%'");
//	            }
////	            起始时间
//	            if(Common.notEmpty(entity2.getAuditEndDate())) {
//	                hql.append(" and b.audit_end_date like '%"+entity2.getAuditEndDate()+ "%'");
//	            }
////	            终止时间

	            if(Common.notEmpty(dataAuthority)){
	            	hql.append(" and ( p.id in (select project_id from bimr_inside_audit_project_appendix where audit_impl_dept_id in ("+dataAuthority+"))");
	            }
	            if(Common.notEmpty(vcEmployeeId)){
	                hql.append(" or p.project_principal_id = " + vcEmployeeId+" )");
	            }
	            
	           
	            
	            if(Common.notEmpty(entity4.getProblem())) {
	                hql.append(" and q.problem like '%"+entity4.getProblem()+ "%'");
	            }
//	            存在问题
	            
	           
	            if(entity2.getAuditProjectProp()!=null) {
	                hql.append(" and p.audit_project_prop like '%"+entity2.getAuditProjectProp()+ "%'");
	            }
//	            项目性质
	           
	            
	            if(Common.notEmpty(entity2.getAuditProjectName())) {
	                hql.append(" and p.audit_project_name like '%"+entity2.getAuditProjectName()+ "%'");
	            }
	            
//审计项目名称

	            if (Common.notEmpty(entity.getRectifyAdvice())){
	                hql.append(" and r.rectify_advice like '%" + entity.getRectifyAdvice()+"%'");
	            }
	            
//	            审计建议
	            if (Common.notEmpty(entity1.getRectifyMeasure())){
	                hql.append(" and m.rectify_measure like '%" + entity1.getRectifyMeasure()+"%'");
	            }
	            
//	            整改措施w
	        
	            if (Common.notEmpty(entity.getRectifyResponseName())){
	                hql.append(" and r.rectify_response_name like '%" + entity.getRectifyResponseName()+"%'");
	            }
//	            整改负责人
	            if (Common.notEmpty(entity.getRectifyTime())){
	                hql.append(" and r.rectify_time like '%" + entity.getRectifyTime()+"%'");
	            }   
	            
//	            整改时限
	            
	            
	            if (Common.notEmpty(entity.getStatus())){
	                hql.append(" and r.status like '%" + entity.getStatus()+"%'");
	            }   
//	            整改验收情况
	     
	      
//	            if (entity2.getAuditProjectProp() != null){
//	                hql.append(" and a.audit_project_prop = " + entity2.getAuditProjectProp());
//	            }
////	            项目性质

	               
	            
	        }
	        hql.append(" order by p.create_date asc  ");
	        
	        Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());

	        return query.list();
		
		
		
		
	}
//项目维度
	@Override
	public List<Object[]> getProjectDimensionExport(
			BimrInsideAuditRectify entity, BimrInsideAuditMeasures entity1,
			BimrInsideAuditProject entity2,BimrInsideAuditFeedback entity3,BimrInsideAuditQuestion entity4, String dataAuthority,
			String vcEmployeeId) {
		if(entity==null) {
            return new ArrayList<Object[]>();
        }
        StringBuilder  hql = new StringBuilder();
        

        
        hql.append(" SELECT q.problem,r.rectify_advice,m.rectify_measure , r.rectify_response_name,r.rectify_time, " +
        		" m.`status`,f.feedback_desc,p.audit_start_date,p.audit_end_date,p.audit_project_prop,p.audit_project_name   " +
        		" FROM bimr_inside_audit_project p "+
        		"  JOIN bimr_inside_audit_question q "+
        		" ON p.id = q.project_id "+	
        		" AND q.is_del = 0"+
        		" AND p.is_del = 0"+
        		" LEFT JOIN bimr_inside_audit_rectify r "+ 
        		" ON p.id = r.project_id " +
        		"  AND r.answer_id = q.id "+
        		" AND r.is_del = 0"+
        		" LEFT JOIN bimr_inside_audit_measures m"+ 
        		" ON p.id = m.project_id"+
        		" AND m.rectify_id = r.id " +
        		" LEFT JOIN bimr_inside_audit_feedback f " +
        		" ON r.id = f.rectify_id AND m.id=f.measures_id where 1=1 " );
       
        if(entity!=null) {
        	
        	if(Common.notEmpty(entity2.getAuditStartDate()))	{
                hql.append(" and p.audit_start_date >= date('"+entity2.getAuditStartDate()+ "')");
            }
//        	时间
            
            if(Common.notEmpty(entity2.getAuditEndDate())) {
                hql.append(" and p.audit_end_date <= date('"+entity2.getAuditEndDate()+ "')");
            }
           
            if (Common.notEmpty(entity2.getAuditImplDeptId())) {
             	hql.append(" and ( p.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+entity2.getAuditImplDeptId()+"))");
 			}else if(Common.notEmpty(dataAuthority)){
            	hql.append(" and ( p.id in (select project_id from bimr_inside_audit_project_appendix where audit_impl_dept_id in ("+dataAuthority+"))");
            }
            if(Common.notEmpty(vcEmployeeId)){
                hql.append(" or p.project_principal_id = " + vcEmployeeId+" )");
            }
     
           
            if(entity2.getAuditProjectProp()!=null) {
                hql.append(" and p.audit_project_prop like '%"+entity2.getAuditProjectProp()+ "%'");
            }
//            项目性质
           
            
            if(Common.notEmpty(entity2.getAuditProjectName())) {
                hql.append(" and p.audit_project_name like '%"+entity2.getAuditProjectName()+ "%'");
            }
//            项目 名称
         

        
       
  
            
      
      
//         

               
            
        }
        hql.append(" order by p.create_date asc ");
        
        Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());

        return query.list();
	}

	
}
