package com.softline.dao.riskcontrol.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;

import com.softline.dao.riskcontrol.IAuditProjectAbarbeitungQuestionDao;
import com.softline.entity.AuditProjectAbarbeitungQuestion;

@Repository(value = "auditProjectAbarbeitungQuestionDao")
public class AuditProjectAbarbeitungQuestionDao implements IAuditProjectAbarbeitungQuestionDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer getAuditProjectAbarbeitungQuestionListCount(AuditProjectAbarbeitungQuestion entity) {
		if(entity == null){
			return 0;
		}	
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from AuditProjectAbarbeitungQuestion s where 1=1 and isDel=0 ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	@Override
	public List<AuditProjectAbarbeitungQuestion> getAuditProjectAbarbeitungQuestionList(AuditProjectAbarbeitungQuestion entity,
			Integer offset, Integer pageSize) {
		if(entity==null)
			return new ArrayList<AuditProjectAbarbeitungQuestion>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from AuditProjectAbarbeitungQuestion s where 1=1 and isDel=0 ");
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
	public Integer saveAuditProjectAbarbeitungQuestion(AuditProjectAbarbeitungQuestion entity) {
		return (Integer) sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public AuditProjectAbarbeitungQuestion getAuditProjectAbarbeitungQuestionById(Integer id) {
		String hql = " from AuditProjectAbarbeitungQuestion s where s.isDel = 0 and s.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (AuditProjectAbarbeitungQuestion)query.uniqueResult();
	}

	@Override
	public void updateAuditProjectAbarbeitungQuestion(AuditProjectAbarbeitungQuestion entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void deleteAuditProjectAbarbeitungQuestion(Integer id) {
		String hql = "update AuditProjectAbarbeitungQuestion s set s.isDel=1" + " where s.id=" + id + "";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public List<AuditProjectAbarbeitungQuestion> getAuditProjectAbarbeitungQuestionByAuditProjectId(
			Integer auditProjectId) {
		StringBuilder  hql = new StringBuilder();
		hql.append("from AuditProjectAbarbeitungQuestion s where isDel=0 and s.auditProjectId="+ auditProjectId);
		hql.append(" order by s.createDate desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
}
