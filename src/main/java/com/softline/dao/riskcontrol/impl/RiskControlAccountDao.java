package com.softline.dao.riskcontrol.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;

import com.softline.common.Common;
import com.softline.dao.riskcontrol.IRiskControlAccountDao;
import com.softline.entity.HhOrganInfo;
import com.softline.entity.RiskControlAccount;

@Repository(value = "riskControlAccountDao")
public class RiskControlAccountDao implements IRiskControlAccountDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer getAccountListCount(RiskControlAccount entity, String complainReceiveDate2, String dataAuthority) {
		if(entity == null){
			return 0;
		}	
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from RiskControlAccount s where 1=1 and isDel=0 ");
		if(entity != null) {
			if(Common.notEmpty(entity.getComplainProjectName()))	{
				hql.append(" and s.complainProjectName like '%"+entity.getComplainProjectName()+"%'");
			}
			if(entity.getComplainedPersonDept() == null) {
				hql.append(" and s.complainedPersonDept in( "+dataAuthority+")");
			} else {
				hql.append(" and s.complainedPersonDept = "+entity.getComplainedPersonDept());
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getComplainReceiveDate())) {
				hql.append(" and s.complainReceiveDate >= date('"+entity.getComplainReceiveDate()+ "')");
			}
			if (Common.notEmpty(complainReceiveDate2)) {
				hql.append(" and s.complainReceiveDate <= date('"+complainReceiveDate2+ "')");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	@Override
	public List<RiskControlAccount> getAccountList(RiskControlAccount entity,
			Integer offset, Integer pageSize, String complainReceiveDate2, String dataAuthority) {
		if(entity == null)
			return new ArrayList<RiskControlAccount>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from RiskControlAccount s where 1=1 and isDel=0 ");
		if(entity != null) {
			if(Common.notEmpty(entity.getComplainProjectName()))	{
				hql.append(" and s.complainProjectName like '%"+entity.getComplainProjectName()+"%'");
			}
			if(entity.getComplainedPersonDept() == null) {
				hql.append(" and s.complainedPersonDept in( "+dataAuthority+")");
			} else {
				hql.append(" and s.complainedPersonDept = "+entity.getComplainedPersonDept());
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getComplainReceiveDate())) {
				hql.append(" and s.complainReceiveDate >= date('"+entity.getComplainReceiveDate()+ "')");
			}
			if (Common.notEmpty(complainReceiveDate2)) {
				hql.append(" and s.complainReceiveDate <= date('"+complainReceiveDate2+ "')");
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
	public Integer saveAccount(RiskControlAccount entity) {
		return (Integer) sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public RiskControlAccount getAccountById(Integer id) {
		String hql = " from RiskControlAccount s where s.isDel = 0 and s.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (RiskControlAccount)query.uniqueResult();
	}

	@Override
	public void updateAccount(RiskControlAccount entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void deleteAccount(Integer id) {
		String hql = "update RiskControlAccount s set s.isDel=1" + " where s.id=" + id + "";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public RiskControlAccount getAccount(RiskControlAccount entity) {
		String hql = " from RiskControlAccount s where isDel = 0 and s.id = " + entity.getId();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (RiskControlAccount)query.uniqueResult();
	}

	@Override
	public Integer getExamineAccountListCount(RiskControlAccount entity, String complainReceiveDate2, String dataAuthority) {
		if(entity == null){
			return 0;
		}	
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from RiskControlAccount s where 1=1 and isDel=0 and status!=50112");
		if(entity != null) {
			if(Common.notEmpty(entity.getComplainProjectName()))	{
				hql.append(" and s.complainProjectName like '%"+entity.getComplainProjectName()+"%'");
			}
			if(entity.getComplainedPersonDept() == null) {
				hql.append(" and s.complainedPersonDept in( "+dataAuthority+")");
			} else {
				hql.append(" and s.complainedPersonDept = "+entity.getComplainedPersonDept());
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getComplainReceiveDate())) {
				hql.append(" and s.complainReceiveDate >= date('"+entity.getComplainReceiveDate()+ "')");
			}
			if (Common.notEmpty(complainReceiveDate2)) {
				hql.append(" and s.complainReceiveDate <= date('"+complainReceiveDate2+ "')");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<RiskControlAccount> getExamineAccountList(RiskControlAccount entity, Integer offset, 
			Integer pageSize, String complainReceiveDate2, String dataAuthority) {
		if(entity == null)
			return new ArrayList<RiskControlAccount>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from RiskControlAccount s where 1=1 and isDel=0 and status!=50112");
		if(entity != null) {
			if(Common.notEmpty(entity.getComplainProjectName()))	{
				hql.append(" and s.complainProjectName like '%"+entity.getComplainProjectName()+"%'");
			}
			if(entity.getComplainedPersonDept() == null) {
				hql.append(" and s.complainedPersonDept in( "+dataAuthority+")");
			} else {
				hql.append(" and s.complainedPersonDept = "+entity.getComplainedPersonDept());
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getComplainReceiveDate())) {
				hql.append(" and s.complainReceiveDate >= date('"+entity.getComplainReceiveDate()+ "')");
			}
		}
		if (Common.notEmpty(complainReceiveDate2)) {
			hql.append(" and s.complainReceiveDate <= date('"+complainReceiveDate2+ "')");
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
	public HhOrganInfo getCoreCompId(String companyId) {
		String hql = "from HhOrganInfo h where nnodeId = '" + companyId + "' and cflag = 1 ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (HhOrganInfo)query.uniqueResult();
	}
}
