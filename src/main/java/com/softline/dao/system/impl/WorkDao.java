package com.softline.dao.system.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.system.IWorkDao;
import com.softline.entity.PortalWork;
import com.softline.entity.SysUsers;

@Repository(value = "workDao")
public class WorkDao implements IWorkDao {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public List<PortalWork> getPortalList(SysUsers usersEntity,String beginDate,String endDate){
		StringBuffer hql = new StringBuffer();
		if(usersEntity != null){
			hql.append("from PortalWork t where t.delFlag = 0 and t.hhusers = "+usersEntity.getVcEmployeeId());
			hql.append(" and t.date >= '"+beginDate+"' and  t.date <= '"+endDate+"' ");
			hql.append(" order by t.date asc");
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			return query.list();
		}else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PortalWork> getWorkListByCurDate(String curDate, String VcEmployeeId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from PortalWork t where t.delFlag = 0 and t.hhusers = "+VcEmployeeId);
		if(Common.notEmpty(curDate)){
			hql.append(" and t.date >= '"+curDate+"'");
		}
		hql.append(" order by t.date asc");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
}
