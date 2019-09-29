package com.softline.dao.riskcontrol.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;

import com.softline.common.Common;
import com.softline.dao.riskcontrol.IAuditProjectDao;
import com.softline.entity.AdDocument;
import com.softline.entity.AuditProject;

@Repository(value = "auditProjectDao")
public class AuditProjectDao implements IAuditProjectDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer getAuditProjectListCount(AuditProject entity, String dataAuthority) {
		if(entity == null){
			return 0;
		}	
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from AuditProject s where 1=1 and isDel=0 ");
		if(entity != null) {
			if(Common.notEmpty(entity.getAuditStartDate()))	{
				hql.append(" and s.auditStartDate >= date('"+entity.getAuditStartDate()+ "')");
			}
			if(Common.notEmpty(entity.getAuditEndDate())) {
				hql.append(" and s.auditEndDate <= date('"+entity.getAuditEndDate()+ "')");
			}
			if(entity.getAuditDept() == null) {
				hql.append(" and s.auditDept in( "+dataAuthority+")");
			}else {
				hql.append(" and s.auditDept = "+entity.getAuditDept());
			}
			if(Common.notEmpty(entity.getAuditProjectName())) {
				hql.append(" and s.auditProjectName like '%"+entity.getAuditProjectName()+ "%'");
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(entity.getAuditType() != null) {
				hql.append(" and s.auditType = "+entity.getAuditType());
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	@Override
	public List<AuditProject> getAuditProjectList(AuditProject entity,
			Integer offset, Integer pageSize, String dataAuthority) {
		if(entity==null)
			return new ArrayList<AuditProject>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from AuditProject s where 1=1 and isDel=0 ");
		if(entity!=null) {
			if(Common.notEmpty(entity.getAuditStartDate()))	{
				hql.append(" and s.auditStartDate >= date('"+entity.getAuditStartDate()+ "')");
			}
			if(Common.notEmpty(entity.getAuditEndDate())) {
				hql.append(" and s.auditEndDate <= date('"+entity.getAuditEndDate()+ "')");
			}
			if(entity.getAuditDept() == null) {
				hql.append(" and s.auditDept in( "+dataAuthority+")");
			}else {
				hql.append(" and s.auditDept = "+entity.getAuditDept());
			}
			if(Common.notEmpty(entity.getAuditProjectName())) {
				hql.append(" and s.auditProjectName like '%"+entity.getAuditProjectName()+ "%'");
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(entity.getAuditType() != null) {
				hql.append(" and s.auditType = "+entity.getAuditType());
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
	public Integer saveAuditProject(AuditProject entity) {
		return (Integer) sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public AuditProject getAuditProjectById(Integer id) {
		String hql = " from AuditProject s where s.isDel = 0 and s.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (AuditProject)query.uniqueResult();
	}

	@Override
	public void updateAuditProject(AuditProject entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void deleteAuditProject(Integer id) {
		String hql = "update AuditProject s set s.isDel=1" + " where s.id=" + id + "";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public AuditProject getAuditProject(AuditProject entity) {
		String hql = " from AuditProject s where isDel = 0 and s.id = " + entity.getId();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (AuditProject)query.uniqueResult();
	}

	@Override
	public Integer getExamineAuditProjectListCount(AuditProject entity, String dataAuthority) {
		if(entity == null){
			return 0;
		}	
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from AuditProject s where 1=1 and isDel=0 and status!=50112");
		if(entity != null) {
			if(Common.notEmpty(entity.getAuditStartDate()))	{
				hql.append(" and s.auditStartDate >= date('"+entity.getAuditStartDate()+ "')");
			}
			if(Common.notEmpty(entity.getAuditEndDate())) {
				hql.append(" and s.auditEndDate <= date('"+entity.getAuditEndDate()+ "')");
			}
			if(entity.getAuditDept() == null) {
				hql.append(" and s.auditDept in( "+dataAuthority+")");
			}else {
				hql.append(" and s.auditDept = "+entity.getAuditDept());
			}
			if(Common.notEmpty(entity.getAuditProjectName())) {
				hql.append(" and s.auditProjectName like '%"+entity.getAuditProjectName()+ "%'");
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(entity.getAuditType() != null) {
				hql.append(" and s.auditType = "+entity.getAuditType());
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<AuditProject> getExamineAuditProjectList(AuditProject entity,
			Integer offset, Integer pageSize, String dataAuthority) {
		if(entity==null)
			return new ArrayList<AuditProject>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from AuditProject s where 1=1 and isDel=0 and status!=50112");
		if(entity!=null) {
			if(Common.notEmpty(entity.getAuditStartDate()))	{
				hql.append(" and s.auditStartDate >= date('"+entity.getAuditStartDate()+ "')");
			}
			if(Common.notEmpty(entity.getAuditEndDate())) {
				hql.append(" and s.auditEndDate <= date('"+entity.getAuditEndDate()+ "')");
			}
			if(entity.getAuditDept() == null) {
				hql.append(" and s.auditDept in( "+dataAuthority+")");
			}else{
				hql.append(" and s.auditDept = "+entity.getAuditDept());
			}
			if(Common.notEmpty(entity.getAuditProjectName())) {
				hql.append(" and s.auditProjectName like '%"+entity.getAuditProjectName()+ "%'");
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(entity.getAuditType() != null) {
				hql.append(" and s.auditType = "+entity.getAuditType());
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
