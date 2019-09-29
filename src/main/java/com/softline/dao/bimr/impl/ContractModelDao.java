package com.softline.dao.bimr.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.dao.bimr.IContractModelDao;
import com.softline.entity.bimr.BimrContractModel;

/**
 * 合同范本DaoImpl
 * @author pengguolin
 */
@Repository(value = "contractModelDao")
public class ContractModelDao implements IContractModelDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void delContractModel(Integer id) {
		String hql = "update BimrContractModel h set h.isDel = 1 where h.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public BimrContractModel getById(Integer id) {
		String hql = " from BimrContractModel h where h.isDel = 0 and h.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (BimrContractModel)query.uniqueResult();
	}

	@Override
	public Integer getContractModelListCount(BimrContractModel entity, String dataAuthority) {
		if(entity == null){
			return 0;
		}	
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from BimrContractModel s where 1=1 and isDel=0 ");
		if(entity != null) {
			if(entity.getContractType() != null) {
				hql.append(" and s.contractType = "+entity.getContractType());
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BimrContractModel> getContractModelList(BimrContractModel entity, Integer offset,
			Integer pageSize, String dataAuthority) {
		if(entity == null)
			return new ArrayList<BimrContractModel>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from BimrContractModel s where 1=1 and isDel=0 ");
		if(entity != null) {
			if(entity.getContractType() != null) {
				hql.append(" and s.contractType = "+entity.getContractType());
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
	public Integer save(BimrContractModel entity) {
		return (Integer) sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void update(BimrContractModel entity) {
		sessionFactory.getCurrentSession().update(entity);
	}
	
}
