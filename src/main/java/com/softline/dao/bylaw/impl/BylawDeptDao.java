package com.softline.dao.bylaw.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.dao.bylaw.IBylawDeptDao;
import com.softline.entity.bylaw.BylawDept;
@Repository("bylawDeptDao")
public class BylawDeptDao implements IBylawDeptDao{
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public List<BylawDept> getBylawDepts(BylawDept condition, Integer offset,
			Integer pageSize) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from BylawDept t where 1=1");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		
		if(offset != null && pageSize!=null){
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
		}
		return query.list();
	}
	
}
