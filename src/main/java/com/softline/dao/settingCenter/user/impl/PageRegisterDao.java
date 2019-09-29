package com.softline.dao.settingCenter.user.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.settingCenter.user.IPageRegisterDao;
import com.softline.entity.settingCenter.HhPageregister;

@Repository(value = "pageRegisterDao")
public class PageRegisterDao implements IPageRegisterDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public Integer getAllRowCount(String qPageNum, String qPageName, String qModelId) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(id) from hh_pageregister h where h.is_del=0 ");
		if (qPageName != null && !qPageName.equals("")) {
			sql.append(" and h.page_name like '%" + qPageName + "%' ");
		}
		if (qPageNum != null && !qPageNum.equals("")) {
			sql.append(" and h.page_num like '%" + qPageNum + "%' ");
		}
		if (qModelId != null && !qModelId.equals("")) {
			sql.append(" and h.model_id = " + Integer.parseInt(qModelId));
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return Integer.parseInt(query.list().get(0).toString());
	}

	@Override
	public List<HhPageregister> getPageRegisterList(String qPageNum, String qPageName, String qModelId, int offset,
			Integer pageSize) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append(" from HhPageregister h where h.isDel=0 ");
		if (qPageName != null && !qPageName.equals("")) {
			hql.append(" and h.pageName like '%" + qPageName + "%' ");
		}
		if (qPageNum != null && !qPageNum.equals("")) {
			hql.append(" and h.pageNum like '%" + qPageNum + "%' ");
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
	public HhPageregister getThePageRegister(Integer id) {
		// TODO Auto-generated method stub
		String hql = (" from HhPageregister h where h.id = " + id + " and h.isDel = 0");
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (HhPageregister)query.uniqueResult();
	}

	@Override
	public void saveOrUpdatePageRegister(HhPageregister register) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(register);
	}

	@Override
	public void deletePageRegister(Integer id) {
		// TODO Auto-generated method stub
		String hql = "update HhPageregister h set h.isDel = 1 where h.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public List<HhPageregister> getRegistedList() {
		// TODO Auto-generated method stub
		String hql = (" from HhPageregister h where h.isDel = 0 ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<HhPageregister> getPagesByModelId(Integer modelId) {
		// TODO Auto-generated method stub
		String hql = "from HhPageregister h where h.isDel = 0 and h.modelId = " + modelId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void deletePageCodeByPageId(Integer id) {
		// TODO Auto-generated method stub
		String hql = "update HhPagecode h set h.isDel = 1 where h.pageId = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public List<HhPageregister> getPagesByRoleId(Integer id) {
		// TODO Auto-generated method stub
		String hql = " from HhPageregister h where h.isDel = 0 and h.id in (select r.pageId from PortalHhRolepage r where r.roleId = " + id + ")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<HhPageregister> getRolePageListByModelIdAndRoleId(
			Integer modelId, Integer roleId) {
		// TODO Auto-generated method stub
		String hql = " from HhPageregister h where h.isDel = 0 and h.id in (select r.pageId from PortalHhRolepage r where r.roleId = " + roleId + " and r.modelId = " + modelId + ")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public Integer checkPageNum(String pageNum, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from HhPageregister h where h.isDel = 0 and h.id != " + id + " and h.pageNum = '" + pageNum + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list().size();
	}
//	分页
	@Override
	public Integer getPagePersonlistCount(Integer id, String vcName,String vcAccount) {
		StringBuilder sql =new StringBuilder();
		sql.append("SELECT count(0) from hh_users u where u.vcEmployeeID in ");
		sql.append(" ( SELECT s.vcEmployeeID from hh_role r ,hh_rolepage m ,hh_usersrole s where r.id = m.role_id and m.page_id = "+id+" and s.role_id = r.id  ");
		sql.append(" and r.is_del = 0 and r.is_use = 0 and r.role_status = 1 ) ");
		if (Common.notEmpty(vcName)) {
			sql.append(" and u.vcName like '%" + vcName + "%' ");
		}
		if (Common.notEmpty(vcAccount)) {
			sql.append(" and u.vcAccount like '%" + vcAccount + "%' ");
		}
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return Integer.parseInt(query.uniqueResult().toString());
	}
//	查看
	@Override
	public List<Object[]> getPagePersonlist(Integer id, String vcName,String vcAccount,Integer offset,Integer pageSize) {
		StringBuilder sql =new StringBuilder();
		sql.append("SELECT u.vcName,u.vcAccount,u.vcFullName from hh_users u where u.vcEmployeeID in ");
		sql.append(" ( SELECT s.vcEmployeeID from hh_role r ,hh_rolepage m ,hh_usersrole s where r.id = m.role_id and m.page_id = "+id+" and s.role_id = r.id  ");
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
}
