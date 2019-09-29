package com.softline.dao.riskcontrol.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;

import com.softline.dao.riskcontrol.IAuditProjectFindQuestionDao;
import com.softline.entity.AuditProjectFindQuestion;

@Repository(value = "auditProjectFindQuestionDao")
public class AuditProjectFindQuestionDao implements IAuditProjectFindQuestionDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer getAuditProjectFindQuestionListCount(AuditProjectFindQuestion entity) {
		if(entity == null){
			return 0;
		}	
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from AuditProjectFindQuestion s where 1=1 and isDel=0 ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	@Override
	public List<AuditProjectFindQuestion> getAuditProjectFindQuestionList(AuditProjectFindQuestion entity,
			Integer offset, Integer pageSize) {
		if(entity==null)
			return new ArrayList<AuditProjectFindQuestion>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from AuditProjectFindQuestion s where 1=1 and isDel=0 ");
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
	public Integer saveAuditProjectFindQuestion(AuditProjectFindQuestion entity) {
		return (Integer) sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public AuditProjectFindQuestion getAuditProjectFindQuestionById(Integer id) {
		String hql = " from AuditProjectFindQuestion s where s.isDel = 0 and s.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (AuditProjectFindQuestion)query.uniqueResult();
	}

	@Override
	public void updateAuditProjectFindQuestion(AuditProjectFindQuestion entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void deleteAuditProjectFindQuestion(Integer id) {
		String hql = "update AuditProjectFindQuestion s set s.isDel=1" + " where s.id=" + id + "";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public AuditProjectFindQuestion getAuditProjectFindQuestionByAuditProjectId(Integer auditProjectId) {
		String hql = " from AuditProjectFindQuestion s where isDel = 0 and s.auditProjectId = " + auditProjectId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (AuditProjectFindQuestion)query.uniqueResult();
	}
}
