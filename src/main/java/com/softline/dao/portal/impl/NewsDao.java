package com.softline.dao.portal.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.portal.INewsDao;
import com.softline.entity.KnowledgeBase;
import com.softline.entity.PortalMsg;
import com.softline.entity.PortalNews;

@Repository(value = "newsDao")
public class NewsDao implements INewsDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer getAllRowCount(PortalNews portalNews) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(t.id) from portal_news t where t.del_flag = 0 ");
		if(Common.notEmpty(portalNews.getTitle())){
			sql.append(" and t.title like '%" + portalNews.getTitle() + "%'");
		}
		if(Common.notEmpty(portalNews.getDescription())){
			sql.append(" and t.description like '%" + portalNews.getDescription() + "%'");
		}
		if(portalNews.getType() != null){
			sql.append(" and t.type = " + portalNews.getType());
		}
		if(portalNews.getIsIssue() != null){
			sql.append(" and t.is_issue = " + portalNews.getIsIssue());
		}
		sql.append(" order by t.create_time desc");
		Query query= (Query) sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return Integer.parseInt(query.list().get(0).toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PortalNews> getPortalMsgList(PortalNews portalNews, int offset,
			Integer pageSize) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from PortalNews t where t.delFlag = 0 ");
		if(Common.notEmpty(portalNews.getTitle())){
			hql.append(" and t.title like '%" + portalNews.getTitle() + "%'");
		}
		if(Common.notEmpty(portalNews.getDescription())){
			hql.append(" and t.description like '%" + portalNews.getDescription() + "%'");
		}
		if(portalNews.getType() != null){
			hql.append(" and t.type = " + portalNews.getType());
		}
		if(portalNews.getIsIssue() != null){
			hql.append(" and t.isIssue = " + portalNews.getIsIssue());
		}
		hql.append(" order by t.createTime desc");
		Query query= (Query) sessionFactory.getCurrentSession().createQuery(hql.toString());    
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);            
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PortalNews> getPortalNewsList(PortalNews portalNews) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from PortalNews t where t.delFlag = 0 ");
		if(Common.notEmpty(portalNews.getTitle())){
			hql.append(" and t.title like '%" + portalNews.getTitle() + "%'");
		}
		if(Common.notEmpty(portalNews.getDescription())){
			hql.append(" and t.description like '%" + portalNews.getDescription() + "%'");
		}
		if(portalNews.getType() != null){
			hql.append(" and t.type = " + portalNews.getType());
		}
		if(portalNews.getIsIssue() != null){
			hql.append(" and t.isIssue = " + portalNews.getIsIssue());
		}
		hql.append(" order by t.createTime desc");
		Query query= (Query) sessionFactory.getCurrentSession().createQuery(hql.toString());    
		return query.list();
	}

	@Override
	public PortalNews getPortalNews(Integer id) {
		String hql = " from PortalNews s where s.delFlag=0 and s.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (PortalNews)query.uniqueResult();
	}
}
