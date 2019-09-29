package com.softline.dao.bimr.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.bimr.ITrainDao;
import com.softline.entity.bimr.BimrTrain;

/**
 * 合规培训DaoImpl
 * @author pengguolin
 */
@Repository(value = "trainDao")
public class TrainDao implements ITrainDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void delTrain(Integer id) {
		String hql = "update BimrTrain h set h.isDel = 1 where h.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public BimrTrain getById(Integer id) {
		String hql = " from BimrTrain h where h.isDel = 0 and h.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (BimrTrain)query.uniqueResult();
	}

	@Override
	public Integer getTrainListCount(BimrTrain entity, String dataAuthority) {
		if(entity == null){
			return 0;
		}	
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from BimrTrain s where 1=1 and isDel=0 ");
		if(entity != null) {
			if(Common.notEmpty(entity.getTrainName())) {
				hql.append(" and s.trainName like '%"+entity.getTrainName()+ "%'");
			}
			if(Common.notEmpty(entity.getLecturer())) {
				hql.append(" and s.lecturer like '%"+entity.getLecturer()+ "%'");
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getStartDate())) {
				hql.append(" and s.trainDate >= date('"+entity.getStartDate()+ "')");
			}
			if(Common.notEmpty(entity.getEndDate())){
				hql.append(" and s.trainDate <= date('"+entity.getEndDate()+ "')");
			}
			if (Common.notEmpty(dataAuthority)) {
				hql.append(" and s.companyId in (" + dataAuthority + ")");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BimrTrain> getTrainList(BimrTrain entity, Integer offset,
			Integer pageSize, String dataAuthority) {
		if(entity == null)
			return new ArrayList<BimrTrain>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from BimrTrain s where 1=1 and isDel=0 ");
		if(entity != null) {
			if(Common.notEmpty(entity.getTrainName())) {
				hql.append(" and s.trainName like '%"+entity.getTrainName()+ "%'");
			}
			if(Common.notEmpty(entity.getLecturer())) {
				hql.append(" and s.lecturer like '%"+entity.getLecturer()+ "%'");
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getStartDate())) {
				hql.append(" and s.trainDate >= date('"+entity.getStartDate()+ "')");
			}
			if(Common.notEmpty(entity.getEndDate())){
				hql.append(" and s.trainDate <= date('"+entity.getEndDate()+ "')");
			}
			if (Common.notEmpty(dataAuthority)) {
				hql.append(" and s.companyId in (" + dataAuthority + ")");
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
	public List<BimrTrain> getTrainListExport(BimrTrain entity, String dataAuthority) {
		if(entity == null)
			return new ArrayList<BimrTrain>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from BimrTrain s where 1=1 and isDel=0 ");
		if(entity != null) {
			if(Common.notEmpty(entity.getTrainName())) {
				hql.append(" and s.trainName like '%"+entity.getTrainName()+ "%'");
			}
			if(Common.notEmpty(entity.getLecturer())) {
				hql.append(" and s.lecturer like '%"+entity.getLecturer()+ "%'");
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getStartDate())) {
				hql.append(" and s.trainDate >= date('"+entity.getStartDate()+ "')");
			}
			if(Common.notEmpty(entity.getEndDate())){
				hql.append(" and s.trainDate <= date('"+entity.getEndDate()+ "')");
			}
			if (Common.notEmpty(dataAuthority)) {
				hql.append(" and s.companyId in (" + dataAuthority + ")");
			}
		}
		hql.append(" order by s.createDate desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		
		return query.list();
	}
	@Override
	public Integer getExamineTrainListCount(BimrTrain entity, String dataAuthority) {
		if(entity == null){
			return 0;
		}	
		StringBuilder  hql = new StringBuilder();
		hql.append("select count(0) from BimrTrain s where 1=1 and isDel=0 and s.status != 50112 ");
		if(entity != null) {
			if(Common.notEmpty(entity.getTrainName())) {
				hql.append(" and s.trainName like '%"+entity.getTrainName()+ "%'");
			}
			if(Common.notEmpty(entity.getLecturer())) {
				hql.append(" and s.lecturer like '%"+entity.getLecturer()+ "%'");
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getStartDate())) {
				hql.append(" and s.trainDate >= date('"+entity.getStartDate()+ "')");
			}
			if(Common.notEmpty(entity.getEndDate())){
				hql.append(" and s.trainDate <= date('"+entity.getEndDate()+ "')");
			}
			if (Common.notEmpty(dataAuthority)) {
				hql.append(" and s.companyId in (" + dataAuthority + ")");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BimrTrain> getExamineTrainList(BimrTrain entity, Integer offset,
			Integer pageSize, String dataAuthority) {
		if(entity == null)
			return new ArrayList<BimrTrain>();
		StringBuilder  hql = new StringBuilder();
		hql.append("from BimrTrain s where 1=1 and isDel=0 and s.status != 50112 ");
		if(entity != null) {
			if(Common.notEmpty(entity.getTrainName())) {
				hql.append(" and s.trainName like '%"+entity.getTrainName()+ "%'");
			}
			if(Common.notEmpty(entity.getLecturer())) {
				hql.append(" and s.lecturer like '%"+entity.getLecturer()+ "%'");
			}
			if(entity.getStatus() != null) {
				hql.append(" and s.status = "+entity.getStatus());
			}
			if(Common.notEmpty(entity.getStartDate())) {
				hql.append(" and s.trainDate >= date('"+entity.getStartDate()+ "')");
			}
			if(Common.notEmpty(entity.getEndDate())){
				hql.append(" and s.trainDate <= date('"+entity.getEndDate()+ "')");
			}
			if (Common.notEmpty(dataAuthority)) {
				hql.append(" and s.companyId in (" + dataAuthority + ")");
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
	public Integer save(BimrTrain entity) {
		return (Integer) sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void update(BimrTrain entity) {
		sessionFactory.getCurrentSession().update(entity);
	}
	
}
