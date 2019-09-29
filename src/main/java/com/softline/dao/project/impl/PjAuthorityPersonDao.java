package com.softline.dao.project.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.project.IPjAuthorityPersonDao;
import com.softline.entity.project.PjAuthorityPerson;
@Repository("pjAuthorityPersonDao")
public class PjAuthorityPersonDao implements IPjAuthorityPersonDao{

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public List<PjAuthorityPerson> getPjAuthorityPerson(
			PjAuthorityPerson condition,Integer offset,Integer pageSize) {
		StringBuffer hql = new StringBuffer();
		hql.append("from PjAuthorityPerson t where t.isdel = 0");
		if(condition.getPjId() != null){
			hql.append(" and t.pjId = ").append(condition.getPjId());
		}
		if(condition.getPersonType() != null){
			hql.append(" and t.personType = ").append(condition.getPersonType());
		}
		if(Common.notEmpty(condition.getPersonVcEmployeeId())){
			hql.append(" and t.personVcEmployeeId = '").append(condition.getPersonVcEmployeeId()).append("'");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		
		if(offset != null && pageSize!=null){
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
		}
		
		return query.list();
	}

}
