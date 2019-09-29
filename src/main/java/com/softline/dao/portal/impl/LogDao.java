package com.softline.dao.portal.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.portal.ILogDao;
import com.softline.entity.HhOperationLog;

@Repository(value = "logDao")
public class LogDao implements ILogDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer getAllRowCount(HhOperationLog log) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(t.id) from hh_operation_log t where 1=1 ");
		if(Common.notEmpty(log.getName())){
			sql.append(" and t.name like '%" + log.getName() + "%'");
		}
		sql.append(" order by t.operation_time desc");
		Query query= (Query) sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return Integer.parseInt(query.list().get(0).toString());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HhOperationLog> getPortalLogList(HhOperationLog log, int offset, Integer pageSize) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from HhOperationLog t where 1=1 ");
		if(log.getName() != null){
			hql.append(" and t.name like '%" + log.getName() + "' ");
		}
		hql.append(" order by t.operationTime desc");
		Query query= (Query) sessionFactory.getCurrentSession().createQuery(hql.toString());    
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);            
		return query.list();
	}
}
