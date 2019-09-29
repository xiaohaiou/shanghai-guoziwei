package com.softline.dao.project.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.project.IProjectDao;
import com.softline.entity.project.PjContract;
import com.softline.entity.project.PjProject;
import com.softline.entity.project.PjProjectHistory;
import com.softline.entity.project.PjProjectvideo;

@Repository("projectDao")
public class ProjectDao implements IProjectDao{
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<PjProject> getPjProjects(String ids,String pjDeptId,PjProject condition, Integer offset,
			Integer pageSize) {
		List<PjProject> result = new ArrayList<PjProject>();
		if(Common.notEmpty(ids)){
			StringBuffer hql = new StringBuffer();
			hql.append("from  PjProject t where t.isdel = 0");
			if(!ids.equals("view")){//不是查看页面，是操作列表页
				hql.append(" and t.id in (").append(ids).append(")");
			}
			if(Common.notEmpty(condition.getPjName())){
				hql.append(" and t.pjName like '%" + condition.getPjName()+"%'");
			}
			if(Common.notEmpty(condition.getPjDept())){
				hql.append(" and t.pjDept like '%" + condition.getPjDept() + "%'");
			}
			if(Common.notEmpty(pjDeptId)){
				hql.append(" and t.pjDeptId in(" + pjDeptId + ")");
			}
			hql.append(" order by pjSort asc");
			Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
			if(offset != null && pageSize!=null){
				query.setFirstResult(offset);
				query.setMaxResults(pageSize);
			}
			result = query.list();
		}
		return result;
	}

	@Override
	public List<PjProjectHistory> getPjProjectHistories(Integer pjId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from  PjProjectHistory t where t.isdel = 0");
		if(pjId != null){
			hql.append(" and t.projectId =" + pjId);
		}
		hql.append(" order by t.reportTime desc");
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<PjProject> getAllPjProjects() {
		StringBuffer hql = new StringBuffer();
		hql.append("from  PjProject t where t.isdel = 0");
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		
		return  query.list();
	}

	@Override
	public PjProjectvideo getProjectVideoByPjId(Integer pjId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from  PjProjectvideo t where t.isdel = 0 and t.pjId=").append(pjId);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return (PjProjectvideo) query.uniqueResult();
	}

	@Override
	public PjProjectHistory getMaxVersionProject(Integer pjId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from  PjProjectHistory t where t.isdel = 0");
		if(pjId != null){
			hql.append(" and t.projectId =" + pjId);
		}
		hql.append(" order by t.version desc");
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.setMaxResults(1);
		return (PjProjectHistory) query.uniqueResult();
	}

	

}
