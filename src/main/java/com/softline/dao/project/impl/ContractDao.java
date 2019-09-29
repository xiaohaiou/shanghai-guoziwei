package com.softline.dao.project.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.dao.project.IContractDao;
import com.softline.entity.project.PjContract;
import com.softline.entity.project.PjContractHistory;
@Repository("contractDao")
public class ContractDao implements IContractDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public List<PjContract> getContracts(Integer pjId, Integer offset,
			Integer pageSize) {
		StringBuffer hql = new StringBuffer();
		hql.append("from  PjContract t where t.isdel = 0");
		if(pjId != null){
			hql.append(" and t.pjId =" + pjId);
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offset != null && pageSize!=null){
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
		}
		return query.list();
	}

	@Override
	public List<PjContract> getContracts(Integer pjId,Integer reportStatus) {
		StringBuffer hql = new StringBuffer();
		hql.append("from  PjContract t where t.isdel = 0");
		if(pjId != null){
			hql.append(" and t.pjId =" + pjId);
		}
		if(pjId != null){
			hql.append(" and t.reportStatus = " + reportStatus);
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<PjContractHistory> getContractHistories(Integer contractId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from  PjContractHistory t where t.isdel = 0");
		hql.append(" and t.contractId =" + contractId);
		hql.append(" order by t.reportTime desc");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
}
