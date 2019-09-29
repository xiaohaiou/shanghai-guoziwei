package com.softline.dao.riskcontrol.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;

import com.softline.common.Common;
import com.softline.dao.riskcontrol.IRiskControlContractDao;
import com.softline.entity.RiskControlContract;

@Repository(value = "riskControlContractDao")
public class RiskControlContractDao implements IRiskControlContractDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer getContractListCount(RiskControlContract entity, String contractSignDate2, String dataAuthority) {
		if(entity == null){
			return 0;
		}	
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from RiskControlContract s where 1=1 and isDel=0 ");
		if(entity != null) {
			if(Common.notEmpty(entity.getContractName())) {
				hql.append(" and s.contractName like '%"+entity.getContractName()+ "%'");
			}
			if(entity.getContractBelongCompany() == null)	{
				hql.append(" and s.contractBelongCompany in( "+dataAuthority+")");
			} else {
				hql.append(" and s.contractBelongCompany = "+entity.getContractBelongCompany());
			}
			if(entity.getContractType() != null) {
				hql.append(" and s.contractType = "+entity.getContractType());
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getContractSignDate())) {
				hql.append(" and s.contractSignDate >= date('"+entity.getContractSignDate()+ "')");
			}
			if(Common.notEmpty(contractSignDate2)){
				hql.append(" and s.contractSignDate <= date('"+contractSignDate2+ "')");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	@Override
	public List<RiskControlContract> getContractList(RiskControlContract entity, Integer offset, 
			Integer pageSize, String contractSignDate2, String dataAuthority) {
		if(entity == null)
			return new ArrayList<RiskControlContract>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from RiskControlContract s where 1=1 and isDel=0 ");
		if(entity != null) {
			if(Common.notEmpty(entity.getContractName())) {
				hql.append(" and s.contractName like '%"+entity.getContractName()+ "%'");
			}
			if(entity.getContractBelongCompany() == null)	{
				hql.append(" and s.contractBelongCompany in( "+dataAuthority+")");
			} else {
				hql.append(" and s.contractBelongCompany = "+entity.getContractBelongCompany());
			}
			if(entity.getContractType() != null) {
				hql.append(" and s.contractType = "+entity.getContractType());
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getContractSignDate())) {
				hql.append(" and s.contractSignDate >= date('"+entity.getContractSignDate()+ "')");
			}
			if(Common.notEmpty(contractSignDate2)){
				hql.append(" and s.contractSignDate <= date('"+contractSignDate2+ "')");
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
	public Integer saveContract(RiskControlContract entity) {
		return (Integer) sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public RiskControlContract getContractById(Integer id) {
		String hql = " from RiskControlContract s where s.isDel = 0 and s.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (RiskControlContract)query.uniqueResult();
	}

	@Override
	public void updateContract(RiskControlContract entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void deleteContract(Integer id) {
		String hql = "update RiskControlContract s set s.isDel=1" + " where s.id=" + id + "";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public RiskControlContract getContract(RiskControlContract entity) {
		String hql = " from RiskControlContract s where isDel = 0 and s.id = " + entity.getId();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (RiskControlContract)query.uniqueResult();
	}

	@Override
	public Integer getExamineContractListCount(RiskControlContract entity, String contractSignDate2, String dataAuthority) {
		if(entity == null){
			return 0;
		}	
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from RiskControlContract s where 1=1 and isDel=0 and status!=50112");
		if(entity != null) {
			if(Common.notEmpty(entity.getContractName())) {
				hql.append(" and s.contractName like '%"+entity.getContractName()+ "%'");
			}
			if(entity.getContractBelongCompany() == null)	{
				hql.append(" and s.contractBelongCompany in( "+dataAuthority+")");
			} else {
				hql.append(" and s.contractBelongCompany = "+entity.getContractBelongCompany());
			}
			if(entity.getContractType() != null) {
				hql.append(" and s.contractType = "+entity.getContractType());
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getContractSignDate())) {
				hql.append(" and s.contractSignDate >= date('"+entity.getContractSignDate()+ "')");
			}
			if(Common.notEmpty(contractSignDate2)){
				hql.append(" and s.contractSignDate <= date('"+contractSignDate2+ "')");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<RiskControlContract> getExamineContractList(RiskControlContract entity, Integer offset, 
			Integer pageSize, String contractSignDate2, String dataAuthority) {
		if(entity == null)
			return new ArrayList<RiskControlContract>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from RiskControlContract s where 1=1 and isDel=0 and status!=50112");
		if(entity != null) {
			if(Common.notEmpty(entity.getContractName())) {
				hql.append(" and s.contractName like '%"+entity.getContractName()+ "%'");
			}
			if(entity.getContractBelongCompany() == null)	{
				hql.append(" and s.contractBelongCompany in( "+dataAuthority+")");
			} else {
				hql.append(" and s.contractBelongCompany = "+entity.getContractBelongCompany());
			}
			if(entity.getContractType() != null) {
				hql.append(" and s.contractType = "+entity.getContractType());
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getContractSignDate())) {
				hql.append(" and s.contractSignDate >= date('"+entity.getContractSignDate()+ "')");
			}
			if(Common.notEmpty(contractSignDate2)){
				hql.append(" and s.contractSignDate <= date('"+contractSignDate2+ "')");
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
