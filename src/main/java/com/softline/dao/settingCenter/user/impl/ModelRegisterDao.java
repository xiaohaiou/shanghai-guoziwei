package com.softline.dao.settingCenter.user.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.common.Common;
import com.softline.dao.settingCenter.user.IModelRegisterDao;
import com.softline.entity.settingCenter.HhModelRegister;

@Repository(value = "modelRegisterDao")
public class ModelRegisterDao implements IModelRegisterDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public Integer getAllRowCount(String qModelNum, String qModelName, String qSysId) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(id) from hh_modelregister h where h.is_del=0 ");
		if (qModelName != null && !qModelName.equals("")) {
			sql.append(" and h.model_name like '%" + qModelName + "%' ");
		}
		if (qModelNum != null && !qModelNum.equals("")) {
			sql.append(" and h.model_num like '%" + qModelNum + "%' ");
		}
		if (qSysId != null && !qSysId.equals("")) {
			sql.append(" and h.sys_id = " + Integer.parseInt(qSysId));
		}
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return Integer.parseInt(query.list().get(0).toString());
	}

	@Override
	public List<HhModelRegister> getModelRegisterList(String qModelNum, String qModelName, String qSysId, int offset,
			Integer pageSize) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append(" from HhModelRegister h where h.isDel=0 ");
		if (qModelName != null && !qModelName.equals("")) {
			hql.append(" and h.modelName like '%" + qModelName + "%' ");
		}
		if (qModelNum != null && !qModelNum.equals("")) {
			hql.append(" and h.modelNum like '%" + qModelNum + "%' ");
		}
		if (qSysId != null && !qSysId.equals("")) {
			hql.append(" and h.sysId = " + Integer.parseInt(qSysId));
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public HhModelRegister getTheModelRegister(Integer id) {
		// TODO Auto-generated method stub
		String hql = (" from HhModelRegister h where h.id = " + id + " and h.isDel = 0");
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (HhModelRegister)query.uniqueResult();
	}

	@Override
	public void saveOrUpdateModelRegister(HhModelRegister register) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(register);
	}

	@Override
	public void deleteModelRegister(Integer id) {
		// TODO Auto-generated method stub
		String hql = "update HhModelRegister h set h.isDel = 1 where h.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public List<HhModelRegister> getRegistedList() {
		// TODO Auto-generated method stub
		String hql = (" from HhModelRegister h where h.isDel = 0 ");
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<HhModelRegister> getModelsBySysId(Integer sysId) {
		// TODO Auto-generated method stub
		String hql = "from HhModelRegister h where h.isDel = 0 and h.sysId = " + sysId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<HhModelRegister> getModelsByRoleId(Integer id) {
		// TODO Auto-generated method stub
		String hql = " from HhModelRegister h where h.isDel = 0 and h.id in (select r.modelId from HhRoleModel r where r.roleId = " + id + ")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<HhModelRegister> getRegistedModelList(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = " from HhModelRegister h where h.isDel = 0 and h.sysId in (select r.sysId from HhRoleSys r where r.roleId = " + roleId + ")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<HhModelRegister> getRegistedModelListBySysId(Integer sysId) {
		// TODO Auto-generated method stub
		String hql = " from HhModelRegister h where h.isDel = 0 and h.sysId = " + sysId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<HhModelRegister> getRoleModelListBySysIdAndRoleId(
			Integer sysId, Integer roleId) {
		// TODO Auto-generated method stub
		String hql = " from HhModelRegister h where h.isDel = 0 and h.id in (select r.modelId from HhRoleModel r where r.roleId = " + roleId + " and r.sysId = " + sysId + ")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public Integer checkModelNum(String modelNum, Integer id) {
		// TODO Auto-generated method stub
		String hql = "from HhModelRegister h where modelNum = '" + modelNum + "' and h.id != " + id + " and h.isDel = 0";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list().size();
	}

	@Override
	public Integer getModelPersonlistCount(Integer id,String vcName,String vcAccount) {
		// TODO Auto-generated method stub
		StringBuilder sql =new StringBuilder();
		sql.append("SELECT u.vcName,u.vcAccount,u.vcFullName from hh_users u where u.vcEmployeeID in ");
		sql.append(" ( SELECT s.vcEmployeeID from hh_role r ,hh_rolemodel m ,hh_usersrole s where r.id = m.role_id and m.model_id = "+id+" and s.role_id = r.id  ");
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getModelPersonlist(Integer id,String vcName,String vcAccount, Integer offset,
			Integer pageSize) {
		// TODO Auto-generated method stub
		StringBuilder sql =new StringBuilder();
		sql.append("SELECT u.vcName,u.vcAccount,u.vcFullName from hh_users u where u.vcEmployeeID in ");
		sql.append(" ( SELECT s.vcEmployeeID from hh_role r ,hh_rolemodel m ,hh_usersrole s where r.id = m.role_id and m.model_id = "+id+" and s.role_id = r.id  ");
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
