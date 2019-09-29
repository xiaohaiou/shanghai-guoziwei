package com.softline.dao.knowledgeStoreHouse.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.knowledgeStoreHouse.IKnowledgeStoreHouseDao;
import com.softline.entity.KnowledgeStoreHouse;

@Repository(value = "knowledgeStoreHouseDao")
public class KnowledgeStoreHouseDao implements IKnowledgeStoreHouseDao{

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public Integer getKnowledgeStoreHouseListCount(KnowledgeStoreHouse entity, String dataAuthority) {
		if(entity == null){
			return 0;
		}	
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from KnowledgeStoreHouse s where 1=1 and isDel=0");
		if(entity != null) {
			if(Common.notEmpty(entity.getDocumentNo()))	{
				hql.append(" and s.documentNo like '%"+entity.getDocumentNo()+"%'");
			}
			if(Common.notEmpty(entity.getDocumentName()))	{
				hql.append(" and s.documentName like '%"+entity.getDocumentName()+"%'");
			}
			if(entity.getOrganizationId() == null) {
				hql.append(" and s.organizationId in( "+dataAuthority+")");
			} else {
				hql.append(" and s.organizationId = "+entity.getOrganizationId());
			}
			if(entity.getModuleId() != null) {
				hql.append(" and s.moduleId = "+entity.getModuleId());
			}
			if(Common.notEmpty(entity.getYear())) {
				hql.append(" and s.year = '"+entity.getYear()+ "'");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<KnowledgeStoreHouse> getKnowledgeStoreHouseList(KnowledgeStoreHouse entity,
			Integer offset, Integer pageSize, String dataAuthority) {
		if(entity == null)
			return new ArrayList<KnowledgeStoreHouse>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from KnowledgeStoreHouse s where 1=1 and isDel=0 ");
		if(entity != null) {
			if(Common.notEmpty(entity.getDocumentNo()))	{
				hql.append(" and s.documentNo like '%"+entity.getDocumentNo()+"%'");
			}
			if(Common.notEmpty(entity.getDocumentName()))	{
				hql.append(" and s.documentName like '%"+entity.getDocumentName()+"%'");
			}
			if(entity.getOrganizationId() == null) {
				hql.append(" and s.organizationId in( "+dataAuthority+")");
			} else {
				hql.append(" and s.organizationId = "+entity.getOrganizationId());
			}
			if(entity.getModuleId() != null) {
				hql.append(" and s.moduleId = "+entity.getModuleId());
			}
			if(Common.notEmpty(entity.getYear())) {
				hql.append(" and s.year = '"+entity.getYear()+ "'");
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
	public Integer saveKnowledgeStoreHouse(KnowledgeStoreHouse entity) {
		return (Integer) sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void updateKnowledgeStoreHouse(KnowledgeStoreHouse entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void deleteKnowledgeStoreHouse(Integer id) {
		String hql = "update KnowledgeStoreHouse s set s.isDel=1 where s.id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public KnowledgeStoreHouse getKnowledgeStoreHouseById(Integer id) {
		String hql = " from KnowledgeStoreHouse s where s.isDel=0 and s.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (KnowledgeStoreHouse)query.uniqueResult();
	}

	@Override
	public KnowledgeStoreHouse getKnowledgeStoreHouse(KnowledgeStoreHouse entity) {
		String hql = " from KnowledgeStoreHouse s where s.isDel=0 and s.id = " + entity.getId();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (KnowledgeStoreHouse)query.uniqueResult();
	}

	@Override
	public List<KnowledgeStoreHouse> getKnowledgeStoreHouseByDocumentNo(String documentNo, Integer id) {
		StringBuilder hql = new StringBuilder();
		hql.append("from KnowledgeStoreHouse s where 1=1 and isDel=0 ");
		if(Common.notEmpty(documentNo))	{
			hql.append(" and s.documentNo = '"+documentNo+"'");
		}
		if (id != null) {
			hql.append(" and s.id !="+id);
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
	
}
