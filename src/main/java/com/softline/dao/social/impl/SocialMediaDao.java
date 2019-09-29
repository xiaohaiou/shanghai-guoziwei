package com.softline.dao.social.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Base;
import com.softline.dao.social.ISocialMediaDao;
import com.softline.entity.DataSocialMedia;

@Repository(value = "socialMediaDao")
/**
 * 
 * @author zy
 *
 */
public class SocialMediaDao implements ISocialMediaDao {
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
	public DataSocialMedia getSocialMedia(Integer id) {
		if (id == null) {
			return new DataSocialMedia();
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" from DataSocialMedia s where 1=1 and isdel=0 and id=" + id);
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return (DataSocialMedia) query.uniqueResult();
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
	public List<DataSocialMedia> getSocialMediaList(DataSocialMedia entity,
			Integer offsize, Integer pagesize, Integer fun) {
		if (entity == null) {
			return new ArrayList<DataSocialMedia>();
		}
		StringBuilder hql = new StringBuilder();
		hql.append(" from DataSocialMedia s where 1=1 and isdel=0 ");
		if (fun.equals(Base.fun_examine)) {
			hql.append(" AND s.status <> 50112 ");
		}
		if (entity != null) {
			if (entity.getMonth() != null) {
				hql.append(" and s.month = " + entity.getMonth() + " ");
			}
			if (entity.getYear() != null) {
				hql.append(" and s.year = " + entity.getYear() + " ");
			}
			if (entity.getStatus() != null) {
				hql.append(" and s.status = " + entity.getStatus() + " ");
			}
			if (entity.getId() != null) {
				hql.append(" and s.id = " + entity.getId() + " ");
			}
		}
		hql.append(" order by year desc,month desc ");
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
	public int getSocialMediaListCount(DataSocialMedia entity, Integer fun) {
		if (entity == null)
			return 0;
		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from DataSocialMedia s where 1=1 and isdel=0 ");
		if (fun.equals(Base.fun_examine)) {
			hql.append(" AND s.status <> 50112 ");
		}
		if (entity != null) {
			if (entity.getMonth() != null) {
				hql.append(" and s.month = " + entity.getMonth() + " ");
			}
			if (entity.getYear() != null) {
				hql.append(" and s.year = '" + entity.getYear() + "' ");
			}
			if (entity.getStatus() != null) {
				hql.append(" and s.status = '" + entity.getStatus() + "' ");
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
	public boolean saveSocialMediaRepeatCheck(DataSocialMedia entity,
			String type) {

		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from DataSocialMedia s where 1=1 and isdel=0  ");
		if (entity != null) {
			if (entity.getMonth() != null) {
				hql.append(" and s.month = " + entity.getMonth() + " ");
			}
			if (entity.getYear() != null) {
				hql.append(" and s.year = " + entity.getYear() + " ");
			}
			if (entity.getMediaClass() != null) {
				hql.append(" and s.mediaClass = " + entity.getMediaClass()
						+ " ");
			}
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
	public boolean checkCanEdit(DataSocialMedia entity) {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(0) from DataSocialMedia s where 1=1 and isdel=0 and status in("
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
