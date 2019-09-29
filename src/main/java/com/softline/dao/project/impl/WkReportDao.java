package com.softline.dao.project.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.dao.project.IWkReportDao;
import com.softline.entity.project.PjNode;
import com.softline.entity.project.PjWeekreport;
import com.softline.entity.project.PjWeekreportHistory;
@Repository("wkReportDao")
public class WkReportDao implements IWkReportDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<PjWeekreport> getWkReports(Integer pjId, Integer offset,
			Integer pageSize) {
		String hql = " from PjWeekreport t where t.isdel = 0 and t.pjId = " + pjId;
		hql = hql + " order by t.wrYear desc,t.wrWeek desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(offset != null && pageSize!=null){
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
		}
		List<PjWeekreport> list = query.list();
		return list;
	}

	@Override
	public List<PjWeekreport> getWkReports(Integer pjId, Integer reportStatus) {
		StringBuffer hql  =new StringBuffer();
		hql.append(" from PjWeekreport t where t.isdel = 0");
		if(pjId != null){
			hql.append(" and t.pjId = " + pjId);
		}
		if(reportStatus != null){
			hql.append(" and t.reportStatus = " + reportStatus);
		}
		hql.append(" order by t.wrYear desc,t.wrWeek desc");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		List<PjWeekreport> list = query.list();
		return list;
	}

	@Override
	public List<PjWeekreportHistory> getPjWeekreportHistories(Integer wkId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from  PjWeekreportHistory t where t.isdel = 0");
		if(wkId != null){
			hql.append(" and t.wkReportId =" + wkId);
		}
		hql.append(" order by t.reportTime desc");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}
}
