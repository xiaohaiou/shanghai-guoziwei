package com.softline.dao.portal.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softline.dao.portal.IHhUsersDao;
import com.softline.entity.HhUsers;
@Repository("hhUsersDao")
public class HhUsersDao implements IHhUsersDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HhUsers> getUsersByCondition(HhUsers condi, Integer offset, Integer pageSize) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from HhUsers t  where 1=1 and t.cflag = 1 and t.vcAccount is not null and t.vcAccount <> '' ");
		if(StringUtils.isNotBlank(condi.getVcAccount())) {
			hql.append(" and t.vcAccount like '%").append(condi.getVcAccount()).append( "%'");
		}
		if(StringUtils.isNotBlank(condi.getVcName())) {
			hql.append(" and t.vcName like '%").append(condi.getVcName()).append("%'");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(offset != null && pageSize!= null) {
			query.setMaxResults(pageSize);
			query.setFirstResult(offset);
		}
		return query.list();
	}

	@Override
	public Integer getUsersCountByCondition(HhUsers condi) {
		StringBuffer hql = new StringBuffer();
		hql.append("select *  from hh_users t  where 1=1 and t.cFlag = 1 and t.vcAccount is not null and t.vcAccount <> ''");
		if(StringUtils.isNotBlank(condi.getVcAccount())) {
			hql.append(" and t.vcAccount like '%").append(condi.getVcAccount()).append( "%'");
		}
		if(StringUtils.isNotBlank(condi.getVcName())) {
			hql.append(" and t.vcName like '%").append(condi.getVcName()).append("%'");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql.toString());
		return query.list().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HhUsers> getUsersByAccount(String account) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from HhUsers t  where 1=1 and t.vcAccount ='").append(account).append("'");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HhUsers> getUsersByVcEmployee(String vcEmployee) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from HhUsers t  where 1=1 and t.vcEmployeeId ='").append(vcEmployee).append("'");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

}
