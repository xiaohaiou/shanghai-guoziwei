package com.softline.dao.bimr.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.bimr.IDutyDao;
import com.softline.entity.bimr.BimrDuty;

/**
 * 合规培训DaoImpl
 * @author pengguolin
 */
@Repository(value = "dutyDao")
public class DutyDao implements IDutyDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void delDuty(Integer id) {
		String hql = "update BimrDuty h set h.isDel = 1 where h.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public BimrDuty getById(Integer id) {
		String hql = " from BimrDuty h where h.isDel = 0 and h.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (BimrDuty)query.uniqueResult();
	}

	@Override
	public Integer getDutyListCount(BimrDuty entity, String dataAuthority) {
		if(entity == null){
			return 0;
		}	
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from BimrDuty s where 1=1 and isDel=0 ");
		if(entity != null) {
			if(Common.notEmpty(entity.getStartDate())) {
				hql.append(" and s.happenDate >= date('"+entity.getStartDate()+ "')");
			}
			if(Common.notEmpty(entity.getEndDate())){
				hql.append(" and s.happenDate <= date('"+entity.getEndDate()+ "')");
			}
			if(Common.notEmpty(entity.getBumfNum())) {
				hql.append(" and s.bumfNum like '%"+entity.getBumfNum()+ "%'");
			}
			if(Common.notEmpty(entity.getArticleNum())) {
				hql.append(" and s.articleNum like '%"+entity.getArticleNum()+ "%'");
			}
			if(Common.notEmpty(entity.getPerson())) {
				hql.append(" and s.person like '%"+entity.getPerson()+ "%'");
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(dataAuthority != null) {
				hql.append(" and s.personId in (select vcEmployeeId from HhUsers where nnodeId in ("+dataAuthority+"))");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BimrDuty> getDutyList(BimrDuty entity, Integer offset,
			Integer pageSize, String dataAuthority) {
		if(entity == null)
			return new ArrayList<BimrDuty>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from BimrDuty s where 1=1 and isDel=0 ");
		if(entity != null) {
			if(Common.notEmpty(entity.getStartDate())) {
				hql.append(" and s.happenDate >= date('"+entity.getStartDate()+ "')");
			}
			if(Common.notEmpty(entity.getEndDate())){
				hql.append(" and s.happenDate <= date('"+entity.getEndDate()+ "')");
			}
			if(Common.notEmpty(entity.getBumfNum())) {
				hql.append(" and s.bumfNum like '%"+entity.getBumfNum()+ "%'");
			}
			if(Common.notEmpty(entity.getArticleNum())) {
				hql.append(" and s.articleNum like '%"+entity.getArticleNum()+ "%'");
			}
			if(Common.notEmpty(entity.getPerson())) {
				hql.append(" and s.person like '%"+entity.getPerson()+ "%'");
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(dataAuthority != null) {
				hql.append(" and s.personId in (select vcEmployeeId from HhUsers where nnodeId in ("+dataAuthority+"))");
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
	public Integer getExamineDutyListCount(BimrDuty entity, String dataAuthority) {
		if(entity == null){
			return 0;
		}	
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from BimrDuty s where 1=1 and isDel=0 and s.status != 50112 ");
		if(entity != null) {
			if(Common.notEmpty(entity.getStartDate())) {
				hql.append(" and s.happenDate >= date('"+entity.getStartDate()+ "')");
			}
			if(Common.notEmpty(entity.getEndDate())){
				hql.append(" and s.happenDate <= date('"+entity.getEndDate()+ "')");
			}
			if(Common.notEmpty(entity.getBumfNum())) {
				hql.append(" and s.bumfNum like '%"+entity.getBumfNum()+ "%'");
			}
			if(Common.notEmpty(entity.getArticleNum())) {
				hql.append(" and s.articleNum like '%"+entity.getArticleNum()+ "%'");
			}
			if(Common.notEmpty(entity.getPerson())) {
				hql.append(" and s.person like '%"+entity.getPerson()+ "%'");
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(dataAuthority != null) {
				hql.append(" and s.personId in (select vcEmployeeId from HhUsers where nnodeId in ("+dataAuthority+"))");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BimrDuty> getExamineDutyList(BimrDuty entity, Integer offset,
			Integer pageSize, String dataAuthority) {
		if(entity == null)
			return new ArrayList<BimrDuty>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from BimrDuty s where 1=1 and isDel=0 and s.status != 50112 ");
		if(entity != null) {
			if(Common.notEmpty(entity.getStartDate())) {
				hql.append(" and s.happenDate >= date('"+entity.getStartDate()+ "')");
			}
			if(Common.notEmpty(entity.getEndDate())){
				hql.append(" and s.happenDate <= date('"+entity.getEndDate()+ "')");
			}
			if(Common.notEmpty(entity.getBumfNum())) {
				hql.append(" and s.bumfNum like '%"+entity.getBumfNum()+ "%'");
			}
			if(Common.notEmpty(entity.getArticleNum())) {
				hql.append(" and s.articleNum like '%"+entity.getArticleNum()+ "%'");
			}
			if(Common.notEmpty(entity.getPerson())) {
				hql.append(" and s.person like '%"+entity.getPerson()+ "%'");
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(dataAuthority != null) {
				hql.append(" and s.personId in (select vcEmployeeId from HhUsers where nnodeId in ("+dataAuthority+"))");
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
	public Integer save(BimrDuty entity) {
		return (Integer) sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void update(BimrDuty entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public BimrDuty getBimrDutyByAccountableId(Integer id) {
		// TODO Auto-generated method stub
		String hql = " from BimrDuty h where h.isDel = 0 and h.accountableId = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (BimrDuty)query.uniqueResult();
	}
	
}
