package com.softline.dao.project.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.dao.project.INodeDao;
import com.softline.entity.project.PjNode;
import com.softline.entity.project.PjNodeHistory;
import com.softline.util.MsgPage;
@Repository("nodeDao")
public class NodeDao implements INodeDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public Integer getNodeLastVersion(Integer pjId) {
		String hql = " from PjNode t where t.isdel = 0 and t.pjId = " + pjId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<PjNode> list = query.list();
		if(list.size()> 0){
			return list.get(0).getVersion();
		}else{
			return 0;
		}
	}

	@Override
	public List<PjNode> getNodes(Integer pjId, Integer offset, Integer pageSize) {
		String hql = " from PjNode t where t.isdel = 0 and t.pjId = " + pjId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(offset != null && pageSize!=null){
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
		}
		List<PjNode> list = query.list();
		return list;
	}

	@Override
	public List<PjNode> getNodes(Integer pjId, Integer reportStauts) {
		String hql = " from PjNode t where t.isdel = 0 and t.pjId = " + pjId +" and t.reportStatus = " + reportStauts;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<PjNode> list = query.list();
		return list;
	}

	@Override
	public List<PjNodeHistory> getNodehHistories(Integer nodeId) {
		String hql = " from PjNodeHistory t where t.isdel = 0 and t.nodeId = " + nodeId + "order by t.reportTime desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	
}
