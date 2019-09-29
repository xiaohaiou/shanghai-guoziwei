package com.softline.dao.project.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.dao.project.IPjCodeDao;
import com.softline.entity.project.PjCode;
@Repository("pjCodeDao")
public class PjCodeDao implements IPjCodeDao{
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<PjCode> getPjCodes(Integer codeType) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from PjCode t where  t.codeType = " + codeType);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

}
