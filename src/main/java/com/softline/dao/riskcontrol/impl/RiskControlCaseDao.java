package com.softline.dao.riskcontrol.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;

import com.softline.common.Common;
import com.softline.dao.riskcontrol.IRiskControlCaseDao;
import com.softline.entity.RiskControlCase;

@Repository(value = "riskControlCaseDao")
public class RiskControlCaseDao implements IRiskControlCaseDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer getCaseListCount(RiskControlCase entity, String caseHappenDate2, String dataAuthority) {
		if(entity == null){
			return 0;
		}	
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from RiskControlCase s where 1=1 and isDel=0 ");
		if(entity != null) {
			if(entity.getCaseBelongCompany() == null)	{
				hql.append(" and s.caseBelongCompany in( "+dataAuthority+")");
			} else {
				hql.append(" and s.caseBelongCompany = "+entity.getCaseBelongCompany());
			}
			if(entity.getCaseType() != null) {
				hql.append(" and s.caseType = "+entity.getCaseType());
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getCaseHappenDate())) {
				hql.append(" and s.caseHappenDate >= date('"+entity.getCaseHappenDate()+ "')");
			}
			if(Common.notEmpty(caseHappenDate2)){
				hql.append(" and s.caseHappenDate <= date('"+caseHappenDate2+ "')");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	@Override
	public List<RiskControlCase> getCaseList(RiskControlCase entity,
			Integer offset, Integer pageSize, String caseHappenDate2, String dataAuthority) {
		if(entity == null)
			return new ArrayList<RiskControlCase>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from RiskControlCase s where 1=1 and isDel=0 ");
		if(entity != null) {
			if(entity.getCaseBelongCompany() == null)	{
				hql.append(" and s.caseBelongCompany in( "+dataAuthority+")");
			} else {
				hql.append(" and s.caseBelongCompany = "+entity.getCaseBelongCompany());
			}
			if(entity.getCaseType() != null) {
				hql.append(" and s.caseType = "+entity.getCaseType());
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getCaseHappenDate())) {
				hql.append(" and s.caseHappenDate >= date('"+entity.getCaseHappenDate()+ "')");
			}
			if(Common.notEmpty(caseHappenDate2)){
				hql.append(" and s.caseHappenDate <= date('"+caseHappenDate2+ "')");
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
	public Integer saveCase(RiskControlCase entity) {
		return (Integer) sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public RiskControlCase getCaseById(Integer id) {
		String hql = " from RiskControlCase s where s.isDel = 0 and s.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (RiskControlCase)query.uniqueResult();
	}

	@Override
	public void updateCase(RiskControlCase entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void deleteCase(Integer id) {
		String hql = "update RiskControlCase s set s.isDel=1" + " where s.id=" + id + "";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public RiskControlCase getCase(RiskControlCase entity) {
		String hql = " from RiskControlCase s where isDel = 0 and s.id = " + entity.getId();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (RiskControlCase)query.uniqueResult();
	}

	@Override
	public Integer getExamineCaseListCount(RiskControlCase entity, String caseHappenDate2, String dataAuthority) {
		if(entity == null){
			return 0;
		}	
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from RiskControlCase s where 1=1 and isDel=0 and status!=50112");
		if(entity != null) {
			if(entity.getCaseBelongCompany() == null)	{
				hql.append(" and s.caseBelongCompany in( "+dataAuthority+")");
			} else {
				hql.append(" and s.caseBelongCompany = "+entity.getCaseBelongCompany());
			}
			if(entity.getCaseType() != null) {
				hql.append(" and s.caseType = "+entity.getCaseType());
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getCaseHappenDate())) {
				hql.append(" and s.caseHappenDate >= date('"+entity.getCaseHappenDate()+ "')");
			}
			if(Common.notEmpty(caseHappenDate2)){
				hql.append(" and s.caseHappenDate <= date('"+caseHappenDate2+ "')");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<RiskControlCase> getExamineCaseList(RiskControlCase entity,
			Integer offset, Integer pageSize, String caseHappenDate2, String dataAuthority) {
		if(entity == null)
			return new ArrayList<RiskControlCase>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from RiskControlCase s where 1=1 and isDel=0 and status!=50112");
		if(entity != null) {
			if(entity.getCaseBelongCompany() == null)	{
				hql.append(" and s.caseBelongCompany in( "+dataAuthority+")");
			} else {
				hql.append(" and s.caseBelongCompany = "+entity.getCaseBelongCompany());
			}
			if(entity.getCaseType() != null) {
				hql.append(" and s.caseType = "+entity.getCaseType());
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getCaseHappenDate())) {
				hql.append(" and s.caseHappenDate >= date('"+entity.getCaseHappenDate()+ "')");
			}
			if(Common.notEmpty(caseHappenDate2)){
				hql.append(" and s.caseHappenDate <= date('"+caseHappenDate2+ "')");
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
}
