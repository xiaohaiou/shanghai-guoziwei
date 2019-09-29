package com.softline.dao.project.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.dao.project.IPjPcPayrecordDao;
import com.softline.entity.project.PjPcPayrecord;
import com.softline.entity.project.PjPcPayrecordTemp;
@Repository("pjPcPayrecordDao")
public class PjPcPayrecordDao implements IPjPcPayrecordDao{
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<PjPcPayrecordTemp> getPayrecordTemps(Integer contractTempId) {
		StringBuffer hql =new  StringBuffer();
		hql.append(" from PjPcPayrecordTemp t where t.isdel = 0 and t.pcId = " + contractTempId );
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<PjPcPayrecord> getPayrecords(Integer contractId) {
		StringBuffer hql =new  StringBuffer();
		hql.append(" from PjPcPayrecord t where t.isdel = 0 and t.pcId = " + contractId );
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

}
