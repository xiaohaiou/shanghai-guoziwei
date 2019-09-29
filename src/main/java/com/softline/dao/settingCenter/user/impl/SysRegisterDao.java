package com.softline.dao.settingCenter.user.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.softline.dao.settingCenter.user.ISysRegisterDao;
import com.softline.entity.settingCenter.HhSysRegister;

@Repository(value = "sysRegisterDao")
public class SysRegisterDao implements ISysRegisterDao {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public Integer getAllRowCount(String qSysNum, String qSysName) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(id) from hh_sysregister h where h.is_del=0 ");
		if (qSysName != null && !qSysName.equals("")) {
			sql.append(" and h.sys_name like '%" + qSysName + "%' ");
		}
		if (qSysNum != null && !qSysNum.equals("")) {
			sql.append(" and h.sys_num like '%" + qSysNum + "%' ");
		}
		/*if (register.getSysDescription() != null && !register.getSysDescription().equals("")) {
			sql.append(" and h.sys_description like '%" + register.getSysDescription() + "%' ");
		}*/
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
		return Integer.parseInt(query.list().get(0).toString());
	}

	@Override
	public List<HhSysRegister> getSysRegisterList(String qSysNum, String qSysName, int offset,
			Integer pageSize) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append(" from HhSysRegister h where h.isDel=0 ");
		if (qSysName != null && !qSysName.equals("")) {
			hql.append(" and h.sysName like '%" + qSysName + "%' ");
		}
		if (qSysNum != null && !qSysNum.equals("")) {
			hql.append(" and h.sysNum like '%" + qSysNum + "%' ");
		}
		/*if (register.getSysDescription() != null && !register.getSysDescription().equals("")) {
			hql.append(" and h.sysDescription like '%" + register.getSysDescription() + "%' ");
		}*/
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public HhSysRegister getTheSysRegister(Integer id) {
		// TODO Auto-generated method stub
		String hql = (" from HhSysRegister h where h.id = " + id + " and h.isDel = 0");
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (HhSysRegister)query.uniqueResult();
	}

	@Override
	public void saveOrUpdateSysRegister(HhSysRegister register) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(register);
	}

	@Override
	public void deleteSysRegister(Integer id) {
		// TODO Auto-generated method stub
		String hql = "update HhSysRegister h set h.isDel = 1 where h.id = " + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public Integer checkSysNum(String sysNum, Integer id) {
		// TODO Auto-generated method stub
		String hql = "from HhSysRegister h where h.sysNum = '" + sysNum + "' and h.id != " + id + " and h.isDel = 0";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list().size();
	}

	@Override
	public List<HhSysRegister> getRegistedList() {
		// TODO Auto-generated method stub
		String hql = "from HhSysRegister h where h.isDel = 0";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<HhSysRegister> getRegistedListByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		String hql = " from HhSysRegister h where h.isDel = 0 and h.id in ( select s.sysId from HhRoleSys s where s.roleId = " + roleId + ")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

}
