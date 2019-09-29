package com.softline.dao.bimr.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.controller.common.commonController;
import com.softline.dao.bimr.IComplianceDao;
import com.softline.entity.bimr.BimrCompliance;
import com.softline.entity.bimr.BimrComplianceBJInfo;
import com.softline.entity.bimr.BimrCompliancePerson;
import com.softline.entity.bimr.BimrComplianceProgress;
import com.softline.entity.bimr.BimrCompliancePrompt;
import com.softline.entity.bimr.BimrComplianceSituation;
import com.softline.entity.bimr.BimrComplianceSituationPerson;
import com.softline.entity.bimr.BimrComplianceSuggest;
import com.softline.entity.bimr.BimrDuty;

/**
 * 实现合规调查数据操作
 * 
 * @author tch
 */
@Repository(value = "complianceDao")
public class ComplianceDao implements IComplianceDao{
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 数据件数查询
	 * 
	 * @param entity
	 * @return
	 */
	public int getComplianceListCount(BimrCompliance entity) {
		if (entity == null)
			return 0;
		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from BimrCompliance s where 1=1 and isdel=0 ");
		
		//条件
		getInspectProjectListCondition(entity,hql);
		
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	public List<BimrCompliance> getComplianceList(
			BimrCompliance entity, Integer offsize, Integer pagesize) {
		if (entity == null) {
			return new ArrayList<BimrCompliance>();
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrCompliance s where 1=1 and isdel=0 ");
		
		//条件
		getInspectProjectListCondition(entity,hql);
		
		hql.append(" order by createDate desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		if (offsize != null) {
			query.setFirstResult(offsize);
		}
		if (pagesize != null) {
			query.setMaxResults(pagesize);
		}
		return query.list();
	}
	
	private void getInspectProjectListCondition(BimrCompliance entity,StringBuilder hql){
		if (entity != null) {
			if (entity.getInvestigationType() != null) {
				hql.append(" and s.investigationType = " + entity.getInvestigationType() + " ");
			}
			if (entity.getStatus() != null) {
				hql.append(" and s.status = " + entity.getStatus() + " ");
			}
			if (Common.notEmpty(entity.getStartTime()) ) {
				hql.append(" and s.startTime >= '" + entity.getStartTime() + "' ");
			}
			
			if (Common.notEmpty(entity.getEndTime())) {
				hql.append(" and s.endTime <= '" + entity.getEndTime() + "' ");
			}
			if (Common.notEmpty(entity.getProjectName())) {
				hql.append(" and s.projectName like '%" + entity.getProjectName() + "%' ");
			}
			//合规调查承办企业在数据权限范围内
			hql.append(" and s.investigationId in ("+entity.getDataauth()+")");
			
			/*if(entity.getSearchType().equals("manage")){
				hql.append(" and s.responsiblePersonId = '" + entity.getResponsiblePersonId() + "' ");
			}*/
		}
	}
	
	public BimrCompliance getComplianceById(Integer id) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrCompliance s where id="+id+" ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return (BimrCompliance)query.uniqueResult();
	}
	
	public List<BimrCompliancePerson> getCompliancePerson(Integer complianceId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrCompliancePerson where complianceId="+complianceId+" and isDel=0 ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return query.list();
	}
	
	public List<BimrComplianceSituation> getComplianceSituation(Integer complianceId,Integer dataType) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrComplianceSituation where complianceId="+complianceId+"  and isDel=0 ");
		if (null!=dataType) {
			hql.append(" and dataType="+dataType+" ");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return query.list();
	}
	
	@Override
	public Integer getListCountByStatuses(BimrCompliance entity, Integer[] statuses) {
		Criteria criteria = sessionFactory.getCurrentSession().
				createCriteria(BimrCompliance.class).
				setProjection(Projections.count("id"));
		
		buildListByStatusesCondition(criteria, entity, statuses);
		
		return ((Long)criteria.uniqueResult()).intValue();
	}

	private void buildListByStatusesCondition(Criteria criteria, BimrCompliance entity, Integer[] statues){
		if(entity.getInvestigationType() != null){
			criteria.add(Restrictions.eq("investigationType", entity.getInvestigationType()));
		}
		if(entity.getStatus() != null){
			criteria.add(Restrictions.eq("status", entity.getStatus()));
		}else{
			criteria.add(Restrictions.in("status", statues));
		}
		if(StringUtils.isNotBlank(entity.getStartTime())){
			criteria.add(Restrictions.ge("startTime", entity.getStartTime()));
		}
		if(StringUtils.isNotBlank(entity.getEndTime())){
			criteria.add(Restrictions.le("endTime", entity.getEndTime()));
		}
		if(StringUtils.isNotBlank(entity.getProjectName())){
			criteria.add(Restrictions.like("projectName", "%" +entity.getProjectName() + "%"));
		}
		if(StringUtils.isNotBlank(entity.getCompId())){
			String[] compIds = entity.getCompId().split(",");
			criteria.add(Restrictions.in("compId", compIds));
		}  
		String[] dataauth = entity.getDataauth().split(",");
		criteria.add(Restrictions.in("investigationId", dataauth));
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<BimrCompliance> getListByStatuses(
			BimrCompliance entity, Integer[] statues, Integer offset, Integer pageSize) {
		
		Criteria criteria = sessionFactory.getCurrentSession().
				createCriteria(BimrCompliance.class).
				addOrder(Order.asc("status"));
		
		buildListByStatusesCondition(criteria, entity, statues);
		
		offset = offset != null ? offset : 0;
		pageSize = pageSize != null ? pageSize : Base.pagesize;
		criteria.setFirstResult(offset);
		criteria.setMaxResults(pageSize);
		return criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<BimrCompliance> getListByStatusesExport(
			BimrCompliance entity, Integer[] statues) {
		
		Criteria criteria = sessionFactory.getCurrentSession().
				createCriteria(BimrCompliance.class).
				addOrder(Order.asc("status"));
		
		buildListByStatusesCondition(criteria, entity, statues);
		return criteria.list();
	}
	public List<BimrComplianceSituationPerson> getComplianceSituationPerson(Integer situationId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrComplianceSituationPerson where situationId="+situationId+" ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return query.list();
	}
	
	public BimrComplianceSituation getComplianceSituationById(Integer id) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrComplianceSituation s where id="+id+" ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return (BimrComplianceSituation)query.uniqueResult();
	}
	
	public BimrCompliancePrompt getCompliancePromptById(Integer id) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrCompliancePrompt s where id="+id+" ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return (BimrCompliancePrompt)query.uniqueResult();
	}
	
	public List<BimrCompliancePrompt> getCompliancePrompt(Integer complianceId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrCompliancePrompt where complianceId="+complianceId+" and isDel=0 ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return query.list();
	}
	
	public BimrComplianceSuggest getComplianceSuggestById(Integer id) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrComplianceSuggest s where id="+id+" ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return (BimrComplianceSuggest)query.uniqueResult();
	}
	
	public List<BimrComplianceSuggest> getComplianceSuggest(Integer complianceId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrComplianceSuggest where complianceId="+complianceId+" and isDel=0 ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return query.list();
	}
	
	public BimrComplianceProgress getComplianceProgressById(Integer id) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrComplianceProgress s where id="+id+" ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return (BimrComplianceProgress)query.uniqueResult();
	}
	
	public List<BimrComplianceProgress> getComplianceProgress(Integer complianceId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrComplianceProgress where complianceId="+complianceId+" and isDel=0 ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return query.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public BimrComplianceBJInfo getBJInfoByComplianceId(Integer complianceId) {
		String hql = "FROM BimrComplianceBJInfo WHERE complianceId = :complianceId AND isDel = 0";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("complianceId", complianceId);
		List<BimrComplianceBJInfo> list = (List<BimrComplianceBJInfo>)query.list();
		return list.size() == 0? null : list.get(0);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BimrComplianceSuggest> getSuggestListByComplianceId(Integer complianceId) {
		String hql = "FROM BimrComplianceSuggest WHERE complianceId = :complianceId AND isDel = 0";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("complianceId", complianceId);
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BimrComplianceSituationPerson> getSituationPersonListByByComplianceId(Integer complianceId) {
		String hql = "FROM BimrComplianceSituationPerson s " +
				"WHERE s.situationId IN (SELECT c.id FROM BimrComplianceSituation c WHERE c.isDel = 0 AND c.complianceId = :complianceId)";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("complianceId", complianceId);
		return query.list();
	}

	@Override
	public List<BimrCompliance> getcompliance(BimrCompliance compliance1) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder();
		hql.append(" from BimrCompliance s where 1=1 and isdel=0 ");
		
		//条件
		getInspectProjectListCondition(compliance1,hql);
		
		hql.append(" order by createDate desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return query.list();
	}

	@Override
	public List getProjectCodelist(String projectCode,int id) {
		// TODO Auto-generated method stub
		StringBuilder hql=new StringBuilder();
		hql.append("from BimrCompliance c where c.projectCode like '"+projectCode+"%' ");
		hql.append(" and isDel = 0 and id != "+id);//and version != ''
		hql.append(" order by c.projectCode desc ");
		Query query=sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
		
	}

	@Override
	public List<Object[]> getgetSuggestAndEmail() {
		// TODO Auto-generated method stub
		StringBuilder hql=new StringBuilder();
		hql.append("SELECT c.projectName,s.suggest,i.vcEmail,s.changeLastTime FROM bimr_compliance_suggest s ");
		hql.append("JOIN bimr_compliance c on s.complianceId = c.id and c.isDel = 0 and c.`status` = 81014 ");
		hql.append("JOIN hh_usersmessageinfo i on i.vcEmployeeID = s.followPersonID ");
		hql.append("where s.isDel = 0 and s.changeStatus in(0,2) and  TO_DAYS(s.changeLastTime) - TO_DAYS(NOW())<= 30 and TO_DAYS(s.changeLastTime) - TO_DAYS(NOW()) >=0");
		Query query=sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return query.list();
	}

	@Override
	public int saveEmailInfo(String date, String sendPerson,
			String recivePerson, int status, int type, String emailtitle,
			String result) {
		// TODO Auto-generated method stub
		StringBuilder hql=new StringBuilder();
		hql.append("insert into sendEmailInfo values(0,?,?,?,?,?,?,?)");
	
		Query query=sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		query.setParameter(0, date);
		query.setParameter(1, sendPerson);
		query.setParameter(2, recivePerson);
		query.setParameter(3, status);
		query.setParameter(4, type);
		query.setParameter(5, emailtitle);
		query.setParameter(6, result);
		return query.executeUpdate();
	}

	@Override
	public List<Object[]> getQueryExportDC(BimrCompliance entity1,BimrCompliancePerson entity2, BimrComplianceSituation entity3,
			BimrComplianceSituationPerson entity4, BimrComplianceSuggest entity5,
											BimrComplianceBJInfo entity6, String dataAuthority,
											String vcEmployeeId) {
		
        StringBuilder  hql = new StringBuilder();
//    officeId    submitOfficeId  
//        d.proposal,s.truth,g.suggest    g.isChange,d.proposal,d.bumf_num,  bimr_compliance_situation_person
        hql.append(" select c.investigationType,c.investigationName,c.compName,c.project_code,c.id," +
        		" c.isInside,c.projectName,c.investigationDep,s.problem,p.employeeNumber, " +
        		" p.employeeName,p.employeePostion,c.investigationFrom,c.itemName,c.isAnonymous,c.accusationTime, " +
        		" c.assignLeader,c.responsiblePerson,c.startTime,c.endTime, " +
        		" s.dataType,s.isTruth,s.truth,s.isAccountability,n.suggestInfo,g.suggest,o.office_id, " +
        		" o.`name`,o.submit_person_name,o.submit_time,o.audit_person_name, " +
        		" o.audit_content,g.isChange,n.submitOfficeId,n.officeId,g.accountabilityPerson, " +
        		" g.changeLastTime,g.changeProgress,g.changeStatus,	count(c.id) " +
        		" FROM bimr_compliance c  "+
        		" LEFT  JOIN  bimr_compliance_suggest g"+
        		" ON  c.id=g.complianceId"+
        		" LEFT JOIN bimr_compliance_situation s"+  
        		" ON g.complianceId=s.complianceId  "+
        		" LEFT JOIN bimr_compliance_person p  "+
        		" ON s.complianceId=p.complianceId  "+
        		" LEFT JOIN bimr_compliance_situation_person n "+ 
        		" ON s.id=n.situationId  "+
        		" LEFT JOIN bimr_compliance_bj_info o "+ 
        		" ON p.complianceId=o.compliance_id   "+
        		" where 1=1 " );
        
        
        /**
         * " FROM bimr_compliance_suggest g " +
        		"  JOIN   bimr_compliance c " +
        		" ON  g.complianceId=c.id " +
        		" LEFT JOIN bimr_compliance_situation s " +
        		" ON g.complianceId=s.complianceId " +
        		" LEFT JOIN bimr_compliance_person p " +
        		" ON g.complianceId=p.complianceId " +
        		" LEFT JOIN bimr_compliance_situation_person n " +
        		" ON s.id=n.situationId " +
        		" LEFT JOIN bimr_compliance_bj_info o " +
        		" ON g.complianceId=o.compliance_id " +
         */
        
        if (entity1 != null  && entity2 != null && entity3 != null && entity4 !=null && entity5 !=null  && entity6 !=null) {
			
        	if (Common.notEmpty(entity1.getCompName())) {
				hql.append(" and c.compName like '%"  + entity1.getCompName()+"%'");
			}
//        	调查涉及单位
        	if (entity1.getStatus()!=null) {
				hql.append(" and c.status = "  + entity1.getStatus());
			}


        	if (Common.notEmpty(entity1.getProjectName())) {
				hql.append(" and c.projectName like '%"   + entity1.getProjectName()+"%'");
			}
//        	7调查项目名称

        	if (Common.notEmpty(entity1.getAccusationTime())) {
				 hql.append(" and c.startTime >= date('"+entity1.getAccusationTime()+ "')");
			}
//        	调查时间
//        	19
        	if (Common.notEmpty(entity1.getEndTime())) {
        		 hql.append(" and c.endTime <= date('"+entity1.getEndTime()+ "')");
			}
//        	20调查时间
        	
        	
//        	if(Common.notEmpty(dataAuthority)){
//            	hql.append(" and ( s.id in (select projectId from BimrInsideAuditProjectAppendix where auditImplDeptId in ("+dataAuthority+"))");
//            }
//            if(Common.notEmpty(vcEmployeeId)){
//                hql.append(" or s.projectPrincipalId = " + vcEmployeeId +" )");
//            }
        	
//     s.situationId IN   	SELECT c.id FROM BimrComplianceSituation c WHERE c.isDel = 0 AND c.complianceId = :complianceId
//        
        	
 
        	hql.append(" GROUP BY   s.id ");
   
		}
        
//        hql.append(" order by s.createDate desc  ");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString()); 
        return query.list();
	}

	@Override
	public Integer getComplianceAbilityCount(Integer id) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(0) FROM bimr_compliance_situation s JOIN bimr_compliance_situation_person p on s.id = p.situationId and s.isDel = 0 and s.isAccountability = 1 and s.complianceId = "+id);
		 Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString()); 
		 return Integer.parseInt(query.uniqueResult().toString());
	}
}
