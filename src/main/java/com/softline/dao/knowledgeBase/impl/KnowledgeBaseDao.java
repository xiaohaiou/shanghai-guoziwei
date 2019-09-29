package com.softline.dao.knowledgeBase.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.knowledgeBase.IKnowledgeBaseDao;
import com.softline.entity.KnowledgeBase;

@Repository(value = "knowledgeBaseDao")
public class KnowledgeBaseDao implements IKnowledgeBaseDao{

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public Integer getKnowledgeBaseListCount(KnowledgeBase entity, String doDate2) {
		if(entity == null){
			return 0;
		}	
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from KnowledgeBase s where 1=1 and isDel=0");
		if(entity != null) {
			if(Common.notEmpty(entity.getTitle()))	{
				hql.append(" and s.title like '%"+entity.getTitle()+"%'");
			}
			if(Common.notEmpty(entity.getNo()))	{
				hql.append(" and s.no like '%"+entity.getNo()+"%'");
			}
			if(entity.getTypeId() != null) {
				hql.append(" and s.typeId = "+entity.getTypeId());
			}
			if(entity.getAreaId() != null) {
				hql.append(" and s.areaId = "+entity.getAreaId());
			}
			if(Common.notEmpty(entity.getDoDate())) {
				hql.append(" and s.doDate >= date('"+entity.getDoDate()+ "')");
			}
			if (Common.notEmpty(doDate2)) {
				hql.append(" and s.doDate <= date('"+doDate2+ "')");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<KnowledgeBase> getKnowledgeBaseList(KnowledgeBase entity,
			Integer offset, Integer pageSize, String doDate2) {
		if(entity == null)
			return new ArrayList<KnowledgeBase>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from KnowledgeBase s where 1=1 and isDel=0 ");
		if(entity != null) {
			if(Common.notEmpty(entity.getTitle()))	{
				hql.append(" and s.title like '%"+entity.getTitle()+"%'");
			}
			if(Common.notEmpty(entity.getNo()))	{
				hql.append(" and s.no like '%"+entity.getNo()+"%'");
			}
			if(entity.getTypeId() != null) {
				hql.append(" and s.typeId = "+entity.getTypeId());
			}
			if(entity.getAreaId() != null) {
				hql.append(" and s.areaId = "+entity.getAreaId());
			}
			if(Common.notEmpty(entity.getDoDate())) {
				hql.append(" and s.doDate >= date('"+entity.getDoDate()+ "')");
			}
			if (Common.notEmpty(doDate2)) {
				hql.append(" and s.doDate <= date('"+doDate2+ "')");
			}
		}
		hql.append(" order by s.top asc, s.doDate desc ");
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
	public Integer saveKnowledgeBase(KnowledgeBase entity) {
		return (Integer) sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void updateKnowledgeBase(KnowledgeBase entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void deleteKnowledgeBase(Integer id) {
		String hql = "update KnowledgeBase s set s.isDel=1 where s.id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public KnowledgeBase getKnowledgeBaseById(Integer id) {
		String hql = " from KnowledgeBase s where s.isDel=0 and s.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (KnowledgeBase)query.uniqueResult();
	}

	@Override
	public KnowledgeBase getKnowledgeBase(KnowledgeBase entity) {
		String hql = " from KnowledgeBase s where s.isDel=0 and s.id = " + entity.getId();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (KnowledgeBase)query.uniqueResult();
	}
	
}
