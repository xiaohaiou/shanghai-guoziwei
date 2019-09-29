package com.softline.dao.social.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Base;
import com.softline.common.Common;
import com.softline.dao.social.ISocialConsensusDao;
import com.softline.entity.DataSocialConsensus;
import com.softline.util.DateUtils;
import com.softline.util.StringUtil;

@Repository(value = "socialConsensusDao")
/**
 * 
 * @author zy
 *
 */
public class SocialConsensusDao implements ISocialConsensusDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 单个主键查询
	 * 
	 * @param id
	 * @return
	 */
	public DataSocialConsensus getSocialConsensus(Integer id) {
		if (id == null) {
			return new DataSocialConsensus();
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" from DataSocialConsensus s where 1=1 and isdel=0 and id="
				+ id);
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return (DataSocialConsensus) query.uniqueResult();
	}

	/**
	 * 一览查询画面检索
	 * 
	 * @param entity
	 * @param offsize
	 * @param pagesize
	 * @param fun
	 * @return
	 */
	public List<DataSocialConsensus> getSocialConsensusList(
			DataSocialConsensus entity, Integer offsize, Integer pagesize,
			Integer fun) {
		if (entity == null) {
			return new ArrayList<DataSocialConsensus>();
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" from DataSocialConsensus s where 1=1 and isdel=0 ");
		if (fun.equals(Base.fun_examine)) {
			hql.append(" AND s.status <> 50112 ");
		}
		if (entity != null) {
			if (!StringUtil.isNull(entity.getDateFrom())) {
				Integer from = DateUtils.getWeekSub(entity.getDateFrom());
				hql.append(" and s.week >= " + from + " ");
			}
			if (!StringUtil.isNull(entity.getDateTo())) {
				Integer to = DateUtils.getWeekSub(entity.getDateTo());
				hql.append(" and s.week <= " + to + " ");
			}
			if (entity.getStatus() != null) {
				hql.append(" and s.status = " + entity.getStatus() + " ");
			}
			if (entity.getId() != null) {
				hql.append(" and s.id = " + entity.getId() + " ");
			}
		}
		hql.append(" order by dateFrom desc,dateTo desc ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		if (offsize != null) {
			query.setFirstResult(offsize);
		}
		if (pagesize != null) {
			query.setMaxResults(pagesize);
		}
		return query.list();
	}

	/**
	 * 数据件数查询
	 * 
	 * @param entity
	 * @return
	 */
	public int getSocialConsensusListCount(DataSocialConsensus entity,
			Integer fun) {
		if (entity == null)
			return 0;
		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from DataSocialConsensus s where 1=1 and isdel=0 ");
		if (fun.equals(Base.fun_examine)) {
			hql.append(" AND s.status <> 50112 ");
		}
		if (entity != null) {
			if (!StringUtil.isNull(entity.getDateFrom())) {
				Integer from = DateUtils.getWeekSub(entity.getDateFrom());
				hql.append(" and s.week >= " + from + " ");
			}
			if (!StringUtil.isNull(entity.getDateTo())) {
				Integer to = DateUtils.getWeekSub(entity.getDateTo());
				hql.append(" and s.week <= " + to + " ");
			}
			if (entity.getStatus() != null) {
				hql.append(" and s.status = " + entity.getStatus() + " ");
			}
			if(entity.getId() != null){
				hql.append(" and s.id = " + entity.getId());
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	/**
	 * 新增时校验重复的方法
	 * 
	 * @param entity
	 * @return
	 */
	public boolean saveSocialConsensusRepeatCheck(DataSocialConsensus entity,
			String type) {

		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from DataSocialConsensus s where 1=1 and isdel=0  ");
		if (entity != null) {
			if(null!= entity.getYear())
				hql.append(" and s.year = " + entity.getYear() + " ");
			if(null!=entity.getWeek())
				hql.append(" and s.week = " + entity.getWeek() + " ");
			if (type.equals("report")) {
				hql.append(" and s.status = " + Base.examstatus_2 + " ");
			}
			if (entity.getId() != null) {
				hql.append(" and s.id !=" + entity.getId() + "");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		Integer a = Integer.parseInt(query.uniqueResult().toString());
		if (a != null && a.equals(0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 保存时检查数据是否被能修改
	 * 
	 * @param entity
	 * @return
	 */
	public boolean checkCanEdit(DataSocialConsensus entity) {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from DataSocialConsensus s where 1=1 and isdel=0 and status in("
				+ Base.examCanEdit + ") ");
		if (entity != null) {
			if (entity.getId() != null) {
				hql.append(" and id =" + entity.getId() + "");
			} else {
				return true;
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		Integer a = Integer.parseInt(query.uniqueResult().toString());
		if (a != null && a.equals(0)) {
			return false;
		} else {
			return true;
		}
	}
}
