package com.softline.dao.settingCenter.user.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.settingCenter.user.IPageCodeDao;
import com.softline.entity.HhPagecode;
import com.softline.entity.settingCenter.PortalHhPagecode;

@Repository(value = "pageCodeDao")
public class PageCodeDao implements IPageCodeDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public Integer getAllRowCount(String qCodeName, String qModelId, String qPageId) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(id) from portal_hh_pagecode h where h.is_del=0 ");
		if (qCodeName != null && !qCodeName.equals("")) {
			sql.append(" and h.code_name like '%" + qCodeName + "%' ");
		}
		if (qPageId != null && !qPageId.equals("")) {
			sql.append(" and h.page_id = " + Integer.parseInt(qPageId));
		}
		if (qModelId != null && !qModelId.equals("")) {
			sql.append(" and h.model_id = " + Integer.parseInt(qModelId));
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return Integer.parseInt(query.list().get(0).toString());
	}

	@Override
	public List<PortalHhPagecode> getPageCodeList(String qCodeName, String qModelId, String qPageId, int offset,
			Integer pageSize) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append(" from PortalHhPagecode h where h.isDel=0 ");
		if (qCodeName != null && !qCodeName.equals("")) {
			hql.append(" and h.codeName like '%" + qCodeName + "%' ");
		}
		if (qPageId != null && !qPageId.equals("")) {
			hql.append(" and h.pageId = " + Integer.parseInt(qPageId));
		}
		if (qModelId != null && !qModelId.equals("")) {
			hql.append(" and h.modelId = " + Integer.parseInt(qModelId));
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public PortalHhPagecode getThePageCode(Integer id) {
		// TODO Auto-generated method stub
		String hql = (" from PortalHhPagecode h where h.id = " + id + " and h.isDel = 0");
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (PortalHhPagecode)query.uniqueResult();
	}

	@Override
	public void saveOrUpdatePageCode(PortalHhPagecode register) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(register);
	}

	@Override
	public void deletePageCode(Integer id) {
		// TODO Auto-generated method stub
		String hql = "update PortalHhPagecode h set h.isDel = 1 where h.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PortalHhPagecode> getCodeList(Integer sysId, Integer pageId) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from PortalHhPagecode h where h.isDel=0 ");
		if (sysId != null) {
			hql.append(" and h.sysId = " + sysId);
		}
		if (pageId != null ) {
			hql.append(" and h.pageId = " + pageId);
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<PortalHhPagecode> getPagecodesByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = " from PortalHhPagecode h where h.isDel = 0 and h.id in (select c.codeId from HhRolepagecode c where c.roleId = " + roleId + ")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<PortalHhPagecode> getPagecodesByModelIdAndRoleId(Integer id,
			Integer roleId) {
		// TODO Auto-generated method stub
		String hql = " from PortalHhPagecode h where h.isDel = 0 and h.id in (select c.codeId from HhRolepagecode c where c.roleId = " + roleId + " and c.modelId = " + id + ")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public Integer checkPagecodeNum(String pagecodeNum, Integer id) {
		// TODO Auto-generated method stub
		String hql = "from PortalHhPagecode h where h.code = '" + pagecodeNum + "' and h.id != " + id + " and h.isDel = 0";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list().size();
	}


	
	@Override
	public Integer getPageCodePersonlistCount(Integer id, String vcName,String vcAccount) {
		StringBuilder sql =new StringBuilder();
		sql.append("SELECT u.vcName,u.vcAccount,u.vcFullName from hh_users u where u.vcEmployeeID in ");
		sql.append(" ( SELECT s.vcEmployeeID from hh_role r ,hh_rolepagecode m ,hh_usersrole s where r.id = m.role_id and m.code_id = "+id+" and s.role_id = r.id  ");
		sql.append(" and r.is_del = 0 and r.is_use = 0 and r.role_status = 1 ) ");
		if (Common.notEmpty(vcName)) {
			sql.append(" and u.vcName like '%" + vcName + "%' ");
		}
		if (Common.notEmpty(vcAccount)) {
			sql.append(" and u.vcAccount like '%" + vcAccount + "%' ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return query.list().size();
	}
//	查看
	@Override
	public List<Object[]> getPageCodePersonlist(Integer id, String vcName,String vcAccount,Integer offset,Integer pageSize) {
		StringBuilder sql =new StringBuilder();
		sql.append("SELECT u.vcName,u.vcAccount,u.vcFullName from hh_users u where u.vcEmployeeID in ");
		sql.append(" ( SELECT s.vcEmployeeID from hh_role r ,hh_rolepagecode m ,hh_usersrole s where r.id = m.role_id and m.code_id = "+id+" and s.role_id = r.id  ");
		sql.append(" and r.is_del = 0 and r.is_use = 0 and r.role_status = 1 ) ");
		if (Common.notEmpty(vcName)) {
			sql.append(" and u.vcName like '%" + vcName + "%' ");
		}
		if (Common.notEmpty(vcAccount)) {
			sql.append(" and u.vcAccount like '%" + vcAccount + "%' ");
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		if (null!=offset) {
			query.setFirstResult(offset);
		}
		if(null!=pageSize){
			query.setMaxResults(pageSize);
		}
		
		return query.list();
	
	}
	

	/*@Override
	public Integer checkSysNum(String sysNum) {
		// TODO Auto-generated method stub
		String hql = "from HhSysRegister h where sysNum = '" + sysNum + "' and h.isDel = 0";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list().size();
	}*/

}
